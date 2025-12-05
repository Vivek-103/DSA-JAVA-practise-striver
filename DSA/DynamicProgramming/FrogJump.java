import java.util.*; // Import utility package for Scanner and Arrays

public class FrogJump {

    // ==========================================
    // 1. BRUTE FORCE RECURSION
    // ==========================================

    // Function to calculate minimum energy using brute force
    static int bruteForce(int index, int[] height) {

        // If frog is at the starting stair (0), no energy is needed
        if (index == 0) {
            return 0;
        }

        // Energy required if frog jumps from (index - 1) to index
        int left = bruteForce(index - 1, height)
                + Math.abs(height[index] - height[index - 1]);

        // Initialize right jump energy as maximum value
        int right = Integer.MAX_VALUE;

        // Check if 2-step jump is possible
        if (index > 1) {
            right = bruteForce(index - 2, height)
                    + Math.abs(height[index] - height[index - 2]);
        }

        // Return minimum of 1-step and 2-step energy
        return Math.min(left, right);
    }

    /*
     Time Complexity: O(2^N)
     Space Complexity: O(N) -> recursion stack
    */

    // ==========================================
    // 2. MEMOIZATION (TOP-DOWN DP)
    // ==========================================

    // Function using dp array to store intermediate results
    static int memoization(int index, int[] height, int[] dp) {

        // If already computed, return stored value
        if (dp[index] != -1) {
            return dp[index];
        }

        // If at starting stair, no energy needed
        if (index == 0) {
            return 0;
        }

        // Compute energy for 1-step jump
        int left = memoization(index - 1, height, dp)
                + Math.abs(height[index] - height[index - 1]);

        // Initialize 2-step jump energy
        int right = Integer.MAX_VALUE;

        // If 2-step jump is possible, compute energy
        if (index > 1) {
            right = memoization(index - 2, height, dp)
                    + Math.abs(height[index] - height[index - 2]);
        }

        // Store and return the minimum energy
        dp[index] = Math.min(left, right);
        return dp[index];
    }

    /*
     Time Complexity: O(N)
     Space Complexity: O(N) -> dp array + recursion stack
    */

    // ==========================================
    // 3. TABULATION (BOTTOM-UP DP)
    // ==========================================

    static int tabulation(int n, int[] height) {

        // Create dp array of size n
        int[] dp = new int[n];

        // Base case: no energy needed at first stair
        dp[0] = 0;

        // Loop from second stair to last stair
        for (int i = 1; i < n; i++) {

            // Energy for jumping from previous stair
            int oneStep = dp[i - 1]
                    + Math.abs(height[i] - height[i - 1]);

            // Initialize two-step jump as maximum
            int twoStep = Integer.MAX_VALUE;

            // If 2-step jump is possible
            if (i > 1) {
                twoStep = dp[i - 2]
                        + Math.abs(height[i] - height[i - 2]);
            }

            // Store minimum energy at dp[i]
            dp[i] = Math.min(oneStep, twoStep);
        }

        // Return energy required to reach last stair
        return dp[n - 1];
    }

    /*
     Time Complexity: O(N)
     Space Complexity: O(N)
    */

    // ==========================================
    // 4. MOST OPTIMAL (SPACE OPTIMIZED DP)
    // ==========================================

    static int optimal(int n, int[] height) {

        // Variable to store previous stair energy
        int prev1 = 0; // dp[i-1]

        // Variable to store second previous stair energy
        int prev2 = 0; // dp[i-2]

        // Loop from second stair to last stair
        for (int i = 1; i < n; i++) {

            // Energy from previous stair
            int oneStep = prev1
                    + Math.abs(height[i] - height[i - 1]);

            // Initialize two-step energy
            int twoStep = Integer.MAX_VALUE;

            // If 2-step jump is possible
            if (i > 1) {
                twoStep = prev2
                        + Math.abs(height[i] - height[i - 2]);
            }

            // Current minimum energy
            int curr = Math.min(oneStep, twoStep);

            // Update prev2 to prev1
            prev2 = prev1;

            // Update prev1 to current
            prev1 = curr;
        }

        // Final answer stored in prev1
        return prev1;
    }

    /*
     Time Complexity: O(N)
     Space Complexity: O(1) ✅
    */

    // ==========================================
    // MAIN METHOD (INPUT & OUTPUT)
    // ==========================================

    public static void main(String[] args) {

        // Create Scanner object for input
        Scanner sc = new Scanner(System.in);

        // Ask for number of stairs
        System.out.print("Enter number of stairs: ");
        int n = sc.nextInt();

        // Create height array
        int[] height = new int[n];

        // Take height values as input
        System.out.println("Enter height of each stair:");
        for (int i = 0; i < n; i++) {
            height[i] = sc.nextInt();
        }

        // ----------- OUTPUTS ------------

        // Brute Force Output
        System.out.println("\nBrute Force Minimum Energy: "
                + bruteForce(n - 1, height));

        // Memoization Output
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        System.out.println("Memoization Minimum Energy: "
                + memoization(n - 1, height, dp));

        // Tabulation Output
        System.out.println("Tabulation Minimum Energy: "
                + tabulation(n, height));

        // Optimal Output
        System.out.println("Optimal (Space Optimized) Minimum Energy: "
                + optimal(n, height));

        // Close scanner
        sc.close();
    }
}
