package test;

import java.io.File;

/**
 * Recursion
 * ---------
 * - Recursion is a technique by which a method makes one or more calls to itself during
 *   execution ,or by which a data structure relies upon smaller instance of the very same
 *   type of structure in its representation.
 * - Examples:
 *    - Factorial Calculation
 *    - File System Size Calculation
 *    - Binary Search
 *
 * Types of Recursion
 * ------------------
 * We can organize recursive algorithms by considering the maximum number of recursive calls that
 * may be started from within the body of a single activation.
 *
 * - Linear Recursion: if a recursive call starts at most one other.
 *      Example: Factorial Function,Binary Search
 * - Binary Recursion: if a recursive call starts two other.
 *      Example: Binary Sum
 * - Multiple Recursion: if a recursinve call starts more than two other.
 *     Example: File System
 *
 * Maximum recursion depth in java
 * -------------------------------
 * In java, a recursive or nested method call can go upto 1000 simultaneous active
 * call method calls otherwise StackOverFlowException occurs by default though this limit
 * is configurable via command line as -Xss parameter
 *
 * Eliminating Tail recursion
 * --------------------------
 * A recursion is tail recursion if any recursive call that is made from one context is the very
 * last operation in that context with return value of recursive call immediately returned by the
 * enclosing recursion. So tail recursion must be a linear recursion but n*factorial(n-1) can not be tail.
 *
 */
public class Main {

    public static void main(String[] args) {
        // Factorial linear recursion
        int a = 16;
        int fact = LinearRecursion.factorial(a);
        System.out.printf("\nFactorial of %d is: %d", a, fact);
        System.out.println("\n");

        // binary search linear recursion
        int[] data = {0,1,2,3,4,5,6,7,8,9};
        int n = 9;
        int low = 0;
        int high = data.length - 1;
        boolean result = LinearRecursion.binarySearch(data,low,high,n);
        if (result)
            System.out.println("Search element exists in list");
        else
            System.out.println("Search element doensn't exiit in list");

        // Binary sum binary recursion
        int[] data1 = {0,1,2,3,4,5,6,7,8,9};
        int low1 = 0;
        int high1 = data1.length - 1;
        int sum = BinaryRecursion.binarySum(data, low1, high1);
        System.out.println("\nSum is: " + sum);

        // Multiple recursion, disk usage
        File root = new File(".");
        long diskUsage = MultipleRecursion.diskUsage(root);
        System.out.println("\nDisk Usage of folder " + root.getAbsolutePath() + ": " + diskUsage/1024/1024 + " MB" );
    }
}
