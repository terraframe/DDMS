#!/bin/bash

set -e

cd ../
docker build -f build/Dockerfile -t ddms-build .
cd build
docker-compose down
docker-compose up

