(ns repl
  (:require
   [acme.server :as srv]
   [ring.adapter.jetty :as jetty]
   #_{:clj-kondo/ignore [:unused-namespace]}
   [shadow.cljs.devtools.api :as shadow]
   [clj-reload.core :as reload]))

(defonce !jetty-ref (atom nil))

(defn ^:shadow/requires-server start! []
  ;; No need to start shadow.cljs watcher if using Calva
  #_(shadow/watch :frontend)

  (reset! !jetty-ref
          (jetty/run-jetty #'srv/handler
                           {:port 3000
                            :join? false}))
  ::started)

(defn stop! []
  (when-some [jetty @!jetty-ref]
    (reset! !jetty-ref nil)
    (.stop jetty))
  ::stopped)

(defn go! []
  (reload/init {:dirs ["src" "dev"]})
  (start!))

(defn restart! []
  (stop!)
  (reload/reload)
  (start!))
