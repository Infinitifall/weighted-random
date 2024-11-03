# Weighted RNG

Generate a random integer between two integers $[a, b]$ (both inclusive) that has higher probability of being closer to one than the other as defined by a floating-point weight $w$.

- $w = 0$ will produce integers uniformly distributed between $a, b$
- $w > 0$ will produce integers closer to $a$ on average
- $w < 0$ will produce integers closer to $b$ on average
- Swapping $a$ and $b$ is equivalent to negating $w$


## Language implementations

Run the example wrapper which generates random numbers between $0$ and $100$ weighted towards $0$ with a weight of $2.5$

- C
  - `make`
  - `./example 0 100 2.5`
- Python
  - `python example.py 0 100 2.5`
- Java
  - `java wrgen/example.java 0 100 2.5`
- Clojure
  - `clojure -M -i wrgen.clj -i example.clj`
- Javascript
  - Figure it out lol


## How it works

A random variable with a probability density function $p(x) = [\frac{1-w}{2^{1-w} - 0.5^{1-w}}] x ^{-w} \; \forall x \in [0.5, 2], x\neq 1$ is used to generate a number in the range $(0.5, 2)$ which is scaled to integers in $[a, b]$ and returned. This is achieved by hard coding the inverse of its cumulative distribution function. Negative weights are taken as positive and the result is flipped in the end to give consistent behavior. The expected runtime is $O(1)$.
