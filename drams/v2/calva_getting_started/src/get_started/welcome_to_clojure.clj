(ns get-started.welcome-to-clojure
  (:require [clojure.repl :refer [source apropos dir pst doc find-doc]]
            [clojure.string :as string]
            [clojure.test :refer [is are]]))

;; Welcome to Clojure! ♥️

;; Start with loading this file.
;; Ctrl+Alt+C Enter
;; (The Alt key is sometimes named Option or Opt)

;; Then evaluate this expression with Alt+Enter:

"Hello World"

;; That's a concise Hello World for any language.
;; And note that there are no parens. 😀

;; This guide will try to give you a basic
;; understanding of the Clojure language. Basic in
;; the sense that it is not extensive. Basic in the
;; sense that it is foundational, building from first
;; principles in order to make the Clojure journey
;; you have ahead easier to comprehend.

;; With the foundations in place you'll have a good
;; chance of having the right gut feeling for how to
;; code something, how to formulate your questions,
;; how to search effectively for information, how to make
;; sense of code you stumble across, and so on.

;; There will be links here and there, ctrl/cmd-click
;; those to open them in a browser. Here's the first
;; such link;
;; https://clojure.org/guides/learn/syntax
;; There you can read more about the concepts
;; mentioned in this guide.

;; The way to use the guide is to read about the
;; concepts and evaluate the examples. Sometimes there
;; will be exercises in the text. Don't limit your
;; exercising to those, though. Please feel encouraged
;; to edit the examples, and add new code
;; and evaluate that. Evaluate this to warm up:

(comment
  (str "Welcome"
       " to "
       "Clojure!"
       " "
       "♥️"))

;; Then see what happens if you throw in some numbers
;; here and there and evaluate again.

;; NB: This is work in progress...
;; When you create the Getting Started REPL project the
;; next time, it may have been updated. 😀
;; You can also always find the latest version here:
;; https://github.com/BetterThanTomorrow/dram/blob/dev/drams/calva_getting_started/src/get_started/welcome_to_clojure.clj

(comment
  ;; = EXPRESSIONS =
  ;; In Clojure everything is an expression.
  ;; (There are no statements.) Unless there is
  ;; an error when evaluating an expression, there
  ;; is always a return value (which is sometimes `nil`).

  ;; An important aspect of this is that the result
  ;; of an expression is always the last form/expression
  ;; evaluated. E.g. if you have a function defined
  ;; like so:

  (defn last-eval-wins []
    (println 'side-effect-1)
    1
    (println 'side-effect-2)
    2)

  ;; This defines a function named
  ;; `last-eval-wins`, taking no arguments, with four
  ;; expressions in its function body. (We'll return to
  ;; defining functions later.)

  ;; Calling the function

  (last-eval-wins) ; <- Evaluate that 😄

  ;; will cause all four expressions in the function
  ;; body to be evaluated. The result of the call will
  ;; be the last expression that was evaluated.

  ;; In the output window you will also see the
  ;; `println` calls happening. They are also
  ;; expressions, evaluating to `nil`.

  (println 'prints-this-evaluates-to-nil)

  ;; Expressions are composed from literals (evaluating
  ;; to themselves) and/or calls to either:
  ;; * special forms
  ;; * macros
  ;; * functions

  ;; ”Hello World” at the beginning of this guide is a
  ;; literal string (thus, it evaluates to itself).
  ;; More about literals in the next section.

  ;; Calls are written as lists with the called thing
  ;; as the first element.

  (def foo "foo") ; Calls the special form `def`,
                  ; evaluates to the var it creates
                  ; (More on this later)

  (for [x '(1 2 3)  ; Calls the macro `for`
        y '(:a :b)] ; (List comprehension)
    [x y])

  (str 1 2 3) ; Calls the function `str` with the
              ; arguments 1, 2, and 3.

  ;; Only functions can be passed as values

  (map str [:foo :bar])    ; works
  (map for [:foo :bar])    ; can't take value of a macro
  (map def [:foo :bar])    ; def is not even a symbol

  )

(comment
  ;; = LITERALS =
  ;; Literals evaluate to themselves.
  ;; (Remember your friends:
  ;;   Alt+Enter and Ctrl+Enter)

  ;; Numeric types
  18        ; integer
  -1.8      ; floating point
  0.18e2    ; exponent
  18.0M     ; big decimal
  18/324    ; ratio
  18N       ; big integer
  0x12      ; hex
  022       ; octal
  2r10010   ; base 2

  ;; Character types
  "hello"         ; string
  \e              ; character
  #"[0-9]+"       ; regular expression

  ;; Symbols and idents
  map             ; symbol
  +               ; symbol - most punctuation allowed
  clojure.core/+  ; namespaced symbol
  nil             ; null/nil value (named in the LISP tradition)
  true false      ; booleans
  :alpha          ; keyword
  :release/alpha  ; namespaced keyword
  ::alpha         ; namespaced keyword,
                  ; in current namespace

  ;; == KEYWORDS ==
  ;; Keywords start with a `:`. They are a thing
  ;; in themselves, often used as identifiers and as
  ;; keys in maps (more on maps later). Keywords are
  ;; very memory and speed efficient.

  ;; The same keyword is of course equal to itself

  (= :foo :foo)

  ;; It is, however, also identical to itself

  (identical? :foo :foo)

  ;; This means it is the same thing, occupying the
  ;; same (very tiny) place in memory.
  ;; Even if you construct a non-literal keyword
  ;; it remains identical to its literal form

  (identical? (keyword "foo") :foo)

  ;; This holds true for your whole Clojure program.
  ;; Keywords are global. There is namespace syntax
  ;; for them, so that you can have control of this.

  ;; Keywords are also functions, actually. But more
  ;; on that later. For now let it suffice to say
  ;; that keywords have a very special and important
  ;; role in most Clojure programs.

  ;; == STRINGS ==
  ;; Somewhere in between the atomic literals and
  ;; the collections we have strings. They are sometimes
  ;; treated as sequences (a cool abstraction I'll
  ;; talk more about).
  ;; Strings are enclosed by double quotes.

  "A string can be
   multi-line, but will contain any leading spaces."
  "Write strings
like this, if leading spaces are no-no."

  ;; (The single quote is used for something else.
  ;; You'll see for what a bit later.)
  )

;; = TIPS: Tools for practicing Clojure =
;;
;; Here are two resources that work very well together
;; with this guide:
;;
;; Rich4Clojure: Practice Clojure wit 4Clojure problems in VS Code
;; * https://github.com/PEZ/Rich4Clojure
;; Learning Clojure using Koans using VS Code and Calva
;; * https://github.com/DanBunea/clojure-koans
;;
;; Both let you learn and practice Clojure by solving
;; problems, connected to the REPL, in the editor, like
;; this guide. A way to use them is to open them in
;; separate VS Code windows and go back and forth between
;; them and this guide, as you learn thing, and as you
;; see the need to learn more.

(comment
  ;; = NAMESPACES =
  ;; As important as namespaces are, we won't dwell on
  ;; the subject very much in this guide. The official
  ;; docs make them the best justice:
  ;; https://clojure.org/reference/namespaces
  ;;
  ;; There are some things we really need to know
  ;; though...
  ;; Clojure symbols are defined in namespaces (With
  ;; the `def `special form) where they are reachable
  ;; from any other namespace.

  (def foo-2 "foo")

  ;; Also know that there is such a thing as the
  ;; current namespace. (A bit like the current working
  ;; directory in the shell.) When you evaluated the
  ;; `def` form above, you saw where `foo-2` got
  ;; defined.

  ;; When evaluating a symbol from any namespace it
  ;; must have been defined, or the compiler will
  ;; complain, and throw

  foo-3

  ;; The namespace also needs to have been created

  some-namespace/foo

  ;; If you have loaded the `hello_repl.clj` file
  ;; the `hello-repl` namespace is created and its
  ;; top level symbols are defined.

  get-started.hello-repl/greet

  (get-started.hello-repl/greet "from the welcome-to-clojure namespace")

  ;; If those throw, you need to first load
  ;; `hello_repl.clj`, or at least evaluate its `ns`
  ;; form and the `greet` form.

  ;; It is not to recommend that you rely on some
  ;; namespace existing like this though. That makes
  ;; your code brittle. It is better to `require`
  ;; the namespace. If you haven't loaded it, you
  ;; can do that in the same go:

  (require 'get-started.hello-paredit :reload)

  get-started.hello-paredit/strict-greet
  (get-started.hello-paredit/strict-greet "World")

  ;; For most Clojure code you write you will arrange
  ;; it into separate files with one namespace each,
  ;; and use the `ns` form (that starts most Clojure
  ;; files) to `:require` the needed namespaces, aliasing
  ;; them to something convenient and sometimes `:refer`
  ;; in some of their symbols so that you can use
  ;; them without the namespace prefix (which is the
  ;; text before the `/`, btw, in case that wasn't
  ;; obvious enough). Examine the `ns` form of this
  ;; file to see why these forms compile without
  ;; complaints:

  (doc require) ; Check the output window
  (string/split "foo:bar:baz" #":")

  ;; See also:
  ;; https://clojuredocs.org/clojure.core/ns

  ;; Any namespace can be created at the REPL. However,
  ;; when a namespace is required, either via the
  ;; `require` or `use` functions, or via the `ns` form
  ;; The Clojure Reader will look up the file addressed
  ;; by the namespace required in the classpath. When
  ;; doing so, dots in in the namespace name separate
  ;; directories, and dashes will be replaced by underscores.
  ;; Say you have a `src` in your classpath, and a file
  ;; `src/foo/bar_baz.clj` in the project. This file should
  ;; have an `ns` form looking like:

  ;(ns foo.bar-baz ,,,)

  ;; And you require it using something like:

  ;(require 'foo.bar-baz)

  ;; Or:

  ;(ns welcome-to-clojure
  ;  (:require [foo.bar-baz]))

  ;; If you evaluate any of those requires, you will get an
  ;; error message from the repl, telling you which files the
  ;; Clojure Reader looked for to find the namespace definition.

  ;; Two common mistakes:
  ;; 1. Naming files using dashes instead of underscores.
  ;; 2. Using `(require ...)` instead of `(:require)` in the
  ;;    `ns` form.

  ;; The `ns` form has a lot of functionality and can be a bit
  ;; tricky to figure out. Here's a nice cheat sheet:
  ;; https://gist.github.com/ghoseb/287710/


  ;; === Namespaced keywords ===
  ;; Keywords can also be namespaced, but they are
  ;; not really registered in a namespace, like
  ;; symbols are, so you can just use them, regardless

  :foo-whatever
  :whatever-namespace/foo

  ;; The notion about the current namespace exists
  ;; for keywords in that the double-colon prefix
  ;; expands to `:<current-namespace>/foo`:

  ::foo

  ;; This is important to know about. `:foo` will
  ;; refer to the same keyword regardless of from which
  ;; namespace it is used. `::foo` will not.
  )

(comment
  ;; = COLLECTIONS =
  ;; Clojure has literal syntax for four collection types
  ;; They evaluate to themselves.

  '(1 2 3)     ; list (a quoted list, more about this below)
  [1 2 3]      ; vector
  #{1 2 3}     ; set
  {:a 1 :b 2}  ; map

  ;; They compose

  {:foo [1 2]
   :bar #{1 2}}

  ;; In Clojure we do most things with just these
  ;; collections. Literal collections and functions.
  )

(comment
  ;; = FUNCTIONS =
  ;; So far you have been able to evaluate all examples.
  ;; It's because we quoted that list.
  ;; Actually lists look like so

  (1 2 3)

  ;; But if you evaluate that, you'll get an error:
  ;; => class java.lang.Long cannot be cast to class
  ;;    clojure.lang.IFn
  ;; (Of course, the linter already warned you.)
  ;; When evaluating unquoted lists the first element
  ;; in the list is regarded as being in ”function
  ;; position”. That means Clojure will try to call `1`
  ;; as a function, which won't work because it is not
  ;; a function.
  ;; You might be starting to suspect that a Clojure
  ;; program is just data? Which is correct. Clojure
  ;; code is data. Fancier, Clojure is homoiconic:
  ;; https://wiki.c2.com/?HomoiconicLanguages
  ;; This gives great macro power, more about that below.

  ;; Here are some lists with proper functions at
  ;; position 1:

  (str 1 2 3 4 5 :foo)
  (< 1 2 3 4 5)
  (*)
  (= "1"
     (str "1")
     (str \1))
  (println "From Clojure with ♥️")
  (reverse [5 4 3 2 1])

  ;; Everything after the first position is
  ;; handed to the function as arguments

  ;; Note: I'll be referring to literals, symbols, lists,
  ;; and other literal collections, collectively as forms,
  ;; sometimes, sexprs:
  ;; https://en.wikipedia.org/wiki/S-expression
  ;; Above, `(str 1 2 3 4 5 :foo)` is a form, as is `str`,
  ;; `1` and `:foo`.


  ;; You define new functions and bind them to names
  ;; in the current namespace using the macro `defn`.
  ;; It's a very flexible macro. Here's a simple use:

  (defn add2
    [arg]
    (+ arg 2))

  ;; It defines the function `add2` taking one argument.
  ;; The function body calls the core functions `+`
  ;; with the arguments `arg` and 2.
  ;; Evaluating the form will define it and you'll see:
  ;; => #'welcome-to-clojure/add2
  ;; That's a var ”holding” the value of the function
  ;; You can now reference the var using the symbol
  ;; `add2`. Putting it in the function position of a
  ;; list with 3 in the first argument position and
  ;; evaluating the list gives us back what?

  (add2 3)

  ;; Clojure has an extensive core library of functions
  ;; and macros. See: https://clojuredocs.org for a
  ;; community-driven Clojure core (and more) search engine.
  )

(comment
  ;; = SPECIAL FORMS and MACROS =
  ;; The core library is composed from the functions and macros
  ;; in the library itself. Bootstrapping the library is
  ;; a few (15-ish) built-in primitive forms,
  ;; aka ”special forms”.

  ;; You have met one of these special forms already:

  (quote (1 2 3))

  ;; The doc hover of the symbol `quote` tells you that
  ;; it is a special form.

  ;; Wondering where you met this special form before?
  ;; I used the shorthand syntax for it then:

  '(1 2 3)

  ;; Convince yourself they are the same with the `=` function:

  (= (quote (1 2 3))
     '(1 2 3))

  ;; Clojure has value semantics. Any data structures
  ;; that evaluate to the same data are equal,
  ;; no matter how deep or big the structures are.

  (= [1 [1 #{1 {:a 1 :b '(:foo bar)}}]]
     [1 [1 #{1 {:a (- 3 2) :b (quote (:foo bar))}}]])

  ;; ... but that was a detour, back to special forms.
  ;; Official docs:
  ;; https://clojure.org/reference/special_forms#_other_special_forms

  ;; A very important special form is `fn` (which is
  ;; actually four special forms, but anyway).
  ;; Without this form we can't define new functions.
  ;; The following form evaluates to a function which
  ;; adds 2 to its argument.

  (fn [arg] (+ arg 2))

  ;; Calling the function with the argument 3:

  ((fn [arg] (+ arg 2)) 3)

  ;; Another special form is `def`. It defines things,
  ;; giving them namespaced names.

  (def foo :foo)

  ;; ”Defining a thing” means that a var is created,
  ;; holding the value, and that a symbol is bound
  ;; to the var. Evaluating the symbol picks up the
  ;; value from the var it is bound to.

  foo

  ;; The var can be accessed using the `var` special
  ;; form.

  (var foo)

  ;; You will most often see the var-quote shorthand

  #'foo

  ;; With these two special forms we can define functions

  (def add2-2 (fn [arg] (+ arg 2)))
  (add2-2 3)

  ;; This is what the macro `defn` does. You will most
  ;; often be defining functions like what we saw
  ;; earlier, (when discussing the function position of
  ;; a form):

  (defn add2-3
    [arg]
    (+ arg 2))

  ;; We can use the function `macroexpand` to see what
  ;; the macro produces:

  (macroexpand '(defn add2-3
                  [arg]
                  (+ arg 2)))

  ;; Yet another super duper important special form:

  (if 'test
    'value-if-true
    'value-if-false)

  ;; `macroexpand does nothing here, since `if` is not
  ;; a macro:

  (macroexpand '(if test
                  value-if-true
                  value-if-false))

  ;; (Nor is it a function)

  ;; Fun fact: Besides `case`, all conditional and control
  ;; flow constructs in Clojure are build using `if`:

  (macroexpand '(when test
                  value-if-true))

  (macroexpand '(or a b))

  (require 'clojure.walk) ;; you'll need to evaluate this

  (clojure.walk/macroexpand-all '(or a b)) ;; to evaluate this

  (macroexpand '(cond
                  y value-if-y
                  z value-if-z
                  :else value-if-x-neither-y-nor-z))

  (clojure.walk/macroexpand-all '(cond
                                   y value-if-y
                                   z value-if-z
                                   :else value-if-x-neither-y-nor-z))

  ;; A programming language needs its conditionals. But
  ;; at the core Clojure almost makes due with only `if`.
  ;; Almost. As I mentioned earlier, `case` is an exception.
  ;; At least on the JVM, where Clojure uses jump tables to
  ;; implement it:

  (macroexpand '(case x
                  y value-if-x-is-y
                  z value-if-x-is-z
                  value-if-x-is-neither-y-nor-z))


  ;; We'll return to `if` and conditionals.

  ;; == `let` ==
  ;; `let` is a special form that lets you bind values to
  ;; variables that will be used in the body of the form.

  (let [x 1
        y 2]
    (str x y))

  ;; The bindings are provided as the first ”argument”,
  ;; in a vector. This is a pattern that is used by
  ;; other special forms and macros that let you define
  ;; bindings. It is similar to the lexical scope of other
  ;; programming languages (even if this rather is
  ;; structural). Sibling and parent forms do not
  ;; ”see” these bindings (they have no way they could
  ;; possibly reach it). Here's an example:

  (do
    (def x :namespace-x)
    (println "`x` in `do` _before_ `let`: " x)
    (let [x :let-x]
      (println "`x` from `let`: " x))
    (println "`x` in `do`, _after_ `let`: " x))

  (println "`x` _outside_ `do`: " x)

  ;; As noted before in this guide, the `def` special
  ;; form defines things ”globally”, though namespaced.

  ;; If you have followed the instructions to examine
  ;; things mentioned here, for instance by
  ;; ctrl/cmd-clicking the `let` symbol in the code
  ;; snippets, you'll find that in  `core.clj`, `let`
  ;; is defined as a macro. Never mind that. It is
  ;; actually referred to as a special form here
  ;; https://clojure.org/reference/special_forms#let

  ;; Let's (pun unintended) wrap the special forms section
  ;; up with noting that together with _how_ Clojure
  ;; reads and evaluates code, the special forms make up
  ;; the Clojure language itself. The next level och
  ;; building blocks are macros.

  ;; But let us investigate this thing with how code
  ;; is read first...
  )

(comment
  ;; = THE READER =
  ;; https://clojure.org/reference/reader
  ;; The Clojure Reader is responsible for reading text,
  ;; making data from it, which is what the compiler gets.
  ;; The Reader is where literals, symbols, strings, lists,
  ;; vectors, maps, and sets are picked apart and
  ;; re-assembled, figuring out what is a function,
  ;; a macro or special form.

  ;; In doing this whitespace plays a key role and there
  ;; are also some extra syntax rules are in play.

  ;; == WHITESPACE ==
  ;; Most things you would think counts as whitespace
  ;; is whitespace, and then there is also that Clojure,
  ;; being a LISP, does not need commas to separate
  ;; list items. However, commas can be used for this
  ;; anyway, since commas are whitespace.

  (= '(1 2 3)
     '(1,2,3)
     '(1, 2, 3)
     '(1,,,,2,,,,3))

  ;; (There are no operators in Clojure, `=` is a
  ;; function. It will check for equality of all
  ;; arguments it is passed.)

  ;; == LINE COMMENTS ==
  ;; The Reader skips reading everything on a line from
  ;; a semicolon. This is unstructured comments in
  ;; that if you start a form

  (range 1 ; 10)
  ;; and then place a line comment so that the closing
  ;; bracket of that form gets commented out, the
  ;; structure breaks.
         )
  ;;     ^ Healing the structure.

  ;; If you remove the semicolon on the opening form
  ;; above, make sure to also remove this closing paren.

  ;; Since everything on the line is ignored, you can
  ;; add as many semicolons as you want.
  ;;;;;;;;;; (skipped by the Reader)
  ;; It's common to use two semicolons to start a full
  ;; line comment.

  ;; == EXTRA SYNTAX ===
  ;; We've already seen the single quote

  'something

  ;; Which is, as we have seen, transformed to

  (quote something)

  ;; `quote` is needed to stop the Reader from treating
  ;; things as something that should be evaluated.
  ;; See what happens if you evaluate `something`
  ;; without the quoting:

  something

  ;; as well as the difference between evaluating these:

  (1 2 3 4)
  '(1 2 3 4)

  ;; There are some more quoting, and even splicing
  ;; symbols, which I won't cover in this guide.

  ;; === Deref ===
  ;; Clojure also has reference types, we'll discuss
  ;; (briefly) the most common one, `atom`, later.

  (def an-atom (atom [1 2 3]))
  (type an-atom)

  ;; To access value from a reference:

  (deref an-atom)
  (type (deref an-atom))

  ;; Again, `deref` is used for dereferencing a lot
  ;; of different reference types, including futures,
  ;; https://clojure.org/reference/refs
  ;; https://clojure.org/about/concurrent_programming

  ;; Anyway, `deref` is so common that there is
  ;; shorthand syntax for it

  @an-atom
  (= (deref an-atom)
     @an-atom)

  ;; It's a common mistake to forget to deref

  (first an-atom)
  (first @an-atom)

  ;; === THE DISPATCHER (HASH SIGN) ===
  ;; That hash sign shows up now and then. It has a
  ;; special role. It is also known as Dispatch.
  ;; Depending on what character is following it,
  ;; different cool things happen. Some cool ones
  ;; follow here:

  ;; Regular expressions have literal syntax, they are
  ;; written like strings, but with a hash sign in front

  #"reg(?:ular )?exp(?:ression)?"

  ;; Regexps are handled by the host platform, so they
  ;; are Java regexps in this tutorial. If you
  ;; evaluated the above regexp, we can test it.

  (re-seq *1 "regexp regular expression")

  ;; `*1` is a special symbol for a variable holding
  ;; the value of the last evaluation result. It might
  ;; be easier to get a regexp right by using it
  ;; directly:

  (re-seq #"fooo*" "fo foo fooo")
  (re-find #"fooo*" "fo foo fooo")

  ;; If the hash sign is followed by a `(`, the Reader
  ;; will start expecting a function body.

  #(+ % 2)

  ;; This is special syntax for ”function literals”, a
  ;; way to specify a function. The example above is
  ;; equivalent to this anonymous function.

  (fn [arg] (+ arg 2))

  ;; Nesting function literals is forbidden activity

  ;(#(+ % (#(- % 2) 3)))

  ;; (thankfully)

  ;; In addition to sets, regexps and function literals
  ;; we have seen var-quotes earlier in this guide

  #'add2

  ;; There was also a brief discussion about `vars`.
  ;; You might want to revisit it and also read more
  ;; about it, because it is a very important concept.
  ;; https://clojure.org/reference/vars

  ;; There is a very useful hash-dispatcher which
  ;; is used to make the Reader ignore the next form

  #_(println "The reader will not send this function call
to the compiler") "This is not ignored"

  ;; To test this select the ignore marker together with
  ;; the function call and the string, then use Alt+Enter,
  ;; to make Calva send it all to the Reader, which will
  ;; read it, ignore the function call, and only evaluate
  ;; the string.

  ;; Since #_ ignores the next form it is a structural
  ;; comment mechanism, often used to temporarily disable
  ;; some code or some data

  (str "a" "b" #_(str 1 2 3 [4 5 6]) "c")

  ;; Ignore markers stack

  (str "a" #_#_"b" (str 1 2 3 [4 5 6]) "c")

  ;; Note that the Reader _will_ read the ignored form.
  ;; If there are syntactic errors in there, the
  ;; Reader will get sad, complain, and stop reading.
  ;; Select from the marker up to and including the string
  ;; here and press Ctrl+Enter

  ;#_(#(+ % (#(- % 2) 3))) "foo"

  ;; Two more common #-variants you will see, and use,
  ;; are namespaced map keyword shorthand syntax and
  ;; tagged literals, aka, data readers. Let's start
  ;; with the former:

  (= #:foo {:bar 'bar
            :baz 'baz}
     {:foo/bar 'bar
      :foo/baz 'baz})

  ;; Unrelated to the #: There is another shorthand for
  ;; specifying namespaced keywords. Double colon
  ;; keywords get namespaced with the current namespace

  ::foo
  (= ::foo :calva-getting-started.src.get-started.welcome-to-clojure/foo)

  ;; Tagged literals, then. It's a way to invoke functions
  ;; bound to the tags on the form following it.
  ;; https://clojure.org/reference/reader#tagged_literals
  ;; They are also referred to as data readers. You can
  ;; define your own. Here let it suffice with mentioning
  ;; the two build in ones.

  ;; #inst will convert the string it tags to an instant.
  ;; (I.e. a moment in time)

  #inst "2018-03-28T10:48:00.000"
  (type *1)

  ;; #uuid will make an UUID of the string it tags

  #uuid "0000000-0000-0000-0000-000000000016"
  (java.util.UUID/fromString "0000000-0000-0000-0000-000000000016")

  ;; You now know how to read (in the sense of you
  ;; being a Clojure Reader) most Clojure code.
  ;; That said, let's skip going into the syntax
  ;; sugar and special forms for making host platform
  ;; interop extra nice.
  ;; https://clojure.org/reference/java_interop
  ;; Just a sneak peek:

  (.before #inst "2018-03-28T10:48:00.000"
           #inst "2021-02-17T00:27:00.000")

  ;; This invokes the method `before` on the date
  ;; object for year 2018 giving it the date from
  ;; year 2021 as argument. You'll see some little
  ;; more Java interop in this guide and probably
  ;; notice how available the host platform is when
  ;; coding Clojure. The same goes for
  ;; ClojureScript and for Clojure CLR.

  ;; Repeating this important resource on the Reader:
  ;; https://clojure.org/reference/reader
  ;; And in addition to that, read about All Those
  ;; Weird Characters here:
  ;; https://clojure.org/guides/weird_characters
  )

(comment
  ;; = MACROS =
  ;; Clojure has powerful data transformation
  ;; capabilities. We'll touch on that a bit later.
  ;; Here I want to highlight that this power can
  ;; be wielded for extending the language itself.
  ;; Since Clojure code is structured and code is
  ;; data, Clojure can be used to produce Clojure
  ;; code from Clojure code. It is similar to the
  ;; preprocessor facilitates that some languages
  ;; offer, like C's `#pragma`, but it is much more
  ;; convenient and powerful. A lot of what you
  ;; will learn to love and recognize as Clojure
  ;; is actually created with Clojure, as macros.

  ;; This guide is mostly concerned with letting you
  ;; know that macros are a thing, to help you to
  ;; quickly realize when you are using a macro rather
  ;; than a function. I.e. I will not go into the
  ;; subject of how to create macros.

  ;; The distinction is important, because even if
  ;; macro calls look a lot like function calls,
  ;; macros are not first class. They can't be
  ;; passed as arguments, or returned as results.
  ;; More about ”first class” in the section about
  ;; functions, later.

  ;; == `when` ==
  ;; Let's just briefly examine the macro`when`.
  ;; This macro helps with writing more readable code.
  ;; How? Let's say you want to conditionally evaluate
  ;; something. Above you learnt that there is
  ;; a special form named `if` that can be used for
  ;; this. Like so:

  (if 'this-is-true
    'evaluate-this
    'else-evaluate-this)

  ;; Now say you don't have something to evaluate
  ;; in the else case. `if` allows you to write this

  (if 'this-is-true
    'evaluate-this)

  ;; Which is fine, but you will have to scan the
  ;; code a bit extra to see that there is no else
  ;; branch. Easy with this short example, but can
  ;; get pretty hairy in real code. To address this,
  ;; you could write:

  (if 'this-is-true
    'evaluate-this
    nil)

  ;; But that is a bit silly, what if there was a
  ;; way to tell the human reading the code that
  ;; there is no else branch? There is!

  (when 'this-is-true
    'evaluate-this)

  ;; Let's look at how `when` is defined, you can
  ;; ctrl/cmd-click `when` to navigate to where
  ;; it is defined in Clojure `core.clj`.
  ;; You can also use the function `macroexpand`

  (macroexpand '(when 'this-is-true
                  'evaluate-this))

  ;; You'll notice that `when` wraps the body in
  ;; a `(do ...)`, which is a special form that lets
  ;; you evaluate several expressions, returning the
  ;; results of the last one.
  ;; https://clojuredocs.org/clojure.core/do
  ;; `do` is handy when you want to have some side-
  ;; effect going, in addition to evaluating something.
  ;; In development this often happens when you
  ;; want to `println` something before the result
  ;; of the expression is evaluated and returned.

  (do (println "The quick brown fox jumps over the lazy dog")
      (+ 2 2))

  ;; The `when` macro let's you take advantage of that
  ;; there is only one branch, so you can do this

  (when 'this-is-true
    (println "The quick brown fox jumps over the lazy dog")
    (+ 2 2))

  ;; Without `when` you would write:

  (if 'this-is-true
    (do
      (println "The quick brown fox jumps over the lazy dog")
      (+ 2 2)))

  ;; Here `when` saves us both the extra scanning for
  ;; the else-branch and the use of `do`.

  ;; As far as macros go, `when` is about as simple as
  ;; they get. From two built-in special forms,
  ;; `if` and `do`, it composes a form that helps us
  ;; write easy to write and easy to read code.

  ;; == `for` ==
  ;; The `for` macro really demonstrates how Clojure
  ;; can be extended using Clojure. You might think
  ;; it provides looping like the for loop in many
  ;; other languages, but in Clojure there are no for
  ;; loops. Instead `for` is about list comprehensions
  ;; (if you have Python experience, yes, that kind of
  ;; list comprehensions). Here's how to produce the
  ;; cartesian product of two vectors, `x` and `y`:

  (for [x [1 2 3]
        y [1 2 3 4]]
    [x y])

  ;; If you recall the `let` form above, and how it
  ;; lets you bind variables to use in the body of the
  ;; form, this is similar, only that `x` and `y` will
  ;; get bound to each value in the sequences and the
  ;; body will get evaluated for all combinations of
  ;; `x` and `y`.

  ;; All values? Well, `for` also lets you filter the
  ;; results

  (for [x [1 2 3]
        y [1 2 3 4]
        :when (not= x y)]
    [x y])

  ;; You can bind variable names in the comprehension
  ;; to store intermediate calculations and generally
  ;; make code more readable

  (for [x [1 2 3]
        y [1 2 3 4]
        :let [d' (- x y)
              d (Math/abs d')]]
    d)

  ;; Is the same as:

  (for [x [1 2 3]
        y [1 2 3 4]]
    (Math/abs (- x y)))

  ;; Debatable what is more readable in this particular
  ;; case... ¯\_(ツ)_/¯

  ;; A note about the variable name `d'` above:
  ;; `d'` is just a symbol name like any other. The
  ;; single-quote has no special meaning unless it is
  ;; the first character

  ;; Filters and bindings can be used together.
  ;; Use both `:let` and `:when` to make this
  ;; comprehension return a list of all `[x y]` where
  ;; their sum is odd. The functions `+` and `odd?`
  ;; are your friends here.

  (for [x [1 2 3]
        y [1 2 3 4]]
    [x y])

  ;; (Yes, it can be solved without `:let` or `:when`.
  ;; Humour me. 😎)

  ;; See https://www.youtube.com/watch?v=5lvV9ICwaMo for
  ;; a great primer on Clojure list comprehensions
  ;; See https://clojuredocs.org/clojure.core/for for
  ;; example usages and tips.

  ;; Note that even though `let` and `for` look like
  ;; functions, they are not. The compiler would not
  ;; like it if you are passing undefined symbols to a
  ;; function. This is legal code:

  (let [abc 1]
    2)

  ;; This isn't.

  (str [abc 1]
       1)

  ;; (Notice that the clj-kondo linter is marking the
  ;; first with a warning, and the second as an error)
  ;; Macros extend the Clojure compiler.
  ;; https://clojure.org/reference/macros

  ;; == Threading macros ==
  ;; Macros can totally rearrange your code. The
  ;; built-in ”threading” macros do this. Sometimes
  ;; when the nesting of function(-ish) calls get
  ;; deep it can get a bit hard to read and to keep
  ;; track of all the parens

  (Math/abs
   (apply -
          (:d (zipmap
               [:a :b :c :d]
               (partition 2 [1 1 2 3 5 8 13 21])))))

  ;; You read Clojure from the innermost expression
  ;; and out, which gets easier with time, but an
  ;; experienced Clojure coder would still find it
  ;; easier to read this

  (->> [1 1 2 3 5 8 13 21]
       (partition 2)
       (zipmap [:a :b :c :d])
       :d
       (apply -)
       (Math/abs))

  ;; Let's read this together. The thread-last macro,
  ;; `->>` is used, it takes its first argument and
  ;; places it (threads it) as the last argument to
  ;; following function. The first such step in
  ;; isolation:

  (->> [1 1 2 3 5 8 13 21]
       (partition 2))

  ;; The first argument/element passed to `->>` is
  ;; `[1 1 2 3 5 8 13 21]`
  ;; This is inserted as the last element of the
  ;; function call `(partition 2)`, yielding:

  (partition 2 [1 1 2 3 5 8 13 21])

  ;; This partitions the list into lists of
  ;; 2 elements => `((1 1) (2 3) (5 8) (13 21))`
  ;; This new list is then inserted (threaded)
  ;; as the last argument to the next function,
  ;; yielding:

  (zipmap [:a :b :c :d] '((1 1) (2 3) (5 8) (13 21)))

  ;; Which ”zips” together a Clojure map using
  ;; the first list as keys and the second list
  ;; as values
  ;; => `{:a (1 1), :b (2 3), :c (5 8), :d (13 21)}`
  ;; This map is then threaded as the last argument
  ;; to the function `:d`

  (:d '{:a (1 1), :b (2 3), :c (5 8), :d (13 21)})

  ;; (In clojure keywords are functions that look
  ;;  themselves up in the map handed to them.)
  ;; => `(13 21)`
  ;; You know the drill by now, this is threaded

  (apply - '(13 21))

  ;; Which applies the `-` function over the list
  ;; => `-8`
  ;; Then this is threaded to `Math/abs`

  (Math/abs -8)
  ;; 🎉

  ;; (In many Clojure capable editors, including
  ;; Calva, there are commands for ”unwinding”
  ;; a thread, and for converting a nested
  ;; expressions into a thread. Search for ”thread”
  ;; among the commands.)
  ;; https://github.com/clojure-emacs/clj-refactor.el/wiki/cljr-unwind-all

  ;; There is also a thread-first macro
  ;; `->` https://clojuredocs.org/clojure.core/-%3E
  ;; Sometimes you neither want to thread first
  ;; or last. There is a macro for this too.
  ;; `as->` lets you bind a variable name to the
  ;; threaded thing and place it wherever you
  ;; fancy in each function call.

  (as-> 15 foo
    (range 1 foo 3)
    (interpose ":" foo))

  ;; https://clojuredocs.org/clojure.core/as-%3E

  ;; It's common to utilize the fact that most characters
  ;; are available when naming Clojure symbols. I often
  ;; use `$` for this threading macro:

  (as-> 15 $
    (range 1 $ 3)
    (interpose ":" $))

  ;; Others use other names 😄

  (as-> 15 <>
    (range 1 <> 3)
    (interpose ":" <>))

  ;; I think emojis should be avoided, the official
  ;; docs only mention alphanumerics plus:
  ;; `*`, `+`, `!`, `-`, `_`, `'`, `?`, `<`, `>`, and `=`
  ;; (so not even `$`) but here goes:

  (as-> 15 ❤️
    (range 1 ❤️ 3)
    (interpose ":" ❤️))

  ;; Other core threading macros are:
  ;; `cond->`, `cond->>`, `some->`, and `some->>`
  ;; https://clojuredocs.org/clojure.core/cond-%3E

  ;; Please feel encouraged to copy the examples
  ;; from ClojureDocs here and play with them.
  ;; Here's one:

  (cond-> 1        ; we start with 1
    true inc       ; the condition is true so (inc 1) => 2
    false (* 42)   ; the condition is false so the operation is skipped
    (= 2 2) (* 3)) ; (= 2 2) is true so (* 2 3) => 6

  ;; See ”Threading with Style” by Stuart Sierra
  ;; for idiomatic use of the threading facilities.
  ;; https://stuartsierra.com/2018/07/06/threading-with-style
  )

;; With special forms, the special syntax of the Reader,
;; and macros, the foundations of what is the Clojure
;; language you use are laid. You can of course extend
;; the language further with libraries including macros
;; or create your own. However the core language, with
;; its macros is very expressive. Taking data oriented
;; approaches is often enough. Even to prefer, rather
;; than creating more macros.

;; On to flow control!

(comment
  ;; = Flow Control, Conditionals, Branching =
  ;; Clojure is richer than most languages in what it
  ;; offers us to let our programs flow the way we want
  ;; them to. Almost all the core library features for
  ;; this are implemented using the primitive (special
  ;; form) `if`. This is still the staple for us as
  ;; Clojure coders. It takes three forms as its
  ;; arguments:
  ;; 1. A condition to evaluate
  ;; 2. What to evaluate if the condition evaluates
  ;;    to something true (truthy)
  ;; 3. The form to evaluate if the condition does not
  ;;    evaluate to something truthy (the ”else” branch)
  ;; Roll this die, some ten-twenty times, checking if
  ;; it is a six:

  (if (= 6 (inc (rand-int 6)))
    "One time out of six you get a six"
    "Five times out of six you get something else")

  ;; Since there are no statements in Clojure `if` is
  ;; the equivalent to the ternary `if` expression you
  ;; find in C and many other languages:
  ;;   test ? true-expression : false-expression
  ;; Pseudo code for our dice:
  ;;   int(rand() * 6) + 1 == 6 ?
  ;;     "One time out of six you get a six" :
  ;;     "Five times out of six you get something else";

  ;; == The Search for Truth ==
  ;; Again, in Clojure we use expressions evaluating to
  ;; values. When examined for branching all values
  ;; are either truthy or falsy. In fact, almost all
  ;; values are truthy

  (if true :truthy :falsy)
  (if :foo :truthy :falsy)
  (if '() :truthy :falsy)
  (if 0 :truthy :falsy)
  (if "" :truthy :falsy)

  ;; The only falsy values are `false` and `nil`

  (if false :truthy :falsy)
  (if nil :truthy :falsy)
  (when false :truthy)

  ;; About that last one: `when` evaluates to `nil`
  ;; when the condition is falsy. Since `nil` is
  ;; falsy the above `when` expression would be
  ;; making the ”else” branch of an `if` to be
  ;; evaluated

  (if (when false :truthy) :true :falsy)

  ;; (Super extra bad code, but anyway)
  ;; When only boolean truth or falsehood can cut
  ;; it for you, there is the `true?` function

  (true? true)
  (true? 0)
  (true? '())
  (true? nil)
  (true? false)

  ;; Thus

  (if (true? 0) :true :false)

  ;; == `when` ==
  ;; As mentioned before, `when` is a one-branch
  ;; `if`, only for the truthy branch, which is
  ;; wrapped in a `do` for you. Try this and then
  ;; try it replacing the `when` with an `if`:

  (when :truthy
    (println "That sounds true to me")
    :truthy-for-you)

  ;; If the `when` condition is not truthy,
  ;; `nil` will be returned.

  (when nil :true-enough?)

  ;; == `cond` ==
  ;; Since deeply nested if/else structures can be
  ;; hard to write, read, and maintain, Clojure core
  ;; offers several more constructs for flow control,
  ;; one very common such is the `cond` macro. It
  ;; takes pairs of condition/result forms, tests
  ;; each condition, if it is true, then the result
  ;; form is evaluated and ”returned”, short-circuiting
  ;; so that no more condition is tested.

  (let [dice-roll (inc (rand-int 6))]
    (cond
      (= 6 dice-roll)  "Six is as high as it gets"
      (odd? dice-roll) (str "An odd roll " dice-roll " is")
      :else            (str "Not six, nor odd, instead: " dice-roll)))

  ;; The `:else` is just the keyword `:else` which
  ;; evaluates to itself and is truthy. It is the
  ;; conventional way to give your cond forms a
  ;; default value. Without a default clause, the
  ;; form would evaluate to `nil` for anything not-six
  ;; not-odd. Try it by placing two ignore markers
  ;; (`#_ #_`) in front of the `:else` keyword.

  ;; Gotta love ClojureDocs
  ;; https://clojuredocs.org/clojure.core/cond
  ;; Paste examples from there here and play around:

  ;; See also links to `cond->` info above

  ;; == `case` ==
  ;; A bit similar to `switch/case` constructs in
  ;; other languages, Clojure core has the `case`
  ;; macro which takes a test expression, followed by
  ;; zero or more clauses (pairs) of test constant/expr,
  ;; followed by an optional expr. (However, the body
  ;; after the test expression may not be empty.)

  (let [test-str "foo bar"]
    (case test-str
      "foo bar" (str "That's very " :foo-bar)
      "baz"     :baz
      (count    test-str)))

  ;; The trailing expression, if any, is ”returned” as
  ;; the default value.

  (let [test-str "foo bar"]
    (case test-str
      #_#_"foo bar" (str "That's very " :foo-bar)
      "baz"     :baz
      (count    test-str)))

  ;; If no clause matches and there is no default,
  ;; a run time error happens

  (let [test-str "foo bar"]
    (case test-str
      #_#_"foo bar" (str "That's very " :foo-bar)
      "baz"     :baz
      #_(count    test-str)))

  ;; WATCH OUT! A test constant must be a compile
  ;; time literal, and the compiler won't  help you
  ;; find bugs like this:

  (let [test-int 2
        two 2]
    (case test-int
      1     :one
      two   (str "That's not a literal 2")
      (str test-int ": Probably not expected")))

  ;; https://clojuredocs.org/clojure.core/case
  ;; Paste some `case` examples here and experiment

  ;; The Functional Design in Clojure podcast has a
  ;; fantastic episode about branching
  ;; https://clojuredesign.club/episode/089-branching-out/

  ;; == Less branching is good, right? ==
  ;; The core library is rich with functions that
  ;; helps you avoid writing branching code. Instead
  ;; you provide the condition as a predicate.
  ;; An often used predicate function is `filter`

  (filter even? [0 1 2 3 4 5 6 7 8 9 10 11 12])

  ;; and its ”sibling” `remove`

  (remove odd? [0 1 2 3 4 5 6 7 8 9 10 11 12])

  ;; Filtering sequences of values is a common task
  ;; and your programming time can instead be used
  ;; to decide _how_ it should be filtered, by writing
  ;; the predicate. Sometimes you don't even need to
  ;; do that, Clojure core is rich with predicates

  (zero? 0)
  (even? 0)
  (neg? 0)
  (pos? 0)
  (nat-int? 0)
  (empty? "")
  (empty? [])
  (empty? (take 0 [1 2 3]))
  (integer? -2/1)
  (indexed? [1 2 3])
  (indexed? '(1 2 3))

  ;; What's a predicate? For the purpose of this guide
  ;; A predicate is a function testing things for
  ;; truthiness. It is convention that these functions
  ;; end with `?`. Many take only one argument.

  ;; A handy predicate is `some?` which tests for
  ;; "somethingness”, if it is not `nil` it is
  ;; something

  (some? nil)
  (some? false)
  (some? '())

  ;; You can use it to test for if something is `nil`
  ;; by wrapping it in a call to the `not` function

  (not (some? nil))
  (not (some? false))

  ;; You get the urge to define a function named `nil?`,
  ;; right? You don't have to

  (nil? nil)
  (nil? false)

  ;; Clojure core also contains predicates that take
  ;; a predicate plus a collection to apply it on.
  ;; Such as `every?`

  (every? nat-int? [0 1 2])
  (every? nat-int? [-1 0 1 2])

  ;; Check the docs for `nat-int? and come up
  ;; with some more lists to test, like

  (every? nat-int? [0 1 2N]) ; 2N is not fixed precision
  (doc nat-int?)

  ;; This pattern with functions that take functions
  ;; as argument is common in Clojure. It spans beyond
  ;; predicates. Functions that take functions as
  ;; arguments are referred to as ”higher order
  ;; functions”.
  ;; https://en.wikipedia.org/wiki/Higher-order_function
  )

(comment
  ;; = Functions =
  ;; Before diving into higher order functions, let's
  ;; look at functions. Functions are first class
  ;; Clojure citizens and the main building blocks for
  ;; solving your business problems.

  ;; We have seen a few ways you can create functions.
  ;; Here's an anonymous function that returns the
  ;; integer given to it, unless it is divisible by
  ;; 15, in which case it returns "fizz buzz".
  ;; (Not the full Fizz Buzz problem by any means.)

  (fn [n]
    (if (zero? (mod n 15))
      "fizz buzz"
      n))

  ;; Let's define it (bind it to a symbol we can use)

  (def fizz-buzz-1 (fn [n]
                     (if (zero? (mod n 15))
                       "fizz buzz"
                       n)))
  (fizz-buzz-1 2)
  (fizz-buzz-1 15)

  ;; There's a macro that lets us define and create
  ;; the function in one call

  (defn fizz-buzz-2 [n]
    (if (zero? (mod n 15))
      "fizz buzz"
      n))

  (fizz-buzz-2 4)

  ;; `defn` lets us provide documentation for the
  ;; function

  (defn fizz-buzz-3
    "Says 'fizz buzz' if `n` is divisible by 15,
     otherwise says `n`"
    [n]
    (if (zero? (mod n 15))
      "fizz buzz"
      n))

  (doc fizz-buzz-3) ; (or hover `fizz-buzz-3`)

  ;; It is easy to place the doc string wrong,
  ;; especially since it is common to write the `defn`
  ;; form like we did with `fizz-buzz-2` above.

  (defn fizz-buzz-4
    [n]
    "Says 'fizz buzz' if `n` is divisible by 15,
     otherwise says `n`"
    (if (zero? (mod n 15))
      "fizz buzz"
      n))

  ;; This specifies a fully valid function body, so
  ;; Clojure won't complain about it. But:

  (doc fizz-buzz-4)

  ;; clj-kondo's default configuration will help you
  ;; spot these errors. However, it can't help with
  ;; this:

  (defn only-the-last-eval-returns [x]
    [1 x]
    [2 x])

  (only-the-last-eval-returns "foo")

  ;; It is easy enough to spot like this and also to
  ;; wonder why you would ever write a function that
  ;; way. Yet you probably will make this mistake,
  ;; especially if you ever write some Hiccup, which
  ;; is a super nice way of writing HTML with Clojure
  ;; data structures. It's used by the popular Reagent
  ;; library
  ;; https://purelyfunctional.tv/guide/reagent/#hiccup
  ;; When you do the mistake and finish your hour-long
  ;; bug hunt, you will hear this guide whisper
  ;;   ”Called it!”

  ;; The argument binding vector of `fn` (and
  ;; therefore `defn`) binds each argument in order
  ;; to a name.

  (defn coords->str [x y]
    (str "x: " x ", y: " y))

  ;; == Variadic Functions ==
  ;; You can define functions that take an arbitrary
  ;; number of arguments by placing a `&` in front
  ;; of the last argument name. That binds the name
  ;; to a sequence that contains all the remaining
  ;; arguments.

  (defn lead+members [lead & members]
    {:lead lead
     :members members})

  (lead+members "Dave Mustain"
                "Marty Friedman"
                "Nick Menza"
                "David Ellefson")

  ;; == Multi-arity ==
  ;; Clojure supports function signatures based on
  ;; the number of arguments. The `defn` macro lets
  ;; you define each arity as a separate list. This
  ;; is often used to provide default values

  (defn hello
    ([] (hello "World"))
    ([s] (str "Hello " s "!")))

  (hello)
  (hello "Clojure Friend")

  ;; Or to create an ”identity” value for a function,
  ;; (A starting value that the rest of the operation
  ;; uses.) Say you want to add two x-y coordinates

  (defn add-coords-1 [coord-1 coord-2]
    {:x (+ (:x coord-1)
           (:x coord-2))
     :y (+ (:y coord-1)
           (:y coord-2))})

  (add-coords-1 {:x -2 :y 10}
                {:x 4 :y 6})

  ;; What if the requirements were that if the
  ;; function is called with one argument it should
  ;; add it to the origin? (See what I did there?
  ;; The identity value is where the function
  ;; should start, so start from the origin. 😎)
  ;; We can see that `add-coords-1` fails here

  (add-coords-1 {:x -2 :y 10})

  ;; we need to add a one-arity

  (defn add-coords-2
    ([coord]
     (add-coords-2 {:x 0
                    :y 0}
                   coord))
    ([coord-1 coord-2]
     {:x (+ (:x coord-1)
            (:x coord-2))
      :y (+ (:y coord-1)
            (:y coord-2))}))

  (add-coords-2 {:x -2 :y 10})

  ;; Now if called with no arguments it should
  ;; return the origin, because if you do not add
  ;; any coordinate you stay at the start.
  ;; Write a function `add-coords-3` that returns
  ;; the origin when called like this

  (add-coords-3)

  ;; It should still handle to be called like this

  (add-coords-3 {:x 3 :y 4})
  (add-coords-3 {:x 2 :y 4}
                {:x -4 :y -4})

  ;; It has to do with making the function compose
  ;; with other functions. E.g. the `apply` function
  ;; which is a higher order function that ”applies”
  ;; a function over a sequence. Right now we can
  ;; apply our `add-coords-2` function like this

  (apply add-coords-2 [{:x 1 :y 1} {:x 4 :y 4}])

  ;; And like this

  (apply add-coords-2 [{:x 1 :y 1}])

  ;; But not like this

  (apply add-coords-2 [])

  ;; But the `add-coords-3` function you created can

  (apply add-coords-3 [])

  ;; It will not handle an arbitrary long sequence
  ;; of coords, though. For that we would need one
  ;; more arity like so

  (defn add-coords-4
    ;; add zero-arity from your `add-coords-3` here
    ;; add one-arity from your `add-coords-3` here
    ([coord-1 coord-2]
     {:x (+ (:x coord-1)
            (:x coord-2))
      :y (+ (:y coord-1)
            (:y coord-2))})
    ([coord-1 coord-2 & more-coords]
     ;; Implement this arity when you have learnt
     ;; about the higher order function `reduce`
     ))

  (apply add-coords-4 [{:x 1 :y 1}
                       {:x 1 :y 1}
                       {:x 1 :y 1}
                       {:x -6 :y -6}])

  ;; Listen to Eric Normand explain in more detail
  ;; why the identity of a function is important:
  ;; https://lispcast.com/what-is-a-functions-identity/

  ;; == Closures ==
  ;; When you create functions on the fly, lambdas,
  ;; if you like, you use either the `fn` special
  ;; form directly, or by proxy with the `#()` syntax.
  ;; This creates a closure, like it does in JavaScript
  ;; and other languages. That is, these function can
  ;; access snapshots of variables with the values they
  ;; had when the function was created

  (defn named-coords-factory [name]
    (fn [x y] {:name name
               :coords {:x x
                        :y y}}))

  (def bob-coords-fn (named-coords-factory "Bob"))
  (def fred-coords-fn (named-coords-factory "Fred"))

  (bob-coords-fn 0 0)
  (fred-coords-fn 5 5)
  (bob-coords-fn 7 7)

  ;; Closures are handy to create low-arity functions
  ;; inside let binding boxes for the function body
  ;; to use:

  (defn whisper-or-yell-or-ask [command sentence]
    (let [whisper (fn []
                    (str (string/lower-case sentence) command))
          yell (fn []
                 (str (string/upper-case sentence) command))
          ask (fn []
                (str sentence "?"))
          default (fn []
                    (str sentence command " ¯\\_(ツ)_/¯"))]
      (case command
        "" (whisper)
        "!" (yell)
        "?" (ask)
        (default))))

  ;; All functions created in the let binding box
  ;; ”close in” the `command` and the `sentence` so
  ;; the `case` can be kept terse and readable.

  (whisper-or-yell-or-ask "" "How wOnDerFuLLY NIce To seE")
  (whisper-or-yell-or-ask "!" "Hello tHERE")
  (whisper-or-yell-or-ask "?" "How are you doing")
  (whisper-or-yell-or-ask ":" "Oh well")

  ;; == The Attributes Map ==
  ;; The `defn` macro lets you add attributes to the
  ;; function in the form of a map. This gets added
  ;; as meta-data (some little more on that later)
  ;; to the var holding the function. The map goes
  ;; after the function name, and after any docs,
  ;; and before the arguments vector (or any arities)

  (defn i-have-attributes
    {:doc "Docs can be added like this too"
     :foo "Any attributes you fancy"}
    []
    "Good for you")

  (doc i-have-attributes)
  (meta #'i-have-attributes)

  ;; One handy attribute you can add is a test
  ;; function. Test runners will pick this up

  (defn fizz-buzz-5
    "That limited fizz-buzz function again"
    {:test (fn []
             (is (= "fizz-buzz" (fizz-buzz-5 15)))
             (is (= 3 (fizz-buzz-5 3))))}
    [n]
    (if (pos? (rem 15 n))
      "fizz-buzz"
      n))

  (clojure.test/test-var #'fizz-buzz-5)
  ;; Oops! You'll need to fix the bugs. 😀

  ;; How about implementing the complete Fizz Buzz?
  ;; https://en.wikipedia.org/wiki/Fizz_buzz

  (defn fizz-buzz
    "My Fizz Buzz solution"
    {:test (fn []
             (are [arg expected]
                  (= expected (fizz-buzz arg))
               1  1
               3  "Fizz"
               4  4
               5  "Buzz"
               7  7
               15 "Fizz Buzz"
               20 "Buzz"))}
    [n])

  (clojure.test/test-var #'fizz-buzz)
  (map fizz-buzz (range 1 40))

  ;; The meta-data that has special meaning to
  ;; the compiler and various Clojure core
  ;; facilities is listed here:
  ;; https://clojure.org/reference/special_forms

  ;; Now, on to higher order functions!
  )

(comment
  ;; = Higher order functions =
  ;; A big contribution to what makes Clojure such a
  ;; powerful language is that functions are
  ;; ”first-class”
  ;; https://en.wikipedia.org/wiki/First-class_function
  ;; They can be values in collections (also keys
  ;; in maps) and can be passed as arguments to other
  ;; functions, and ”returned ”as results from
  ;; evaluations. You might be familiar with the
  ;; concept from languages like JavaScript.

  ;; Let's look at some higher order functions in
  ;; Clojure core. `some` calls the function on the
  ;; elements of its collection, one-by-one, and
  ;; returns the first truthy result, and will return
  ;; `nil` if the list is exhausted before some element
  ;; results in something truthy.

  (some even? [1 1 2 3 5 8 13 21])

  ;; Not to be confused with `some?`, which is not
  ;; a higher order function.

  (some some? [nil false])
  (some some? [nil nil])

  ;; A common idiom in Clojure is to look for things
  ;; in collection using a `set` as the predicate.
  ;; Yes, sets are functions. Used as functions they
  ;; will look up the argument given to them in
  ;; themselves.

  (#{"foo" "bar"} "bar")

  ;; Thus

  (some #{"foo"} ["foo" "bar" "baz"])
  (some #{"fubar"} ["foo" "bar" "baz"])

  ;; `apply` takes a function and a collection and
  ;; ”applies” the function on the collection. Say you
  ;; have a collection of numbers and want to add them.
  ;; This won't work:

  (+ [1 1 2 3 5 8 13 21])

  ;; `apply` to the rescue

  (apply + [1 1 2 3 5 8 13 21])

  ;; Concatenate the numbers as a string:

  (apply str [1 1 2 3 5 8 13 21])

  ;; Contrast with

  (str [1 1 2 3 5 8 13 21])

  ;; We've also seen `filter` and `remove` above, two
  ;; very commonly used higher order functions. They
  ;; play in the same league as `map`, and `reduce`.
  ;; Read on. 😎
  )

(comment
  ;; = `map` and `reduce` =
  ;; Among the higher order functions you might have
  ;; used in other languages with first class
  ;; functions are `map` and `reduce`. They are worth
  ;; studying and practicing in much detail, here's
  ;; a super nice teaser:
  ;; https://purelyfunctional.tv/courses/3-functional-tools/

  ;; Let's also check them out briefly here.
  ;; `map` calls a function on the elements of one or
  ;; more collection from start to end and returns a
  ;; (lazy, more on that later) sequence of the results
  ;; in the same order. Let's say we want to decrement
  ;; each element in a list of numbers by one

  (map dec '(1 1 2 3 5 8 13 21))

  ;; Let's say we then want to dec them again

  (->> '(1 1 2 3 5 8 13 21)
       (map dec)
       (map dec))

  ;; Hmmm, better to subtract by two, maybe?

  (map (fn [n] (- n 2)) '(1 1 2 3 5 8 13 21))

  ;; If you give `map` more collections to work on
  ;; it will repeatedly:
  ;; 1. pick the next item from each collection
  ;; 2. give them to the mapping function as arguments
  ;; 3. add the result to its return sequence
  ;; Until the shortest collection is exhausted

  (map + [1 2 3] '(0 2 4 6 8))
  (map (fn [n1 s n2] (str n1 ": " s "-" n2))
       (range)
       ["foo" "bar" "baz"]
       (range 2 -1 -1))

  ;; (We haven't talked much about `range`, it is a
  ;; function producing sequences of numbers. Given no
  ;; arguments it produces an infinite, watch out 😀,
  ;; sequence of integers
  ;; 0, 0+1, 0+2, 0+3, 0+4, 0+5, 0.6 ...
  ;; Good thing the other sequences got exhausted!)

  ;; A lot of the tasks you might solve with `for`
  ;; loops in other languages, are solved with `map`
  ;; in Clojure.

  ;; With other such ”for loopy” tasks you will
  ;; be wielding `reduce`. Unlike `map` it is not
  ;; limited to producing results of the same length
  ;; or shape as the input collection. Instead it
  ;; accumulates a result of any shape. For instance,
  ;; it can create a string from a collection of
  ;; numbers

  (reduce (fn [acc n]
            (str acc ":" n))
          [1 1 2 3 5 8 13 21])

  ;; `reduce` will call the function with two
  ;;  arguments: the result of the last function
  ;;  call and the next number from the list. The
  ;;  start of the process is special, since then
  ;;  there are no results yet. `reduce` has two
  ;;  ways to deal with this, two arities to be
  ;;  specific. Called with two arguments, it
  ;;  uses the two first elements from the list
  ;;  for the first function call.
  ;;  Here's reducing the `+` function using the
  ;;  two-arity version of `reduce`

  (reduce + [1 1 2 3 5 8 13 21])

  ;;  The process then starts with calling `+`
  ;;  like so

  (+ 1 1)

  ;; Giving `reduce` three arguments makes it use
  ;; the second argument as the starting ”result”.

  (reduce + 100 [1 1 2 3 5 8 13 21])

  ;; You might have noticed that the `+` function
  ;; takes more (and less) than 2 arguments.

  (+)
  (+ 1)
  (+ 1 1)
  (+ 1 1 2 3 5 8 13 21)

  ;; `+` will take the first argument, if any, and
  ;; add it to ”the current” value (which is zero),
  ;; then the next argument and add that to the new
  ;; current value, and so on, and so forth, until
  ;; there is a result. This process sounds a bit
  ;; like I just described a reduce, right?
  ;; In fact it is.

  ;; If we were to implement the `+` function, how
  ;; could we do it? We could start by implementing
  ;; something that adds two numbers together, then
  ;; use it as as the reducing function with
  ;; `reduce`.
  ;; Of course, now we have the task of adding two
  ;; numbers together, without using the existing
  ;; `+` function... 🤔 SICP has this one, using
  ;; Peano Arithmetics
  ;; https://youtu.be/V_7mmwpgJHU?t=814
  ;; https://en.wikipedia.org/wiki/Peano_axioms#Addition

  (defn plus [x y]
    (if (zero? x)
      y
      (plus (dec x) (inc y))))

  ;; But there are too many cases not handled here...
  ;; Hmmm... Let's keep it simple and only do
  ;; integer math. Then we can use Java's
  ;; `Integer.sum(x, y)` method.

  (Integer/sum 1 1)

  ;; Awesome, with this we can create an `add-two`
  ;; function

  (defn add-two [x y]
    (Integer/sum x y))
  (add-two 1 1)

  ;; Unlike `+`, this one is not fully composable
  ;; with a higher order function like apply

  (apply add-two [])
  (apply add-two [1])
  (apply add-two [1 1])
  (apply add-two [1 1 2 3 5 8 13 21])

  ;; We need `add-many`. With `reduce` and our
  ;; `add-two` we can define `add-many` like so

  (defn add-many [& numbers]
    (reduce add-two numbers))
  ;; That does it, right?

  (apply add-many [1])
  (apply add-many [1 1])
  (apply add-many [1 1 2 3 5 8 13 21])

  ;; What about the zero-arity version of `+`, you
  ;; ask? Correct, that will blow up

  (add-many)

  ;; The built-in `+` function has a default ”current”
  ;; value of zero, remember? We can add that to
  ;; `add-many` in two ways: Either add a zero-arity
  ;; signature, or use the three-arity `reduce`. Let's
  ;; go for the latter option, since we are learning
  ;; about reduce here:

  (defn add* [& numbers]
    (reduce add-two 0 numbers))
  (add*)
  (add* 1)
  (add* 1 1)
  (add* 1 1 2 3)

  ;; BOOM.

  ;; We can use it with `apply` as well:
  (apply add* [])
  (apply add* [1])
  (apply add* [1 1])
  (apply add* [1 1 2 3 5 8 13 21])

  ;; Or `reduce`:
  (reduce add* [])
  (reduce add* [1])
  (reduce add* [1 1])
  (reduce add* [1 1 2 3 5 8 13 21])

  ;; Apart from that we only handle integers, our `add*`
  ;; is very much like how `+` is implemented in
  ;; Clojure core. Check it out (in the output window):

  (source +)

  ;; Hmmm, well, they seem to be using multi-arity
  ;; function signatures for the low-arity cases, probably
  ;; because of the casting, but anyway, 😀

  ;; There's one more thing with `reduce` we want to
  ;; mention. When writing reducing functions you can
  ;; stop the process before the input sequence is
  ;; exhausted, using the `reduced` function. Say we
  ;; want the input sequence as a string separated by
  ;; `:`, as above, but stop when we see a `nil` item.
  ;; Here's the last version for comparison:

  (reduce (fn [acc n]
            (str acc ":" n))
          [1 1 2 3 5 8 nil 13 21])

  ;; We can short circuit the process by calling
  ;; `reduced` with the accumulated value when we
  ;; encounter a `nil` item

  (reduce (fn [acc n]
            (if (nil? n)
              (reduced acc)
              (str acc ":" n)))
          [1 1 2 3 5 8 nil 13 21])

  ;; Here is what is going on

  (doc reduced)

  ;; Reducing is a mighty important concept in Clojure
  ;; since it is a ”functional first” language. Or as
  ;; it is worded in this Functional Design episode
  ;; https://clojuredesign.club/episode/058-reducing-it-down/
  ;; ”Reducing functions are a backbone of functional
  ;; programming, because we don’t have mutation.”

  ;; In fact in Clojure reducing is so important that
  ;; Rich Hickey has added a whole library with reducers
  ;; packing even more punch
  ;; https://clojure.org/reference/reducers
  ;; Again, the Functional Design duo, Nate Jones, and
  ;; Christoph Neumann have examined this library
  ;; a bit:
  ;; https://clojuredesign.club/episode/060-reduce-done-quick/
  ;; Amazing quote from that episode:
  ;;   “The seq abstraction, it’s rather lazy.”

  ;; We are not going down the rabbit hole of the
  ;; `reducers` library, though...
  )

;; ... Instead we are picking up that Nate and
;; Christoph mention three super important concepts
;; in those two above quotes.
;; * immutability
;; * the `seq` abstraction
;; * laziness
;; They are related, and maybe it is best to start with
;; immutability...

(comment
  ;; = Immutability =
  ;; It is rather crazy that we have been talking about
  ;; Clojure for this long without discussing how
  ;; it encourages us to avoid mutating our data as
  ;; it is being processed. Clojurians never shut up
  ;; about immutability, right? We can almost sound
  ;; like Rothbardians in defining ourselves as
  ;; Enemies of the State 😄
  ;; https://www.youtube.com/watch?v=qe60zwUAOqE

  ;; This is to some extent true, as Clojurians we
  ;; often try to stay in a data transformation mode
  ;; for the duration of an operation and only deal
  ;; with the impure world, at the ”boundaries”. at
  ;; the start we might be reading some input, and
  ;; at the end we might be updating a database,
  ;; printing the results to a file (or to the
  ;; screen), or mutating the DOM of a web page.

  ;; Clojure encourages us to walk the immutable
  ;; path in many ways, two of which I am going
  ;; to mention a bit here:
  ;; * Persistent Data Structures
  ;; * Pure Functions

  ;; == Persistent Data Structures ==
  ;; Clojure helps us to stay in immutable land by
  ;; providing us with immutable data structures.
  ;; The implementation of these is called Persistent
  ;; Data Structure:
  ;; https://en.wikipedia.org/wiki/Persistent_data_structure
  ;; In effect it means that the data structures are
  ;; never changed, The functions we use to transform
  ;; them actually create copies. (In a very smart
  ;; way, so don't you start worrying now.)

  ;; Say we define a a vector of some digits

  (def eighteen [1 0 0 1 0])
  eighteen

  ;; Now we want to change that last `0` to a `1`
  ;; We can use the `assoc` function. When used on
  ;; a vector, takes an index and the new value

  (def nineteen (assoc eighteen 4 1))
  nineteen

  ;; Examining `eighteen` again ...

  eighteen

  ;; ... we see that it is still true to its name.
  ;; Associng a `1` at index 4 of it created a
  ;; copy, which was then defined as `nineteen`

  ;; Perhaps obvious, this stands true in local
  ;; bindings as well.

  (let [origin {:x 0
                :y 0}
        x-travel (assoc origin :x 100)]
    [origin x-travel])

  ;; This provides for a very deterministic program
  ;; flow. Data does not willy-nilly change under our
  ;; feet. And, transformation processes that do not
  ;; mutate state are much easier to parallelize,
  ;; other threads can't change the data you are
  ;; transforming. A whole category of bugs never get
  ;; the chance to hatch!

  ;; Another benefit we get from immutability is
  ;; that Clojure can efficiently offer us value
  ;; equality. Values are immutable, by definition.
  ;; In Clojure, even the deepest data structures can
  ;; be compared in less than a jiffy.

  ;; Let's show this with a not so deep structure
  ;; (except in the name)

  (def universa {:one {"Alice" {:x 100
                                :y 100
                                :z 100}
                       "Bob" {:x 100
                              :y 100
                              :z 100}}
                 :two {"Alice" {:x 100
                                :y 100
                                :z 100}
                       "Bob" {:x 100
                              :y 100
                              :z 99}}})

  (= (:one universa)
     (:two universa))

  ;; `update-in` is a higher order function for
  ;;  transforming data structures given an
  ;;  ”address” and a function. We can use it
  ;;  to make two-Bob find two-Alice, just like
  ;;  one-Bob and one-Alice have found each other

  (def unified-universa
    (update-in universa [:two "Bob" :z] inc))
  unified-universa

  (= (:one unified-universa)
     (:two unified-universa))

  (= universa unified-universa)

  ;; You'll never have to write an `equals()`
  ;; method again! 😄

  ;; Immutability also makes our programs different
  ;; than they are when you can change the value of
  ;; a variable at will. It can take a while to get
  ;; used to this. (I am still at the point where I
  ;; have an easier time to see mutating solutions
  ;; to many problems. It is less and less so, but
  ;; anyway. Probably you will grok it quicker than
  ;; I am doing.)

  ;; It is totally worth it to insist on getting it.
  ;; The payoff is huge. If you are only going to
  ;; check out one of the resources I am recommending
  ;; in this guide, I suggest it be this one about
  ;; solving problems the Clojure way, by Rafal
  ;; Dittwald:
  ;; https://www.youtube.com/watch?v=vK1DazRK_a0
  ;; Spoiler: He is not using Clojure in the video

  ;; Of course, in the talk, Rafal is not only
  ;; pretending data is immutable. He is also
  ;; employing function purity.

  ;; == Pure functions
  ;; Clojure doesn't force purity on you, like
  ;; some languages do (looking at you Haskell), but
  ;; it makes it easy to fall into the habit of
  ;; writing pure functions and thus push side effects
  ;; towards the ”edges” of your program.

  ;; A function is considered pure if it abides to
  ;; these rules:
  ;; 1. Always return the same value for the same input
  ;; 2. Does not affect anything in its environment.
  ;;    So, not mutating anything, including not printing
  ;;    anything anywhere, or hitting mutating API
  ;;    endpoints.

  ;; A pure function is deterministic and you can
  ;; safely call it without worrying that it will update
  ;; application state or do anything else than
  ;; compute its return value based on the input you
  ;; hand it, and nothing but the input you hand it.
  )

;; Before examining the `seq` abstraction, let's divert
;; a bit into some common Clojure core functions for
;; transforming data structures.

(comment
  ;; = Transforming Data Structures =
  ;; Clojure has a core library that makes it easy,
  ;; fun, and readable to ”reach in” to a data
  ;; structure and manipulate it, creating a
  ;; copy with the result.

  ;; We have seen `assoc`, which creates a copy of
  ;; the data structure with a new value at the index
  ;; (in case of a `vector`) or key (in case of a map)

  (def colt-express
    {:name "Colt Express"
     :categories ["Family"
                  "Strategy"]
     :play-time 40
     :ratings {:pez 5
               :kat 5
               :wiv 5
               :vig 3
               :rex 5
               :lun 4}})

  (def exit-haunted
    {:name "EXIT: The Haunted Roller Coaster"
     :categories ["Family"
                  "Co-op"
                  "Puzzle"
                  "Cards"]
     :ratings {:pez 5
               :kat 5
               :wiv 5
               :vig 4
               :rex 5}})

  ;; `assoc` can add a new key to a map

  (def colt-express-w-age
    (assoc colt-express :age-from 10))

  ;; With a vector you can only add a new item right
  ;; behind the last item, not beyond

  (def board-games-empty
    [])

  (def board-games-w-c-e
    (assoc board-games-empty 0 colt-express))

  ;; board-games-empty is still empty. Thus

  (def board-games-w-c-e-and-exit-fail
    (assoc board-games-empty 1 exit-haunted))

  (def board-games-w-c-e-and-exit
    (assoc board-games-w-c-e 1 exit-haunted))

  ;; Not that it is very common to add things
  ;; to a vector using `assoc`. For this `conj`
  ;; often makes more sense

  (conj board-games-empty colt-express exit-haunted)

  ;; `assoc` on maps can replace existing values
  ;; (in the copy)

  (def colt-express-w-age-and-adjusted-playtime
    (assoc colt-express-w-age :play-time 45))

  ;; `assoc` on vectors can do this too

  (def board-games-w-adjusted-c-e
    (assoc board-games-w-c-e
           0
           colt-express-w-age-and-adjusted-playtime))

  ;; You can `assoc` multiple things in one call

  (assoc colt-express
         :play-time 50
         :age-from 10)

  (assoc board-games-empty
         0 colt-express
         1 exit-haunted)

  ;; (Again, there is `conj` for this.)

  ;; With maps there is also `merge`, letting you
  ;; merge two or more maps together

  (merge colt-express
         {:play-time 45
          :age-from 10})

  ;; NB: it is a ”shallow” merge, so adding a family
  ;; member rating like this won't work.

  (merge exit-haunted
         {:play-time 90
          :ratings {:lun 5}
          :age-from 10})

  ;; `assoc` does the same

  (assoc exit-haunted :ratings {:lun 5})

  ;; There is no deep-merge in Clojure core, but
  ;; there is `assoc-in` for reaching in deeper
  ;; Instead of an key (or index) it takes a ”path”

  (assoc-in exit-haunted [:ratings :lun] 5)

  (assoc-in colt-express [:categories 2] "Planning")
  ;; (But... don't, see below under `update` for
  ;; how to `conj` the category instead.)

  ;; Unlike with `assoc`, you can only add one thing
  ;; at a time with `assoc-in`

  ;; Removing things from a map is done with
  ;; `dissoc`

  (dissoc colt-express :play-time :ratings :categories)

  ;; You will probably use `dissoc` often with
  ;; the REPL (like you do in this file) to
  ;; examine some data structures that might
  ;; have some large data structures in them,
  ;; like a log or something

  (dissoc colt-express :log)
  ;; (This data structure didn't have any log,
  ;; so it was left unchanged, but anyway.)

  ;; There is no `dissoc-in` in Clojure core, but
  ;; let's return to that after we have visited
  ;; `update` and `update-in`.

  ;; `update` and `update-in` are similar to
  ;; their `assoc` counterparts, but instead of a
  ;; value, they take a function which is used to
  ;; manipulate the value.

  (update exit-haunted :name string/upper-case)

  ;; An exercise for you: Update the `:play-time`
  ;; of the `colt-express` entry with 5 or so




  ;; Arguments that you add after the function
  ;; get passed to the function

  (update colt-express :categories conj "Planning")

  ;; Exercise: Make your update of the :play-time
  ;; take the `5` (or so) as an argument.




  ;; Exercise: Remove the `:pez` and `:wiv` entries
  ;; from the `:ratings` of `exit-haunted`





  ;; `update-in` is to `assoc-in` what `update` is
  ;; to `assoc`.

  (update-in colt-express [:ratings :lun] inc)

  (update-in colt-express [:ratings :lun] + 9000)
  ;; https://www.youtube.com/watch?v=PCHxU7witPA

  ;; Exercise: There is no `dissoc-in`, but it does
  ;; look like you can use `update-in` for this,
  ;; in'it?





  ;; The reward is one less visit to StackOverflow
  ;; for you when lacking `dissoc-in` 😄
  ;; https://stackoverflow.com/a/21942548/44639

  ;; We have been using keywords as map lookup
  ;; functions earlier. That's fine, but you might
  ;; sometimes prefer the `get` function

  (get colt-express :ratings)

  (= (:ratings colt-express)
     (get colt-express :ratings))

  ;; `get` takes a third argument that will be used
  ;;  as the default, should the entry be missing

  (get exit-haunted :play-time 0)

  ;; keywords as lookup functions also supports
  ;; this

  (:play-time exit-haunted 0)

  ;; Without the default, `nil` will be returned.
  ;; Which might blow up, depending on what you
  ;; use the value for

  (* (get colt-express :play-time) 2)
  (* (get exit-haunted :play-time) 2)

  ;; Better safe than sorry, in cases like this

  (* (get colt-express :play-time 0) 2)
  (* (get exit-haunted :play-time 0) 2)

  ;; Yes, there is `get-in` as well
  ;; Exercise: Use `get-in` to grab my rating
  ;; on these two wonderful family games


  ;; You might have noticed that all the
  ;; functions in this section take the collection
  ;; as their first argument. That makes them
  ;; easy to use with the Thread First, `->`,
  ;; macro. This is by design and highly idiomatic
  ;; Clojure.

  ;; It is common to see data transformation pipe-
  ;; lines like this

  (-> exit-haunted
      (assoc :play-time 90)
      (update :categories conj "Scary")
      (assoc-in [:ratings :lun] 5)
      (update-in [:ratings :vig] + 1)
      (dissoc :name)
      (update :log vec)
      (update :log conj "Name redacted")
      (update :log conj "(Because scary)"))

  ;; (Although, perhaps more meaningful than that)

  ;; I can recommend ”See also”-browsing ClojureDocs
  ;; some starting here:
  ;; https://clojuredocs.org/clojure.core/update-in
  ;; And pasting a lot of examples here to
  ;; experiment with.
  )

(comment
  ;; == Manipulating `sets` ==
  ;; Maps, vectors and sets are the bread and
  ;; butter for most Clojure programs. With the
  ;; amazing literal syntax for these the code gets
  ;; gets easy to read and reason about. And
  ;; manipulating them is easy and intuitive.

  ;; `sets` are `seqs` (more on that later)
)


;; To be continued...

;; Until there's more material to read here, maybe
;; it's time you check how to connect Calva to
;; your Clojure/ClojureScript projects:
;; https://calva.io/connect/

;; Things on the to-write-about list:
;; meta-data
;; comments
;; destructuring
;; atoms
;; nil, nil safety, nil punning
;; seqs
;; laziness
;; loop, recur
;; debugging
;; some wrapping up exercises here and there

;; Learn much more Clojure at https://clojure.org/
;; There is also ClojureScript, the same wonderful language,
;; for JavaScript VMs: https://clojurescript.org

;; There is so much about Clojure not mentioned in this
;; short guide. https://clojure.org/ is where you
;; go for the complete story.

;; To get help with your Clojure questions, check these
;; resources out:
;; https://ask.clojure.org/
;; https://clojurians.net
;; https://clojureverse.org
;; https://www.reddit.com/r/Clojure/
;; https://exercism.io/tracks/clojure

;; And there are also many other resources, such as:
;; https://clojuredocs.org
;; https://clojure.org/api/cheatsheet

"File loaded. Welcome to Clojure! ♥️"

;; This guide is downloaded from:
;; https://github.com/BetterThanTomorrow/dram
;; Please consider contributing.
