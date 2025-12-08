import java.util.*; // Import Scanner and Arrays utilities

public class HouseRobber2 {

    // =====================================================
    // HELPER FUNCTION FOR NORMAL (LINEAR) HOUSE ROBBER
    // =====================================================

    // Brute Force for linear houses
    static int bruteLinear(int index, int[] arr) {

        // If index is negative, no house left to rob
        if (index < 0) {
            return 0;
        }

        // If only one house is available, rob it
        if (index == 0) {
            return arr[0];
        }

        // Rob current house and skip adjacent one
        int pick = arr[index] + bruteLinear(index - 2, arr);

        // Skip current house
        int notPick = bruteLinear(index - 1, arr);

        // Return the maximum of both choices
        return Math.max(pick, notPick);
    }

    // =====================================================
    // 1. BRUTE FORCE FOR CIRCULAR HOUSES
    // =====================================================

    static int bruteForce(int[] arr) {

        // If only one house exists, rob it
        if (arr.length == 1) {
            return arr[0];
        }

        // Create subarray excluding the first house
        int[] arr1 = Arrays.copyOfRange(arr, 1, arr.length);

        // Create subarray excluding the last house
        int[] arr2 = Arrays.copyOfRange(arr, 0, arr.length - 1);

        // Compute result for both cases and return maximum
        return Math.max(
                bruteLinear(arr1.length - 1, arr1),
                bruteLinear(arr2.length - 1, arr2)
        );
    }

    /*
     Time Complexity: O(2^N)
     Space Complexity: O(N)
    */

    // =====================================================
    // 2. MEMOIZATION (TOP-DOWN DP)
    // =====================================================

    static int memoLinear(int index, int[] arr, int[] dp) {

        // If already computed, return stored value
        if (dp[index] != -1) {
            return dp[index];
        }

        // If only one house is left
        if (index == 0) {
            return arr[0];
        }

        // If index becomes negative
        if (index < 0) {
            return 0;
        }

        // Rob current house
        int pick = arr[index] + memoLinear(index - 2, arr, dp);

        // Skip current house
        int notPick = memoLinear(index - 1, arr, dp);

        // Store the result in dp array
        dp[index] = Math.max(pick, notPick);

        // Return stored result
        return dp[index];
    }

    static int memoization(int[] arr) {

        // If only one house
        if (arr.length == 1) {
            return arr[0];
        }

        // Create array excluding first house
        int[] arr1 = Arrays.copyOfRange(arr, 1, arr.length);

        // Create array excluding last house
        int[] arr2 = Arrays.copyOfRange(arr, 0, arr.length - 1);

        // Create dp arrays
        int[] dp1 = new int[arr1.length];
        int[] dp2 = new int[arr2.length];

        // Fill dp arrays with -1
        Arrays.fill(dp1, -1);
        Arrays.fill(dp2, -1);

        // Return max of both cases
        return Math.max(
                memoLinear(arr1.length - 1, arr1, dp1),
                memoLinear(arr2.length - 1, arr2, dp2)
        );
    }

    /*
     Time Complexity: O(N)
     Space Complexity: O(N)
    */

    // =====================================================
    // 3. TABULATION (BOTTOM-UP DP)
    // =====================================================

    static int tabulationLinear(int[] arr) {

        // If array length is 0
        if (arr.length == 0) return 0;

        // dp[i] stores maximum amount till i-th house
        int[] dp = new int[arr.length];

        // Base case
        dp[0] = arr[0];

        // Fill dp array
        for (int i = 1; i < arr.length; i++) {

            // Pick current house
            int pick = arr[i];

            // If possible, add dp[i-2]
            if (i > 1) {
                pick += dp[i - 2];
            }

            // Skip current house
            int notPick = dp[i - 1];

            // Store maximum
            dp[i] = Math.max(pick, notPick);
        }

        // Return last dp value
        return dp[arr.length - 1];
    }

    static int tabulation(int[] arr) {

        // If only one element
        if (arr.length == 1) {
            return arr[0];
        }

        // Excluding first house
        int[] arr1 = Arrays.copyOfRange(arr, 1, arr.length);

        // Excluding last house
        int[] arr2 = Arrays.copyOfRange(arr, 0, arr.length - 1);

        // Return maximum of both cases
        return Math.max(tabulationLinear(arr1), tabulationLinear(arr2));
    }

    /*
     Time Complexity: O(N)
     Space Complexity: O(N)
    */

    // =====================================================
    // 4. MOST OPTIMAL (SPACE OPTIMIZED DP)
    // =====================================================

    static int optimalLinear(int[] arr) {

        // If no houses
        if (arr.length == 0) return 0;

        // prev2 -> dp[i-2]
        int prev2 = 0;

        // prev1 -> dp[i-1]
        int prev1 = arr[0];

        // Loop through array
        for (int i = 1; i < arr.length; i++) {

            // Pick current house
            int pick = arr[i];

            // If possible, add prev2
            if (i > 1) {
                pick += prev2;
            }

            // Skip current house
            int notPick = prev1;

            // Current maximum
            int curr = Math.max(pick, notPick);

            // Shift prev2 and prev1
            prev2 = prev1;
            prev1 = curr;
        }

        // Return last stored value
        return prev1;
    }

    static int optimal(int[] arr) {

        // If only one house
        if (arr.length == 1) {
            return arr[0];
        }

        // Create subarray excluding first house
        int[] arr1 = Arrays.copyOfRange(arr, 1, arr.length);

        // Create subarray excluding last house
        int[] arr2 = Arrays.copyOfRange(arr, 0, arr.length - 1);

        // Return best of both
        return Math.max(optimalLinear(arr1), optimalLinear(arr2));
    }

    /*
     Time Complexity: O(N)
     Space Complexity: O(1) ✅
    */

    // =====================================================
    // MAIN METHOD (INPUT & OUTPUT)
    // =====================================================

    public static void main(String[] args) {

        // Create scanner for input
        Scanner sc = new Scanner(System.in);

        // Input number of houses
        System.out.print("Enter number of houses: ");
        int n = sc.nextInt();

        // Declare array for money in houses
        int[] arr = new int[n];

        // Input money values
        System.out.println("Enter money in each house:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Output results
        System.out.println("\nBrute Force Result: " + bruteForce(arr));
        System.out.println("Memoization Result: " + memoization(arr));
        System.out.println("Tabulation Result: " + tabulation(arr));
        System.out.println("Optimal (Space Optimized) Result: " + optimal(arr));

        // Close scanner
        sc.close();
    }
}
