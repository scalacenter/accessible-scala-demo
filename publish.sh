#!/usr/bin/env bash

rm -rf index.html
rm -rf FiraCode*
rm -rf app.*
cp -R ~/accessible-scala/web/target/scala-2.12/scalajs-bundler/main/out/accessible-scala-web/* .