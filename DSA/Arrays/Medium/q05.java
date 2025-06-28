// 2 sum problem
import java.util.HashMap;

public class q5 {
    
    // 1. Brute Force Approach
    public static int[] twoSumBruteForce(int[] nums, int target) {
        // We check all possible pairs (i, j) such that i < j
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                // If the pair adds to the target, return their indices
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1}; // If no solution is found
    }

        // 2. HashMap (Better) Approach
    public static int[] twoSumHashing(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // We loop through the array
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; // What do we need to complete the pair?
            if (map.containsKey(complement)) {
                // If we've already seen the complement, return both indices
                return new int[]{map.get(complement), i};
            }
            // Otherwise, store the current value and its index
            map.put(nums[i], i);
        }
        return new int[]{-1, -1}; // If no solution
    }


        // 3. Optimized Approach using Two Pointers (only if array is sorted or can be sorted)
    public static int[] twoSumOptimized(int[] nums, int target) {
        // Copy original array and sort it to apply two-pointer
        int[] sorted = nums.clone();
        java.util.Arrays.sort(sorted);

        int left = 0;
        int right = sorted.length - 1;

        while (left < right) {
            int sum = sorted[left] + sorted[right];
            if (sum == target) {
                // Now map back to original indices
                int index1 = -1, index2 = -1;
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] == sorted[left] && index1 == -1) {
                        index1 = i;
                    } else if (nums[i] == sorted[right] && index2 == -1) {
                        index2 = i;
                    }
                }
                return new int[]{index1, index2};
            } else if (sum < target) {
                left++; // Move right to get larger sum
            } else {
                right--; // Move left to get smaller sum
            }
        }
        return new int[]{-1, -1};
    }


        public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] res1 = twoSumBruteForce(nums, target);
        System.out.println("Brute Force Result: [" + res1[0] + ", " + res1[1] + "]");

        int[] res2 = twoSumHashing(nums, target);
        System.out.println("Hashing Result: [" + res2[0] + ", " + res2[1] + "]");

        int[] res3 = twoSumOptimized(nums, target);
        System.out.println("Optimized Result: [" + res3[0] + ", " + res3[1] + "]");
    }
}

