// Problem Statement: Implementation of Queue using Arrays
// Class Name (short & concise as per instruction): QueueArray
// Goal: Implement queue operations (enqueue, dequeue, peek, isEmpty, isFull) using arrays.
// Core Principle: Queue follows FIFO (First In, First Out)

/*
──────────────────────────── VISUALIZATION ─────────────────────────────
Think of a queue like a line of people waiting for tickets — 
the one who comes first leaves first (FIFO).

Representation using array (front → rear):

Initial State (capacity = 5):
[ ] [ ] [ ] [ ] [ ]    ← Empty Queue (front = -1, rear = -1)

Enqueue(10): [10] [ ] [ ] [ ] [ ]     front=0, rear=0
Enqueue(20): [10] [20] [ ] [ ] [ ]    front=0, rear=1
Enqueue(30): [10] [20] [30] [ ] [ ]   front=0, rear=2

Dequeue(): removes 10 → [ ] [20] [30] [ ] [ ]   front=1, rear=2
Peek(): shows 20

──────────────────────────── TIME COMPLEXITY ────────────────────────────
Enqueue() → O(1)  // Constant time insertion at rear
Dequeue() → O(1)  // Constant time removal from front
Peek()    → O(1)  // Constant time access of front element
isEmpty() → O(1)
isFull()  → O(1)

Space Complexity → O(n) where n = size of array

──────────────────────────── OPTIMIZATION ──────────────────────────────
We’ll use a **Circular Queue** concept to make it efficient.
Without circular approach, once rear reaches end of array,
we can’t insert even if there’s space at the beginning.
────────────────────────────────────────────────────────────────────────
*/

public class QueueArray {

    // Array to store queue elements
    private int[] queue;

    // Pointers for front and rear positions
    private int front, rear;

    // Capacity of queue
    private int capacity;

    // Current size of queue
    private int count;

    // Constructor: initializes queue
    public QueueArray(int size) {
        queue = new int[size];  // allocate array
        capacity = size;        // store capacity
        front = 0;              // initially front = 0
        rear = -1;              // rear = -1 (no elements yet)
        count = 0;              // initially empty
    }

    // ────────────────────────────────────────────────────────────────
    // ENQUEUE OPERATION → Add an element at the rear
    // Time Complexity: O(1)
    // ────────────────────────────────────────────────────────────────
    public void enqueue(int item) {
        // Check if queue is full
        if (isFull()) {
            System.out.println("Queue Overflow! Cannot enqueue " + item);
            return;
        }

        // Move rear pointer circularly (mod capacity)
        rear = (rear + 1) % capacity;

        // Insert item at rear position
        queue[rear] = item;

        // Increment count of elements
        count++;

        System.out.println(item + " enqueued into queue.");
    }

    // ────────────────────────────────────────────────────────────────
    // DEQUEUE OPERATION → Remove element from front
    // Time Complexity: O(1)
    // ────────────────────────────────────────────────────────────────
    public int dequeue() {
        // Check if queue is empty
        if (isEmpty()) {
            System.out.println("Queue Underflow! Nothing to dequeue.");
            return -1; // invalid return for empty queue
        }

        // Get front element
        int item = queue[front];

        // Move front pointer circularly
        front = (front + 1) % capacity;

        // Decrease count
        count--;

        System.out.println(item + " dequeued from queue.");
        return item;
    }

    // ────────────────────────────────────────────────────────────────
    // PEEK OPERATION → View the element at the front
    // Time Complexity: O(1)
    // ────────────────────────────────────────────────────────────────
    public int peek() {
        // Check if empty
        if (isEmpty()) {
            System.out.println("Queue is empty! No front element.");
            return -1;
        }

        System.out.println("Front element is: " + queue[front]);
        return queue[front];
    }

    // ────────────────────────────────────────────────────────────────
    // isEmpty() → Returns true if queue is empty
    // Time Complexity: O(1)
    // ────────────────────────────────────────────────────────────────
    public boolean isEmpty() {
        return count == 0;
    }

    // ────────────────────────────────────────────────────────────────
    // isFull() → Returns true if queue is full
    // Time Complexity: O(1)
    // ────────────────────────────────────────────────────────────────
    public boolean isFull() {
        return count == capacity;
    }

    // ────────────────────────────────────────────────────────────────
    // DISPLAY OPERATION → Shows all elements in the queue
    // Time Complexity: O(n)
    // ────────────────────────────────────────────────────────────────
    public void display() {
        // If queue is empty
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return;
        }

        System.out.print("Current Queue (Front → Rear): ");

        // Print elements in correct circular order
        for (int i = 0; i < count; i++) {
            int index = (front + i) % capacity;
            System.out.print(queue[index] + " ");
        }
        System.out.println();
    }

    // ────────────────────────────────────────────────────────────────
    // MAIN METHOD → Demonstration of Queue operations
    // ────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        // Create a queue of size 5
        QueueArray q = new QueueArray(5);

        // Enqueue elements
        q.enqueue(10); // Queue: [10]
        q.enqueue(20); // Queue: [10, 20]
        q.enqueue(30); // Queue: [10, 20, 30]
        q.enqueue(40); // Queue: [10, 20, 30, 40]
        q.display();   // Shows current state

        // Peek the front element
        q.peek(); // Should show 10

        // Dequeue two elements
        q.dequeue(); // Removes 10
        q.dequeue(); // Removes 20
        q.display(); // Queue: [30, 40]

        // Add more elements to show circular behavior
        q.enqueue(50);
        q.enqueue(60);
        q.enqueue(70); // Circularly fills remaining space
        q.display();   // Queue after circular enqueue

        // Try to enqueue when full
        q.enqueue(80); // Overflow test

        // Dequeue all elements
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue(); // Underflow test
    }
}
