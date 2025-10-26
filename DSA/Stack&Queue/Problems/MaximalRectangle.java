// File Name: MaximalRectangle.java
// Problem: Maximal Rectangle
// Objective: Given a binary matrix filled with 0’s and 1’s,
// find the area of the largest rectangle containing only 1’s.

import java.util.*;

public class MaximalRectangle {

    // --------------------------- MAIN METHOD --------------------------- //
    public static void main(String[] args) {
        // Example test case
        char[][] matrix = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };

        // Compute maximal rectangle area
        int area = maximalRectangle(matrix);

        System.out.println("Matrix:");
        for (char[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("\nLargest Rectangle Area of 1s: " + area);

        // ---------------- VISUALIZATION ----------------
        /*
         * Step-by-step visualization:
         *
         * Matrix:
         * 1 0 1 0 0
         * 1 0 1 1 1
         * 1 1 1 1 1
         * 1 0 0 1 0
         *
         * We treat each row as the "base" of a histogram:
         *
         * Row 0: [1, 0, 1, 0, 0] -> max area = 1
         * Row 1: [2, 0, 2, 1, 1] -> max area = 3
         * Row 2: [3, 1, 3, 2, 2] -> max area = 6
         * Row 3: [4, 0, 0, 3, 0] -> max area = 4
         *
         * Final Answer = 6
         */
    }

    // ---------------------- FUNCTION IMPLEMENTATION ---------------------- //
    public static int maximalRectangle(char[][] matrix) {
        // Edge case: if matrix is empty
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int n = matrix[0].length;  // number of columns
        int[] heights = new int[n]; // histogram heights for each column
        int maxArea = 0;            // store largest area found

        // Iterate through each row
        for (char[] row : matrix) {
            // Update the histogram heights
            for (int i = 0; i < n; i++) {
                // If current cell = '1', add to height; if '0', reset to 0
                heights[i] = (row[i] == '1') ? heights[i] + 1 : 0;
            }

            // Calculate largest rectangle area for this histogram row
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    // ---------------- HISTOGRAM HELPER FUNCTION ---------------- //
    // Reuses logic from "Largest Rectangle in Histogram"
    private static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        // Traverse each bar including one extra pass for cleanup
        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];

            // If current height < top of stack height → calculate area
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = (stack.isEmpty()) ? i : (i - stack.peek() - 1);
                int area = height * width;
                maxArea = Math.max(maxArea, area);
            }

            stack.push(i);
        }

        return maxArea;
    }

    // --------------------------- TIME COMPLEXITY --------------------------- //
    /*
     * Time Complexity:
     * ----------------
     * O(m * n)
     * where m = number of rows, n = number of columns.
     * For each row, we compute largest rectangle in O(n) using a stack.
     *
     * Space Complexity:
     * -----------------
     * O(n)
     * for the heights array and stack.
     */
}
