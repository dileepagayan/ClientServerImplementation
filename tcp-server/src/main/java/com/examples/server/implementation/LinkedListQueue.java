package com.examples.server.implementation;

import java.util.LinkedList;
import java.util.Queue;

public class LinkedListQueue {

    private Queue<Runnable> queue = new LinkedList<Runnable>();

    private int EMPTY = 0;
    private int MAX_TASK_IN_QUEUE = -1;

    public LinkedListQueue(int size) {
        this.MAX_TASK_IN_QUEUE = size;
    }

    public synchronized void enqueue(Runnable task)
            throws InterruptedException {
        while (this.queue.size() == this.MAX_TASK_IN_QUEUE) {
            wait();
        }
        if (this.queue.size() == EMPTY) {
            notifyAll();
        }
        this.queue.offer(task);
    }


    public synchronized Runnable dequeue()
            throws InterruptedException {
        while (this.queue.size() == EMPTY) {
            wait();
        }
        if (this.queue.size() == this.MAX_TASK_IN_QUEUE) {
            notifyAll();
        }
        return this.queue.poll();
    }


}
