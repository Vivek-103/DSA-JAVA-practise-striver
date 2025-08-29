//Median of Two Sorted Arrays
import java.util.*;

public class q11 {

    /* ------------------------------------------------------
       BRUTE FORCE APPROACH
       Steps:
       1. Merge both arrays into a new array
       2. Sort the merged array
       3. Return the median element(s)
       Time Complexity: O((m+n) log(m+n))
       Space Complexity: O(m+n)
    ------------------------------------------------------ */
    public static double findMedianBrute(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[] merged = new int[n + m];

        // Copy nums1 into merged
        for (int i = 0; i < n; i++) merged[i] = nums1[i];

        // Copy nums2 into merged
        for (int j = 0; j < m; j++) merged[n + j] = nums2[j];

        // Sort the merged array
        Arrays.sort(merged);

        int len = merged.length;
        if (len % 2 == 1) {
            return merged[len / 2]; // Odd length → middle element
        } else {
            return (merged[len / 2] + merged[(len / 2) - 1]) / 2.0; // Even length → average of two middle
        }
    }

    /* ------------------------------------------------------
       BETTER APPROACH
       Steps:
       1. Use two pointers to merge arrays logically
       2. Stop once we reach the median position
       3. Return the median
       Time Complexity: O(m+n)
       Space Complexity: O(1)
    ------------------------------------------------------ */
    public static double findMedianBetter(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int total = n + m;

        int i = 0, j = 0;       // Pointers
        int count = 0;          // Count of merged elements
        int prev = -1, curr = -1;

        // Merge until we reach the middle element
        while (count <= total / 2) {
            prev = curr;

            if (i < n && (j >= m || nums1[i] <= nums2[j])) {
                curr = nums1[i];
                i++;
            } else {
                curr = nums2[j];
                j++;
            }

            count++;
        }

        if (total % 2 == 1) {
            return curr; // Odd length → middle element
        } else {
            return (prev + curr) / 2.0; // Even length → average of two middle
        }
    }

    /* ------------------------------------------------------
       OPTIMAL APPROACH (Binary Search)
       Steps:
       1. Perform binary search on the smaller array
       2. Partition both arrays
       3. Ensure left side <= right side
       4. Return median accordingly
       Time Complexity: O(log(min(m,n)))
       Space Complexity: O(1)
    ------------------------------------------------------ */
    public static double findMedianOptimal(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianOptimal(nums2, nums1); // Ensure nums1 is smaller
        }

        int n1 = nums1.length, n2 = nums2.length;
        int low = 0, high = n1;

        while (low <= high) {
            int cut1 = (low + high) / 2;
            int cut2 = (n1 + n2 + 1) / 2 - cut1;

            int left1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int left2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];

            int right1 = (cut1 == n1) ? Integer.MAX_VALUE : nums1[cut1];
            int right2 = (cut2 == n2) ? Integer.MAX_VALUE : nums2[cut2];

            if (left1 <= right2 && left2 <= right1) {
                if ((n1 + n2) % 2 == 1) {
                    return Math.max(left1, left2);
                } else {
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                }
            } else if (left1 > right2) {
                high = cut1 - 1; // Too many from nums1
            } else {
                low = cut1 + 1; // Too few from nums1
            }
        }

        return 0.0; // Should never happen
    }

    /* ------------------------------------------------------
       MAIN METHOD for Testing
    ------------------------------------------------------ */
    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};

        System.out.println("Brute Force Median: " + findMedianBrute(nums1, nums2));
        System.out.println("Better Approach Median: " + findMedianBetter(nums1, nums2));
        System.out.println("Optimal Approach Median: " + findMedianOptimal(nums1, nums2));

        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};

        System.out.println("\nBrute Force Median: " + findMedianBrute(nums3, nums4));
        System.out.println("Better Approach Median: " + findMedianBetter(nums3, nums4));
        System.out.println("Optimal Approach Median: " + findMedianOptimal(nums3, nums4));
    }
}
