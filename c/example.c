#include <stdlib.h>
#include <stdio.h>
#include "wrgen.h"


int main(int argc, char const *argv[]) {
    if (argc != 4) {
        printf("Need 3 arguments!\n");
        return -1;
    }
    
    char *p;
    int a = strtol(argv[1], &p, 10);
    int b = strtol(argv[2], &p, 10);
    double w = strtod(argv[3], &p);

    for (int i = 0; i < 50; i ++) {
        int r = weighted_random(a, b, w);
        printf("%d, ", r);
    }
    printf("\n");

    return 0;
}
