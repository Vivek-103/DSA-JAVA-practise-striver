import java.util.*;
class q12{
    public static int maxSubarraySumBrute(int[]arr , int n){
        int maxi = Integer.MIN_VALUE;// maximum sum
        for(int i =0;i<n;i++){
            for(int j=i;j<n;j++){
                int sum = 0;
                for (int k =i;k<=j;k++){
                    sum +=arr[k];
                }
                maxi = Math.max(maxi,sum);
            }
        }
        return maxi;
    }

    // BETTER APPROACH
    public static int maxSubarraySumBetter(int []arr , int n){
        int maxi = Integer.MIN_VALUE;
        for (int i =0;i<n;i++){
            int sum =0;
            for(int j = i;j<n;j++){
                sum += arr[j];
                maxi = Math.max(maxi,sum);
            }
        }
        return maxi;
    }

    // OPTIMAL APPROACH(KADANES ALGORITHM)
    public static long maxSubarraySumOptimal(int []arr , int n){
        long maxi = Long.MIN_VALUE;
        long sum = 0;

        for (int i =0;i<n;i++){
            sum +=arr[i];
            if(sum > maxi){
                maxi = sum;
            }
            if (sum < 0){
                sum =0;
            }
            // To consider empty sub array⬇️
            if(maxi < 0){
                maxi =0;
            }
        }
        return maxi;
    }
}


