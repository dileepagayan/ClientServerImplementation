#!/bin/bash
PORT=$1
QueueSize=$2
nThreads=$3
waitTime=$4

java -jar ./out/artifacts/CustomThreadPool_jar/CustomThreadPool.jar ${PORT} ${QueueSize} ${nThreads} ${waitTime}


