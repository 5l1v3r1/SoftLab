package synchronizeddemo;

/**
 * Some of the most common use cases are those in which we have an object and many
 * threads interacting with it. This can create many problems, because in
 * cases that 2 different threads try to interact with the same resource,
 * we have no idea what may actually happen (which is something that needs
 * to be avoided in programming
 *
 * This is why Java provides the synchronized keyword,
 * which allows us to handle certain parts of code in an atomic way
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        SharedObject sharedObject = new SharedObject();

        Thread visitor1T = new VisitorThread(sharedObject,"VisitorOne");
        Thread visitor2T = new VisitorThread(sharedObject,"VisitorTwo");

        visitor1T.start();
        visitor2T.start();
    }
}
