package main.java.mulithreadedexecutors;

import main.java.WebsiteSearcherTask;

public class MyThreadPoolImplementation {
    MyBlockingQueue<Runnable> blockingQueue;
    public MyThreadPoolImplementation(int size, int maxThreads) throws InterruptedException{
        blockingQueue = new MyBlockingQueue<>(size);
        WebsiteSearcherTask task;
        String threadName;

        for(int i=0; i < maxThreads; i++){
            threadName = "Thread-" + i;
            task = new WebsiteSearcherTask(blockingQueue);
            Thread thread = new Thread(task, threadName);
            thread.start();
        }
    }

    public void submitTask(Runnable task) throws InterruptedException {
        blockingQueue.enqueue(task);
    }
}
