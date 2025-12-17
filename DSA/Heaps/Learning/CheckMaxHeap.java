/**
 * ===============================================================
 * 📌 CHECK IF AN ARRAY REPRESENTS A MAX HEAP (JAVA)
 * ===============================================================
 *
 * A Max Heap must satisfy TWO properties:
 *
 * 1️⃣ Complete Binary Tree Property
 *    - Automatically satisfied when heap is stored as an array
 *
 * 2️⃣ Max Heap Property
 *    - For every parent node i:
 *        arr[i] >= arr[leftChild]
 *        arr[i] >= arr[rightChild]
 *
 * ---------------------------------------------------------------
 * ARRAY INDEX RULES
 * ---------------------------------------------------------------
 * Parent index     → (i - 1) / 2
 * Left child index → 2 * i + 1
 * Right child index→ 2 * i + 2
 *
 * ---------------------------------------------------------------
 * KEY INSIGHT
 * ---------------------------------------------------------------
 * - Leaf nodes always satisfy heap property
 * - Only NON-LEAF nodes need to be checked
 * - Last non-leaf node index = (n / 2) - 1
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
 * ✔ Iterative approach (Optimal)
 * ✔ Recursive approach (For understanding)
 * ✔ Main method with test cases
 * ===============================================================
 */

public class CheckMaxHeap {

    /* ==========================================================
       METHOD 1: ITERATIVE APPROACH (RECOMMENDED)
       ========================================================== */

    /**
     * Checks if the given array is a Max Heap
     *
     * @param arr Input array
     * @param n   Number of elements
     * @return true if array is Max Heap, else false
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static boolean isMaxHeap(int[] arr, int n) {

        // Check only non-leaf nodes
        for (int i = 0; i <= (n / 2) - 1; i++) {

            int left = 2 * i + 1;
            int right = 2 * i + 2;

            // If left child exists and is greater than parent
            if (left < n && arr[i] < arr[left]) {
                return false;
            }

            // If right child exists and is greater than parent
            if (right < n && arr[i] < arr[right]) {
                return false;
            }
        }

        return true;
    }

    /* ==========================================================
       METHOD 2: RECURSIVE APPROACH (LEARNING PURPOSE)
       ========================================================== */

    /**
     * Recursive method to check Max Heap property
     *
     * @param arr Input array
     * @param i   Current index
     * @param n   Size of array
     *
     * Time Complexity: O(n)
     * Space Complexity: O(log n) due to recursion stack
     */
    public static boolean isMaxHeapRecursive(int[] arr, int i, int n) {

        // Base case: if leaf node
        if (i >= n / 2) {
            return true;
        }

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Check max heap property at current node
        if ((left < n && arr[i] < arr[left]) ||
            (right < n && arr[i] < arr[right])) {
            return false;
        }

        // Recur for left and right subtree
        return isMaxHeapRecursive(arr, left, n)
                && isMaxHeapRecursive(arr, right, n);
    }

    /* ==========================================================
       MAIN METHOD (TEST CASES)
       ========================================================== */

    public static void main(String[] args) {

        System.out.println("=== CHECK MAX HEAP ===");

        // Example 1: Valid Max Heap
        int[] heap1 = {50, 30, 40, 10, 5, 20};

        // Example 2: Not a Max Heap
        int[] heap2 = {50, 30, 45, 60, 5, 20};

        System.out.println("heap1 (Iterative): "
                + isMaxHeap(heap1, heap1.length));

        System.out.println("heap1 (Recursive): "
                + isMaxHeapRecursive(heap1, 0, heap1.length));

        System.out.println("heap2 (Iterative): "
                + isMaxHeap(heap2, heap2.length));

        System.out.println("heap2 (Recursive): "
                + isMaxHeapRecursive(heap2, 0, heap2.length));
    }
}
