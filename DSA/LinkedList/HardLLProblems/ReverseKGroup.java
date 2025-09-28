// Class name: ReverseKGroup
public class ReverseKGroup {

    // ---------------- List node definition ----------------
    static class ListNode {
        int val;            // value stored in the node
        ListNode next;      // pointer to next node
        ListNode(int val) { // constructor
            this.val = val;
            this.next = null;
        }
    }

    // ---------------- Brute approach ----------------
    // Idea: copy node values to array, reverse each k-sized block in the array,
    // rebuild new linked list from the array.
    // Time Complexity: O(n) (traverse list once + rebuild once; reversing sublists overall O(n))
    // Space Complexity: O(n) (extra array to hold values)
    public static ListNode reverseKGroupBrute(ListNode head, int k) {
        // Edge cases: if list empty or k <= 1 then nothing to do
        if (head == null || k <= 1) return head;

        // Copy values from linked list to dynamic array (list)
        java.util.List<Integer> vals = new java.util.ArrayList<>(); // store node values
        ListNode cur = head; // iterator
        while (cur != null) {      // traverse entire list
            vals.add(cur.val);     // append current node value
            cur = cur.next;        // move to next node
        }

        // Reverse each block of size k in the values list
        for (int start = 0; start + k <= vals.size(); start += k) {
            // reverse the subList view in-place (subList returns a view)
            java.util.Collections.reverse(vals.subList(start, start + k));
        }

        // Rebuild a new linked list from the modified values
        ListNode dummy = new ListNode(0); // dummy head to simplify building
        ListNode tail = dummy;            // tail pointer for the new list
        for (int v : vals) {
            tail.next = new ListNode(v);  // create new node with value v
            tail = tail.next;             // move tail
        }

        return dummy.next; // return head of rebuilt list
    }

    // ---------------- Optimal approach (in-place) ----------------
    // Idea: iterate groups, for each k-group reverse links in-place using head insertion
    // Time Complexity: O(n) - each node is visited a constant number of times
    // Space Complexity: O(1) - only a few pointers used
    public static ListNode reverseKGroupOptimal(ListNode head, int k) {
        // Edge cases: empty list or k <= 1 means no change
        if (head == null || k <= 1) return head;

        // Dummy node helps to easily handle head changes
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // groupPrev is the node before the current group to reverse
        ListNode groupPrev = dummy;

        // Iterate until we cannot find k nodes ahead
        while (true) {
            // Find the k-th node from groupPrev (start counting at 1)
            ListNode kth = groupPrev;
            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next; // move forward
            }
            // If fewer than k nodes remain, we're done
            if (kth == null) break;

            // groupNext is the node after the k-th node (start of next group)
            ListNode groupNext = kth.next;

            // Now reverse current group: nodes from groupPrev.next up to kth
            // We'll reverse by standard pointer re-linking: move nodes one-by-one
            // to front of the group (head insertion technique).
            ListNode prev = groupNext;               // prev will ultimately become tail->next
            ListNode curr = groupPrev.next;          // first node of the group to process

            // Reverse nodes until we reach groupNext
            while (curr != groupNext) {
                ListNode tmp = curr.next; // temporarily store next node
                curr.next = prev;        // reverse current node's pointer
                prev = curr;             // move prev forward
                curr = tmp;              // move curr forward
            }

            // After reversal:
            // - prev points to kth (new head of reversed group)
            // - groupPrev.next was the old head, which is now the tail of reversed group
            // Reconnect groupPrev to new head (kth), and move groupPrev to tail for next iteration
            ListNode tailOfGroup = groupPrev.next; // old head is now tail after reversal
            groupPrev.next = kth;                  // connect previous part to new head
            groupPrev = tailOfGroup;               // prepare groupPrev for next group
        }

        // Return new head (dummy.next might have changed)
        return dummy.next;
    }

    // ---------------- Utility: build list from array ----------------
    public static ListNode buildList(int[] arr) {
        ListNode dummy = new ListNode(0); // dummy head
        ListNode tail = dummy;            // tail pointer
        for (int v : arr) {
            tail.next = new ListNode(v);  // append new node
            tail = tail.next;             // advance tail
        }
        return dummy.next;                // actual head
    }

    // ---------------- Utility: print list ----------------
    public static void printList(ListNode head) {
        ListNode cur = head;              // iterator
        while (cur != null) {
            System.out.print(cur.val);    // print value
            if (cur.next != null) System.out.print(" -> ");
            cur = cur.next;               // move next
        }
        System.out.println(" -> null");   // terminator
    }

    // ---------------- Main with examples ----------------
    public static void main(String[] args) {
        // Example 1
        int[] arr1 = {1, 2, 3, 4, 5};
        ListNode list1 = buildList(arr1);
        System.out.println("Original list 1:");
        printList(list1);

        // Reverse in groups of k = 2 using brute (array) approach
        ListNode out1Brute = reverseKGroupBrute(buildList(arr1), 2);
        System.out.println("Brute (k=2) ->");
        printList(out1Brute); // expected: 2 -> 1 -> 4 -> 3 -> 5

        // Reverse in groups of k = 2 using optimal (in-place) approach
        ListNode out1Opt = reverseKGroupOptimal(buildList(arr1), 2);
        System.out.println("Optimal (k=2) ->");
        printList(out1Opt);  // expected: 2 -> 1 -> 4 -> 3 -> 5

        // Example 2: k = 3
        ListNode list2 = buildList(arr1);
        System.out.println("\nOriginal list 2:");
        printList(list2);

        ListNode out2Brute = reverseKGroupBrute(buildList(arr1), 3);
        System.out.println("Brute (k=3) ->");
        printList(out2Brute); // expected: 3 -> 2 -> 1 -> 4 -> 5

        ListNode out2Opt = reverseKGroupOptimal(buildList(arr1), 3);
        System.out.println("Optimal (k=3) ->");
        printList(out2Opt);   // expected: 3 -> 2 -> 1 -> 4 -> 5

        // Example 3: k larger than length -> unchanged
        int[] arr3 = {1, 2};
        System.out.println("\nOriginal list 3:");
        printList(buildList(arr3));

        ListNode out3 = reverseKGroupOptimal(buildList(arr3), 3);
        System.out.println("Optimal (k=3, length=2) ->");
        printList(out3);      // expected: 1 -> 2 -> null
    }
}
