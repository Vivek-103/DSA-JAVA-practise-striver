// SieveOfEratosthenes.java

import java.util.Arrays; // Import the Arrays class to use its fill method.

/**
 * This class demonstrates finding all prime numbers up to a given integer 'n'
 * using two methods: a simple brute-force approach and the highly efficient
 * Sieve of Eratosthenes algorithm.
 */
public class SieveOfEratosthenes {

    /**
     * The main method acts as a driver to execute and display the results
     * from both prime-finding methods.
     * @param args Command line arguments (not used in this program).
     */
    public static void main(String[] args) {
        int n = 50; // Define the upper limit to find prime numbers up to.

        System.out.println("--- Brute-Force Method (Primes up to " + n + ") ---"); // Header for the first method.
        bruteForcePrimes(n); // Call the brute-force method.
        System.out.println("\n"); // Add a newline for better readability.

        System.out.println("--- Optimal Method: Sieve of Eratosthenes (Primes up to " + n + ") ---"); // Header for the optimal method.
        sieve(n); // Call the Sieve of Eratosthenes method.
    }

    // ==================================================================================
    // Method 1: Brute-Force Approach
    // ==================================================================================
    /*
     * Time Complexity: O(n * sqrt(n))
     * Space Complexity: O(1)
     *
     * Explanation:
     * The outer loop runs 'n' times (from 1 to n). Inside this loop, we call the isPrime
     * function. The isPrime function has its own loop that runs up to sqrt(i) times in the
     * worst case for each number 'i'. This results in a total complexity of roughly n * sqrt(n).
     *
     * Visualization:
     * Imagine checking every single number individually.
     * For 2: Is it prime? Yes.
     * For 3: Is it prime? Yes.
     * For 4: Check divisibility by 2. No, not prime.
     * For 5: Is it prime? Yes.
     * For 6: Check divisibility by 2. No, not prime.
     * ... This process is straightforward but performs many redundant checks.
     */
    public static void bruteForcePrimes(int n) {
        for (int i = 1; i <= n; i++) { // Loop through every number from 1 to n.
            if (isPrime(i)) { // Check if the current number 'i' is prime using a helper function.
                System.out.print(i + " "); // If it is prime, print it to the console followed by a space.
            }
        }
    }

    /**
     * Helper function to check if a single number is prime.
     * @param num The number to check.
     * @return true if the number is prime, false otherwise.
     */
    public static boolean isPrime(int num) {
        if (num <= 1) { // 1 and numbers less than 1 are not prime.
            return false; // So, return false immediately.
        }
        // Loop from 2 up to the square root of the number.
        // We only need to check up to sqrt(num) because if a number 'num' has a factor
        // larger than its square root, it must also have a factor smaller than it.
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) { // If 'num' is perfectly divisible by 'i', it's not prime.
                return false; // So, return false.
            }
        }
        return true; // If the loop finishes without finding any factors, the number is prime.
    }


    // ==================================================================================
    // Method 2: Optimal - Sieve of Eratosthenes
    // ==================================================================================
    /*
     * Time Complexity: O(n * log(log(n)))
     * Space Complexity: O(n)
     *
     * Explanation:
     * This is a highly efficient algorithm. We create a boolean array of size 'n' which
     * accounts for the O(n) space. The outer loop runs up to sqrt(n). The key insight is
     * that the inner loops (which mark multiples) collectively run a total number of times
     * proportional to n * (1/2 + 1/3 + 1/5 + ...), which mathematically converges to
     * n * log(log(n)). This is very close to linear time, O(n), making it extremely fast.
     *
     * Visualization:
     * Let's find primes up to 10.
     * 1. Create a boolean list representing numbers 0-10, all initially 'true' (is prime).
     * [ T, T, T, T, T, T, T, T, T, T, T ] (Indices 0-10)
     *
     * 2. Start at p=2. It's marked 'true'. So, 2 is prime. Now, "sieve out" all its multiples
     * (4, 6, 8, 10) by marking them 'false'.
     * [ T, T, T, T, F, T, F, T, F, T, F ]
     *
     * 3. Move to the next number marked 'true', which is p=3. It's prime. Sieve out its multiples
     * (6, 9).
     * [ T, T, T, T, F, T, F, T, F, F, F ]
     *
     * 4. The next number marked 'true' is 5. Its square (25) is > 10, so we can stop.
     *
     * 5. The numbers whose indices are still 'true' are the primes: 2, 3, 5, 7.
     */
    public static void sieve(int n) {
        // Create a boolean array `primes` of size n+1.
        // `primes[i]` will be true if 'i' is a prime number, and false otherwise.
        boolean[] primes = new boolean[n + 1];

        // Initialize all entries in the array to true.
        // By default, we assume every number is a prime until proven otherwise.
        Arrays.fill(primes, true);

        primes[0] = false; // 0 is not a prime number, so mark it as false.
        primes[1] = false; // 1 is not a prime number, so mark it as false.

        // Loop from p=2 up to the square root of n.
        // We only need to check up to sqrt(n) for the same reason as in the isPrime helper.
        for (int p = 2; p * p <= n; p++) {
            // If primes[p] is still true, it means 'p' is a prime number.
            if (primes[p]) {
                // Now, mark all multiples of 'p' as not prime (i.e., false).
                // We start marking from p*p because smaller multiples (like 2*p, 3*p)
                // would have already been marked by smaller primes (like 2, 3).
                for (int i = p * p; i <= n; i += p) {
                    primes[i] = false; // Mark this multiple as not a prime.
                }
            }
        }

        // After the sieve process is complete, print all the numbers that are still marked as true.
        for (int i = 2; i <= n; i++) { // Loop from 2 to n.
            if (primes[i]) { // Check if the number at index 'i' is marked as prime.
                System.out.print(i + " "); // If it is, print it to the console.
            }
        }
    }
}