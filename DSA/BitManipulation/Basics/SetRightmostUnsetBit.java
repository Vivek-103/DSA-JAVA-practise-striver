// ✅ Problem: Set the Rightmost Unset Bit
// ---------------------------------------------------------------
// Given a positive integer n, set (turn to 1) the rightmost 0 bit
// in its binary representation and return the resulting number.
// If all bits are already set (like 7 → 111), return n as it is.
//
// Example:
// n = 10 (binary: 1010) → output = 11 (binary: 1011)
// n = 7  (binary: 111)  → output = 7  (all bits already set)

public class SetRightmostUnsetBit {

    // Function to set the rightmost 0 (unset) bit of n
    static int setRightmostUnsetBit(int n) {

        // 🧠 Step 1: If all bits are already 1, just return n
        // To check if all bits are 1, add 1 to n and see if it becomes a power of 2
        // Example: 7 (111) + 1 = 8 (1000), which has only one bit set.
        if ((n & (n + 1)) == 0) {
            return n; // all bits are already set
        }

        // 🧠 Step 2: Formula to set the rightmost 0 bit
        // n | (n + 1)
        // Explanation:
        // - Adding 1 to n flips all bits after the rightmost 0 bit (including it).
        // - OR operation sets that bit to 1 while keeping others same.
        int result = n | (n + 1);

        // Step 3: Return the updated result
        return result;
    }

    // Main function to test
    public static void main(String[] args) {

        int n1 = 10; // binary: 1010 → output: 1011 = 11
        int n2 = 7;  // binary: 0111 → output: 0111 = 7 (no change)
        int n3 = 18; // binary: 10010 → output: 10011 = 19

        System.out.println("Original: " + n1 + " → After setting: " + setRightmostUnsetBit(n1));
        System.out.println("Original: " + n2 + " → After setting: " + setRightmostUnsetBit(n2));
        System.out.println("Original: " + n3 + " → After setting: " + setRightmostUnsetBit(n3));
    }
}

/*
-----------------------------------------------
🧠 Visualization:
-----------------------------------------------

Example 1:
n = 10 → Binary: 1010
n + 1 = 11 → Binary: 1011
n | (n + 1) = 1010 | 1011 = 1011 → 11
✅ Rightmost 0 (2nd bit from right) becomes 1.

Example 2:
n = 7 → Binary: 111
n + 1 = 8 → Binary: 1000
n | (n + 1) = 111 | 1000 = 111 (no unset bit)
✅ All bits were already 1 → return n.

Example 3:
n = 18 → Binary: 10010
n + 1 = 19 → Binary: 10011
n | (n + 1) = 10010 | 10011 = 10011 → 19
✅ Rightmost 0 set → 10011

-----------------------------------------------
⏱️ Time Complexity:
-----------------------------------------------
O(1) — Bitwise operations take constant time.

💾 Space Complexity:
-----------------------------------------------
O(1) — No extra space used.

-----------------------------------------------
✅ Optimal Explanation:
-----------------------------------------------
✔ Checking (n & (n + 1)) == 0 efficiently detects all bits set.
✔ Using n | (n + 1) smartly sets the rightmost 0 bit with one operation.
✔ No loops, no conditionals inside loops — pure bitwise magic!

*/
