@echo off

@set Name=suicidegamepc
@set Version=1.0-SNAPSHOT
@set bin=target\%Name%-%Version%-jar-with-dependencies.jar

@if "%1" == "launch" goto launch
@goto :EOF

:launch
   start "" java -jar %bin%
@goto :EOF
