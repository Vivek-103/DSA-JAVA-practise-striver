/**
 * ===============================================================
 * 📌 MIN HEAP IMPLEMENTATION IN JAVA (FROM SCRATCH)
 * ===============================================================
 *
 * A Min Heap is a Complete Binary Tree where:
 * - The value of each node is LESS THAN or EQUAL to its children.
 * - The minimum element is always present at the ROOT.
 *
 * ---------------------------------------------------------------
 * WHY HEAP?
 * ---------------------------------------------------------------
 * - Efficient priority-based data access
 * - Used in:
 *   ✔ Priority Queue
 *   ✔ Heap Sort
 *   ✔ Dijkstra’s Algorithm
 *   ✔ Kth Largest / Smallest problems
 *
 * ---------------------------------------------------------------
 * REPRESENTATION
 * ---------------------------------------------------------------
 * A heap is efficiently stored using an ARRAY.
 *
 * For index i:
 * Parent index     → (i - 1) / 2
 * Left child index → 2 * i + 1
 * Right child index→ 2 * i + 2
 *
 * ---------------------------------------------------------------
 * This file contains:
 * ✔ Min Heap Implementation
 * ✔ Insert
 * ✔ Extract Min (Delete)
 * ✔ Peek
 * ✔ Heapify Up
 * ✔ Heapify Down
 * ✔ Build Heap
 * ✔ Heap Sort
 * ✔ Time & Space Complexity
 * ✔ Main Method with examples
 * ===============================================================
 */

import java.util.Arrays;

public class MinHeap {

    /* ==========================================================
       INTERNAL DATA STRUCTURE
       ========================================================== */

    private int[] heap;   // Array to store heap elements
    private int size;     // Current number of elements
    private int capacity; // Maximum capacity of heap

    /* ==========================================================
       CONSTRUCTOR
       ========================================================== */

    /**
     * Initializes a Min Heap with given capacity
     *
     * Time Complexity: O(1)
     * Space Complexity: O(n)
     */
    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
    }

    /* ==========================================================
       INDEX CALCULATION HELPERS
       ========================================================== */

    /**
     * Returns parent index
     */
    private int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * Returns left child index
     */
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * Returns right child index
     */
    private int rightChild(int i) {
        return 2 * i + 2;
    }

    /* ==========================================================
       SWAP UTILITY
       ========================================================== */

    /**
     * Swaps two elements in heap
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /* ==========================================================
       INSERT OPERATION
       ========================================================== */

    /**
     * Inserts a value into Min Heap
     *
     * Steps:
     * 1. Insert element at end
     * 2. Restore heap property using Heapify Up
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public void insert(int value) {

        if (size == capacity) {
            throw new IllegalStateException("Heap Overflow");
        }

        // Step 1: Insert at end
        heap[size] = value;
        size++;

        // Step 2: Fix heap property
        heapifyUp(size - 1);
    }

    /**
     * Heapify Up (Bottom → Top)
     * Used after insertion
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    private void heapifyUp(int index) {

        while (index > 0 && heap[parent(index)] > heap[index]) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    /* ==========================================================
       EXTRACT MIN (DELETE)
       ========================================================== */

    /**
     * Removes and returns the minimum element from heap
     *
     * Steps:
     * 1. Save root value
     * 2. Replace root with last element
     * 3. Reduce size
     * 4. Heapify Down
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public int extractMin() {

        if (size == 0) {
            throw new IllegalStateException("Heap Underflow");
        }

        // If only one element
        if (size == 1) {
            size--;
            return heap[0];
        }

        int min = heap[0];

        // Replace root with last element
        heap[0] = heap[size - 1];
        size--;

        // Restore heap property
        heapifyDown(0);

        return min;
    }

    /**
     * Heapify Down (Top → Bottom)
     * Used after deletion
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    private void heapifyDown(int index) {

        int smallest = index;
        int left = leftChild(index);
        int right = rightChild(index);

        if (left < size && heap[left] < heap[smallest]) {
            smallest = left;
        }

        if (right < size && heap[right] < heap[smallest]) {
            smallest = right;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    /* ==========================================================
       PEEK OPERATION
       ========================================================== */

    /**
     * Returns minimum element without removing it
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int peek() {

        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        return heap[0];
    }

    /* ==========================================================
       BUILD HEAP
       ========================================================== */

    /**
     * Builds a Min Heap from an unsorted array
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void buildHeap(int[] arr) {

        if (arr.length > capacity) {
            throw new IllegalArgumentException("Input array exceeds heap capacity");
        }

        heap = Arrays.copyOf(arr, capacity);
        size = arr.length;

        // Heapify from last non-leaf node
        for (int i = parent(size - 1); i >= 0; i--) {
            heapifyDown(i);
        }
    }

    /* ==========================================================
       HEAP SORT
       ========================================================== */

    /**
     * Sorts heap elements using Heap Sort
     *
     * Time Complexity: O(n log n)
     * Space Complexity: O(1)
     */
    public void heapSort() {

        int originalSize = size;

        for (int i = size - 1; i >= 0; i--) {
            swap(0, i);
            size--;
            heapifyDown(0);
        }

        size = originalSize;
    }

    /* ==========================================================
       DISPLAY HEAP
       ========================================================== */

    /**
     * Prints heap elements
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void printHeap() {

        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    /* ==========================================================
       MAIN METHOD (TESTING)
       ========================================================== */

    public static void main(String[] args) {

        System.out.println("====== MIN HEAP DEMO ======");

        MinHeap heap = new MinHeap(15);

        // Insert elements
        heap.insert(10);
        heap.insert(4);
        heap.insert(15);
        heap.insert(20);
        heap.insert(0);
        heap.insert(8);

        System.out.print("Heap after insertion: ");
        heap.printHeap();

        // Peek minimum
        System.out.println("Peek Min: " + heap.peek());

        // Extract minimum
        System.out.println("Extracted Min: " + heap.extractMin());

        System.out.print("Heap after extractMin: ");
        heap.printHeap();

        // Build Heap
        int[] arr = {12, 3, 17, 8, 22, 9, 14};
        heap.buildHeap(arr);

        System.out.print("Heap after buildHeap: ");
        heap.printHeap();

        // Heap Sort
        heap.heapSort();
        System.out.print("Heap after Heap Sort: ");
        heap.printHeap();
    }
}
