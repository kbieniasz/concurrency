package zad1;


public class ActiveObject {
    private Buffer buffer;
    private Scheduler scheduler;
    private Proxy proxy;

    public ActiveObject(int queueSize){
        buffer = new Buffer(queueSize);
        scheduler = new Scheduler();
        proxy = new Servant(buffer, scheduler);
        scheduler.start();
    }

    public Proxy getProxy(){
        return this.proxy;
    }
}
