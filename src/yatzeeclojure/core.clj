(ns yatzeeclojure.core (:use clojure.test))

(with-test 
  (defn simple [dices digit]
    (reduce + (filter #(= % digit) dices))
    )
  (is (= 0 (simple [2 2 3 4 5] 1)) "No matches")
  (is (= 4 (simple [2 2 3 4 5] 2)) "Two twos")
)