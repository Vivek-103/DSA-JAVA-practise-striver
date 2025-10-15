// Problem Statement: Implementation of Stack using Queues
// Class Name (short & concise as per instruction): StackUsingQueue
// Goal: Implement stack operations (push, pop, peek, isEmpty) using queues.
// Principle: Stack follows LIFO (Last In, First Out)
// But Queue follows FIFO (First In, First Out)
// Hence, we need to manipulate queues to simulate stack behavior.

/*
──────────────────────────── VISUALIZATION ─────────────────────────────
We use TWO queues (queue1 and queue2) to simulate stack behavior.

Let’s push elements 10, 20, 30 (stack top should be 30):

queue1 (after push): [30, 20, 10]
Visual interpretation (front → rear): 10 → 20 → 30
But we arrange it such that "30" behaves like the TOP of stack.

How? Every time we push an element, we:
→ Insert new element into queue2
→ Move all elements from queue1 → queue2
→ Swap names of queue1 and queue2

This ensures queue1 always has the “top” element at the front.

──────────────────────────── TIME COMPLEXITY ─────────────────────────────
Push (costly method): O(n)    // Need to rearrange elements
Pop: O(1)                     // Just remove from front
Peek: O(1)                    // Access front
isEmpty: O(1)
Space Complexity: O(n)
─────────────────────────────────────────────────────────────────────────
*/

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueue {

    // ─────────────────────────────
    // Define two queues
    // ─────────────────────────────
    private Queue<Integer> queue1 = new LinkedList<>();  // main queue (acts as stack)
    private Queue<Integer> queue2 = new LinkedList<>();  // helper queue for operations

    // ────────────────────────────────────────────────────────────────
    // PUSH OPERATION → Add element to the stack (top)
    // Time Complexity: O(n) because we move all elements each time
    // ────────────────────────────────────────────────────────────────
    public void push(int data) {
        // Step 1: Enqueue new element into queue2
        queue2.add(data);
        System.out.println(data + " pushed into stack.");

        // Step 2: Move all elements from queue1 → queue2
        while (!queue1.isEmpty()) {
            queue2.add(queue1.remove());
        }

        // Step 3: Swap names of queue1 and queue2
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;

        // Now, queue1 has the latest pushed element at the front
    }

    // ────────────────────────────────────────────────────────────────
    // POP OPERATION → Remove top element from the stack
    // Time Complexity: O(1)
    // ────────────────────────────────────────────────────────────────
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow! Nothing to pop.");
            return -1;
        }

        // Dequeue from queue1 (front element = top of stack)
        int value = queue1.remove();
        System.out.println(value + " popped from stack.");
        return value;
    }

    // ────────────────────────────────────────────────────────────────
    // PEEK OPERATION → View the top element without removing
    // Time Complexity: O(1)
    // ────────────────────────────────────────────────────────────────
    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty! No top element.");
            return -1;
        }

        // The front element of queue1 is the top of stack
        System.out.println("Top element is: " + queue1.peek());
        return queue1.peek();
    }

    // ────────────────────────────────────────────────────────────────
    // isEmpty() → Check if the stack is empty
    // Time Complexity: O(1)
    // ────────────────────────────────────────────────────────────────
    public boolean isEmpty() {
        return queue1.isEmpty();
    }

    // ────────────────────────────────────────────────────────────────
    // DISPLAY OPERATION → Show all elements from top to bottom
    // Time Complexity: O(n)
    // ────────────────────────────────────────────────────────────────
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return;
        }

        System.out.print("Current Stack (Top → Bottom): ");
        for (int val : queue1) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    // ────────────────────────────────────────────────────────────────
    // MAIN METHOD → Demonstration & Testing
    // ────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        StackUsingQueue stack = new StackUsingQueue();

        // Push elements
        stack.push(10); // Stack: [10]
        stack.push(20); // Stack: [20, 10]
        stack.push(30); // Stack: [30, 20, 10]
        stack.display();

        // Peek the top element
        stack.peek(); // Should show 30

        // Pop elements
        stack.pop();  // Removes 30
        stack.display(); // Now: [20, 10]

        // Push more elements
        stack.push(40);
        stack.push(50);
        stack.display(); // Now: [50, 40, 20, 10]

        // Pop all elements to test underflow
        stack.pop(); // Removes 50
        stack.pop(); // Removes 40
        stack.pop(); // Removes 20
        stack.pop(); // Removes 10
        stack.pop(); // Underflow test
    }
}

/*
──────────────────────────── VISUALIZATION SUMMARY ─────────────────────────────
Initially:
queue1 = [], queue2 = []

Push(10):
→ queue2 = [10], move all queue1 → queue2 → swap
→ queue1 = [10]

Push(20):
→ queue2 = [20], move queue1 [10] → queue2
→ queue2 = [20, 10]
→ swap queues
→ queue1 = [20, 10]

Push(30):
→ queue2 = [30]
→ move [20, 10] → queue2 = [30, 20, 10]
→ swap → queue1 = [30, 20, 10]

Pop():
→ Remove front of queue1 → removes 30 (top of stack)

──────────────────────────── TIME COMPLEXITY SUMMARY ─────────────────────────────
Operation   | Time Complexity | Space Complexity | Description
------------------------------------------------------------------
Push        | O(n)            | O(n)             | Move all elements on each push
Pop         | O(1)            | O(n)             | Remove from front
Peek        | O(1)            | O(n)             | View front element
isEmpty     | O(1)            | O(1)             | Check if queue empty
Display     | O(n)            | O(n)             | Print all elements
──────────────────────────────────────────────────────────────────────────────────
*/
