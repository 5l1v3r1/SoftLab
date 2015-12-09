package synchronizeddemo;

/**
 * Created by hdhamee on 12/9/15.
 */
public class SharedObject {
    private int visitCount = 0;

    public synchronized void  doVisit(String threadName){
        System.out.println("[" + threadName + "]" + " Incrementing visit count to : " + (++visitCount));
    }
}
