package zad1;


public class Servant implements Proxy {
    Buffer buffer;
    Scheduler scheduler;

    public Servant(Buffer buffer, Scheduler scheduler){
        this.buffer = buffer;
        this.scheduler = scheduler;
    }


    public void add(Object object){
        scheduler.enqueue(new AddRequest(buffer, object));
    }

    public FutureVariable remove(){
        FutureVariable futureVariable = new FutureVariable();
        scheduler.enqueue(new RemoveRequest(buffer, futureVariable));
        return futureVariable;
    }
}
