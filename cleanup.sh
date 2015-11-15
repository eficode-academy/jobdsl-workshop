#!/usr/bin/env sh

set -e

#docker-compose -p sandbox stop
#docker-compose -p sandbox rm -f

docker rm -f jslave jmaster
docker rmi jslave jmaster

