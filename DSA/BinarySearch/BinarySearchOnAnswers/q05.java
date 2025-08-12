//Find the Smallest Divisor Given a Threshold
public class q5 {

    // -------------------------------
    // Brute Force Approach
    // -------------------------------
    public static int smallestDivisorBrute(int[] arr, int limit) {
        int n = arr.length;

        // Step 1: Find the maximum element in the array
        // Because the divisor cannot be greater than the largest element
        int maxi = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxi = Math.max(maxi, arr[i]);
        }

        // Step 2: Try every possible divisor from 1 to maxi
        for (int d = 1; d <= maxi; d++) {
            int sum = 0;

            // Step 3: For the current divisor d, calculate the sum of
            // ceil(arr[i] / d) for all elements
            for (int i = 0; i < n; i++) {
                sum += Math.ceil((double) arr[i] / (double) d);
            }

            // Step 4: If the sum is within the limit, return this divisor
            if (sum <= limit)
                return d;
        }

        // Step 5: If no divisor is found, return -1
        return -1;
    }

    // -------------------------------
    // Helper Function
    // -------------------------------
    // This function calculates sum of ceil(arr[i] / div) for all elements
    public static int sumByD(int[] arr, int div) {
        int n = arr.length;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += Math.ceil((double) arr[i] / (double) div);
        }

        return sum;
    }

    // -------------------------------
    // Optimal Approach (Binary Search)
    // -------------------------------
    public static int smallestDivisorOptimal(int[] arr, int limit) {
        int n = arr.length;

        // If number of elements is greater than the limit, it's impossible
        // Because even if divisor = maximum possible, each element will contribute at least 1
        if (n > limit)
            return -1;

        // Step 1: Find the maximum element in the array
        int maxi = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxi = Math.max(maxi, arr[i]);
        }

        // Step 2: Binary search for smallest divisor between 1 and maxi
        int low = 1, high = maxi;
        while (low <= high) {
            int mid = low + (high - low) / 2; // Avoids integer overflow

            // Step 3: If current divisor works, try smaller one
            if (sumByD(arr, mid) <= limit) {
                high = mid - 1;
            } 
            // Step 4: Else, try larger divisor
            else {
                low = mid + 1;
            }
        }

        // Step 5: Low will be the smallest divisor that satisfies the condition
        return low;
    }

    // -------------------------------
    // Driver Code for Testing
    // -------------------------------
    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 9};
        int limit = 6;

        System.out.println("Brute Force Result: " + smallestDivisorBrute(arr, limit));
        System.out.println("Optimal Result: " + smallestDivisorOptimal(arr, limit));
    }
}
