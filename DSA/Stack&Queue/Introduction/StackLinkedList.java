// Problem Statement: Implementation of Stack Using LinkedList
// Class Name: StackLinkedList
// Goal: Implement basic Stack operations using a LinkedList
// Core Principle: Stack works on LIFO (Last In, First Out)

/*
──────────────────────────── VISUALIZATION ────────────────────────────
Think of the stack as a pile of plates (LIFO):

Initial State:
    null  ← stack is empty

After Push(10):
    [10] -> null                (Top = 10)

After Push(20):
    [20] -> [10] -> null        (Top = 20)

After Push(30):
    [30] -> [20] -> [10] -> null  (Top = 30)

Pop() removes 30:
    [20] -> [10] -> null        (Top = 20)

Peek() shows 20

──────────────────────────── TIME COMPLEXITY ──────────────────────────
Push()   → O(1)   // Inserting new node at top
Pop()    → O(1)   // Removing node from top
Peek()   → O(1)   // Accessing top node only
isEmpty()→ O(1)   // Simple null check
Display()→ O(n)   // Traverse entire stack for display
Space Complexity → O(n)  // Each element stored as a node
───────────────────────────────────────────────────────────────────────
*/

public class StackLinkedList {

    // Inner class Node to represent each element in the linked list
    private class Node {
        int data;     // Stores the value
        Node next;    // Points to the next node in stack

        // Constructor: creates a new node with given data
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Top pointer points to the topmost node of the stack
    private Node top;

    // Constructor: Initializes an empty stack
    public StackLinkedList() {
        top = null;  // Empty stack → top is null
    }

    // ────────────────────────────────────────────────────────────────
    // PUSH OPERATION → Adds a new element on top of the stack
    // Time Complexity: O(1)
    // ────────────────────────────────────────────────────────────────
    public void push(int data) {
        Node newNode = new Node(data);  // Create new node with given data

        // New node's next points to current top
        newNode.next = top;

        // Now newNode becomes the new top
        top = newNode;

        System.out.println(data + " pushed into stack.");
    }

    // ────────────────────────────────────────────────────────────────
    // POP OPERATION → Removes and returns top element
    // Time Complexity: O(1)
    // ────────────────────────────────────────────────────────────────
    public int pop() {
        // Check if stack is empty before popping
        if (isEmpty()) {
            System.out.println("Stack Underflow! Nothing to pop.");
            return -1;  // Return invalid value when empty
        }

        // Store data of top node before removing
        int poppedValue = top.data;

        // Move top to the next node
        top = top.next;

        System.out.println(poppedValue + " popped from stack.");
        return poppedValue;
    }

    // ────────────────────────────────────────────────────────────────
    // PEEK OPERATION → Returns top element without removing it
    // Time Complexity: O(1)
    // ────────────────────────────────────────────────────────────────
    public int peek() {
        // If stack is empty, no top element exists
        if (isEmpty()) {
            System.out.println("Stack is empty! No top element.");
            return -1;
        }

        System.out.println("Top element is: " + top.data);
        return top.data;
    }

    // ────────────────────────────────────────────────────────────────
    // ISEMPTY OPERATION → Checks whether stack is empty
    // Time Complexity: O(1)
    // ────────────────────────────────────────────────────────────────
    public boolean isEmpty() {
        return top == null; // Returns true if stack has no elements
    }

    // ────────────────────────────────────────────────────────────────
    // DISPLAY OPERATION → Prints all elements in the stack
    // Time Complexity: O(n)
    // ────────────────────────────────────────────────────────────────
    public void display() {
        // If stack is empty, print message
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return;
        }

        // Traverse from top to bottom
        Node temp = top;
        System.out.print("Current Stack (Top → Bottom): ");
        while (temp != null) {
            System.out.print(temp.data + " ");  // Print node data
            temp = temp.next;                   // Move to next node
        }
        System.out.println();
    }

    // ────────────────────────────────────────────────────────────────
    // MAIN METHOD → Demonstration and testing of stack operations
    // ────────────────────────────────────────────────────────────────
    public static void main(String[] args) {

        // Create a new stack
        StackLinkedList stack = new StackLinkedList();

        // Push elements into stack
        stack.push(10);  // Stack: [10]
        stack.push(20);  // Stack: [20, 10]
        stack.push(30);  // Stack: [30, 20, 10]
        stack.display(); // Display current stack

        // Peek the top element
        stack.peek();    // Shows 30

        // Pop elements one by one
        stack.pop();     // Removes 30
        stack.display(); // Stack now: [20, 10]

        // Push more elements
        stack.push(40);
        stack.push(50);
        stack.display(); // Stack: [50, 40, 20, 10]

        // Pop all elements to test underflow
        stack.pop();  // Removes 50
        stack.pop();  // Removes 40
        stack.pop();  // Removes 20
        stack.pop();  // Removes 10
        stack.pop();  // Underflow (empty stack)
    }
}
