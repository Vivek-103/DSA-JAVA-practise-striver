/*
📘 Problem: Single Number II (LeetCode #137)
------------------------------------------------------------
Given an integer array nums, where every element appears exactly three times 
except for one element which appears exactly once. 
Find and return the single number that appears once.

You must implement a solution with linear runtime complexity (O(n))
and constant extra space (O(1)).
*/

import java.util.*;

public class SingleNumberII {
    
    // 🔹 Approach 1: Optimal Bit Manipulation (using two bitmasks)
    // ------------------------------------------------------------
    // Uses 'ones' and 'twos' to track bits that appear once and twice respectively.
    // When a bit appears thrice, it’s removed from both.
    // Time: O(n)
    // Space: O(1)
    
    public int singleNumberOptimal(int[] nums) {
        int ones = 0;  // Bits that have appeared exactly once
        int twos = 0;  // Bits that have appeared exactly twice

        // Iterate through each number in the array
        for (int num : nums) {
            // Step 1️⃣: Add bits to 'twos' that are already set in 'ones' and appear again in 'num'
            twos |= ones & num;

            // Step 2️⃣: XOR with 'num' → toggles bits (adds new, removes duplicates)
            ones ^= num;

            // Step 3️⃣: Identify bits that appeared three times
            int commonMask = ~(ones & twos);

            // Step 4️⃣: Remove bits that appeared thrice from both 'ones' and 'twos'
            ones &= commonMask;
            twos &= commonMask;
        }

        // ✅ 'ones' will hold the unique number after full traversal
        return ones;
    }


    // 🧩 Visualization Example:
    /*
        nums = [2, 2, 3, 2]

        Step | num | ones | twos | Explanation
        -----|------|------|------|-----------------------------
        Init |  -   | 0000 | 0000 | Start empty
          1  |  2   | 0010 | 0000 | 2 appears first → add to ones
          2  |  2   | 0000 | 0010 | 2 appears twice → remove from ones, add to twos
          3  |  3   | 0011 | 0010 | 3 appears → add to ones
          4  |  2   | 0011 | 0000 | 2 appears third → remove from both

        ✅ Final Answer = 0011 (binary) = 3
    */


    // 🔹 Approach 2: Bit Counting (Simpler to Understand)
    // ------------------------------------------------------------
    // Count number of 1s at each bit position (0 to 31)
    // If count % 3 != 0, that bit belongs to the unique number.
    // Time: O(32 × n)
    // Space: O(1)
    
    public int singleNumberBitCount(int[] nums) {
        int result = 0;

        // Iterate over all 32 bits (for standard int)
        for (int i = 0; i < 32; i++) {
            int count = 0;

            // Count how many numbers have this i-th bit set
            for (int num : nums) {
                if ((num >> i & 1) == 1) {
                    count++;
                }
            }

            // If bit appears not multiple of 3 → belongs to single number
            if (count % 3 != 0) {
                result |= (1 << i);
            }
        }

        return result;
    }

    // 🧠 Visualization for Bit Counting:
    /*
        nums = [2, 2, 3, 2]
        Binary forms:
        2 → 10
        3 → 11

        Bit Position | Bit Count | Count % 3 | Result Bit
        --------------|------------|------------|------------
        0 (LSB)       |     1      |     1      |     1
        1             |     4      |     1      |     1
        ✅ Result = 11₂ = 3
    */


    // 🧾 Time & Space Complexity Summary
    /*
        Approach         | Time Complexity | Space Complexity | Notes
        -----------------|----------------|------------------|------------------------------------
        Bitmask (Optimal) | O(n)           | O(1)             | Uses bit logic, fastest method.
        Bit Count (Simple)| O(32 × n) ≈ O(n)| O(1)            | More intuitive but slightly slower.
    */


    // 🚀 Driver Method for Testing
    public static void main(String[] args) {
        SingleNumberII obj = new SingleNumberII();
        int[] nums = {2, 2, 3, 2};

        System.out.println("🔹 Using Optimal Bitmask Approach: " + obj.singleNumberOptimal(nums));
        System.out.println("🔹 Using Bit Counting Approach:   " + obj.singleNumberBitCount(nums));
    }
}
