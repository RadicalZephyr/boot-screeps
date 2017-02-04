(ns radicalzephyr.boot-screeps
  {:boot/export-tasks true}
  (:require [boot.core :as boot :refer [deftask]]
            [boot.util :as util]))

(deftask boot-screeps
  "Commit screeps code to a screeps server."
  [u username USERNAME str "The username to authenticate with."
   p password PASSWORD str "The password to authenticate with."
   d dir      DIR      str "The directory in the fileset to commit to the screeps server."
   U url      URL      str "The url of the screeps server API."]
  (boot/with-pre-wrap fs
    fs))
