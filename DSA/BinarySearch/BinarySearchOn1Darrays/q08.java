package BinarySearchOn1Darrays;

// Problem Statement:
// Given a rotated sorted array (which may contain duplicates) and a target value k,
// return true if k exists in the array, otherwise return false.

public class q08 {

    // Brute-force approach: linear search through array
    public static boolean SearchinRotatedArray(int[] arr, int k) {
        int n = arr.length; // Get array length
        for (int i = 0; i < n; i++) {
            if (arr[i] == k) return true; // If target found, return true
        }
        return false; // If loop ends, target not found
    }

    // Optimal approach: modified binary search to handle duplicates
    public static boolean SearchinRotatedArrayOptimal(int[] arr, int k) {
        int n = arr.length;      // Get array length
        int low = 0;             // Start pointer
        int high = n - 1;        // End pointer

        // Continue until the search space is exhausted
        while (low <= high) {
            int mid = low + (high - low) / 2; // Avoid overflow

            if (arr[mid] == k) return true; // If found at mid, return true

            // If low, mid, and high are all equal, we can't decide sorted half -> shrink range
            if (arr[low] == arr[mid] && arr[mid] == arr[high]) {
                low++;
                high--;
                continue; // Skip this iteration after shrinking search space
            }

            // Check if left half is sorted
            if (arr[low] <= arr[mid]) {
                // Check if target lies in left sorted half
                if (arr[low] <= k && k <= arr[mid]) {
                    high = mid - 1; // Discard right half
                } else {
                    low = mid + 1; // Discard left half
                }
            }
            // Otherwise, right half is sorted
            else {
                // Check if target lies in right sorted half
                if (arr[mid] <= k && k <= arr[high]) {
                    low = mid + 1; // Discard left half
                } else {
                    high = mid - 1; // Discard right half
                }
            }
        }

        return false; // If loop ends, target not found
    }

    // Main method to test both approaches
    public static void main(String[] args) {
        // Rotated sorted array with duplicates
        int[] arr = {2, 5, 6, 0, 0, 1, 2};
        int target = 0;

        // Brute-force search
        boolean resultBrute = SearchinRotatedArray(arr, target);
        System.out.println("Brute-force Search: Element " + target + " found? " + resultBrute);

        // Optimal search
        boolean resultOptimal = SearchinRotatedArrayOptimal(arr, target);
        System.out.println("Optimal Binary Search: Element " + target + " found? " + resultOptimal);
    }
}
