/**
 * ===============================================================
 * 📌 CONVERT MIN HEAP TO MAX HEAP (JAVA)
 * ===============================================================
 *
 * Given an array representing a Min Heap,
 * convert it into a Max Heap IN-PLACE.
 *
 * ---------------------------------------------------------------
 * KEY OBSERVATION
 * ---------------------------------------------------------------
 * - Heap structure (Complete Binary Tree) remains same
 * - Only heap property changes
 * - Apply Max Heapify from bottom to top
 *
 * ---------------------------------------------------------------
 * APPROACH (OPTIMAL)
 * ---------------------------------------------------------------
 * 1. Find last non-leaf node → (n / 2) - 1
 * 2. Perform Max Heapify from that node down to root
 *
 * ---------------------------------------------------------------
 * TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------------
 * Time Complexity  → O(n)
 * Space Complexity → O(1)
 *
 * ---------------------------------------------------------------
 * THIS FILE CONTAINS
 * ---------------------------------------------------------------
 * ✔ In-place conversion
 * ✔ Max Heapify logic
 * ✔ Helper methods
 * ✔ Main method with example
 * ===============================================================
 */

public class MinHeapToMaxHeap {

    /* ==========================================================
       MAX HEAPIFY (CORE LOGIC)
       ========================================================== */

    /**
     * Ensures Max Heap property at given index
     *
     * @param arr Heap array
     * @param n   Size of heap
     * @param i   Current index
     */
    public static void maxHeapify(int[] arr, int n, int i) {

        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Compare with left child
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // Compare with right child
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            swap(arr, i, largest);
            maxHeapify(arr, n, largest);
        }
    }

    /* ==========================================================
       CONVERSION METHOD
       ========================================================== */

    /**
     * Converts Min Heap to Max Heap in-place
     *
     * @param arr Min Heap array
     */
    public static void convertMinToMaxHeap(int[] arr) {

        int n = arr.length;

        // Start heapifying from last non-leaf node
        for (int i = (n / 2) - 1; i >= 0; i--) {
            maxHeapify(arr, n, i);
        }
    }

    /* ==========================================================
       SWAP UTILITY
       ========================================================== */

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /* ==========================================================
       PRINT UTILITY
       ========================================================== */

    private static void printArray(int[] arr) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    /* ==========================================================
       MAIN METHOD (DEMO)
       ========================================================== */

    public static void main(String[] args) {

        System.out.println("=== CONVERT MIN HEAP TO MAX HEAP ===");

        int[] minHeap = {1, 3, 5, 7, 9, 8};

        System.out.print("Original Min Heap: ");
        printArray(minHeap);

        convertMinToMaxHeap(minHeap);

        System.out.print("Converted Max Heap: ");
        printArray(minHeap);
    }
}
