//Kth element of 2 sorted Arrays
import java.util.*;

public class q12 {

    /* ------------------------------------------------------
       BRUTE FORCE APPROACH
       Steps:
       1. Merge both arrays into a new array
       2. Sort the merged array
       3. Return the k-th element (1-based index)
       Time Complexity: O((m+n) log(m+n))
       Space Complexity: O(m+n)
    ------------------------------------------------------ */
    public static int kthElementBrute(int[] nums1, int[] nums2, int k) {
        // Step 1: Create merged array
        int[] merged = new int[nums1.length + nums2.length];
        
        // Step 2: Copy elements from nums1
        for (int i = 0; i < nums1.length; i++) {
            merged[i] = nums1[i];
        }

        // Step 3: Copy elements from nums2
        for (int j = 0; j < nums2.length; j++) {
            merged[nums1.length + j] = nums2[j];
        }

        // Step 4: Sort merged array
        Arrays.sort(merged);

        // Step 5: Return k-th element (1-based index → k-1)
        return merged[k - 1];
    }

    /* ------------------------------------------------------
       BETTER APPROACH
       Steps:
       1. Use two pointers (like merge step in merge sort)
       2. Traverse until we reach k-th element
       3. Return that element
       Time Complexity: O(k)   (better than sorting)
       Space Complexity: O(1)
    ------------------------------------------------------ */
    public static int kthElementBetter(int[] nums1, int[] nums2, int k) {
        int i = 0, j = 0; // Pointers for nums1 and nums2
        int count = 0;    // Count elements traversed
        int answer = -1;  // To store the k-th element

        // Step 1: Traverse until we reach k-th element
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                answer = nums1[i];
                i++;
            } else {
                answer = nums2[j];
                j++;
            }
            count++;

            // Step 2: Stop once we hit k
            if (count == k) return answer;
        }

        // Step 3: If nums1 still has elements
        while (i < nums1.length) {
            answer = nums1[i];
            i++;
            count++;
            if (count == k) return answer;
        }

        // Step 4: If nums2 still has elements
        while (j < nums2.length) {
            answer = nums2[j];
            j++;
            count++;
            if (count == k) return answer;
        }

        return answer; // Should never reach if inputs are valid
    }

    /* ------------------------------------------------------
       OPTIMAL APPROACH (Binary Search on partitions)
       Steps:
       1. Always binary search on the smaller array
       2. Partition arrays such that total left = k
       3. Ensure max(left1, left2) ≤ min(right1, right2)
       4. Answer = max(left1, left2)
       Time Complexity: O(log(min(m,n)))
       Space Complexity: O(1)
    ------------------------------------------------------ */
    public static int kthElementOptimal(int[] nums1, int[] nums2, int k) {
        // Ensure nums1 is smaller
        if (nums1.length > nums2.length) {
            return kthElementOptimal(nums2, nums1, k);
        }

        int n1 = nums1.length, n2 = nums2.length;
        int low = Math.max(0, k - n2);   // At least take (k-n2) from nums1
        int high = Math.min(k, n1);      // At most take k from nums1

        // Step 1: Binary Search
        while (low <= high) {
            int cut1 = (low + high) / 2;      // Elements taken from nums1
            int cut2 = k - cut1;              // Elements taken from nums2

            // Handle edges
            int left1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int left2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];

            int right1 = (cut1 == n1) ? Integer.MAX_VALUE : nums1[cut1];
            int right2 = (cut2 == n2) ? Integer.MAX_VALUE : nums2[cut2];

            // Step 2: Valid partition check
            if (left1 <= right2 && left2 <= right1) {
                return Math.max(left1, left2); // k-th element
            } else if (left1 > right2) {
                high = cut1 - 1; // Too many elements from nums1
            } else {
                low = cut1 + 1;  // Too few elements from nums1
            }
        }

        return -1; // Should never happen
    }

    /* ------------------------------------------------------
       MAIN METHOD for Testing
    ------------------------------------------------------ */
    public static void main(String[] args) {
        int[] nums1 = {2, 3, 6, 7, 9};
        int[] nums2 = {1, 4, 8, 10};
        int k = 5;

        System.out.println("Brute Force: " + kthElementBrute(nums1, nums2, k));
        System.out.println("Better Approach: " + kthElementBetter(nums1, nums2, k));
        System.out.println("Optimal Approach: " + kthElementOptimal(nums1, nums2, k));
    }
}

