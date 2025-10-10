/*
📘 Problem: DivideIntegers
------------------------------------------------------------
Given two integers dividend and divisor, divide them **without using** *, /, or % operators.
Return the quotient. Assume integer division truncates toward zero.

Example: dividend = 10, divisor = 3 → result = 3
*/

public class DivideIntegers {

    // 🔹 Approach: Bit Manipulation + Subtraction (Optimal)
    // -----------------------------------------------------
    // Idea:
    // - Repeatedly subtract multiples of divisor from dividend
    // - Use bit shifting to multiply divisor by powers of 2 efficiently
    // - Keeps subtracting largest possible multiples
    // - Handles negative numbers
    // Time Complexity: O(log N)^2 → because we double divisor each time
    // Space Complexity: O(1)

    public static int divide(int dividend, int divisor) {
        // Edge case: overflow
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;

        // Determine the sign of result
        boolean negative = (dividend < 0) ^ (divisor < 0); // XOR: true if result is negative

        // Convert both dividend and divisor to long and make positive
        long dvd = Math.abs((long) dividend);
        long dvs = Math.abs((long) divisor);

        long quotient = 0;

        // Keep subtracting multiples of divisor from dividend
        while (dvd >= dvs) {
            long temp = dvs, multiple = 1;

            // Double the divisor until it exceeds dividend
            while (dvd >= (temp << 1)) {
                temp <<= 1;        // temp = temp * 2
                multiple <<= 1;    // multiple = multiple * 2
            }

            // Subtract the largest found multiple from dividend
            dvd -= temp;
            // Add the count to quotient
            quotient += multiple;
        }

        // Apply the sign
        return negative ? (int) -quotient : (int) quotient;
    }

    // 🚀 Driver Method
    public static void main(String[] args) {
        int dividend = 10, divisor = 3;
        System.out.println("10 ÷ 3 = " + divide(dividend, divisor));

        dividend = 43; divisor = -8;
        System.out.println("43 ÷ -8 = " + divide(dividend, divisor));

        dividend = -2147483648; divisor = -1;
        System.out.println("-2147483648 ÷ -1 = " + divide(dividend, divisor));
    }
}

/*
🧠 Visualization Example:

Example: dividend = 43, divisor = 8

Step 1: Initialize quotient = 0, dividend = 43
Step 2: Find largest multiple of 8 <= 43 using bit shifts:
    8 << 0 = 8 → 8 <= 43
    8 << 1 = 16 → 16 <= 43
    8 << 2 = 32 → 32 <= 43
    8 << 3 = 64 → 64 > 43 → stop
Step 3: Subtract largest multiple (32) → dividend = 43-32 = 11
        Add multiple to quotient → quotient = 4
Step 4: Repeat:
    8 << 0 = 8 → 8 <= 11
    8 << 1 = 16 → 16 > 11 → stop
Step 5: Subtract 8 → dividend = 11-8 = 3
        Add multiple 1 → quotient = 5
Step 6: dividend < divisor → stop
Result: 43 ÷ 8 = 5

📌 Time Complexity: O(log N)^2 → Outer loop runs log(N) times, inner loop also log(N)
📌 Space Complexity: O(1) → Constant extra space

Key Ideas:
1️⃣ Use bit shifting to multiply divisor efficiently
2️⃣ Repeated subtraction instead of division
3️⃣ Track sign separately
4️⃣ Handle edge cases like MIN_VALUE and -1
*/
