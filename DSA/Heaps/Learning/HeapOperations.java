/*
 ============================================================================
 📌 HEAP OPERATIONS IN JAVA (MIN HEAP)
 ----------------------------------------------------------------------------
 A Heap is a COMPLETE Binary Tree that follows the Heap Property.

 Types of Heaps:
 1. Min Heap → Parent node is ALWAYS smaller than children
 2. Max Heap → Parent node is ALWAYS larger than children

 This file implements a MIN HEAP using an array.
 ============================================================================
*/

import java.util.Arrays;

public class HeapOperations {

    // ----------------- HEAP DATA -----------------

    private int[] heap;     // Array to store heap elements
    private int size;       // Current number of elements in heap
    private int capacity;   // Maximum size of heap

    // ----------------- CONSTRUCTOR -----------------

    /*
     Constructor to initialize heap
     Time Complexity: O(1)
     Space Complexity: O(n)
    */
    public HeapOperations(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
    }

    // ----------------- INDEX HELPERS -----------------

    /*
     Get parent index
     Time: O(1), Space: O(1)
    */
    private int parent(int i) {
        return (i - 1) / 2;
    }

    /*
     Get left child index
     Time: O(1), Space: O(1)
    */
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    /*
     Get right child index
     Time: O(1), Space: O(1)
    */
    private int rightChild(int i) {
        return 2 * i + 2;
    }

    // ----------------- SWAP -----------------

    /*
     Swap two elements in heap
     Time: O(1), Space: O(1)
    */
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // ----------------- INSERT OPERATION -----------------

    /*
     Insert an element into Min Heap

     Steps:
     1. Insert element at end
     2. Heapify Up to restore heap property

     Time Complexity: O(log n)
     Space Complexity: O(1)
    */
    public void insert(int value) {
        if (size == capacity) {
            System.out.println("Heap is full!");
            return;
        }

        heap[size] = value;
        size++;

        heapifyUp(size - 1);
    }

    /*
     Heapify Up (Bottom → Top)
     Used after insertion

     Time: O(log n)
     Space: O(1)
    */
    private void heapifyUp(int index) {
        while (index != 0 && heap[parent(index)] > heap[index]) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    // ----------------- DELETE MIN -----------------

    /*
     Remove and return minimum element (root)

     Steps:
     1. Replace root with last element
     2. Reduce size
     3. Heapify Down

     Time Complexity: O(log n)
     Space Complexity: O(1)
    */
    public int extractMin() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        if (size == 1) {
            size--;
            return heap[0];
        }

        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;

        heapifyDown(0);

        return min;
    }

    /*
     Heapify Down (Top → Bottom)
     Used after deletion

     Time: O(log n)
     Space: O(1)
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

    // ----------------- PEEK -----------------

    /*
     Return minimum element without removing it

     Time Complexity: O(1)
     Space Complexity: O(1)
    */
    public int peek() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap[0];
    }

    // ----------------- BUILD HEAP -----------------

    /*
     Build heap from unsorted array

     Time Complexity: O(n)
     Space Complexity: O(1)
    */
    public void buildHeap(int[] arr) {
        if (arr.length > capacity) {
            throw new IllegalArgumentException("Array too large");
        }

        heap = Arrays.copyOf(arr, capacity);
        size = arr.length;

        for (int i = parent(size - 1); i >= 0; i--) {
            heapifyDown(i);
        }
    }

    // ----------------- HEAP SORT -----------------

    /*
     Heap Sort using Min Heap

     Time Complexity: O(n log n)
     Space Complexity: O(1)
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

    // ----------------- DISPLAY -----------------

    /*
     Print heap elements

     Time: O(n)
     Space: O(1)
    */
    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    // ----------------- MAIN METHOD -----------------

    public static void main(String[] args) {

        System.out.println("=== MIN HEAP OPERATIONS ===");

        HeapOperations heap = new HeapOperations(10);

        // Insert elements
        heap.insert(10);
        heap.insert(5);
        heap.insert(20);
        heap.insert(2);
        heap.insert(8);

        System.out.print("Heap after insertion: ");
        heap.printHeap();

        // Peek
        System.out.println("Min element (peek): " + heap.peek());

        // Extract Min
        System.out.println("Extracted Min: " + heap.extractMin());

        System.out.print("Heap after deletion: ");
        heap.printHeap();

        // Build Heap
        int[] arr = {15, 3, 17, 10, 84, 19, 6, 22, 9};
        heap.buildHeap(arr);

        System.out.print("Heap after buildHeap: ");
        heap.printHeap();

        // Heap Sort
        heap.heapSort();
        System.out.print("After Heap Sort: ");
        heap.printHeap();
    }
}
