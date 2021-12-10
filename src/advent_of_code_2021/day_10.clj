(ns advent-of-code-2021.day-10
  (:require [clojure.string :as str]))

(defn slurp-input []
  (->> (slurp "./resources/day_10/input")
       str/split-lines))

(def expecting {\[ \]
                \< \>
                \{ \}
                \( \)})

(def error-scores {\) 3
                   \] 57
                   \} 1197
                   \> 25137})

(def completion-scores {\) 1
                        \] 2
                        \} 3
                        \> 4})

(def opening? (set (keys expecting)))

(def push #(conj %2 %1))

(defn parse [input]
  (reduce (fn [stack next]
            (cond (opening? next)
                  (push next stack)

                  (= (expecting (peek stack)) next)
                  (pop stack)

                  :else
                  (reduced next)))
          '()
          input))

(defn middle [v]
  (nth v (int (/ (count v) 2))))

(defn completion-score [v]
  (reduce (fn [acc e]
            (+ (* 5 acc) e))
          0 v))

(def stack? seq?)

(def error? char?)

(->> (slurp-input)
     (map parse)
     (filter error?)
     (map error-scores)
     (apply +)
     (println "Solution part 1:"))

(->> (slurp-input)
     (map parse)
     (filter stack?)
     (map (comp completion-score
                (partial map
                         (comp completion-scores
                               expecting))))
     sort
     middle
     (println "Solution part 2:"))
