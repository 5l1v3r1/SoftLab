package test;

/**
 * @author Hikamt Dhamee
 * @email me.hemant.available@gmail.com
 */
public class LinearRecursion {

    public static int factorial(int n){
        if (n < 0) throw new IllegalArgumentException(String.valueOf(n));
        if (n == 0) return 1;
        return n * factorial(n-1);
    }

    public static boolean binarySearch(int[] data,int low, int high, int n){
        if (low > high)
            return false;

        int mid = (low + high)/2;

        if (data[mid] == n)
            return true;

        if (n < data[mid])
            high = mid-1;
        else
            low = mid +1;

        return binarySearch(data,low,high,n);
    }
}
