# Generate random List view for evernote

android list targets
android create project --target android-28 --name everandom --path . --activity MainActivity --package com.suicide.everandom
ant help
ant compile
ant debug
ant install

keytool -genkey -alias iwillb5.jks -keyalg RSA -validity 100000 -keysize 2049 -keystore iwillb5.jks -storepass 123456 -keypass 123456

adb install -r bin\*.apk
make start

```
${BUILD_TOOLS}/aapt package -f -m -J gen/ -S res \
      -M AndroidManifest.xml -I "${PLATFORM}/android.jar"

javac -source 1.7 -target 1.7 -bootclasspath "${JAVA_HOME}/jre/lib/rt.jar" \
      -classpath "${PLATFORM}/android.jar" -d build/obj \
      build/gen/net/hanshq/hello/R.java java/net/hanshq/hello/MainActivity.java
```
