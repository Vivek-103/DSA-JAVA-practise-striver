package BinarySearchOn1Darrays;

import java.util.*;

// Class to find the single non-duplicate element in a sorted array
public class q11 {

    // Brute-force method using XOR operation
    public static int singleElementNonDuplicateBrute(ArrayList<Integer> arr) {
        // Get the size of the array
        int n = arr.size();  
        int ans = 0;

        // XOR all elements; duplicates cancel each other and the unique remains
        for (int i = 0; i < n; i++) {
            ans = ans ^ arr.get(i);
        }

        // Return the single element
        return ans;
    }

    // Optimal method using binary search in O(log n)
    public static int singleElementNonDuplicateOptimal(ArrayList<Integer> arr) {
        int n = arr.size();

        // Edge case: only one element
        if (n == 1) return arr.get(0);

        // Check if the first element is the unique one
        if (!arr.get(0).equals(arr.get(1))) return arr.get(0);

        // Check if the last element is the unique one
        if (!arr.get(n - 1).equals(arr.get(n - 2))) return arr.get(n - 1);

        // Binary search: skip first and last index since already checked
        int low = 1;
        int high = n - 2;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Check if mid is the unique element (not equal to neighbors)
            if (!arr.get(mid).equals(arr.get(mid + 1)) &&
                !arr.get(mid).equals(arr.get(mid - 1))) {
                return arr.get(mid);
            }

            // If mid is even and equals next OR mid is odd and equals previous,
            // then unique is on the right half
            if ((mid % 2 == 0 && arr.get(mid).equals(arr.get(mid + 1))) ||
                (mid % 2 == 1 && arr.get(mid).equals(arr.get(mid - 1)))) {
                low = mid + 1;
            }
            // Otherwise, unique is in the left half
            else {
                high = mid - 1;
            }
        }

        // If no unique element found (ideally shouldn't happen with valid input)
        return -1;
    }

    // Main method to test the functions
    public static void main(String[] args) {
        // Create a sample input: each number appears twice except one
        ArrayList<Integer> input = new ArrayList<>(Arrays.asList(1, 1, 2, 2, 3, 4, 4, 5, 5));

        // Call brute-force method
        int resultBrute = singleElementNonDuplicateBrute(input);
        System.out.println("Brute-force result: " + resultBrute); // Output: 3

        // Call optimal binary search method
        int resultOptimal = singleElementNonDuplicateOptimal(input);
        System.out.println("Optimal binary search result: " + resultOptimal); // Output: 3
    }
}
