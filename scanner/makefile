CC = clang++
FLAGS = -g

all: scanner

scanner: scanner.o
	$(CC) $< -o $@

%.o: %.cc
	$(CC) $(FLAGS) -c $< -o $@
