(ns lychrels.core
  (:require [clojure.contrib.str-utils2 :as su])
  (:gen-class))

(defn reverse-int
  "Returns an integer with its digits reversed, e.g. 123 returns 321"
  [x]
  (BigInteger. (su/reverse (pr-str x))))

(defn palindromic?
  "Returns true if the number is palindromic, e.g. 121, 1234321"
  [x]
  (= x (reverse-int x)))

(defn lychrel?
  ([x] (lychrel? x 50))
  ([x iterations]
     (let [next-num #(+ % (reverse-int %))]
       ((comp not
              #(some palindromic? %)
              (partial take iterations)
              rest
              #(iterate next-num %)) x))))

(defn count-lychrels-to-10000
  "Counts the number of lychrels below 10000"
  []
  (count (filter lychrel? (range 1 10000))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main
  []
  (time (println (count-lychrels-to-10000))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
