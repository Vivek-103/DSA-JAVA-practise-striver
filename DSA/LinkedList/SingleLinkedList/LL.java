// Class representing a Singly Linked List
public class SingleLL {

    private Node head; // Pointer to the first node in the list
    private Node tail; // Pointer to the last node in the list
    private int size;  // Tracks the number of elements in the list

    // Constructor to initialize an empty linked list
    public SingleLL() {
        this.size = 0; // Initialize size to 0
    }

    // Insert a new node at the beginning of the list
    // Time Complexity: O(1)
    // Space Complexity: O(1)
    public void insertFirst(int val) {
        Node node = new Node(val); // Create new node with given value
        node.next = head; // Link new node to the current head
        head = node; // Update head to point to the new node

        if (tail == null) { // If list was empty before insertion
            tail = head; // Tail also points to the new node
        }
        size += 1; // Increase the size by 1
    }

    // Insert a new node at the end of the list
    // Time Complexity: O(1)
    // Space Complexity: O(1)
    public void insertLast(int val) {
        if (tail == null) { // If the list is empty
            insertFirst(val); // Insert at the beginning
            return;
        }
        Node node = new Node(val); // Create new node with given value
        tail.next = node; // Link the last node to the new node
        tail = node; // Update tail to the new node
        size += 1; // Increase the size by 1
    }

    // Insert a node at a specific index in the list
    // Time Complexity: O(n) in worst case (when inserting at the end)
    // Space Complexity: O(1)
    public void insert(int val, int index) {
        if (index == 0) { // If inserting at the beginning
            insertFirst(val); // Use insertFirst method
            return;
        }
        if (index == size) { // If inserting at the end
            insertLast(val); // Use insertLast method
            return;
        }
        Node temp = head;
        for (int i = 1; i < index; i++) { // Traverse to node before index
            temp = temp.next;
        }
        Node node = new Node(val, temp.next); // Create new node and link it
        temp.next = node; // Update previous node's next reference
        size++; // Increase the size by 1
    }

    // Delete the first node from the list and return its value
    // Time Complexity: O(1)
    // Space Complexity: O(1)
    public int deleteFirst() {
        int val = head.value; // Store value of head node
        head = head.next; // Move head to the next node
        if (head == null) { // If the list becomes empty
            tail = null; // Set tail to null
        }
        size--; // Decrease the size by 1
        return val; // Return the removed value
    }

    // Delete the last node from the list and return its value
    // Time Complexity: O(n) because we may need to traverse the list
    // Space Complexity: O(1)
    public int deleteLast() {
        if (size <= 1) { // If list has 0 or 1 elements
            return deleteFirst(); // Delete the first node
        }
        Node secondLast = get(size - 2); // Get second last node
        int val = tail.value; // Store value of the last node
        tail = secondLast; // Update tail to second last node
        tail.next = null; // Remove reference to last node
        size--; // Decrease the size by 1
        return val; // Return the removed value
    }

    // Delete node at specific index and return its value
    // Time Complexity: O(n) in worst case
    // Space Complexity: O(1)
    public int delete(int index) {
        if (index == 0) { // If deleting the first node
            return deleteFirst();
        }
        if (index == size - 1) { // If deleting the last node
            return deleteLast();
        }
        Node prev = get(index - 1); // Get the node before the one to delete
        int val = prev.next.value; // Store the value of node to delete
        prev.next = prev.next.next; // Bypass the node to delete
        size--; // Decrease the size by 1
        return val; // Return the removed value
    }

    // Find a node by its value
    // Time Complexity: O(n) in worst case
    // Space Complexity: O(1)
    public Node find(int value) {
        Node node = head; // Start from head
        while (node != null) { // Traverse until end
            if (node.value == value) { // If value matches
                return node; // Return the node
            }
            node = node.next; // Move to next node
        }
        return null; // Return null if not found
    }

    // Get the node at a specific index
    // Time Complexity: O(n) in worst case
    // Space Complexity: O(1)
    public Node get(int index) {
        Node node = head; // Start from head
        for (int i = 0; i < index; i++) { // Traverse until index
            node = node.next;
        }
        return node; // Return the node at index
    }

    // Display all elements in the linked list
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public void display() {
        Node temp = head; // Start from head
        while (temp != null) { // Traverse until end
            System.out.print(temp.value + "->"); // Print value followed by arrow
            temp = temp.next; // Move to next node
        }
        System.out.print("END"); // Indicate end of list
    }

    // Inner class representing a node in the linked list
    private class Node {
        private int value; // Store the value
        private Node next; // Reference to the next node

        // Constructor to create node with value only
        public Node(int value) {
            this.value = value;
        }

        // Constructor to create node with value and reference to next node
        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
