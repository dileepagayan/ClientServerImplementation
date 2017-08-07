package com.examples.server;


import com.examples.server.implementation.Request;
import com.examples.server.implementation.ThreadPool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


class Server implements Runnable {

    protected int serverPort = 9000;
    protected ServerSocket serverSocket = null;
    protected int queueSize = 10;
    protected int nThread = 5;
    protected int WaitTime = 0;


    public Server(int port, int queueSize, int nThread, int WaitTime) {

        this.serverPort = port;
        this.queueSize = queueSize;
        this.nThread = nThread;
        this.WaitTime = WaitTime;
    }

    public static void main(String args[]) {
        //args[0]   port number
        //args[1]   max queue size
        //args[2]   number of threads
        //args[3]   waiting time in seconds


        int port = (args.length > 0 && args[0] != null) ? Integer.parseInt(args[0]) : 9000;
        int maxQueueSize = (args.length > 1 && args[1] != null) ? Integer.parseInt(args[1]) : 10;
        int nThreads = (args.length > 2 && args[2] != null) ? Integer.parseInt(args[2]) : 5;
        int maxWaitTime = (args.length > 3 && args[3] != null) ? Integer.parseInt(args[3]) : 0;


        Server server = new Server(port, maxQueueSize, nThreads, maxWaitTime);
        new Thread(server).start();

    }

    public void run() {

        ThreadPool threadPool = new ThreadPool(queueSize, nThread);

        openServerSocket();
        while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(
                        "Error accepting client connection", e);
            }

            try {
                threadPool.submitTask(new Request(clientSocket, WaitTime));
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
