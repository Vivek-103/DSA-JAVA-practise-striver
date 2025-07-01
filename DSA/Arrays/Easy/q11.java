//Find the Majority Element that occurs more than N/2 times
//Problem Statement: Given an array of N integers, write a program to return an element that occurs more than N/2 times in the given array. You may consider that such an element always exists in the array.

 
import java.util.HashMap;

public class q11 {

    // ----------- 1. Naive Approach (Brute Force) -----------
    // Time: O(N^2), Space: O(1)
    public static int majorityElementNaive(int[] arr) {
        int n = arr.length;

        // Outer loop picks each element one by one
        for (int i = 0; i < n; i++) {
            int count = 0;

            // Inner loop counts how many times arr[i] occurs
            for (int j = 0; j < n; j++) {
                if (arr[j] == arr[i]) {
                    count++;
                }
            }

            // If this count is more than n/2, it's the majority element
            if (count > n / 2) {
                return arr[i];
            }
        }

        // This line won't be reached because problem guarantees existence
        return -1;
    }

    // ----------- 2. Better Approach using HashMap -----------
    // Time: O(N), Space: O(N)
    public static int majorityElementBetter(int[] arr) {
        int n = arr.length;

        // Create a hashmap to store frequencies of elements
        HashMap<Integer, Integer> map = new HashMap<>();

        // Traverse the array and populate the map
        for (int num : arr) {
            // Increment count if already exists, else set to 1
            map.put(num, map.getOrDefault(num, 0) + 1);

            // If count exceeds n/2, return that element
            if (map.get(num) > n / 2) {
                return num;
            }
        }

        // Won’t reach here due to the problem’s guarantee
        return -1;
    }

    // ----------- 3. Optimal Approach: Moore's Voting Algorithm -----------
    // Time: O(N), Space: O(1)
    public static int majorityElementOptimal(int[] arr) {
        int count = 0;        // Count of the current candidate
        int candidate = -1;   // Potential majority element

        // Phase 1: Find a candidate
        for (int num : arr) {
            // If count is 0, choose current number as candidate
            if (count == 0) {
                candidate = num;
            }

            // If num matches candidate, increment count
            if (num == candidate) {
                count++;
            } else {
                // Otherwise, decrement count
                count--;
            }
        }

        // Phase 2: Optional verification step (since element is guaranteed)
        count = 0;
        for (int num : arr) {
            if (num == candidate) {
                count++;
            }
        }

        // If candidate appears more than n/2 times, return it
        if (count > arr.length / 2) {
            return candidate;
        }

        // Fallback, though won't be used here
        return -1;
    }

    // ----------- Main Method for Testing All Approaches -----------
    public static void main(String[] args) {
        int[] arr = {2, 2, 1, 2, 3, 2, 2}; // Example input

        System.out.println("Naive Approach Result: " + majorityElementNaive(arr));
        System.out.println("Better Approach Result: " + majorityElementBetter(arr));
        System.out.println("Optimal Approach Result: " + majorityElementOptimal(arr));
    }
}
