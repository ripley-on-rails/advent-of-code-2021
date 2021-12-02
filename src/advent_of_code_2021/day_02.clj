(ns advent-of-code-2021.day-02
  (:require [clojure.string :as str]))

(defn slurp-input []
  (->> (slurp "./resources/day_02/input.txt")
       str/split-lines))

(def directions {"forward" [1 0]
                 "up" [0 -1]
                 "down" [0 1]})

(defn mult [v m]
  (map (partial * m) v))

(defn add [v1 v2]
  (map + v1 v2))

(->> (slurp-input)
     (map #(let [[d v] (str/split % #" ")]
             (mult (directions d) (read-string v))))
     (reduce add)
     (reduce *)
     (println "Solution part 1:"))

(->> (slurp-input)
     (map #(let [[d v] (str/split % #" ")]
             (mult (directions d) (read-string v))))
     (reduce (fn [[x y aim] [forward aim-change]]
               [(+ x forward)
                (+ y (* aim forward))
                (+ aim aim-change)])
             [0 0 0])
     drop-last
     (reduce *)
     (println "Solution part 2:"))
