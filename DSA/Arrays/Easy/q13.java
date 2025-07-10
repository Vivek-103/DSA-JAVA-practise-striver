// EXTENSION OF Q12 HERE YOU HAVE TO PRINT THE SUB ARRAY
import java.util.*;
class q13 {
    public static int maxSubarraySum(int []arr, int n){
        int maxi = Integer.MIN_VALUE;
        int sum = 0;

        int start = 0;
        int ansStart = -1;
        int ansEnd = -1;
        for(int i =0;i<n;i++){
            if (sum == 0) start = i;// starting index

            sum +=arr[i];

            if(sum > maxi){
                maxi = sum;

                ansStart = start;
                ansEnd = i;
            }
            if (sum < 0) {
                sum = 0;
            }
        }
        // PRINTING THE SUBARRAY
        System.out.println("The sub array is :");
        for (int i = ansStart ; i<=ansEnd ; i++){
            System.out.println(arr[i] + " ");
        }
        return maxi;
    }
}
