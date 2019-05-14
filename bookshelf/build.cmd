@echo off

@rem @set keytool=d:\Java\jdk1.8.0_73\bin\keytool.exe
@rem @set JAVA_HOME=d:\Java\jdk1.8.0_73
@rem @set JRE_HOME=d:\Java\jdk1.8.0_73\jre
@rem @set PATH=d:\Java\jdk1.8.0_73\bin;%PATH%
@set CAS_DIR=\etc\cas
@rem Call this script with DNAME and CERT_SUBJ_ALT_NAMES already set to override
@if "%DNAME%" == "" set DNAME=CN=cas.example.org,OU=Example,OU=Org,C=US
@rem List other host names or ip addresses you want in your certificate, may help with host name verification, 
@rem   if client apps make https connection for ticket validation and compare name in cert (include sub. alt. names) 
@rem   to name used to access CAS
@if "%CERT_SUBJ_ALT_NAMES%" == "" set CERT_SUBJ_ALT_NAMES=dns:example.org,dns:localhost,dns:%COMPUTERNAME%,ip:127.0.0.1

@if "%1" == "gencert" call:gencert
@if "%1" == "cert" call:cert
@if "%1" == "impcert" call:impcert
@goto :EOF

:gencert
    where /q keytool
    if ERRORLEVEL 1 (
        @echo Java keytool.exe not found in path. 
        exit /b 1
    ) else (
        if not exist %CAS_DIR% mkdir %CAS_DIR%
        if exist %CAS_DIR%\thekeystore del %CAS_DIR%\thekeystore
        @echo on
        @echo Generating self-signed SSL cert for %DNAME% in %CAS_DIR%\thekeystore
        %keytool% -genkeypair -storetype PKCS12 -alias cas -keyalg RSA -keypass changeit -storepass changeit -keystore %CAS_DIR%\thekeystore -dname %DNAME% -ext SAN=%CERT_SUBJ_ALT_NAMES%
        @echo Exporting cert for use in trust store (used by cas clients)
        %keytool% -exportcert -alias cas -storepass changeit -keystore %CAS_DIR%\thekeystore -file %CAS_DIR%\cas.cer
        %keytool% -delete -alias cas -storepass changeit -v -keystore %JRE_HOME%\lib\security\cacerts
        @rem keytool -import -alias cas -storepass changeit -file %CAS_DIR%\cas.cer -keystore %JRE_HOME%\lib\security\cacerts
        %keytool% -import -noprompt -alias cas -storepass changeit -file %CAS_DIR%\cas.cer -keystore %JRE_HOME%\lib\security\cacerts
        %keytool% -list -v -storetype PKCS12 -storepass changeit -keystore %CAS_DIR%\thekeystore
    )
@goto :EOF

:cert
    where /q keytool
    if ERRORLEVEL 1 (
        @echo Java keytool.exe not found in path. 
        exit /b 1
    ) else (
        if not exist %CAS_DIR% mkdir %CAS_DIR%
        if exist %CAS_DIR%\thekeystore del %CAS_DIR%\thekeystore
        @echo on
        @echo Generating self-signed SSL cert for %DNAME% in %CAS_DIR%\thekeystore
        keytool -genkeypair -storetype PKCS12 -alias cas -keyalg RSA -keypass changeit -storepass changeit -keystore %CAS_DIR%\thekeystore -dname %DNAME% -ext SAN=%CERT_SUBJ_ALT_NAMES%
        @echo Exporting cert for use in trust store (used by cas clients)
        keytool -exportcert -alias cas -storepass changeit -keystore %CAS_DIR%\thekeystore -file %CAS_DIR%\cas.cer
        keytool -delete -alias cas -cacerts -storepass changeit -v
        @rem keytool -import -alias cas -storepass changeit -file %CAS_DIR%\cas.cer -keystore %JRE_HOME%\lib\security\cacerts
        keytool -import -noprompt -storepass changeit -alias cas -file %CAS_DIR%\cas.cer -cacerts
        keytool -list -v -storetype PKCS12 -storepass changeit -keystore %CAS_DIR%\thekeystore
    )
@goto :EOF

:impcert
   d:\jdk-12.0.1\bin\keytool.exe -delete -alias cas -storepass changeit -v -cacerts
   d:\jdk-12.0.1\bin\keytool.exe -import -noprompt -alias cas -storepass changeit -file %CAS_DIR%\cas.cer -cacerts
   d:\Java\jdk1.8.0_131\bin\keytool -delete -alias cas -storepass changeit -v -keystore d:\Java\jdk1.8.0_131\jre\lib\security\cacerts
   d:\Java\jdk1.8.0_131\bin\keytool -noprompt -storepass changeit -import -alias cas -storepass changeit -file %CAS_DIR%\cas.cer -keystore d:\Java\jdk1.8.0_131\jre\lib\security\cacerts
   d:\Java\jdk1.8.0_73\bin\keytool -delete -alias cas -storepass changeit -v -keystore d:\Java\jdk1.8.0_73\jre\lib\security\cacerts
   d:\Java\jdk1.8.0_73\bin\keytool -noprompt -storepass changeit -import -alias cas -storepass changeit -file %CAS_DIR%\cas.cer -keystore d:\Java\jdk1.8.0_73\jre\lib\security\cacerts
@goto :EOF
