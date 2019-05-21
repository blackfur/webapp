include f:\Users\iwillbe5\default.mk
#include d:\default.mk

Name=bookshelf
public=public

build\libs\$(Name).war:
	gradlew clean bootWar

watch: stop build\libs\$(Name).war 
	copy /y build\libs\$(Name).war $(CATALINA_HOME)\webapps
	if exist "$(CATALINA_HOME)\webapps\$(Name).war" start "" $(CATALINA_HOME)\bin\catalina.bat run

run:
	build.cmd impcert
	$(CMD) /c start "" gradlew clean bootRun

stop:
	$(CATALINA_HOME)\bin\catalina.bat stop

rm:
	if exist "$(CATALINA_HOME)\webapps\$(Name).war" del $(CATALINA_HOME)\webapps\$(Name).war
