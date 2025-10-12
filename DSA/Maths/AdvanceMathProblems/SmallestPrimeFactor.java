// SmallestPrimeFactor.java

/**
 * This class provides solutions to the query-based problem of finding the
 * Smallest Prime Factor (SPF) of a given integer.
 * It includes a simple brute-force method for single calculations and an
 * optimal, sieve-based pre-computation method for handling a large
 * number of queries efficiently.
 */
public class SmallestPrimeFactor {

    // ==================================================================================
    // Optimal Method Variables (For Pre-computation)
    // ==================================================================================

    // Define the maximum value for which we want to find the SPF.
    // This determines the size of our pre-computation array.
    private static final int MAX_N = 100000;

    // An integer array to store the Smallest Prime Factor for each number.
    // spf[i] will hold the smallest prime number that divides 'i'.
    private static int[] spf = new int[MAX_N + 1];

    // A static initializer block. This code is executed exactly once when the class
    // is first loaded, which is the perfect place for our one-time pre-computation.
    static {
        precomputeSPFs(); // Call the method to pre-calculate all SPFs up to MAX_N.
    }

    /**
     * The main method serves as a driver program to test and demonstrate the
     * two implemented methods for finding the Smallest Prime Factor.
     * @param args Command line arguments (not used in this program).
     */
    public static void main(String[] args) {
        // A list of numbers for which we want to find the smallest prime factor.
        int[] queries = {15, 29, 77, 99, 100, 99991}; // 99991 is a prime number.

        System.out.println("--- Method 1: Brute-Force per Query ---");
        // Iterate through each number in our list of queries.
        for (int n : queries) {
            // Find the SPF using the brute-force method for the current number.
            int result = bruteForceSPF(n);
            // Print the result for the current query.
            System.out.println("Smallest Prime Factor of " + n + " is: " + result);
        }

        System.out.println("\n--- Method 2: Optimal using Pre-computation ---");
        // Iterate through the same list of queries.
        for (int n : queries) {
            // Find the SPF using the optimal O(1) query method.
            int result = optimalSPF(n);
            // Print the result.
            System.out.println("Smallest Prime Factor of " + n + " is: " + result);
        }
    }

    // ==================================================================================
    // Method 1: Brute-Force Approach
    // ==================================================================================
    /*
     * Time Complexity: O(sqrt(n)) for a single query. For Q queries, it's O(Q * sqrt(MAX_N)).
     * Space Complexity: O(1)
     *
     * Explanation:
     * For each number 'n', we iterate from 2 up to its square root. The first number 'i'
     * that divides 'n' must be its smallest prime factor. If no such 'i' is found,
     * 'n' itself is prime. This process is repeated from scratch for every query, making it
     * inefficient if the number of queries is large.
     *
     * Visualization:
     * To find SPF of 99:
     * 1. Check n % 2 == 0? 99 % 2 != 0. No.
     * 2. Check n % 3 == 0? 99 % 3 == 0. Yes!
     * 3. Return 3. Done.
     *
     * To find SPF of 97:
     * 1. Check 97 % 2, 97 % 3, 97 % 5, 97 % 7... loop continues up to sqrt(97) (~9.8).
     * 2. No factors found.
     * 3. Return 97 itself.
     */
    public static int bruteForceSPF(int n) {
        if (n <= 1) return n; // Handle edge cases for 0 and 1.
        if (n % 2 == 0) return 2; // The smallest prime factor of any even number is 2.

        // Loop through odd numbers starting from 3 up to the square root of n.
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) { // If 'i' divides 'n'...
                return i;    // ...then 'i' is the smallest prime factor.
            }
        }
        // If the loop completes without finding a factor, 'n' must be prime.
        return n; // So, its smallest prime factor is itself.
    }


    // ==================================================================================
    // Method 2: Optimal Approach (Pre-computation + O(1) Query)
    // ==================================================================================
    /*
     * Pre-computation Time: O(MAX_N * log(log(MAX_N)))
     * Query Time: O(1)
     * Total Time for Q queries: O(MAX_N * log(log(MAX_N)) + Q)
     * Space Complexity: O(MAX_N)
     *
     * Explanation:
     * We use a modified version of the Sieve of Eratosthenes. Instead of just marking
     * numbers as prime/composite, we store their Smallest Prime Factor (SPF).
     * We pre-calculate the SPF for every number from 1 to MAX_N and store it in an array.
     * Any subsequent query for a number 'n' becomes a simple array lookup, which is
     * extremely fast (constant time).
     *
     * Visualization of Pre-computation (for MAX_N=15):
     * 1. Initialize spf[i] = i for all i:
     * spf -> [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]
     * 2. Mark SPF of all even numbers as 2:
     * spf -> [0, 1, 2, 3, 2, 5, 2, 7, 2, 9, 2, 11, 2, 13, 2, 15]
     * 3. Start loop at p=3. Since spf[3]==3, it's a prime.
     * - Mark multiples of 3: Set spf[9]=3, spf[15]=3.
     * - (spf[6] and spf[12] are already 2, which is smaller, so they are not changed).
     * spf -> [0, 1, 2, 3, 2, 5, 2, 7, 2, 3, 2, 11, 2, 13, 2, 3]
     * 4. Next prime is 5 (spf[5]==5). Loop for multiples starts at 5*5=25, which is > 15. So we stop.
     * 5. The spf array is now fully computed and ready for queries.
     */
    private static void precomputeSPFs() {
        // Initialize the SPF for every number to be the number itself.
        // We assume each number is prime until we find a smaller factor.
        for (int i = 1; i <= MAX_N; i++) {
            spf[i] = i;
        }

        // Set the SPF for all even numbers (greater than 2) to be 2.
        for (int i = 4; i <= MAX_N; i += 2) {
            spf[i] = 2;
        }

        // Iterate through numbers starting from 3.
        // We only need to go up to the square root of MAX_N.
        for (int p = 3; p * p <= MAX_N; p++) {
            // Check if 'p' is a prime number.
            // We know 'p' is prime if its SPF is still itself.
            if (spf[p] == p) {
                // 'p' is a prime. Now, update the SPF for all multiples of 'p'.
                // Start from p*p, as smaller multiples would have been handled by smaller primes.
                for (int i = p * p; i <= MAX_N; i += p) {
                    // If the current SPF of 'i' is still 'i', it means we haven't
                    // found a smaller prime factor for it yet.
                    if (spf[i] == i) {
                        spf[i] = p; // Update its SPF to be 'p'.
                    }
                }
            }
        }
    }

    /**
     * Answers a query in O(1) time by looking up the pre-computed value.
     * @param n The number for which to find the smallest prime factor.
     * @return The smallest prime factor of n.
     */
    public static int optimalSPF(int n) {
        // Simply return the pre-calculated value from our spf array.
        return spf[n];
    }
}