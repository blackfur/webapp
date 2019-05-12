Name=bookshelf
CATALINA_HOME=g:\apache-tomcat-9.0.17

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
