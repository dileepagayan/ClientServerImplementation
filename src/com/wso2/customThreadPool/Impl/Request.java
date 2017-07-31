package com.wso2.customThreadPool.Impl;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Request implements Runnable {

    protected Socket clientSocket = null;

    public Request(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {


            System.out.println("Request started to execute");

            InputStream input  = clientSocket.getInputStream();
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader br = new BufferedReader(isr);
            String text = br.readLine();

            System.out.println("Message Receved from client" + text);
            String message = "Thread ID" + Thread.currentThread().getId() + "Handled Clent requst : " + text + "successfully";

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
