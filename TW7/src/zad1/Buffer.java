package zad1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Buffer {
    private int size;
    private List<Object> list;
    public Buffer(int size){
        this.size = size;
        this.list = new ArrayList<>();
    }
    public void add(Object object) {
        if(this.isPossibleToAdd()){
            this.list.add(object);
        }
    }
    public Object remove() {
        if(!this.isPossibleToTake()){
            return null;
        }
        else{
            return list.remove(0);
        }
    }
    public boolean isPossibleToAdd() {
        return !(list.size() == size);
    }
    public boolean isPossibleToTake() {
        return !(list.isEmpty());
    }
}
