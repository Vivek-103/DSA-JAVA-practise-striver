// ✅ Class name short and concise
public class SetIthBit {

    // Function to set the ith bit of integer n
    public static int setBit(int n, int i) {
        // Step 1️⃣: Create a mask by left-shifting 1 by i positions
        int mask = 1 << i;  // Example: if i = 2, mask = 0000...0100

        // Step 2️⃣: Use bitwise OR to set the ith bit
        // OR operation sets a bit to 1 if either bit is 1
        // So, even if the ith bit is already 1, it stays 1
        return n | mask;
    }

    public static void main(String[] args) {
        int n = 9;  // Binary: 1001
        int i = 1;  // We want to set the 1st bit (0-indexed from right)

        System.out.println("Before setting bit: n = " + n + " (Binary: 1001)");

        int result = setBit(n, i);

        System.out.println("After setting bit " + i + ": " + result + " (Binary: 1011)");
    }
}

/*
-----------------------------------
🧠 EXPLANATION / VISUALIZATION
-----------------------------------

Let’s take an example:
n = 9 → Binary: 1001
Indexes (0-indexed from right): [3][2][1][0]
                                 1  0  0  1

We want to **set the 1st bit** (i = 1).

Step 1️⃣: Create a mask
   1 << 1 = 0000...0010  (binary representation)

Step 2️⃣: Perform bitwise OR
   n = 1001
 mask = 0010
 ---------------
 n | mask = 1011  ✅

Result = 11 (in decimal)

✅ So, the 1st bit is now set to 1 (it was 0 earlier).

-----------------------------------
⚙️ HOW IT WORKS (Bitwise OR)
-----------------------------------
Bitwise OR truth table:
  0 | 0 = 0
  0 | 1 = 1
  1 | 0 = 1
  1 | 1 = 1

Thus, using `n | (1 << i)` ensures that the ith bit becomes **1**
and all other bits remain unchanged.

-----------------------------------
⏱ TIME & SPACE COMPLEXITY
-----------------------------------
👉 Time Complexity: O(1)
   (Only one bit shift and one OR operation)

👉 Space Complexity: O(1)
   (No extra space required)

-----------------------------------
⚡ OPTIMALITY
-----------------------------------
✅ Fastest and most memory-efficient way  
✅ No loops or conditionals  
✅ Direct bitwise manipulation in constant time  
✅ Standard approach in competitive programming and low-level operations  

-----------------------------------
🔍 QUICK CHECK
-----------------------------------
Example 1:
n = 9 (1001), i = 1 → output = 11 (1011)

Example 2:
n = 10 (1010), i = 2 → output = 14 (1110)

Example 3:
n = 7 (0111), i = 3 → output = 15 (1111)

-----------------------------------
*/
