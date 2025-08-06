package BinarySearchOnAnswers;

// Finding the square root (floor value) of a number using different methods
public class q1 {

    // Brute-force method to find floor of square root
    public static int floorSqrtBrute(int n) {
        int ans = 0;

        // Loop from 1 to n (long is used to avoid overflow on multiplication)
        for (long i = 1; i <= n; i++) {
            long val = i * i; // Calculate square of current number

            // If square is less than or equal to n, store it as potential answer
            if (val <= (long) n) {
                ans = (int) i; // Update answer
            } else {
                break; // If square exceeds n, no need to continue
            }
        }

        return ans; // Return the final answer
    }
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    // Optimal method using built-in Math.sqrt function
    public static int floorSqrtOptimal(int n) {
        int ans = (int) Math.sqrt(n); // Calculate square root and typecast to int to get floor value
        return ans; // Return floor of square root
    }
    // Time Complexity: O(1)
    // Space Complexity: O(1)

    // Most efficient method using Binary Search to find floor of square root
    public static int floorSqrtOptimal2(int n) {
        int low = 1;          // Start of binary search range
        int high = n;         // End of binary search range

        // Perform binary search
        while (low <= high) {
            long mid = (low + high) / 2;   // Calculate mid (long to avoid overflow)
            long val = mid * mid;          // Square of mid

            if (val <= (long) n) {
                // If mid^2 is less than or equal to n, move to right half
                // We cast mid + 1 to int because 'low' is an int and mid is a long
                low = (int) (mid + 1); // Type casting is necessary to assign long to int
            } else {
                // If mid^2 is more than n, move to left half
                high = (int) (mid - 1); // Again cast to int for same reason
            }
        }

        // When loop ends, 'high' holds the floor of sqrt(n)
        return high;
    }
    // Time Complexity: O(log n)
    // Space Complexity: O(1)

    // Main method to test all three implementations
    public static void main(String[] args) {
        int n = 40; // Example input number

        // Test brute-force method
        int sqrtBrute = floorSqrtBrute(n);
        System.out.println("Brute-force floor sqrt of " + n + " = " + sqrtBrute);

        // Test Math.sqrt based method
        int sqrtOptimal = floorSqrtOptimal(n);
        System.out.println("Math.sqrt floor sqrt of " + n + " = " + sqrtOptimal);

        // Test binary search based method
        int sqrtBinarySearch = floorSqrtOptimal2(n);
        System.out.println("Binary Search floor sqrt of " + n + " = " + sqrtBinarySearch);
    }
}
