#!/bin/bash

set -e

cd ../
docker build -f build/Dockerfile -t ddms-build .
cd build
#docker-compose down
#docker-compose up

docker run -v /usr/bin/docker:/usr/bin/docker -v /var/run/docker.sock:/var/run/docker.sock -v /home/rich/dev/projects/DDMS/git/build-out:/ddms-out -v /home/rich/dev/projects/DDMS/git/DDMS:/ddms --network=host ddms-build -Dpostgres.port=5442
