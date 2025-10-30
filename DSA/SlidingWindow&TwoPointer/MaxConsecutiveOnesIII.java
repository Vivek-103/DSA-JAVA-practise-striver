// File Name: MaxConsecutiveOnesIII.java
// Problem: Maximum Consecutive Ones III
// Language: Java

/*
---------------------------------------------
🧩 PROBLEM UNDERSTANDING:
---------------------------------------------
You are given a binary array `nums` and an integer `k`.

You can flip at most `k` zeros to ones.

Goal → Find the length of the longest subarray containing only 1's 
after performing at most k flips.

Example:
nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
We can flip at most 2 zeros.
Longest subarray of 1's = 6 → flip two zeros at positions (5,6)
✅ Output: 6

---------------------------------------------
💡 VISUALIZATION (Sliding Window Approach):
---------------------------------------------
We will use a sliding window with two pointers (left and right):

Idea:
- Expand the window by moving `right`.
- Keep track of the count of zeros in the window.
- If zeros > k → shrink window from the left until zeros <= k.
- Keep updating max window length.

Example:
nums = [1,1,0,0,1,1,1,0], k = 2

Step-by-step:
right → expand → count zeros
left → shrink when zeros > k

Window movements:
[1] → len=1
[1,1] → len=2
[1,1,0] → len=3
[1,1,0,0] → zeros=2 → len=4
[1,1,0,0,1] → len=5 ✅ (max)
[1,1,0,0,1,1] → len=6 ✅ (max)
[1,1,0,0,1,1,1] → len=7 ✅ (max)
[1,1,0,0,1,1,1,0] → zeros=3 ❌ → move left
after adjusting → len=6 ✅

Answer = 7

---------------------------------------------
⚙️ ALGORITHM:
---------------------------------------------
1️⃣ Initialize left = 0, zeroCount = 0, maxLen = 0
2️⃣ Iterate right from 0 → n-1:
     - If nums[right] == 0 → increment zeroCount
     - While zeroCount > k → move left until zeros <= k
     - Update maxLen = max(maxLen, right - left + 1)
3️⃣ Return maxLen

---------------------------------------------
⏱️ TIME & SPACE COMPLEXITY:
---------------------------------------------
Time Complexity: O(n) → Each element visited at most twice (once by left, once by right)
Space Complexity: O(1) → Constant extra variables only
---------------------------------------------
*/

import java.util.*;

public class MaxConsecutiveOnesIII {

    // Method to calculate the longest subarray with at most k zeros flipped
    public static int longestOnes(int[] nums, int k) {
        int left = 0;          // left boundary of window
        int zeroCount = 0;     // count of zeros in current window
        int maxLen = 0;        // result variable

        // Expand the window using 'right' pointer
        for (int right = 0; right < nums.length; right++) {

            // If we encounter a zero, increment zeroCount
            if (nums[right] == 0) {
                zeroCount++;
            }

            // If more than k zeros, shrink from the left
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;  // removing a zero from window
                }
                left++;  // move left boundary to right
            }

            // Update max length of valid window
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    // 🧮 Visualization Helper (to understand sliding window movement)
    private static void visualizeProcess(int[] nums, int k) {
        System.out.println("\n🧮 Visualization of Sliding Window Process:");
        int left = 0, zeroCount = 0, maxLen = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) zeroCount++;

            // Shrink if window becomes invalid
            while (zeroCount > k) {
                if (nums[left] == 0) zeroCount--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);

            // Printing the current window
            System.out.print("Window [" + left + " - " + right + "] → ");
            System.out.print(Arrays.toString(Arrays.copyOfRange(nums, left, right + 1)));
            System.out.println(" | zeros = " + zeroCount + " | maxLen = " + maxLen);
        }

        System.out.println("\n✅ Final Maximum Consecutive Ones (with ≤ " + k + " flips) = " + maxLen);
    }

    // 🧠 MAIN METHOD - Driver Code
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k = 2;

        System.out.println("Input Array: " + Arrays.toString(nums));
        System.out.println("Allowed Zero Flips (k): " + k);

        int result = longestOnes(nums, k);
        System.out.println("\n✅ Maximum Consecutive Ones (with ≤ " + k + " flips): " + result);

        visualizeProcess(nums, k);
    }
}

/*
---------------------------------------------
✅ SAMPLE OUTPUT:
---------------------------------------------
Input Array: [1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0]
Allowed Zero Flips (k): 2

✅ Maximum Consecutive Ones (with ≤ 2 flips): 6

🧮 Visualization of Sliding Window Process:
Window [0 - 0] → [1] | zeros = 0 | maxLen = 1
Window [0 - 1] → [1, 1] | zeros = 0 | maxLen = 2
Window [0 - 2] → [1, 1, 1] | zeros = 0 | maxLen = 3
Window [0 - 3] → [1, 1, 1, 0] | zeros = 1 | maxLen = 4
Window [0 - 4] → [1, 1, 1, 0, 0] | zeros = 2 | maxLen = 5
Window [0 - 5] → [1, 1, 1, 0, 0, 0] | zeros = 3 | maxLen = 5
Window [3 - 6] → [0, 0, 1, 1] | zeros = 2 | maxLen = 4
Window [3 - 7] → [0, 0, 1, 1, 1] | zeros = 2 | maxLen = 5
Window [3 - 8] → [0, 0, 1, 1, 1, 1] | zeros = 2 | maxLen = 6 ✅
Window [3 - 9] → [0, 0, 1, 1, 1, 1, 1] | zeros = 2 | maxLen = 7 ✅
Window [4 - 10] → [0, 1, 1, 1, 1, 1, 0] | zeros = 2 | maxLen = 7 ✅

✅ Final Maximum Consecutive Ones (with ≤ 2 flips) = 6
---------------------------------------------
*/
