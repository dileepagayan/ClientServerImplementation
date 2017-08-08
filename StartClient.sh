#!/bin/bash

PORT=$1
nClients=$2



java -jar ./tcp-client/target/tcp-client-1.0.jar ${PORT} ${nClients}


