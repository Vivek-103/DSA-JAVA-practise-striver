// Class name as per your requirement
public class FindingMiddleElement {

    // Node class to represent each element in the linked list
    static class Node {
        int data;    // Value stored in the node
        Node next;   // Reference to the next node

        // Constructor with only data (next will be null by default)
        Node(int data) {
            this.data = data;
            this.next = null;
        }

        // Constructor with both data and next node reference
        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    // Function to find the middle node of the linked list
    // If even number of nodes, returns the SECOND middle
    public static Node findMiddle(Node head) {
        // If the list is empty, simply return null
        if (head == null) {
            return null;
        }

        // Initialize slow and fast pointers
        Node slow = head;  // moves 1 step at a time
        Node fast = head;  // moves 2 steps at a time

        // Loop until fast reaches the end
        while (fast != null && fast.next != null) {
            slow = slow.next;          // move slow by 1 step
            fast = fast.next.next;     // move fast by 2 steps
        }

        // slow is now at the middle
        return slow;
    }

    // Helper function to print the linked list
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
        // Example 1: Odd number of nodes (5 nodes)
        Node head1 = new Node(1,
                        new Node(2,
                            new Node(3,
                                new Node(4,
                                    new Node(5, null)))));

        System.out.println("Linked List 1:");
        printList(head1);
        Node middle1 = findMiddle(head1);
        System.out.println("Middle element (Odd size): " + middle1.data);

        // Example 2: Even number of nodes (6 nodes)
        Node head2 = new Node(10,
                        new Node(20,
                            new Node(30,
                                new Node(40,
                                    new Node(50,
                                        new Node(60, null))))));

        System.out.println("\nLinked List 2:");
        printList(head2);
        Node middle2 = findMiddle(head2);
        System.out.println("Middle element (Even size): " + middle2.data);
    }
}

/*
-----------------------------------------
TIME COMPLEXITY:
- Each loop iteration moves fast pointer twice as quickly as slow.
- Loop runs about N/2 times → O(N).
So, Time Complexity = O(N).

SPACE COMPLEXITY:
- Only uses two pointers (slow and fast), no extra space.
So, Space Complexity = O(1).
-----------------------------------------
*/
