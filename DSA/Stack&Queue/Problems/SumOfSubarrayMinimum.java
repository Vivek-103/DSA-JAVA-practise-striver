// ======================================================================
// 🧩 Problem: Sum of Subarray Minimums
// ======================================================================
//
// Given an integer array `arr`, find the sum of the minimum value of every
// possible contiguous subarray. Since the answer can be large, return it modulo (1e9 + 7).
//
// ----------------------------------------------------------------------
// Example:
// Input: arr = [3, 1, 2, 4]
// Output: 17
//
// Explanation of all subarrays:
// [3] → min = 3
// [3,1] → min = 1
// [3,1,2] → min = 1
// [3,1,2,4] → min = 1
// [1] → min = 1
// [1,2] → min = 1
// [1,2,4] → min = 1
// [2] → min = 2
// [2,4] → min = 2
// [4] → min = 4
// Sum = 3 + 1 + 1 + 1 + 1 + 1 + 2 + 2 + 4 = 17
// ----------------------------------------------------------------------
//
// 🧠 Intuition:
//
// Instead of generating all subarrays (O(n²)), we can calculate for each element
// how many subarrays it is the *minimum* of, then multiply its contribution.
//
// For each element arr[i]:
//   - Find how many elements to the LEFT are greater (distance to previous smaller element).
//   - Find how many elements to the RIGHT are greater or equal (distance to next smaller element).
//
// Then its contribution = arr[i] * leftDistance * rightDistance
//
// Because each element acts as the minimum in several subarrays.
//
// We’ll use a **monotonic stack** to efficiently find these distances.
//
// ======================================================================
// ✅ Approach: Monotonic Stack (Optimal)
// ======================================================================
//
// We use two arrays:
// - left[i]  = number of subarrays ending at i where arr[i] is the minimum
// - right[i] = number of subarrays starting at i where arr[i] is the minimum
//
// Final sum = Σ (arr[i] * left[i] * right[i])
//
// ======================================================================
// ⚙️ Time Complexity: O(n)
// ⚙️ Space Complexity: O(n)
// ======================================================================

public class SumOfSubarrayMinimum {

    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        long mod = (long)1e9 + 7;

        // Arrays to store distances
        int[] left = new int[n];
        int[] right = new int[n];

        // Stack to find "Previous Smaller Element"
        java.util.Stack<Integer> stack = new java.util.Stack<>();

        // ------------------------------------------------------------
        // Step 1️⃣: Find distance to Previous Smaller Element (left)
        // ------------------------------------------------------------
        // left[i] = number of elements to the left (including self)
        // where arr[i] is the minimum.
        for (int i = 0; i < n; i++) {

            // Pop elements greater than current element
            // (because current element is smaller → new min starts here)
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }

            // If stack is empty, no smaller element on left → (i + 1)
            // Else, distance = i - previous smaller index
            left[i] = stack.isEmpty() ? (i + 1) : (i - stack.peek());

            // Push current index
            stack.push(i);
        }

        // Clear the stack for the next pass
        stack.clear();

        // ------------------------------------------------------------
        // Step 2️⃣: Find distance to Next Smaller Element (right)
        // ------------------------------------------------------------
        // right[i] = number of elements to the right (including self)
        // where arr[i] is the minimum.
        for (int i = n - 1; i >= 0; i--) {

            // NOTE:
            // Use >= here instead of > to handle duplicates properly
            // to avoid counting them multiple times.
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }

            // If stack empty → no smaller element to right → (n - i)
            // Else, distance = next smaller index - i
            right[i] = stack.isEmpty() ? (n - i) : (stack.peek() - i);

            // Push current index
            stack.push(i);
        }

        // ------------------------------------------------------------
        // Step 3️⃣: Calculate total sum using contributions
        // ------------------------------------------------------------
        long result = 0;
        for (int i = 0; i < n; i++) {
            // Contribution of arr[i]
            long contrib = (long) arr[i] * left[i] * right[i];
            result = (result + contrib) % mod;
        }

        // Return final result modulo 1e9+7
        return (int) result;
    }

    // ------------------------------------------------------------------
    // 🧠 Visualization Example Walkthrough
    // ------------------------------------------------------------------
    //
    // Input: [3, 1, 2, 4]
    //
    // Step 1: left[] (Previous Smaller)
    // i=0: stack empty → left[0]=1
    // i=1: 3>1 pop → stack empty → left[1]=2
    // i=2: 1<=2 → left[2]=2-1=1
    // i=3: 2<=4 → left[3]=3-2=1
    // left = [1,2,1,1]
    //
    // Step 2: right[] (Next Smaller)
    // i=3: stack empty → right[3]=1
    // i=2: 4>=2 pop → stack empty → right[2]=2
    // i=1: 2>=1 pop → stack empty → right[1]=3
    // i=0: 1<3 → right[0]=1
    // right = [1,3,2,1]
    //
    // Step 3: Contribution
    // arr[i] * left[i] * right[i]
    // 3 * 1 * 1 = 3
    // 1 * 2 * 3 = 6
    // 2 * 1 * 2 = 4
    // 4 * 1 * 1 = 4
    //
    // Total = 3 + 6 + 4 + 4 = 17 ✅
    //
    // ------------------------------------------------------------------
    // ✅ Output: 17
    // ------------------------------------------------------------------
}

//
// ======================================================================
// ⏱️ COMPLEXITY SUMMARY
// ======================================================================
// Time Complexity: O(n)
//   → Each element pushed and popped once from stack.
//
// Space Complexity: O(n)
//   → For left[], right[], and stack.
//
// ======================================================================
// ✅ KEY TAKEAWAYS
// ======================================================================
// - This problem is a direct application of the "Next/Previous Smaller Element" pattern.
// - Using Monotonic Stack ensures we efficiently calculate contribution counts.
// - Each element's total effect depends on how far it can extend as minimum in both directions.
// - Modulo operation is important for large results.
//
// ======================================================================
