.PHONY: etc 
etc:
	rsync -ruhv ./etc/cas /etc/ 

start: etc
	./build.sh clean run

cert:
	rm -rf /etc/cas
	./build.sh gencert
