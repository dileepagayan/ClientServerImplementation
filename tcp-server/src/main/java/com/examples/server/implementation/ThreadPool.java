package com.examples.server.implementation;


public class ThreadPool {

    LinkedListQueue queue;

    public ThreadPool(int queueSize, int nThread) {
        queue = new LinkedListQueue(queueSize);

        String threadName = null;
        Executor task = null;

        for (int count = 0; count < nThread; count++) {
            threadName = "Thread-" + count;
            task = new Executor(queue);
            Thread thread = new Thread(task, threadName);
            thread.start();
        }
    }

    public void submitTask(Runnable task) throws InterruptedException {
        queue.enqueue(task);
    }
}

