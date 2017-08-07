#!/bin/bash
nClients=$2
PORT =$1


java -jar ./tcp-client/target/tcp-client-1.0.jar ${PORT} ${nClients}


