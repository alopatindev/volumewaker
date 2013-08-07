#!/bin/bash

set -e

./clear.sh
rm -rf libs obj
cd jni
ndk-build V=1
