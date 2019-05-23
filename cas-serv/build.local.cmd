@echo off

@set CATALINA_HOME=d:\apache-tomcat-9.0.19
@set JRE_HOME=d:\jdk-12.0.1
@set JAVA_HOME=d:\jdk-12.0.1
@set Name=cas

@if "%1" == "tomcat" goto tomcat
@goto :EOF

:tomcat
	if exist "%CATALINA_HOME%\webapps\%Name%.war" start "" %CATALINA_HOME%\bin\catalina.bat run
@goto :EOF
