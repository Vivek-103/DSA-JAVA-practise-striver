import java.util.Stack;

public class ReverseStack {

    /*
     * -------------------- PROBLEM: Reverse a Stack using Recursion --------------------
     * 
     * Goal:
     *  - Reverse the given stack without using any extra data structure.
     *  - Use only recursion (implicit call stack).
     * 
     * Approach (Recursive):
     *  1. Pop the top element from the stack.
     *  2. Recursively reverse the remaining stack.
     *  3. Insert the popped element at the bottom of the reversed stack.
     * 
     * Visualization:
     *  Initial Stack (Top on right): [1, 2, 3, 4, 5]
     *  Step 1: Pop 5 → reverse([1, 2, 3, 4])
     *  Step 2: Pop 4 → reverse([1, 2, 3])
     *  Step 3: Pop 3 → reverse([1, 2])
     *  Step 4: Pop 2 → reverse([1])
     *  Base Case Hit → Start Inserting at Bottom:
     *      Insert 2 → [2, 1]
     *      Insert 3 → [3, 2, 1]
     *      Insert 4 → [4, 3, 2, 1]
     *      Insert 5 → [5, 4, 3, 2, 1]
     * 
     * ✅ Final Reversed Stack: [5, 4, 3, 2, 1]
     * 
     * -------------------- COMPLEXITY ANALYSIS --------------------
     * Time Complexity:  O(N²)
     *   - Each element can be moved multiple times (popped and reinserted)
     * Space Complexity: O(N)
     *   - Due to recursion call stack
     * -------------------------------------------------------------
     */

    // Function to reverse the stack recursively
    public static void reverse(Stack<Integer> stack) {
        // Base Case: if stack is empty, return
        if (stack.isEmpty()) return;

        // Step 1: Pop the top element
        int top = stack.pop();

        // Step 2: Recursively reverse the rest of the stack
        reverse(stack);

        // Step 3: Insert the popped element at the bottom
        insertAtBottom(stack, top);
    }

    // Helper function to insert an element at the bottom of a stack
    private static void insertAtBottom(Stack<Integer> stack, int value) {
        // Base Case: if stack is empty, place the element here
        if (stack.isEmpty()) {
            stack.push(value);
            return;
        }

        // Step 1: Pop the top element
        int top = stack.pop();

        // Step 2: Recursively go deeper until bottom is reached
        insertAtBottom(stack, value);

        // Step 3: After insertion, push back the top element
        stack.push(top);
    }

    // Main method to test our recursive reversal
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        // Push elements into stack
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        // Print original stack
        System.out.println("Original Stack: " + stack);

        // Reverse the stack using recursion
        reverse(stack);

        // Print reversed stack
        System.out.println("Reversed Stack: " + stack);
    }
}
