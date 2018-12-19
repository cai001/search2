import java.util.ArrayList;

public class StatList {
    ArrayList<Statobj> al;

    StatList(){
        this.al = new ArrayList<Statobj>();
    }
    public Statobj get(int i){
         return al.get(i);
    }
    synchronized public boolean add(Statobj e){
        return al.add(e);
    }//Synchronized method for use by the thread pool is added
    public Statobj remove(int i){
        return al.remove(i);
    }
    public int size(){
        return al.size();
    }
    public boolean isEmpty(){
        return al.isEmpty();
    }
}
