/**
 * ===============================================================
 * 📌 CONVERT MAX HEAP TO MIN HEAP (JAVA)
 * ===============================================================
 *
 * Given an array representing a Max Heap,
 * convert it into a Min Heap IN-PLACE.
 *
 * ---------------------------------------------------------------
 * KEY OBSERVATION
 * ---------------------------------------------------------------
 * - Heap structure (Complete Binary Tree) remains unchanged
 * - Only the heap property needs to be fixed
 * - Apply Min Heapify from bottom to top
 *
 * ---------------------------------------------------------------
 * APPROACH (OPTIMAL)
 * ---------------------------------------------------------------
 * 1. Find last non-leaf node → (n / 2) - 1
 * 2. Perform Min Heapify starting from that node up to root
 *
 * ---------------------------------------------------------------
 * TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------------
 * Time Complexity  → O(n)
 * Space Complexity → O(1) (in-place)
 *
 * ---------------------------------------------------------------
 * THIS FILE CONTAINS
 * ---------------------------------------------------------------
 * ✔ In-place conversion
 * ✔ Min Heapify logic
 * ✔ Helper methods
 * ✔ Main method with example
 * ===============================================================
 */

public class MaxHeapToMinHeap {

    /* ==========================================================
       MIN HEAPIFY (CORE LOGIC)
       ========================================================== */

    /**
     * Ensures Min Heap property at given index
     *
     * @param arr Heap array
     * @param n   Size of heap
     * @param i   Current index
     */
    public static void minHeapify(int[] arr, int n, int i) {

        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Compare with left child
        if (left < n && arr[left] < arr[smallest]) {
            smallest = left;
        }

        // Compare with right child
        if (right < n && arr[right] < arr[smallest]) {
            smallest = right;
        }

        // If smallest is not root
        if (smallest != i) {
            swap(arr, i, smallest);
            minHeapify(arr, n, smallest);
        }
    }

    /* ==========================================================
       CONVERSION METHOD
       ========================================================== */

    /**
     * Converts Max Heap to Min Heap in-place
     *
     * @param arr Max Heap array
     */
    public static void convertMaxToMinHeap(int[] arr) {

        int n = arr.length;

        // Start heapifying from last non-leaf node
        for (int i = (n / 2) - 1; i >= 0; i--) {
            minHeapify(arr, n, i);
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

        System.out.println("=== CONVERT MAX HEAP TO MIN HEAP ===");

        int[] maxHeap = {50, 30, 40, 10, 5, 20};

        System.out.print("Original Max Heap: ");
        printArray(maxHeap);

        convertMaxToMinHeap(maxHeap);

        System.out.print("Converted Min Heap: ");
        printArray(maxHeap);
    }
}
