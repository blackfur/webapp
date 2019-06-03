# initialized to the current directory
LOCAL_PATH := $(call my-dir)

# CLEAR_VARS clears existing local variables that might have been set from previous builds or more complex builds that have multiple makefiles.
include $(CLEAR_VARS)

#LOCAL_LDLIBS := -llog

src=msg
LOCAL_MODULE    := $(src)
#LOCAL_C_INCLUDES :=
LOCAL_SRC_FILES := $(src).c

include $(BUILD_SHARED_LIBRARY)
# include $(BUILD_EXECUTABLE)
