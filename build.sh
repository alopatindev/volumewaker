#!/bin/bash

set -e

./clear.sh
rm -rf libs obj
cd jni
ndk-build V=1
adb shell rm /data/local/tmp/volumewaker
adb push ../libs/armeabi/volumewaker /data/local/tmp/volumewaker
adb shell /data/local/tmp/volumewaker
