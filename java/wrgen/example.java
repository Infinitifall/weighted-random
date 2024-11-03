package wrgen;

import wrgen.wrgen;

public class example {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Need 3 arguments!");
            return;
        }

        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        double w = Double.parseDouble(args[2]);

        for (int i = 0; i < 50; i++) {
            int r = wrgen.weighted_random(a, b, w);
            System.out.print(r + ", ");
        }
        System.out.println("");
    }
}
