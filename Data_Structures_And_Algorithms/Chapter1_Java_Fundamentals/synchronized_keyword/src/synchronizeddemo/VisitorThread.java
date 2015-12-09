package synchronizeddemo;

/**
 * Created by hdhamee on 12/9/15.
 */
public class VisitorThread extends Thread {
    SharedObject sharedObject = null;
    String threadName = "";
    int localCounter = 0;

    public VisitorThread(SharedObject sharedObject, String threadName) {
        this.sharedObject = sharedObject;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        while (localCounter < 5){
            sharedObject.doVisit(threadName);
            ++localCounter;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
