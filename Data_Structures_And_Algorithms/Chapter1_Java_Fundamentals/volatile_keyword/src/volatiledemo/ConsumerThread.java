package volatiledemo;

/**
 * Created by hdhamee on 12/9/15.
 */
public class ConsumerThread extends Thread {

    Semaphore semaphore = null;

    public ConsumerThread(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        int local_value = semaphore.getMY_INT();
        while (local_value < 5) {
            if (local_value != semaphore.getMY_INT()) {
                System.out.println("\nGot Change for MY_INT :" + semaphore.getMY_INT());
                local_value = semaphore.getMY_INT();
            }
        }
    }
}
