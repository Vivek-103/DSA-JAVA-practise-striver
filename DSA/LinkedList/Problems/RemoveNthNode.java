// Problem: Remove N-th node from the end of a Linked List
// Class name as per problem (short and concise)
public class RemoveNthNodeFromEnd {

    // ------------------- Node Definition -------------------
    static class Node {
        int val;      // value stored in node
        Node next;    // pointer to next node

        // Constructor for only value
        Node(int val) {
            this.val = val;
            this.next = null;
        }

        // Constructor for value + next node
        Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    // ------------------- Brute Force Approach -------------------
    // Time: O(L), Space: O(1)
    public static Node removeNthFromEndBrute(Node head, int n) {
        if (head == null) return null;

        // Step 1: Find total length
        int length = 0;
        Node temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }

        // Step 2: If we need to remove the head
        if (n == length) {
            return head.next; // new head will be 2nd node
        }

        // Step 3: Traverse to node before the one to delete
        temp = head;
        for (int i = 1; i < length - n; i++) {
            temp = temp.next;
        }

        // Step 4: Skip the node
        temp.next = temp.next.next;

        return head; // return unchanged head
    }

    // ------------------- Optimal Approach (Two Pointers) -------------------
    // Time: O(L), Space: O(1)
    public static Node removeNthFromEndOptimal(Node head, int n) {
        // Dummy node simplifies edge cases
        Node dummy = new Node(0, head);
        Node fast = dummy, slow = dummy;

        // Move fast n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move both pointers until fast reaches null
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Slow is before the node to delete
        slow.next = slow.next.next;

        return dummy.next; // return actual head (may change if first node was deleted)
    }

    // ------------------- Utility Functions -------------------
    // Print linked list
    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // ------------------- Main Method (Examples) -------------------
    public static void main(String[] args) {
        // Example: Linked List = [1,2,3,4,5], n=2
        // Expected Output = [1,2,3,5]

        // Build linked list using both constructors
        Node head = new Node(1,
                        new Node(2,
                            new Node(3,
                                new Node(4,
                                    new Node(5)))));

        System.out.println("Original List:");
        printList(head);

        // Brute Force Removal
        Node resultBrute = removeNthFromEndBrute(head, 2);
        System.out.println("After removing 2nd node from end (Brute):");
        printList(resultBrute);

        // Reset list for optimal approach
        head = new Node(1,
                  new Node(2,
                      new Node(3,
                          new Node(4,
                              new Node(5)))));

        // Optimal Removal
        Node resultOptimal = removeNthFromEndOptimal(head, 2);
        System.out.println("After removing 2nd node from end (Optimal):");
        printList(resultOptimal);
    }
}
