#!/bin/bash

# cd "out/production/stage2/" || exit
compiled="out/production/stage2"

cd "$compiled" || exit

jar --create --file HelloService.jar --manifest "../../../src/ServiceManifest.mf" "stage2/Hello.class" "stage2/HelloImpl.class" "stage2/HelloService.class"
mv "HelloService.jar" "../../../"