clean:
	ls | grep -v WebContent | xargs rm
all: clean
	mv WebContent/* .
	rm -rf WebContent