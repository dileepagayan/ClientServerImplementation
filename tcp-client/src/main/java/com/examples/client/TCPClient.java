package com.examples.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.io.IOException;

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
            //Send the message to the server
            OutputStream os = clientSocket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            String clientMessage = "Message " + Math.random() ;

            String sendMessage = clientMessage + "\n";
            bw.write(sendMessage);
            bw.flush();
            System.out.println("Message sent to the server : "+sendMessage);

            //Get the return message from the server
            InputStream is = clientSocket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String message = br.readLine();

            System.out.println("FROM SERVER: " + message);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
