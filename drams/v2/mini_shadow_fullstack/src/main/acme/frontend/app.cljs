(ns acme.frontend.app)

(defn ^:dev/after-load init []
  (println "Hello World")
  (-> js/document
      (.getElementById "root")
      (.-innerHTML)
      (set! "Acme App started.")))