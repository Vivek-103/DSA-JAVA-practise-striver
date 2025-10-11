/**
 * A class to demonstrate methods for finding the prime factors of a number.
 */
public class PrimeFactors {

    /**
     * The main method to run and test our prime factorization functions.
     */
    public static void main(String[] args) {
        // The number for which we want to find prime factors.
        int number = 48;
        
        System.out.println("Finding prime factors for the number: " + number);
        
        // --- Call Method 1: Brute Force ---
        System.out.println("\n## Method 1: Brute Force Approach ##");
        printPrimeFactorsBruteForce(number);
        
        // --- Call Method 2: Optimal ---
        System.out.println("\n\n## Method 2: Optimal Approach ##");
        printPrimeFactorsOptimal(number);
    }

    // -------------------------------------------------------------------------
    //   Method 1: Brute Force Approach
    // -------------------------------------------------------------------------
    /*
     * Logic:
     * This method iterates through all numbers `i` from 2 up to `n`. For each `i`,
     * it first checks if `i` is a prime number using a helper function. If `i` is
     * prime and it divides `n`, we print it and update `n`.
     *
     * Time Complexity: O(n * sqrt(n))
     * The outer loop runs `n` times, and the `isPrime` check inside takes roughly
     * `sqrt(n)` time, making this approach extremely slow and impractical for
     * large numbers.
     */
    public static void printPrimeFactorsBruteForce(int n) {
        // Loop from 2 up to the number n.
        for (int i = 2; i <= n; i++) {
            // First, check if the current number 'i' is prime.
            if (isPrime(i)) {
                // If 'i' is prime, check if it divides 'n'.
                // Use a 'while' loop to handle repeated factors (e.g., 12 = 2 * 2 * 3).
                while (n % i == 0) {
                    // Print the prime factor.
                    System.out.print(i + " ");
                    // Divide 'n' by the factor to reduce it.
                    n = n / i;
                }
            }
        }
    }

    /**
     * A helper function to check if a number is prime.
     */
    public static boolean isPrime(int num) {
        // A prime number must be greater than 1.
        if (num <= 1) {
            return false;
        }
        // Check for factors from 2 up to the square root of the number.
        for (int i = 2; i * i <= num; i++) {
            // If a factor is found, it's not a prime number.
            if (num % i == 0) {
                return false;
            }
        }
        // If no factors are found, it's a prime number.
        return true;
    }

    // -------------------------------------------------------------------------
    //   Method 2: Optimal Approach
    // -------------------------------------------------------------------------
    /*
     * Logic: 🧠
     * This method is much faster because it doesn't need a separate `isPrime` check.
     * By dividing `n` by its factors as we find them, we ensure that any new factor
     * we encounter must be prime. For example, once we've divided out all factors of 2,
     * we will never encounter 4, 6, 8, etc., as potential factors.
     *
     * Time Complexity: O(sqrt(n))
     * The loop runs at most up to the square root of 'n'. This is incredibly
     * efficient compared to the brute-force method.
     *
     * Visualization (for n = 48):
     * 1. Check for factors of 2:
     * - 48 % 2 == 0. Print 2. n becomes 24.
     * - 24 % 2 == 0. Print 2. n becomes 12.
     * - 12 % 2 == 0. Print 2. n becomes 6.
     * - 6 % 2 == 0.  Print 2. n becomes 3.
     * - 3 % 2 != 0. Stop checking for 2.
     *
     * 2. Check for odd factors, starting from i = 3 up to sqrt(3) which is ~1.7.
     * - The loop `for (int i = 3; i * i <= n; ...)` condition (3*3 <= 3) is false.
     * - The loop does not run.
     *
     * 3. Final Check:
     * - After the loop, is n > 2? Yes, n is 3.
     * - This means the remaining value of n is a prime factor. Print 3.
     *
     * Final Output: 2 2 2 2 3
     */
    public static void printPrimeFactorsOptimal(int n) {
        // First, handle all factors of 2. This is an optimization.
        while (n % 2 == 0) {
            // As long as n is divisible by 2, print 2.
            System.out.print(2 + " ");
            // And divide n by 2.
            n /= 2;
        }

        // After the above loop, 'n' must be odd. So, we can skip even numbers
        // in our next loop, incrementing by 2.
        for (int i = 3; i * i <= n; i = i + 2) {
            // While 'i' is a factor of the remaining 'n'...
            while (n % i == 0) {
                // ...print 'i'.
                System.out.print(i + " ");
                // And divide 'n' by 'i'.
                n /= i;
            }
        }

        // This condition is for cases where 'n' is a prime number greater than 2
        // after all other divisions. For example, if the input was 14, after
        // dividing by 2, 'n' would be 7. The loop wouldn't find any more factors,
        // so we need to print the remaining 7.
        if (n > 2) {
            System.out.print(n);
        }
    }
}