/**
 * ===============================================================
 * 📌 SORT A K-SORTED ARRAY (JAVA)
 * ===============================================================
 *
 * PROBLEM:
 * ---------
 * You are given an array where each element is at most `k` positions
 * away from its correct sorted position.
 *
 * Example:
 *     arr = [6, 5, 3, 2, 8, 10, 9],  k = 3
 *
 * In a fully sorted array:
 *     [2, 3, 5, 6, 8, 9, 10]
 *
 * ---------------------------------------------------------------
 * WHY MIN HEAP?
 * ---------------------------------------------------------------
 * Since each element is at most k away from correct position:
 *
 * → At index 0, the correct smallest element must be among the
 *   first (k+1) elements.
 *
 * → So push first k+1 elements in a Min Heap.
 *
 * → Repeatedly extract min & push next element.
 *
 * ---------------------------------------------------------------
 * TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------------
 * Time Complexity  :  O(n log k)
 * Space Complexity :  O(k)
 *
 * ---------------------------------------------------------------
 * THIS FILE CONTAINS
 * ---------------------------------------------------------------
 * ✔ sortKSortedArray() implementation
 * ✔ detailed documentation
 * ✔ helper print function
 * ✔ demo main method
 *
 * ===============================================================
 */

import java.util.PriorityQueue;

public class SortKSortedArray {

    /**
     * Sorts a k-sorted array using Min Heap
     *
     * @param arr Input k-sorted array
     * @param k   Max distance from sorted position
     */
    public static void sortKSortedArray(int[] arr, int k) {

        // Min Heap to get smallest element quickly
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int index = 0;

        // Step 1: Push first k+1 elements into heap
        for (int i = 0; i < Math.min(k + 1, arr.length); i++) {
            minHeap.add(arr[i]);
        }

        // Step 2: Process remaining elements
        for (int i = k + 1; i < arr.length; i++) {
            arr[index++] = minHeap.poll();
            minHeap.add(arr[i]);
        }

        // Step 3: Empty remaining heap
        while (!minHeap.isEmpty()) {
            arr[index++] = minHeap.poll();
        }
    }

    /* ==========================================================
       HELPER FUNCTION: Print array
       ========================================================== */
    public static void printArray(int[] arr) {
        for (int val : arr)
            System.out.print(val + " ");
        System.out.println();
    }

    /* ==========================================================
       MAIN METHOD (DEMO)
       ========================================================== */
    public static void main(String[] args) {

        System.out.println("=== SORT K-SORTED ARRAY DEMO ===");

        int[] arr = {6, 5, 3, 2, 8, 10, 9};
        int k = 3;

        System.out.print("Original array: ");
        printArray(arr);

        sortKSortedArray(arr, k);

        System.out.print("Sorted array:   ");
        printArray(arr);
    }
}
