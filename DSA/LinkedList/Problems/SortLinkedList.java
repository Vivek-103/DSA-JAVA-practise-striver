import java.util.*; // For ArrayList and Collections.sort

// Class name: SortLinkedList (short and concise as requested)
public class SortLinkedList {

    // -------------------- List Node Definition --------------------
    // Simple singly-linked list node definition
    static class ListNode {
        int val;            // value stored in the node
        ListNode next;      // reference to the next node
        ListNode(int val) { // constructor with value
            this.val = val;
            this.next = null;
        }
        ListNode(int val, ListNode next) { // constructor with value and next
            this.val = val;
            this.next = next;
        }
    }

    // -------------------- Approach 1: Sort by copying to array --------------------
    // Concept: copy all node values into an array/list, sort the array, then rebuild the linked list.
    // Time Complexity: O(n log n) due to array sorting
    // Space Complexity: O(n) for the temporary array/list
    public static ListNode sortUsingArray(ListNode head) {
        // If list is empty or single node, it's already sorted
        if (head == null || head.next == null) return head;

        // Create a dynamic array to store values
        List<Integer> vals = new ArrayList<>(); // O(n) extra space
        ListNode cur = head;                     // pointer to traverse the list

        // Copy values from linked list to array
        while (cur != null) {                    // traverse list
            vals.add(cur.val);                   // add current node value to array
            cur = cur.next;                      // move to next node
        }

        // Sort the array of values
        Collections.sort(vals);                   // O(n log n) sort

        // Rebuild a new sorted linked list from sorted values
        ListNode dummy = new ListNode(0);         // dummy head to simplify list building
        ListNode tail = dummy;                    // tail points to last node in new list

        for (int v : vals) {                      // iterate sorted values
            tail.next = new ListNode(v);          // append new node with value v
            tail = tail.next;                     // move tail forward
        }

        return dummy.next;                        // return head of sorted list
    }

    // -------------------- Approach 2: Insertion Sort on Linked List (Brute) --------------------
    // Concept: insert nodes one-by-one into a sorted part of the list.
    // Time Complexity: O(n^2) in worst case
    // Space Complexity: O(1) extra space (in-place)
    public static ListNode insertionSortList(ListNode head) {
        // Edge case: empty or single node - already sorted
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(Integer.MIN_VALUE); // dummy with very small value
        ListNode curr = head;                             // current node to insert next

        // Iterate through the original list and insert each node into sorted part
        while (curr != null) {                            // while nodes remain
            ListNode next = curr.next;                    // save next node to process later
            ListNode prev = dummy;                        // start from dummy each insertion

            // Find position to insert current node: prev.next.val >= curr.val -> stop
            while (prev.next != null && prev.next.val < curr.val) {
                prev = prev.next;                         // move prev forward
            }

            // Insert curr between prev and prev.next
            curr.next = prev.next;                        // link curr to next of prev
            prev.next = curr;                             // link prev to curr

            // Move to next node in original list
            curr = next;                                  // continue loop with saved next
        }

        return dummy.next;                                // return sorted head
    }

    // -------------------- Approach 3: Merge Sort (Top-Down) - Optimal --------------------
    // Concept: divide list into halves using slow/fast pointers, recursively sort halves, merge them.
    // Time Complexity: O(n log n)
    // Space Complexity: O(log n) due to recursion stack (optimal for linked lists)
    public static ListNode mergeSortList(ListNode head) {
        // Base case: empty or single node list is already sorted
        if (head == null || head.next == null) return head;

        // Find middle and split list into two halves
        ListNode mid = getMiddle(head); // mid is start of right half
        ListNode left = head;           // left half head
        ListNode right = mid;           // right half head

        // Recursively sort left and right halves
        ListNode sortedLeft = mergeSortList(left);   // sort left half
        ListNode sortedRight = mergeSortList(right); // sort right half

        // Merge two sorted halves and return merged list
        return mergeTwoLists(sortedLeft, sortedRight);
    }

    // Helper: find middle and split list into two halves
    // Implementation detail: for finding middle we use slow/fast; after loop split at slow
    // This function will return the head of the right half, and will cut the list by setting the end of left to null.
    private static ListNode getMiddle(ListNode head) {
        // If only one or zero nodes, right half is null or head.next
        if (head == null) return null;

        ListNode slow = head;            // slow pointer moves 1 step
        ListNode fast = head.next;       // fast pointer moves 2 steps (start at head.next to get left bias)
        // Move pointers till fast reaches end
        while (fast != null && fast.next != null) {
            slow = slow.next;            // move slow by 1
            fast = fast.next.next;       // move fast by 2
        }
        // slow now points to node before mid (end of left half)
        ListNode mid = slow.next;        // mid is head of right half
        slow.next = null;                // cut left half from right half
        return mid;                      // return head of right half
    }

    // Helper: merge two sorted linked lists and return merged head
    // Time Complexity: O(n1 + n2)
    // Space Complexity: O(1) (only pointers)
    private static ListNode mergeTwoLists(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0); // dummy head for merged list
        ListNode tail = dummy;            // tail pointer for merged list

        while (a != null && b != null) {  // while both lists have nodes
            if (a.val <= b.val) {         // if node a should come first
                tail.next = a;            // append a
                a = a.next;               // advance a
            } else {
                tail.next = b;            // append b
                b = b.next;               // advance b
            }
            tail = tail.next;             // move tail forward
        }

        // Append remaining nodes if any
        if (a != null) tail.next = a;     // attach remainder of a
        if (b != null) tail.next = b;     // attach remainder of b

        return dummy.next;                // head of merged list
    }

    // -------------------- Utility: Print List --------------------
    // Print linked list nodes in "val -> val -> ... -> null" format
    public static void printList(ListNode head) {
        ListNode cur = head;               // pointer for traversal
        while (cur != null) {              // while nodes remain
            System.out.print(cur.val);     // print current value
            if (cur.next != null) System.out.print(" -> "); // print arrow if not last
            cur = cur.next;                // move to next node
        }
        System.out.println(" -> null");    // print tail null
    }

    // -------------------- Helper: Build List from array --------------------
    // Create a linked list from integer array and return head
    public static ListNode buildList(int[] arr) {
        ListNode dummy = new ListNode(0);  // dummy head
        ListNode tail = dummy;             // tail pointer
        for (int v : arr) {                // iterate values
            tail.next = new ListNode(v);   // append node
            tail = tail.next;              // advance tail
        }
        return dummy.next;                 // return real head
    }

    // -------------------- Main Method: Examples --------------------
    public static void main(String[] args) {
        // Example 1: small unsorted list
        int[] vals1 = {4, 2, 1, 3};                     // sample input array
        ListNode list1 = buildList(vals1);              // build linked list from array
        System.out.println("Original list 1:");         // header
        printList(list1);                               // print original

        // Use array-based sorting approach (copy->sort->rebuild)
        ListNode sortedByArray = sortUsingArray(buildList(vals1)); // build again to keep original unchanged
        System.out.println("Sorted by array approach (O(n log n), O(n) space):");
        printList(sortedByArray);                       // print result

        // Use insertion sort approach (insertion sort on list - O(n^2))
        ListNode sortedInsertion = insertionSortList(buildList(vals1)); // rebuild and sort
        System.out.println("Sorted by insertion sort (in-place, O(n^2)):");
        printList(sortedInsertion);                     // print result

        // Use merge sort approach (optimal for lists - O(n log n), O(log n) stack)
        ListNode sortedMerge = mergeSortList(buildList(vals1)); // rebuild and sort
        System.out.println("Sorted by merge sort (top-down O(n log n)):");
        printList(sortedMerge);                         // print result

        // Example 2: list with duplicates and larger size
        int[] vals2 = {10, -1, 3, 5, 3, 0, 2};          // another example with negative and duplicates
        ListNode list2 = buildList(vals2);              // build second list
        System.out.println("\nOriginal list 2:");
        printList(list2);                               // print second original

        // Merge sort (recommended) on second example
        ListNode sorted2 = mergeSortList(buildList(vals2)); // rebuild for sort
        System.out.println("Sorted list 2 by merge sort:");
        printList(sorted2);                             // print sorted list 2
    }
}
