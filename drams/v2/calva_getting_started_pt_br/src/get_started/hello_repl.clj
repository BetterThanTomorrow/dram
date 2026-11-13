(ns get-started.hello-repl)

"Bem-vindo ao Getting Started REPL! 💜"

;; == Algum conhecimento de VS Code é necessário ==
;; Este tutorial pressupõe que você conhece algumas coisas
;; sobre o VS Code. Dê uma olhada nesta página se você é novo
;; no editor: https://code.visualstudio.com/docs

;; == Notação de Atalhos de Teclado usada neste tutorial ==
;; Usamos uma notação para atalhos de teclado, onde
;; `+` significa que as teclas são pressionadas ao mesmo tempo
;; e ` ` (espaço) separa qualquer sequência de teclas.
;; `Ctrl+Alt/Option+C Enter` significa pressionar
;; Ctrl, Alt (ou Option) e C
;; todas ao mesmo tempo, soltar as teclas e
;; então pressionar Enter. (A tecla Alt se chama Option ou
;; Opt em algumas máquinas)
;; Quando uma tecla é diferente em plataformas diferentes,
;; escrevemos as teclas separadas por uma barra.

;; == Resultados da avaliação ==
;; Avaliar código mostra a primeira linha do resultado
;; inline. O resultado completo é mostrado em dois lugares:
;; 1. O “terminal” Calva Output, que você encontra
;;    na view Terminal do VS Code.
;; 2. O Calva Inspector, que é um painel lateral
;;    que permite inspecionar o resultado com mais detalhes.

;; Este projeto está configurado para iniciar automaticamente
;; o REPL e conectá-lo ao editor (Jack-in)
;; Se isso não acontecer, você pode estar usando uma versão
;; antiga do Calva. Atualize para a versão mais recente
;; (ou pelo menos para a v2.0.460).
;;
;; Quando o REPL estiver conectado (o botão REPL na
;; barra de status fica cor de brasa), você deve carregar
;; este arquivo no REPL. Use o comando:
;;   Calva: Load/Evaluate Current File and its Requires/Dependencies
;; Depois você pode avaliar o código ;; na linha acima
;; que diz "Bem-vindo ao Getting Started REPL! 💜"
;; colocando o cursor na linha e pressionando
;; `Alt/Option+Enter`.

;; Conseguiu? Ótimo!
;; Viu o `=> "Bem-vindo ...` no final da linha?
;; Esse é o resultado da avaliação que você acabou de
;; fazer. Você acabou de usar o REPL do Clojure!
;; 🎉 Parabéns! 🎂
;; Você pode dispensar o resultado inline pressionando `Esc`.

(comment
  ;; Você pode avaliar a string abaixo da mesma forma

  "Hello World!"

  ;; Você está em uma 'Rich Comment Form', que é onde
  ;; nós, Clojuristas, mais desenvolvemos código novo.
  ;; https://calva.io/rich-comments/
  ;; Costuma ser abreviada como RCF.

  ;; Avalie o form a seguir também (você pode
  ;; colocar o cursor em qualquer lugar de qualquer uma das duas linhas):

  (repeat 7
          "I am using the REPL! 💪")

  ;; Apenas `=> ("I am using the REPL! 💪"` é exibido
  ;; inline. Você pode ver o resultado completo em três lugares:
  ;; 1. O “terminal” Calva Output
  ;; 2. O Calva Inspector. Ele tem um botão _Inspect_
  ;;    para cada resultado que permite expandir o resultado
  ;;    e explorá-lo como uma estrutura em árvore.
  ;; 3. Na parte de baixo do hover do resultado inline

  ;; Vamos entrar no clima do REPL de verdade. 😂
  ;; Coloque o cursor em qualquer uma das cinco linhas de código abaixo:
  ;; `Alt+Enter`, depois `Ctrl/Cmd+K Ctrl/Cmd+I`.

  (map (fn [s]
         (if (< (count s) 5)
           (str "Give me " s "! ~•~ " (last s) "!")
           s))
       ["an R" "an E" "a  P" "an L" "What do you get?" "REPL!"])

  ;; Limpe a exibição inline com `Esc`. Os resultados
  ;; inline também somem quando você edita o arquivo.

  ;; O que nos leva a uma coisa MUITO IMPORTANTE:
  ;; Por padrão, o Calva será um Guardião dos Parênteses.
  ;; Isso significa que as teclas backspace e delete
  ;; não apagam parênteses balanceados. Vá em frente
  ;; e tente apagar um parêntese na expressão acima.
  ;; Viu?

  ;; PARA APAGAR UM PARÊNTESE BALANCEADO:
  ;;   pressione `alt/option+backspace` ou `alt/option+delete`

  :rcf) ; <- Esta é uma forma conveniente de impedir que o
        ;    parêntese de fechamento de um Rich comment form
        ;    seja dobrado quando o código é formatado.



;;
;; = COMO ESTE GUIA FUNCIONA =
;;

;; Há três arquivos para você explorar. Para
;; quem é novo no Calva:
;; 1. hello_repl.clj (este arquivo)
;; 2. welcome_to_clojure.clj (um Guia de Clojure para Iniciantes)
;; 3. hello_paredit.clj (introdução à edição estrutural do Calva)

;; Não se preocupe se você não entender o código
;; deste arquivo e do arquivo do paredit.
;; Eles são curtos e feitos para que você possa simplesmente
;; acompanhar sem que o código em si faça sentido ainda. As coisas
;; vão se esclarecer quando você mergulhar no Guia de Clojure.

;; Sobre comandos e atalhos:
;; Leia https://calva.io/finding-commands/
;; (É bem curto.)
;; Quando nos referirmos a comandos pelo nome, use
;; a Paleta de Comandos do VS Code para procurá-los
;; se você não souber o atalho de teclado.
;; Todos os comandos do Calva têm o prefixo ”Calva”.

;; == Avaliando definições ==
;; Alt+Enter é o atalho de teclado padrão do Calva
;; para avaliar os forms de ”nível superior” atuais. Nível
;; superior significa o ”contêiner” mais externo dos forms,
;; que é o arquivo. Esta definição de função está no
;; nível superior. Avalie-a!

(defn greet
  "Eu vou cumprimentar você"
  [s]
  (str "Hello " s "!"))

;; Forms dentro de `(comment ...)` também são considerados
;; de nível superior. Isso facilita experimentar
;; com código.

(comment
  (greet "World")
  :rcf)

;; Qualquer coisa impressa em stdout não é mostrada inline.

(comment
  (println (greet "World"))
  :rcf)

;; Você deve ver o resultado da avaliação, nil,
;; inline, e ”Hello World!” seguido do resultado
;; impresso no terminal de saída.

;; Será que você se pergunta o que é um ”form”? De forma
;; geral, é quase o mesmo que uma S-expression:
;; https://en.wikipedia.org/wiki/S-expression
;; Ou seja, uma ”palavra” ou algo envolvido por
;; algum tipo de delimitador: parênteses (), colchetes [],
;; chaves {} ou aspas "". Isto tudo é um
;; form:

(str 23 (apply + [2 3]) (:foo {:foo "foo"}))

;; Assim como `str`, `23`, "foo", `(apply + [2 3])`,
;; `{:foo "foo"}`, `+`, `[2 3]`, `apply` e também
;; `(:foo {:foo "foo"})`.

;; O Calva tem o conceito de ”form atual”, para permitir
;; avaliar forms que não estão no nível superior. A
;; ”form atual” é determinada pela posição do cursor.
;; O Calva tem dois comandos que permitem experimentar
;; facilmente qual form é considerada atual:
;; * Calva: Select Current Form
;; * Calva: Expand Selection


;; == Avaliando o Form Atual ==
;; Ctrl+Enter avalia o form ”atual”
;; Tente com o cursor em diferentes lugares deste
;; trecho de código:

(comment

  (str 23 (apply + [2 3]) (:foo {:foo "foo"}))

  ;; Você pode descobrir que o Calva considera palavras dentro
  ;; de strings como forms. Não entre em pânico se `foo` causar
  ;; um erro de avaliação. Ele não está definido, e não
  ;; deveria estar. Você pode defini-lo, claro,
  ;; só por diversão e aprendizado: avalie em nível superior estas
  ;; definições. (`Alt/Option+Enter`)

  (def foo
    [1 2 "three four"])
  (def three 3)
  (def four "four")

  :rcf)

;; Depois avalie o form atual dentro das strings acima.
;; O que quer que você peça ao Calva para enviar ao REPL,
;; o Calva enviará ao REPL.


;; == Suporte a Rich Comments ==
;; Repetindo um conceito importante: Forms dentro de
;; `(comment ...)` também são consideradas de nível superior
;; pelo Calva. Use Alt/Option+Enter em diferentes lugares abaixo
;; para pegar o jeito.

(comment
  "I ♥️ Clojure"

  (greet "World")

  foo

  (range 10)

  ;; https://calva.io/rich-comments/
  :rcf)


;; Experimente também os comandos *Show Hover*,
;; *Show Definition Preview Hover*
;; *Go to Definition*

(comment
  (println (greet "side effect"))
  (+ (* 2 2)
     2)

  ;; Aqui também, se você tiver os fontes do Java instalados
  (Math/abs -1)
  :rcf)


;; == Você Controla o que é Avaliado ==
;; Note que o Calva nunca avalia seu código
;; a menos que você peça explicitamente. Então, exceto por
;; este arquivo, você terá que carregar os arquivos que abrir
;; por conta própria. Faça disso um hábito, porque
;; às vezes as coisas não funcionam, e falham de
;; formas peculiares, quando seu arquivo não está carregado.

;; Tente com este arquivo: `Ctrl+Alt+C Enter`.
;; O resultado de carregar um arquivo é a última
;; form de nível superior do arquivo.

;; == Editando Código ==
;; Uma nota sobre editar Clojure no Calva:
;; Se você editar e experimentar com os exemplos, vai
;; notar que o Calva indenta seu código automaticamente.
;; Você pode reindentar e formatar o código quando quiser,
;; usando a tecla `Tab`. Ela formata o form
;; que envolve o cursor. Experimente nos lugares numerados deste
;; trecho de código, começando em `; 1`:

(comment ; 3
  (defn- divisible
    "`n` é divisível por `d`?"
    [n d]
    (zero? (mod n d)))

  (defn fizz-buzz [n] ; 2
    (cond ; 1
      (divisible n (* 5 3)) "FizzBuzz"
      (divisible n 5)       "Buzz"
      (divisible n 3)       "Fizz"
      :else                  n))
  :rcf)

;; === O `modo estrito` do Paredit está ativado ===
;; O Calva suporta edição estrutural (edição que
;; considera forms em vez de linhas) usando um sistema
;; chamado Paredit. Por padrão, o Paredit tenta proteger
;; contra apagar parênteses acidentalmente e desbalancear
;; a estrutura dos forms. Para ignorar a proteção,
;; use `Alt+Backspace` ou `Alt+delete`.

(comment
  (defn strict-greet
    "Tente remover os parênteses e as aspas da string
   usando Backspace ou Delete. Tente o mesmo
   com a tecla Alt pressionada."
    [name]
    (str "Strictly yours, " name "!"))

  (strict-greet "dear Paredit fan")
  :rcf)

;; (Restaure com *Undo* se precisar.)
;; Veja `hello_paredit.clj` para mais. E também:
;; https://calva.io/paredit

;;;;;;;;;;;;;;;;;;; CHECKPOINT ;;;;;;;;;;;;;;;;;;;

;; Agora você sabe o bastante sobre o Calva para experimentar
;; com o código em `welcome_to_clojure.clj`.
;; Este arquivo continua com mais alguns recursos legais do Calva,
;; então você definitivamente deve voltar aqui
;; depois de aprender algumas coisas sobre Clojure.
;; Por exemplo, o que é uma threaded expression.

;; == Avaliando em Threaded Expressions
;; Ctrl+Alt+Enter avalia o form envolvente atual
;; até o cursor. Útil em threaded
;; expressions. Digamos que você queira calcular a média
;; das avaliações dos dados abaixo:

(comment
  ;; Primeiro você precisa avaliar isto em nível superior
  (def colt-express
    {:name "Colt Express"
     :categories ["Family"
                  "Strategy"]
     :play-time 40
     :ratings {:pez 5.0
               :kat 5.0
               :wiw 5.0
               :vig 3.0
               :rex 5.0
               :lun 4.0}})

  ;; E isto
  (defn average [coll]
    (/ (apply + coll) (count coll)))

  ;; Isto também, se quiser
  (->> colt-express
       :ratings
       vals
       average)

  ;; Para ver o resultado em cada passo da thread
  ;; você também pode usar Ctrl+Alt+Enter após cada form.
  ;; Coloque o cursor após `(->> colt-express` e
  ;; tente. Depois após `:ratings`, e após `vals`.
  :rcf)

;; == Avaliando o Form de Nível Superior até o Cursor
;; Shift+Alt+Enter avalia todo o código desde
;; o início do form de nível superior atual até
;; o cursor, com todos os parênteses abertos fechados.
;; Experimente repetindo o exemplo acima, mas começando
;; com o cursor logo depois de
;; `:wiw 5.0`, e então avalie o nível superior até o cursor.

(comment
  ;; O comando é útil para avaliar um bloco de
  ;; código até certo ponto. Você pode, por exemplo, envolver
  ;; algum código em um `(do ...)` e usar o comando
  ;; *Evaluate From Start of Top Level Form to Cursor*
  ;; em diferentes lugares para examinar o código.
  ;; Tente na linha com comentário numerado abaixo.

  ; Mas primeiro avalie esta em nível superior
  (defn average [coll]
    (/ (apply + coll) (count coll)))

  (do
    (def bar-express
      {:name "Bar Express"
       :categories ["Family"
                    "Strategy"]
       :play-time 40
       :ratings {:pez 5.0
                 :kat 5.0
                 :wiw 5.0   ; 1, depois avalie `bar-express`
                 :vig 3.0
                 :rex 5.0
                 :lun 4.0}})

    (let [foo-express (-> bar-express
                          (assoc :name "Foo Express")
                          (assoc-in [:ratings :lyr] 5.0)
                          (update-in [:ratings :vig] inc))]
      (->> foo-express   ; 2
           :ratings      ; 3
           vals          ; 4
           (average)     ; 5 (Se isto der erro, você precisa
                         ;   avaliar em nível superior a definição
                         ;   da função `average`.)
           )))
  :rcf)

;; == O Debugger do Calva ==
;; https://calva.io/debugger/
;; A forma mais fácil de usá-lo é
;; instrumentar uma função para debug. Você faz isso
;; colocando o cursor na função e então
;; usando o comando:
;; *Instrument Current Top Level Form for Debugging*
;; Depois você chama a função. Isso fará o
;; debugger parar no primeiro ponto de parada
;; da função instrumentada

(comment
  ;; Vamos tentar primeiro sem instrumentação. Esta
  ;; função tem um bug. Avalie-a do jeito de sempre
  ;; (`Alt+Enter`) primeiro e depois chame-a.

  (defn bar
    [n]
    (cond (> n 40) (+ n 20)
          (> n 20) (- (first n) 20)
          :else 0))

  (bar 2)  ; funciona
  (bar 24) ; lança erro, o que está acontecendo?

  ;; Que mensagem de erro estranha (talvez você diga,
  ;; dependendo de quão familiarizado você é com Clojure).
  ;; Agora instrumente a função como descrito acima.
  ;; O Calva vai indicar o código que está instrumentado para
  ;; debug. Agora avalie a chamada de função
  ;; problemática. O debugger vai iniciar e esperar você
  ;; percorrer a função passo a passo.
  ;;
  ;; Para remover a instrumentação da função, basta avaliá-la
  ;; do jeito normal (avaliação em nível superior).
  ;; Documentação do debugger aqui: https://calva.io/debugger/

  ;; NB: Se você é novo em Clojure, pode achar reconfortante
  ;; saber que o Calva tem um debugger.
  ;; No entanto, tente explorar a Programação Interativa
  ;; usando o REPL primeiro. Esse é o Jeito Clojure.
  ;; Esta seção está aqui para você saber que
  ;; o debugger existe, para as raras ocasiões
  ;; em que ele realmente for necessário.
  :rcf)

;; == Interrompendo Loops Infinitos ==
;; Como avaliar expressões Clojure é tão
;; fácil e divertido, às vezes você avalia
;; algo que nunca termina, ou demora demais
;; para terminar. Para isso, o Calva tem um comando chamado
;; *Interrupt Running Evaluations*. Você vai precisar
;; dele se avaliar isto em nível superior:

(comment
  (def tmp1 (dorun (range)))
  :rcf)

;; Pronto? Incrível. Considere se familiarizar
;; mais com o Paredit usando o guia
;; interativo. Se você é novo em Clojure,
;; continue com `welcome_to_clojure.clj`, que é um
;; guia interativo da linguagem Clojure.

;; Tem um projeto Clojure ao qual quer conectar
;; o Calva? Veja: https://calva.io/connect/

;; Aprenda muito mais sobre o Calva em https://calva.io

;; Esta string é a última expressão deste arquivo
"hello_repl.clj foi carregado e está pronto com algumas coisas para você experimentar."
;; É o que você verá impresso na janela de
;; Output quando carregar o arquivo.

;; Este guia foi baixado de:
;; https://github.com/BetterThanTomorrow/dram
;; Considere contribuir.
