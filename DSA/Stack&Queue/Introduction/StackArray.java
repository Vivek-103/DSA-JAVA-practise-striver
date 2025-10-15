// Problem: Implementation of Stack using Arrays
// Class Name (short & concise as per instruction): StackArray
// Goal: To implement basic stack operations (push, pop, peek, isEmpty, isFull) using an array

// Time Complexity:
// 1. Push Operation → O(1)  (constant time insertion)
// 2. Pop Operation  → O(1)  (constant time removal)
// 3. Peek Operation → O(1)  (constant time access)
// 4. isEmpty/isFull → O(1)  (constant time checks)

// Visualization:
/*
   Imagine the stack as a vertical column:
   
   Initially:  [ ]  [ ]  [ ]  [ ]  [ ]     <-- Empty Stack (size = 5)

   Push(10):   [10] [ ]  [ ]  [ ]  [ ]     <-- Top = 0
   Push(20):   [10] [20] [ ]  [ ]  [ ]     <-- Top = 1
   Push(30):   [10] [20] [30] [ ]  [ ]     <-- Top = 2

   Pop(): Removes 30 → [10] [20] [ ]  [ ]  [ ]   <-- Top = 1

   Peek(): Shows 20 (top element)
*/

public class StackArray {
    
    // Declare array to store stack elements
    private int[] stack;
    
    // Variable to keep track of the top element's index
    private int top;
    
    // Variable to store the maximum size of the stack
    private int capacity;

    // Constructor to initialize stack with a specific size
    public StackArray(int size) {
        stack = new int[size];   // Allocate memory for stack
        capacity = size;         // Store the size
        top = -1;                // Stack is initially empty, so top = -1
    }

    // Push Operation: Add element to top of stack
    public void push(int item) {
        // Check if stack is already full
        if (isFull()) {
            System.out.println("Stack Overflow! Cannot push " + item);
            return;  // Stop further execution
        }
        // Increment top and insert new element
        stack[++top] = item;
        System.out.println(item + " pushed into stack.");
    }

    // Pop Operation: Remove top element from stack
    public int pop() {
        // Check if stack is empty before popping
        if (isEmpty()) {
            System.out.println("Stack Underflow! Nothing to pop.");
            return -1;  // Return invalid value when empty
        }
        // Return and remove the top element
        int poppedElement = stack[top--];
        System.out.println(poppedElement + " popped from stack.");
        return poppedElement;
    }

    // Peek Operation: View the top element without removing it
    public int peek() {
        // Check if stack is empty
        if (isEmpty()) {
            System.out.println("Stack is empty! No top element.");
            return -1;
        }
        // Return the element at the top
        System.out.println("Top element is: " + stack[top]);
        return stack[top];
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return top == -1;  // True when no elements in stack
    }

    // Check if the stack is full
    public boolean isFull() {
        return top == capacity - 1;  // True when top reaches last index
    }

    // Display all stack elements for visualization
    public void display() {
        // Check if stack is empty first
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return;
        }
        System.out.println("Current Stack:");
        // Print from bottom to top for better understanding
        for (int i = 0; i <= top; i++) {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }

    // Main method to test the stack implementation
    public static void main(String[] args) {
        // Create a stack of size 5
        StackArray s = new StackArray(5);

        // Perform various stack operations step by step
        s.push(10); // Stack: [10]
        s.push(20); // Stack: [10, 20]
        s.push(30); // Stack: [10, 20, 30]
        s.display(); // Shows current stack elements

        s.peek();   // Shows top element (30)
        s.pop();    // Removes top (30)
        s.display(); // Stack after pop: [10, 20]
        
        s.push(40);
        s.push(50);
        s.push(60); // Now full
        s.push(70); // Overflow test
        s.display();
    }
}
