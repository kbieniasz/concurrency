package zad1;


public class RemoveRequest implements IMethodRequest {
    private Buffer buffer;
    private FutureVariable futureVariable;

    public RemoveRequest(Buffer buffer, FutureVariable futureVariable){
        this.buffer = buffer;
        this.futureVariable = futureVariable;
    }

    @Override
    public void call() {
        futureVariable.setObject(buffer.remove());
    }

    @Override
    public boolean guard() {
        return buffer.isPossibleToTake();
    }
}
