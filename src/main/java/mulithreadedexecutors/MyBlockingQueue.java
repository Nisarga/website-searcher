package main.java.mulithreadedexecutors;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * A blocking queue implementation that waits for queue to become non-empty during a dequeue operation and waits for space
 * to be available when adding tasks to the queue
 *
 * @param <Runnable>
 */
public class MyBlockingQueue<Runnable> {

    private Queue<Runnable> queue = new ConcurrentLinkedDeque<>();
    private int EMPTY = 0;
    private int NUM_TASKS_IN_QUEUE = -1;

    public MyBlockingQueue(int size){
        this.NUM_TASKS_IN_QUEUE = size;
    }

    public synchronized void enqueue(Runnable task) throws InterruptedException  {
        while(this.queue.size() == this.NUM_TASKS_IN_QUEUE) {
            wait();
        }
        if(this.queue.size() == EMPTY) {
            notifyAll();
        }
        this.queue.offer(task);
    }

    public synchronized Runnable dequeue() throws InterruptedException{
        while(this.queue.size() == EMPTY){
            wait();
        }
        if(this.queue.size() == this.NUM_TASKS_IN_QUEUE){
            notifyAll();
        }
        return this.queue.poll();
    }
}
