public // Find a Peak Element in a 2D Array
// Problem: A peak element in a 2D array is an element which is greater than or equal to its
// four neighbors (up, down, left, right). The task is to find any one peak element.

import java.util.*;

public class q04 {

    /* ------------------------------------------------------
       BRUTE FORCE APPROACH
       Steps:
       1. Traverse all elements in the 2D array
       2. Check if the current element is >= all its neighbors
       3. Return the first peak found
       Time Complexity: O(n*m) [n = rows, m = columns]
       Space Complexity: O(1)
    ------------------------------------------------------ */
    public static int[] findPeakBrute(int[][] matrix) {
        int n = matrix.length;        // Number of rows
        int m = matrix[0].length;     // Number of columns

        // Traverse each row
        for (int i = 0; i < n; i++) {
            // Traverse each column
            for (int j = 0; j < m; j++) {
                int current = matrix[i][j];

                // Get neighbors; use Integer.MIN_VALUE for out-of-bound neighbors
                int up = (i > 0) ? matrix[i - 1][j] : Integer.MIN_VALUE;
                int down = (i < n - 1) ? matrix[i + 1][j] : Integer.MIN_VALUE;
                int left = (j > 0) ? matrix[i][j - 1] : Integer.MIN_VALUE;
                int right = (j < m - 1) ? matrix[i][j + 1] : Integer.MIN_VALUE;

                // Check if current element is greater than or equal to all neighbors
                if (current >= up && current >= down && current >= left && current >= right) {
                    return new int[]{i, j}; // Return row & column of peak
                }
            }
        }
        return new int[]{-1, -1}; // No peak found (should not happen)
    }

    /* ------------------------------------------------------
       OPTIMAL APPROACH (Binary Search on Middle Column)
       Idea:
       1. Pick middle column
       2. Find global maximum in this column
       3. Compare it with neighbors in left and right columns
       4. Move to the half which has a larger neighbor
       5. Repeat until a peak is found
       Time Complexity: O(n * log m)
       Space Complexity: O(1)
    ------------------------------------------------------ */
    public static int[] findPeakOptimal(int[][] matrix) {
        int n = matrix.length;        // Number of rows
        int m = matrix[0].length;     // Number of columns

        int low = 0;
        int high = m - 1;

        while (low <= high) {
            int midCol = (low + high) / 2; // Middle column

            // Find index of maximum element in midCol
            int maxRow = 0;
            int maxVal = matrix[0][midCol];
            for (int i = 1; i < n; i++) {
                if (matrix[i][midCol] > maxVal) {
                    maxVal = matrix[i][midCol];
                    maxRow = i;
                }
            }

            int leftNeighbor = (midCol > 0) ? matrix[maxRow][midCol - 1] : Integer.MIN_VALUE;
            int rightNeighbor = (midCol < m - 1) ? matrix[maxRow][midCol + 1] : Integer.MIN_VALUE;

            // Check if current element is a peak
            if (maxVal >= leftNeighbor && maxVal >= rightNeighbor) {
                return new int[]{maxRow, midCol}; // Peak found
            } else if (leftNeighbor > maxVal) {
                high = midCol - 1; // Move to left half
            } else {
                low = midCol + 1;  // Move to right half
            }
        }

        return new int[]{-1, -1}; // Should never reach here
    }

    /* ------------------------------------------------------
       MAIN METHOD for Testing
    ------------------------------------------------------ */
    public static void main(String[] args) {
        // Example matrix
        int[][] matrix = {
            {10, 8, 10, 10},
            {14, 13, 12, 11},
            {15, 9, 11, 21},
            {16, 17, 19, 20}
        };

        // Brute Force
        int[] peakBrute = findPeakBrute(matrix);
        System.out.println("Peak (Brute Force) at: (" + peakBrute[0] + ", " + peakBrute[1] + 
                           ") with value " + matrix[peakBrute[0]][peakBrute[1]]);

        // Optimal
        int[] peakOptimal = findPeakOptimal(matrix);
        System.out.println("Peak (Optimal) at: (" + peakOptimal[0] + ", " + peakOptimal[1] + 
                           ") with value " + matrix[peakOptimal[0]][peakOptimal[1]]);
    }
}
 {
    
}
