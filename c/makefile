CC=gcc
CFLAGS = -Wall -O

all: example

example: example.o wrgen.o
	$(CC) $(CFLAGS) -o example example.o wrgen.o -lm

example.o: example.c wrgen.h
	$(CC) $(CFLAGS) -c example.c wrgen.h

wrgen.o: wrgen.c wrgen.h
	$(CC) $(CFLAGS) -c wrgen.c wrgen.h

clean:
	$(RM) example
	$(RM) wrgen.o
	$(RM) example.o
