// Problem: Remove Middle Node from a Linked List
// Class name as per problem statement (short and concise)
public class RemoveMiddleNode {

    // ---------------- Node Definition ----------------
    static class ListNode {
        int val;            // data in the node
        ListNode next;      // pointer to the next node
        ListNode(int val) { // constructor
            this.val = val;
            this.next = null;
        }
    }

    // ---------------- Brute Force Approach ----------------
    // Step 1: Find length of the list
    // Step 2: Remove node at index length/2
    // Time Complexity: O(L)
    // Space Complexity: O(1)
    public static ListNode removeMiddleBrute(ListNode head) {
        if (head == null || head.next == null) return null; // list has 0 or 1 element

        // First pass: count total length
        int length = 0;
        ListNode temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }

        // Middle position (0-based index)
        int mid = length / 2;

        // If removing the head itself
        if (mid == 0) return head.next;

        // Second pass: reach node just before the middle
        temp = head;
        for (int i = 0; i < mid - 1; i++) {
            temp = temp.next;
        }

        // Remove middle node
        temp.next = temp.next.next;

        return head;
    }

    // ---------------- Optimal Approach ----------------
    // Use slow and fast pointer technique
    // Time Complexity: O(L)
    // Space Complexity: O(1)
    public static ListNode removeMiddleOptimal(ListNode head) {
        if (head == null || head.next == null) return null; // list has <=1 element

        // Dummy node to handle head deletion
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = head;

        // Move fast by 2 steps, slow by 1 step
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Now slow is just before the middle node
        slow.next = slow.next.next;

        return dummy.next; // return correct head
    }

    // ---------------- Utility Function ----------------
    // Print linked list
    public static void printList(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // ---------------- Main Method (Examples) ----------------
    public static void main(String[] args) {
        // Example 1: List = [1,2,3,4,5]
        // Middle = 3
        // Result = [1,2,4,5]
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);

        System.out.println("Original List 1:");
        printList(head1);

        ListNode resultBrute1 = removeMiddleBrute(head1);
        System.out.println("After removing middle (Brute):");
        printList(resultBrute1);

        // Example 2: List = [10,20,30,40]
        // Middle = 20 (index 1, since length=4, mid=2, remove 2nd node (0-based))
        // Result = [10,30,40]
        ListNode head2 = new ListNode(10);
        head2.next = new ListNode(20);
        head2.next.next = new ListNode(30);
        head2.next.next.next = new ListNode(40);

        System.out.println("\nOriginal List 2:");
        printList(head2);

        ListNode resultOptimal2 = removeMiddleOptimal(head2);
        System.out.println("After removing middle (Optimal):");
        printList(resultOptimal2);
    }
}
