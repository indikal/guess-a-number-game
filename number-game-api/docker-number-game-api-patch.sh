#!/usr/bin/env bash
echo "building number-game-api image......."
docker-compose build
echo "reloading containers"
docker-compose up -d --build number-game-api
echo "all done"
