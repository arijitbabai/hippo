#!/bin/bash

ant debug
if [ $? -eq 0 ];
    then adb uninstall com.arijit.pro && adb install bin/hipo-debug.apk;
    else  echo "build failed";
fi
