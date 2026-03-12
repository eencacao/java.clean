SRCDIR  = src
BINDIR  = bin
SOURCES = $(shell find $(SRCDIR) -name "*.java")

all:
	mkdir -p $(BINDIR)
	javac -sourcepath $(SRCDIR) -d $(BINDIR) $(SOURCES)

run: all
	java -cp $(BINDIR) adapters.Main

clean:
	rm -rf $(BINDIR)
