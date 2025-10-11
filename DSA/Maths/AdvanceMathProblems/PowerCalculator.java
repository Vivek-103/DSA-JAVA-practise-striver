/**
 * A concise class to demonstrate different methods for power calculation.
 */
public class PowerCalculator {

    /**
     * The main method to run and test both power calculation functions.
     */
    public static void main(String[] args) {
        // The base and exponent for our calculation.
        double base = 2.0;
        int exponent = 10;

        System.out.println("Calculating " + base + " ^ " + exponent);
        
        // --- Call Method 1: Simple Iterative ---
        System.out.println("\n## Method 1: Simple Iterative Approach ##");
        double result1 = powerBruteForce(base, exponent);
        System.out.println("Result: " + result1);

        // --- Call Method 2: Binary Exponentiation (Optimal) ---
        System.out.println("\n## Method 2: Binary Exponentiation (Optimal) ##");
        double result2 = powerOptimal(base, exponent);
        System.out.println("Result: " + result2);
        
        // --- Test a negative exponent ---
        System.out.println("\nCalculating 3.0 ^ -4");
        System.out.println("Result (Optimal): " + powerOptimal(3.0, -4));
    }

    // -------------------------------------------------------------------------
    //   Method 1: Simple Iterative Approach (Brute Force)
    // -------------------------------------------------------------------------
    /*
     * Logic:
     * This is the most intuitive method. To calculate x^n, we simply multiply
     * x by itself 'n' times using a loop.
     *
     * Time Complexity: O(n)
     * The loop runs 'n' times, making the runtime directly proportional to the
     * exponent 'n'. This can be very slow for large values of 'n'.
     *
     * Visualization (for base = 2, exponent = 5):
     * - Initialize result = 1.0
     * - Loop 1: result = 1.0 * 2 = 2.0
     * - Loop 2: result = 2.0 * 2 = 4.0
     * - Loop 3: result = 4.0 * 2 = 8.0
     * - Loop 4: result = 8.0 * 2 = 16.0
     * - Loop 5: result = 16.0 * 2 = 32.0
     * - Return 32.0
     */
    public static double powerBruteForce(double x, int n) {
        // The result of any number raised to the power of 0 is 1.
        if (n == 0) {
            return 1.0;
        }

        // Initialize the result to 1.
        double result = 1.0;
        // Take the absolute value of n for the loop count.
        long exponent = Math.abs((long)n);

        // Loop 'exponent' times.
        for (int i = 0; i < exponent; i++) {
            // Multiply the result by the base in each iteration.
            result *= x;
        }
        
        // If the original exponent was negative, return the reciprocal.
        if (n < 0) {
            return 1.0 / result;
        }
        
        // Otherwise, return the calculated result.
        return result;
    }

    // -------------------------------------------------------------------------
    //   Method 2: Binary Exponentiation (Optimal)
    // -------------------------------------------------------------------------
    /*
     * Logic: 🧠
     * This method, also known as exponentiation by squaring, is much faster.
     * It uses the binary representation of the exponent 'n'. The core idea is:
     * - If n is even, x^n = (x*x)^(n/2).
     * - If n is odd,  x^n = x * x^(n-1).
     * We can iterate while n is not 0, checking if n is odd at each step.
     *
     * Time Complexity: O(log n)
     * The number of operations is proportional to the number of bits in the
     * exponent 'n', which is log base 2 of n. This is extremely fast.
     *
     * Visualization (for base = 3, exponent = 9, which is 1001 in binary):
     * Initial State: x = 3, n = 9, result = 1.0
     *
     * 1. n = 9 (odd):
     * - result = result * x  ->  1.0 * 3 = 3.0
     * - x = x * x          ->  3 * 3 = 9.0
     * - n = n / 2          ->  9 / 2 = 4
     *
     * 2. n = 4 (even):
     * - (result is unchanged)
     * - x = x * x          ->  9.0 * 9.0 = 81.0
     * - n = n / 2          ->  4 / 2 = 2
     *
     * 3. n = 2 (even):
     * - (result is unchanged)
     * - x = x * x          ->  81.0 * 81.0 = 6561.0
     * - n = n / 2          ->  2 / 2 = 1
     *
     * 4. n = 1 (odd):
     * - result = result * x  ->  3.0 * 6561.0 = 19683.0
     * - x = x * x          ->  6561.0 * 6561.0 = ...
     * - n = n / 2          ->  1 / 2 = 0
     *
     * 5. n is 0. Loop terminates. Return result: 19683.0
     */
    public static double powerOptimal(double x, int n) {
        // This will hold the final answer.
        double result = 1.0;
        
        // Use a long for the exponent to safely handle Integer.MIN_VALUE.
        long exponent = n;
        
        // If the exponent is negative, we'll calculate the power for its
        // positive equivalent and take the reciprocal later.
        if (exponent < 0) {
            exponent = -exponent;
        }

        // Loop until the exponent becomes 0.
        while (exponent > 0) {
            // If the current exponent is odd (i.e., its last binary digit is 1).
            if (exponent % 2 == 1) {
                // Multiply the base 'x' into our result.
                result = result * x;
            }
            
            // Square the base. This is the "exponentiation by squaring" part.
            x = x * x;
            
            // Halve the exponent (equivalent to a right bit shift).
            exponent = exponent / 2;
        }

        // If the original exponent 'n' was negative, return the reciprocal of the result.
        if (n < 0) {
            return 1.0 / result;
        }
        
        // Otherwise, return the calculated result.
        return result;
    }
}