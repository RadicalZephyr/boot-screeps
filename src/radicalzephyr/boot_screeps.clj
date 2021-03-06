(ns radicalzephyr.boot-screeps
  {:boot/export-tasks true}
  (:require
    [boot.core :as core :refer [deftask]]
    [boot.util :as util]
    [org.httpkit.client :as http]
    [cheshire.core :as json]
    [clojure.string :as str])
  (:import
   (java.io File)))

(defn- basename [^File file]
  (-> file
      .getName
      (str/replace ".js" "")))

(deftask commit
  "Commit screeps code to a screeps server."
  [u username USERNAME str "The username to authenticate with."
   p password PASSWORD str "The password to authenticate with."
   b branch   BRANCH   str "The branch name to commit to."
   U url      URL      str "The url of the screeps server API."]

  (when-not (and username password)
    (throw (Exception. "Must supply both a username and password for API authentication.")))

  (let [branch (or branch "default")
        url (or url "https://screeps.com")
        api-url (format "%s/api/user/code" url)]
    (core/with-pre-wrap fs
      (let [js-files (->> fs core/output-files (core/by-ext [".js"]))
            modules (into {}
                          (map (comp (juxt basename slurp) core/tmp-file)
                               js-files))
            body (json/generate-string {:branch branch
                                        :modules modules})
            {json-result :body :as response}
            @(http/post api-url
                        {:as :text
                         :headers {"Content-Type" "application/json; charset=utf-8"}
                         :basic-auth [username password]
                         :body body})
            result (json/parse-string json-result)]
        (when-not (get result "ok")
          (throw (ex-info "Failed to send code to server"
                          {:result result
                           :request-body body
                           :response-body response})))
        (util/info "Successfully commited code to branch %s at timestamp %s\n" branch result))
      fs)))
