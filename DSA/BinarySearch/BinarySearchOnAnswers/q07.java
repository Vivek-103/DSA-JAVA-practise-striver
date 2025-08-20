// FIND Kth missing Number
public class q7 {

    // ---------------- BRUTE FORCE APPROACH ----------------
    // Iterate over array elements and adjust k
    public static int missingKbrute(int[] arr, int n, int k) {
        // Traverse each element in the array
        for (int i = 0; i < n; i++) {
            // If current element is <= current k,
            // it means this number exists in array → so we must skip one more missing number
            if (arr[i] <= k) k++;
            else break; // If arr[i] > k, then k is not present in array → stop
        }
        return k; // Return the final Kth missing number
    }
    // Time Complexity: O(n)
    // Space Complexity: O(1)


    // ---------------- OPTIMAL BINARY SEARCH APPROACH ----------------
    // Use binary search to find Kth missing number efficiently
    public static int missingKoptimal(int[] arr, int n, int k) {
        int low = 0;         // Left boundary
        int high = n - 1;    // Right boundary

        // Binary search loop
        while (low <= high) {
            int mid = (low + high) / 2; // Mid index

            // Missing numbers count till index mid:
            // arr[mid] - (mid + 1) gives how many numbers are missing up to arr[mid]
            int missing = arr[mid] - (mid + 1);

            if (missing < k) {
                // If missing numbers till mid are less than k,
                // it means the Kth missing number is on the right side
                low = mid + 1;
            } else {
                // Otherwise, it's on the left side
                high = mid - 1;
            }
        }

        // When loop ends:
        // 'high' is the index where missing < k,
        // So answer = k + high + 1
        return k + high + 1;
    }
    // Time Complexity: O(log n)
    // Space Complexity: O(1)


    // ---------------- MAIN METHOD ----------------
    public static void main(String[] args) {
        // Example array (sorted positive integers without duplicates)
        int[] arr = {2, 3, 4, 7, 11};
        int n = arr.length; // Size of array
        int k = 5;          // We want the 5th missing number

        // ---------- Brute Force Test ----------
        int bruteAns = missingKbrute(arr, n, k);
        System.out.println("Brute Force → " + k + "th missing number = " + bruteAns);
        // Explanation:
        // Traverse the array, keep adjusting k whenever an element <= k is found.
        // Once arr[i] > k, stop. Answer is the current k.


        // ---------- Optimal Binary Search Test ----------
        int optimalAns = missingKoptimal(arr, n, k);
        System.out.println("Binary Search → " + k + "th missing number = " + optimalAns);
        // Explanation:
        // Count missing numbers up to mid.
        // If missing < k → go right, else go left.
        // At the end, compute answer using formula (k + high + 1).
    }
}
