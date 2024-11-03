import sys
import wrgen

def example_simple():
    if len(sys.argv) != 4:
        print('Need 3 arguments!')
        return -1

    a = int(sys.argv[1])
    b = int(sys.argv[2])
    w = float(sys.argv[3])

    for i in range(50):
        r = wrgen.weighted_random(a, b, w)
        print(r, end=', ')
    print('')

    return 0

if __name__ == '__main__':
    example_simple()
