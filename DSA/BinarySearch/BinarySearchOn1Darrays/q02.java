package BinarySearchOn1Darrays;
// UPPERBOUND IMPLEMENTATION
import java.util.*;

public class q02 {

    // Brute force method to find the upper bound
    public static int upperBoundBrute(int[] arr, int x, int n) {
        // Traverse the array from the beginning
        for (int i = 0; i < n; i++) {
            // If current element is greater than x, return the index
            if (arr[i] > x) {
                return i;
            }
        }
        // If no element is greater than x, return n (not found case)
        return n;
    }

    // Optimal method using Binary Search to find the upper bound
    public static int upperBoundOptimal(int[] arr, int x, int n) {
        int low = 0;        // Start index of search space
        int high = n - 1;   // End index of search space
        int ans = n;        // Default answer if upper bound is not found in array

        // Binary search loop
        while (low <= high) {
            // Prevents integer overflow
            int mid = low + (high - low) / 2;

            // If mid element is greater than x, it could be a potential upper bound
            if (arr[mid] > x) {
                ans = mid;       // Store this index as a potential answer
                high = mid - 1;  // Move left to find an even smaller valid index
            } else {
                low = mid + 1;   // Move right as current mid is not greater than x
            }
        }

        // Return the final index found
        return ans;
    }

    // Main method to test both approaches
    public static void main(String[] args) {
        // Sample sorted array (required for binary search)
        int[] arr = {1, 2, 4, 4, 5, 7, 10};
        int n = arr.length;

        // Target value for which we want to find the upper bound
        int x = 4;

        // Call and print result from brute force method
        int bruteResult = upperBoundBrute(arr, x, n);
        System.out.println("Upper Bound (Brute Force): Index = " + bruteResult);

        // Call and print result from binary search method
        int optimalResult = upperBoundOptimal(arr, x, n);
        System.out.println("Upper Bound (Binary Search): Index = " + optimalResult);
    }
}
