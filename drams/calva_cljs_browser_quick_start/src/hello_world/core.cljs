(ns hello-world.core)

(println "Hello ClojureScript World!")

(comment
  ;; Evaluate these forms by placing the cursor in them
  ;; and press Alt+Enter. See also the command:
  ;; *Fire up the Getting Started REPL*
  (js/alert "Hello from the REPL!")
  (inc 1)
  (map inc [1 2 3])
  (-> js/document
      (.getElementById "app")
      (.-innerHTML)
      (set! "Hello ClojureScript REPL from Calva!")
      ;; reload browser page to restore the original content 😎
      ))