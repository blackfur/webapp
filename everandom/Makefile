Drive=g:
PACKAGE=net.suicide.everandom
packpath=net\\suicide\\everandom
NAME=everandom
Main=MainActivity
NDK=$(Drive)\android-ndk-r16-beta1
SDK=$(Drive)\android-sdk-windows
PLATFORM=$(SDK)\platforms\android-21
platool=$(SDK)\platform-tools
tools=$(SDK)\tools
BUILD_TOOLS=$(SDK)\build-tools\21.1.2
JAVA_HOME=$(Drive)\Java\jdk1.8.0_131
source=1.8
target=1.8
keystore=keystore
alias=keystore
password=password
JNIDIR=.\\jni
JNI_SRC=$(wildcard $(JNIDIR)/*.c)
prop=$(wildcard ./%.properties)

# OS detect
ifeq ($(OS),Windows_NT)
    CCFLAGS += -D WIN32
    ifeq ($(PROCESSOR_ARCHITEW6432),AMD64)
        CCFLAGS += -D AMD64
    else
        ifeq ($(PROCESSOR_ARCHITECTURE),AMD64)
            CCFLAGS += -D AMD64
        endif
        ifeq ($(PROCESSOR_ARCHITECTURE),x86)
            CCFLAGS += -D IA32
        endif
    endif
else
    UNAME_S := $(shell uname -s)
    ifeq ($(UNAME_S),Linux)
        CCFLAGS += -D LINUX
    endif
    ifeq ($(UNAME_S),Darwin)
        CCFLAGS += -D OSX
    endif
    UNAME_P := $(shell uname -p)
    ifeq ($(UNAME_P),x86_64)
        CCFLAGS += -D AMD64
    endif
    ifneq ($(filter %86,$(UNAME_P)),)
        CCFLAGS += -D IA32
    endif
    ifneq ($(filter arm%,$(UNAME_P)),)
        CCFLAGS += -D ARM
    endif
endif

#start: install
start:
	$(platool)\adb shell am start -n $(PACKAGE)/.$(Main)

ARM_TOOLCHAIN=$(NDK)\toolchains\arm-linux-androideabi-4.9\prebuilt\windows-x86_64\bin\arm-linux-androideabi-gcc.exe
X86_TOOLCHAIN=$(NDK)\toolchains\x86_64-4.9\prebuilt\windows-x86_64\bin\x86_64-linux-android-gcc.exe

#set NDK_PROJECT_PATH=$(JNIDIR)
#if not exist "bin\armeabi-v7a" mkdir bin\armeabi-v7a
#if not exist "bin\x86" mkdir bin\x86
init:
	set NDK=$(NDK)

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
ifeq ($(OS),Windows_NT)
Separator=;
else
Separator=:
endif
#type  $(JNIDIR)\$(Main).h
#grep -Al $(argv) $(JNIDIR)/$(Main).h
ifeq ($(argv),)
argv=""
endif
javah:
	if exist "$(JNIDIR)\$(Main).h" del $(JNIDIR)\$(Main).h
	$(JAVA_HOME)\bin\javah.exe -classpath "${PLATFORM}/android.jar$(Separator)bin/classes" -o $(JNIDIR)\$(Main).h $(PACKAGE).$(Main)
	findstr $(argv) $(JNIDIR)\$(Main).h

CFLAGS=-g -Wall -Id:\android-ndk-r16-beta1\sysroot\usr\include
arm_incl=-Id:\android-ndk-r16-beta1\sysroot\usr\include\arm-linux-androideabi
x86_incl=-Id:\android-ndk-r16-beta1\sysroot\usr\include\x86_64-linux-android
# make src="msg" build
#build: $(JNI_SRC) init
#	"${ARM_TOOLCHAIN}" $(CFLAGS) $(arm_incl) -I --sysroot="${NDK}/platforms/android-26/arch-arm" -march=armv7-a -mfpu=vfpv3-d16 -mfloat-abi=softfp -Wl,--fix-cortex-a8 -fPIC -shared -o bin/armeabi-v7a/lib$(src).so $(JNI_SRC)
#	"${X86_TOOLCHAIN}" $(CFLAGS) $(x86_incl) --sysroot="${NDK}/platforms/android-26/arch-x86" -fPIC -shared -o bin/x86/lib$(src).so $(JNI_SRC)

build: $(JNI_SRC) init
	$(NDK)\ndk-build NDK_APPLICATION_MK=$(JNIDIR)/Application.mk

#EXE=echo
#$(platool)\adb push libs\x86\$(EXE) /mnt/sdcard/
#$(platool)\adb shell chmod 777 /mnt/sdcard/$(EXE)
#$(platool)\adb shell /mnt/sdcard/$(EXE)

avd:
	$(tools)\android avd

device:
	$(platool)\adb devices
