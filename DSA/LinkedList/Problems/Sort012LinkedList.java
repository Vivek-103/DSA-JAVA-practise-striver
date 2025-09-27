// Class name: Sort012LinkedList
public class Sort012LinkedList {

    // ---------------- Linked List Node ----------------
    static class ListNode {
        int val;          // node value (0,1,2)
        ListNode next;    // pointer to next node
        ListNode(int val) { this.val = val; this.next = null; } // constructor
    }

    // ---------------- Approach 1: Brute Force ----------------
    // Count 0s,1s,2s and overwrite node values
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static ListNode sortBrute(ListNode head) {
        int count0 = 0, count1 = 0, count2 = 0; // initialize counters
        ListNode cur = head;                     // start traversal from head

        // Count occurrences of 0,1,2
        while (cur != null) {
            if (cur.val == 0) count0++;          // increment 0 counter
            else if (cur.val == 1) count1++;     // increment 1 counter
            else count2++;                        // increment 2 counter
            cur = cur.next;                       // move to next node
        }

        // Refill nodes in sorted order using counters
        cur = head;                                // start again from head
        while (cur != null) {
            if (count0 > 0) {                      // fill 0s first
                cur.val = 0;
                count0--;
            } else if (count1 > 0) {               // then 1s
                cur.val = 1;
                count1--;
            } else {                                // finally 2s
                cur.val = 2;
                count2--;
            }
            cur = cur.next;                         // move to next node
        }

        return head;                               // return sorted list
    }

    // ---------------- Approach 2: Optimal (Change Links) ----------------
    // Rearrange links using 3 dummy lists for 0s,1s,2s
    // Time Complexity: O(n)
    // Space Complexity: O(1) extra (dummy nodes only)
    public static ListNode sortOptimal(ListNode head) {
        if (head == null || head.next == null) return head; // edge case: 0 or 1 node

        // Create dummy nodes for 0,1,2 lists
        ListNode zeroD = new ListNode(0); // dummy head for 0s
        ListNode oneD = new ListNode(0);  // dummy head for 1s
        ListNode twoD = new ListNode(0);  // dummy head for 2s

        // Tail pointers for each list (to keep track of last node)
        ListNode zero = zeroD, one = oneD, two = twoD;

        // Traverse original list
        ListNode curr = head;              // start from head
        while (curr != null) {
            if (curr.val == 0) {           // attach node to 0 list
                zero.next = curr;
                zero = zero.next;          // move zero tail
            } else if (curr.val == 1) {    // attach node to 1 list
                one.next = curr;
                one = one.next;            // move one tail
            } else {                        // attach node to 2 list
                two.next = curr;
                two = two.next;            // move two tail
            }
            curr = curr.next;               // move to next node in original list
        }

        // Connect 0->1->2 lists
        zero.next = (oneD.next != null) ? oneD.next : twoD.next; // 0 list -> 1 list if exists else 2 list
        one.next = twoD.next;              // 1 list -> 2 list
        two.next = null;                   // last node points to null

        // Return head of new sorted list
        return zeroD.next != null ? zeroD.next : (oneD.next != null ? oneD.next : twoD.next);
    }

    // ---------------- Utility: Print Linked List ----------------
    public static void printList(ListNode head) {
        ListNode cur = head;               // start from head
        while (cur != null) {              // traverse till end
            System.out.print(cur.val);     // print current value
            if (cur.next != null) System.out.print(" -> "); // arrow if not last node
            cur = cur.next;                // move to next
        }
        System.out.println(" -> null");    // end of list
    }

    // ---------------- Helper: Build List from Array ----------------
    public static ListNode buildList(int[] arr) {
        ListNode dummy = new ListNode(0);  // dummy head
        ListNode tail = dummy;             // tail pointer
        for (int v : arr) {                // iterate values
            tail.next = new ListNode(v);   // create node
            tail = tail.next;              // move tail
        }
        return dummy.next;                 // return actual head
    }

    // ---------------- Main Method: Examples ----------------
    public static void main(String[] args) {
        // Example 1
        int[] vals1 = {1, 2, 0, 1, 2, 0, 1};
        ListNode list1 = buildList(vals1);               // build list from array
        System.out.println("Original List:");
        printList(list1);                                // print original

        // Brute Force Sort
        ListNode sortedBrute = sortBrute(buildList(vals1)); // rebuild to keep original unchanged
        System.out.println("Sorted List (Brute Force):");
        printList(sortedBrute);

        // Optimal Sort by changing links
        ListNode sortedOptimal = sortOptimal(buildList(vals1));
        System.out.println("Sorted List (Optimal - changing links):");
        printList(sortedOptimal);

        // Example 2
        int[] vals2 = {2,0,1,2,1,0,0,2,1};
        ListNode list2 = buildList(vals2);               // build second list
        System.out.println("\nOriginal List 2:");
        printList(list2);

        // Optimal sort for second example
        ListNode sorted2 = sortOptimal(buildList(vals2));
        System.out.println("Sorted List 2 (Optimal - changing links):");
        printList(sorted2);
    }
}
