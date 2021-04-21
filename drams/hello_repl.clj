(ns hello-repl)

;; Welcome to the Getting Started REPL!
;; There are three files for you to explore:
;; 1. hello_repl.clj (this file)
;; 2. hello_paredit.clj
;; 3. hello_clojure.clj (a Beginner's Clojure Guide)

;; The first two files are for everyone new to Calva.

;; If you are new to Clojure, then all files are
;; for you. Please don't worry if you don't understand
;; the code in the first two files. They are short and
;; made so that you can just follow along without the
;; actual code making sense yet. Things will clear up
;; once you have dug into the Clojure Guide.

;; About commands and shortcuts:
;; When we refer to commands by their name, use
;; the VS Code Command palette to search for them
;; if you don't know the keyboard shortcut.
;; All Calva commands are prefixed with ”Calva”.

;; == Evaluating definitions ==
;; Alt+Enter is the Calva default keyboard shortcut
;; to evaluate the current ”top level” forms. Top
;; level meaning the outermost ”container” of forms, 
;; which is the file. Place the cursor anywhere
;; inside this function to give it a try.

(defn greet
  "I'll greet you"
  [s]
  (str "Hello " s "!"))

;; Forms inside `(comment ...)` are also top level.

(comment
  (greet "World"))

;; You should see "Hello World!" displayed inline,
;; and also printed to the `output.calva-repl` editor,
;; aka ”The Output Window”, aka ”The REPL Window”.
;; To clear inline results display, press `ESC`.

;; Anything printed to stdout is not shown inline.

(comment
  (println (greet "World")))

;; You should see the result of the evaluation, nil,
;; inline, and ”Hello World!” followed by the result
;; printed to the output window.

;; == Evaluating the Current Form ==
;; Ctrl+Enter evaluates the ”current” form
;; Try with the cursor at different places in this
;; code snippet:

(comment
  (def foo
    [1 2 "three four"]))

;; You might discover that Calva regards words in
;; strings as forms. Don't panic if `three` causes
;; en evacuation error. It is not defined, since
;; it shouldn't be. You can define it, of course,
;; just for fun and learning: Top level eval these 

(comment
  (def three 3)
  (def four "four"))

;; Then eval current form inside the string above
;; Calva sends to the REPL whatever you ask it send.

;; == Evaluating in Threaded Expressions
;; Ctrl+Alt+Enter will evaluate the current enclosing
;; form up to the cursor. Useful in threaded
;; expressions. Say you want to calculate the average
;; ratings for the data below:

(comment
  ;; First you need to top-level evaluate this
  (def colt-express
    {:name "Colt Express"
     :categories ["Family"
                  "Strategy"]
     :play-time 40
     :ratings {:pez 5.0
               :kat 5.0
               :wiv 5.0
               :vig 3.0
               :rex 5.0
               :lun 4.0}})

  ;; And this
  (defn average [coll]
    (/ (apply + coll) (count coll)))

  ;; This too, if you like
  (->> colt-express
       :ratings
       vals
       average)
  ;; To see the result at each step in the thread
  ;; You can also Ctrl+Alt+Enter after each form.
  ;; Place the cursor after `(->> colt-express` and
  ;; try it. Then after `:ratings`, and after `vals`.
  )

;; == Rich Comments Support ==
;; Repeating an important concept: Forms inside
;; `(comment ...)` are also considered top level
;; by Calva. Alt+Enter at different places below
;; to get a feel for it.

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

;; == You Control what is Evaluated ==
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


(comment
  ;; == The Calva Debugger ==
  ;; As powerful as the REPL is, Calva actually also
  ;; has a debugger. The easiest way to use it is to
  ;; instrument a function for debugging. You do that
  ;; by having the cursor in the function and then 
  ;; use the command:
  ;; *Instrument Current Top Level Form for Debugging*
  ;; Then you call the function. This will cause the
  ;; debugger to stop at the first breakable point in
  ;; the instrumented function

  ;; Let's try it without instrumentation first. This
  ;; function has a bug. Evaluate it the usual way
  ;; (`Alt+Enter`) first and then call it.

  (defn bar
    [n]
    (cond (> n 40) (+ n 20)
          (> n 20) (- (first n) 20)
          :else 0))

  (bar 2)  ; works
  (bar 24) ; throws, what's going on?

  ;; That's a strange error message (maybe you say,
  ;; depending on how familiar you are with Clojure).
  ;; Now instrument the function as described above.
  ;; Calva will indicate code that is instrumented for
  ;; debugging. Now evaluate the problematic function
  ;; call. The debugger will start and wait for you
  ;; to step through the function. To un-instrument
  ;; the function, just evaluate it the normal way.
  ;; Debugger docs here: https://calva.io/debugger/
  )

;; == Stopping Infinite Loops ==
;; Since evaluating Clojure expressions is so
;; easy and fun. Sometimes you happen to evaluate
;; something that never finishes, or takes to long
;; to finish. For this, Calva has a command named
;; *Interrupt Running Evaluations*. You will need
;; it if you top-level evaluate this:

(comment
  (def tmp1 (dorun (range))))

;; == Editing Code ==
;; A note about editing Clojure in Calva:
;; If you edit and experiment with the examples
;; you will notice that Calva auto-indents your
;; code. You can re-indent, and format, code at
;; will, using the `Tab` key.

;; There is also some help with writing blocks of
;; line comments (like this one).
;; Alt+Enter with the cursor in a line comment
;; will ”continure” the comment on next line.
;; Try it, why don't ya! 😎
;; 
;; There is some extra help given when Calva thinks
;; you are typing a bulleted or numbered list.
;; 
;; * foo
;; * bar
;; 
;; 1. CIDER 
;; 2. Cursive

;; === More about editing ===
;; Please continue to `hello_paredit.clj` to learn
;; the very basics of structural editing in Calva.
;; Here's maybe the most important thing from that
;; guide: _Paredit strict mode is active by default_ 
;; It will help you to not delete brackets that
;; would break the structure of the code.
;; Use Alt+Backspace to override.

(comment
  (defn strict-greet
    "Try to remove brackets and string quotes
   using Backspace or Delete. Try the same
   with the Alt key pressed."
    [name]
    (str "Strictly yours, " name "!"))
  
  (strict-greet "dear Paredit fan"))

;; (Restore with *Undo* if needed.)

;; Done? Awesome. Please consider to familiarize
;; yourself more with Paredit using the interactive
;; guide. If you are new to Clojure, please
;; continue with `hello_clojure.clj`, which is an
;; interactive guide to the Clojure language.

;; Have a Clojure project you want to get hook
;; Calva up to? See: https://calva.io/connect/

;; Learn much more about Calva at https://calva.io 

"hello_repl.clj is loaded, and ready with some things for you to try."

;; This guide downloaded from:
;; https://github.com/BetterThanTomorrow/dram
;; Please consider contributing.