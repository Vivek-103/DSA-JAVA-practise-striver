import java.util.*; 
// Imports utility classes like Arrays, List, Map, etc.
// Time Complexity: O(1)
// Space Complexity: O(1)

/**
 * Class Name: MinCurrencyChange
 * Purpose  : Find minimum number of coins/notes needed to make value V
 * Currency : Indian denominations
 */
public class MinCurrencyChange {

    // Indian currency denominations (sorted in ascending order)
    // Time Complexity: O(1)
    // Space Complexity: O(1)
    static int[] denominations = {1, 2, 5, 10, 20, 50, 100, 500, 1000};

    // ===========================
    // 1️⃣ OPTIMAL APPROACH (GREEDY)
    // ===========================

    /**
     * Greedy method to find minimum coins
     * Always pick the largest denomination possible
     */
    public static int greedyMinCoins(int value) {

        // Variable to store count of coins/notes used
        // Space Complexity: O(1)
        int count = 0;

        // Iterate from largest denomination to smallest
        // Time Complexity: O(N) where N = number of denominations (9)
        for (int i = denominations.length - 1; i >= 0; i--) {

            // While the current denomination can be used
            while (value >= denominations[i]) {

                // Reduce value by denomination
                value -= denominations[i];  
                // Time Complexity: O(1)

                // Increment coin count
                count++;  
                // Space Complexity: O(1)
            }
        }

        // Return minimum coins required
        return count;  
        // Time Complexity: O(1)
    }

    // ==================================================
    // 2️⃣ BETTER APPROACH (DYNAMIC PROGRAMMING)
    // ==================================================

    /**
     * Dynamic Programming approach
     * Works for ALL coin systems (even non-canonical)
     */
    public static int dpMinCoins(int value) {

        // dp[i] will store minimum coins needed for value i
        // Space Complexity: O(V)
        int[] dp = new int[value + 1];

        // Initialize dp array with maximum value
        // Time Complexity: O(V)
        Arrays.fill(dp, Integer.MAX_VALUE);

        // Base case: 0 value requires 0 coins
        dp[0] = 0;  
        // Space Complexity: O(1)

        // Compute dp values bottom-up
        // Time Complexity: O(V * N)
        for (int i = 1; i <= value; i++) {

            // Try all denominations
            for (int coin : denominations) {

                // If coin can be used
                if (coin <= i && dp[i - coin] != Integer.MAX_VALUE) {

                    // Take minimum coins
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                    // Time Complexity: O(1)
                }
            }
        }

        // Return result
        return dp[value];
    }

    // ===================================
    // 3️⃣ BRUTE FORCE (RECURSIVE)
    // ===================================

    /**
     * Brute force recursive solution
     * NOT recommended for large values
     */
    public static int bruteMinCoins(int value) {

        // Base case: if value is 0, no coins needed
        if (value == 0) {
            return 0;
        }

        // Initialize result as maximum
        int minCoins = Integer.MAX_VALUE;

        // Try every denomination
        // Time Complexity: Exponential O(N^V)
        for (int coin : denominations) {

            // If coin can be used
            if (coin <= value) {

                // Recursive call
                int subResult = bruteMinCoins(value - coin);

                // If valid solution exists
                if (subResult != Integer.MAX_VALUE) {

                    // Take minimum
                    minCoins = Math.min(minCoins, subResult + 1);
                }
            }
        }

        return minCoins;
    }

    // ===========================
    // MAIN METHOD
    // ===========================

    public static void main(String[] args) {

        // Input value
        int V = 2758;

        // Calling Greedy Method
        System.out.println("Greedy Approach Result: " + greedyMinCoins(V));
        // Time Complexity: O(N)
        // Space Complexity: O(1)

        // Calling DP Method
        System.out.println("DP Approach Result: " + dpMinCoins(V));
        // Time Complexity: O(V * N)
        // Space Complexity: O(V)

        // Calling Brute Force (small values only)
        System.out.println("Brute Force Result (for small V): " + bruteMinCoins(11));
        // Time Complexity: Exponential
        // Space Complexity: O(V) due to recursion stack
    }
}
