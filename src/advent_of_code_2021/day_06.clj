(ns advent-of-code-2021.day-06
  (:require [clojure.string :as str]))


(defn slurp-input []
  (->> (str/split (slurp "./resources/day_06/input") #",")
       (map read-string)
       (frequencies)
       ((fn [f]
          (map #(or (f %) 0) (range 9))))))

(defn next-step [[f & _ :as input]]
  (update (->> input
               cycle
               (drop 1)
               (take 9)
               vec)
          6
          (partial + f)))

(defn all-steps [input]
  (lazy-seq (cons input (all-steps (next-step input)))))

(defn alive-in-generation [n]
  (-> (slurp-input)
      (all-steps)
      (nth n)
      ((partial apply +))))

(println "Solution part 1:" (alive-in-generation 80))
(println "Solution part 2:" (alive-in-generation 256))
