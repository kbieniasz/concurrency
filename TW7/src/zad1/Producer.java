package zad1;

import java.util.Random;

public class Producer extends Thread{
    private int id;
    private Proxy proxy;

    public Producer(int id, Proxy proxy){
        this.id = id;
        this.proxy = proxy;

    }

    public void run(){
        while(true){
            Random random = new Random();
            int number = random.nextInt(1000);
            proxy.add(number);
            System.out.println("Producer " + id + " added " + number);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
