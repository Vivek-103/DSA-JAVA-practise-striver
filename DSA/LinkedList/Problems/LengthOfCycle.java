// Class to find length of a cycle in a linked list
public class LengthOfCycle {

    // Node class for linked list
    static class Node {
        int data;    // Value stored in node
        Node next;   // Pointer to next node

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
    // 1. BRUTE FORCE APPROACH
    // Using HashSet to detect the loop and count nodes
    // ---------------------------
    public static int lengthOfCycleBruteForce(Node head) {
        java.util.HashSet<Node> visited = new java.util.HashSet<>();
        Node temp = head;

        while (temp != null) {
            if (visited.contains(temp)) {
                // Cycle detected, now calculate length
                Node start = temp;
                int length = 1;
                Node current = start.next;

                while (current != start) {
                    length++;
                    current = current.next;
                }

                return length; // Length of cycle
            }

            visited.add(temp);  // Mark node as visited
            temp = temp.next;   // Move to next node
        }

        return 0; // No cycle
    }

    // ---------------------------
    // 2. OPTIMAL APPROACH
    // Using Floyd’s Tortoise and Hare method
    // ---------------------------
    public static int lengthOfCycleOptimal(Node head) {
        if (head == null) return 0;

        Node slow = head;  // Moves 1 step
        Node fast = head;  // Moves 2 steps
        boolean cycleExists = false;

        // Step 1: Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                cycleExists = true;
                break;
            }
        }

        if (!cycleExists) return 0; // No cycle

        // Step 2: Count length of cycle
        int length = 1;
        Node temp = slow.next;
        while (temp != slow) {
            length++;
            temp = temp.next;
        }

        return length;
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
        System.out.println("Brute Force cycle length: " + lengthOfCycleBruteForce(head1));
        System.out.println("Optimal cycle length: " + lengthOfCycleOptimal(head1));

        // Example 2: Linked list with cycle
        Node head2 = new Node(10);
        head2.next = new Node(20);
        head2.next.next = new Node(30);
        head2.next.next.next = new Node(40);
        head2.next.next.next.next = head2.next; // Create cycle (40 -> 20)

        System.out.println("\nLinked List 2 (With Cycle):");
        // Cannot print because it will loop infinitely
        System.out.println("Brute Force cycle length: " + lengthOfCycleBruteForce(head2));
        System.out.println("Optimal cycle length: " + lengthOfCycleOptimal(head2));
    }
}

/*
-----------------------------------------
TIME AND SPACE COMPLEXITY:

1. Brute Force:
   - Time: O(N) → traverse all nodes + count nodes in cycle
   - Space: O(N) → store visited nodes in HashSet

2. Optimal (Floyd’s Tortoise and Hare):
   - Time: O(N) → detect cycle + count nodes in cycle
   - Space: O(1) → only pointers used

-----------------------------------------
*/
