#!/bin/bash

# cd "out/production/ru.ifmo.swpractice.stage2/" || exit
compiled="out/production/stage2/"
pkgs="ru/ifmo/swpractice"

cd "$compiled" || exit

jar --create --file HelloClient.jar --manifest "../../../src/ClientManifest.mf" "$pkgs/client/HelloClient.class"
mv "HelloClient.jar" "../../.."