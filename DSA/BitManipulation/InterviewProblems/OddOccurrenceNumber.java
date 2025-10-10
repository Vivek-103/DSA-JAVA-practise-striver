// ✅ Problem: Find the Number that Appears Odd Number of Times
// ------------------------------------------------------------------
// Given an array of integers where every number appears twice except one,
// find the number that appears only once (odd number of times).

public class OddOccurrenceNumber {

    // Function to find the element that appears once
    static int findOddOccurrence(int[] nums) {

        // Step 1: Initialize a variable to hold XOR result
        int xor = 0;

        // Step 2: XOR all elements in the array
        for (int num : nums) {
            xor = xor ^ num;  
            // XOR property:
            //  a ^ a = 0  (same numbers cancel out)
            //  a ^ 0 = a  (XOR with 0 keeps the number)
            // Thus, all duplicates become 0, leaving the single number.
        }

        // Step 3: The remaining number after XORing all elements
        // is the one that appears an odd number of times
        return xor;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 5, 4, 5, 3, 4};  // 2 appears once (odd number of times)

        int result = findOddOccurrence(nums);

        System.out.println("The number appearing odd number of times is: " + result);
    }
}

/*
------------------------------------------------------------------
🧠 Visualization:
------------------------------------------------------------------
Array: [2, 3, 5, 4, 5, 3, 4]

Perform XOR step-by-step:

xor = 0
→ 0 ^ 2 = 2
→ 2 ^ 3 = 1
→ 1 ^ 5 = 4
→ 4 ^ 4 = 0
→ 0 ^ 5 = 5
→ 5 ^ 3 = 6
→ 6 ^ 4 = 2

✅ Final Result = 2
All pairs cancel out, only '2' (the odd occurrence) remains.

------------------------------------------------------------------
⚙️ How XOR Helps:
------------------------------------------------------------------
1. Duplicate numbers vanish because:
   → a ^ a = 0
2. 0 has no effect on XOR:
   → 0 ^ b = b
3. XOR is commutative and associative, so order doesn’t matter.

------------------------------------------------------------------
⏱️ Time Complexity:
------------------------------------------------------------------
O(n) → Each element is visited once.

------------------------------------------------------------------
💾 Space Complexity:
------------------------------------------------------------------
O(1) → Uses only one variable for XOR.

------------------------------------------------------------------
✅ Notes:
------------------------------------------------------------------
✔ No extra data structures (memory efficient)
✔ Works even if numbers are large
✔ Can easily extend to bit-manipulation-based problems
*/
