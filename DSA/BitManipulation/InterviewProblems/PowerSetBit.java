// ✅ Problem: Power Set | Bit Manipulation + Recursion
// ---------------------------------------------------------
// Given an array of numbers, generate all possible subsets
// (the Power Set) using both bitwise and recursive approaches.

import java.util.*;

public class PowerSetBit {

    // 🧩 APPROACH 1: Using Bit Manipulation
    static List<List<Integer>> powerSetBitwise(int[] nums) {
        int n = nums.length;
        int total = 1 << n; // total subsets = 2^n

        List<List<Integer>> result = new ArrayList<>();

        // Iterate through all possible binary representations
        for (int mask = 0; mask < total; mask++) {
            List<Integer> subset = new ArrayList<>();

            // Check every bit of mask
            for (int i = 0; i < n; i++) {
                // If ith bit is set, include nums[i]
                if ((mask & (1 << i)) != 0) {
                    subset.add(nums[i]);
                }
            }
            result.add(subset);
        }
        return result;
    }

    // 🧩 APPROACH 2: Using Recursion (Backtracking)
    static void generateRecursive(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        // Base case: if we've considered all elements
        if (index == nums.length) {
            result.add(new ArrayList<>(current)); // add a copy of current subset
            return;
        }

        // Choice 1: Exclude current element
        generateRecursive(nums, index + 1, current, result);

        // Choice 2: Include current element
        current.add(nums[index]);
        generateRecursive(nums, index + 1, current, result);

        // Backtrack → remove last added element
        current.remove(current.size() - 1);
    }

    static List<List<Integer>> powerSetRecursive(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generateRecursive(nums, 0, new ArrayList<>(), result);
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};

        // ✅ Using Bit Manipulation
        System.out.println("🔹 Power Set using Bit Manipulation:");
        List<List<Integer>> bitResult = powerSetBitwise(nums);
        for (List<Integer> subset : bitResult) System.out.println(subset);

        // ✅ Using Recursion
        System.out.println("\n🔹 Power Set using Recursion:");
        List<List<Integer>> recResult = powerSetRecursive(nums);
        for (List<Integer> subset : recResult) System.out.println(subset);
    }
}

/*
-------------------------------------------------------------
🧠 VISUALIZATION for nums = [1, 2, 3]
-------------------------------------------------------------

Binary Mask  Subset
--------------------
000          []
001          [1]
010          [2]
011          [1, 2]
100          [3]
101          [1, 3]
110          [2, 3]
111          [1, 2, 3]

Total Subsets = 2^3 = 8

-------------------------------------------------------------
💡 BITWISE IDEA:
-------------------------------------------------------------
Each subset corresponds to a binary number where:
- Bit = 1 → include element
- Bit = 0 → exclude element

Example:
mask = 101 (binary of 5)
→ include nums[0] and nums[2] → subset = [1, 3]

-------------------------------------------------------------
💡 RECURSIVE IDEA:
-------------------------------------------------------------
Think of decisions for each element:
For nums = [1, 2, 3]

                   []
             /               \
          [1]               []
        /      \           /     \
   [1,2]     [1]       [2]       []
    / \       / \       / \       / \
[1,2,3][1,2][2,3][2] [3] [2][3]  []

You either:
✅ Include the element  
❌ Exclude the element  

Each path in the recursion tree represents one subset.

-------------------------------------------------------------
⚙️ TIME COMPLEXITY:
-------------------------------------------------------------
Both Approaches → O(n * 2^n)
🔸 For each of 2^n subsets, we might include up to n elements.

-------------------------------------------------------------
💾 SPACE COMPLEXITY:
-------------------------------------------------------------
🔹 Bit Manipulation → O(1) (excluding output list)
🔹 Recursion → O(n) stack space due to recursion calls

-------------------------------------------------------------
✅ Summary
-------------------------------------------------------------
| Approach           | Method              | Complexity | Notes                         |
|--------------------|---------------------|-------------|-------------------------------|
| Bit Manipulation   | Iterative, Bitwise  | O(n * 2^n) | Fast, clean, non-recursive    |
| Recursion          | Backtracking        | O(n * 2^n) | Easier to visualize choices   |
*/
