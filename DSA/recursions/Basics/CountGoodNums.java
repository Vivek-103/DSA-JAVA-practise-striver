// Class name: short version of the problem statement
public class CountGoodNums {

    // Define modulo (10^9 + 7) as the problem usually requires to prevent overflow
    static final long MOD = 1000000007;

    // Main function to count good numbers
    public static int countGoodNumbers(long n) {

        // Even index digits can be [0,2,4,6,8] → 5 choices
        // Odd index digits can be [2,3,5,7] → 4 choices

        // Count how many even and odd positions exist in n-digit number
        long evenCount = (n + 1) / 2;  // even indices (0,2,4,...)
        long oddCount = n / 2;         // odd indices (1,3,5,...)

        // Use fast exponentiation to compute:
        // total = (5^evenCount * 4^oddCount) % MOD
        long total = (modPow(5, evenCount) * modPow(4, oddCount)) % MOD;

        // Return total as integer (since it's within MOD range)
        return (int) total;
    }

    // Recursive fast modular exponentiation: computes (base^exp) % MOD
    static long modPow(long base, long exp) {
        // Base case: anything raised to 0 is 1
        if (exp == 0) return 1;

        // Recursive call: divide exponent by 2
        long half = modPow(base, exp / 2);

        // Combine results: (half * half) % MOD
        long result = (half * half) % MOD;

        // If exponent is odd, multiply one extra base
        if (exp % 2 != 0)
            result = (result * base) % MOD;

        // Return final result under modulo
        return result;
    }

    // Main method to test with examples
    public static void main(String[] args) {
        System.out.println(countGoodNumbers(1));  // 5 (even indices only)
        System.out.println(countGoodNumbers(4));  // 5^2 * 4^2 = 400
        System.out.println(countGoodNumbers(50)); // larger input check
        System.out.println(countGoodNumbers(1000000000L)); // very large n
    }
}
