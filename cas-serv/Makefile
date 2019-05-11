JAVA_HOME=g:\\Java\\jdk-12
resources=src\\main\resources
cas=$(resources)\\etc\\cas
pass=changeit
keystore=thekeystore

# if exist $(cas)\$(keystore) del $(cas)\$(keystore)
#	if exist $(cas)\$(keystore).crt del $(cas)\$(keystore).crt
#	keytool -genkey -keyalg RSA -alias $(keystore) -keystore $(cas)\\$(keystore) -storepass $(pass) -validity 360 -keysize 2048
#	keytool -import -alias $(keystore) -storepass $(pass) -file $(cas)\\$(keystore).crt -keystore "$(JAVA_HOME)\jre\lib\security\cacerts"
conf:
	if exist "$(resources)\etc" rmdir /s /q $(resources)\etc
	mkdir $(resources)\etc
	xcopy /e etc $(resources)\etc

clean:
	if exist "$(resources)\etc" rmdir /s /q $(resources)\etc
	if exist "etc\cas\$(keystore)" del etc\cas\$(keystore)
	gradlew clean
