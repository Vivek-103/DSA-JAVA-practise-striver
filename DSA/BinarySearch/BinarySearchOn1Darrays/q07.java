package BinarySearchOn1Darrays;
import java.util.*;

// SEARCH IN ROTATED SORTED ARRAY
public class q07 {

    // Brute-force linear search method
    public static int RotatedSearchBrute(ArrayList<Integer> arr, int n, int k) {
        for (int i = 0; i < n; i++) {
            if (arr.get(i) == k) {
                return i; // Return index if element is found
            }
        }
        return -1; // Return -1 if element not found
    }

    // Optimal binary search method for rotated sorted array
    public static int RotatedSearchOptimal(ArrayList<Integer> arr, int n, int k) {
        int low = 0;         // Initialize low pointer
        int high = n - 1;    // Initialize high pointer

        while (low <= high) {  // Binary search loop
            int mid = low + (high - low) / 2; // Prevents overflow

            if (arr.get(mid) == k) {
                return mid; // If element at mid is the target, return index
            }

            // Check if left half is sorted
            if (arr.get(low) <= arr.get(mid)) {
                // If target lies within the left sorted half
                if (arr.get(low) <= k && k <= arr.get(mid)) {
                    high = mid - 1; // Search in the left half
                } else {
                    low = mid + 1; // Otherwise, search in the right half
                }
            } else {
                // Else, right half must be sorted
                if (arr.get(mid) <= k && k <= arr.get(high)) {
                    low = mid + 1; // If target lies within right sorted half
                } else {
                    high = mid - 1; // Otherwise, search in the left half
                }
            }
        }

        return -1; // Return -1 if element not found
    }

    // Main method to test both brute and optimal search functions
    public static void main(String[] args) {
        // Example rotated sorted array
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(4, 5, 6, 7, 0, 1, 2));
        int n = arr.size();
        int target = 0;

        // Brute-force search
        int bruteIndex = RotatedSearchBrute(arr, n, target);
        System.out.println("Brute-force Search: Element found at index " + bruteIndex);

        // Optimal binary search
        int optimalIndex = RotatedSearchOptimal(arr, n, target);
        System.out.println("Optimal Binary Search: Element found at index " + optimalIndex);
    }
}
