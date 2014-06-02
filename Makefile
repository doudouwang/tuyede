clean:
	rm -rfv cfg
	rm -rfv database
	rm -rfv Makefile
	rm -rfv README.md
	rm -rfv resin-abc.ds.xml
	rm -rfv src
	rm -rf test
all: clean
	mv WebContent/* .
	rm -rfv WebContent