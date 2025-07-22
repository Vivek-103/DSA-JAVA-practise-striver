import java.util.*;
// LOWER BOUND IMPLEMENTATION
public class q01 {

    // Brute force method to find the lower bound
    public static int lowerBoundBrute(int[] arr, int n, int x) {
        // Traverse the array linearly
        for (int i = 0; i < n; i++) {
            // If the current element is greater than or equal to x, return its index
            if (arr[i] >= x) {
                return i;
            }
        }
        // If no element is ≥ x, return n (index after last element)
        return n;
    }

    // Optimal method using binary search to find the lower bound
    public static int lowerBoundOptimal(int[] arr, int n, int x) {
        int low = 0;          // Start of the search range
        int high = n - 1;     // End of the search range
        int ans = n;          // Default answer is n (in case x is greater than all elements)

        // Continue searching while the search range is valid
        while (low <= high) {
            // Calculate the middle index safely to avoid overflow
            int mid = low + (high - low) / 2;

            // If mid element is greater than or equal to x, it could be a potential answer
            if (arr[mid] >= x) {
                ans = mid;       // Store the index
                high = mid - 1;  // Try to find a smaller index on the left side
            } else {
                low = mid + 1;   // Move right since arr[mid] < x
            }
        }

        // Return the final answer
        return ans;
    }

    // Main method to test both approaches
    public static void main(String[] args) {
        // Sample sorted array
        int[] arr = {1, 2, 4, 4, 5, 7, 10};
        int n = arr.length;

        // Value to search for lower bound
        int x = 4;

        // Call brute force approach
        int bruteResult = lowerBoundBrute(arr, n, x);
        System.out.println("Lower Bound (Brute Force): Index = " + bruteResult);

        // Call optimal binary search approach
        int optimalResult = lowerBoundOptimal(arr, n, x);
        System.out.println("Lower Bound (Binary Search): Index = " + optimalResult);
    }
}
