package zad1;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ActivationQueue {
    public Queue<IMethodRequest> queue;

    public ActivationQueue() {
        queue = new ConcurrentLinkedQueue<>();
    }
}
