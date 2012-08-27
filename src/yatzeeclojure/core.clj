(ns yatzeeclojure.core (:use clojure.test))

(with-test 
  (defn simple [dices digit]
    (reduce + (filter #(= % digit) dices))
    )
  (is (= 0 (simple [2 2 3 4 5] 1)) "No matches")
  (is (= 4 (simple [2 2 3 4 5] 2)) "Two twos")
)

(with-test 
  (defn dice-map [dices] "Transaltes the dice into a map with rollcount"
  (reduce (partial merge-with +) (map #(hash-map % 1) dices))
  )
  (is (= {1 2, 2 1, 4 1, 6 1} (dice-map [1 1 2 4 6])))
)

(with-test
  (defn one-pair [dices]
    (let [pairs (filter #(= (second %) 2) (dice-map dices))]
    (if (empty? pairs) 0 (* 2 (reduce max (map #(first %) pairs))))
    )) 
    (is (= 0 (one-pair [6 2 3 4 5])) "No pairs")
    (is (= 4 (one-pair [2 2 3 4 5])) "One pair")
    (is (= 12 (one-pair [2 2 6 6 5])) "Highest pair")
)