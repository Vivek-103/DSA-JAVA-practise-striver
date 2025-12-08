import java.util.*; // Import Scanner and Arrays utilities

public class MaxNonAdjSum {

    // ==========================================
    // 1. BRUTE FORCE RECURSION
    // ==========================================

    // Function to find maximum sum using brute force
    static int bruteForce(int index, int[] arr) {

        // Base case: if index is less than 0, no elements can be picked
        if (index < 0) {
            return 0;
        }

        // Base case: if only one element is left, pick it
        if (index == 0) {
            return arr[0];
        }

        // Pick the current element and skip adjacent element
        int pick = arr[index] + bruteForce(index - 2, arr);

        // Do not pick the current element
        int notPick = bruteForce(index - 1, arr);

        // Return the maximum of pick and not pick
        return Math.max(pick, notPick);
    }

    /*
     Time Complexity: O(2^N)
     Space Complexity: O(N) -> recursion stack
    */

    // ==========================================
    // 2. MEMOIZATION (TOP-DOWN DP)
    // ==========================================

    // Function with memoization
    static int memoization(int index, int[] arr, int[] dp) {

        // If already computed, return stored value
        if (dp[index] != -1) {
            return dp[index];
        }

        // Base case: only one element
        if (index == 0) {
            return arr[0];
        }

        // Base case: no elements
        if (index < 0) {
            return 0;
        }

        // Pick current element
        int pick = arr[index] + memoization(index - 2, arr, dp);

        // Do not pick current element
        int notPick = memoization(index - 1, arr, dp);

        // Store the result in dp array
        dp[index] = Math.max(pick, notPick);

        // Return stored result
        return dp[index];
    }

    /*
     Time Complexity: O(N)
     Space Complexity: O(N) -> dp array + recursion stack
    */

    // ==========================================
    // 3. TABULATION (BOTTOM-UP DP)
    // ==========================================

    static int tabulation(int n, int[] arr) {

        // Create dp array to store results
        int[] dp = new int[n];

        // Base case initialization
        dp[0] = arr[0];

        // Loop through the array
        for (int i = 1; i < n; i++) {

            // Pick current element
            int pick = arr[i];

            // If two steps back exists, add its value
            if (i > 1) {
                pick += dp[i - 2];
            }

            // Do not pick current element
            int notPick = dp[i - 1];

            // Store maximum of both options
            dp[i] = Math.max(pick, notPick);
        }

        // Return result at last index
        return dp[n - 1];
    }

    /*
     Time Complexity: O(N)
     Space Complexity: O(N)
    */

    // ==========================================
    // 4. MOST OPTIMAL (SPACE OPTIMIZED DP)
    // ==========================================

    static int optimal(int n, int[] arr) {

        // Variable for dp[i-2]
        int prev2 = 0;

        // Variable for dp[i-1]
        int prev1 = arr[0];

        // Loop through elements
        for (int i = 1; i < n; i++) {

            // Pick current element
            int pick = arr[i];

            // If possible, add dp[i-2]
            if (i > 1) {
                pick += prev2;
            }

            // Do not pick current element
            int notPick = prev1;

            // Calculate current result
            int curr = Math.max(pick, notPick);

            // Shift prev2 to prev1
            prev2 = prev1;

            // Move prev1 to current
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

        // Create scanner for input
        Scanner sc = new Scanner(System.in);

        // Ask for array size
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        // Declare input array
        int[] arr = new int[n];

        // Input array elements
        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // -------- OUTPUT SECTION --------

        // Brute Force Result
        System.out.println("\nBrute Force Result: "
                + bruteForce(n - 1, arr));

        // Memoization Result
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        System.out.println("Memoization Result: "
                + memoization(n - 1, arr, dp));

        // Tabulation Result
        System.out.println("Tabulation Result: "
                + tabulation(n, arr));

        // Optimal Result
        System.out.println("Optimal (Space Optimized) Result: "
                + optimal(n, arr));

        // Close scanner
        sc.close();
    }
}
