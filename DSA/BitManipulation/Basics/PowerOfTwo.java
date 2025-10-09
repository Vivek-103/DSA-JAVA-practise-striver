// ✅ Problem: Check if Number is Power of Two
// ---------------------------------------------------------------
// Given an integer n, return true if n is a power of two, otherwise false.
// A number is a power of two if it has exactly one set bit in binary representation.
//
// Example:
// n = 8 → binary: 1000 → true
// n = 10 → binary: 1010 → false

public class PowerOfTwo {

    // Function to check if n is a power of two
    static boolean isPowerOfTwo(int n) {

        // Step 1️⃣: Handle edge case
        // 0 or negative numbers are NOT powers of two
        if (n <= 0) return false;

        // Step 2️⃣: Bitwise trick
        // n & (n - 1) removes the rightmost set bit
        // For power of two numbers, this will make n zero
        // Example: 8 = 1000 → 8-1 = 0111 → 1000 & 0111 = 0000 ✅
        return (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {

        int n1 = 8;   // binary: 1000 → power of 2
        int n2 = 10;  // binary: 1010 → not power of 2
        int n3 = 1;   // binary: 1    → power of 2 (2^0)
        int n4 = 0;   // binary: 0    → not power of 2

        System.out.println(n1 + " is power of 2? → " + isPowerOfTwo(n1));
        System.out.println(n2 + " is power of 2? → " + isPowerOfTwo(n2));
        System.out.println(n3 + " is power of 2? → " + isPowerOfTwo(n3));
        System.out.println(n4 + " is power of 2? → " + isPowerOfTwo(n4));
    }
}

/*
-----------------------------------------------
🧠 Visualization:
-----------------------------------------------

Example 1:
n = 8 → Binary: 1000
n - 1 = 7 → Binary: 0111
n & (n - 1) = 1000 & 0111 = 0000 → true ✅

Example 2:
n = 10 → Binary: 1010
n - 1 = 9 → Binary: 1001
n & (n - 1) = 1010 & 1001 = 1000 → not 0 → false ❌

Example 3:
n = 1 → Binary: 1
n - 1 = 0 → Binary: 0
n & (n - 1) = 1 & 0 = 0 → true ✅

Example 4:
n = 0 → Binary: 0 → false (edge case)

-----------------------------------------------
⏱️ Time Complexity:
-----------------------------------------------
O(1) — Single AND operation and comparison, constant time.

💾 Space Complexity:
-----------------------------------------------
O(1) — Only a few integer variables used.

-----------------------------------------------
✅ Optimal Explanation:
-----------------------------------------------
✔ Power of two numbers have exactly one set bit in binary.
✔ Using (n & (n - 1)) == 0 detects this in constant time.
✔ Works for all positive integers.
✔ Very common and efficient trick in bit manipulation problems.

*/
