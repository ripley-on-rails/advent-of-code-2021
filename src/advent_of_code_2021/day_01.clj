(ns advent-of-code-2021.day-01
  (:require [clojure.string :as str]))

(defn slurp-input []
  (->> (slurp "./resources/day_01/sample.txt")
       str/split-lines
       (map read-string)))

(defn count-larger-than-previous [v]
  (->> (map -
            (drop 1 v)
            (drop-last v))
       (filter pos?)
       count))

(defn solution-part-one [samples]
  (count-larger-than-previous samples))

(defn solution-part-two [samples]
  (count-larger-than-previous (->> samples
                                   (partition 3 1)
                                   (map (partial apply +)))))

(let [samples (slurp-input)]
  (println "Solution for part one:" (solution-part-one samples))
  (println "Solution for part two:" (solution-part-two samples)))
