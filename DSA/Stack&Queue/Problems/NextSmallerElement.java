// ✅ Problem: Next Smaller Element
// ---------------------------------------------------------
// Given an array 'arr' of size N, for each element, find the
// next smaller element (NSE) to its right.
//
// The Next Smaller Element for an element x is the first
// element to the right of x in the array that is strictly smaller
// than x. If no such element exists, the answer is -1.
//
// Example:
// Input : arr = [4, 8, 5, 2, 25]
// Output:       [2, 5, 2, -1, -1]
//
// Explanation:
// - For 4 → next smaller is 2
// - For 8 → next smaller is 5
// - For 5 → next smaller is 2
// - For 2 → none → -1
// - For 25 → none → -1
//
// ---------------------------------------------------------
// GOAL: Find the next smaller element for every element in O(N) time.
//
// 🧠 Optimal Approach: Use Stack (Monotonic Stack)
// ---------------------------------------------------------
// Idea:
// Traverse the array from RIGHT to LEFT.
// Maintain a stack that keeps elements in *increasing order* (from top to bottom).
// For each element, pop all elements from the stack that are >= current element.
// The top of the stack (after popping) will be the next smaller element.
// If the stack becomes empty, there is no smaller element → store -1.
//
// Then, push the current element into the stack.
//
// Each element is pushed and popped at most once → O(N) time.
//

import java.util.*;

public class NextSmallerElement {   // concise and meaningful class name

    // ✅ Function to find the Next Smaller Element for every array element
    public static int[] nextSmaller(int[] arr) {
        // Step 1️⃣: Create a result array to store next smaller elements
        int n = arr.length;
        int[] result = new int[n];

        // Step 2️⃣: Create a stack to store potential "next smaller" elements
        Stack<Integer> stack = new Stack<>();

        // Step 3️⃣: Traverse from RIGHT to LEFT
        for (int i = n - 1; i >= 0; i--) {

            // Step 4️⃣: Remove all elements from stack that are >= current element
            // Because they can't be the "next smaller" for current or any left elements
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }

            // Step 5️⃣: If stack is empty → no smaller element on right
            // else → top of stack is the next smaller element
            if (stack.isEmpty()) {
                result[i] = -1;
            } else {
                result[i] = stack.peek();
            }

            // Step 6️⃣: Push the current element into stack for future processing
            stack.push(arr[i]);
        }

        // Step 7️⃣: Return the result array
        return result;
    }

    // ✅ Main method to test the function
    public static void main(String[] args) {
        int[] arr = {4, 8, 5, 2, 25}; // sample input
        int[] ans = nextSmaller(arr);

        // Output the result in a readable format
        System.out.print("Next Smaller Elements: ");
        for (int val : ans) {
            System.out.print(val + " ");
        }
    }

    // =====================================================
    // 🧮 TIME AND SPACE COMPLEXITY ANALYSIS
    // -----------------------------------------------------
    // Time Complexity: O(N)
    // Each element is pushed and popped at most once.
    //
    // Space Complexity: O(N)
    // Stack stores at most N elements in the worst case.
    // Result array also of size N.
    //
    // =====================================================
    // 🧠 DETAILED LOGIC EXPLANATION
    // -----------------------------------------------------
    // ➤ When we move from right to left:
    //    - We only care about elements on the right side.
    //    - For each element, we pop out all elements that are >= it,
    //      since they can’t be the next smaller one.
    // ➤ If the stack becomes empty, there’s no smaller element to the right.
    // ➤ Otherwise, the top of the stack is the next smaller element.
    //
    // =====================================================
    // 🎯 VISUALIZATION (Dry Run Example)
    // -----------------------------------------------------
    // arr = [4, 8, 5, 2, 25]
    //
    // Step 1: i = 4 → element = 25
    // stack = []
    // → No smaller → result[4] = -1
    // → Push(25)
    // stack = [25]
    //
    // Step 2: i = 3 → element = 2
    // stack = [25]
    // → Pop(25) because 25 >= 2
    // stack = []
    // → No smaller → result[3] = -1
    // → Push(2)
    // stack = [2]
    //
    // Step 3: i = 2 → element = 5
    // stack = [2]
    // → Top (2) < 5 → result[2] = 2
    // → Push(5)
    // stack = [2, 5]
    //
    // Step 4: i = 1 → element = 8
    // stack = [2, 5]
    // → Pop(5) because 5 >= 8 ? No, 5 < 8, so stop.
    // → result[1] = 5
    // → Push(8)
    // stack = [2, 5, 8]
    //
    // Step 5: i = 0 → element = 4
    // stack = [2, 5, 8]
    // → Pop(8) (8 >= 4)
    // → Pop(5) (5 >= 4)
    // stack = [2]
    // → Top(2) < 4 → result[0] = 2
    // → Push(4)
    // stack = [2, 4]
    //
    // Final Output:
    // result = [2, 5, 2, -1, -1]
    //
    // =====================================================
    // 🧩 SUMMARY OF APPROACH
    // -----------------------------------------------------
    // ➤ Traverse from right to left
    // ➤ Maintain a "monotonic increasing stack"
    // ➤ Pop greater/equal elements
    // ➤ Stack top → next smaller element
    // ➤ Push current element for next iteration
    // ➤ Each element is processed only once → O(N)
    //
    // =====================================================
    // ✅ KEY TAKEAWAYS
    // -----------------------------------------------------
    // ✔ Efficient O(N) solution using stack
    // ✔ No nested loops or repeated scans
    // ✔ Works for arrays with duplicates
    // ✔ Template logic for other similar problems:
    //    - Next Greater Element
    //    - Previous Smaller Element
    //    - Stock Span Problem
    //    - Temperature Rise Problem
}
