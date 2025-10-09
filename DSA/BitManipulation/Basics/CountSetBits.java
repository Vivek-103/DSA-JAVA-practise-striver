// ✅ Problem: Count Set Bits in an Integer
// ---------------------------------------------------------------
// Given a positive integer n, return the number of set bits (1s) 
// in its binary representation.
//
// Two methods:
// 1️⃣ Optimized using n & (n - 1) → removes rightmost 1 each time
// 2️⃣ Naive using (n & 1) and right shift → checks each bit

public class CountSetBits {

    // Method 1: Optimized using n & (n - 1)
    static int countSetBitsOptimized(int n) {
        int count = 0;

        while (n > 0) {
            n = n & (n - 1); // remove rightmost set bit
            count++;
        }

        return count;
    }

    // Method 2: Naive using (n & 1) and right shift
    static int countSetBitsNaive(int n) {
        int count = 0;

        while (n > 0) {
            count += n & 1;  // if last bit is 1, add 1 to count
            n = n >> 1;      // right shift n by 1 bit
        }

        return count;
    }

    public static void main(String[] args) {

        int n1 = 13; // binary: 1101 → 3 set bits
        int n2 = 7;  // binary: 0111 → 3 set bits

        System.out.println("Using Optimized method:");
        System.out.println("Number of set bits in " + n1 + ": " + countSetBitsOptimized(n1));
        System.out.println("Number of set bits in " + n2 + ": " + countSetBitsOptimized(n2));

        System.out.println("\nUsing Naive method:");
        System.out.println("Number of set bits in " + n1 + ": " + countSetBitsNaive(n1));
        System.out.println("Number of set bits in " + n2 + ": " + countSetBitsNaive(n2));
    }
}

/*
-----------------------------------------------
🧠 Visualization:
-----------------------------------------------

n = 13 → binary: 1101

Method 1 (Optimized):
Step 1: n = 1101 → n & (n-1) = 1101 & 1100 = 1100 → count = 1
Step 2: n = 1100 → n & (n-1) = 1100 & 1011 = 1000 → count = 2
Step 3: n = 1000 → n & (n-1) = 1000 & 0111 = 0000 → count = 3

Method 2 (Naive):
Step 1: n = 1101 → n & 1 = 1 → count = 1 → n >> 1 = 0110
Step 2: n = 0110 → n & 1 = 0 → count = 1 → n >> 1 = 0011
Step 3: n = 0011 → n & 1 = 1 → count = 2 → n >> 1 = 0001
Step 4: n = 0001 → n & 1 = 1 → count = 3 → n >> 1 = 0000

Result = 3 ✅

-----------------------------------------------
⏱️ Time Complexity:
-----------------------------------------------
Optimized method: O(k) — loops only for number of set bits
Naive method: O(log n) — loops for each bit

💾 Space Complexity:
-----------------------------------------------
Both: O(1) — only count variable used

-----------------------------------------------
✅ Notes:
-----------------------------------------------
- Optimized method is faster if n has few set bits.
- Naive method is straightforward and easy to implement.
- Both methods are widely used in bit manipulation problems.
*/
