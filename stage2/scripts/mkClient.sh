#!/bin/bash

# cd "out/production/stage2/" || exit
compiled="out/production/stage2"

cd "$compiled" || exit

jar --create --file HelloClient.jar --manifest "../../../src/ClientManifest.mf" "client/HelloClient.class"
mv "HelloClient.jar" "../../../"