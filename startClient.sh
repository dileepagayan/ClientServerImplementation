#!/bin/bash
nClients=$1
PORT =$2

javac ./src/com/wso2/customThreadPool/SimpleClient.java


cd src
java com.wso2.customThreadPool.runClient ${nClients}


