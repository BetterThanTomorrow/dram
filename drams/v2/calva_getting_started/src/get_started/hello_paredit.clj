(ns get-started.hello-paredit)

;; Start with loading this file
;; Ctrl+Alt+C Enter

;; Clojure is a LISP and therefore the code
;; is structural. Everything is organized in
;; ”forms”, aka S-expressions (sexprs).
;; https://en.wikipedia.org/wiki/S-expression
;; A form is any literal or ”symbol” or
;; literal collection (different kinds of
;; lists) of literals. Paredit helps you take
;; advantage of this structure.

;; == There are Many Paredit Commands ==
;; Search the VS Code command pallette for
;; Paredit to see all its commands. Pay
;; close attention to the shortcuts it displays
;; for the commands you use often.
;; See https://calva.io/paredit for much
;; more than we show here.

;; Alt+Enter this one

(->> ["I" "💖" "Paredit"]
     (interpose " ~ ")
     (apply str))

;; (To get into a good mood. 😍)

;; == Strict Mode Protection ==
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

;; == Select with Ease ==
;; Place the cursor in a form, for example on `select-me`
;; below, and then use *Paredit Expand Selection*
;; Repeat the command to expand one level more

(comment
  (-> 4
      (repeat (let [select-me 'bar]
                {:foo select-me}))
      (->>
       (repeat 3))
      (vec)))

;; There is also *Paredit Shrink Selection*

;; == Navigate the Structure ==
;; Move form-by-form using *Paredit Forward Sexp*
;; and *Paredit Backward Sexp*
;; Note: Despite what the command palette is showing, the
;; Keyboard shortcuts for Mac are alt+right/left and
;; for Windows and Linux they are ctrl+right/left
;; See https://github.com/BetterThanTomorrow/calva/issues/1161

(def move
  [{:zero 0}
   1 2 3
   "four"
   #:five {:bar 'baz}])

;; Also try *Paredit Select Forward/Backward*
;; All *Paredit Select ...* commands work together with
;; *Paredit Expand/Shrink Selection*

;; == Edit the Structure ==
;; A structural delete a day keeps the doctor away
;; Search the Command Palette for *Paredit Kill*

(defn delete
  "Strings are treated a bit like lists, try
   *Kill/Delete Sexp Backwards* and others in this
   documentation string"
  [kill-forward kill-backward]
  [{:zero 0}
   1 2 3
   "four"
   #:five {:foo kill-forward
           :bar kill-backward}]
  "To delete and copy, use *Paredit Select ...*
   then *Cut*")

;; === Move those Brackets ===
;; *Paredit Slurp* and *Paredit Barf* are handy
;; commands to move forms in and out of the current
;; list/vector/map/string (or move brackets, depending
;; on your perspective.)

(def slurp-barf [{:barf-me "barf-me-too"}
                 'slurp-me-then-barf-me])

;; === Raise ===
;; *Paredit Raise Sexp* replaces the enclosing
;; form with the ”current” form
;; Raise and Undo a bit in this snippet:

(comment
  (def raise-me
    #:or-raise-me {:or-me [1 2 3 4]
                   :or-this-> #{1 2 3 4}}))

;; Learn much more Paredit: https://calva.io/paredit

;; == Insert Coin to Continue ==
;; If you are new to Clojure, please continue
;; with `hello_clojure.clj` and evaluate your way
;; to some basic Clojure knowledge.

"Hello Calva Paredit λ 💖 🚀"

;; This guide downloaded from:
;; https://github.com/BetterThanTomorrow/dram
;; Please consider contributing.
