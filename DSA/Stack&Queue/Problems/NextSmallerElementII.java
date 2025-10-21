// ✅ Problem: Next Smaller Element II (Circular Array)
// Given a circular array, for each element, find the next smaller element.
// If no smaller element exists, output -1.
//
// Example:
// Input: [1, 2, 1]
// Output: [-1, 1, -1]
//
// Explanation:
// - For element 1 (index 0): next smaller circularly is none → -1
// - For element 2 (index 1): next smaller circularly is 1 → 1
// - For element 1 (index 2): next smaller circularly is none → -1

// ✅ Class name short and concise as per instructions
public class NextSmallerElementII {

    // ✅ Function to find next smaller elements for a circular array
    public int[] nextSmallerElements(int[] nums) {

        // Step 1️⃣: Get length of array
        int n = nums.length;

        // Step 2️⃣: Create result array filled with -1 (default value)
        // Since for some elements, there might not be any smaller element
        int[] result = new int[n];
        java.util.Arrays.fill(result, -1);

        // Step 3️⃣: Create a stack to store indices of elements
        // We store indices to easily access elements circularly
        java.util.Stack<Integer> stack = new java.util.Stack<>();

        // Step 4️⃣: Traverse array twice (because array is circular)
        // We use (2 * n) to simulate a circular array traversal
        for (int i = 0; i < 2 * n; i++) {

            // ✅ Compute circular index using modulo operation
            int circularIndex = i % n;

            // Step 5️⃣: Pop elements from stack while current element is smaller
            // than the element represented by top index of stack
            // (this means current element is the next smaller element)
            while (!stack.isEmpty() && nums[circularIndex] < nums[stack.peek()]) {

                // Set result for that index as current element (smaller element found)
                result[stack.pop()] = nums[circularIndex];
            }

            // Step 6️⃣: Only push indices of first pass (i < n)
            // In the second loop, we only check but do not push
            // to avoid infinite loop growth of stack
            if (i < n) {
                stack.push(circularIndex);
            }
        }

        // Step 7️⃣: Return the result array containing next smaller elements
        return result;
    }

    // ✅ Main method for testing the solution
    public static void main(String[] args) {

        // Create object of class
        NextSmallerElementII obj = new NextSmallerElementII();

        // Example input
        int[] nums = {1, 2, 1};

        // Get result
        int[] ans = obj.nextSmallerElements(nums);

        // Print output
        System.out.println(java.util.Arrays.toString(ans)); // Output: [-1, 1, -1]
    }
}
