// Class to detect cycle in a linked list
public class LinkedListCycle {

    // Node class for linked list
    static class Node {
        int data;     // Value stored in node
        Node next;    // Reference to next node

        // Constructor with only data
        Node(int data) {
            this.data = data;
            this.next = null;
        }

        // Constructor with data and next node reference
        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    // ---------------------------
    // 1. BRUTE FORCE APPROACH
    // Using HashSet to detect visited nodes
    // ---------------------------
    public static boolean hasCycleBruteForce(Node head) {
        java.util.HashSet<Node> visited = new java.util.HashSet<>();

        Node temp = head;
        while (temp != null) {
            if (visited.contains(temp)) {
                // Node already visited → cycle detected
                return true;
            }
            visited.add(temp); // Mark node as visited
            temp = temp.next;  // Move to next node
        }

        // Reached end of list → no cycle
        return false;
    }

    // ---------------------------
    // 2. OPTIMAL APPROACH
    // Floyd’s Tortoise and Hare (Two Pointers)
    // ---------------------------
    public static boolean hasCycleOptimal(Node head) {
        if (head == null) return false;

        Node slow = head;  // Moves 1 step at a time
        Node fast = head;  // Moves 2 steps at a time

        while (fast != null && fast.next != null) {
            slow = slow.next;           // Move slow by 1
            fast = fast.next.next;      // Move fast by 2

            if (slow == fast) {
                // Pointers meet → cycle exists
                return true;
            }
        }

        // Fast reached end → no cycle
        return false;
    }

    // Helper function to print linked list (only works if no cycle)
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
        // Example 1: Linked list without cycle
        Node head1 = new Node(1,
                        new Node(2,
                            new Node(3,
                                new Node(4,
                                    new Node(5, null)))));

        System.out.println("Linked List 1 (No Cycle):");
        printList(head1);
        System.out.println("Brute Force detects cycle: " + hasCycleBruteForce(head1));
        System.out.println("Optimal detects cycle: " + hasCycleOptimal(head1));

        // Example 2: Linked list with cycle
        Node head2 = new Node(10);
        head2.next = new Node(20);
        head2.next.next = new Node(30);
        head2.next.next.next = new Node(40);
        head2.next.next.next.next = head2.next; // Creating cycle (40 -> 20)

        System.out.println("\nLinked List 2 (With Cycle):");
        // Cannot print because it will loop infinitely
        System.out.println("Brute Force detects cycle: " + hasCycleBruteForce(head2));
        System.out.println("Optimal detects cycle: " + hasCycleOptimal(head2));
    }
}

/*
-----------------------------------------
TIME AND SPACE COMPLEXITY:

1. Brute Force (HashSet):
   - Time: O(N) → traverse all nodes
   - Space: O(N) → store each node in HashSet

2. Optimal (Floyd’s Tortoise and Hare):
   - Time: O(N) → each node visited at most once
   - Space: O(1) → only two pointers used

-----------------------------------------
*/
