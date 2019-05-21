@rem call %USERPROFILE%\default.cmd %*
@echo off

call d:\env.bat

@set port=9080
@set public=d:\workspace\SuicideGame.com\bulletin-board-system\src\main\webapp
@set dist=%public%

call d:\default.cmd %*
