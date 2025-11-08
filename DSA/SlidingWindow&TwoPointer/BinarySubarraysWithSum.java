// File: BinarySubarraysWithSum.java
// Author: Vivek Srivastava (example student)
// -----------------------------------------------------------------------------
// Problem Statement:
// You are given a binary array nums (only 0s and 1s) and an integer goal.
// Return the number of non-empty subarrays whose sum is exactly equal to goal.
//
// Example:
// Input: nums = [1, 0, 0, 1, 1, 0], goal = 2
// Output: 6
// Explanation: There are 6 subarrays that sum to 2:
// [1,0,0,1], [0,0,1,1], [0,1,1], [1,1], [1,1,0], [0,0,1,1,0]
// -----------------------------------------------------------------------------


import java.util.*;

public class BinarySubarraysWithSum {

    // -------------------------------------------------------------------------
    // BRUTE FORCE APPROACH
    // -------------------------------------------------------------------------
    // Idea:
    //  - For every start index, check all subarrays starting from it.
    //  - Keep adding elements until sum == goal.
    //  - Increment count whenever a subarray sum equals goal.
    //
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    // -------------------------------------------------------------------------
    public static int numSubarraysWithSumBruteForce(int[] nums, int goal) {
        int count = 0;

        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end]; // Add current element to subarray sum
                if (sum == goal) count++; // Found valid subarray
            }
        }

        return count;
    }

    // -------------------------------------------------------------------------
    // OPTIMAL APPROACH (Prefix Sum + HashMap)
    // -------------------------------------------------------------------------
    // Idea:
    // Use prefix sum to count how many times a certain cumulative sum has appeared.
    //
    // If prefixSum[j] - prefixSum[i] = goal  →  subarray (i+1...j) sums to goal
    //
    // So for each index j:
    //   - currentSum = prefixSum[j]
    //   - we look for (currentSum - goal) in HashMap
    //
    // HashMap stores frequency of each prefix sum encountered.
    //
    // Visualization:
    // nums = [1,0,0,1,1,0], goal = 2
    // -------------------------------------
    // index | num | prefixSum | needed | count | map
    // -------------------------------------
    //   0   |  1  |     1     |  -1    |   0   | {1=1}
    //   1   |  0  |     1     |  -1    |   0   | {1=2}
    //   2   |  0  |     1     |  -1    |   0   | {1=3}
    //   3   |  1  |     2     |   0    |   0   | {1=3,2=1}
    //   4   |  1  |     3     |   1    |   3   | {1=3,2=1,3=1}
    //   5   |  0  |     3     |   1    |   6   | {1=3,2=1,3=2}
    //
    // Final count = 6 ✅
    //
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    // -------------------------------------------------------------------------
    public static int numSubarraysWithSumOptimal(int[] nums, int goal) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1); // Base case: prefix sum 0 seen once

        int currentSum = 0;
        int count = 0;

        for (int num : nums) {
            currentSum += num; // running prefix sum

            // If (currentSum - goal) exists, it means subarray ending here sums to goal
            count += prefixCount.getOrDefault(currentSum - goal, 0);

            // Add current prefix sum to map
            prefixCount.put(currentSum, prefixCount.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }

    // -------------------------------------------------------------------------
    // ALTERNATE OPTIMAL (for binary array only)
    // -------------------------------------------------------------------------
    // Since the array contains only 0 and 1, we can also use a 2-pointer method
    // to count subarrays with at most 'goal' and subtract:
    //    exactly(goal) = atMost(goal) - atMost(goal - 1)
    // This avoids HashMap, works in O(n).
    // -------------------------------------------------------------------------
    public static int numSubarraysWithSumBinaryOptimized(int[] nums, int goal) {
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }

    // Helper: counts subarrays with sum <= goal
    private static int atMost(int[] nums, int goal) {
        if (goal < 0) return 0;
        int left = 0, sum = 0, count = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            // Shrink window if sum exceeds goal
            while (sum > goal) sum -= nums[left++];

            // Number of subarrays ending at right
            count += right - left + 1;
        }

        return count;
    }

    // -------------------------------------------------------------------------
    // MAIN METHOD: TESTING BOTH APPROACHES
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        int[] nums1 = {1, 0, 0, 1, 1, 0};
        int goal1 = 2;

        int[] nums2 = {0, 0, 0, 0, 0, 0};
        int goal2 = 0;

        System.out.println("========== BRUTE FORCE APPROACH ==========");
        System.out.println("Input: [1, 0, 0, 1, 1, 0], goal=2 → Output: " + numSubarraysWithSumBruteForce(nums1, goal1));
        System.out.println("Input: [0,0,0,0,0,0], goal=0 → Output: " + numSubarraysWithSumBruteForce(nums2, goal2));

        System.out.println("\n========== OPTIMAL APPROACH (Prefix Sum + HashMap) ==========");
        System.out.println("Input: [1, 0, 0, 1, 1, 0], goal=2 → Output: " + numSubarraysWithSumOptimal(nums1, goal1));
        System.out.println("Input: [0,0,0,0,0,0], goal=0 → Output: " + numSubarraysWithSumOptimal(nums2, goal2));

        System.out.println("\n========== OPTIMAL (Binary Array 2-Pointer Formula) ==========");
        System.out.println("Input: [1, 0, 0, 1, 1, 0], goal=2 → Output: " + numSubarraysWithSumBinaryOptimized(nums1, goal1));
        System.out.println("Input: [0,0,0,0,0,0], goal=0 → Output: " + numSubarraysWithSumBinaryOptimized(nums2, goal2));
    }
}
