(ns hello-world.core)

;; Evaluate these forms by placing the cursor in them
;; and press Alt+Enter.

(defn hello [s]
  (println (str "Hello " s "!")))

(hello "ClojureScript World")

(comment
  ;; This is a Rich Comment block:
  ;;   https://youtu.be/Qx0-pViyIDU?t=1229
  ;; Evaluate the forms in it the same way as the ones above.

  (inc 1)
  (map inc [1 2 3])

  (defn fizz-buzz [n]
    (cond (zero? (mod n 15)) "FizzBuzz"
          (zero? (mod n 5))  "Buzz"
          (zero? (mod n 3))  "Fizz"
          :else              n))
  ;; Evalute these steps with Ctrl+Alt+Enter after each line.
  (->> (range 1 101)
       (map fizz-buzz)
       (filter number?)
       (map inc)
       (map fizz-buzz))

  ;; Please edit the code in this block and see
  ;; what happens when you evaluate.
)

;; Learn about the Calva REPL and about Clojure with
;; the command: *Fire up the Getting Started REPL*.
