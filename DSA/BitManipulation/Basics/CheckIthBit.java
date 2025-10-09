// ✅ Class name short and concise
public class CheckIthBit {

    // Function to check if the ith bit of n is set (1)
    public static boolean isSetBit(int n, int i) {
        // Step 1️⃣: Create a mask by left-shifting 1 by i positions
        int mask = 1 << i;  // Example: if i = 2, mask = 0000...0100

        // Step 2️⃣: Use bitwise AND to check if the ith bit is set
        // If (n & mask) != 0 → the ith bit is 1
        return (n & mask) != 0;
    }

    public static void main(String[] args) {
        int n = 13;  // Binary: 1101
        int i = 2;   // We want to check the 2nd bit (0-indexed from right)

        System.out.println("Number: " + n + " (Binary: 1101)");
        System.out.println("Checking if bit at index " + i + " is set...");

        boolean result = isSetBit(n, i);

        System.out.println("Result: " + result);
    }
}

/*
-----------------------------------
🧠 EXPLANATION / VISUALIZATION
-----------------------------------

Let’s visualize with:
n = 13 → Binary: 1101
Indexes (0-indexed from right): [3][2][1][0]
                                 1  1  0  1

Now check if the ith bit (say i = 2) is set.

Step 1️⃣: Create mask → 1 << i
    1 << 2 = 0000...0100 (binary 4)

Step 2️⃣: Perform bitwise AND
    n = 1101
  mask = 0100
  ----------------
  n & mask = 0100  (non-zero → ith bit is set ✅)

So, the 2nd bit (from right) is indeed **1** → returns **true**.

-----------------------------------
⚙️ ALTERNATIVE UNDERSTANDING
-----------------------------------
We’re basically checking:
👉 If (n / 2^i) is odd → ith bit is set.
👉 If (n / 2^i) is even → ith bit is not set.

But the bitwise method `(n & (1 << i)) != 0` is far more efficient and elegant.

-----------------------------------
⏱ TIME & SPACE COMPLEXITY
-----------------------------------
👉 Time Complexity: O(1)
   (Only constant-time bitwise operations)

👉 Space Complexity: O(1)
   (No extra space or data structures)

-----------------------------------
⚡ OPTIMALITY
-----------------------------------
✅ Fastest possible — just one shift and one AND operation.  
✅ Works for any integer size.  
✅ No loops, no conversions.  
✅ Ideal for bit manipulation problems in competitive programming.

-----------------------------------
*/
