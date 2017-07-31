package com.wso2.customThreadPool;

import com.wso2.customThreadPool.Impl.CustomThreadPool;
import com.wso2.customThreadPool.Impl.Request;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Server implements Runnable{

    protected int          serverPort   = 9000;
    protected ServerSocket serverSocket = null;
    protected int queueSize = 10 ;
    protected int nThread = 5;


    public Server(int port , int queueSize , int nThread ){

        this.serverPort = port;
        this.queueSize = queueSize;
        this.nThread = nThread;
    }

    public void run(){

        CustomThreadPool threadPool = new CustomThreadPool(queueSize , nThread);

        openServerSocket();
        while(true){
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(
                        "Error accepting client connection", e);
            }

            try {
                threadPool.submitTask(new Request(clientSocket));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }



    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
            System.out.println("Server Started and listning to port" + this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port " + this.serverPort, e);
        }
    }
}