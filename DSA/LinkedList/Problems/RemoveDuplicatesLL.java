import java.util.HashSet; // Import HashSet for removing duplicates in unsorted list

public class RemoveDuplicatesLL {

    // Node class to represent each element in the list
    private static class Node {
        int val;   // Stores the data (value of the node)
        Node next; // Reference to the next node in the linked list

        // Constructor to create a new node with a value
        Node(int val) {
            this.val = val;
            this.next = null; // By default, next is null
        }
    }

    private Node head;  // Head pointer (first node of the linked list)

    // Insert element at the END of the linked list
    public void insert(int val) {
        Node newNode = new Node(val); // Create a new node
        if (head == null) { // If list is empty
            head = newNode; // First node becomes head
            return;
        }
        Node temp = head; // Start from the head
        while (temp.next != null) { // Traverse until the last node
            temp = temp.next;
        }
        temp.next = newNode; // Link new node at the end
    }

    // Display all elements of the linked list
    public void display() {
        Node temp = head; // Start from head
        while (temp != null) { // Traverse until null
            System.out.print(temp.val + " -> "); // Print current value
            temp = temp.next; // Move to next node
        }
        System.out.println("END"); // Indicate end of list
    }

    // ✅ Remove duplicates from an UNSORTED linked list
    // Uses a HashSet to track seen values
    public void removeDuplicatesUnsorted() {
        if (head == null) return; // If list is empty, nothing to do

        HashSet<Integer> seen = new HashSet<>(); // Store unique values
        Node current = head; // Start from the head
        Node prev = null;    // Previous pointer (initially null)

        while (current != null) { // Traverse the whole list
            if (seen.contains(current.val)) {
                // If value already seen, it's a duplicate → remove it
                prev.next = current.next; // Skip current node
            } else {
                // If value not seen yet, add it to the set
                seen.add(current.val);
                prev = current; // Move prev to current
            }
            current = current.next; // Move forward
        }
    }

    // ✅ Remove duplicates from a SORTED linked list
    // Works because duplicates are always next to each other
    public void removeDuplicatesSorted() {
        Node current = head; // Start from the head
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                // If current and next are equal → duplicate found
                current.next = current.next.next; // Skip the next node
            } else {
                current = current.next; // Otherwise, move forward
            }
        }
    }

    // Main method to test the code
    public static void main(String[] args) {
        RemoveDuplicatesLL list = new RemoveDuplicatesLL();

        // Insert elements into the linked list (with duplicates)
        list.insert(10);
        list.insert(20);
        list.insert(20);
        list.insert(30);
        list.insert(40);
        list.insert(30);
        list.insert(10);

        // Show original list
        System.out.println("Original Linked List:");
        list.display();

        // Call one of the duplicate removal methods
        list.removeDuplicatesUnsorted(); // Works for unsorted lists

        // Show modified list
        System.out.println("After Removing Duplicates:");
        list.display();
    }
}

/*
========================
⏱ Time Complexity:
- removeDuplicatesUnsorted() → O(n) 
   (Each node is visited once, HashSet lookup is O(1) average).
- removeDuplicatesSorted() → O(n) 
   (Single traversal of the list).

🧮 Space Complexity:
- removeDuplicatesUnsorted() → O(n) 
   (Extra space for HashSet).
- removeDuplicatesSorted() → O(1) 
   (No extra space needed).
========================
*/
