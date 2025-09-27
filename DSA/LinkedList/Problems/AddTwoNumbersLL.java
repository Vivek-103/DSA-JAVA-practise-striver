// Class name: AddTwoNumbersLL
public class AddTwoNumbersLL {

    // ---------------- Linked List Node ----------------
    static class ListNode {
        int val;          // value of node (0-9)
        ListNode next;    // pointer to next node
        ListNode(int val) { this.val = val; this.next = null; }
    }

    // ---------------- Approach 1: Iterative ----------------
    // Add two numbers stored in reverse order iteratively
    // Time Complexity: O(max(m, n)) where m,n = lengths of lists
    // Space Complexity: O(1) for pointers (not counting output list)
    public static ListNode addTwoNumbersIterative(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0); // dummy head to simplify code
        ListNode tail = dummy;            // tail pointer for building result
        int carry = 0;                     // carry for sum of digits

        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;               // start with carry

            if (l1 != null) {              // add digit from list1 if available
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {              // add digit from list2 if available
                sum += l2.val;
                l2 = l2.next;
            }

            carry = sum / 10;              // update carry for next node
            tail.next = new ListNode(sum % 10); // create new node with digit
            tail = tail.next;              // move tail
        }

        return dummy.next;                 // return head of result list
    }

    // ---------------- Approach 2: Recursive ----------------
    // Recursively add digits with carry
    // Time Complexity: O(max(m, n))
    // Space Complexity: O(max(m, n)) due to recursion stack
    public static ListNode addTwoNumbersRecursive(ListNode l1, ListNode l2) {
        return addTwoNumbersHelper(l1, l2, 0); // start with carry 0
    }

    private static ListNode addTwoNumbersHelper(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) return null; // base case

        int sum = carry;
        if (l1 != null) sum += l1.val;       // add value from list1
        if (l2 != null) sum += l2.val;       // add value from list2

        ListNode node = new ListNode(sum % 10); // create node for current digit

        // recurse for next nodes
        node.next = addTwoNumbersHelper(
                (l1 != null) ? l1.next : null,
                (l2 != null) ? l2.next : null,
                sum / 10
        );

        return node; // return current node
    }

    // ---------------- Utility: Print Linked List ----------------
    public static void printList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val);
            if (cur.next != null) System.out.print(" -> ");
            cur = cur.next;
        }
        System.out.println(" -> null");
    }

    // ---------------- Helper: Build List from Array ----------------
    public static ListNode buildList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int v : arr) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    // ---------------- Main Method: Examples ----------------
    public static void main(String[] args) {
        // Example 1: 342 + 465 = 807
        // List1: 2 -> 4 -> 3 (represents 342)
        // List2: 5 -> 6 -> 4 (represents 465)
        int[] vals1 = {2, 4, 3};
        int[] vals2 = {5, 6, 4};

        ListNode l1 = buildList(vals1);
        ListNode l2 = buildList(vals2);

        System.out.println("List 1:");
        printList(l1);
        System.out.println("List 2:");
        printList(l2);

        // Iterative approach
        ListNode sumIter = addTwoNumbersIterative(buildList(vals1), buildList(vals2));
        System.out.println("Sum (Iterative):");
        printList(sumIter);

        // Recursive approach
        ListNode sumRec = addTwoNumbersRecursive(buildList(vals1), buildList(vals2));
        System.out.println("Sum (Recursive):");
        printList(sumRec);

        // Example 2: Different lengths: 99 + 1 = 100
        int[] vals3 = {9, 9};
        int[] vals4 = {1};
        ListNode l3 = buildList(vals3);
        ListNode l4 = buildList(vals4);

        System.out.println("\nList 3:");
        printList(l3);
        System.out.println("List 4:");
        printList(l4);

        ListNode sumIter2 = addTwoNumbersIterative(buildList(vals3), buildList(vals4));
        System.out.println("Sum (Iterative) for 99 + 1:");
        printList(sumIter2);

        ListNode sumRec2 = addTwoNumbersRecursive(buildList(vals3), buildList(vals4));
        System.out.println("Sum (Recursive) for 99 + 1:");
        printList(sumRec2);
    }
}
