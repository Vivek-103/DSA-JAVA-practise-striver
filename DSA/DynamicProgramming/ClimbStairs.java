import java.util.*; // Import utility package for Scanner and Arrays

public class ClimbStairs {

    // ============================
    // 1. BRUTE FORCE (RECURSION)
    // ============================
    
    // Function to calculate ways using pure recursion
    static int bruteForce(int n) {
        // If n is 0 or 1, there is only one way to stand or take one step
        if (n == 0 || n == 1) {
            return 1; // Only one way
        }

        // Count ways by taking 1 step + ways by taking 2 steps
        return bruteForce(n - 1) + bruteForce(n - 2);
    }

    /*
     Time Complexity: O(2^N)
     Space Complexity: O(N) -> recursion stack
    */

    // ==================================
    // 2. OPTIMIZED USING MEMOIZATION (Top-Down DP)
    // ==================================
    
    // Function with memo array to store previously computed results
    static int memoization(int n, int[] dp) {
        // Base cases
        if (n == 0 || n == 1) {
            return 1;
        }

        // If already solved, return stored value
        if (dp[n] != -1) {
            return dp[n];
        }

        // Store and return result
        dp[n] = memoization(n - 1, dp) + memoization(n - 2, dp);
        return dp[n];
    }

    /*
     Time Complexity: O(N)
     Space Complexity: O(N) -> recursion stack + dp array
    */

    // ==================================
    // 3. TABULATION (Bottom-Up DP)
    // ==================================

    static int tabulation(int n) {
        // If n is 0 or 1, only one way
        if (n == 0 || n == 1) {
            return 1;
        }

        // Create DP array to store results
        int[] dp = new int[n + 1];

        // Base cases initialization
        dp[0] = 1;
        dp[1] = 1;

        // Build the DP table iteratively
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // Final answer at nth index
        return dp[n];
    }

    /*
     Time Complexity: O(N)
     Space Complexity: O(N)
    */

    // ==================================
    // 4. MOST OPTIMAL (Space Optimized DP)
    // ==================================

    static int optimal(int n) {
        // If n is 0 or 1, only one way
        if (n == 0 || n == 1) {
            return 1;
        }

        // Previous two values
        int prev1 = 1; // ways for stair 0
        int prev2 = 1; // ways for stair 1

        // Loop from 2 to n
        for (int i = 2; i <= n; i++) {
            int curr = prev1 + prev2; // current ways
            prev1 = prev2;           // shift
            prev2 = curr;            // update
        }

        // Final result
        return prev2;
    }

    /*
     Time Complexity: O(N)
     Space Complexity: O(1)
    */

    // ==================================
    // MAIN METHOD (Input & Output)
    // ==================================

    public static void main(String[] args) {
        // Create scanner object for user input
        Scanner sc = new Scanner(System.in);

        // Ask user for input
        System.out.print("Enter number of stairs: ");
        int n = sc.nextInt(); // Read input

        // BRUTE FORCE OUTPUT
        System.out.println("\nBrute Force Result: " + bruteForce(n));

        // MEMOIZATION OUTPUT
        int[] dp = new int[n + 1];           // Create dp array
        Arrays.fill(dp, -1);                // Initialize with -1
        System.out.println("Memoization Result: " + memoization(n, dp));

        // TABULATION OUTPUT
        System.out.println("Tabulation Result: " + tabulation(n));

        // OPTIMAL OUTPUT
        System.out.println("Optimal (Space Optimized) Result: " + optimal(n));

        // Close scanner
        sc.close();
    }
}
