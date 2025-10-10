/*
📘 Problem: XORRange
------------------------------------------------------------
Given two integers L and R, find the XOR of all numbers in the range [L, R].
Example: L=3, R=7 → XOR = 3^4^5^6^7
*/

public class XORRange {

    // 🔹 Helper Function: XOR from 0 to n
    // ----------------------------------
    // This uses a known pattern:
    // n % 4 == 0 → result = n
    // n % 4 == 1 → result = 1
    // n % 4 == 2 → result = n + 1
    // n % 4 == 3 → result = 0
    // This pattern comes from observing XOR of consecutive numbers from 0.
    // Time: O(1)
    // Space: O(1)
    public static int xorFromZeroToN(int n) {
        switch (n % 4) {
            case 0: return n;     // n % 4 == 0 → XOR = n
            case 1: return 1;     // n % 4 == 1 → XOR = 1
            case 2: return n + 1; // n % 4 == 2 → XOR = n+1
            case 3: return 0;     // n % 4 == 3 → XOR = 0
        }
        return 0; // default (never reached)
    }

    // 🔹 Function: XOR from L to R
    // ----------------------------
    // XOR(L, R) = XOR(0 to R) ^ XOR(0 to L-1)
    // This is because XOR is its own inverse:
    // a^a = 0 → cancels all numbers before L
    // Time: O(1)
    // Space: O(1)
    public static int xorFromLToR(int L, int R) {
        return xorFromZeroToN(R) ^ xorFromZeroToN(L - 1);
    }

    // 🚀 Driver Method
    public static void main(String[] args) {
        int L = 3;
        int R = 7;

        System.out.println("🔹 XOR from " + L + " to " + R + " = " + xorFromLToR(L, R));

        // Test another example
        L = 5;
        R = 10;
        System.out.println("🔹 XOR from " + L + " to " + R + " = " + xorFromLToR(L, R));
    }
}

/*
🧠 Visualization Example:

Example 1: L = 3, R = 7
Numbers in range: 3,4,5,6,7
Step 1: XOR from 0 to R=7
0^1^2^3^4^5^6^7 = 0 (by pattern)
Step 2: XOR from 0 to L-1=2
0^1^2 = 3 (by pattern)
Step 3: XOR(L,R) = XOR(0 to R) ^ XOR(0 to L-1) = 0 ^ 3 = 3
✅ Result = 3

📌 Time Complexity: O(1) → Only constant time operations
📌 Space Complexity: O(1) → No extra memory used

Key Idea:
- XOR has a pattern when starting from 0
- XOR(L,R) can be computed using XOR(0,R) ^ XOR(0,L-1)
- Very efficient for large ranges
*/
