package com.examples.server.implementation;

public class Executor implements Runnable {

    LinkedListQueue queue;

    public Executor(LinkedListQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String name = Thread.currentThread().getName();
                Runnable task = queue.dequeue();
                System.out.println("Task Started by Thread : " + name);
                task.run();
                System.out.println("Task Finished by Thread : " + name);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}