// File Name: LargestRectangle.java
// Problem: Largest Rectangle in Histogram
// Objective: Given an array of bar heights representing a histogram,
// find the area of the largest rectangle that can be formed within it.

import java.util.*;  // Import Java utilities

public class LargestRectangle {

    // --------------------------- MAIN METHOD --------------------------- //
    public static void main(String[] args) {

        // Example input: heights of bars in histogram
        int[] heights = {2, 1, 5, 6, 2, 3};

        // Call the function to compute largest rectangle area
        int maxArea = largestRectangleArea(heights);

        // Print the result
        System.out.println("Heights: " + Arrays.toString(heights));
        System.out.println("Largest Rectangle Area: " + maxArea);

        // ---------------- VISUALIZATION EXPLANATION ----------------
        /*
         * Visualization of the Stack Process:
         * heights = [2, 1, 5, 6, 2, 3]
         *
         * Step-by-step using a monotonic increasing stack:
         *
         * i=0, h=2 -> stack empty -> push 0 -> [0]
         * i=1, h=1 -> current < heights[stack.top] -> pop 0
         *    area = 2 * 1 = 2
         *    push 1 -> [1]
         *
         * i=2, h=5 -> push 2 -> [1,2]
         * i=3, h=6 -> push 3 -> [1,2,3]
         *
         * i=4, h=2 -> current < heights[stack.top]
         *    pop 3: height=6, width=(4-2-1)=1 -> area=6*1=6
         *    pop 2: height=5, width=(4-1-1)=2 -> area=5*2=10
         *    push 4 -> [1,4]
         *
         * i=5, h=3 -> push 5 -> [1,4,5]
         *
         * End of array -> pop remaining:
         *    pop 5: height=3, width=(6-4-1)=1 -> area=3*1=3
         *    pop 4: height=2, width=(6-1-1)=4 -> area=2*4=8
         *    pop 1: height=1, width=(6-0)=6 -> area=1*6=6
         *
         * Max Area = 10
         *
         * Final Answer = 10
         */
    }

    // ---------------------- FUNCTION IMPLEMENTATION ---------------------- //
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;

        // Stack to store indices of bars
        Stack<Integer> stack = new Stack<>();

        // Variable to store maximum area
        int maxArea = 0;

        // Traverse all bars including one extra iteration for cleanup
        for (int i = 0; i <= n; i++) {

            // currentHeight = current bar height or 0 if we're past the end
            int currentHeight = (i == n) ? 0 : heights[i];

            // While stack not empty and current bar is lower than top of stack
            // means rectangle with stack.top() as height has ended
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {

                // Height of bar to calculate area for
                int height = heights[stack.pop()];

                // Width = current index - index of previous smaller element - 1
                int width = (stack.isEmpty()) ? i : (i - stack.peek() - 1);

                // Calculate area and update maxArea
                int area = height * width;
                maxArea = Math.max(maxArea, area);
            }

            // Push current index onto stack
            stack.push(i);
        }

        // Return the largest area found
        return maxArea;
    }

    // --------------------------- TIME COMPLEXITY --------------------------- //
    /*
     * Time Complexity:
     * ----------------
     * O(n) — Each bar is pushed and popped at most once.
     * (n = number of bars in histogram)
     *
     * Space Complexity:
     * -----------------
     * O(n) — for the stack used to store indices.
     */
}
