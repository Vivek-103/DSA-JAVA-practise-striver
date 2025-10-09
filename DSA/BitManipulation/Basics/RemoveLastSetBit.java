// ✅ Problem: Remove Last Set Bit
// ---------------------------------------------------------------
// Given a positive integer n, remove (turn to 0) the rightmost set bit (1)
// in its binary representation and return the resulting number.
//
// Example:
// n = 10 (binary: 1010) → output = 1000 = 8
// n = 7  (binary: 0111) → output = 0110 = 6

public class RemoveLastSetBit {

    // Function to remove the rightmost set bit of n
    static int removeLastSetBit(int n) {

        // Step 1️⃣: n & (n - 1) removes the rightmost set bit
        // Explanation:
        // - n - 1 flips all bits after the rightmost 1 (including it)
        // - AND operation with n clears that rightmost 1
        int result = n & (n - 1);

        // Step 2️⃣: Return the updated result
        return result;
    }

    public static void main(String[] args) {

        int n1 = 10; // binary: 1010 → output: 1000 = 8
        int n2 = 7;  // binary: 0111 → output: 0110 = 6
        int n3 = 18; // binary: 10010 → output: 10000 = 16

        System.out.println("Original: " + n1 + " → After removing last set bit: " + removeLastSetBit(n1));
        System.out.println("Original: " + n2 + " → After removing last set bit: " + removeLastSetBit(n2));
        System.out.println("Original: " + n3 + " → After removing last set bit: " + removeLastSetBit(n3));
    }
}

/*
-----------------------------------------------
🧠 Visualization:
-----------------------------------------------

Example 1:
n = 10 → Binary: 1010
n - 1 = 9  → Binary: 1001
n & (n - 1) = 1010 & 1001 = 1000 → 8
✅ Rightmost set bit (2nd from right) removed.

Example 2:
n = 7 → Binary: 0111
n - 1 = 6 → Binary: 0110
n & (n - 1) = 0111 & 0110 = 0110 → 6
✅ Rightmost set bit removed.

Example 3:
n = 18 → Binary: 10010
n - 1 = 17 → Binary: 10001
n & (n - 1) = 10010 & 10001 = 10000 → 16
✅ Rightmost set bit removed.

-----------------------------------------------
⏱️ Time Complexity:
-----------------------------------------------
O(1) — Only a single subtraction and AND operation.

💾 Space Complexity:
-----------------------------------------------
O(1) — Only uses a few integer variables.

-----------------------------------------------
✅ Optimal Explanation:
-----------------------------------------------
✔ Using n & (n - 1) is a classic bitwise trick to remove the rightmost 1.
✔ No loops or conditionals are needed.
✔ Works in constant time, ideal for competitive programming and low-level manipulation.

*/
