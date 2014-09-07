#!/bin/bash

ant debug
if [ $? -eq 0 ];
    then adb install -r bin/hipo-debug.apk;
    else  echo "build failed";
fi
