PACKAGE=com.suicide.everandom
packpath=com\\suicide\\everandom
NAME=everandom
Main=MainActivity
NDK=d:\android-ndk-r16-beta1
SDK=d:\Android\android-sdk
PLATFORM=$(SDK)\platforms\android-28
BUILD_TOOLS=$(SDK)\build-tools\28.0.3
JAVA_HOME=d:\Java\jdk1.8.0_131
source=1.8
target=1.8
keystore=keystore
alias=keystore
password=password
JNIDIR=jni
JNI_SRC=$(wildcard ./$(JNIDIR)/%.c)

start: install
	adb shell am start -n $(PACKAGE)/.$(Main)

init:
	set NDK=$(NDK)
	set ARM_TOOLCHAIN=$(NDK)\toolchains\arm-linux-androideabi-4.9\prebuilt\windows-x86_64\bin\arm-linux-androideabi-gcc.exe
	set X86_TOOLCHAIN=$(NDK)\toolchains\x86_64-4.9\prebuilt\windows-x86_64\bin\x86_64-linux-android-gcc.exe
	if not exist "bin\armeabi-v7a" mkdir bin\armeabi-v7a
	if not exist "bin\x86" mkdir bin\x86

aapt: gen\$(packpath)\R.java
gen\$(packpath)\R.java: AndroidManifest.xml
	if not exist "gen\$(packpath)" mkdir gen\$(packpath)
	${BUILD_TOOLS}/aapt package -f -m -J gen/ -S res -M AndroidManifest.xml -I "${PLATFORM}/android.jar"

compile: bin/classes
bin/classes: gen\$(packpath)\R.java
	if not exist "bin\classes" mkdir bin\classes
	javac -source $(source) -target $(target) -bootclasspath "${JAVA_HOME}/jre/lib/rt.jar" -classpath "${PLATFORM}/android.jar" -d bin/classes gen/$(packpath)/R.java src/$(packpath)/$(Main).java

dex: bin/classes.dex
bin/classes.dex: bin/classes
	${BUILD_TOOLS}/dx --dex --output=bin/classes.dex bin/classes/

pack: bin/$(NAME).unsigned.apk
bin/$(NAME).unsigned.apk: bin/classes.dex
	if exist "bin\*.apk" del bin\*.apk
	${BUILD_TOOLS}/aapt.exe package -f -M AndroidManifest.xml -S res -I "${PLATFORM}/android.jar" -F bin/$(NAME).unsigned.apk bin

zipalign: bin/$(NAME).unsigned.aligned.apk

bin/$(NAME).unsigned.aligned.apk: bin/$(NAME).unsigned.apk
	"${BUILD_TOOLS}/zipalign.exe" -f -p 4 bin/$(NAME).unsigned.apk bin/$(NAME).unsigned.aligned.apk

$(keystore):
	keytool -genkeypair -keystore $(keystore) -alias $(keystore) -validity 10000 -keyalg RSA -keysize 2048 -storepass $(password) -keypass $(password)

sign: bin/$(NAME).apk
bin/$(NAME).apk: $(keystore) bin/$(NAME).unsigned.aligned.apk
	"${BUILD_TOOLS}/apksigner" sign --ks $(keystore) --ks-key-alias $(alias) --ks-pass pass:$(password) --key-pass pass:$(password) --out bin/$(NAME).apk bin/$(NAME).unsigned.aligned.apk

install: bin/$(NAME).apk
	$('-k': keep the data and cache directories)
	"${SDK}/platform-tools/adb" shell pm uninstall -k $(PACKAGE)
	"${SDK}/platform-tools/adb" install -r $<

clean:
	if exist "gen" rmdir /s /q gen

# JNI
javah:
	$(JAVA_HOME)\bin\javah.exe -classpath "${PLATFORM}/android.jar;bin/classes" -o $(JNIDIR)\$(Main).h $(PACKAGE).$(Main)
	grep -Al $(argv) $(JNIDIR)/$(Main).h

# make src="msg" build
build: init
	"${ARM_TOOLCHAIN}" --sysroot="${NDK}/platforms/android-26/arch-arm" -march=armv7-a -mfpu=vfpv3-d16 -mfloat-abi=softfp -Wl,--fix-cortex-a8 -fPIC -shared -o bin/armeabi-v7a/lib$(src).so $(JNI_SRC)
	"${X86_TOOLCHAIN}" --sysroot="${NDK}/platforms/android-26/arch-x86" -fPIC -shared -o bin/x86/lib$(src).so $(JNI_SRC)
