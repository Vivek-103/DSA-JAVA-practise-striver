// ✅ Class name short and concise
public class SwapInPlace {

    public static void main(String[] args) {
        int a = 5;
        int b = 10;

        System.out.println("Before swapping: a = " + a + ", b = " + b);

        // 🔁 Swap using arithmetic (no third variable)
        // Step 1: Add both numbers and store in 'a'
        a = a + b;  // Now a = 15 (5 + 10)

        // Step 2: Subtract new 'a' with 'b' to get original 'a' value into 'b'
        b = a - b;  // b = 15 - 10 = 5

        // Step 3: Subtract new 'a' with new 'b' to get original 'b' value into 'a'
        a = a - b;  // a = 15 - 5 = 10

        System.out.println("After swapping:  a = " + a + ", b = " + b);
    }
}

/*
-----------------------------------
🧠 EXPLANATION / VISUALIZATION
-----------------------------------

Let’s visualize how the values change step by step.

Initial:
a = 5, b = 10

Step 1️⃣ → a = a + b
   a = 5 + 10 = 15, b = 10

Step 2️⃣ → b = a - b
   b = 15 - 10 = 5

Step 3️⃣ → a = a - b
   a = 15 - 5 = 10

✅ Final:
a = 10, b = 5  → Successfully swapped!

-----------------------------------
⚙️ ALTERNATIVE (Bitwise XOR Method)
-----------------------------------
You can also swap without arithmetic, using XOR (avoids overflow risk):

a = a ^ b;  // a becomes (a XOR b)
b = a ^ b;  // b becomes (a XOR b) XOR b = original a
a = a ^ b;  // a becomes (a XOR b) XOR a = original b

Both methods achieve the same result.

-----------------------------------
⏱ TIME & SPACE COMPLEXITY
-----------------------------------
👉 Time Complexity: O(1)
   (Only constant-time arithmetic operations)

👉 Space Complexity: O(1)
   (No extra variables used — done completely in-place)

-----------------------------------
⚡ OPTIMALITY
-----------------------------------
✅ Uses only 2 variables (a, b)
✅ No temporary or third variable
✅ Constant-time and constant-space
✅ Simple arithmetic or bitwise operations

-----------------------------------
*/
