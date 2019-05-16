@echo off
@set JAVA_HOME=g:\Java\jdk1.8.0_201
@set JRE_HOME=g:\Java\jre1.8.0_201
@set CAS_DIR=\etc\cas
@rem Call this script with DNAME and CERT_SUBJ_ALT_NAMES already set to override
@if "%DNAME%" == "" set DNAME=CN=cas.example.org,OU=Example,OU=Org,C=US
@rem List other host names or ip addresses you want in your certificate, may help with host name verification, 
@rem   if client apps make https connection for ticket validation and compare name in cert (include sub. alt. names) 
@rem   to name used to access CAS
@if "%CERT_SUBJ_ALT_NAMES%" == "" set CERT_SUBJ_ALT_NAMES=dns:example.org,dns:localhost,dns:%COMPUTERNAME%,ip:127.0.0.1
@set keytool=g:\Java\jdk1.8.0_201\bin\keytool.exe

@if "%1" == "impcert" call:impcert
@goto :EOF

:impcert
   %keytool% -delete -alias cas -keystore %JAVA_HOME%\jre\lib\security\cacerts -storepass changeit -v
   %keytool% -delete -alias cas -keystore %JRE_HOME%\lib\security\cacerts -storepass changeit -v
   %keytool% -import -noprompt -alias cas -storepass changeit -file %CAS_DIR%\cas.cer -keystore %JAVA_HOME%\jre\lib\security\cacerts
   %keytool% -import -noprompt -alias cas -storepass changeit -file %CAS_DIR%\cas.cer -keystore %JRE_HOME%\lib\security\cacerts
@goto :EOF
