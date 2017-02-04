(def project 'radicalzephyr/boot-screeps)
(def version "0.1.0-SNAPSHOT")

(set-env! :resource-paths #{"src"}
          :dependencies   '[[org.clojure/clojure "1.8.0"]
                            [boot/core "2.7.1" :scope "test"]
                            [http-kit "2.2.0" :scope "test"]
                            [cheshire "5.7.0" :scope "test"]])

(task-options!
 pom {:project     project
      :version     version
      :description "Commit screeps code to a screeps server."
      :url         "https://github.com/RadicalZephyr/boot-screeps"
      :scm         {:url "https://github.com/RadicalZephyr/boot-screeps"}
      :license     {"Eclipse Public License"
                    "http://www.eclipse.org/legal/epl-v10.html"}})

(deftask build
  "Build and install the project locally."
  []
  (comp (pom) (jar) (install)))

(require '[radicalzephyr.boot-screeps :refer [boot-screeps]])
