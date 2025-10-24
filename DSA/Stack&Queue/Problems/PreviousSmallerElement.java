// ======================================================================
// 🧩 Problem: Previous Smaller Element
// ======================================================================
//
// Given an array of integers `arr`, for each element, find the **previous smaller element**
// i.e., the nearest element to the left that is smaller than the current one.
// If no such element exists, return -1 for that position.
//
// ----------------------------------------------------------------------
// Example:
// Input:  arr = [4, 5, 2, 10, 8]
// Output: [-1, 4, -1, 2, 2]
//
// Explanation:
// For 4 → no smaller element on left → -1
// For 5 → previous smaller = 4
// For 2 → no smaller element → -1
// For 10 → previous smaller = 2
// For 8 → previous smaller = 2
// ----------------------------------------------------------------------
//
// 🧠 Intuition:
// - We need to find for each element, the **nearest smaller element to its left**.
// - A naive approach checks all elements on the left → O(n²).
// - But we can optimize using a **monotonic stack** that keeps elements in increasing order.
// - When we move from left to right:
//     ➤ Pop elements from stack that are **greater or equal** than the current element.
//     ➤ After popping, if stack is empty → no smaller element → -1
//       else → top of the stack = previous smaller element.
// - Push the current element to stack for future comparisons.
//
// ----------------------------------------------------------------------
// ✅ Approach: Monotonic Stack (Optimal)
// ----------------------------------------------------------------------
// ⚙️ Time Complexity: O(n)
// ⚙️ Space Complexity: O(n)
// ----------------------------------------------------------------------

public class PreviousSmallerElement {

    public int[] previousSmaller(int[] arr) {
        int n = arr.length;

        // Result array to store answers
        int[] result = new int[n];

        // Stack to store potential "previous smaller" elements
        java.util.Stack<Integer> stack = new java.util.Stack<>();

        // Traverse the array from left to right
        for (int i = 0; i < n; i++) {

            // ------------------------------------------------------------
            // 🔁 Step 1: Remove all elements from stack that are greater
            // or equal to current element because they cannot be the
            // "previous smaller" for future elements.
            // ------------------------------------------------------------
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }

            // ------------------------------------------------------------
            // 🔍 Step 2: Check top of stack after popping
            // ------------------------------------------------------------
            // If stack empty → no smaller element → -1
            // Else → top of stack = previous smaller
            result[i] = stack.isEmpty() ? -1 : stack.peek();

            // ------------------------------------------------------------
            // 📦 Step 3: Push current element into stack for future use
            // ------------------------------------------------------------
            stack.push(arr[i]);
        }

        // Return the computed result array
        return result;
    }

    // ------------------------------------------------------------------
    // 🧠 Visualization Example Walkthrough
    // ------------------------------------------------------------------
    //
    // Input: arr = [4, 5, 2, 10, 8]
    //
    // Step-by-step stack trace:
    //
    // i=0 → arr[0]=4
    //   Stack = []
    //   No smaller → result[0] = -1
    //   Push(4) → Stack = [4]
    //
    // i=1 → arr[1]=5
    //   Stack = [4]
    //   4 < 5 → result[1] = 4
    //   Push(5) → Stack = [4,5]
    //
    // i=2 → arr[2]=2
    //   Stack = [4,5]
    //   Pop 5 (>=2), Pop 4 (>=2)
    //   Stack empty → result[2] = -1
    //   Push(2) → Stack = [2]
    //
    // i=3 → arr[3]=10
    //   Stack = [2]
    //   2 < 10 → result[3] = 2
    //   Push(10) → Stack = [2,10]
    //
    // i=4 → arr[4]=8
    //   Stack = [2,10]
    //   Pop 10 (>=8)
    //   2 < 8 → result[4] = 2
    //   Push(8) → Stack = [2,8]
    //
    // ✅ Final Output: [-1, 4, -1, 2, 2]
    //
    // ------------------------------------------------------------------
}

//
// ======================================================================
// ⏱️ COMPLEXITY ANALYSIS
// ======================================================================
// Time Complexity: O(n)
//   - Each element is pushed and popped at most once from the stack.
//
// Space Complexity: O(n)
//   - Stack stores at most n elements in the worst case.
//
// ======================================================================
// ✅ KEY TAKEAWAYS
// ======================================================================
// - Monotonic stacks are perfect for "nearest smaller/larger" type problems.
// - "Previous" → Traverse left ➡️ right
// - "Next" → Traverse right ➡️ left
// - For duplicates: Use >= (for smaller) or <= (for greater) carefully.
//
// ======================================================================
