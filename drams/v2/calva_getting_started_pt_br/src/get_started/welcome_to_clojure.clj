(ns get-started.welcome-to-clojure
  (:require [clojure.repl :refer [source apropos dir pst doc find-doc]]
            [clojure.string :as string]
            [clojure.test :refer [is are]]))

;; Bem-vindo ao Clojure! ♥️

;; Comece carregando este arquivo.
;; Ctrl+Alt+C Enter
;; (A tecla Alt às vezes se chama Option ou Opt)

;; Depois avalie esta expressão com Alt+Enter:

"Hello World"

;; Esse é um Hello World bem conciso para qualquer linguagem.
;; E repare que não há parênteses. 😀

;; Este guia vai tentar te dar um entendimento básico
;; da linguagem Clojure. Básico no sentido de que não
;; é extenso. Básico no sentido de que é fundamental,
;; partindo dos primeiros princípios para facilitar a
;; compreensão da jornada Clojure que você tem pela frente.

;; Com as bases estabelecidas, você terá uma boa
;; chance de ter o instinto certo para saber como
;; programar algo, como formular suas perguntas,
;; como buscar informações de forma eficaz, como
;; entender o código que você encontrar por aí, e assim por diante.

;; Haverá links aqui e ali, use ctrl/cmd-click
;; para abri-los no navegador. Aqui está o primeiro
;; desses links;
;; https://clojure.org/guides/learn/syntax
;; Lá você pode ler mais sobre os conceitos
;; mencionados neste guia.

;; A forma de usar o guia é ler sobre os
;; conceitos e avaliar os exemplos. Às vezes haverá
;; exercícios no texto. Mas não se limite a eles.
;; Fique à vontade para editar os exemplos, adicionar
;; código novo e avaliá-lo. Avalie isto para aquecer:

(comment
  (str "Welcome"
       " to "
       "Clojure!"
       " "
       "♥️"))

;; Depois veja o que acontece se você colocar alguns números
;; aqui e ali e avaliar de novo.

;; NB: Este guia ainda é um trabalho em andamento...
;; Quando você criar o projeto Getting Started REPL da
;; próxima vez, ele pode ter sido atualizado. 😀
;; Você também pode sempre encontrar a versão mais recente aqui:
;; https://github.com/BetterThanTomorrow/dram/blob/dev/drams/calva_getting_started/src/get_started/welcome_to_clojure.clj

(comment
  ;; = EXPRESSÕES =
  ;; No Clojure tudo é uma expressão.
  ;; (Não existem statements.) A menos que haja
  ;; um erro ao avaliar uma expressão, sempre
  ;; há um valor de retorno (que às vezes é `nil`).

  ;; Um aspecto importante disso é que o resultado
  ;; de uma expressão é sempre a último form/expressão
  ;; avaliada. Por exemplo, se você tem uma função
  ;; definida assim:

  (defn last-eval-wins []
    (println 'side-effect-1)
    1
    (println 'side-effect-2)
    2)

  ;; Isso define uma função chamada
  ;; `last-eval-wins`, sem argumentos, com quatro
  ;; expressões no corpo da função. (Voltaremos a
  ;; definir funções mais adiante.)

  ;; Chamando a função

  (last-eval-wins) ; <- Avalie isto 😄

  ;; todas as quatro expressões do corpo da função
  ;; serão avaliadas. O resultado da chamada será
  ;; a última expressão que foi avaliada.

  ;; Na janela de saída você também verá as chamadas
  ;; de `println` acontecendo. Elas também são
  ;; expressões, que avaliam para `nil`.

  (println 'prints-this-evaluates-to-nil)

  ;; Expressões são compostas de literais (que avaliam
  ;; para si mesmos) e/ou chamadas a:
  ;; * special forms
  ;; * macros
  ;; * funções

  ;; ”Hello World” no começo deste guia é uma
  ;; string literal (portanto, avalia para si mesma).
  ;; Mais sobre literais na próxima seção.

  ;; Chamadas são escritas como listas com a coisa chamada
  ;; como primeiro elemento.

  (def foo "foo") ; Chama a special form `def`,
                  ; avalia para a var que ela cria
                  ; (Mais sobre isso depois)

  (for [x '(1 2 3)  ; Chama a macro `for`
        y '(:a :b)] ; (List comprehension)
    [x y])

  (str 1 2 3) ; Chama a função `str` com os
              ; argumentos 1, 2 e 3.

  ;; Somente funções podem ser passadas como valores

  (map str [:foo :bar])    ; funciona
  (map for [:foo :bar])    ; não dá para pegar o valor de uma macro
  (map def [:foo :bar])    ; def nem sequer é um símbolo

  )

(comment
  ;; = LITERAIS =
  ;; Literais avaliam para si mesmos.
  ;; (Lembre-se dos seus amigos:
  ;;   Alt+Enter e Ctrl+Enter)

  ;; Tipos numéricos
  18        ; inteiro
  -1.8      ; ponto flutuante
  0.18e2    ; expoente
  18.0M     ; big decimal
  18/324    ; razão
  18N       ; big integer
  0x12      ; hexadecimal
  022       ; octal
  2r10010   ; base 2

  ;; Tipos de caracteres
  "hello"         ; string
  \e              ; caractere
  #"[0-9]+"       ; expressão regular

  ;; Símbolos e identificadores
  map             ; símbolo
  +               ; símbolo - a maioria das pontuações é permitida
  clojure.core/+  ; símbolo com namespace
  nil             ; valor nulo/nil (nomeado na tradição LISP)
  true false      ; booleanos
  :alpha          ; keyword
  :release/alpha  ; keyword com namespace
  ::alpha         ; keyword com namespace,
                  ; no namespace atual

  ;; == KEYWORDS ==
  ;; Keywords começam com `:`. Elas são uma coisa
  ;; em si mesmas, frequentemente usadas como identificadores e
  ;; como chaves em maps (mais sobre maps depois). Keywords são
  ;; muito eficientes em memória e velocidade.

  ;; A mesma keyword é, claro, igual a si mesma

  (= :foo :foo)

  ;; Mas ela também é idêntica a si mesma

  (identical? :foo :foo)

  ;; Isso significa que é a mesma coisa, ocupando o
  ;; mesmo (minúsculo) lugar na memória.
  ;; Mesmo que você construa uma keyword não literal
  ;; ela continua idêntica à sua forma literal

  (identical? (keyword "foo") :foo)

  ;; Isso vale para todo o seu programa Clojure.
  ;; Keywords são globais. Existe uma sintaxe de namespace
  ;; para elas, para que você possa ter controle sobre isso.

  ;; Keywords também são funções, na verdade. Mas mais
  ;; sobre isso depois. Por enquanto basta dizer
  ;; que keywords têm um papel muito especial e importante
  ;; na maioria dos programas Clojure.

  ;; == STRINGS ==
  ;; Em algum lugar entre os literais atômicos e
  ;; as coleções temos as strings. Às vezes elas são
  ;; tratadas como sequências (uma abstração legal sobre a qual
  ;; vou falar mais).
  ;; Strings são delimitadas por aspas duplas.

  "A string can be
   multi-line, but will contain any leading spaces."
  "Write strings
like this, if leading spaces are no-no."

  ;; (As aspas simples são usadas para outra coisa.
  ;; Você verá para quê daqui a pouco.)
  )

;; = DICAS: Ferramentas para praticar Clojure =
;;
;; Aqui estão dois recursos que funcionam muito bem junto
;; com este guia:
;;
;; Rich4Clojure: Pratique Clojure com problemas do 4Clojure no VS Code
;; * https://github.com/PEZ/Rich4Clojure
;; Aprendendo Clojure com Koans usando VS Code e Calva
;; * https://github.com/DanBunea/clojure-koans
;;
;; Ambos permitem que você aprenda e pratique Clojure resolvendo
;; problemas, conectado ao REPL, no editor, como
;; este guia. Uma forma de usá-los é abri-los em
;; janelas separadas do VS Code e ir e voltar entre
;; elas e este guia, conforme você aprende coisas e
;; percebe que precisa aprender mais.

(comment
  ;; = NAMESPACES =
  ;; Por mais importantes que sejam os namespaces, não vamos
  ;; nos prender muito a esse assunto neste guia. A documentação
  ;; oficial faz a melhor justiça a eles:
  ;; https://clojure.org/reference/namespaces
  ;;
  ;; Há algumas coisas que realmente precisamos saber
  ;; porém...
  ;; Símbolos Clojure são definidos em namespaces (com
  ;; o special form `def`) onde ficam acessíveis
  ;; a partir de qualquer outro namespace.

  (def foo-2 "foo")

  ;; Saiba também que existe o conceito de
  ;; namespace atual. (Um pouco como o diretório de trabalho
  ;; atual no shell.) Quando você avaliou o form
  ;; `def` acima, você viu onde `foo-2` foi
  ;; definido.

  ;; Ao avaliar um símbolo de qualquer namespace ele
  ;; precisa ter sido definido, ou o compilador vai
  ;; reclamar e lançar um erro

  foo-3

  ;; O namespace também precisa ter sido criado

  some-namespace/foo

  ;; Se você carregou o arquivo `hello_repl.clj`
  ;; o namespace `hello-repl` foi criado e seus
  ;; símbolos de nível superior estão definidos.

  get-started.hello-repl/greet

  (get-started.hello-repl/greet "from the welcome-to-clojure namespace")

  ;; Se esses lançarem erro, você precisa primeiro carregar
  ;; `hello_repl.clj`, ou pelo menos avaliar seu form `ns`
  ;; e o form `greet`.

  ;; Não é recomendável depender de algum
  ;; namespace existir assim, porém. Isso torna
  ;; seu código frágil. É melhor usar `require`
  ;; no namespace. Se você ainda não o carregou, você
  ;; pode fazer isso de uma vez:

  (require 'get-started.hello-paredit :reload)

  get-started.hello-paredit/strict-greet
  (get-started.hello-paredit/strict-greet "World")

  ;; Para a maior parte do código Clojure que você escrever, você vai organizá-lo
  ;; em arquivos separados com um namespace cada,
  ;; e usar o form `ns` (que inicia a maioria dos arquivos
  ;; Clojure) para fazer `:require` dos namespaces necessários, dando
  ;; a eles um alias conveniente e às vezes fazendo `:refer`
  ;; de alguns de seus símbolos para poder usá-los
  ;; sem o prefixo do namespace (que é o
  ;; texto antes da `/`, aliás, caso isso não fosse
  ;; óbvio o bastante). Examine o form `ns` deste
  ;; arquivo para ver por que estes forms compilam sem
  ;; reclamações:

  (doc require) ; Veja a janela de saída
  (string/split "foo:bar:baz" #":")

  ;; Veja também:
  ;; https://clojuredocs.org/clojure.core/ns

  ;; Qualquer namespace pode ser criado no REPL. Porém,
  ;; quando um namespace é requerido, seja via as funções
  ;; `require` ou `use`, ou via o form `ns`,
  ;; o Clojure Reader procura no classpath o arquivo indicado
  ;; pelo namespace requerido. Ao
  ;; fazer isso, pontos no nome do namespace separam
  ;; diretórios, e traços são substituídos por underscores.
  ;; Digamos que você tenha um `src` no seu classpath, e um arquivo
  ;; `src/foo/bar_baz.clj` no projeto. Esse arquivo deve
  ;; ter um form `ns` parecida com:

  ;(ns foo.bar-baz ,,,)

  ;; E você o requer usando algo como:

  ;(require 'foo.bar-baz)

  ;; Ou:

  ;(ns welcome-to-clojure
  ;  (:require [foo.bar-baz]))

  ;; Se você avaliar qualquer um desses requires, receberá uma
  ;; mensagem de erro do repl, dizendo quais arquivos o
  ;; Clojure Reader procurou para encontrar a definição do namespace.

  ;; Dois erros comuns:
  ;; 1. Nomear arquivos usando traços em vez de underscores.
  ;; 2. Usar `(require ...)` em vez de `(:require)` na
  ;;    form `ns`.

  ;; O form `ns` tem muita funcionalidade e pode ser um pouco
  ;; difícil de entender. Aqui vai uma boa cheat sheet:
  ;; https://gist.github.com/ghoseb/287710/


  ;; === Keywords com namespace ===
  ;; Keywords também podem ter namespace, mas elas não
  ;; são realmente registradas em um namespace, como
  ;; os símbolos, então você pode simplesmente usá-las, sem se preocupar

  :foo-whatever
  :whatever-namespace/foo

  ;; A noção de namespace atual existe
  ;; para keywords no sentido de que o prefixo de dois-pontos duplo
  ;; se expande para `:<namespace-atual>/foo`:

  ::foo

  ;; É importante saber disso. `:foo` vai
  ;; se referir à mesma keyword, independentemente de qual
  ;; namespace ela é usada. `::foo` não.
  )

(comment
  ;; = COLEÇÕES =
  ;; Clojure tem sintaxe literal para quatro tipos de coleção
  ;; Elas avaliam para si mesmas.

  '(1 2 3)     ; list (uma lista com quote, mais sobre isso abaixo)
  [1 2 3]      ; vector
  #{1 2 3}     ; set
  {:a 1 :b 2}  ; map

  ;; Elas se compõem

  {:foo [1 2]
   :bar #{1 2}}

  ;; No Clojure fazemos a maior parte das coisas só com essas
  ;; coleções. Coleções literais e funções.
  )

(comment
  ;; = FUNÇÕES =
  ;; Até agora você conseguiu avaliar todos os exemplos.
  ;; Isso é porque colocamos quote naquela lista.
  ;; Na verdade as listas se parecem com isto

  (1 2 3)

  ;; Mas se você avaliar isso, vai receber um erro:
  ;; => class java.lang.Long cannot be cast to class
  ;;    clojure.lang.IFn
  ;; (Claro, o linter já tinha te avisado.)
  ;; Ao avaliar listas sem quote, o primeiro elemento
  ;; da lista é considerado como estando na ”posição
  ;; de função”. Isso significa que o Clojure vai tentar chamar `1`
  ;; como uma função, o que não vai funcionar porque ele não é
  ;; uma função.
  ;; Você talvez esteja começando a suspeitar que um programa
  ;; Clojure são apenas dados? Isso está correto. O código
  ;; Clojure são dados. Falando de forma mais sofisticada, Clojure é homoicônico:
  ;; https://wiki.c2.com/?HomoiconicLanguages
  ;; Isso dá um grande poder às macros, mais sobre isso abaixo.

  ;; Aqui estão algumas listas com funções de verdade na
  ;; posição 1:

  (str 1 2 3 4 5 :foo)
  (< 1 2 3 4 5)
  (*)
  (= "1"
     (str "1")
     (str \1))
  (println "From Clojure with ♥️")
  (reverse [5 4 3 2 1])

  ;; Tudo depois da primeira posição é
  ;; passado para a função como argumentos

  ;; Nota: vou me referir a literais, símbolos, listas
  ;; e outras coleções literais, coletivamente, como forms,
  ;; às vezes, sexprs:
  ;; https://en.wikipedia.org/wiki/S-expression
  ;; Acima, `(str 1 2 3 4 5 :foo)` é um form, assim como `str`,
  ;; `1` e `:foo`.


  ;; Você define novas funções e as associa a nomes
  ;; no namespace atual usando a macro `defn`.
  ;; É uma macro muito flexível. Aqui vai um uso simples:

  (defn add2
    [arg]
    (+ arg 2))

  ;; Ela define a função `add2` que recebe um argumento.
  ;; O corpo da função chama a função do core `+`
  ;; com os argumentos `arg` e 2.
  ;; Avaliar o form vai defini-la e você verá:
  ;; => #'welcome-to-clojure/add2
  ;; Isso é uma var ”contendo” o valor da função
  ;; Agora você pode referenciar a var usando o símbolo
  ;; `add2`. Colocando-o na posição de função de uma
  ;; lista com 3 na posição do primeiro argumento e
  ;; avaliando a lista, o que recebemos de volta?

  (add2 3)

  ;; Clojure tem uma biblioteca core extensa de funções
  ;; e macros. Veja: https://clojuredocs.org para um
  ;; mecanismo de busca mantido pela comunidade para o core do Clojure (e mais).
  )

(comment
  ;; = SPECIAL FORMS e MACROS =
  ;; A biblioteca core é composta das funções e macros
  ;; da própria biblioteca. O bootstrap da biblioteca são
  ;; uns poucos (cerca de 15) forms primitivos embutidos,
  ;; também conhecidos como ”special forms”.

  ;; Você já conheceu uma dessas special forms:

  (quote (1 2 3))

  ;; O hover de documentação do símbolo `quote` diz que
  ;; ele é um special form.

  ;; Está se perguntando onde você viu esse special form antes?
  ;; Eu usei a sintaxe abreviada dela naquela hora:

  '(1 2 3)

  ;; Convença-se de que são iguais com a função `=`:

  (= (quote (1 2 3))
     '(1 2 3))

  ;; Clojure tem semântica de valor. Quaisquer estruturas de dados
  ;; que avaliam para os mesmos dados são iguais,
  ;; não importa o quão profundas ou grandes sejam as estruturas.

  (= [1 [1 #{1 {:a 1 :b '(:foo bar)}}]]
     [1 [1 #{1 {:a (- 3 2) :b (quote (:foo bar))}}]])

  ;; ... mas isso foi um desvio, de volta às special forms.
  ;; Documentação oficial:
  ;; https://clojure.org/reference/special_forms#_other_special_forms

  ;; Um special form muito importante é `fn` (que na
  ;; verdade são quatro special forms, mas enfim).
  ;; Sem esse form não conseguimos definir novas funções.
  ;; O form a seguir avalia para uma função que
  ;; soma 2 ao seu argumento.

  (fn [arg] (+ arg 2))

  ;; Chamando a função com o argumento 3:

  ((fn [arg] (+ arg 2)) 3)

  ;; Outro special form é `def`. Ele define coisas,
  ;; dando a elas nomes com namespace.

  (def foo :foo)

  ;; ”Definir uma coisa” significa que uma var é criada,
  ;; contendo o valor, e que um símbolo é associado
  ;; à var. Avaliar o símbolo pega o
  ;; valor da var à qual ele está associado.

  foo

  ;; A var pode ser acessada usando o special
  ;; form `var`.

  (var foo)

  ;; Você verá com mais frequência a forma abreviada do var-quote

  #'foo

  ;; Com esses dois special forms podemos definir funções

  (def add2-2 (fn [arg] (+ arg 2)))
  (add2-2 3)

  ;; É isso que a macro `defn` faz. Na maioria das vezes você
  ;; vai definir funções como vimos
  ;; antes (ao discutir a posição de função de
  ;; um form):

  (defn add2-3
    [arg]
    (+ arg 2))

  ;; Podemos usar a função `macroexpand` para ver o que
  ;; a macro produz:

  (macroexpand '(defn add2-3
                  [arg]
                  (+ arg 2)))

  ;; Mais um special form super importante:

  (if 'test
    'value-if-true
    'value-if-false)

  ;; `macroexpand` não faz nada aqui, já que `if` não é
  ;; uma macro:

  (macroexpand '(if test
                  value-if-true
                  value-if-false))

  ;; (Nem é uma função)

  ;; Curiosidade: além de `case`, todas as construções condicionais e de controle
  ;; de fluxo do Clojure são construídas usando `if`:

  (macroexpand '(when test
                  value-if-true))

  (macroexpand '(or a b))

  (require 'clojure.walk) ;; você precisará avaliar isto

  (clojure.walk/macroexpand-all '(or a b)) ;; para avaliar isto

  (macroexpand '(cond
                  y value-if-y
                  z value-if-z
                  :else value-if-x-neither-y-nor-z))

  (clojure.walk/macroexpand-all '(cond
                                   y value-if-y
                                   z value-if-z
                                   :else value-if-x-neither-y-nor-z))

  ;; Uma linguagem de programação precisa de seus condicionais. Mas
  ;; no núcleo o Clojure quase se vira apenas com `if`.
  ;; Quase. Como mencionei antes, `case` é uma exceção.
  ;; Pelo menos na JVM, onde o Clojure usa jump tables para
  ;; implementá-lo:

  (macroexpand '(case x
                  y value-if-x-is-y
                  z value-if-x-is-z
                  value-if-x-is-neither-y-nor-z))


  ;; Voltaremos ao `if` e aos condicionais.

  ;; == `let` ==
  ;; `let` é um special form que permite associar valores a
  ;; variáveis que serão usadas no corpo do form.

  (let [x 1
        y 2]
    (str x y))

  ;; As associações são fornecidas como o primeiro ”argumento”,
  ;; em um vector. Esse é um padrão usado por
  ;; outras special forms e macros que permitem definir
  ;; associações. É parecido com o escopo léxico de outras
  ;; linguagens de programação (embora este seja mais
  ;; estrutural). Forms irmãos e pais não
  ;; ”enxergam” essas associações (não há como elas
  ;; possivelmente alcançá-las). Aqui está um exemplo:

  (do
    (def x :namespace-x)
    (println "`x` in `do` _before_ `let`: " x)
    (let [x :let-x]
      (println "`x` from `let`: " x))
    (println "`x` in `do`, _after_ `let`: " x))

  (println "`x` _outside_ `do`: " x)

  ;; Como observado antes neste guia, o special
  ;; form `def` define coisas ”globalmente”, ainda que com namespace.

  ;; Se você seguiu as instruções para examinar
  ;; as coisas mencionadas aqui, por exemplo
  ;; ctrl/cmd-clicando no símbolo `let` nos trechos
  ;; de código, vai descobrir que em `core.clj`, `let`
  ;; é definido como uma macro. Não ligue para isso. Ele é
  ;; de fato tratado como special form aqui
  ;; https://clojure.org/reference/special_forms#let

  ;; Vamos (trocadilho não intencional) encerrar a seção de special forms
  ;; observando que, junto com _como_ o Clojure
  ;; lê e avalia o código, os special forms compõem
  ;; a própria linguagem Clojure. O próximo nível de
  ;; blocos de construção são as macros.

  ;; Mas vamos investigar primeiro essa coisa de como o código
  ;; é lido...
  )

(comment
  ;; = O READER =
  ;; https://clojure.org/reference/reader
  ;; O Clojure Reader é responsável por ler texto,
  ;; fazendo dados a partir dele, que é o que o compilador recebe.
  ;; O Reader é onde literais, símbolos, strings, listas,
  ;; vectors, maps e sets são separados e
  ;; remontados, descobrindo o que é uma função,
  ;; uma macro ou um special form.

  ;; Nesse processo o espaço em branco tem um papel fundamental e há
  ;; também algumas regras extras de sintaxe em jogo.

  ;; == ESPAÇO EM BRANCO ==
  ;; A maioria das coisas que você imaginaria que contam como espaço em branco
  ;; é espaço em branco, e além disso o Clojure,
  ;; sendo um LISP, não precisa de vírgulas para separar
  ;; itens de uma lista. Porém, vírgulas podem ser usadas para isso
  ;; mesmo assim, já que vírgulas são espaço em branco.

  (= '(1 2 3)
     '(1,2,3)
     '(1, 2, 3)
     '(1,,,,2,,,,3))

  ;; (Não há operadores no Clojure, `=` é uma
  ;; função. Ela verifica a igualdade de todos os
  ;; argumentos que recebe.)

  ;; == COMENTÁRIOS DE LINHA ==
  ;; O Reader pula a leitura de tudo em uma linha a partir
  ;; de um ponto e vírgula. Isso são comentários não estruturados, no
  ;; sentido de que se você inicia um form

  (range 1 ; 10)
  ;; e depois coloca um comentário de linha de modo que o colchete
  ;; de fechamento desse form fique comentado, a
  ;; estrutura quebra.
         )
  ;;     ^ Curando a estrutura.

  ;; Se você remover o ponto e vírgula do form de abertura
  ;; acima, lembre-se de remover também este parêntese de fechamento.

  ;; Como tudo na linha é ignorado, você pode
  ;; adicionar quantos pontos e vírgulas quiser.
  ;;;;;;;;;; (pulado pelo Reader)
  ;; É comum usar dois pontos e vírgulas para iniciar um
  ;; comentário de linha inteira.

  ;; == SINTAXE EXTRA ===
  ;; Já vimos a aspa simples

  'something

  ;; Que, como vimos, é transformada em

  (quote something)

  ;; `quote` é necessário para impedir que o Reader trate
  ;; as coisas como algo que deve ser avaliado.
  ;; Veja o que acontece se você avaliar `something`
  ;; sem o quote:

  something

  ;; bem como a diferença entre avaliar estes:

  (1 2 3 4)
  '(1 2 3 4)

  ;; Existem mais alguns símbolos de quoting, e até de splicing,
  ;; que não vou cobrir neste guia.

  ;; === Deref ===
  ;; Clojure também tem tipos de referência, vamos discutir
  ;; (brevemente) o mais comum, `atom`, mais adiante.

  (def an-atom (atom [1 2 3]))
  (type an-atom)

  ;; Para acessar o valor de uma referência:

  (deref an-atom)
  (type (deref an-atom))

  ;; Novamente, `deref` é usado para fazer dereference de muitos
  ;; tipos diferentes de referência, incluindo futures,
  ;; https://clojure.org/reference/refs
  ;; https://clojure.org/about/concurrent_programming

  ;; Enfim, `deref` é tão comum que existe uma
  ;; sintaxe abreviada para ele

  @an-atom
  (= (deref an-atom)
     @an-atom)

  ;; É um erro comum esquecer de fazer deref

  (first an-atom)
  (first @an-atom)

  ;; === O DISPATCHER (SÍMBOLO DE HASH) ===
  ;; Esse símbolo de hash aparece de vez em quando. Ele tem um
  ;; papel especial. Também é conhecido como Dispatch.
  ;; Dependendo de qual caractere vem depois dele,
  ;; coisas legais diferentes acontecem. Algumas das legais
  ;; seguem aqui:

  ;; Expressões regulares têm sintaxe literal, são
  ;; escritas como strings, mas com um símbolo de hash na frente

  #"reg(?:ular )?exp(?:ression)?"

  ;; Regexps são tratadas pela plataforma host, então
  ;; são regexps Java neste tutorial. Se você
  ;; avaliou a regexp acima, podemos testá-la.

  (re-seq *1 "regexp regular expression")

  ;; `*1` é um símbolo especial para uma variável que guarda
  ;; o valor do último resultado de avaliação. Pode
  ;; ser mais fácil acertar uma regexp usando-o
  ;; diretamente:

  (re-seq #"fooo*" "fo foo fooo")
  (re-find #"fooo*" "fo foo fooo")

  ;; Se o símbolo de hash for seguido por um `(`, o Reader
  ;; passará a esperar um corpo de função.

  #(+ % 2)

  ;; Essa é uma sintaxe especial para ”function literals”, uma
  ;; forma de especificar uma função. O exemplo acima é
  ;; equivalente a esta função anônima.

  (fn [arg] (+ arg 2))

  ;; Aninhar function literals é uma atividade proibida

  ;(#(+ % (#(- % 2) 3)))

  ;; (felizmente)

  ;; Além de sets, regexps e function literals
  ;; vimos var-quotes mais cedo neste guia

  #'add2

  ;; Também houve uma breve discussão sobre `vars`.
  ;; Talvez você queira revisitá-la e também ler mais
  ;; sobre isso, porque é um conceito muito importante.
  ;; https://clojure.org/reference/vars

  ;; Existe um hash-dispatcher muito útil que
  ;; faz o Reader ignorar o próximo form

  #_(println "The reader will not send this function call
to the compiler") "This is not ignored"

  ;; Para testar isso, selecione o marcador de ignorar junto com
  ;; a chamada de função e a string, depois use Alt+Enter,
  ;; para fazer o Calva enviar tudo ao Reader, que vai
  ;; lê-lo, ignorar a chamada de função e avaliar apenas
  ;; a string.

  ;; Como #_ ignora o próximo form, ele é um mecanismo de
  ;; comentário estrutural, frequentemente usado para desabilitar temporariamente
  ;; algum código ou alguns dados

  (str "a" "b" #_(str 1 2 3 [4 5 6]) "c")

  ;; Marcadores de ignorar se acumulam

  (str "a" #_#_"b" (str 1 2 3 [4 5 6]) "c")

  ;; Note que o Reader _vai_ ler o form ignorado.
  ;; Se houver erros de sintaxe nele, o
  ;; Reader vai ficar triste, reclamar e parar de ler.
  ;; Selecione do marcador até a string inclusive
  ;; aqui e pressione Ctrl+Enter

  ;#_(#(+ % (#(- % 2) 3))) "foo"

  ;; Mais duas variantes de # comuns que você verá, e usará,
  ;; são a sintaxe abreviada de keywords em map com namespace e
  ;; tagged literals, também conhecidos como data readers. Vamos começar
  ;; com a primeira:

  (= #:foo {:bar 'bar
            :baz 'baz}
     {:foo/bar 'bar
      :foo/baz 'baz})

  ;; Sem relação com o #: Existe outra abreviação para
  ;; especificar keywords com namespace. Keywords com dois-pontos duplo
  ;; recebem o namespace atual

  ::foo
  (= ::foo :calva-getting-started.src.get-started.welcome-to-clojure/foo)

  ;; Tagged literals, então. É uma forma de invocar funções
  ;; associadas às tags no form que vem depois delas.
  ;; https://clojure.org/reference/reader#tagged_literals
  ;; Eles também são chamados de data readers. Você pode
  ;; definir os seus. Aqui basta mencionar
  ;; os dois que já vêm embutidos.

  ;; #inst converte a string que ele marca em um instante.
  ;; (Ou seja, um momento no tempo)

  #inst "2018-03-28T10:48:00.000"
  (type *1)

  ;; #uuid cria um UUID a partir da string que ele marca

  #uuid "0000000-0000-0000-0000-000000000016"
  (java.util.UUID/fromString "0000000-0000-0000-0000-000000000016")

  ;; Agora você sabe como ler (no sentido de você
  ;; ser um Clojure Reader) a maior parte do código Clojure.
  ;; Dito isso, vamos pular a parte do açúcar
  ;; sintático e dos special forms que tornam a
  ;; interoperabilidade com a plataforma host especialmente agradável.
  ;; https://clojure.org/reference/java_interop
  ;; Só uma espiadinha:

  (.before #inst "2018-03-28T10:48:00.000"
           #inst "2021-02-17T00:27:00.000")

  ;; Isso invoca o método `before` no objeto de data
  ;; do ano 2018, passando a data do
  ;; ano 2021 como argumento. Você verá um pouco
  ;; mais de interop com Java neste guia e provavelmente
  ;; vai notar como a plataforma host está à mão ao
  ;; programar em Clojure. O mesmo vale para
  ;; ClojureScript e para Clojure CLR.

  ;; Repetindo este importante recurso sobre o Reader:
  ;; https://clojure.org/reference/reader
  ;; E, além disso, leia sobre Todos Aqueles
  ;; Caracteres Estranhos aqui:
  ;; https://clojure.org/guides/weird_characters
  )

(comment
  ;; = MACROS =
  ;; Clojure tem poderosas capacidades de transformação
  ;; de dados. Vamos tocar nisso um pouco mais adiante.
  ;; Aqui quero destacar que esse poder pode
  ;; ser usado para estender a própria linguagem.
  ;; Como o código Clojure é estruturado e código é
  ;; dado, Clojure pode ser usado para produzir código
  ;; Clojure a partir de código Clojure. É parecido com as
  ;; facilidades de pré-processador que algumas linguagens
  ;; oferecem, como o `#pragma` do C, mas é muito mais
  ;; conveniente e poderoso. Muito do que você
  ;; vai aprender a amar e reconhecer como Clojure
  ;; é, na verdade, criado com Clojure, como macros.

  ;; Este guia se preocupa principalmente em te deixar
  ;; saber que macros existem, para ajudar você a
  ;; perceber rapidamente quando está usando uma macro em vez
  ;; de uma função. Ou seja, não vou entrar no
  ;; assunto de como criar macros.

  ;; A distinção é importante, porque mesmo que
  ;; chamadas de macro se pareçam muito com chamadas de função,
  ;; macros não são de primeira classe. Elas não podem ser
  ;; passadas como argumentos, nem retornadas como resultados.
  ;; Mais sobre ”primeira classe” na seção sobre
  ;; funções, adiante.

  ;; == `when` ==
  ;; Vamos examinar brevemente a macro `when`.
  ;; Essa macro ajuda a escrever código mais legível.
  ;; Como? Digamos que você queira avaliar algo
  ;; condicionalmente. Acima você aprendeu que existe
  ;; um special form chamado `if` que pode ser usada para
  ;; isso. Assim:

  (if 'this-is-true
    'evaluate-this
    'else-evaluate-this)

  ;; Agora digamos que você não tenha nada para avaliar
  ;; no caso else. `if` permite escrever isto

  (if 'this-is-true
    'evaluate-this)

  ;; O que é ok, mas você terá que examinar o
  ;; código um pouco mais para ver que não há branch
  ;; else. Fácil com este exemplo curto, mas pode
  ;; ficar bem complicado em código real. Para resolver isso,
  ;; você poderia escrever:

  (if 'this-is-true
    'evaluate-this
    nil)

  ;; Mas isso é meio bobo, e se existisse uma
  ;; forma de dizer ao humano que lê o código que
  ;; não há branch else? Existe!

  (when 'this-is-true
    'evaluate-this)

  ;; Vamos ver como `when` é definido, você pode
  ;; ctrl/cmd-clicar em `when` para navegar até onde
  ;; ele é definido no `core.clj` do Clojure.
  ;; Você também pode usar a função `macroexpand`

  (macroexpand '(when 'this-is-true
                  'evaluate-this))

  ;; Você vai notar que `when` envolve o corpo em
  ;; um `(do ...)`, que é um special form que permite
  ;; avaliar várias expressões, retornando o
  ;; resultado da última.
  ;; https://clojuredocs.org/clojure.core/do
  ;; `do` é útil quando você quer ter algum efeito
  ;; colateral acontecendo, além de avaliar algo.
  ;; No desenvolvimento isso costuma acontecer quando você
  ;; quer dar um `println` em algo antes de o resultado
  ;; da expressão ser avaliado e retornado.

  (do (println "The quick brown fox jumps over the lazy dog")
      (+ 2 2))

  ;; A macro `when` permite aproveitar que
  ;; existe apenas um branch, então você pode fazer isto

  (when 'this-is-true
    (println "The quick brown fox jumps over the lazy dog")
    (+ 2 2))

  ;; Sem `when` você escreveria:

  (if 'this-is-true
    (do
      (println "The quick brown fox jumps over the lazy dog")
      (+ 2 2)))

  ;; Aqui `when` nos poupa tanto de examinar
  ;; o branch else quanto do uso de `do`.

  ;; Como macros, `when` é praticamente a mais simples
  ;; que existe. A partir de dois special forms embutidos,
  ;; `if` e `do`, ela compõe um form que nos ajuda a
  ;; escrever código fácil de escrever e fácil de ler.

  ;; == `for` ==
  ;; A macro `for` realmente demonstra como o Clojure
  ;; pode ser estendido usando Clojure. Você pode pensar que
  ;; ela fornece laços como o for de muitas
  ;; outras linguagens, mas no Clojure não existem laços
  ;; for. Em vez disso, `for` trata de list comprehensions
  ;; (se você tem experiência com Python, sim, esse tipo de
  ;; list comprehension). Veja como produzir o
  ;; produto cartesiano de dois vectors, `x` e `y`:

  (for [x [1 2 3]
        y [1 2 3 4]]
    [x y])

  ;; Se você lembra do form `let` acima, e de como ele
  ;; permite associar variáveis para usar no corpo do
  ;; form, isto é parecido, só que `x` e `y` serão
  ;; associados a cada valor das sequências e o
  ;; corpo será avaliado para todas as combinações de
  ;; `x` e `y`.

  ;; Todos os valores? Bem, `for` também permite filtrar os
  ;; resultados

  (for [x [1 2 3]
        y [1 2 3 4]
        :when (not= x y)]
    [x y])

  ;; Você pode associar nomes de variáveis na comprehension
  ;; para guardar cálculos intermediários e, em geral,
  ;; deixar o código mais legível

  (for [x [1 2 3]
        y [1 2 3 4]
        :let [d' (- x y)
              d (Math/abs d')]]
    d)

  ;; É o mesmo que:

  (for [x [1 2 3]
        y [1 2 3 4]]
    (Math/abs (- x y)))

  ;; Discutível qual é mais legível neste caso
  ;; específico... ¯\_(ツ)_/¯

  ;; Uma observação sobre o nome de variável `d'` acima:
  ;; `d'` é só um nome de símbolo como qualquer outro. A
  ;; aspa simples não tem significado especial a menos que seja
  ;; o primeiro caractere

  ;; Filtros e associações podem ser usados juntos.
  ;; Use `:let` e `:when` para fazer esta
  ;; comprehension retornar uma lista de todos os `[x y]` em que
  ;; a soma deles seja ímpar. As funções `+` e `odd?`
  ;; são suas amigas aqui.

  (for [x [1 2 3]
        y [1 2 3 4]]
    [x y])

  ;; (Sim, dá para resolver sem `:let` nem `:when`.
  ;; Me faça esse favor. 😎)

  ;; Veja https://www.youtube.com/watch?v=5lvV9ICwaMo para
  ;; uma ótima introdução às list comprehensions do Clojure
  ;; Veja https://clojuredocs.org/clojure.core/for para
  ;; exemplos de uso e dicas.

  ;; Note que, mesmo que `let` e `for` pareçam
  ;; funções, elas não são. O compilador não
  ;; gostaria se você passasse símbolos não definidos para uma
  ;; função. Este é um código válido:

  (let [abc 1]
    2)

  ;; Este não é.

  (str [abc 1]
       1)

  ;; (Repare que o linter clj-kondo marca o
  ;; primeiro com um aviso, e o segundo como um erro)
  ;; Macros estendem o compilador do Clojure.
  ;; https://clojure.org/reference/macros

  ;; == Threading macros ==
  ;; Macros podem reorganizar totalmente o seu código. As
  ;; macros ”threading” embutidas fazem isso. Às vezes,
  ;; quando o aninhamento de chamadas de função(-ish) fica
  ;; profundo, pode ficar difícil de ler e de acompanhar
  ;; todos os parênteses

  (Math/abs
   (apply -
          (:d (zipmap
               [:a :b :c :d]
               (partition 2 [1 1 2 3 5 8 13 21])))))

  ;; Você lê Clojure da expressão mais interna
  ;; para fora, o que fica mais fácil com o tempo, mas um
  ;; programador Clojure experiente ainda acharia
  ;; mais fácil ler isto

  (->> [1 1 2 3 5 8 13 21]
       (partition 2)
       (zipmap [:a :b :c :d])
       :d
       (apply -)
       (Math/abs))

  ;; Vamos ler isto juntos. A macro thread-last,
  ;; `->>`, é usada, ela pega o seu primeiro argumento e
  ;; o coloca (faz thread dele) como último argumento da
  ;; função seguinte. O primeiro passo desses, em
  ;; isolamento:

  (->> [1 1 2 3 5 8 13 21]
       (partition 2))

  ;; O primeiro argumento/elemento passado para `->>` é
  ;; `[1 1 2 3 5 8 13 21]`
  ;; Ele é inserido como último elemento da
  ;; chamada de função `(partition 2)`, resultando em:

  (partition 2 [1 1 2 3 5 8 13 21])

  ;; Isso particiona a lista em listas de
  ;; 2 elementos => `((1 1) (2 3) (5 8) (13 21))`
  ;; Essa nova lista é então inserida (thread)
  ;; como último argumento da próxima função,
  ;; resultando em:

  (zipmap [:a :b :c :d] '((1 1) (2 3) (5 8) (13 21)))

  ;; Que faz o ”zip” de um map do Clojure usando
  ;; a primeira lista como chaves e a segunda lista
  ;; como valores
  ;; => `{:a (1 1), :b (2 3), :c (5 8), :d (13 21)}`
  ;; Esse map é então passado como último argumento
  ;; para a função `:d`

  (:d '{:a (1 1), :b (2 3), :c (5 8), :d (13 21)})

  ;; (No Clojure, keywords são funções que se procuram
  ;;  no map que recebem.)
  ;; => `(13 21)`
  ;; Você já manja, isto passa pelo thread

  (apply - '(13 21))

  ;; Que aplica a função `-` sobre a lista
  ;; => `-8`
  ;; Depois isso passa pelo thread para `Math/abs`

  (Math/abs -8)
  ;; 🎉

  ;; (Em muitos editores com suporte a Clojure, incluindo
  ;; o Calva, existem comandos para ”desenrolar”
  ;; um thread, e para converter expressões
  ;; aninhadas em um thread. Procure por ”thread”
  ;; entre os comandos.)
  ;; https://github.com/clojure-emacs/clj-refactor.el/wiki/cljr-unwind-all

  ;; Existe também uma macro thread-first
  ;; `->` https://clojuredocs.org/clojure.core/-%3E
  ;; Às vezes você não quer fazer thread nem no primeiro
  ;; nem no último. Existe uma macro para isso também.
  ;; `as->` permite associar um nome de variável ao
  ;; valor do thread e colocá-lo onde você
  ;; preferir em cada chamada de função.

  (as-> 15 foo
    (range 1 foo 3)
    (interpose ":" foo))

  ;; https://clojuredocs.org/clojure.core/as-%3E

  ;; É comum aproveitar o fato de que a maioria dos caracteres
  ;; está disponível para nomear símbolos Clojure. Eu costumo
  ;; usar `$` para essa macro de threading:

  (as-> 15 $
    (range 1 $ 3)
    (interpose ":" $))

  ;; Outros usam outros nomes 😄

  (as-> 15 <>
    (range 1 <> 3)
    (interpose ":" <>))

  ;; Acho que emojis devem ser evitados, a documentação
  ;; oficial só menciona alfanuméricos mais:
  ;; `*`, `+`, `!`, `-`, `_`, `'`, `?`, `<`, `>` e `=`
  ;; (então nem mesmo `$`) mas lá vai:

  (as-> 15 ❤️
    (range 1 ❤️ 3)
    (interpose ":" ❤️))

  ;; Outras macros de threading do core são:
  ;; `cond->`, `cond->>`, `some->` e `some->>`
  ;; https://clojuredocs.org/clojure.core/cond-%3E

  ;; Fique à vontade para copiar os exemplos
  ;; do ClojureDocs aqui e brincar com eles.
  ;; Aqui vai um:

  (cond-> 1        ; começamos com 1
    true inc       ; a condição é true então (inc 1) => 2
    false (* 42)   ; a condição é false então a operação é pulada
    (= 2 2) (* 3)) ; (= 2 2) é true então (* 2 3) => 6

  ;; Veja ”Threading with Style” de Stuart Sierra
  ;; para o uso idiomático dos recursos de threading.
  ;; https://stuartsierra.com/2018/07/06/threading-with-style
  )

;; Com os special forms, a sintaxe especial do Reader
;; e as macros, as bases do que é a linguagem Clojure
;; que você usa estão lançadas. Você pode, claro, estender
;; a linguagem ainda mais com bibliotecas que incluem macros
;; ou criar as suas próprias. No entanto, a linguagem core, com
;; suas macros, é muito expressiva. Adotar abordagens
;; orientadas a dados costuma ser suficiente. Até preferível, em vez
;; de criar mais macros.

;; Vamos ao controle de fluxo!

(comment
  ;; = Controle de Fluxo, Condicionais, Branching =
  ;; Clojure é mais rico que a maioria das linguagens no que
  ;; nos oferece para fazer nossos programas fluírem do jeito que queremos.
  ;; Quase todos os recursos da biblioteca core para
  ;; isso são implementados usando o primitivo (special
  ;; form) `if`. Ele continua sendo a base para nós como
  ;; programadores Clojure. Ele recebe três forms como
  ;; argumentos:
  ;; 1. Uma condição a ser avaliada
  ;; 2. O que avaliar se a condição avaliar
  ;;    para algo verdadeiro (truthy)
  ;; 3. O form a avaliar se a condição não
  ;;    avaliar para algo truthy (o branch ”else”)
  ;; Jogue este dado, umas dez ou vinte vezes, verificando se
  ;; saiu um seis:

  (if (= 6 (inc (rand-int 6)))
    "Uma vez em cada seis você tira um seis"
    "Cinco vezes em cada seis você tira outra coisa")

  ;; Como não há statements no Clojure, `if` é
  ;; o equivalente à expressão `if` ternária que você
  ;; encontra em C e em muitas outras linguagens:
  ;;   test ? true-expression : false-expression
  ;; Pseudocódigo para o nosso dado:
  ;;   int(rand() * 6) + 1 == 6 ?
  ;;     "Uma vez em cada seis você tira um seis" :
  ;;     "Cinco vezes em cada seis você tira outra coisa";

  ;; == A Busca pela Verdade ==
  ;; Mais uma vez, no Clojure usamos expressões que avaliam para
  ;; valores. Quando examinados para branching, todos os valores
  ;; são truthy ou falsy. Na verdade, quase todos os
  ;; valores são truthy

  (if true :truthy :falsy)
  (if :foo :truthy :falsy)
  (if '() :truthy :falsy)
  (if 0 :truthy :falsy)
  (if "" :truthy :falsy)

  ;; Os únicos valores falsy são `false` e `nil`

  (if false :truthy :falsy)
  (if nil :truthy :falsy)
  (when false :truthy)

  ;; Sobre esse último: `when` avalia para `nil`
  ;; quando a condição é falsy. Como `nil` é
  ;; falsy, a expressão `when` acima faria
  ;; o branch ”else” de um `if` ser
  ;; avaliado

  (if (when false :truthy) :true :falsy)

  ;; (Código super extra ruim, mas enfim)
  ;; Quando só a verdade ou falsidade booleana serve
  ;; para você, existe a função `true?`

  (true? true)
  (true? 0)
  (true? '())
  (true? nil)
  (true? false)

  ;; Portanto

  (if (true? 0) :true :false)

  ;; == `when` ==
  ;; Como mencionado antes, `when` é um `if` de um só branch,
  ;; apenas para o branch truthy, que é
  ;; envolvido em um `do` para você. Experimente isto e depois
  ;; experimente trocar o `when` por um `if`:

  (when :truthy
    (println "That sounds true to me")
    :truthy-for-you)

  ;; Se a condição do `when` não for verdadeira
  ;; (truthy), `nil` será retornado.

  (when nil :true-enough?)

  ;; == `cond` ==
  ;; Como estruturas if/else muito aninhadas são
  ;; difíceis de escrever, ler e manter, o core do
  ;; Clojure oferece várias outras construções para
  ;; controle de fluxo. Uma muito comum é a macro
  ;; `cond`. Ela recebe pares de forms de
  ;; condição/resultado, testa cada condição e, se
  ;; for verdadeira, o form de resultado é avaliado e
  ;; ”retornado”, interrompendo o processo para que
  ;; nenhuma outra condição seja testada.

  (let [dice-roll (inc (rand-int 6))]
    (cond
      (= 6 dice-roll)  "Six is as high as it gets"
      (odd? dice-roll) (str "An odd roll " dice-roll " is")
      :else            (str "Not six, nor odd, instead: " dice-roll)))

  ;; O `:else` é apenas a keyword `:else`, que
  ;; avalia para si mesma e é truthy. É a forma
  ;; convencional de dar um valor padrão aos seus
  ;; forms `cond`. Sem uma cláusula padrão, o
  ;; form avaliaria para `nil` para qualquer valor
  ;; que não seja seis nem ímpar. Teste isso
  ;; colocando dois marcadores de ignorar
  ;; (`#_ #_`) na frente da keyword `:else`.

  ;; Tem que amar o ClojureDocs
  ;; https://clojuredocs.org/clojure.core/cond
  ;; Cole exemplos de lá aqui e experimente:

  ;; Veja também os links sobre `cond->` acima

  ;; == `case` ==
  ;; Um pouco parecida com as construções
  ;; `switch/case` de outras linguagens, o core do
  ;; Clojure tem a macro `case`, que recebe uma
  ;; expressão de teste, seguida de zero ou mais
  ;; cláusulas (pares) de constante de teste/expr,
  ;; seguidas de uma expr opcional. (Porém, o corpo
  ;; depois da expressão de teste não pode ser vazio.)

  (let [test-str "foo bar"]
    (case test-str
      "foo bar" (str "That's very " :foo-bar)
      "baz"     :baz
      (count    test-str)))

  ;; A expressão final, se houver, é ”retornada” como
  ;; o valor padrão.

  (let [test-str "foo bar"]
    (case test-str
      #_#_"foo bar" (str "That's very " :foo-bar)
      "baz"     :baz
      (count    test-str)))

  ;; Se nenhuma cláusula casar e não houver padrão,
  ;; ocorre um erro em tempo de execução

  (let [test-str "foo bar"]
    (case test-str
      #_#_"foo bar" (str "That's very " :foo-bar)
      "baz"     :baz
      #_(count    test-str)))

  ;; ATENÇÃO! Uma constante de teste precisa ser um
  ;; literal em tempo de compilação, e o compilador
  ;; não vai ajudar você a achar bugs como este:

  (let [test-int 2
        two 2]
    (case test-int
      1     :one
      two   (str "That's not a literal 2")
      (str test-int ": Probably not expected")))

  ;; https://clojuredocs.org/clojure.core/case
  ;; Cole alguns exemplos de `case` aqui e experimente

  ;; O podcast Functional Design in Clojure tem um
  ;; episódio fantástico sobre ramificação (branching)
  ;; https://clojuredesign.club/episode/089-branching-out/

  ;; == Menos ramificação é bom, certo? ==
  ;; A biblioteca core é rica em funções que ajudam
  ;; você a evitar escrever código com ramificações.
  ;; Em vez disso, você fornece a condição como um
  ;; predicado. Uma função predicado muito usada é `filter`

  (filter even? [0 1 2 3 4 5 6 7 8 9 10 11 12])

  ;; e sua ”irmã” `remove`

  (remove odd? [0 1 2 3 4 5 6 7 8 9 10 11 12])

  ;; Filtrar sequências de valores é uma tarefa comum
  ;; e o seu tempo de programação pode ser usado
  ;; para decidir _como_ filtrar, escrevendo
  ;; o predicado. Às vezes você nem precisa fazer
  ;; isso, o core do Clojure é rico em predicados

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

  ;; O que é um predicado? Para os fins deste guia,
  ;; um predicado é uma função que testa coisas
  ;; quanto à veracidade (truthiness). Por convenção, essas funções
  ;; terminam com `?`. Muitas recebem só um argumento.

  ;; Um predicado útil é `some?`, que testa
  ;; ”algo-ice”: se não for `nil`, é
  ;; alguma coisa

  (some? nil)
  (some? false)
  (some? '())

  ;; Você pode usá-lo para testar se algo é `nil`
  ;; envolvendo-o em uma chamada à função `not`

  (not (some? nil))
  (not (some? false))

  ;; Dá vontade de definir uma função chamada `nil?`,
  ;; né? Você não precisa

  (nil? nil)
  (nil? false)

  ;; O core do Clojure também contém predicados que
  ;; recebem um predicado mais uma coleção para
  ;; aplicá-lo. Como `every?`

  (every? nat-int? [0 1 2])
  (every? nat-int? [-1 0 1 2])

  ;; Confira a documentação de `nat-int?` e invente
  ;; mais listas para testar, como

  (every? nat-int? [0 1 2N]) ; 2N não é de precisão fixa
  (doc nat-int?)

  ;; Esse padrão de funções que recebem funções
  ;; como argumento é comum em Clojure. Vai além
  ;; dos predicados. Funções que recebem funções
  ;; como argumentos são chamadas de ”funções de
  ;; ordem superior” (higher order functions).
  ;; https://pt.wikipedia.org/wiki/Fun%C3%A7%C3%A3o_de_ordem_superior
  )

(comment
  ;; = Funções =
  ;; Antes de mergulhar nas funções de ordem
  ;; superior, vamos olhar para as funções. Funções
  ;; são cidadãs de primeira classe do Clojure e os
  ;; principais blocos de construção para resolver
  ;; os seus problemas de negócio.

  ;; Já vimos algumas formas de criar funções.
  ;; Aqui está uma função anônima que retorna o
  ;; inteiro recebido, a menos que ele seja divisível
  ;; por 15, caso em que retorna "fizz buzz".
  ;; (Nem de longe o problema Fizz Buzz completo.)

  (fn [n]
    (if (zero? (mod n 15))
      "fizz buzz"
      n))

  ;; Vamos defini-la (vinculá-la a um símbolo que possamos usar)

  (def fizz-buzz-1 (fn [n]
                     (if (zero? (mod n 15))
                       "fizz buzz"
                       n)))
  (fizz-buzz-1 2)
  (fizz-buzz-1 15)

  ;; Há uma macro que nos permite definir e criar
  ;; a função em uma única chamada

  (defn fizz-buzz-2 [n]
    (if (zero? (mod n 15))
      "fizz buzz"
      n))

  (fizz-buzz-2 4)

  ;; `defn` nos permite fornecer documentação para a
  ;; função

  (defn fizz-buzz-3
    "Says 'fizz buzz' if `n` is divisible by 15,
     otherwise says `n`"
    [n]
    (if (zero? (mod n 15))
      "fizz buzz"
      n))

  (doc fizz-buzz-3) ; (ou passe o mouse sobre `fizz-buzz-3`)

  ;; É fácil colocar a doc string no lugar errado,
  ;; especialmente porque é comum escrever o form
  ;; `defn` como fizemos com `fizz-buzz-2` acima.

  (defn fizz-buzz-4
    [n]
    "Says 'fizz buzz' if `n` is divisible by 15,
     otherwise says `n`"
    (if (zero? (mod n 15))
      "fizz buzz"
      n))

  ;; Isso especifica um corpo de função totalmente válido,
  ;; então o Clojure não vai reclamar. Mas:

  (doc fizz-buzz-4)

  ;; A configuração padrão do clj-kondo ajuda você a
  ;; identificar esses erros. Porém, ela não ajuda
  ;; com este:

  (defn only-the-last-eval-returns [x]
    [1 x]
    [2 x])

  (only-the-last-eval-returns "foo")

  ;; É fácil de perceber assim, e também de
  ;; se perguntar por que alguém escreveria uma função
  ;; desse jeito. Mesmo assim, você provavelmente vai cometer esse erro,
  ;; principalmente se escrever Hiccup, que
  ;; é uma forma super legal de escrever HTML com
  ;; estruturas de dados Clojure. É usado pela popular
  ;; biblioteca Reagent
  ;; https://purelyfunctional.tv/guide/reagent/#hiccup
  ;; Quando você cometer esse erro e terminar sua
  ;; caçada de uma hora ao bug, vai ouvir este guia sussurrar
  ;;   ”Eu avisei!”

  ;; O vetor de binding de argumentos de `fn` (e
  ;; portanto de `defn`) vincula cada argumento, em
  ;; ordem, a um nome.

  (defn coords->str [x y]
    (str "x: " x ", y: " y))

  ;; == Funções Variádicas ==
  ;; Você pode definir funções que recebem um número
  ;; arbitrário de argumentos colocando um `&` na frente
  ;; do nome do último argumento. Isso vincula o nome
  ;; a uma sequência que contém todos os argumentos
  ;; restantes.

  (defn lead+members [lead & members]
    {:lead lead
     :members members})

  (lead+members "Dave Mustain"
                "Marty Friedman"
                "Nick Menza"
                "David Ellefson")

  ;; == Multi-arity ==
  ;; Clojure suporta assinaturas de função baseadas
  ;; no número de argumentos. A macro `defn` permite
  ;; definir cada aridade (arity) como uma lista separada. Isso
  ;; é frequentemente usado para fornecer valores padrão

  (defn hello
    ([] (hello "World"))
    ([s] (str "Hello " s "!")))

  (hello)
  (hello "Clojure Friend")

  ;; Ou para criar um valor de ”identidade” para uma função
  ;; (um valor inicial a partir do qual o resto da operação
  ;; continua). Digamos que você queira somar duas coordenadas x-y

  (defn add-coords-1 [coord-1 coord-2]
    {:x (+ (:x coord-1)
           (:x coord-2))
     :y (+ (:y coord-1)
           (:y coord-2))})

  (add-coords-1 {:x -2 :y 10}
                {:x 4 :y 6})

  ;; E se o requisito fosse que, se a função for
  ;; chamada com um argumento, ela deve
  ;; somá-lo à origem? (Viu o que eu fiz aí?
  ;; O valor de identidade é onde a função
  ;; deve começar, então começamos da origem. 😎)
  ;; Dá para ver que `add-coords-1` falha aqui
  (add-coords-1 {:x -2 :y 10})

  ;; precisamos adicionar uma aridade de um argumento

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

  ;; Agora, se chamada sem argumentos, ela deve
  ;; retornar a origem, porque se você não somar
  ;; nenhuma coordenada, você continua no início.
  ;; Escreva uma função `add-coords-3` que retorne
  ;; a origem quando chamada assim

  (add-coords-3)

  ;; Ela ainda deve funcionar quando chamada assim

  (add-coords-3 {:x 3 :y 4})
  (add-coords-3 {:x 2 :y 4}
                {:x -4 :y -4})

  ;; Isso tem a ver com fazer a função compor
  ;; com outras funções. Por exemplo, a função `apply`,
  ;; que é uma função de ordem superior que ”aplica”
  ;; uma função sobre uma sequência. Agora mesmo podemos
  ;; aplicar nossa função `add-coords-2` assim
  (apply add-coords-2 [{:x 1 :y 1} {:x 4 :y 4}])

  ;; E assim

  (apply add-coords-2 [{:x 1 :y 1}])

  ;; Mas não assim

  (apply add-coords-2 [])

  ;; Mas a função `add-coords-3` que você criou consegue

  (apply add-coords-3 [])

  ;; Ela não vai lidar com uma sequência de coords de
  ;; tamanho arbitrário, porém. Para isso precisaríamos de uma
  ;; aridade a mais, assim

  (defn add-coords-4
    ;; adicione aqui a aridade zero do seu `add-coords-3`
    ;; adicione aqui a aridade um do seu `add-coords-3`
    ([coord-1 coord-2]
     {:x (+ (:x coord-1)
            (:x coord-2))
      :y (+ (:y coord-1)
            (:y coord-2))})
    ([coord-1 coord-2 & more-coords]
     ;; Implemente esta aridade quando tiver aprendido
     ;; sobre a função de ordem superior `reduce`
     ))

  (apply add-coords-4 [{:x 1 :y 1}
                       {:x 1 :y 1}
                       {:x 1 :y 1}
                       {:x -6 :y -6}])

  ;; Ouça Eric Normand explicar em mais detalhes
  ;; por que a identidade de uma função é importante:
  ;; https://lispcast.com/what-is-a-functions-identity/

  ;; == Closures ==
  ;; Quando você cria funções na hora, lambdas,
  ;; se preferir, você usa o special form `fn`
  ;; diretamente, ou por tabela a sintaxe `#()`.
  ;; Isso cria uma closure, como em JavaScript
  ;; e outras linguagens. Ou seja, essas funções podem
  ;; acessar instantâneos de variáveis com os valores que
  ;; elas tinham quando a função foi criada

  (defn named-coords-factory [name]
    (fn [x y] {:name name
               :coords {:x x
                        :y y}}))

  (def bob-coords-fn (named-coords-factory "Bob"))
  (def fred-coords-fn (named-coords-factory "Fred"))

  (bob-coords-fn 0 0)
  (fred-coords-fn 5 5)
  (bob-coords-fn 7 7)

  ;; Closures são úteis para criar funções de baixa aridade
  ;; dentro de blocos let binding para o corpo da função
  ;; usar:

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

  ;; Todas as funções criadas no bloco let binding
  ;; ”fecham sobre” o `command` e a `sentence`, então
  ;; o `case` pode ficar enxuto e legível.

  (whisper-or-yell-or-ask "" "How wOnDerFuLLY NIce To seE")
  (whisper-or-yell-or-ask "!" "Hello tHERE")
  (whisper-or-yell-or-ask "?" "How are you doing")
  (whisper-or-yell-or-ask ":" "Oh well")

  ;; == O Mapa de Atributos ==
  ;; A macro `defn` permite adicionar atributos à
  ;; função na forma de um mapa. Ele é adicionado
  ;; como metadados (um pouco mais sobre isso depois)
  ;; à var que guarda a função. O mapa vai
  ;; depois do nome da função, depois de qualquer doc,
  ;; e antes do vetor de argumentos (ou de qualquer aridade)

  (defn i-have-attributes
    {:doc "Docs can be added like this too"
     :foo "Any attributes you fancy"}
    []
    "Good for you")

  (doc i-have-attributes)
  (meta #'i-have-attributes)

  ;; Um atributo útil que você pode adicionar é uma
  ;; função de teste. Os test runners vão detectá-la

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
  ;; Ops! Você vai precisar corrigir os bugs. 😀

  ;; Que tal implementar o Fizz Buzz completo?
  ;; https://pt.wikipedia.org/wiki/Fizz_Buzz

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

  ;; Os metadados que têm significado especial para
  ;; o compilador e várias funcionalidades do core do
  ;; Clojure estão listados aqui:
  ;; https://clojure.org/reference/special_forms

  ;; Agora, vamos às funções de ordem superior!
  )

(comment
  ;; = Funções de ordem superior =
  ;; Uma grande contribuição para fazer do Clojure uma
  ;; linguagem tão poderosa é que as funções são
  ;; ”de primeira classe”
  ;; https://pt.wikipedia.org/wiki/Fun%C3%A7%C3%A3o_de_primeira_classe
  ;; Elas podem ser valores em coleções (também chaves
  ;; em mapas) e podem ser passadas como argumentos para outras
  ;; funções, e ”retornadas” como resultados de
  ;; avaliações. Você pode já conhecer o
  ;; conceito de linguagens como JavaScript.

  ;; Vamos ver algumas funções de ordem superior no
  ;; core do Clojure. `some` chama a função sobre os
  ;; elementos da coleção, um a um, e
  ;; retorna o primeiro resultado truthy, ou retorna
  ;; `nil` se a lista acabar antes de algum elemento
  ;; resultar em algo truthy.

  (some even? [1 1 2 3 5 8 13 21])

  ;; Não confundir com `some?`, que não é
  ;; uma função de ordem superior.

  (some some? [nil false])
  (some some? [nil nil])

  ;; Um idioma comum em Clojure é procurar coisas
  ;; em uma coleção usando um `set` como predicado.
  ;; Sim, sets são funções. Usados como funções eles
  ;; procuram o argumento recebido dentro de
  ;; si mesmos.

  (#{"foo" "bar"} "bar")

  ;; Portanto

  (some #{"foo"} ["foo" "bar" "baz"])
  (some #{"fubar"} ["foo" "bar" "baz"])

  ;; `apply` recebe uma função e uma coleção e
  ;; ”aplica” a função sobre a coleção. Digamos que você
  ;; tenha uma coleção de números e queira somá-los.
  ;; Isto não vai funcionar:

  (+ [1 1 2 3 5 8 13 21])

  ;; `apply` ao resgate

  (apply + [1 1 2 3 5 8 13 21])

  ;; Concatene os números como uma string:

  (apply str [1 1 2 3 5 8 13 21])

  ;; Compare com

  (str [1 1 2 3 5 8 13 21])

  ;; Também vimos `filter` e `remove` acima, duas
  ;; funções de ordem superior muito usadas. Elas
  ;; jogam na mesma liga de `map` e `reduce`.
  ;; Continue lendo. 😎
  )

(comment
  ;; = `map` e `reduce` =
  ;; Entre as funções de ordem superior que você pode ter
  ;; usado em outras linguagens com funções de primeira classe
  ;; estão `map` e `reduce`. Vale estudá-las e
  ;; praticá-las em detalhe, aqui vai
  ;; uma prévia super legal:
  ;; https://purelyfunctional.tv/courses/3-functional-tools/

  ;; Vamos dar uma olhada rápida nelas aqui também.
  ;; `map` chama uma função sobre os elementos de uma ou
  ;; mais coleções, do início ao fim, e retorna uma
  ;; sequência (lazy, mais sobre isso depois) com os resultados
  ;; na mesma ordem. Digamos que queremos decrementar
  ;; cada elemento de uma lista de números em um

  (map dec '(1 1 2 3 5 8 13 21))

  ;; Digamos que depois queremos decrementá-los de novo

  (->> '(1 1 2 3 5 8 13 21)
       (map dec)
       (map dec))

  ;; Hmmm, melhor subtrair dois de uma vez, talvez?

  (map (fn [n] (- n 2)) '(1 1 2 3 5 8 13 21))

  ;; Se você der mais coleções para o `map` trabalhar,
  ;; ele vai repetidamente:
  ;; 1. pegar o próximo item de cada coleção
  ;; 2. passá-los como argumentos para a função de mapeamento
  ;; 3. adicionar o resultado à sequência de retorno
  ;; Até que a menor coleção se esgote

  (map + [1 2 3] '(0 2 4 6 8))
  (map (fn [n1 s n2] (str n1 ": " s "-" n2))
       (range)
       ["foo" "bar" "baz"]
       (range 2 -1 -1))

  ;; (Ainda não falamos muito sobre `range`, uma
  ;; função que produz sequências de números. Sem
  ;; argumentos ela produz uma sequência infinita (cuidado 😀)
  ;; de inteiros
  ;; 0, 0+1, 0+2, 0+3, 0+4, 0+5, 0.6 ...
  ;; Ainda bem que as outras sequências se esgotaram!)

  ;; Muitas das tarefas que você resolveria com laços
  ;; `for` em outras linguagens são resolvidas com `map`
  ;; em Clojure.

  ;; Com outras tarefas ”de laço for” você
  ;; vai usar `reduce`. Diferente de `map`, ele não é
  ;; limitado a produzir resultados com o mesmo tamanho
  ;; ou formato da coleção de entrada. Em vez disso, ele
  ;; acumula um resultado de qualquer formato. Por exemplo,
  ;; ele pode criar uma string a partir de uma coleção de
  ;; números

  (reduce (fn [acc n]
            (str acc ":" n))
          [1 1 2 3 5 8 13 21])

  ;; `reduce` chama a função com dois
  ;;  argumentos: o resultado da última chamada da
  ;;  função e o próximo número da lista. O
  ;;  início do processo é especial, pois ainda
  ;;  não há resultados. `reduce` tem duas
  ;;  formas de lidar com isso, duas aridades para
  ;;  ser específico. Chamado com dois argumentos, ele
  ;;  usa os dois primeiros elementos da lista
  ;;  na primeira chamada da função.
  ;;  Aqui está a redução da função `+` usando a
  ;;  versão de duas aridades de `reduce`

  (reduce + [1 1 2 3 5 8 13 21])

  ;;  O processo então começa chamando `+`
  ;;  assim

  (+ 1 1)

  ;; Dar três argumentos ao `reduce` faz com que ele use
  ;; o segundo argumento como o ”resultado” inicial.

  (reduce + 100 [1 1 2 3 5 8 13 21])

  ;; Você pode ter notado que a função `+`
  ;; recebe mais (e menos) do que 2 argumentos.

  (+)
  (+ 1)
  (+ 1 1)
  (+ 1 1 2 3 5 8 13 21)

  ;; `+` pega o primeiro argumento, se houver, e
  ;; o soma ao valor ”atual” (que é zero),
  ;; depois pega o próximo argumento e soma ao novo
  ;; valor atual, e assim por diante, até que
  ;; haja um resultado. Esse processo parece um pouco
  ;; com o que acabei de descrever como um reduce, certo?
  ;; Na verdade, é.

  ;; Se fôssemos implementar a função `+`, como
  ;; poderíamos fazer? Poderíamos começar implementando
  ;; algo que soma dois números e, em seguida,
  ;; usá-lo como a função redutora do
  ;; `reduce`.
  ;; Claro, agora temos a tarefa de somar dois
  ;; números sem usar a função
  ;; `+` existente... 🤔 O SICP tem essa, usando
  ;; Aritmética de Peano
  ;; https://youtu.be/V_7mmwpgJHU?t=814
  ;; https://pt.wikipedia.org/wiki/Axiomas_de_Peano

  (defn plus [x y]
    (if (zero? x)
      y
      (plus (dec x) (inc y))))

  ;; Mas há casos demais não tratados aqui...
  ;; Hmmm... Vamos simplificar e fazer apenas
  ;; matemática com inteiros. Então podemos usar o método
  ;; `Integer.sum(x, y)` do Java.

  (Integer/sum 1 1)

  ;; Incrível, com isso podemos criar uma função
  ;; `add-two`

  (defn add-two [x y]
    (Integer/sum x y))
  (add-two 1 1)

  ;; Diferente de `+`, esta não é totalmente composável
  ;; com uma função de ordem superior como apply

  (apply add-two [])
  (apply add-two [1])
  (apply add-two [1 1])
  (apply add-two [1 1 2 3 5 8 13 21])

  ;; Precisamos de `add-many`. Com `reduce` e nosso
  ;; `add-two` podemos definir `add-many` assim

  (defn add-many [& numbers]
    (reduce add-two numbers))
  ;; Isso resolve, certo?

  (apply add-many [1])
  (apply add-many [1 1])
  (apply add-many [1 1 2 3 5 8 13 21])

  ;; E a versão de aridade zero de `+`, você
  ;; pergunta? Correto, essa vai explodir

  (add-many)

  ;; A função `+` embutida tem um valor ”atual”
  ;; padrão de zero, lembra? Podemos adicionar isso a
  ;; `add-many` de duas formas: adicionando uma assinatura
  ;; de aridade zero, ou usando o `reduce` de três aridades.
  ;; Vamos pela segunda opção, já que estamos aprendendo
  ;; sobre reduce aqui:

  (defn add* [& numbers]
    (reduce add-two 0 numbers))
  (add*)
  (add* 1)
  (add* 1 1)
  (add* 1 1 2 3)

  ;; BOOM.

  ;; Podemos usá-la com `apply` também:
  (apply add* [])
  (apply add* [1])
  (apply add* [1 1])
  (apply add* [1 1 2 3 5 8 13 21])

  ;; Ou `reduce`:
  (reduce add* [])
  (reduce add* [1])
  (reduce add* [1 1])
  (reduce add* [1 1 2 3 5 8 13 21])

  ;; Tirando o fato de que só tratamos inteiros, nosso `add*`
  ;; é bem parecido com como `+` é implementado no
  ;; core do Clojure. Dê uma olhada (na janela de output):

  (source +)

  ;; Hmmm, bem, parece que eles usam assinaturas de função
  ;; multi-arity para os casos de baixa aridade, provavelmente
  ;; por causa do casting, mas enfim, 😀

  ;; Há mais uma coisa sobre `reduce` que queremos
  ;; mencionar. Ao escrever funções redutoras você pode
  ;; interromper o processo antes de a sequência de entrada
  ;; se esgotar, usando a função `reduced`. Digamos que queremos
  ;; a sequência de entrada como uma string separada por
  ;; `:`, como acima, mas parar quando virmos um item `nil`.
  ;; Aqui está a última versão para comparação:

  (reduce (fn [acc n]
            (str acc ":" n))
          [1 1 2 3 5 8 nil 13 21])

  ;; Podemos interromper o processo chamando
  ;; `reduced` com o valor acumulado quando
  ;; encontrarmos um item `nil`

  (reduce (fn [acc n]
            (if (nil? n)
              (reduced acc)
              (str acc ":" n)))
          [1 1 2 3 5 8 nil 13 21])

  ;; Veja o que está acontecendo

  (doc reduced)

  ;; Reduzir é um conceito muito importante em Clojure
  ;; já que é uma linguagem ”funcional em primeiro lugar”. Ou, como
  ;; diz este episódio do Functional Design
  ;; https://clojuredesign.club/episode/058-reducing-it-down/
  ;; ”Funções redutoras são a espinha dorsal da
  ;; programação funcional, porque não temos mutação.”

  ;; Na verdade, em Clojure reduzir é tão importante que
  ;; Rich Hickey adicionou uma biblioteca inteira de reducers
  ;; com ainda mais poder
  ;; https://clojure.org/reference/reducers
  ;; De novo, a dupla do Functional Design, Nate Jones e
  ;; Christoph Neumann, examinou um pouco essa
  ;; biblioteca:
  ;; https://clojuredesign.club/episode/060-reduce-done-quick/
  ;; Citação incrível desse episódio:
  ;;   “A abstração seq é, digamos, bem preguiçosa (lazy).”

  ;; Não vamos descer pela toca do coelho da
  ;; biblioteca `reducers`, porém...
  )

;; ... Em vez disso, vamos retomar que Nate e
;; Christoph mencionam três conceitos super importantes
;; nessas duas citações acima.
;; * imutabilidade
;; * a abstração `seq`
;; * laziness (avaliação preguiçosa)
;; Eles são relacionados, e talvez seja melhor começar pela
;; imutabilidade...

(comment
  ;; = Imutabilidade =
  ;; É bem louco que tenhamos falado de Clojure por
  ;; tanto tempo sem discutir como ela nos incentiva
  ;; a evitar mutar nossos dados enquanto eles
  ;; são processados. Clojurians não se calam
  ;; sobre imutabilidade, né? Quase soamos
  ;; como Rothbardianos ao nos definirmos como
  ;; Inimigos do Estado 😄
  ;; https://www.youtube.com/watch?v=qe60zwUAOqE

  ;; Isso é verdade até certo ponto: como Clojurians,
  ;; costumamos tentar ficar em um modo de transformação
  ;; de dados durante toda a operação e só lidar
  ;; com o mundo impuro nas ”fronteiras”. No
  ;; início podemos estar lendo alguma entrada, e
  ;; no fim podemos estar atualizando um banco de dados,
  ;; imprimindo os resultados em um arquivo (ou na
  ;; tela), ou mutando o DOM de uma página web.

  ;; Clojure nos incentiva a seguir o caminho
  ;; imutável de várias formas, das quais vou
  ;; mencionar duas aqui:
  ;; * Estruturas de Dados Persistentes
  ;; * Funções Puras

  ;; == Estruturas de Dados Persistentes ==
  ;; Clojure nos ajuda a ficar na terra da imutabilidade
  ;; fornecendo estruturas de dados imutáveis.
  ;; A implementação delas é chamada de Estrutura de
  ;; Dados Persistente:
  ;; https://pt.wikipedia.org/wiki/Estrutura_de_dados_persistente
  ;; Na prática significa que as estruturas de dados
  ;; nunca são alteradas. As funções que usamos para transformá-las
  ;; na verdade criam cópias. (De um jeito muito esperto,
  ;; então não comece a se preocupar agora.)

  ;; Digamos que definimos um vetor de alguns dígitos

  (def eighteen [1 0 0 1 0])
  eighteen

  ;; Agora queremos mudar aquele último `0` para `1`
  ;; Podemos usar a função `assoc`. Usada em
  ;; um vetor, ela recebe um índice e o novo valor

  (def nineteen (assoc eighteen 4 1))
  nineteen

  ;; Examinando `eighteen` de novo ...

  eighteen

  ;; ... vemos que ele continua fiel ao seu nome.
  ;; Fazer assoc de `1` no índice 4 criou uma
  ;; cópia, que então foi definida como `nineteen`

  ;; Talvez óbvio, isso vale também para
  ;; bindings locais.

  (let [origin {:x 0
                :y 0}
        x-travel (assoc origin :x 100)]
    [origin x-travel])

  ;; Isso proporciona um fluxo de programa muito
  ;; determinístico. Os dados não mudam do nada
  ;; debaixo dos nossos pés. E processos de transformação que não
  ;; mutam estado são muito mais fáceis de paralelizar,
  ;; outras threads não podem alterar os dados que você está
  ;; transformando. Uma categoria inteira de bugs nunca tem
  ;; a chance de eclodir!

  ;; Outro benefício que ganhamos com a imutabilidade é
  ;; que o Clojure pode nos oferecer eficientemente
  ;; igualdade de valor. Valores são imutáveis, por definição.
  ;; Em Clojure, até as estruturas de dados mais profundas podem
  ;; ser comparadas em um piscar de olhos.

  ;; Vamos mostrar isso com uma estrutura nem tão profunda
  ;; (exceto no nome)

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

  ;; `update-in` é uma função de ordem superior para
  ;;  transformar estruturas de dados dados um
  ;;  ”endereço” e uma função. Podemos usá-la
  ;;  para fazer o Bob-dois encontrar a Alice-dois, assim como
  ;;  o Bob-um e a Alice-um se encontraram

  (def unified-universa
    (update-in universa [:two "Bob" :z] inc))
  unified-universa

  (= (:one unified-universa)
     (:two unified-universa))

  (= universa unified-universa)

  ;; Você nunca mais vai precisar escrever um método
  ;; `equals()`! 😄

  ;; A imutabilidade também faz nossos programas serem diferentes
  ;; de quando você pode mudar o valor de
  ;; uma variável à vontade. Pode levar um tempo para se
  ;; acostumar. (Eu ainda estou no ponto em que
  ;; acho mais fácil enxergar soluções com mutação
  ;; para muitos problemas. Isso acontece cada vez menos, mas
  ;; enfim. Provavelmente você vai pegar o jeito mais rápido do
  ;; que eu.)

  ;; Vale totalmente a pena insistir em entender.
  ;; O retorno é enorme. Se você for conferir só
  ;; um dos recursos que recomendo
  ;; neste guia, sugiro que seja este sobre
  ;; resolver problemas do jeito Clojure, de Rafal
  ;; Dittwald:
  ;; https://www.youtube.com/watch?v=vK1DazRK_a0
  ;; Spoiler: ele não usa Clojure no vídeo

  ;; Claro, na palestra o Rafal não está apenas
  ;; fingindo que os dados são imutáveis. Ele também está
  ;; empregando pureza de funções.

  ;; == Funções puras
  ;; Clojure não força a pureza em você, como
  ;; algumas linguagens fazem (olhando para você, Haskell), mas
  ;; facilita cair no hábito de
  ;; escrever funções puras e assim empurrar os efeitos colaterais
  ;; para as ”bordas” do seu programa.

  ;; Uma função é considerada pura se obedece a
  ;; estas regras:
  ;; 1. Sempre retorna o mesmo valor para a mesma entrada
  ;; 2. Não afeta nada no seu ambiente.
  ;;    Ou seja, não muta nada, incluindo não imprimir
  ;;    nada em lugar nenhum, nem chamar endpoints de API
  ;;    que mutam estado.

  ;; Uma função pura é determinística e você pode
  ;; chamá-la com segurança sem se preocupar que ela vá atualizar
  ;; o estado da aplicação ou fazer qualquer coisa além de
  ;; calcular seu valor de retorno com base na entrada que você
  ;; entregou, e nada além da entrada que você entregou.
  )

;; Antes de examinar a abstração `seq`, vamos desviar
;; um pouco para algumas funções comuns do core do Clojure para
;; transformar estruturas de dados.

(comment
  ;; = Transformando Estruturas de Dados =
  ;; Clojure tem uma biblioteca core que torna fácil,
  ;; divertido e legível ”alcançar” uma estrutura de
  ;; dados e manipulá-la, criando uma
  ;; cópia com o resultado.

  ;; Já vimos `assoc`, que cria uma cópia da
  ;; estrutura de dados com um novo valor no índice
  ;; (no caso de um `vector`) ou na chave (no caso de um mapa)

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

  ;; `assoc` pode adicionar uma nova chave a um mapa

  (def colt-express-w-age
    (assoc colt-express :age-from 10))

  ;; Com um vetor você só pode adicionar um novo item logo
  ;; depois do último item, não além

  (def board-games-empty
    [])

  (def board-games-w-c-e
    (assoc board-games-empty 0 colt-express))

  ;; board-games-empty continua vazio. Portanto

  (def board-games-w-c-e-and-exit-fail
    (assoc board-games-empty 1 exit-haunted))

  (def board-games-w-c-e-and-exit
    (assoc board-games-w-c-e 1 exit-haunted))

  ;; Não que seja muito comum adicionar coisas
  ;; a um vetor usando `assoc`. Para isso `conj`
  ;; costuma fazer mais sentido

  (conj board-games-empty colt-express exit-haunted)

  ;; `assoc` em mapas pode substituir valores existentes
  ;; (na cópia)

  (def colt-express-w-age-and-adjusted-playtime
    (assoc colt-express-w-age :play-time 45))

  ;; `assoc` em vetores também pode fazer isso

  (def board-games-w-adjusted-c-e
    (assoc board-games-w-c-e
           0
           colt-express-w-age-and-adjusted-playtime))

  ;; Você pode fazer `assoc` de várias coisas em uma chamada

  (assoc colt-express
         :play-time 50
         :age-from 10)

  (assoc board-games-empty
         0 colt-express
         1 exit-haunted)

  ;; (De novo, existe `conj` para isso.)

  ;; Com mapas também existe `merge`, que permite
  ;; mesclar dois ou mais mapas

  (merge colt-express
         {:play-time 45
          :age-from 10})

  ;; Obs.: é uma mesclagem ”rasa”, então adicionar a nota de um
  ;; membro da família assim não vai funcionar.

  (merge exit-haunted
         {:play-time 90
          :ratings {:lun 5}
          :age-from 10})

  ;; `assoc` faz o mesmo

  (assoc exit-haunted :ratings {:lun 5})

  ;; Não existe deep-merge no core do Clojure, mas
  ;; existe `assoc-in` para alcançar níveis mais profundos
  ;; Em vez de uma chave (ou índice) ele recebe um ”caminho”

  (assoc-in exit-haunted [:ratings :lun] 5)

  (assoc-in colt-express [:categories 2] "Planning")
  ;; (Mas... não faça isso, veja abaixo em `update` como
  ;; fazer `conj` da categoria.)

  ;; Diferente de `assoc`, você só pode adicionar uma coisa
  ;; por vez com `assoc-in`

  ;; Remover coisas de um mapa é feito com
  ;; `dissoc`

  (dissoc colt-express :play-time :ratings :categories)

  ;; Você provavelmente vai usar `dissoc` com frequência com
  ;; o REPL (como você faz neste arquivo) para
  ;; examinar estruturas de dados que podem
  ;; ter coisas grandes dentro delas,
  ;; como um log ou algo assim

  (dissoc colt-express :log)
  ;; (Esta estrutura de dados não tinha nenhum log,
  ;; então ficou inalterada, mas enfim.)

  ;; Não existe `dissoc-in` no core do Clojure, mas
  ;; vamos voltar a isso depois de visitar
  ;; `update` e `update-in`.

  ;; `update` e `update-in` são parecidas com
  ;; suas contrapartes `assoc`, mas em vez de um
  ;; valor, recebem uma função que é usada para
  ;; manipular o valor.

  (update exit-haunted :name string/upper-case)

  ;; Um exercício para você: faça update do `:play-time`
  ;; da entrada `colt-express` somando 5 ou algo assim




  ;; Argumentos que você adiciona depois da função
  ;; são passados para a função

  (update colt-express :categories conj "Planning")

  ;; Exercício: faça seu update do :play-time
  ;; receber o `5` (ou outro valor) como argumento.




  ;; Exercício: Remova as entradas `:pez` e `:wiv`
  ;; de `:ratings` em `exit-haunted`





  ;; `update-in` está para `assoc-in` assim como `update` está
  ;; para `assoc`.

  (update-in colt-express [:ratings :lun] inc)

  (update-in colt-express [:ratings :lun] + 9000)
  ;; https://www.youtube.com/watch?v=PCHxU7witPA

  ;; Exercício: Não existe `dissoc-in`, mas parece
  ;; que dá para usar `update-in` para isso,
  ;; não é?





  ;; A recompensa é uma visita a menos ao StackOverflow
  ;; para você quando faltar `dissoc-in` 😄
  ;; https://stackoverflow.com/a/21942548/44639

  ;; Já usamos keywords como funções de busca em mapas
  ;; antes. Tudo bem, mas às vezes você pode preferir
  ;; a função `get`

  (get colt-express :ratings)

  (= (:ratings colt-express)
     (get colt-express :ratings))

  ;; `get` recebe um terceiro argumento que será usado
  ;;  como padrão, caso a entrada não exista

  (get exit-haunted :play-time 0)

  ;; keywords como funções de busca também suportam
  ;; isso

  (:play-time exit-haunted 0)

  ;; Sem o padrão, `nil` será retornado.
  ;; O que pode explodir, dependendo de para que você
  ;; usa o valor

  (* (get colt-express :play-time) 2)
  (* (get exit-haunted :play-time) 2)

  ;; Melhor prevenir do que remediar, em casos como este

  (* (get colt-express :play-time 0) 2)
  (* (get exit-haunted :play-time 0) 2)

  ;; Sim, existe `get-in` também
  ;; Exercício: Use `get-in` para pegar a minha nota
  ;; nesses dois maravilhosos jogos de família


  ;; Você pode ter notado que todas as
  ;; funções desta seção recebem a coleção
  ;; como primeiro argumento. Isso as torna
  ;; fáceis de usar com a macro Thread First, `->`.
  ;; Isso é intencional e altamente idiomático
  ;; em Clojure.

  ;; É comum ver pipelines de transformação de
  ;; dados assim

  (-> exit-haunted
      (assoc :play-time 90)
      (update :categories conj "Scary")
      (assoc-in [:ratings :lun] 5)
      (update-in [:ratings :vig] + 1)
      (dissoc :name)
      (update :log vec)
      (update :log conj "Name redacted")
      (update :log conj "(Because scary)"))

  ;; (Embora, talvez, algo mais significativo que isso)

  ;; Recomendo navegar pelos links ”See also” do ClojureDocs
  ;; começando por aqui:
  ;; https://clojuredocs.org/clojure.core/update-in
  ;; E colar muitos exemplos aqui para
  ;; experimentar.
  )

(comment
  ;; == Manipulando `sets` ==
  ;; Mapas, vetores e sets são o pão com
  ;; manteiga da maioria dos programas Clojure. Com a
  ;; sintaxe literal incrível deles, o código fica
  ;; fácil de ler e de raciocinar sobre. E
  ;; manipulá-los é fácil e intuitivo.

  ;; `sets` são `seqs` (mais sobre isso depois)
)


;; Continua...

;; Até que haja mais material para ler aqui, talvez
;; seja hora de ver como conectar o Calva aos
;; seus projetos Clojure/ClojureScript:
;; https://calva.io/connect/

;; Coisas na lista do que ainda falta escrever:
;; metadados
;; comentários
;; destructuring
;; atoms
;; nil, segurança com nil, nil punning
;; seqs
;; laziness
;; loop, recur
;; depuração (debugging)
;; alguns exercícios de encerramento aqui e ali

;; Aprenda muito mais Clojure em https://clojure.org/
;; Existe também o ClojureScript, a mesma linguagem maravilhosa,
;; para VMs JavaScript: https://clojurescript.org

;; Há muito sobre Clojure que não foi mencionado neste
;; guia curto. https://clojure.org/ é onde você
;; vai para a história completa.

;; Para obter ajuda com suas dúvidas de Clojure, confira estes
;; recursos:
;; https://ask.clojure.org/
;; https://clojurians.net
;; https://clojureverse.org
;; https://www.reddit.com/r/Clojure/
;; https://exercism.io/tracks/clojure

;; E há também muitos outros recursos, como:
;; https://clojuredocs.org
;; https://clojure.org/api/cheatsheet

"Arquivo carregado. Bem-vindo ao Clojure! ♥️"

;; Este guia foi baixado de:
;; https://github.com/BetterThanTomorrow/dram
;; Por favor, considere contribuir.
