package test;

/**
 * @author Hikamt Dhamee
 * @email me.hemant.available@gmail.com
 */
public class BinaryRecursion {

    public static int binarySum(int[] data, int low, int high){
        if(low > high)
            return 0;

        if ( low == high)
            return data[low];

        int mid = (high+low)/2;

        return
                binarySum(data,low,mid) +
                        binarySum(data,mid + 1,high);
    }

}

