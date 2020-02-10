package zad1;


import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] argv) throws InterruptedException {
        ActiveObject activeObject = new ActiveObject(10);
        List<Producer> producers = new ArrayList<>();
        List<Consumer> consumers = new ArrayList<>();

        Proxy proxy = activeObject.getProxy();

        for (int i = 0; i < 5; i++)  producers.add(new Producer(i, proxy));

        for (int i = 0; i < 5; i++) consumers.add(new Consumer(i, proxy));

        for(Consumer consumer : consumers) consumer.start();
        for(Producer producer : producers) producer.start();

        sleep(2000);
        return;
    }
}
