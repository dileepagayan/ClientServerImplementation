package com.wso2.customThreadPool;
import java.io.*;
import java.net.*;

class SimpleClient implements Runnable {


    @Override
    public void run()  {

        String sentence;
        String modifiedSentence = null;
        Socket clientSocket = null;
        try {
            clientSocket = new Socket("localhost", 9000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataOutputStream outToServer = null;
        try {
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader inFromServer = null;
        try {
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sentence = "Client carries the number :" + String.valueOf(Math.random());
        try {
            outToServer.writeBytes(sentence + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            modifiedSentence = inFromServer.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("FROM SERVER: " + modifiedSentence);

        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}


class runClient {

    public static void main(String args[]) throws Exception {

            String threadName = null ;
            SimpleClient client = null;
            int nThread = Integer.valueOf(args[0]);


            for (int count = 0; count < nThread; count++) {
                threadName = "Client Thread-"+count;
                client = new SimpleClient();
                Thread thread = new Thread(client, threadName);
                thread.start();
                System.out.println(threadName + "Started Successfully");
            }
        }

}

