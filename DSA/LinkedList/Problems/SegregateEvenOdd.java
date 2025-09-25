// Class to segregate even and odd nodes in a linked list
public class SegregateEvenOdd {

    // Node class representing a linked list node
    static class Node {
        int data;   // Value stored in the node
        Node next;  // Pointer to the next node

        // Constructor to create a node with value
        Node(int data) {
            this.data = data;
            this.next = null;
        }

        // Constructor to create a node with value and next reference
        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    // ---------------------------
    // OPTIMAL APPROACH (in-place)
    // ---------------------------
    public static Node segregateEvenOdd(Node head) {
        if (head == null) return null; // Edge case: empty list

        Node evenHead = null; // Head of even nodes
        Node evenTail = null; // Tail of even nodes
        Node oddHead = null;  // Head of odd nodes
        Node oddTail = null;  // Tail of odd nodes

        Node current = head;  // Pointer to traverse the original list

        while (current != null) {
            Node nextNode = current.next; // Save next node
            current.next = null;          // Detach current node

            if (current.data % 2 == 0) { // Check if current node is even
                if (evenHead == null) {
                    // First even node
                    evenHead = evenTail = current;
                } else {
                    evenTail.next = current; // Append to even list
                    evenTail = evenTail.next;
                }
            } else {
                // Current node is odd
                if (oddHead == null) {
                    // First odd node
                    oddHead = oddTail = current;
                } else {
                    oddTail.next = current; // Append to odd list
                    oddTail = oddTail.next;
                }
            }

            current = nextNode; // Move to the next node in the original list
        }

        // Merge even and odd lists
        if (evenTail != null) {
            evenTail.next = oddHead; // Append odd list after even list
            return evenHead;         // Return head of merged list
        } else {
            return oddHead;          // No even nodes, return odd list
        }
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
        // Example 1: Mixed even and odd nodes
        Node head1 = new Node(1,
                        new Node(2,
                            new Node(3,
                                new Node(4,
                                    new Node(5, null)))));

        System.out.println("Original List:");
        printList(head1);

        System.out.println("\nAfter Segregation (Even nodes first):");
        Node result1 = segregateEvenOdd(head1);
        printList(result1);

        // Example 2: All even nodes
        Node head2 = new Node(2,
                        new Node(4,
                            new Node(6, null)));

        System.out.println("\nOriginal List 2 (All even nodes):");
        printList(head2);

        System.out.println("After Segregation:");
        Node result2 = segregateEvenOdd(head2);
        printList(result2);

        // Example 3: All odd nodes
        Node head3 = new Node(1,
                        new Node(3,
                            new Node(5, null)));

        System.out.println("\nOriginal List 3 (All odd nodes):");
        printList(head3);

        System.out.println("After Segregation:");
        Node result3 = segregateEvenOdd(head3);
        printList(result3);
    }
}

/*
-----------------------------------------
TIME AND SPACE COMPLEXITY:

Time Complexity: O(N)
- Traverse each node exactly once.

Space Complexity: O(1)
- Rearrange nodes in-place, no extra memory used.

-----------------------------------------
*/
