import java.util.Stack;

public class SortStack {
    
    // ✅ Function to sort a stack using recursion
    public static void sortStack(Stack<Integer> stack) {
        // Base case: if stack has only one element, it's already sorted
        if (stack.size() <= 1) return;

        // Step 1: Remove the top element
        int top = stack.pop();

        // Step 2: Recursively sort the remaining stack
        sortStack(stack);

        // Step 3: Insert the popped element back into the correct position
        insertInSortedOrder(stack, top);
    }

    // ✅ Helper function to insert an element in a sorted stack
    private static void insertInSortedOrder(Stack<Integer> stack, int element) {
        // Base case: if stack is empty or element is greater than top, push it
        if (stack.isEmpty() || element > stack.peek()) {
            stack.push(element);
            return;
        }

        // Otherwise, remove the top and insert the element recursively
        int top = stack.pop();

        // Recursively insert the current element in correct position
        insertInSortedOrder(stack, element);

        // Push back the top element after placing 'element'
        stack.push(top);
    }

    // ✅ Main function to test the implementation
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(34);
        stack.push(3);
        stack.push(31);
        stack.push(98);
        stack.push(92);
        stack.push(23);

        System.out.println("Original Stack: " + stack);

        // Sort the stack
        sortStack(stack);

        System.out.println("Sorted Stack: " + stack);
    }
}

/*
🧠 Approach Explanation:
-------------------------
1️⃣ Remove the top element of the stack.
2️⃣ Recursively sort the rest of the stack.
3️⃣ Insert the removed element back into the sorted stack 
   using another recursive helper function.

- No extra data structures are used — recursion acts as a temporary stack.
- Sorting is done in descending order (top = smallest), but can be reversed by changing the comparison.

🕒 Time Complexity:
-------------------------
O(N²)
➡ Each element is inserted into the sorted stack by traversing elements below it recursively.

🧮 Space Complexity:
-------------------------
O(N)
➡ Due to recursive call stack.
*/
