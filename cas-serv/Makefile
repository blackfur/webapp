.PHONY: etc 
etc:
ifeq ($(OS), Windows_NT)
	xcopy /y /e .\etc\cas \etc\cas
else
	rsync -ruhv ./etc/cas /etc/ 
endif

start: etc
	./build.sh clean run

cert:
ifeq ($(OS), Windows_NT)
	rmdir /s /q \etc\cas
	.\build.cmd gencert
else
	rm -rf /etc/cas
	./build.sh gencert
endif

prepare: cert etc
