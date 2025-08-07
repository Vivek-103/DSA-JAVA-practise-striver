package BinarySearchOnAnswers;

// Class to find the Nth root of a number
public class q2 {

    // Helper function to calculate base^exp using fast exponentiation
    public static long func(int b, int exp) {
        long ans = 1;         // Initialize result to 1
        long base = b;        // Convert base to long for safe multiplication

        // Perform fast exponentiation
        while (exp > 0) {
            if (exp % 2 == 1) {        // If exponent is odd
                exp--;                 // Decrease exponent by 1
                ans = ans * base;      // Multiply result with base
            } else {                   // If exponent is even
                exp /= 2;              // Divide exponent by 2
                base = base * base;    // Square the base
            }
        }

        return ans; // Return the final result
    }

    // ----------------------
    // Brute-force method
    // ----------------------
    public static int NthRoot(int n, int m) {
        // Try all integers from 1 to m
        for (int i = 1; i <= m; i++) {
            long val = func(i, n);          // Compute i^n using helper function

            if (val == (long) m) return i;  // If i^n == m, return i as the Nth root
            else if (val > (long) m) break; // If i^n exceeds m, break early
        }

        return -1; // If no integer root found, return -1
    }
    // Time Complexity: O(m * log n) → for each i from 1 to m, we do O(log n) work
    // Space Complexity: O(1)

    // ----------------------
    // Optimal Binary Search method
    // ----------------------
    public static int NthRootOptimal(int n, int m) {
        int low = 1;          // Minimum possible value of root
        int high = m;         // Maximum possible value of root

        // Binary search over the answer
        while (low <= high) {
            int mid = (low + high) / 2; // Calculate middle value
            long val = func(mid, n);    // Compute mid^n using helper function

            if (val == (long) m) {
                return mid;             // Found exact Nth root
            } else if (val < (long) m) {
                low = mid + 1;          // Search in the right half
            } else {
                high = mid - 1;         // Search in the left half
            }
        }

        return -1; // No integer Nth root exists
    }
    // Time Complexity: O(log m * log n)
    // - log m for binary search range [1...m]
    // - log n per call to func (fast exponentiation)
    // Space Complexity: O(1)

    // ----------------------
    // Main method to test both implementations
    // ----------------------
    public static void main(String[] args) {
        int n = 3;  // Degree of root
        int m = 27; // Number whose root we need

        // Test brute-force method
        int resultBrute = NthRoot(n, m);
        System.out.println("Brute-force Nth root of " + m + " with n = " + n + " is: " + resultBrute);

        // Test optimal binary search method
        int resultOptimal = NthRootOptimal(n, m);
        System.out.println("Optimal Nth root of " + m + " with n = " + n + " is: " + resultOptimal);
    }
}
