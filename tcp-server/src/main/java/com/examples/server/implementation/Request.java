package com.examples.server.implementation;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Request implements Runnable {

    protected Socket clientSocket = null;
    protected int waitTime = 0;

    public Request(Socket clientSocket, int WaitTime) {
        this.clientSocket = clientSocket;
        this.waitTime = WaitTime;
    }


    public void run() {
        try {

            try {
                Thread.sleep(waitTime * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            System.out.println("Request started to execute");

            InputStream input = clientSocket.getInputStream();
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader br = new BufferedReader(isr);
            String text = br.readLine();

            System.out.println("##############################Request#################################");
            System.out.println("Message Receved from client   :" + text);
            System.out.println("######################################################################");

            String message =  text + "Excecuted by "  + Thread.currentThread().getName() + " Successfully";


            OutputStream os = clientSocket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(message);
            bw.flush();

            bw.flush();

            this.clientSocket.close();

            System.out.println("Request executed Successfully");

        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
}
