import java.util.*;

public class q10 {

    // Brute force approach to find the index of the minimum element
    // which corresponds to the number of times the array has been rotated
    public static int findKRotationBrute(int[] arr) {
        int n = arr.length;
        int ans = Integer.MAX_VALUE;  // Initialize minimum element as maximum integer value
        int index = -1;               // Initialize index of minimum element

        // Traverse the array to find the minimum element and its index
        for (int i = 0; i < n; i++) {
            if (arr[i] < ans) {
                ans = arr[i];        // Update the minimum value
                index = i;           // Update the index of the minimum value
            }
        }
        return index; // The index of the smallest element = number of rotations
    }

    // Optimal approach using binary search to find the index of the minimum element
    public static int findKRotationOptimal(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        int index = -1;
        int ans = Integer.MAX_VALUE;

        // Apply modified binary search on rotated sorted array
        while (low <= high) {
            int mid = low + (high - low) / 2;

            // If the subarray is already sorted
            if (arr[low] <= arr[high]) {
                if (arr[low] < ans) {
                    ans = arr[low];   // Update the minimum value
                    index = low;      // Update the index
                }
                break; // No need to continue, as subarray is sorted
            }

            // If left half is sorted
            if (arr[low] <= arr[mid]) {
                if (arr[low] < ans) {
                    ans = arr[low];   // Update minimum and index
                    index = low;
                }
                low = mid + 1;         // Discard the left half
            } else {
                // Right half is sorted, but pivot is in left half
                if (arr[mid] < ans) {
                    ans = arr[mid];   // Update minimum and index
                    index = mid;
                }
                high = mid - 1;        // Discard the right half
            }
        }
        return index; // Index of the smallest element = number of rotations
    }

    // Main method to test the rotation count functions
    public static void main(String[] args) {
        int[] arr = {15, 18, 2, 3, 6, 12}; // Rotated sorted array

        System.out.println("Brute Force Rotation Count: " + findKRotationBrute(arr));
        System.out.println("Optimal Rotation Count: " + findKRotationOptimal(arr));
    }
}
