ifneq ($(TARGET_SIMULATOR),true)
	LOCAL_PATH:= $(call my-dir)
	include $(CLEAR_VARS)
	LOCAL_CFLAGS += -Wall
	#LOCAL_CFLAGS += -Wall -g
endif

LOCAL_LDLIBS := -L$(LOCAL_PATH)/lib -llog
LOCAL_C_INCLUDES := bionic LOCAL_C_INCLUDES += $(LOCAL_PATH)/include
LOCAL_SRC_FILES:= volumewaker.c
LOCAL_MODULE := volumewaker

# don't remove debug symbols
#cmd-strip :=

include $(BUILD_EXECUTABLE)
