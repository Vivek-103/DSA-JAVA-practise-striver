/**
 * ===============================================================
 * 📌 FIND Kth SMALLEST & Kth LARGEST ELEMENT IN AN ARRAY (JAVA)
 * ===============================================================
 *
 * This program demonstrates how to find:
 *  - Kth Smallest element
 *  - Kth Largest element
 *
 * using Heap Data Structure.
 *
 * ---------------------------------------------------------------
 * WHY HEAP?
 * ---------------------------------------------------------------
 * Heaps provide efficient way to retrieve Kth elements:
 *
 * Kth Smallest  → Max Heap (size k)
 * Kth Largest   → Min Heap (size k)
 *
 * ---------------------------------------------------------------
 * TIME COMPLEXITY
 * ---------------------------------------------------------------
 * Building heap      → O(k)
 * Processing n items → O((n - k) log k)
 * Overall            → O(n log k)
 *
 * ---------------------------------------------------------------
 * SPACE COMPLEXITY
 * ---------------------------------------------------------------
 * Heap array → O(k)
 *
 * ---------------------------------------------------------------
 * THIS FILE CONTAINS
 * ---------------------------------------------------------------
 * ✔ kthSmallest() using Max Heap
 * ✔ kthLargest()  using Min Heap
 * ✔ Helper print methods
 * ✔ Main() with example input
 *
 * ===============================================================
 */

import java.util.PriorityQueue;
import java.util.Collections;

public class KthSmallestLargest {

    /* ==========================================================
       Kth SMALLEST using MAX HEAP
       ========================================================== */

    /**
     * Returns Kth smallest element in array
     *
     * Logic:
     *  → Maintain a Max Heap of size k
     *  → Keep only k smallest elements in heap
     *  → Root of Max Heap = kth smallest
     *
     * @param arr Input array
     * @param k   Position k
     * @return Kth smallest element
     */
    public static int kthSmallest(int[] arr, int k) {

        // Max Heap for smallest element
        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(Collections.reverseOrder());

        for (int val : arr) {
            maxHeap.add(val);

            // Keep heap size = k
            if (maxHeap.size() > k) {
                maxHeap.poll(); // remove largest among stored
            }
        }

        return maxHeap.peek(); // root is kth smallest
    }

    /* ==========================================================
       Kth LARGEST using MIN HEAP
       ========================================================== */

    /**
     * Returns Kth largest element in array
     *
     * Logic:
     *  → Maintain a Min Heap of size k
     *  → Keep only k largest elements in heap
     *  → Root of Min Heap = kth largest
     *
     * @param arr Input array
     * @param k   Position k
     * @return Kth largest element
     */
    public static int kthLargest(int[] arr, int k) {

        // Min Heap for largest element
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int val : arr) {
            minHeap.add(val);

            // Keep heap size = k
            if (minHeap.size() > k) {
                minHeap.poll(); // remove smallest among stored
            }
        }

        return minHeap.peek(); // root is kth largest
    }

    /* ==========================================================
       MAIN METHOD (DEMO)
       ========================================================== */

    public static void main(String[] args) {

        int[] arr = {7, 10, 4, 3, 20, 15};

        int k1 = 3;
        int k2 = 4;

        System.out.println("=== Kth Smallest & Largest Demo ===");

        System.out.print("Array: ");
        for (int i : arr) System.out.print(i + " ");
        System.out.println();

        System.out.println(k1 + "rd Smallest: " + kthSmallest(arr, k1));
        System.out.println(k2 + "th Largest: " + kthLargest(arr, k2));
    }
}
