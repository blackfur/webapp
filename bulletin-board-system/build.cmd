@echo off

@set USERPROFILE=d:
@call %USERPROFILE%\env.bat
@rem @call d:\env.bat

@set port=9080
@set public=%USERPROFILE%\workspace\SuicideGame.com\bulletin-board-system\src\main\webapp
@set dist=%public%

@call %USERPROFILE%\default.cmd %*
