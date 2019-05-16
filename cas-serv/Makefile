Name=cas
CATALINA_HOME=d:\apache-tomcat-9.0.19

init:
	set CATALINA_HOME=$(CATALINA_HOME)

.PHONY: etc 
etc:
ifeq ($(OS), Windows_NT)
	xcopy /y /e .\etc \etc
	xcopy /y /e .\etc d:\etc
else
	rsync -ruhv ./etc/cas /etc/ 
endif

start: etc
	./build.sh clean run

cert:
ifeq ($(OS), Windows_NT)
	if exist \etc\cas rmdir /s /q \etc\cas
	.\build.cmd gencert
else
	rm -rf /etc/cas
	./build.sh gencert
endif

prepare: cert etc

build\libs\$(Name).war:
	.\build.cmd package

pack:
	.\build.cmd package

cp:
	copy /y build\libs\$(Name).war $(CATALINA_HOME)\webapps

hot: stop pack cp tomcat

tomcat:
	if exist "$(CATALINA_HOME)\webapps\$(Name).war" start "" $(CATALINA_HOME)\bin\catalina.bat run

run: init etc clean build\libs\$(Name).war cp tomcat

stop: $(CATALINA_HOME)\webapps\$(Name).war
	$(CATALINA_HOME)\bin\catalina.bat stop
	if exist "$<" del $<

clean:
	.\build.cmd clean
