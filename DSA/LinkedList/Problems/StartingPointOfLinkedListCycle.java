// Class to find starting point of a cycle in a linked list
public class StartingPointOfLinkedListCycle {

    // Node class for linked list
    static class Node {
        int data;    // Value stored in the node
        Node next;   // Pointer to the next node

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
    // Using HashSet to detect the first repeated node
    // ---------------------------
    public static Node detectCycleBruteForce(Node head) {
        java.util.HashSet<Node> visited = new java.util.HashSet<>();

        Node temp = head;
        while (temp != null) {
            if (visited.contains(temp)) {
                // First repeated node is the start of the cycle
                return temp;
            }
            visited.add(temp);   // Mark node as visited
            temp = temp.next;    // Move to next node
        }

        // No cycle found
        return null;
    }

    // ---------------------------
    // 2. OPTIMAL APPROACH
    // Floyd’s Tortoise and Hare Algorithm
    // ---------------------------
    public static Node detectCycleOptimal(Node head) {
        if (head == null) return null;

        Node slow = head;  // Moves 1 step at a time
        Node fast = head;  // Moves 2 steps at a time

        boolean cycleExists = false;

        // Step 1: Detect if cycle exists
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                // Pointers meet → cycle exists
                cycleExists = true;
                break;
            }
        }

        if (!cycleExists) return null; // No cycle

        // Step 2: Find starting node of cycle
        slow = head; // Move slow back to head
        while (slow != fast) {
            slow = slow.next;   // Move one step
            fast = fast.next;   // Move one step
        }

        // slow (or fast) now points to the start of the cycle
        return slow;
    }

    // Helper function to print linked list (works only if no cycle)
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
        System.out.println("Brute Force cycle start: " + detectCycleBruteForce(head1));
        System.out.println("Optimal cycle start: " + detectCycleOptimal(head1));

        // Example 2: Linked list with cycle
        Node head2 = new Node(10);
        head2.next = new Node(20);
        head2.next.next = new Node(30);
        head2.next.next.next = new Node(40);
        head2.next.next.next.next = head2.next; // Create cycle (40 -> 20)

        System.out.println("\nLinked List 2 (With Cycle):");
        // Cannot print because it will loop infinitely
        System.out.println("Brute Force cycle start: " + detectCycleBruteForce(head2).data);
        System.out.println("Optimal cycle start: " + detectCycleOptimal(head2).data);
    }
}

/*
-----------------------------------------
TIME AND SPACE COMPLEXITY:

1. Brute Force:
   - Time: O(N) → traverse each node once
   - Space: O(N) → store all visited nodes in HashSet

2. Optimal (Floyd’s Tortoise and Hare):
   - Time: O(N) → detect cycle + find starting node
   - Space: O(1) → only two pointers used

-----------------------------------------
*/
