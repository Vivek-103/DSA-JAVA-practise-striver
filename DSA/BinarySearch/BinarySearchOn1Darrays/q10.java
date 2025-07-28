//Find out how many times the array has been rotated

import java.util.*;
public class q10 {
    public static int findKRotationBrute(int[] arr){
        int n = arr.length;
        int ans = Integer.MAX_VALUE , index = -1;

        for(int i = 0;i<n;i++){
            if(arr[i] < ans){
                ans = arr[i];
                index  = i;
            }
        }
        return index;
    }
    public static int findKRotationOptimal(int[] arr){
        int low = 0;
        int high = arr.length-1;
        int index = -1;
        int ans = Integer.MAX_VALUE;

        while (low<=high){
            int mid = low + (high-low)/2;

            if(arr[low] <=arr[high]){
                if (arr[low] < ans ){
                    index = low;
                    ans = arr[low];
                }
                break;
            }
            if(arr[low] <= arr[mid]){
                if(arr[low] < ans){
                    index = low;
                    ans = arr[low];
                }
                low = mid + 1;
            }else{
                if(arr[mid] < ans){
                    index = mid;
                    ans = arr[mid];
                }
                high = mid-1;
            }
        }
        return index;
    }
}   
