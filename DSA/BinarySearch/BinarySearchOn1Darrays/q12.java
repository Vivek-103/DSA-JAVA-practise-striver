package BinarySearchOn1Darrays;

import java.util.*;

public class q12 {

    // Brute-force approach to find a peak element
    public static int findPeakBrute(ArrayList<Integer> arr) {
        int n = arr.size(); // Get size of the array

        // Traverse each element to check if it is a peak
        for (int i = 0; i < n; i++) {
            // A peak is defined as an element greater than both its neighbors
            // For edge elements, we only compare with one neighbor
            if ((i == 0 || arr.get(i - 1) < arr.get(i)) &&
                (i == n - 1 || arr.get(i) > arr.get(i + 1))) {
                return i; // Return index of the peak element
            }
        }
        return -1; // If no peak is found (theoretically not possible)
    }

    // Optimal approach using Binary Search to find a peak in O(log n)
    public static int findPeakOptimal(ArrayList<Integer> arr) {
        int n = arr.size();

        // Edge case: if only one element, it’s the peak
        if (n == 1) return 0;

        // Check if first element is a peak
        if (arr.get(0) > arr.get(1)) return 0;

        // Check if last element is a peak
        if (arr.get(n - 1) > arr.get(n - 2)) return n - 1;

        // Binary search within the range excluding the first and last element
        int low = 1;
        int high = n - 2;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Check if mid element is greater than both its neighbors
            if (arr.get(mid - 1) < arr.get(mid) && arr.get(mid) > arr.get(mid + 1)) {
                return mid; // Peak found
            }

            // If the left neighbor is smaller, move to the right half
            if (arr.get(mid) > arr.get(mid - 1)) {
                low = mid + 1;
            }
            // Else move to the left half
            else {
                high = mid - 1;
            }
        }

        return -1; // No peak found (ideally shouldn't happen)
    }

    // Main method to test the functions
    public static void main(String[] args) {
        // Sample input: array with multiple peaks (e.g., 1, 3, 20, 4, 1, 0)
        ArrayList<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 20, 4, 1, 0));

        // Find peak using brute-force
        int bruteIndex = findPeakBrute(input);
        System.out.println("Brute-force peak at index: " + bruteIndex + ", value: " + input.get(bruteIndex));

        // Find peak using optimal method
        int optimalIndex = findPeakOptimal(input);
        System.out.println("Optimal peak at index: " + optimalIndex + ", value: " + input.get(optimalIndex));
    }
}
