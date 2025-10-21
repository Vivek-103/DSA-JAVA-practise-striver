// ✅ Problem: Implement Min Stack
// You need to design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
//
// Operations:
// - push(x): Add element x to the stack
// - pop(): Remove the top element
// - top(): Get the top element
// - getMin(): Retrieve the minimum element in constant time
//
// Goal: O(1) time for all operations
// Optimal Solution: Use two stacks (mainStack + minStack)

public class MinStack {

    // 🧱 mainStack → stores all pushed elements
    // 🧱 minStack → stores the minimum value at each level of mainStack
    // At any point, the top of minStack gives the current minimum value in the stack
    private java.util.Stack<Integer> mainStack;
    private java.util.Stack<Integer> minStack;

    // ✅ Constructor: initializes both stacks
    public MinStack() {
        mainStack = new java.util.Stack<>(); // stack to hold actual values
        minStack = new java.util.Stack<>();  // stack to hold minimum values
    }

    // ✅ push(x): Add an element x into the stack
    public void push(int val) {
        // Step 1: Push the value into mainStack (normal operation)
        mainStack.push(val);

        // Step 2: If minStack is empty OR val <= current minimum,
        // then push this val also into minStack
        // This ensures that minStack always has the current minimum at its top
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    // ✅ pop(): Remove the top element from the stack
    public void pop() {
        // Step 1: If top of mainStack is equal to top of minStack,
        // it means the current minimum element is being removed
        // So, pop from minStack too to maintain correct minimum tracking
        if (mainStack.peek().equals(minStack.peek())) {
            minStack.pop();
        }

        // Step 2: Pop the element from mainStack normally
        mainStack.pop();
    }

    // ✅ top(): Return the top element without removing it
    public int top() {
        // Return the top element of mainStack
        return mainStack.peek();
    }

    // ✅ getMin(): Retrieve the minimum element in O(1) time
    public int getMin() {
        // Top of minStack always holds the current minimum value
        return minStack.peek();
    }

    // =====================================================
    // 🧮 TIME AND SPACE COMPLEXITY ANALYSIS
    // -----------------------------------------------------
    // Operation    | Time Complexity | Space Complexity
    // -----------------------------------------------------
    // push(x)      | O(1)            | O(N)
    // pop()        | O(1)            | O(N)
    // top()        | O(1)            | O(N)
    // getMin()     | O(1)            | O(N)
    //
    // Reason: Each operation involves at most one push/pop/peek operation on
    // a stack which takes constant time. minStack grows proportionally with mainStack.

    // =====================================================
    // 🧠 LOGIC EXPLANATION
    // -----------------------------------------------------
    // The main idea is to maintain an auxiliary stack (minStack) that keeps
    // track of the minimum value at every level of the main stack.
    //
    // Whenever we push a new value:
    // - If it's smaller or equal to the current min, also push it into minStack.
    //
    // Whenever we pop:
    // - If the popped element equals the top of minStack, pop minStack too.
    //
    // This ensures:
    //   minStack.top() == current minimum of all elements in mainStack.

    // =====================================================
    // 🎯 OPTIMALITY
    // -----------------------------------------------------
    // ✅ All operations take constant time O(1)
    // ✅ Only two stacks are used → minimal extra space
    // ✅ No scanning or re-computation of minimum required
    // ✅ Works perfectly even with duplicate minimum values

    // =====================================================
    // 🧩 VISUALIZATION EXAMPLE
    // -----------------------------------------------------
    // Let’s simulate step-by-step:
    //
    // push(5)
    // mainStack: [5]
    // minStack:  [5]
    // Minimum = 5
    //
    // push(3)
    // mainStack: [5, 3]
    // minStack:  [5, 3]
    // Minimum = 3
    //
    // push(7)
    // mainStack: [5, 3, 7]
    // minStack:  [5, 3]
    // Minimum = 3
    //
    // push(2)
    // mainStack: [5, 3, 7, 2]
    // minStack:  [5, 3, 2]
    // Minimum = 2
    //
    // pop()
    // mainStack: [5, 3, 7]
    // minStack:  [5, 3]
    // Minimum = 3
    //
    // getMin() → returns 3
    // top() → returns 7
    //
    // Hence, every operation (push/pop/top/getMin) is in O(1) time.

    // =====================================================
    // 🔍 CONCLUSION
    // -----------------------------------------------------
    // ➤ minStack always mirrors the mainStack but only for the minimum values.
    // ➤ The top of minStack = current minimum of the entire stack.
    // ➤ This is the most optimal and clean approach to implement a Min Stack.
}
