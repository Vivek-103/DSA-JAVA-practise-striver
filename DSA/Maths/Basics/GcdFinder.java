/**
 * A concise class to find the GCD (Greatest Common Divisor) of two numbers.
 */
public class GcdFinder {

    /**
     * The main method to run and test both of our GCD functions.
     */
    public static void main(String[] args) {
        // The two numbers for which we want to find the GCD.
        int num1 = 52;
        int num2 = 20;

        System.out.println("Finding the GCD for " + num1 + " and " + num2);
        
        // --- Call Method 1: Brute Force ---
        System.out.println("\n## Method 1: Brute Force Approach ##");
        int gcd1 = findGcdBruteForce(num1, num2);
        System.out.println("Result: " + gcd1);

        // --- Call Method 2: Euclidean Algorithm ---
        System.out.println("\n## Method 2: Euclidean Algorithm (Optimal) ##");
        int gcd2 = findGcdOptimal(num1, num2);
        System.out.println("Result: " + gcd2);
    }

    // -------------------------------------------------------------------------
    //   Method 1: Brute Force Approach
    // -------------------------------------------------------------------------
    /*
     * Logic:
     * The GCD can't be larger than the smaller of the two numbers. So, we can
     * simply loop downwards from the smaller number to 1. The first number we
     * find that divides both `a` and `b` perfectly is their greatest common divisor.
     *
     * Time Complexity: O(min(a, b))
     * In the worst case (e.g., when the GCD is 1), the loop runs `min(a, b)` times.
     * This is very inefficient for large numbers.
     *
     * Visualization (for a = 12, b = 8):
     * - The smaller number is 8. The loop starts from i = 8.
     * - i = 8: 12 % 8 != 0
     * - i = 7: 12 % 7 != 0
     * - i = 6: 12 % 6 == 0, but 8 % 6 != 0
     * - i = 5: 12 % 5 != 0
     * - i = 4: 12 % 4 == 0 AND 8 % 4 == 0.  ✅ Found it! Return 4.
     */
    public static int findGcdBruteForce(int a, int b) {
        // Find the smaller of the two numbers.
        int smaller = Math.min(a, b);
        
        // Loop downwards from the smaller number to 1.
        for (int i = smaller; i >= 1; i--) {
            // Check if 'i' divides both 'a' and 'b' without a remainder.
            if (a % i == 0 && b % i == 0) {
                // If it does, 'i' is the GCD. Return it and exit the loop.
                return i;
            }
        }
        // This line is technically unreachable if a and b are positive,
        // as 1 will always be a common divisor.
        return 1;
    }

    // -------------------------------------------------------------------------
    //   Method 2: Euclidean Algorithm (Optimal)
    // -------------------------------------------------------------------------
    /*
     * Logic: 🧠
     * This ancient and highly efficient algorithm is based on a simple principle:
     * The greatest common divisor of two numbers `a` and `b` is the same as the
     * greatest common divisor of `b` and the remainder of `a` divided by `b`.
     * Principle: gcd(a, b) = gcd(b, a % b)
     * We repeat this process until the remainder is 0. The last non-zero
     * divisor is the GCD.
     *
     * Time Complexity: O(log(min(a, b)))
     * The number of steps is logarithmic, making this extremely fast even for
     * very large numbers. This is the standard and best way to calculate GCD.
     *
     * Visualization (for a = 52, b = 20):
     * 1. Current state: (a = 52, b = 20). Remainder = 52 % 20 = 12.
     * Next state: (a becomes 20, b becomes 12).
     * 2. Current state: (a = 20, b = 12). Remainder = 20 % 12 = 8.
     * Next state: (a becomes 12, b becomes 8).
     * 3. Current state: (a = 12, b = 8). Remainder = 12 % 8 = 4.
     * Next state: (a becomes 8, b becomes 4).
     * 4. Current state: (a = 8, b = 4). Remainder = 8 % 4 = 0.
     * Next state: (a becomes 4, b becomes 0).
     * 5. The loop terminates because b is 0. The GCD is the current value of a, which is 4.
     */
    public static int findGcdOptimal(int a, int b) {
        // The loop continues as long as the second number ('b') is not zero.
        while (b != 0) {
            // Store the value of 'b' in a temporary variable.
            int temp = b;
            // Update 'b' to be the remainder of 'a' divided by 'b'.
            b = a % b;
            // Update 'a' to be the old value of 'b' (which we stored in 'temp').
            a = temp;
        }
        // When 'b' becomes 0, 'a' holds the GCD.
        return a;
    }
}