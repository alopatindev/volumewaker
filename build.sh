#!/bin/bash

#android create project --package com.sbar.volumewaker --activity MainActivity --name volumewaker --path . --target "android-15"
android update project --name volumewaker --path . --target "android-15"
ant debug
#adb install -r bin
