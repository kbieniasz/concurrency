package zad1;


public class FutureVariable {
    private Object object;
    public void setObject(Object object){
        this.object = object;
    }
    public Object getObject(){
        return object;
    }
    public boolean isReady(){
        return object != null;
    }
}
