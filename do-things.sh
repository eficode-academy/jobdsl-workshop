#!/usr/bin/env sh

set -ex

# We can't use compose utill it supports args in compose/build section
# https://github.com/docker/compose/issues/2163
# so we can pass proxy settings

#docker-compose -p sandbox build
#docker-compose -p sandbox up -d

# because of that we will have to keep calling docker commands
[[ ! -z "$HTTP_PROXY" ]]  && PROXY="$PROXY --build-arg HTTP_PROXY=\"$HTTP_PROXY\""
[[ ! -z "$http_proxy" ]]  && PROXY="--build-arg http_proxy=\"$http_proxy\""
[[ ! -z "$HTTPS_PROXY" ]] && PROXY="$PROXY --build-arg HTTPS_PROXY=\"$HTTPS_PROXY\""
[[ ! -z "$https_proxy" ]] && PROXY="$PROXY --build-arg https_proxy=\"$https_proxy\""
[[ ! -z "$NO_PROXY" ]] && PROXY="$PROXY --build-arg NO_PROXY=\"$NO_PROXY\""
[[ ! -z "$no_proxy" ]] && PROXY="$PROXY --build-arg no_proxy=\"$no_proxy\""

cd $(git rev-parse --show-toplevel)/master
docker build $PROXY -t jmaster .
docker run -d -p 8080:8080 -p 50000:50000 --name jmaster jmaster
cd $(git rev-parse --show-toplevel)/slave
docker build $PROXY -t jslave .
docker run -d --link jmaster:slave --name jslave jslave