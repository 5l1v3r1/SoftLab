package volatiledemo;

/**
 * Created by hdhamee on 12/9/15.
 */
public class Semaphore {
    private volatile  int MY_INT = 0;

    public  int getMY_INT() {
        return MY_INT;
    }

    public  void setMY_INT(int MY_INT) {
        this.MY_INT = MY_INT;
    }
}
