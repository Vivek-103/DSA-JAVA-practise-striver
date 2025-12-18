/**
 * ============================================================================
 * 📌 MERGE K SORTED LINKED LISTS (JAVA)
 * ============================================================================
 *
 * PROBLEM:
 * --------
 * You are given an array `heads[]` containing head nodes of k sorted
 * linked lists. Your task is to merge all lists into one sorted list
 * and return the head of that final merged list.
 *
 * WHY MIN HEAP?
 * -------------
 * - At any moment, the smallest element among the current heads of each
 *   list should be selected first.
 * - A Min Heap allows extraction of smallest element in O(log k)
 *
 * TIME & SPACE COMPLEXITY
 * ------------------------
 * Time  = O(n log k)   → n = total nodes, k = number of lists
 * Space = O(k)         → heap stores at most k elements
 *
 * ============================================================================
 */

import java.util.PriorityQueue;

public class MergeKSortedLists {

    /** Node definition for Linked List */
    static class Node {
        int data;       // value stored in node
        Node next;      // reference to next node

        Node(int data) { // constructor to initialize node
            this.data = data; 
            this.next = null;
        }
    }

    /**
     * Merge k sorted linked lists using Min Heap
     *
     * @param heads array of head pointers of sorted lists
     * @return head of merged sorted linked list
     */
    public static Node mergeKLists(Node[] heads) {

        // Step 1: Create a min heap to store nodes by their data value
        PriorityQueue<Node> minHeap = new PriorityQueue<>(
            (a, b) -> a.data - b.data // comparator to sort nodes by value
        );

        // Step 2: Add the head of each list into the heap (if it exists)
        for (Node head : heads) {   // iterate through list heads
            if (head != null) {     // ensure head exists
                minHeap.add(head);  // push node to heap
            }
        }

        // Step 3: Create a dummy node to simplify list merging
        Node dummy = new Node(-1);   // dummy node acts as start reference
        Node tail = dummy;           // tail pointer to build final list

        // Step 4: Process heap until it's empty
        while (!minHeap.isEmpty()) {   // while nodes remain

            Node minNode = minHeap.poll(); // extract smallest node

            tail.next = minNode;     // attach extracted node to merged list
            tail = tail.next;        // move tail forward

            if (minNode.next != null) {   // if extracted node has successor
                minHeap.add(minNode.next); // push the successor into heap
            }
        }

        // Step 5: Return merged list starting from dummy.next
        return dummy.next;
    }

    /** Utility method to print a linked list */
    public static void printList(Node head) {
        Node temp = head;          // start temp pointer at head
        while (temp != null) {     // iterate while nodes exist
            System.out.print(temp.data + " "); // print value
            temp = temp.next;      // move to next node
        }
        System.out.println();      // line break after printing list
    }

    /** MAIN METHOD → DEMO */
    public static void main(String[] args) {

        // Create first sorted list: 1 -> 4 -> 7
        Node l1 = new Node(1);
        l1.next = new Node(4);
        l1.next.next = new Node(7);

        // Create second sorted list: 2 -> 5 -> 8
        Node l2 = new Node(2);
        l2.next = new Node(5);
        l2.next.next = new Node(8);

        // Create third sorted list: 3 -> 6 -> 9
        Node l3 = new Node(3);
        l3.next = new Node(6);
        l3.next.next = new Node(9);

        // Put heads of lists into array
        Node[] lists = {l1, l2, l3};

        // Merge lists
        Node merged = mergeKLists(lists);

        // Print merged list
        System.out.println("Merged sorted linked list:");
        printList(merged);
    }
}
