package volatiledemo;

/**
 * Semaphore
 * ----------
 * In computer science, a semaphore is a shared variable or abstract data type that is used for controlling access,
 * by multiple processes, to a common resource in a concurrent system such as a multiprogramming operating system.
 * It may be useful in producer-consumer scenario.
 * Consumer checks the semaphore if producer has produced anything to consume
 *
 * Volatile is not enough
 * ----------------------
 * The situation where multiple threads are incrementing the same counter is exactly
 * such a situation where a volatile variable is not enough, synchronization is needed
 *
 * Volatile is enough
 * --------------------
 * In case only one thread reads and writes the value of a volatile variable and other threads only
 * read the variable, then the reading threads are guaranteed to see the latest value written to the
 * volatile variable. Without making the variable volatile, this would not be guaranteed.
 *
 */
public class Driver {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore();

        Thread consumerT = new ConsumerThread(semaphore);
        Thread producerT = new ProducerThread(semaphore);

        consumerT.start();
        producerT.start();
    }
}
