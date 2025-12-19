/**
 * ============================================================================
 * 📌 REPLACE ELEMENTS BY THEIR RANK IN THE ARRAY (JAVA)
 * ============================================================================
 *
 * PROBLEM:
 * --------
 * Given an array of integers, replace each element with its rank.
 *
 * Rank definition:
 * - Rank is based on the sorted order (smallest → rank 1)
 * - Duplicate values receive the same rank
 *
 * Example:
 * ----------
 * Input:  [20, 15, 26, 2, 98, 6]
 * Sorted: [2, 6, 15, 20, 26, 98]
 *
 * Output: [4, 3, 5, 1, 6, 2]
 *
 * ---------------------------------------------------------------
 * APPROACH:
 * ---------------------------------------------------------------
 * 1. Copy array into another array
 * 2. Sort copied array
 * 3. Assign rank to each unique value
 * 4. Replace each original element using rank map
 *
 * ---------------------------------------------------------------
 * TIME COMPLEXITY: O(n log n)
 * SPACE COMPLEXITY: O(n)
 * ---------------------------------------------------------------
 */

import java.util.Arrays;
import java.util.HashMap;

public class ReplaceElementsByRank {

    /**
     * Method to replace elements with their rank
     */
    public static void replaceWithRank(int[] arr) {

        // Step 1: Create a copy of the original array
        int[] temp = Arrays.copyOf(arr, arr.length);   // copied so original remains intact

        // Step 2: Sort the copied array
        Arrays.sort(temp);                              // sorting gives ascending order

        // Step 3: Create a HashMap to store rank of each unique value
        HashMap<Integer, Integer> rankMap = new HashMap<>(); // map: value -> rank

        int rank = 1;  // rank starts at 1 (not 0)

        // Step 4: Assign rank for each unique sorted value
        for (int i = 0; i < temp.length; i++) {         // iterate sorted array
            if (!rankMap.containsKey(temp[i])) {        // avoid updating duplicate keys
                rankMap.put(temp[i], rank);             // store rank for value
                rank++;                                 // increment rank for next
            }
        }

        // Step 5: Replace original array elements using rank map
        for (int i = 0; i < arr.length; i++) {          // iterate original array
            arr[i] = rankMap.get(arr[i]);               // replace element with its rank
        }
    }

    /**
     * Utility method to print an array
     */
    public static void printArray(int[] arr) {
        for (int x : arr) {                             // iterate through array
            System.out.print(x + " ");                  // print each element
        }
        System.out.println();                           // line break
    }

    /**
     * MAIN METHOD → Demo
     */
    public static void main(String[] args) {

        System.out.println("=== REPLACE ELEMENTS BY RANK DEMO ===");

        // input array
        int[] arr = {20, 15, 26, 2, 98, 6};

        System.out.print("Original array: ");
        printArray(arr);                                // display original

        replaceWithRank(arr);                           // replace values by rank

        System.out.print("Ranked array:   ");
        printArray(arr);                                // display result
    }
}
