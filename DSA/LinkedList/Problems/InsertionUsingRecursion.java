// Recursive Insertion in a Linked List
public class LinkedList {

    private Node head;  // Head pointer to the linked list
    private int size;   // Size of the linked list

    // Constructor to initialize an empty linked list
    public LinkedList() {
        this.size = 0;
    }

    // Public method to insert using recursion
    public void insertRec(int val, int index) {
        // Calls the private recursive method and updates head (in case index == 0)
        head = insertRec(val, index, head);
    }

    // Private helper method for recursive insertion
    private Node insertRec(int val, int index, Node node) {
        // Base condition: if index == 0, we insert here
        if (index == 0) {
            // Create a new node pointing to the current node
            Node temp = new Node(val, node);
            size++; // Increase size as we added a node
            return temp; // Return this node to be linked by previous recursion calls
        }

        // Recursive call, moving one step ahead in the list
        // ⚠ Use index - 1 instead of index-- (post-decrement won’t work properly here)
        node.next = insertRec(val, index - 1, node.next);

        // Return current node so that links remain intact
        return node;
    }

    // Display function to check the linked list
    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }
        System.out.println("END");
    }

    // Node class representing a single element of the list
    private class Node {
        int val;    // Value stored in the node
        Node next;  // Pointer to the next node

        // Constructor for last node (next is null)
        Node(int val) {
            this.val = val;
        }

        // Constructor for recursive insert (allows linking to existing node)
        Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
}

/*
========================
⏱ Time Complexity:
- O(n), where n = index
  Because in the worst case, we recurse until the given index.

🧮 Space Complexity:
- O(n), due to recursive call stack (worst case index = n).
========================
*/
