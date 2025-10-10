// ✅ Problem: Count Number of Bits to be Flipped to Convert A to B
// ---------------------------------------------------------------
// Given two integers start and goal, return the number of bits 
// that need to be flipped in the binary representation of start 
// to make it equal to goal.

public class CountBitsFlip {

    // Function to count number of bits to be flipped
    static int countBitsFlip(int start, int goal) {
        
        // Step 1: XOR both numbers
        // XOR gives 1 where bits are different
        int xor = start ^ goal; 

        // Step 2: Count number of 1s in XOR result
        // Using Brian Kernighan’s algorithm (optimized)
        int count = 0;
        while (xor > 0) {
            xor = xor & (xor - 1);  // removes the rightmost set bit
            count++;                // increment count for each removed bit
        }

        return count;  // total bits that differ between start and goal
    }

    public static void main(String[] args) {
        int start = 10;   // binary: 1010
        int goal = 20;    // binary: 10100

        int result = countBitsFlip(start, goal);

        System.out.println("Number of bits to be flipped from " + start + " to " + goal + " = " + result);
    }
}

/*
---------------------------------------------------------------
🧠 Visualization:
---------------------------------------------------------------
Example:
start = 10 → binary = 01010
goal  = 20 → binary = 10100
--------------------------------
Step 1: XOR = 01010 ^ 10100 = 11110
         → XOR result = 11110 (4 bits differ)

Step 2: Count set bits in XOR:
    11110 → remove rightmost 1 → 11100 (count=1)
    11100 → remove rightmost 1 → 11000 (count=2)
    11000 → remove rightmost 1 → 10000 (count=3)
    10000 → remove rightmost 1 → 00000 (count=4)
✅ Result = 4 bits need to be flipped

---------------------------------------------------------------
⏱️ Time Complexity:
---------------------------------------------------------------
O(k) — where k = number of set bits in XOR (≤ log₂(n))
Optimized: runs only for the number of differing bits.

---------------------------------------------------------------
💾 Space Complexity:
---------------------------------------------------------------
O(1) — only uses constant extra variables.

---------------------------------------------------------------
✅ Notes:
---------------------------------------------------------------
- XOR highlights differing bits between two numbers.
- Brian Kernighan’s trick efficiently counts set bits.
- Faster than checking each bit individually (O(log n)).
*/
