// ✅ Problem: Clear the ith Bit of a given integer n
// ----------------------------------------------------
// Given two integers n and i, clear (set to 0) the ith bit in the binary
// representation of n. The bits are 0-indexed from the least significant bit (rightmost bit).

// Example:
// n = 13 (Binary: 1101)
// i = 2
// Output = 9 (Binary: 1001)
// Explanation: The 2nd bit (counting from right) is cleared (set to 0).

public class ClearIthBit {

    // Function to clear the ith bit of n
    static int clearBit(int n, int i) {

        // Step 1️⃣: Create a bitmask with all 1s except the ith bit as 0
        // (1 << i) creates a number with only the ith bit as 1
        // ~(1 << i) flips all bits, so only the ith bit becomes 0, others remain 1
        int bitmask = ~(1 << i);

        // Step 2️⃣: AND operation with n clears the ith bit
        // The ith bit of n will be forced to 0 because (0 & x) = 0
        // All other bits remain unchanged because (1 & x) = x
        int result = n & bitmask;

        // Step 3️⃣: Return the updated number
        return result;
    }

    public static void main(String[] args) {

        int n = 13;  // binary: 1101
        int i = 2;   // we want to clear the 2nd bit (0-indexed)

        // Function call to clear the ith bit
        int result = clearBit(n, i);

        // Output the result
        System.out.println("Original number: " + n);
        System.out.println("After clearing " + i + "th bit: " + result);
    }
}

/*
-----------------------------------------------
🧠 Visualization:
-----------------------------------------------
n = 13 → Binary:  1101
i = 2

Step 1: 1 << i  → 0001 << 2 = 0100
Step 2: ~(0100) → 1011  (bitmask)
Step 3: n & bitmask → 1101 & 1011 = 1001 (which is 9)

Final Output: 9

-----------------------------------------------
⏱️ Time Complexity:
-----------------------------------------------
O(1) — Bit manipulation operations are constant time.

💾 Space Complexity:
-----------------------------------------------
O(1) — Uses only a few integer variables.

-----------------------------------------------
✅ Optimal Approach Explanation:
-----------------------------------------------
We use a bitmask to isolate and clear the desired bit.
No loops, no extra memory, pure bitwise manipulation.
This is the most optimal way possible for this problem.
*/
