// ✅ Problem: Next Greater Element II
// ---------------------------------------------------------
// Given a *circular array*, find the Next Greater Element (NGE)
// for every element.
//
// A circular array means after the last element, the array wraps
// around and continues from the beginning.
//
// For each element x, the Next Greater Element is the first
// element greater than x when traversing the array circularly.
//
// If no greater element exists, output -1.
//
// ---------------------------------------------------------
// Example:
// Input : arr = [1, 2, 1]
// Output:       [2, -1, 2]
//
// Explanation:
// - For 1 (index 0) → next greater is 2
// - For 2 (index 1) → no greater → -1
// - For 1 (index 2) → next greater is 2 (from circular wrap)
//
// ---------------------------------------------------------
// 🧠 Optimal Approach: Use Stack (Monotonic Stack + Circular Traversal)
// ---------------------------------------------------------
// Idea:
// Normally, in the Next Greater Element problem, we traverse the array
// from right to left and use a stack to maintain possible NGEs.
//
// Here, since the array is *circular*, we can simulate it by traversing
// the array *twice* (2N times), using modulo (%) to wrap indices around.
//
// For each index i (from 2N - 1 → 0):
//   1️⃣ Pop all elements ≤ arr[i % N] (not greater).
//   2️⃣ If i < N (first pass), record result[i] as stack top (or -1 if empty).
//   3️⃣ Push arr[i % N] onto stack.
//
// Each element is pushed/popped at most once, so still O(N).
//

import java.util.*;

public class NextGreaterElementII {   // concise, problem-specific class name

    // ✅ Function to find Next Greater Element in a circular array
    public static int[] nextGreaterElements(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];          // result array to store answers
        Stack<Integer> stack = new Stack<>(); // stack to store potential next greater elements

        // Step 1️⃣: Traverse from 2N-1 to 0 to simulate circular behavior
        for (int i = 2 * n - 1; i >= 0; i--) {

            // Step 2️⃣: Remove all elements smaller than or equal to current element
            while (!stack.isEmpty() && stack.peek() <= arr[i % n]) {
                stack.pop();
            }

            // Step 3️⃣: Only fill result for first N indices (real array positions)
            if (i < n) {
                if (stack.isEmpty()) {
                    result[i] = -1; // no greater element found
                } else {
                    result[i] = stack.peek(); // top of stack is next greater element
                }
            }

            // Step 4️⃣: Push the current element into stack
            stack.push(arr[i % n]);
        }

        // Step 5️⃣: Return the filled result array
        return result;
    }

    // ✅ Main method to test the function
    public static void main(String[] args) {
        int[] arr = {1, 2, 1}; // sample input
        int[] ans = nextGreaterElements(arr);

        // Output the result in a readable format
        System.out.print("Next Greater Elements (Circular): ");
        for (int val : ans) {
            System.out.print(val + " ");
        }
    }

    // =====================================================
    // 🧮 TIME AND SPACE COMPLEXITY ANALYSIS
    // -----------------------------------------------------
    // Time Complexity: O(N)
    // Each element is pushed and popped at most once from stack.
    //
    // Space Complexity: O(N)
    // Stack stores up to N elements.
    // Result array is of size N.
    //
    // =====================================================
    // 🧠 DETAILED LOGIC EXPLANATION
    // -----------------------------------------------------
    // ➤ A normal Next Greater Element uses a stack and goes right → left.
    // ➤ In circular version, after reaching index 0, the elements to its
    //    right include the array itself again.
    // ➤ Instead of actually duplicating the array, we iterate twice:
    //      for (i = 2N-1; i >= 0; i--)
    // ➤ Using (i % N) gives the actual array index.
    // ➤ The stack always stores potential "next greater candidates".
    // ➤ The top of stack after popping gives us the immediate greater element.
    //
    // =====================================================
    // 🎯 VISUALIZATION (Dry Run Example)
    // -----------------------------------------------------
    // arr = [1, 2, 1]
    // n = 3
    //
    // We'll simulate two rounds (total 6 iterations):
    //
    // i = 5 → arr[i%3] = 1
    // stack = []
    // → no greater → result[2] = -1
    // → push(1) → stack = [1]
    //
    // i = 4 → arr[i%3] = 2
    // stack = [1]
    // → pop(1) since 1 ≤ 2
    // stack = []
    // → result[1] = -1
    // → push(2) → stack = [2]
    //
    // i = 3 → arr[i%3] = 1
    // stack = [2]
    // → top(2) > 1 → result[0] = 2
    // → push(1) → stack = [2, 1]
    //
    // (Extra circular checks)
    // i = 2 → arr[i%3] = 1 → stack=[2,1]
    // → pop(1), top=2, push(1)
    // i = 1 → arr[i%3]=2 → pop(1), top=2
    // i = 0 → arr[i%3]=1 → top(2)>1 → result[0]=2 again (no change)
    //
    // Final result:
    // result = [2, -1, 2]
    //
    // =====================================================
    // 🧩 SUMMARY OF APPROACH
    // -----------------------------------------------------
    // ➤ Use stack to track "next greater" candidates.
    // ➤ Traverse array twice using modulo for circular behavior.
    // ➤ Pop smaller/equal elements from stack.
    // ➤ Stack top = next greater element.
    // ➤ Push current element each iteration.
    // ➤ Each element processed in O(1) amortized time.
    //
    // =====================================================
    // ✅ KEY TAKEAWAYS
    // -----------------------------------------------------
    // ✔ Efficient O(N) time using monotonic stack.
    // ✔ No array duplication required (use modulo).
    // ✔ Handles circular nature perfectly.
    // ✔ Works for duplicates as well.
    // ✔ Template usable for:
    //    - Next Smaller Element II
    //    - Circular stock span or temperature problems.
}
