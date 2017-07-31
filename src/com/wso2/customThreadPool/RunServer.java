package com.wso2.customThreadPool;

public class RunServer {

    public static void main(String args[]){
        //args[0]   port number
        //args[1]   max queue size
        //args[2]   number of threads

        Server server = new Server(Integer.valueOf(args[0]) , Integer.valueOf(args[1]) , Integer.valueOf(args[2]));
        new Thread(server).start();

    }

}
