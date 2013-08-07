#!/bin/bash

set -e

adb shell rm /system/bin/volumewaker
adb push ../libs/armeabi/volumewaker /system/bin/volumewaker
#adb shell /system/bin/volumewaker
