import java.util.*; // Import utility classes for Scanner and Arrays

public class FrogKJump {

    // ==========================================
    // 1. BRUTE FORCE RECURSION
    // ==========================================

    // Function to find minimum energy using brute force
    static int bruteForce(int index, int[] heights, int k) {

        // Base case: if frog is at the first step, no energy is required
        if (index == 0) {
            return 0;
        }

        // Initialize minimum energy as maximum possible
        int minEnergy = Integer.MAX_VALUE;

        // Try jumping from all steps in range [index-k, index-1]
        for (int j = 1; j <= k; j++) {

            // Check if previous index exists
            if (index - j >= 0) {

                // Energy required for this jump
                int jumpEnergy = bruteForce(index - j, heights, k)
                        + Math.abs(heights[index] - heights[index - j]);

                // Store minimum energy
                minEnergy = Math.min(minEnergy, jumpEnergy);
            }
        }

        // Return minimum energy found
        return minEnergy;
    }

    /*
     Time Complexity: O(K^N)
     Space Complexity: O(N) -> recursion stack
    */

    // ==========================================
    // 2. MEMOIZATION (TOP-DOWN DP)
    // ==========================================

    static int memoization(int index, int[] heights, int k, int[] dp) {

        // If already computed, return value
        if (dp[index] != -1) {
            return dp[index];
        }

        // Base case
        if (index == 0) {
            return 0;
        }

        // Initialize minimum energy
        int minEnergy = Integer.MAX_VALUE;

        // Try all valid jumps
        for (int j = 1; j <= k; j++) {

            // Check if index is valid
            if (index - j >= 0) {

                // Compute energy
                int jumpEnergy = memoization(index - j, heights, k, dp)
                        + Math.abs(heights[index] - heights[index - j]);

                // Update minimum
                minEnergy = Math.min(minEnergy, jumpEnergy);
            }
        }

        // Store result in DP array
        dp[index] = minEnergy;

        // Return stored result
        return dp[index];
    }

    /*
     Time Complexity: O(N * K)
     Space Complexity: O(N) -> dp array + recursion stack
    */

    // ==========================================
    // 3. TABULATION (BOTTOM-UP DP)
    // ==========================================

    static int tabulation(int n, int[] heights, int k) {

        // Create dp array to store minimum energy
        int[] dp = new int[n];

        // Base case initialization
        dp[0] = 0;

        // Loop through all steps
        for (int i = 1; i < n; i++) {

            // Initialize min energy as maximum
            int minEnergy = Integer.MAX_VALUE;

            // Try jumps from previous k steps
            for (int j = 1; j <= k; j++) {

                // Check valid index
                if (i - j >= 0) {

                    // Calculate energy
                    int jumpEnergy = dp[i - j]
                            + Math.abs(heights[i] - heights[i - j]);

                    // Store minimum
                    minEnergy = Math.min(minEnergy, jumpEnergy);
                }
            }

            // Store result for current step
            dp[i] = minEnergy;
        }

        // Return minimum energy to reach last step
        return dp[n - 1];
    }

    /*
     Time Complexity: O(N * K)
     Space Complexity: O(N)
    */

    // ==========================================
    // 4. MOST OPTIMAL (SPACE OPTIMIZED DP)
    // ==========================================

    static int optimal(int n, int[] heights, int k) {

        // Create dp array (only N needed, no recursion)
        int[] dp = new int[n];

        // Base case
        dp[0] = 0;

        // Loop through all steps
        for (int i = 1; i < n; i++) {

            // Initialize minimum energy
            int minEnergy = Integer.MAX_VALUE;

            // Try all k jumps
            for (int j = 1; j <= k; j++) {

                // Valid jump check
                if (i - j >= 0) {

                    // Compute energy
                    int jumpEnergy = dp[i - j]
                            + Math.abs(heights[i] - heights[i - j]);

                    // Update minimum
                    minEnergy = Math.min(minEnergy, jumpEnergy);
                }
            }

            // Store result
            dp[i] = minEnergy;
        }

        // Return last step energy
        return dp[n - 1];
    }

    /*
     Time Complexity: O(N * K)
     Space Complexity: O(N)
     
     NOTE: True O(1) space optimization is not possible here
     because we need the last K previous states.
    */

    // ==========================================
    // MAIN METHOD (INPUT & OUTPUT)
    // ==========================================

    public static void main(String[] args) {

        // Create scanner for user input
        Scanner sc = new Scanner(System.in);

        // Input number of steps
        System.out.print("Enter number of steps: ");
        int n = sc.nextInt();

        // Input jump range k
        System.out.print("Enter value of k: ");
        int k = sc.nextInt();

        // Declare heights array
        int[] heights = new int[n];

        // Input heights of each step
        System.out.println("Enter heights of steps:");
        for (int i = 0; i < n; i++) {
            heights[i] = sc.nextInt();
        }

        // -------- OUTPUT SECTION --------

        // Brute Force Answer
        System.out.println("\nBrute Force Minimum Energy: "
                + bruteForce(n - 1, heights, k));

        // Memoization Answer
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        System.out.println("Memoization Minimum Energy: "
                + memoization(n - 1, heights, k, dp));

        // Tabulation Answer
        System.out.println("Tabulation Minimum Energy: "
                + tabulation(n, heights, k));

        // Optimal Answer
        System.out.println("Optimal Minimum Energy: "
                + optimal(n, heights, k));

        // Close scanner
        sc.close();
    }
}
