all: \
	wallofhistorythesaga.swf

clean:
	rm -rf wallofhistorythesaga.swf
	rm -rf icons/2001.swf
	rm -rf icons/2002.swf
	rm -rf icons/2003.swf

wallofhistorythesaga.swf:
	png2swf -o icons/2001.swf icons/2001.png
	png2swf -o icons/2002.swf icons/2002.png
	png2swf -o icons/2003.swf icons/2003.png
	swfc wallofhistorythesaga.sc
	rm icons/2001.swf
	rm icons/2002.swf
	rm icons/2003.swf
