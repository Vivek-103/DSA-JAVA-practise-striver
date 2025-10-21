// ✅ Problem: Next Greater Element
// ---------------------------------------------------------
// Given an array 'arr' of size N, for each element, find the
// next greater element (NGE) to its right.
//
// The Next Greater Element for an element x is the first
// element to the right of x in the array that is strictly greater
// than x. If no such element exists, the answer is -1.
//
// Example:
// Input : arr = [4, 5, 2, 25]
// Output:       [5, 25, 25, -1]
//
// Explanation:
// - For 4 → next greater is 5
// - For 5 → next greater is 25
// - For 2 → next greater is 25
// - For 25 → no greater element → -1
//
// ---------------------------------------------------------
// GOAL: Find the next greater element for every element in O(N) time.
//
// 🧠 Optimal Approach: Use Stack (Monotonic Stack)
// ---------------------------------------------------------
// Idea:
// Traverse the array from right to left, and maintain a stack that
// keeps track of elements in decreasing order.
// For each element, pop all smaller or equal elements from the stack.
// The top of the stack (after popping) will be the next greater element.
//
// If the stack becomes empty, it means there is no greater element → store -1.
//
// Then, push the current element into the stack.
//
// This ensures each element is pushed and popped at most once → O(N) time.
//

import java.util.*;

public class NextGreaterElement {   // short, concise class name

    public static int[] nextGreater(int[] arr) {
        // Step 1️⃣: Create a result array to store next greater elements
        int n = arr.length;
        int[] result = new int[n];

        // Step 2️⃣: Create a stack to store potential next greater elements
        Stack<Integer> stack = new Stack<>();

        // Step 3️⃣: Traverse the array from RIGHT to LEFT
        for (int i = n - 1; i >= 0; i--) {

            // Step 4️⃣: Pop all elements smaller than or equal to arr[i]
            // because they can never be the next greater element for any
            // element to the left of arr[i].
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }

            // Step 5️⃣: If stack is empty → no greater element to the right
            // else → top of stack is the next greater element
            if (stack.isEmpty()) {
                result[i] = -1;
            } else {
                result[i] = stack.peek();
            }

            // Step 6️⃣: Push current element into the stack for future comparisons
            stack.push(arr[i]);
        }

        // Step 7️⃣: Return the result array containing all next greater elements
        return result;
    }

    // ✅ Main method to test the solution
    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 25}; // sample input
        int[] ans = nextGreater(arr);

        // Printing output in readable format
        System.out.print("Next Greater Elements: ");
        for (int val : ans) {
            System.out.print(val + " ");
        }
    }

    // =====================================================
    // 🧮 TIME AND SPACE COMPLEXITY ANALYSIS
    // -----------------------------------------------------
    // Time Complexity: O(N)
    // - Each element is pushed and popped at most once.
    // - Total operations are linear in array size.
    //
    // Space Complexity: O(N)
    // - For the stack (in the worst case, all elements are in decreasing order).
    // - For the result array (size N).
    //
    // =====================================================
    // 🎯 VISUALIZATION (Dry Run Example)
    // -----------------------------------------------------
    // arr = [4, 5, 2, 25]
    // We'll go from right to left:
    //
    // Step 1: i = 3, element = 25
    // stack = []
    // → No greater element → result[3] = -1
    // → Push 25 → stack = [25]
    //
    // Step 2: i = 2, element = 2
    // stack = [25]
    // → Top of stack (25) > 2 → result[2] = 25
    // → Push 2 → stack = [25, 2]
    //
    // Step 3: i = 1, element = 5
    // stack = [25, 2]
    // → Pop 2 (since 2 <= 5)
    // → Top of stack = 25 → result[1] = 25
    // → Push 5 → stack = [25, 5]
    //
    // Step 4: i = 0, element = 4
    // stack = [25, 5]
    // → Top (5) > 4 → result[0] = 5
    // → Push 4 → stack = [25, 5, 4]
    //
    // Final Output:
    // result = [5, 25, 25, -1]
    //
    // =====================================================
    // 🧩 SUMMARY OF THE APPROACH
    // -----------------------------------------------------
    // ➤ Use a stack to keep track of "next greater candidates".
    // ➤ Traverse from right → left.
    // ➤ Pop smaller/equal elements.
    // ➤ The stack top always holds the next greater element.
    // ➤ Push the current element after processing.
    // ➤ Each element is handled in O(1) amortized time.
    //
    // =====================================================
    // ✅ KEY TAKEAWAYS
    // -----------------------------------------------------
    // ✔ Optimal O(N) time using a monotonic stack.
    // ✔ Clean and simple logic.
    // ✔ Works for all array sizes and duplicates.
    // ✔ Easy to adapt for:
    //    - Next Smaller Element
    //    - Previous Greater/Smaller Element
    //    - Stock Span Problem (similar logic)
}
