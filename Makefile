clean:
	rm -rf !(WebContent)
all: clean
	mv WebContent/* .
	rm -rf WebContent