//Longest Subarray with sum K | [Postives and Negatives]
// OPTIMAL SOLUTION AS WE HAVE BEEN THROUGH THIS IN q03

import java.util.HashMap;
import java.util.Map;

class q4 {
    public static int getLongestSubarray(int[] a, long k) {
    int n = a.length;

    // A map to remember the first time we saw each prefix sum.
    // Key = prefix sum, Value = index where it was first seen.
    Map<Long, Integer> prefixSumMap = new HashMap<>();

    long sum = 0;    // This will store the running total as we move through the array
    int maxLen = 0;  // To store the maximum length of any subarray with sum = k

    for (int i = 0; i < n; i++) {
        sum += a[i];  // Add the current element to our running total

        // Case 1: If the total so far is equal to k, the subarray from start to i is valid
        if (sum == k) {
            maxLen = i + 1;
        }

        // Case 2: Check if there was a prefix sum 'sum - k' seen before
        // If so, then the subarray from (index after that) to current index sums to k
        long rem = sum - k;

        if (prefixSumMap.containsKey(rem)) {
            // Find the length of the subarray that adds up to k
            int len = i - prefixSumMap.get(rem);

            // Update the max length if this one is longer
            maxLen = Math.max(maxLen, len);
        }

        // Only record the first time we see each prefix sum.
        // Why? Because earlier prefix gives longer subarray.
        prefixSumMap.putIfAbsent(sum, i);
    }

    // Return the longest length we found
    return maxLen;
}

}
