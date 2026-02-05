#!/bin/bash

COMMIT=`git rev-parse HEAD`
echo $COMMIT
echo 'backup git and storage'
mv .git /tmp
mv storage /tmp
mv node_modules /tmp
docker build --build-arg COMMIT_HASH=$COMMIT  -f Dockerfile.local -t yuezhuo:$1   .
echo 'restore git and storage'
mv /tmp/.git .
mv /tmp/storage .
mv /tmp/node_modules .
echo 'build done'