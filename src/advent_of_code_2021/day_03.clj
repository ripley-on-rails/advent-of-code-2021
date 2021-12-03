(ns advent-of-code-2021.day-03
  (:require [clojure.string :as str]))

(defn slurp-input []
  (->> (slurp "./resources/day_03/input.txt")
       str/split-lines))

(->> (slurp-input)
     (apply map (comp first last
                      (partial sort-by last)
                      frequencies vector))
     (apply str)
     (#(Integer/parseInt % 2))
     (#(* % (- 4095 %)))
     (println "Solution part 1:"))
