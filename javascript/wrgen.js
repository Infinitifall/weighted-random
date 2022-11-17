
/*
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
*/
function weighted_random(a, b, w) {
    if (a == b) {
        return a;
    }

    let u = b > a ? b : a;
    let l = a + b - u;

    let grain = 0x10000000;
    let r = Math.random() * grain;

    if (w == 0.0) {
        return l + r % (u - l + 1);
    }
    
    let d = 2;
    let c = 0.5;
    
    let w2 = w < 0 ? -w : w;
    let c2 = Math.pow(c, -w2);
    let d2 = Math.pow(d, -w2);

    let rs = (r * (c2 - d2)) / grain + d2;
    let result = Math.pow(rs, -1 / w2);
    let result_scaled = ((result - c) * (u - l)) / (d - c);

    if ((w < 0) ^ (a > b)) {
        return Math.ceil(u - result_scaled);
    }
    
    return Math.floor(l + result_scaled);
}
