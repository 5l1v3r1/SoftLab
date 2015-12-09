package volatiledemo;

/**
 * Created by hdhamee on 12/9/15.
 */
public class ProducerThread extends Thread {
    Semaphore semaphore = null;

    public ProducerThread(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        int local_value = semaphore.getMY_INT();
        while (semaphore.getMY_INT() < 5) {
            System.out.println("\nIncrementing MY_INT to: " + (local_value+1));
            semaphore.setMY_INT(++local_value);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

