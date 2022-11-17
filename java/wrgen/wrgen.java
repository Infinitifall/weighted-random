package wrgen;

public class wrgen {

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
    public static int weighted_random(int a, int b, double w) {
        if (a == b) {
            return a;
        }
    
        int u = b > a ? b : a;
        int l = a + b - u;
    
        int grain = 0x10000000;
        double r = Math.random() * grain;
    
        if (w == 0.0) {
            return l + (int) Math.floor(r % (u - l + 1));
        }
        
        double d = 2;
        double c = 0.5;
        
        double w2 = w < 0 ? -w : w;
        double c2 = Math.pow(c, -w2);
        double d2 = Math.pow(d, -w2);
    
        double rs = (r * (c2 - d2)) / grain + d2;
        double result = Math.pow(rs, -1 / w2);
        double result_scaled = ((result - c) * (u - l)) / (d - c);
    
        if ((w < 0) ^ (a > b)) {
            return (int) Math.ceil(u - result_scaled);
        }
        
        return (int) Math.floor(l + result_scaled);
    }
}