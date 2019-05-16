include d:\default.mk

Name=bookshelf

build\libs\$(Name).war:
	gradlew clean bootWar

watch: stop build\libs\$(Name).war 
	copy /y build\libs\$(Name).war $(CATALINA_HOME)\webapps
	if exist "$(CATALINA_HOME)\webapps\$(Name).war" start "" $(CATALINA_HOME)\bin\catalina.bat run

run:
	start "" gradlew clean bootRun

stop:
	$(CATALINA_HOME)\bin\catalina.bat stop

rm:
	if exist "$(CATALINA_HOME)\webapps\$(Name).war" del $(CATALINA_HOME)\webapps\$(Name).war

.PHONY: etc 
etc:
ifeq ($(OS), Windows_NT)
	xcopy /y /e .\etc \etc
	xcopy /y /e .\etc d:\etc
else
	rsync -ruhv ./etc/cas /etc/ 
endif
