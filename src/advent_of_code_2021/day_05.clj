(ns advent-of-code-2021.day-05
  (:require [clojure.string :as str]))

(defn slurp-input []
  (->> (slurp "./resources/day_05/input")
       str/split-lines
       (map #(->> %
                  (re-matches #"(.*),(.*) -> (.*),(.*)")
                  (drop 1)
                  (map read-string)
                  (partition 2)))))


(defn bi-range [from to]
  (if (< to from)
    (range from (dec to) -1)
    (range from (inc to))))

(defn path [[[x1 y1] [x2 y2]]]
  (map vector
       (if (= x1 x2)
         (repeat x1)
         (bi-range x1 x2))
       (if (= y1 y2)
         (repeat y1)
         (bi-range y1 y2))))

(defn count-overlaps [input]
  (->> input
       (mapcat path)
       (frequencies)
       (filter #(> (last %) 1))
       (count)))

(->> (slurp-input)
     (filter (fn [[[x1 y1] [x2 y2]]]
               (or (= x1 x2) (= y1 y2))))
     count-overlaps
     (println "Solution part 1:"))

(->> (slurp-input)
     count-overlaps
     (println "Solution part 2:"))
