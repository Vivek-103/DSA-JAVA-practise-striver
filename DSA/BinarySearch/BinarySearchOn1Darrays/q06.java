// Count Occurrences in Sorted Array



// LOC 1: Importing necessary packages (none needed for this code)

public class q06 {

    // LOC 2: Brute-force method to count occurrences of x in array
    public static int countOccurenceBrute(int[] arr, int n, int x) {
        int count = 0; // LOC 3: Initialize counter

        // LOC 4: Traverse the array linearly
        for (int i = 0; i < n; i++) {
            if (arr[i] == x) { // LOC 5: If element matches target
                count++; // LOC 6: Increment counter
            }
        }

        return count; // LOC 7: Return total count
    }

    // LOC 8: Optimal method to count occurrences of k using binary search
    public static int countOccurenceOptimal(int[] arr, int n, int k) {
        // LOC 9: Find index of first occurrence
        int first = findFirst(arr, n, k);
        // LOC 10: If not found, return 0
        if (first == -1) return 0;

        // LOC 11: Find index of last occurrence
        int last = findLast(arr, n, k);

        // LOC 12: Count = last - first + 1
        return last - first + 1;
    }

    // LOC 13: Helper method to find first occurrence using binary search
    private static int findFirst(int[] arr, int n, int k) {
        int low = 0, high = n - 1, res = -1;

        while (low <= high) { // LOC 14
            int mid = low + (high - low) / 2; // LOC 15
            if (arr[mid] == k) {
                res = mid; // LOC 16: Save result
                high = mid - 1; // LOC 17: Search in left half
            } else if (arr[mid] < k) {
                low = mid + 1; // LOC 18: Move to right half
            } else {
                high = mid - 1; // LOC 19: Move to left half
            }
        }

        return res; // LOC 20: Return first index or -1
    }

    // LOC 21: Helper method to find last occurrence using binary search
    private static int findLast(int[] arr, int n, int k) {
        int low = 0, high = n - 1, res = -1;

        while (low <= high) { // LOC 22
            int mid = low + (high - low) / 2; // LOC 23
            if (arr[mid] == k) {
                res = mid; // LOC 24: Save result
                low = mid + 1; // LOC 25: Search in right half
            } else if (arr[mid] < k) {
                low = mid + 1; // LOC 26: Move to right half
            } else {
                high = mid - 1; // LOC 27: Move to left half
            }
        }

        return res; // LOC 28: Return last index or -1
    }

    // LOC 29: Main method to test both approaches
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 2, 3, 4, 5, 5, 5, 5, 6}; // LOC 30
        int n = arr.length; // LOC 31
        int target = 5; // LOC 32

        // LOC 33: Brute force result
        int bruteCount = countOccurenceBrute(arr, n, target);
        System.out.println("Brute Force Count of " + target + ": " + bruteCount); // LOC 34

        // LOC 35: Optimal approach result
        int optimalCount = countOccurenceOptimal(arr, n, target);
        System.out.println("Optimal Count of " + target + ": " + optimalCount); // LOC 36
    }
}
