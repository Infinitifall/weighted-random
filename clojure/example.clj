(ns example
  (:require [wrgen]
            [clojure.string]))

(println (clojure.string/join ", " (repeatedly 100 (partial wrgen/weighted-random 0 1000 2.5))))
