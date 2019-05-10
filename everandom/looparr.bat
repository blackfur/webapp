@echo off
setlocal enableDelayedExpansion
set arr=gen libs obj
for %%i in (%arr%) do echo %%i && echo hello
endlocal
