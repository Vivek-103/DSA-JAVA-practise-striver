// ✅ Problem: Toggle the ith Bit of a given integer n
// ----------------------------------------------------
// Given two integers n and i, toggle (invert) the ith bit in the binary
// representation of n. The bits are 0-indexed from the least significant bit (rightmost bit).

// Example:
// n = 13 (Binary: 1101)
// i = 1
// Output = 15 (Binary: 1111)
// Explanation: The 1st bit (counting from right) is flipped (0 → 1).

public class ToggleIthBit {

    // Function to toggle the ith bit of n
    static int toggleBit(int n, int i) {

        // Step 1️⃣: Create a bitmask with 1 only at the ith position
        // (1 << i) shifts 1 to the left by i positions
        int bitmask = (1 << i);

        // Step 2️⃣: XOR operation with bitmask flips the ith bit
        // If ith bit = 1 → 1 XOR 1 = 0 (bit cleared)
        // If ith bit = 0 → 0 XOR 1 = 1 (bit set)
        int result = n ^ bitmask;

        // Step 3️⃣: Return the updated number
        return result;
    }

    public static void main(String[] args) {

        int n = 13;  // binary: 1101
        int i = 1;   // toggle the 1st bit (0-indexed)

        // Function call to toggle the ith bit
        int result = toggleBit(n, i);

        // Output the result
        System.out.println("Original number: " + n);
        System.out.println("After toggling " + i + "th bit: " + result);
    }
}

/*
-----------------------------------------------
🧠 Visualization:
-----------------------------------------------
n = 13 → Binary: 1101
i = 1

Step 1: 1 << i → 0001 << 1 = 0010
Step 2: n ^ bitmask → 1101 ^ 0010 = 1111 (which is 15)

Final Output: 15

-----------------------------------------------
⏱️ Time Complexity:
-----------------------------------------------
O(1) — Bit manipulation happens in constant time.

💾 Space Complexity:
-----------------------------------------------
O(1) — Only uses a few integer variables.

-----------------------------------------------
✅ Optimal Approach Explanation:
-----------------------------------------------
Using XOR (^) is the most efficient way to toggle bits.
It requires only one operation and no conditional checks.

⚡ XOR property:
A ^ 0 = A  → bit remains same  
A ^ 1 = ~A → bit is flipped  

Hence, XOR with (1 << i) toggles only the ith bit perfectly.
*/
