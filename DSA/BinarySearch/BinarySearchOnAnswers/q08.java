// AGGRESSIVE COWS PROBLEM
// Two approaches included:
// 1) Brute force over all possible distances from 1 to (max-min).
// 2) Optimal binary search on the largest minimum distance.

import java.util.*;

public class q8 {

    // Helper: Check if we can place 'cows' cows in stalls with at least 'dist' distance apart.
    public static boolean CanWePlace(int[] stalls, int dist, int cows) {
        int n = stalls.length; // Number of stalls.
        int cntCows = 1; // Place the first cow at the first stall.
        int last = stalls[0]; // Position of last placed cow.

        for (int i = 1; i < n; i++) { // Try placing remaining cows.
            if (stalls[i] - last >= dist) { // If current stall is at least 'dist' away...
                cntCows++; // Place a cow here.
                last = stalls[i]; // Update last placed position.
            }
            if (cntCows >= cows) return true; // Successfully placed all cows.
        }
        return false; // Couldn't place all cows with this distance.
    }

    // Brute force: Try every possible minimum distance from 1 to (max-min).
    public static int aggressiveCowsBrute(int[] stalls, int k) {
        int n = stalls.length;
        Arrays.sort(stalls); // Sort stalls to ensure linear placement.

        int limit = stalls[n - 1] - stalls[0]; // Maximum possible distance.
        for (int i = 1; i <= limit; i++) { // Try each distance in increasing order.
            if (CanWePlace(stalls, i, k) == false) { // If this distance fails...
                return (i - 1); // Last valid distance was (i-1).
            }
        }
        return limit; // If all worked, return maximum possible distance.
    }

    // Optimal: Binary search on the answer (largest minimum distance).
    public static int aggressiveCowsOptimal(int[] stalls, int k) {
        int n = stalls.length;
        Arrays.sort(stalls); // Sort stalls.

        int low = 1; // Minimum possible distance.
        int high = stalls[n - 1] - stalls[0]; // Maximum possible distance.

        // Binary search to maximize minimum distance.
        while (low <= high) {
            int mid = (low + high) / 2; // Candidate distance.
            if (CanWePlace(stalls, mid, k) == true) {
                low = mid + 1; // Try larger distance.
            } else {
                high = mid - 1; // Try smaller distance.
            }
        }
        return high; // High will store the largest feasible distance.
    }

    // Main method to test both approaches
    public static void main(String[] args) {
        int[] stalls = {1, 2, 8, 4, 9};
        int cows = 3;

        // Brute force result
        int bruteResult = aggressiveCowsBrute(stalls, cows);
        System.out.println("Brute Force Result: " + bruteResult);

        // Optimal result
        int optimalResult = aggressiveCowsOptimal(stalls, cows);
        System.out.println("Optimal Result: " + optimalResult);
    }
}

/*
-----------------------------------
Time & Space Complexity
-----------------------------------

CanWePlace(int[] stalls, int dist, int cows):
    • Time: O(n), where n = number of stalls (one pass to try placement).
    • Space: O(1).

aggressiveCowsBrute(int[] stalls, int k):
    • Sorting: O(n log n).
    • Checking each distance from 1 to (max - min): O((max-min) * n).
    • Total: O(n log n + (max-min) * n) → very slow for large stall gaps.
    • Space: O(1).

aggressiveCowsOptimal(int[] stalls, int k):
    • Sorting: O(n log n).
    • Binary search on distance: O(log(max-min)).
    • Each check takes O(n).
    • Total: O(n log n + n log(max-min)) → efficient.
    • Space: O(1).
-----------------------------------
*/
