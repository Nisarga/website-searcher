package main.java;

import main.java.mulithreadedexecutors.MyBlockingQueue;

public class WebsiteSearcherTask implements Runnable {
    MyBlockingQueue<Runnable> queue;

    public WebsiteSearcherTask(MyBlockingQueue queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String name = Thread.currentThread().getName();
                Runnable task = queue.dequeue();
                System.out.println("Task Started by Thread :" + name);
                task.run();
                System.out.println("Task Finished by Thread :" + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
