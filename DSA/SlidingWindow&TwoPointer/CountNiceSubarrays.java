// File Name: CountNiceSubarrays.java
// Problem: Count Number of Nice Subarrays
// Language: Java

/*
---------------------------------------------
🧩 PROBLEM UNDERSTANDING:
---------------------------------------------
You are given an integer array `nums` and an integer `k`.

A "nice subarray" is defined as a contiguous subarray that contains exactly k odd numbers.

Goal → Count how many subarrays in `nums` are "nice".

Example:
nums = [1, 1, 2, 1, 1], k = 3

All nice subarrays are:
- [1, 1, 2, 1] (odds = 3)
- [1, 2, 1, 1] (odds = 3)
✅ Output: 2

---------------------------------------------
💡 INTUITION (Prefix-Sum + Sliding Window):
---------------------------------------------
We want the number of subarrays with exactly k odd numbers.

We can count it using the formula:
Count(exactly k odds) = Count(at most k odds) - Count(at most (k - 1) odds)

So, we’ll create a helper function that returns the number of subarrays 
containing at most `K` odd numbers.

Then the final answer = atMost(nums, k) - atMost(nums, k - 1)

---------------------------------------------
⚙️ HOW THE atMost() FUNCTION WORKS:
---------------------------------------------
We use a sliding window approach:
1️⃣ Expand the window with `right` pointer.
2️⃣ Count how many odds we have in the current window.
3️⃣ If odds > k → shrink window from the left until odds <= k.
4️⃣ Every time, we add (right - left + 1) to the count, 
    because all subarrays ending at `right` and starting between [left, right] are valid.

---------------------------------------------
⏱️ TIME & SPACE COMPLEXITY:
---------------------------------------------
Time Complexity: O(n) → Each element visited at most twice
Space Complexity: O(1) → Constant extra space
---------------------------------------------
*/

import java.util.*;

public class CountNiceSubarrays {

    // Helper method: counts subarrays with at most k odd numbers
    public static int countAtMost(int[] nums, int k) {
        int left = 0, count = 0;
        int oddCount = 0;

        // Expand the window using right pointer
        for (int right = 0; right < nums.length; right++) {
            // If current element is odd, increment oddCount
            if (nums[right] % 2 != 0) {
                oddCount++;
            }

            // Shrink the window until oddCount <= k
            while (oddCount > k) {
                if (nums[left] % 2 != 0) {
                    oddCount--;
                }
                left++;
            }

            // Add all valid subarrays ending at 'right'
            count += (right - left + 1);
        }

        return count;
    }

    // Main method to count subarrays with exactly k odd numbers
    public static int numberOfSubarrays(int[] nums, int k) {
        return countAtMost(nums, k) - countAtMost(nums, k - 1);
    }

    // 🧮 Visualization Helper
    private static void visualizeProcess(int[] nums, int k) {
        System.out.println("\n🧮 Visualization of Sliding Window for 'atMost' function:");
        int left = 0, oddCount = 0, totalCount = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] % 2 != 0) oddCount++;

            while (oddCount > k) {
                if (nums[left] % 2 != 0) oddCount--;
                left++;
            }

            totalCount += (right - left + 1);

            System.out.print("Window [" + left + " - " + right + "] → ");
            System.out.print(Arrays.toString(Arrays.copyOfRange(nums, left, right + 1)));
            System.out.println(" | oddCount = " + oddCount + " | totalCount = " + totalCount);
        }

        System.out.println("\n✅ Total subarrays with ≤ " + k + " odd numbers = " + totalCount);
    }

    // 🧠 MAIN METHOD - Driver Code
    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 1, 1};
        int k = 3;

        System.out.println("Input Array: " + Arrays.toString(nums));
        System.out.println("Required number of odd numbers (k): " + k);

        int result = numberOfSubarrays(nums, k);
        System.out.println("\n✅ Count of Nice Subarrays (exactly " + k + " odds): " + result);

        // Visualization for understanding atMost(nums, k)
        visualizeProcess(nums, k);
    }
}

/*
---------------------------------------------
✅ SAMPLE OUTPUT:
---------------------------------------------
Input Array: [1, 1, 2, 1, 1]
Required number of odd numbers (k): 3

✅ Count of Nice Subarrays (exactly 3 odds): 2

🧮 Visualization of Sliding Window for 'atMost' function:
Window [0 - 0] → [1] | oddCount = 1 | totalCount = 1
Window [0 - 1] → [1, 1] | oddCount = 2 | totalCount = 3
Window [0 - 2] → [1, 1, 2] | oddCount = 2 | totalCount = 6
Window [0 - 3] → [1, 1, 2, 1] | oddCount = 3 | totalCount = 10
Window [0 - 4] → [1, 1, 2, 1, 1] | oddCount = 4 | totalCount = 10
Window [1 - 4] → [1, 2, 1, 1] | oddCount = 3 | totalCount = 14
Window [2 - 4] → [2, 1, 1] | oddCount = 2 | totalCount = 17

✅ Total subarrays with ≤ 3 odd numbers = 17
---------------------------------------------
*/

