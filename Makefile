clean:
	ls | grep -v WebContent | xargs rm -rfv
all: clean
	mv WebContent/* .
	rm -rfv WebContent