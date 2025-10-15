// Problem Statement: Implementation of Queue using Stack
// Class Name (short & concise as per instruction): QueueUsingStack
// Goal: Implement queue operations (enqueue, dequeue, peek, isEmpty) using two stacks
// Key Principle: Queue follows FIFO (First In, First Out)
// But Stack follows LIFO (Last In, First Out)
// So we need to use TWO stacks to simulate the FIFO behavior

/*
──────────────────────────── VISUALIZATION ─────────────────────────────
We have TWO stacks: stack1 and stack2

stack1 → Used for enqueue operations
stack2 → Used for dequeue operations

Example:
Enqueue(10), Enqueue(20), Enqueue(30)
stack1: [10, 20, 30]   (top = 30)
stack2: []

Dequeue():
→ Move all elements from stack1 → stack2
   stack1: []             stack2: [30, 20, 10]
→ Pop from stack2 (which gives 10 → front of queue)

After Dequeue:
stack1: []              stack2: [30, 20]

Now stack2 top represents front of queue.
───────────────────────────────────────────────────────────────────────
TIME COMPLEXITY (Optimal Two-Stack Method)
───────────────────────────────────────────────────────────────────────
Enqueue (push operation) → O(1)
Dequeue (amortized)     → O(1) average, O(n) worst case
Peek                    → O(1) average
isEmpty                 → O(1)
Space Complexity        → O(n) for storing all queue elements
───────────────────────────────────────────────────────────────────────
*/

import java.util.Stack;

public class QueueUsingStack {

    // ─────────────────────────────
    // Define two stacks
    // ─────────────────────────────
    private Stack<Integer> stack1 = new Stack<>(); // For enqueue
    private Stack<Integer> stack2 = new Stack<>(); // For dequeue

    // ────────────────────────────────────────────────────────────────
    // ENQUEUE OPERATION → Add element at the end of the queue
    // Time Complexity: O(1)
    // ────────────────────────────────────────────────────────────────
    public void enqueue(int data) {
        // Just push the new element into stack1
        stack1.push(data);
        System.out.println(data + " enqueued into queue.");
    }

    // ────────────────────────────────────────────────────────────────
    // DEQUEUE OPERATION → Remove element from the front of the queue
    // Time Complexity: Amortized O(1), Worst-case O(n)
    // ────────────────────────────────────────────────────────────────
    public int dequeue() {
        // Case 1: If both stacks are empty → Queue underflow
        if (isEmpty()) {
            System.out.println("Queue Underflow! Nothing to dequeue.");
            return -1;
        }

        // Case 2: If stack2 is empty → transfer all elements from stack1
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        // Case 3: Now pop from stack2 (front of queue)
        int value = stack2.pop();
        System.out.println(value + " dequeued from queue.");
        return value;
    }

    // ────────────────────────────────────────────────────────────────
    // PEEK OPERATION → View front element without removing
    // Time Complexity: Amortized O(1), Worst-case O(n)
    // ────────────────────────────────────────────────────────────────
    public int peek() {
        // Case 1: Empty queue
        if (isEmpty()) {
            System.out.println("Queue is empty! No front element.");
            return -1;
        }

        // Case 2: If stack2 is empty, move elements from stack1
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        // The top of stack2 is the front of queue
        System.out.println("Front element is: " + stack2.peek());
        return stack2.peek();
    }

    // ────────────────────────────────────────────────────────────────
    // isEmpty() → Check if both stacks are empty
    // Time Complexity: O(1)
    // ────────────────────────────────────────────────────────────────
    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    // ────────────────────────────────────────────────────────────────
    // DISPLAY OPERATION → Show all elements in queue order
    // Time Complexity: O(n)
    // ────────────────────────────────────────────────────────────────
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return;
        }

        // Visualization: print in correct FIFO order
        System.out.print("Current Queue (Front → Rear): ");

        // To avoid changing actual data, use temporary stacks
        Stack<Integer> tempStack1 = new Stack<>();
        Stack<Integer> tempStack2 = new Stack<>();

        // Step 1: Copy stack2 to tempStack1 (since stack2 holds front)
        tempStack1.addAll(stack2);

        // Step 2: Reverse stack1 to tempStack2 for correct order
        while (!stack1.isEmpty()) {
            tempStack2.push(stack1.pop());
        }

        // Step 3: Print stack2 elements (front part)
        for (int i = tempStack1.size() - 1; i >= 0; i--) {
            System.out.print(tempStack1.get(i) + " ");
        }

        // Step 4: Print reversed stack1 elements (rear part)
        for (int i = 0; i < tempStack2.size(); i++) {
            System.out.print(tempStack2.get(i) + " ");
        }

        System.out.println();
    }

    // ────────────────────────────────────────────────────────────────
    // MAIN METHOD → Demonstration & Testing
    // ────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        QueueUsingStack queue = new QueueUsingStack();

        // Enqueue elements
        queue.enqueue(10); // Queue: [10]
        queue.enqueue(20); // Queue: [10, 20]
        queue.enqueue(30); // Queue: [10, 20, 30]
        queue.display();

        // Peek front element
        queue.peek(); // Should show 10

        // Dequeue elements
        queue.dequeue(); // Removes 10
        queue.display(); // Now: [20, 30]

        // Enqueue more
        queue.enqueue(40);
        queue.enqueue(50);
        queue.display(); // Now: [20, 30, 40, 50]

        // Multiple dequeues
        queue.dequeue(); // Removes 20
        queue.dequeue(); // Removes 30
        queue.display(); // Now: [40, 50]

        // Peek current front
        queue.peek(); // Shows 40

        // Dequeue all to test underflow
        queue.dequeue(); // Removes 40
        queue.dequeue(); // Removes 50
        queue.dequeue(); // Underflow test
    }
}

/*
──────────────────────────── VISUALIZATION SUMMARY ─────────────────────────────
Initial:
stack1 = [], stack2 = []

Enqueue(10):
stack1 = [10], stack2 = []

Enqueue(20):
stack1 = [10, 20], stack2 = []

Enqueue(30):
stack1 = [10, 20, 30], stack2 = []

Dequeue():
→ Move stack1 → stack2
   stack1: [], stack2: [30, 20, 10]
→ Pop from stack2 → removes 10
Remaining: stack2: [30, 20]
Queue now: [20, 30]

──────────────────────────── TIME COMPLEXITY SUMMARY ─────────────────────────────
Operation   | Best / Average Case | Worst Case | Space
-----------------------------------------------------
Enqueue     | O(1)                | O(1)       | O(n)
Dequeue     | O(1) Amortized      | O(n)       | O(n)
Peek        | O(1) Amortized      | O(n)       | O(n)
isEmpty     | O(1)                | O(1)       | O(1)
Display     | O(n)                | O(n)       | O(n)
──────────────────────────────────────────────────────────────────────────────────
*/
