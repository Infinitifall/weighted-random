# Weighted RNG

Generate a random integer between two numbers $[a, b]$ (both inclusive) that has higher probability of being closer to one than the other as defined by a weight $w$.

- $w = 0$ will produce integers uniformly distributed between $a, b$
- $w > 0$ will produce integers closer to $a$ on average
- $w < 0$ will produce integers closer to $b$ on average
- Swapping $a$ and $b$ is equivalent to negating $w$


## How it works
A random variable with a probability density function $p(x) = [\frac{1-w}{2^{1-w} - 0.5^{1-w}}] x ^{-w} \; \forall x \in [0.5, 2], x\neq 1$ is used to generate a number in the range $(0.5, 2)$ which is scaled to integers in $[a, b]$ and returned. This is achieved by hard coding the inverse of its cumulative distribution function. Negative weights are taken as positive and the result is flipped in the end to give consistent behavior. The expected runtime is $O(1)$.

