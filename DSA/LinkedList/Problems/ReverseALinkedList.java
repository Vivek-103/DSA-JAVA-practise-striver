// Class name as you required
public class ReverseALinkedList {

    // Node class for Linked List
    static class Node {
        int data;     // Value of the node
        Node next;    // Pointer to the next node

        // Constructor with only data
        Node(int data) {
            this.data = data;
            this.next = null;
        }

        // Constructor with data and next reference
        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    // ---------------------------
    // 1. ITERATIVE APPROACH
    // ---------------------------
    public static Node reverseIterative(Node head) {
        Node prev = null;      // Previous node (starts as null because new tail points to null)
        Node curr = head;      // Current node starts at head
        Node next = null;      // Temporary pointer to hold next node

        while (curr != null) {           // Traverse till end of list
            next = curr.next;            // Save next node
            curr.next = prev;            // Reverse link: current points to previous
            prev = curr;                 // Move prev forward
            curr = next;                 // Move curr forward
        }

        // At the end, prev is the new head
        return prev;
    }

    // ---------------------------
    // 2. RECURSIVE APPROACH
    // ---------------------------
    public static Node reverseRecursive(Node head) {
        // Base case: empty list or only one node
        if (head == null || head.next == null) {
            return head;
        }

        // Reverse the rest of the list recursively
        Node newHead = reverseRecursive(head.next);

        // Fix the current node's next pointer
        head.next.next = head;   // Make next node point back to current
        head.next = null;        // Current node becomes new tail

        return newHead;          // Return new head of reversed list
    }

    // ---------------------------
    // 3. USING STACK APPROACH
    // ---------------------------
    public static Node reverseUsingStack(Node head) {
        java.util.Stack<Node> stack = new java.util.Stack<>();

        Node temp = head;
        // Push all nodes into the stack
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }

        // If list is empty, return null
        if (stack.isEmpty()) {
            return null;
        }

        // Pop first element -> new head
        Node newHead = stack.pop();
        temp = newHead;

        // Pop remaining nodes and link them
        while (!stack.isEmpty()) {
            temp.next = stack.pop();
            temp = temp.next;
        }

        // Last node should point to null
        temp.next = null;

        return newHead;
    }

    // Helper function to print linked list
    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // MAIN method for testing
    public static void main(String[] args) {
        // Create linked list: 1 -> 2 -> 3 -> 4 -> 5
        Node head = new Node(1,
                        new Node(2,
                            new Node(3,
                                new Node(4,
                                    new Node(5, null)))));

        System.out.println("Original List:");
        printList(head);

        // Iterative
        Node reversedIterative = reverseIterative(head);
        System.out.println("\nReversed (Iterative):");
        printList(reversedIterative);

        // Re-create list for next test (because it got reversed already)
        head = new Node(1,
                    new Node(2,
                        new Node(3,
                            new Node(4,
                                new Node(5, null)))));

        // Recursive
        Node reversedRecursive = reverseRecursive(head);
        System.out.println("\nReversed (Recursive):");
        printList(reversedRecursive);

        // Re-create list again
        head = new Node(1,
                    new Node(2,
                        new Node(3,
                            new Node(4,
                                new Node(5, null)))));

        // Stack
        Node reversedStack = reverseUsingStack(head);
        System.out.println("\nReversed (Using Stack):");
        printList(reversedStack);
    }
}

/*
-----------------------------------------
TIME AND SPACE COMPLEXITY:

1. Iterative:
   - Time: O(N) → each node visited once
   - Space: O(1) → only 3 pointers used

2. Recursive:
   - Time: O(N) → each node visited once
   - Space: O(N) → recursion stack holds N calls

3. Using Stack:
   - Time: O(N) → push and pop each node once
   - Space: O(N) → stack stores all N nodes

-----------------------------------------
*/
