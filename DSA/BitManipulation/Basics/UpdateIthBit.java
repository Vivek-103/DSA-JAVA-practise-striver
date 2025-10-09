// ✅ Problem: Update the ith Bit of a given integer n
// ----------------------------------------------------
// Given three integers n, i, and bitValue (0 or 1),
// update the ith bit of n to the given bitValue.
// Bits are counted from the least significant bit (rightmost bit).

// Example:
// n = 13 (Binary: 1101)
// i = 2, bitValue = 0
// Output = 9 (Binary: 1001)
// Explanation: The 2nd bit (from right) changed from 1 → 0.

public class UpdateIthBit {

    // Function to update ith bit of n to given bitValue (0 or 1)
    static int updateBit(int n, int i, int bitValue) {

        // Step 1️⃣: Clear the ith bit first (make it 0)
        // ~(1 << i) creates a mask where all bits are 1 except ith bit which is 0
        n = n & ~(1 << i);

        // Step 2️⃣: Set the ith bit to bitValue (0 or 1)
        // (bitValue << i) moves bitValue to the correct bit position
        n = n | (bitValue << i);

        // Step 3️⃣: Return the updated number
        return n;
    }

    public static void main(String[] args) {

        int n = 13;       // binary: 1101
        int i = 2;        // we want to modify the 2nd bit
        int bitValue = 0; // we want to set it to 0

        // Function call to update ith bit
        int result = updateBit(n, i, bitValue);

        // Output the result
        System.out.println("Original number: " + n);
        System.out.println("After updating " + i + "th bit to " + bitValue + ": " + result);
    }
}

/*
-----------------------------------------------
🧠 Visualization:
-----------------------------------------------
n = 13 → Binary: 1101
i = 2, bitValue = 0

Step 1:  ~(1 << i)
→ (1 << 2) = 0100
→ ~(0100) = 1011
→ n & 1011 = 1101 & 1011 = 1001 (bit cleared at i=2)

Step 2:  (bitValue << i)
→ (0 << 2) = 0000
→ n | 0000 = 1001 | 0000 = 1001 (no change, bit remains 0)

Result = 1001 (which is 9 in decimal)

-----------------------------------------------
If bitValue = 1:
Step 1: clear ith bit → 1001
Step 2: set ith bit   → 1001 | 0100 = 1101 → 13

-----------------------------------------------
⏱️ Time Complexity:
-----------------------------------------------
O(1) — constant-time bit manipulation

💾 Space Complexity:
-----------------------------------------------
O(1) — only uses a few integer variables

-----------------------------------------------
✅ Optimal Approach Explanation:
-----------------------------------------------
This two-step process (clear + set) ensures accuracy:
1️⃣ Clear ensures the ith bit is 0 (base condition)
2️⃣ OR operation ensures bitValue is placed correctly  
No conditionals or extra space needed!

This is the **most efficient** and **cleanest** way to update a bit.
*/
