package com.examples.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

class TCPClient implements Runnable {

    private int port = 9000;

    public TCPClient(int port) {
        this.port = port;
    }

    public static void main(String args[]) throws Exception {

        String threadName = null;
        TCPClient client = null;

        int nThread = (args.length > 1) ? Integer.parseInt(args[1]) : 10;
        int port = (args.length > 0) ? Integer.parseInt(args[0]) : 9000;


        for (int count = 0; count < nThread; count++) {
            threadName = "Client Thread-" + count;
            client = new TCPClient(port);
            Thread thread = new Thread(client, threadName);
            thread.start();
            System.out.println(threadName + "Started Successfully");
        }
    }

    @Override
    public void run() {

        String sentence;
        String modifiedSentence = null;
        Socket clientSocket = null;
        try {
            clientSocket = new Socket("localhost", port);
            DataOutputStream outToServer = null;
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = null;
            InputStreamReader in = new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8);
            inFromServer = new BufferedReader(in);
            sentence = "Client carries the number :" + String.valueOf(Math.random());
            modifiedSentence = inFromServer.readLine();
            outToServer.writeBytes(sentence + '\n');
            System.out.println("FROM SERVER: " + modifiedSentence);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
