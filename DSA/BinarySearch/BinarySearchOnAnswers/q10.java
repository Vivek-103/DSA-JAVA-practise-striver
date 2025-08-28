// Split Array - Problem
// Goal: Split the array into 'k' subarrays such that the largest subarray sum is minimized.

public class q10 {

    // ---------------- HELPER FUNCTION ---------------- //
    // Function to count how many partitions (subarrays) are required 
    // if we restrict the maximum allowed sum of any subarray to 'maxSum'
    // Time Complexity: O(n), where n = array length
    public static int countPartitions(int[] a, int maxSum) {
        int n = a.length;         // Length of array
        int partitions = 1;       // At least one subarray will always exist
        long subarraySum = 0;     // Keeps track of current subarray sum

        for (int i = 0; i < n; i++) {  
            // If adding a[i] does not exceed the maxSum, keep adding
            if (subarraySum + a[i] <= maxSum) {
                subarraySum += a[i];
            } else {
                // Otherwise, create a new partition starting from a[i]
                partitions++;
                subarraySum = a[i];
            }
        }
        return partitions;  // Return the total number of partitions
    }

    // ---------------- BRUTE FORCE APPROACH ---------------- //
    // Function to find the minimized largest subarray sum using brute force
    // Time Complexity: O(n * (sum(array) - max(array))) → very high, inefficient
    public static int largestSubarraySumMinimizedBrute(int[] a, int k) {
        int low = a[0];   // Minimum possible maxSum (at least the largest element)
        int high = 0;     // Maximum possible maxSum (sum of all elements)

        // Find 'low' = max element in array and 'high' = sum of array
        for (int i = 0; i < a.length; i++) {
            low = Math.max(low, a[i]);
            high += a[i];
        }

        // Try every possible maxSum between low and high
        for (int maxSum = low; maxSum <= high; maxSum++) {
            // If with this maxSum, we can split into exactly 'k' partitions
            if (countPartitions(a, maxSum) == k)
                return maxSum;   // This is the minimized largest sum
        }
        return low; // Fallback (should not happen in correct input)
    }

    // ---------------- OPTIMAL APPROACH ---------------- //
    // Function to find the minimized largest subarray sum using Binary Search
    // Time Complexity: O(n * log(sum(array))) → efficient
    public static int largestSubarraySumMinimizedOptimal(int[] a, int k) {
        int low = a[0];  // Minimum possible value of maxSum
        int high = 0;    // Maximum possible value of maxSum

        // Find 'low' = max element, 'high' = total sum
        for (int i = 0; i < a.length; i++) {
            low = Math.max(low, a[i]);
            high += a[i];
        }

        // Binary search between low and high
        while (low <= high) {
            int mid = (low + high) / 2; // Try a possible maximum subarray sum
            int partitions = countPartitions(a, mid); // How many partitions required?

            if (partitions > k) {
                // Too many partitions → maxSum too small → increase low
                low = mid + 1;
            } else {
                // Valid partitioning → try to minimize further
                high = mid - 1;
            }
        }
        return low; // 'low' will be the minimized largest subarray sum
    }

    // ---------------- MAIN METHOD ---------------- //
    public static void main(String[] args) {
        int[] arr = {7, 2, 5, 10, 8};
        int k = 2;

        // Brute Force Result
        int bruteAns = largestSubarraySumMinimizedBrute(arr, k);
        System.out.println("Brute Force Answer: " + bruteAns);

        // Optimal Result
        int optimalAns = largestSubarraySumMinimizedOptimal(arr, k);
        System.out.println("Optimal Answer: " + optimalAns);
    }
}
