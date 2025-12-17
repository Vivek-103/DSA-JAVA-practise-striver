/**
 * ===============================================================
 * 📌 CHECK IF AN ARRAY REPRESENTS A MIN HEAP (JAVA)
 * ===============================================================
 *
 * A Min Heap must satisfy TWO properties:
 *
 * 1️⃣ Complete Binary Tree Property
 *    - When stored as an array, this is ALWAYS satisfied
 *
 * 2️⃣ Min Heap Property
 *    - For every node i:
 *      arr[i] <= arr[leftChild]
 *      arr[i] <= arr[rightChild]
 *
 * ---------------------------------------------------------------
 * ARRAY INDEX RULES
 * ---------------------------------------------------------------
 * Parent index     → (i - 1) / 2
 * Left child index → 2 * i + 1
 * Right child index→ 2 * i + 2
 *
 * ---------------------------------------------------------------
 * APPROACH
 * ---------------------------------------------------------------
 * - Only NON-LEAF nodes need to be checked
 * - Last non-leaf node index = (n / 2) - 1
 * - Compare each parent with its children
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
 * ✔ Main method with examples
 * ===============================================================
 */

public class CheckMinHeap {

    /* ==========================================================
       METHOD 1: ITERATIVE CHECK 
       ========================================================== */

    /**
     * Checks if given array represents a Min Heap
     *
     * @param arr Input array
     * @param n   Number of elements
     * @return true if Min Heap, false otherwise
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static boolean isMinHeap(int[] arr, int n) {

        // Only need to check non-leaf nodes
        for (int i = 0; i <= (n / 2) - 1; i++) {

            int left = 2 * i + 1;
            int right = 2 * i + 2;

            // If left child exists and is smaller than parent
            if (left < n && arr[i] > arr[left]) {
                return false;
            }

            // If right child exists and is smaller than parent
            if (right < n && arr[i] > arr[right]) {
                return false;
            }
        }

        return true;
    }

    /* ==========================================================
       METHOD 2: RECURSIVE CHECK 
       ========================================================== */

    /**
     * Recursive method to check Min Heap property
     *
     * Time Complexity: O(n)
     * Space Complexity: O(log n) (recursion stack)
     */
    public static boolean isMinHeapRecursive(int[] arr, int i, int n) {

        // If leaf node, return true
        if (i >= n / 2) {
            return true;
        }

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Check heap property at current node
        if ((left < n && arr[i] > arr[left]) ||
            (right < n && arr[i] > arr[right])) {
            return false;
        }

        // Recur for left and right subtree
        return isMinHeapRecursive(arr, left, n) &&
               isMinHeapRecursive(arr, right, n);
    }

    /* ==========================================================
       MAIN METHOD (TEST CASES)
       ========================================================== */

    public static void main(String[] args) {

        // Example 1: Valid Min Heap
        int[] heap1 = {1, 3, 5, 7, 9, 8};

        // Example 2: Not a Min Heap
        int[] heap2 = {10, 15, 14, 25, 30, 12};

        System.out.println("=== CHECK MIN HEAP ===");

        System.out.println("heap1 (Iterative): " +
                isMinHeap(heap1, heap1.length));

        System.out.println("heap1 (Recursive): " +
                isMinHeapRecursive(heap1, 0, heap1.length));

        System.out.println("heap2 (Iterative): " +
                isMinHeap(heap2, heap2.length));

        System.out.println("heap2 (Recursive): " +
                isMinHeapRecursive(heap2, 0, heap2.length));
    }
}
