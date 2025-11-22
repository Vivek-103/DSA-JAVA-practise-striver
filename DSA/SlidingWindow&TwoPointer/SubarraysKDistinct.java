//*************************************************************
// SUBARRAYS WITH EXACTLY K DISTINCT INTEGERS
// Approaches covered: Brute Force, Better, Optimal
// Fully commented for teaching purposes
//*************************************************************

import java.util.*;

public class SubarraysKDistinct {

    // =========================================================
    // MAIN METHOD: Test all approaches
    // =========================================================
    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 2, 3};
        int k = 2;

        System.out.println("Brute Force: " + countBruteForce(arr, k));
        System.out.println("Better Approach: " + countBetter(arr, k));
        System.out.println("Optimal Approach: " + countExactK(arr, k));
    }

    // =========================================================
    // 1️⃣ BRUTE FORCE → Try all subarrays, count distinct every time
    // =========================================================
    public static int countBruteForce(int[] arr, int k) {
        int n = arr.length;
        int count = 0;

        // Pick starting index
        for (int i = 0; i < n; i++) {

            // Pick ending index
            for (int j = i; j < n; j++) {

                // Use set to count distinct elements
                Set<Integer> set = new HashSet<>();

                // Add elements from subarray i to j
                for (int x = i; x <= j; x++) {
                    set.add(arr[x]);
                }

                // If exactly k distinct found
                if (set.size() == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /* TIME COMPLEXITY: O(n^3)
       SPACE COMPLEXITY: O(k)
    */


    // =========================================================
    // 2️⃣ BETTER APPROACH → Keep frequency map instead of recounting
    // =========================================================
    public static int countBetter(int[] arr, int k) {
        int n = arr.length;
        int count = 0;

        // Fix starting point
        for (int i = 0; i < n; i++) {

            // Map to store element frequency
            Map<Integer, Integer> freq = new HashMap<>();

            // Expand j
            for (int j = i; j < n; j++) {
                freq.put(arr[j], freq.getOrDefault(arr[j], 0) + 1);

                // If exactly k distinct
                if (freq.size() == k) count++;

                // If more than k, break
                if (freq.size() > k) break;
            }
        }
        return count;
    }

    /* TIME COMPLEXITY: O(n^2)
       SPACE COMPLEXITY: O(k)
    */


    // =========================================================
    // 3️⃣ OPTIMAL APPROACH → EXACT(K) = ATMOST(K) - ATMOST(K-1)
    // Sliding window technique
    // =========================================================
    public static int countExactK(int[] arr, int k) {
        return countAtMostK(arr, k) - countAtMostK(arr, k - 1);
    }

    // =========================================================
    // Helper: Count subarrays with at most K distinct numbers
    // =========================================================
    public static int countAtMostK(int[] arr, int k) {
        int left = 0, count = 0;
        Map<Integer, Integer> map = new HashMap<>();

        // Expand right pointer
        for (int right = 0; right < arr.length; right++) {

            // Add current element
            map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);

            // If distinct elements exceed k → shrink window
            while (map.size() > k) {
                map.put(arr[left], map.get(arr[left]) - 1);

                // Remove if count becomes zero
                if (map.get(arr[left]) == 0)
                    map.remove(arr[left]);

                left++; // move window
            }

            // Add number of valid subarrays ending at 'right'
            count += (right - left + 1);
        }
        return count;
    }

    /* TIME COMPLEXITY: O(n)
       SPACE COMPLEXITY: O(k)
    */

}

//*************************************************************
// 📌 VISUALIZATION (Example: arr = [1,2,1,2,3], k=2)
// We compute:
// EXACT(2) = ATMOST(2) - ATMOST(1)
//
// ATMOST(2) count steps:
// Window expansions:
//
/*
Right=0 → [1] → {1}=1 distinct → count=1
Right=1 → [1,2] → {1,2}=2 → count+=2 → 3
Right=2 → [1,2,1] → {1,2}=2 → count+=3 → 6
Right=3 → [1,2,1,2] → {1,2}=2 → count+=4 → 10
Right=4 → [1,2,1,2,3] → {1,2,3}=3 → shrink to remove extras
          shrink → window=[2,1,2,3] → still 3
          shrink → window=[1,2,3] → still 3
          shrink → window=[2,3] → 2 distinct
→ count+=2 → final ATMOST(2)=12
*/
//
// ATMOST(1) computed similarly = 5
//
// EXACT(2) = 12 - 5 = 7
//
// Final Answer = 7 subarrays
//*************************************************************
