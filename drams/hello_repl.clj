(ns hello-repl)

;; When we refer to commands by their name, use
;; the VS Code Command palette to search for them
;; if you don't know the keyboard shortcut.
;; All Calva commands are prefixed with ”Calva”.

;; Alt+Enter is the Calva default keyboard shortcut
;; to evaluate the current ”top level” forms. Top
;; level meaning the outermost ”container” of forms, 
;; which is the file. Place the cursor anywhere
;; inside this function to give it a try.
(defn greet
  "I'll greet you"
  [name]
  (str "Hello " name "!"))

;; Forms inside `(comment ...)` are also top level
(comment
  (greet "World"))
;; You should see "Hello World!" displayed inline,
;; and also printed to the `output.calva-repl` editor,
;; aka ”The Output Window”, aka ”The REPL Window”.
;; To clear inline results display, press ESC.

;; Anything printed to stdout is not shown inline
(comment
  (println (greet "World")))
;; You should see the result of the evaluation, nil,
;; inline, and ”Hello World!” followed by the result
;; printed to the output window.

;; Ctrl+Enter evaluates the ”current” form
;; Try with the cursor at different places
(def foo
  [1 2 "three four"])
;; You might discover that Calva regards words in
;; strings as forms. Don't panic if `three` causes
;; en evauation error. It is not defined, since
;; it shouldn't be. You can define it, of course,
;; just for fun and learning: Top level eval these 
(comment
  (def three 3)
  (def four "four"))
;; Then eval current form inside the string above
;; Calva sends to the REPL whatever you ask it send.

;; Repeating an important concept: Forms inside
;; `(comment ...)` are also concidered top level
;; Alt+Enter at different places below to get a
;; feel for it.
(comment
  "I ♥️ Clojure"
  (greet "World")
  foo
  ;; Also try the commands *Show Hover*,
  ;; *Show Definition Preview Hover*
  ;; *Go to Definition*  
  (println (greet "side effect"))
  (+ (* 2 2)
     2)
  ;; Here too, if you have Java sources installed
  (Math/abs -1)
  (greet "Calva REPL")
  (range 10))
;; Google Rich Comments, if you are new
;; to this style of coding.

;; Please note that Calva never evaluates your code
;; unless you explicitly ask for it. So, except for
;; this file, you will have to load files you open
;; yourself. Make it a habit to do this, because
;; sometimes things don't work in peculiar ways when
;; your file is not loaded.
;; Try with this file: `Ctrl+Alt+C Enter`.

;; Also, once you have evaluated a form and then
;; update it. Calva will not automatically evaluate
;; the new code, or code depending on it.


;; Since evaluating Clojure expressions is so
;; easy and fun. Some times you happen to evaluate
;; something that never finishes, or takes to long
;; to finish. For this, Calva has a command named
;; *Interrupt Running Evaluations*. You will need
;; it if you top-level evaluate this:
(comment
  (def tmp1 (dorun (range))))

;; A note about editing Clojure in Calva:
;; If you edit and experiment with the examples
;; you will notice that Calva autoindents your
;; code. You can also re-indent, and format, code
;; at will, using the `tab` key.

;; More about editing:
;; Please continue to hello_paredit.clj to learn the
;; very basics of structural editing in Calva.
;; Here's maybe the most important thing from that
;; guide:
;; Paredit strict mode is active by default. 
;; It will help you to not delete brackets that
;; would break the structure of the code.
;; Use Alt+Backspace to override.
(defn strict-greet
  "Try to remove brackets and string quotes
   using Backspace or Delete. Try the same
   with the Alt key pressed."
  [name]
  (str "Strictly yours, " name "!"))
;; (Restore with *Undo* if needed.)


;; Learn much more about Calva at https://calva.io 

"hello_repl.clj is loaded, and ready with some things for you to try."

;; This guide downloaded from:
;; https://github.com/BetterThanTomorrow/dram
;; Please consider contributing.