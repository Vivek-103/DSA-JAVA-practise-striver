// Class name: RotateLL
public class RotateLL {

    // ---------------- List node ----------------
    static class ListNode {
        int val;            // value stored in the node
        ListNode next;      // pointer to the next node
        ListNode(int val) { // constructor with value
            this.val = val;
            this.next = null;
        }
        ListNode(int val, ListNode next) { // constructor with value and next
            this.val = val;
            this.next = next;
        }
    }

    // ---------------- Brute-force approach ----------------
    // Idea: perform k single rotations: each rotation takes O(n) (find tail and move it to front)
    // Time Complexity: O(k * n)  (worst-case if k ~ n then O(n^2))
    // Space Complexity: O(1) (in-place, only pointers)
    public static ListNode rotateRightBrute(ListNode head, int k) {
        // If list is empty, single node, or no rotation requested -> return as-is
        if (head == null || head.next == null || k == 0) return head;

        // First compute length n so we can reduce unnecessary rotations
        int n = 0;                       // will hold length of list
        ListNode temp = head;            // iterator to compute length
        while (temp != null) {           // traverse the list
            n++;                         // increment count
            temp = temp.next;            // move forward
        }

        // Reduce k modulo n because rotating by n results in same list
        k = k % n;                       // now 0 <= k < n
        if (k == 0) return head;         // no change needed

        // Perform k times: take last node and move it to the front
        for (int i = 0; i < k; i++) {
            ListNode prev = null;        // will point to node before the last node
            ListNode last = head;        // will end up at the last node
            // Find last node and its previous node
            while (last.next != null) {  // iterate until last node
                prev = last;             // previous becomes current
                last = last.next;        // last moves forward
            }
            // At this point 'last' is tail, 'prev' is node before tail (non-null because list has >1 node)
            prev.next = null;            // detach last node from its previous node
            last.next = head;            // link last as new head
            head = last;                 // update head to the moved node
        }

        return head;                     // return the rotated list
    }

    // ---------------- Optimal approach ----------------
    // Idea: make list circular (tail->next = head), locate new tail at position n-k-1, break the circle
    // Time Complexity: O(n)  (single pass to compute length + constant work to re-link)
    // Space Complexity: O(1) (in-place)
    public static ListNode rotateRightOptimal(ListNode head, int k) {
        // Edge cases: empty list or single node or no rotation
        if (head == null || head.next == null || k == 0) return head;

        // Compute length n and find tail (last node)
        int n = 1;                       // start count at 1 because we'll move tail forward
        ListNode tail = head;            // start tail at head
        while (tail.next != null) {      // move until last node
            tail = tail.next;            // advance tail
            n++;                         // increment length
        }

        // Reduce k modulo n: rotating by n leaves list unchanged
        k = k % n;                       // now 0 <= k < n
        if (k == 0) return head;         // no rotation needed

        // Make the list circular for easy rotation: tail -> head
        tail.next = head;                // create circular link

        // Find new tail: it should be the (n - k - 1)-th node (0-based) from head
        int stepsToNewTail = n - k - 1;  // steps to reach new tail starting from head
        ListNode newTail = head;         // start from head
        for (int i = 0; i < stepsToNewTail; i++) {
            newTail = newTail.next;      // advance to newTail position
        }

        // New head is the node after newTail
        ListNode newHead = newTail.next; // this will become the head after rotation

        // Break the circle to finalize rotated list
        newTail.next = null;             // set end of list to null

        return newHead;                  // return new head
    }

    // ---------------- Utility: build list from array ----------------
    // Build a linked list from an int array and return its head
    public static ListNode buildList(int[] arr) {
        ListNode dummy = new ListNode(0); // dummy node to simplify appending
        ListNode tail = dummy;            // tail pointer for building
        for (int v : arr) {               // iterate over given values
            tail.next = new ListNode(v);  // append new node
            tail = tail.next;             // move tail
        }
        return dummy.next;                // return real head (skip dummy)
    }

    // ---------------- Utility: print linked list ----------------
    // Print list in format: v1 -> v2 -> ... -> null
    public static void printList(ListNode head) {
        ListNode cur = head;              // iterator starting at head
        while (cur != null) {             // until end of list
            System.out.print(cur.val);    // print current value
            if (cur.next != null) System.out.print(" -> "); // print arrow if not last
            cur = cur.next;               // move to next node
        }
        System.out.println(" -> null");   // print terminator
    }

    // ---------------- Main method: examples ----------------
    public static void main(String[] args) {
        // Example 1: basic rotation
        int[] vals1 = {1, 2, 3, 4, 5};    // original list values
        ListNode head1 = buildList(vals1); // build list
        System.out.println("Original list 1:");
        printList(head1);                 // print original

        int k1 = 2;                       // rotate right by 2
        // Brute-force rotation
        ListNode brute1 = rotateRightBrute(buildList(vals1), k1); // rebuild original to keep it unchanged
        System.out.println("Brute rotated by " + k1 + ":");
        printList(brute1);                // expected: 4 -> 5 -> 1 -> 2 -> 3 -> null

        // Optimal rotation
        ListNode opt1 = rotateRightOptimal(buildList(vals1), k1);
        System.out.println("Optimal rotated by " + k1 + ":");
        printList(opt1);                  // expected: 4 -> 5 -> 1 -> 2 -> 3 -> null

        // Example 2: k greater than length
        int[] vals2 = {0, 1, 2};          // list representing [0,1,2]
        System.out.println("\nOriginal list 2:");
        printList(buildList(vals2));      // print original
        int k2 = 4;                       // rotating by 4 is same as rotating by 4 % 3 = 1

        // Brute-force rotation with k > n
        ListNode brute2 = rotateRightBrute(buildList(vals2), k2);
        System.out.println("Brute rotated by " + k2 + " (k > n):");
        printList(brute2);                // expected: 2 -> 0 -> 1 -> null

        // Optimal rotation with k > n
        ListNode opt2 = rotateRightOptimal(buildList(vals2), k2);
        System.out.println("Optimal rotated by " + k2 + " (k > n):");
        printList(opt2);                  // expected: 2 -> 0 -> 1 -> null
    }
}
