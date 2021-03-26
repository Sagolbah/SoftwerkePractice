#!/bin/bash

# cd "out/production/ru.ifmo.swpractice.stage2/" || exit
compiled="out/production/stage2/"
pkgs="ru/ifmo/swpractice"

cd "$compiled" || exit

jar --create --file HelloService.jar --manifest "../../../src/ServiceManifest.mf" "$pkgs/service/Hello.class" "$pkgs/service/HelloImpl.class" "$pkgs/service/HelloService.class"
mv "HelloService.jar" "../../../"