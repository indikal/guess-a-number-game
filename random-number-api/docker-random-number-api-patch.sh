#!/usr/bin/env bash
echo "building random-number-api image......."
docker-compose build
echo "reloading containers"
docker-compose up -d --build random-number-api
echo "all done"
