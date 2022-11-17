import random
import math


'''
Description:
    Generate a random integer between a and b (both 
    inclusive) that is "weighted" towards one of them on 
    average, weight > 0 will produce a random integer on 
    average closer to a, weight < 0 closer to b and 
    weight = 0 will produce a random integer with uniform 
    distribution

Inputs:
    int a: first integer
    int b: second integer
    double w: weight (-100 < w < 100 to avoid floating point errors)

Outputs:
    int weighted_random_number

Details:
    A random variable with a probability density 
    function f(x) proportional to (1/x)^(weight) is 
    used to generate a double which is scaled to the 
    required range and returned
'''
def weighted_random(a, b, w):
    if a == b:
        return a

    u = b if b > a else a
    l = a + b - u

    grain = 0x1000000
    r = random.uniform(0, grain)

    if w == 0:
        return l + math.floor(r % (u - l + 1))

    d = 2
    c = 1/d

    w2 = w if w > 0 else -w
    d2 = d ** (-w2)
    c2 = c ** (-w2)

    rs = r * (c2 - d2) / grain + d2
    result = rs ** (-1 / w2)
    result_scaled = ((result - c) * (u - l)) / (d - c)

    if (w < 0) ^ (a > b):
        return math.ceil(u - result_scaled)

    return math.floor(l + result_scaled)
