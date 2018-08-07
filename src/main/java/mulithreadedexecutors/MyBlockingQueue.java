package main.java.mulithreadedexecutors;

import java.util.LinkedList;
import java.util.Queue;

public class MyBlockingQueue<Type> {
    private Queue<Type> queue = new LinkedList<>();
    private int EMPTY = 0;
    private int MAX_TASK_IN_QUEUE = -1;

    public MyBlockingQueue(int size){
        this.MAX_TASK_IN_QUEUE = size;
    }

    public synchronized void enqueue(Type task)
            throws InterruptedException  {
        while(this.queue.size() == this.MAX_TASK_IN_QUEUE) {
            wait();
        }
        if(this.queue.size() == EMPTY) {
            notifyAll();
        }
        this.queue.offer(task);
    }

    public synchronized Type dequeue()
            throws InterruptedException{
        while(this.queue.size() == EMPTY){
            wait();
        }
        if(this.queue.size() == this.MAX_TASK_IN_QUEUE){
            notifyAll();
        }
        return this.queue.poll();
    }
}
