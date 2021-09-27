#!/usr/bin/env bash
echo "building bank-api image......."
docker-compose build
echo "reloading containers"
docker-compose up -d --build bank-api
echo "all done"
