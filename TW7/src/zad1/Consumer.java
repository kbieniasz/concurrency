package zad1;

import org.w3c.dom.html.HTMLTableColElement;

public class Consumer extends Thread{
    private int id;
    private Proxy proxy;

    public Consumer(int id, Proxy proxy){
        this.id = id;
        this.proxy = proxy;
    }


    public void run(){
        while(true){
            FutureVariable futureVariable = proxy.remove();
            while(!futureVariable.isReady()){
                System.out.println("Consumer " + id + " waits");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Consumer " + id
                    + " ate: " + futureVariable.getObject());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
