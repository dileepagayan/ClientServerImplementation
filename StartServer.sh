#!/bin/bash
PORT=$1
QueueSize=$2
nThreads=$3
waitTime=$4

java -jar ./tcp-server/target/tcp-server-1.0.jar ${PORT} ${QueueSize} ${nThreads} ${waitTime}


