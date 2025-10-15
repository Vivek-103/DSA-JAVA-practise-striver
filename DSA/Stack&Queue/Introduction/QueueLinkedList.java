// Problem Statement: Implementation of Queue using Linked List
// Class Name (short & concise as per instruction): QueueLinkedList
// Goal: Implement queue operations (enqueue, dequeue, peek, isEmpty) using a linked list.
// Core Principle: Queue follows FIFO (First In, First Out)

/*
──────────────────────────── VISUALIZATION ─────────────────────────────
Think of a queue as a line of people waiting — the first person to enter
is the first one to leave (FIFO).

Representation using LinkedList nodes:

Initially:  null  ← empty queue (front = null, rear = null)

Enqueue(10): front → [10] → null         rear = [10]
Enqueue(20): front → [10] → [20] → null  rear = [20]
Enqueue(30): front → [10] → [20] → [30] → null  rear = [30]

Dequeue(): removes 10
Now:       front → [20] → [30] → null

Peek(): shows 20

──────────────────────────── TIME COMPLEXITY ────────────────────────────
Enqueue() → O(1)  // Insertion at rear
Dequeue() → O(1)  // Deletion from front
Peek()    → O(1)  // Access front node
isEmpty() → O(1)  // Simple null check
Display() → O(n)  // Traverses all nodes
Space Complexity → O(n) where n = number of elements in the queue
────────────────────────────────────────────────────────────────────────
*/

public class QueueLinkedList {

    // ─────────────────────────────
    // Node class represents one element of the queue
    // ─────────────────────────────
    private class Node {
        int data;     // Value stored in the node
        Node next;    // Pointer to the next node

        // Constructor to initialize node with given data
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Pointers for front (dequeue) and rear (enqueue) of the queue
    private Node front, rear;

    // Constructor: initializes an empty queue
    public QueueLinkedList() {
        front = rear = null;
    }

    // ────────────────────────────────────────────────────────────────
    // ENQUEUE OPERATION → Add element to the rear (end) of the queue
    // Time Complexity: O(1)
    // ────────────────────────────────────────────────────────────────
    public void enqueue(int data) {
        // Step 1: Create a new node with the given data
        Node newNode = new Node(data);

        // Step 2: If queue is empty, front and rear both become this node
        if (rear == null) {
            front = rear = newNode;
            System.out.println(data + " enqueued into queue.");
            return;
        }

        // Step 3: Otherwise, link new node at the end and update rear
        rear.next = newNode;
        rear = newNode;

        System.out.println(data + " enqueued into queue.");
    }

    // ────────────────────────────────────────────────────────────────
    // DEQUEUE OPERATION → Remove element from front of the queue
    // Time Complexity: O(1)
    // ────────────────────────────────────────────────────────────────
    public int dequeue() {
        // Step 1: Check if queue is empty
        if (isEmpty()) {
            System.out.println("Queue Underflow! Nothing to dequeue.");
            return -1; // Invalid value for empty queue
        }

        // Step 2: Get the front node's data
        int value = front.data;

        // Step 3: Move front to next node
        front = front.next;

        // Step 4: If front becomes null, then queue is empty → rear also null
        if (front == null) {
            rear = null;
        }

        System.out.println(value + " dequeued from queue.");
        return value;
    }

    // ────────────────────────────────────────────────────────────────
    // PEEK OPERATION → View the element at the front without removing
    // Time Complexity: O(1)
    // ────────────────────────────────────────────────────────────────
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty! No front element.");
            return -1;
        }

        System.out.println("Front element is: " + front.data);
        return front.data;
    }

    // ────────────────────────────────────────────────────────────────
    // isEmpty() → Checks if the queue is empty
    // Time Complexity: O(1)
    // ────────────────────────────────────────────────────────────────
    public boolean isEmpty() {
        return front == null; // True if no elements
    }

    // ────────────────────────────────────────────────────────────────
    // DISPLAY OPERATION → Print all queue elements from front to rear
    // Time Complexity: O(n)
    // ────────────────────────────────────────────────────────────────
    public void display() {
        // Check if queue is empty first
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return;
        }

        System.out.print("Current Queue (Front → Rear): ");
        Node temp = front; // Start from front
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next; // Move forward
        }
        System.out.println();
    }

    // ────────────────────────────────────────────────────────────────
    // MAIN METHOD → Demonstration and Testing of Queue Operations
    // ────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        // Create a new queue
        QueueLinkedList queue = new QueueLinkedList();

        // Enqueue elements
        queue.enqueue(10); // Queue: [10]
        queue.enqueue(20); // Queue: [10, 20]
        queue.enqueue(30); // Queue: [10, 20, 30]
        queue.display();   // Show current queue

        // Peek the front element
        queue.peek();      // Shows 10

        // Dequeue elements
        queue.dequeue();   // Removes 10
        queue.display();   // Queue: [20, 30]

        // Add more elements
        queue.enqueue(40);
        queue.enqueue(50);
        queue.display();   // Queue: [20, 30, 40, 50]

        // Dequeue all elements to test underflow
        queue.dequeue(); // Removes 20
        queue.dequeue(); // Removes 30
        queue.dequeue(); // Removes 40
        queue.dequeue(); // Removes 50
        queue.dequeue(); // Underflow test
    }
}
