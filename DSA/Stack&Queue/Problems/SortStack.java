// Problem Statement: Sort a Stack
// Class Name: SortStack
// Goal: Sort a stack such that the smallest elements are on top (ascending order)

// Example:
// Input Stack (Top → Bottom): [3, 1, 4, 2]
// Output Stack (Top → Bottom): [1, 2, 3, 4]

import java.util.Stack;

public class SortStack {

    // Function to sort the stack recursively
    public static void sortStack(Stack<Integer> stack) {
        // Base condition: if stack has only one element, it's already sorted
        if (stack.size() <= 1)
            return;

        // Step 1: Remove the top element
        int top = stack.pop();

        // Step 2: Sort the remaining stack recursively
        sortStack(stack);

        // Step 3: Insert the popped element back in sorted order
        insertInSortedOrder(stack, top);
    }

    // Helper function to insert element into correct position in a sorted stack
    private static void insertInSortedOrder(Stack<Integer> stack, int element) {

        // Base Case: If stack is empty or top element is smaller than current element
        // then simply push the element
        if (stack.isEmpty() || stack.peek() <= element) {
            stack.push(element);
            return;
        }

        // Step 1: Pop the top element if it's greater than current element
        int top = stack.pop();

        // Step 2: Recursively call insertInSortedOrder for remaining elements
        insertInSortedOrder(stack, element);

        // Step 3: Push the previously popped element back
        stack.push(top);
    }

    // Main method to test
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        // Pushing elements into stack (unsorted)
        stack.push(3);
        stack.push(1);
        stack.push(4);
        stack.push(2);

        System.out.println("Original Stack (Top → Bottom): " + stack);

        sortStack(stack);

        System.out.println("Sorted Stack (Top → Bottom): " + stack);
    }
}

/*
─────────────────────────────────────────────
🧠 VISUALIZATION:

Initial Stack (Top → Bottom): [2, 4, 1, 3]

Step 1: sortStack([2, 4, 1, 3])
→ Pop 2, sort [4, 1, 3]
→ Pop 4, sort [1, 3]
→ Pop 1, sort [3]
→ Pop 3, base case (single element)
Now insert back in sorted order:
   insert(1): [1, 3]
   insert(4): [1, 3, 4]
   insert(2): remove 4, 3 → insert 2 → push back 3, 4 → [1, 2, 3, 4]
✅ Final Sorted Stack: [1, 2, 3, 4]

─────────────────────────────────────────────
⏱️ TIME COMPLEXITY ANALYSIS:

Let n = number of elements in the stack.

- Each recursive call to `sortStack()` processes one element → O(n)
- Each call to `insertInSortedOrder()` may traverse O(n) in worst case
- ⇒ Total Time = O(n) * O(n) = O(n²)

💾 SPACE COMPLEXITY:
- Recursion depth → O(n)
- No extra data structures used (besides call stack)

─────────────────────────────────────────────
💡 OPTIMAL INSIGHT:

- This is an *in-place recursive solution*, no extra stack used.
- We use the stack’s call stack to simulate temporary storage.
- Sorting happens while backtracking in recursion.
- For interview: Avoid using another data structure unless specified.

─────────────────────────────────────────────
✅ SUMMARY:
- Sort stack using recursion (no loops required).
- O(n²) time, O(n) space.
- Base case stops when stack has ≤ 1 element.
─────────────────────────────────────────────
*/
