/*
    Problem: Implement a Circular Linked List (CLL) in Java
    Features:
    - Insert at beginning & end
    - Delete first, last, or a given value
    - Display list in circular order
    - Find a node
    - Get size of list

    Example Usage:
    CircularLL list = new CircularLL();
    list.insert(10);           // 10 -> HEAD
    list.insert(20);           // 10 -> 20 -> HEAD
    list.insertFirst(5);       // 5 -> 10 -> 20 -> HEAD
    list.display();

    list.delete(10);           // 5 -> 20 -> HEAD
    list.deleteFirst();        // 20 -> HEAD
    list.deleteLast();         // empty list
*/

public class CircularLL {
    private Node head;  // points to the first node
    private Node tail;  // points to the last node

    public CircularLL() {
        this.head = null;
        this.tail = null;
    }

    // ================= INSERTION =================

    // Insert node at the END
    // Time Complexity: O(1), Space Complexity: O(1)
    public void insert(int val) {
        Node node = new Node(val);          // create new node
        if (head == null) {                 // case: empty list
            head = node;
            tail = node;
            node.next = head;               // circular link (self-loop)
        } else {                            // case: non-empty list
            tail.next = node;               // last node points to new node
            node.next = head;               // new node points back to head
            tail = node;                    // update tail
        }
    }

    // Insert node at the BEGINNING
    // Time Complexity: O(1), Space Complexity: O(1)
    public void insertFirst(int val) {
        Node node = new Node(val);
        if (head == null) {                 // case: empty list
            head = node;
            tail = node;
            node.next = head;               // circular link
        } else {                            // case: non-empty
            node.next = head;               // new node points to old head
            head = node;                    // head moves to new node
            tail.next = head;               // tail points to new head
        }
    }

    // ================= DELETION =================

    // Delete first node
    // Time Complexity: O(1), Space Complexity: O(1)
    public void deleteFirst() {
        if (head == null) {                 // empty list
            System.out.println("List is empty!");
            return;
        }
        if (head == tail) {                 // single element
            head = null;
            tail = null;
        } else {
            head = head.next;               // move head forward
            tail.next = head;               // tail reconnects to new head
        }
    }

    // Delete last node
    // Time Complexity: O(n), Space Complexity: O(1)
    public void deleteLast() {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }
        if (head == tail) {                 // single element
            head = null;
            tail = null;
            return;
        }
        Node temp = head;
        // find node before tail
        while (temp.next != tail) {
            temp = temp.next;
        }
        temp.next = head;                   // new last points to head
        tail = temp;                        // update tail
    }

    // Delete node with given value
    // Time Complexity: O(n), Space Complexity: O(1)
    public void delete(int val) {
        if (head == null) {                 // empty list
            return;
        }
        if (head.val == val) {              // if value is at head
            deleteFirst();
            return;
        }
        Node node = head;
        do {
            Node n = node.next;
            if (n.val == val) {             // if next node has the value
                if (n == tail) {            // if it's the last node
                    tail = node;            // move tail back
                }
                node.next = n.next;         // bypass node
                break;
            }
            node = node.next;
        } while (node != head);
    }

    // ================= UTILITY METHODS =================

    // Find node by value
    // Time Complexity: O(n), Space Complexity: O(1)
    public Node find(int val) {
        if (head == null) return null;
        Node temp = head;
        do {
            if (temp.val == val) {
                return temp;                // found node
            }
            temp = temp.next;
        } while (temp != head);
        return null;                        // not found
    }

    // Get size of circular linked list
    // Time Complexity: O(n), Space Complexity: O(1)
    public int size() {
        if (head == null) return 0;
        int count = 0;
        Node temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);
        return count;
    }

    // Display the circular linked list
    // Time Complexity: O(n), Space Complexity: O(1)
    public void display() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        Node temp = head;
        do {
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        } while (temp != head);
        System.out.println("HEAD");
    }

    // ================= NODE CLASS =================
    private class Node {
        int val;     // value of node
        Node next;   // pointer to next node

        Node(int val) {
            this.val = val;
        }
    }
}
