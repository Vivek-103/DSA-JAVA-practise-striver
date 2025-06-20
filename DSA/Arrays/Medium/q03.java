//Longest Subarray with given Sum K(Positives&0)

import java.util.*;
class q3 {
    // APPROACH 1 using 3 FOR-LOOP
    /*public static int getLongestSubarray(int []a, long k) {
        int n = a.length; // size of the array.

        int len = 0;
        for (int i = 0; i < n; i++) { // starting index
            for (int j = i; j < n; j++) { // ending index
                // add all the elements of
                // subarray = a[i...j]:
                long s = 0;
                for (int K = i; K <= j; K++) {
                    s += a[K];
                }

                if (s == k)
                    len = Math.max(len, j - i + 1);
            }
        }
        return len;
    }*/
    // APPROACH 2 using 2 FOR-LOOPS
    /*public static int getLongestSubarray(int []a, long k) {
        int n = a.length; // size of the array.

        int len = 0;
        for (int i = 0; i < n; i++) { // starting index
            long s = 0; // Sum variable
            for (int j = i; j < n; j++) { // ending index
                // add the current element to
                // the subarray a[i...j-1]:
                s += a[j];

                if (s == k)
                    len = Math.max(len, j - i + 1);
            }
        }
        return len;
    } */
   // Better Approach using Hashing 
   /*
    
   // Function to find the length of longest subarray with sum = k
    public static int getLongestSubarray(int[] a, long k) {
        int n = a.length;

        // Map to store (prefix sum, first index it occurred)
        Map<Long, Integer> preSumMap = new HashMap<>();
        long sum = 0; // To store prefix sum
        int maxLen = 0; // To track the max length of subarray

        for (int i = 0; i < n; i++) {
            sum += a[i]; // Add current element to prefix sum

            // Case 1: If prefix sum is equal to k, update maxLen
            if (sum == k) {
                maxLen = Math.max(maxLen, i + 1);
            }

            // Case 2: Check if there is a subarray sum = k
            // by checking if (sum - k) has occurred before
            long rem = sum - k;

            if (preSumMap.containsKey(rem)) {
                // If found, calculate the length of subarray
                int len = i - preSumMap.get(rem);
                maxLen = Math.max(maxLen, len); // Update maxLen
            }

            // Case 3: Store the prefix sum in the map
            // Only store the first occurrence to ensure the longest length
            if (!preSumMap.containsKey(sum)) {
                preSumMap.put(sum, i);
            }
        }

        return maxLen; // Return the final answer
    }

*/
// OPTIMAL APPROACH 2 POINTERS
     public static int getLongestSubarray(int[] a, long k) {
    int n = a.length;

    // Two pointers to maintain the window
    int left = 0, right = 0;

    // Start the sum with the first element
    long sum = a[0];

    // This variable will store the maximum length of the subarray with sum = k
    int maxLen = 0;

    // Traverse the array using right pointer
    while (right < n) {

        // If current sum is greater than k, shrink the window from the left
        while (left <= right && sum > k) {
            sum -= a[left]; // Subtract element at left
            left++;         // Move the left pointer forward
        }

        // If the sum becomes exactly k, update maxLen
        if (sum == k) {
            // (right - left + 1) gives the size of current subarray
            maxLen = Math.max(maxLen, right - left + 1);
        }

        // Move the right pointer to expand the window
        right++;

        // Add the next element to sum if right is within bounds
        if (right < n) {
            sum += a[right];
        }
    }

    // Return the length of the longest subarray found
    return maxLen;
}

    public static void main(String[] args) {
        int[] a = {2, 3, 5, 1, 9};
        long k = 10;
        int len = getLongestSubarray(a, k);
        System.out.println("The length of the longest subarray is: " + len);
    }

}
