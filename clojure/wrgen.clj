(ns wrgen)


;; Description:
;;     Generate a random integer between a and b (both 
;;     inclusive) that is "weighted" towards one of them on 
;;     average, weight > 0 will produce a random integer on 
;;     average closer to a, weight < 0 closer to b and 
;;     weight = 0 will produce a random integer with uniform 
;;     distribution
;;
;; Inputs:
;;     int a: first integer
;;     int b: second integer
;;     double w: weight (-100 < w < 100 to avoid floating point errors)
;;
;; Outputs:
;;     int weighted_random_number
;;
;; Details:
;;     A random variable with a probability density 
;;     function f(x) proportional to (1/x)^(weight) is 
;;     used to generate a double which is scaled to the 
;;     required range and returned
(defn weighted-random [a b w]
  (if (= a b)
    a
    (let [u (max a b)
          l (min a b)
          grain 0x1000000
          r (rand-int grain)]
      (if (zero? w)
        (int (+ l (Math/floor (mod r (+ (- u l) 1)))))
        (let [d 2
              c 0.5
              w2 (abs w)
              c2 (Math/pow c (- w2))
              d2 (Math/pow d (- w2))
              rs (+ (/ (* r (- c2 d2)) grain) d2)
              result (Math/pow rs (/ -1 w2))
              result-scaled (/ (* (- u l) (- result c)) (- d c))]
          (if (not= (neg? w) (> a b))
            (int (Math/ceil  (- u result-scaled)))
            (int (Math/floor (+ l result-scaled)))))))))
