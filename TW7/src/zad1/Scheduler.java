package zad1;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class Scheduler extends Thread {
    private ActivationQueue activationQueue;

    public Scheduler() {
        activationQueue = new ActivationQueue();
    }

    public void enqueue(IMethodRequest request) {
        activationQueue.queue.add(request);
    }

    public void run() {
        while (true) {
            IMethodRequest iMethodRequest = activationQueue.queue.poll();
            if (iMethodRequest != null) {
                if (iMethodRequest.guard()) {
                    iMethodRequest.call();
                } else {
                    activationQueue.queue.add(iMethodRequest);
                }
            }
        }
    }
}
