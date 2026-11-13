(ns get-started.hello-paredit)

;; Comece carregando este arquivo
;; Ctrl+Alt+C Enter

;; Clojure é um LISP e, por isso, o código
;; é estrutural. Tudo é organizado em
;; ”forms”, também chamadas de S-expressions (sexprs).
;; https://en.wikipedia.org/wiki/S-expression
;; Um form é qualquer literal, ”símbolo” ou
;; coleção literal (diferentes tipos de
;; listas) de literais. O Paredit ajuda você a
;; aproveitar essa estrutura.

;; == Existem Muitos Comandos do Paredit ==
;; Procure por Paredit na paleta de comandos do
;; VS Code para ver todos os comandos. Preste
;; bastante atenção nos atalhos exibidos
;; para os comandos que você usa com frequência.
;; Veja https://calva.io/paredit para muito
;; mais do que mostramos aqui.

;; Alt+Enter nesta aqui

(->> ["I" "💖" "Paredit"]
     (interpose " ~ ")
     (apply str))

;; (Para entrar num bom humor. 😍)

;; == Proteção do Modo Estrito (Strict Mode) ==
;; O modo estrito do Paredit está ativo por padrão.
;; Ele ajuda você a não apagar parênteses que
;; quebrariam a estrutura do código.
;; Use Alt+Backspace para ignorar a proteção.

(defn strict-greet
  "Tente remover os parênteses e as aspas da string
   usando Backspace ou Delete. Tente o mesmo
   com a tecla Alt pressionada."
  [name]
  (str "Strictly yours, " name "!"))

;; (Restaure com *Undo* se precisar.)

;; == Selecione com Facilidade ==
;; Coloque o cursor em um form, por exemplo em `select-me`
;; abaixo, e então use *Paredit Expand Selection*
;; Repita o comando para expandir mais um nível

(comment
  (-> 4
      (repeat (let [select-me 'bar]
                {:foo select-me}))
      (->>
       (repeat 3))
      (vec)))

;; Também existe o *Paredit Shrink Selection*

;; == Navegue pela Estrutura ==
;; Mova-se form o form usando *Paredit Forward Sexp*
;; e *Paredit Backward Sexp*
;; Nota: Apesar do que a paleta de comandos mostra, os
;; atalhos de teclado no Mac são alt+right/left e
;; no Windows e Linux são ctrl+right/left
;; Veja https://github.com/BetterThanTomorrow/calva/issues/1161

(def move
  [{:zero 0}
   1 2 3
   "four"
   #:five {:bar 'baz}])

;; Experimente também *Paredit Select Forward/Backward*
;; Todos os comandos *Paredit Select ...* funcionam junto com
;; *Paredit Expand/Shrink Selection*

;; == Edite a Estrutura ==
;; Um delete estrutural por dia mantém o médico longe
;; Procure por *Paredit Kill* na Paleta de Comandos

(defn delete
  "Strings são tratadas um pouco como listas, experimente
   *Kill/Delete Sexp Backwards* e outros nesta
   string de documentação"
  [kill-forward kill-backward]
  [{:zero 0}
   1 2 3
   "four"
   #:five {:foo kill-forward
           :bar kill-backward}]
  "Para apagar e copiar, use *Paredit Select ...*
   e depois *Cut*")

;; === Mova Esses Parênteses ===
;; *Paredit Slurp* e *Paredit Barf* são comandos
;; úteis para mover forms para dentro e para fora da
;; lista/vetor/mapa/string atual (ou mover os parênteses,
;; dependendo do seu ponto de vista.)

(def slurp-barf [{:barf-me "barf-me-too"}
                 'slurp-me-then-barf-me])

;; === Raise ===
;; *Paredit Raise Sexp* substitui o form que
;; envolve o atual pelo form ”atual”
;; Experimente Raise e Undo neste trecho:

(comment
  (def raise-me
    #:or-raise-me {:or-me [1 2 3 4]
                   :or-this-> #{1 2 3 4}}))

;; Aprenda muito mais sobre Paredit: https://calva.io/paredit

;; == Insira uma Moeda para Continuar ==
;; Se você é novo em Clojure, continue
;; com `welcome_to_clojure.clj` e avalie seu caminho
;; até um conhecimento básico de Clojure.

"Hello Calva Paredit λ 💖 🚀"

;; Este guia foi baixado de:
;; https://github.com/BetterThanTomorrow/dram
;; Considere contribuir.
