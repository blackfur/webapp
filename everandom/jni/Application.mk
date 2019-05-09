# https://developer.android.com/ndk/guides/abis
#APP_ABI:=all
#APP_ABI:=arm64-v8a
#APP_ABI:=x86
#APP_ABI:=x86_64
#APP_ABI:=x86 armeabi-v7a
APP_ABI:=armeabi-v7a
#APP_ABI:=armeabi
APP_PLATFORM:=android-23
#APP_PLATFORM:=latest
# use of the static version of the standard library runtime.
APP_STL:=stlport_static
APP_BUILD_SCRIPT:=jni\Android.mk
