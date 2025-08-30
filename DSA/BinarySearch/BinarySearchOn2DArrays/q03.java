// Search in a Row and Column-wise Sorted Matrix
// Problem: Given a matrix where each row and each column is sorted in ascending order,
// determine if a target exists in the matrix.

import java.util.ArrayList;
import java.util.Arrays;

public class q03 {

    /* ------------------------------------------------------
       BRUTE FORCE APPROACH
       Steps:
       1. Traverse each element in the matrix
       2. Compare with target
       3. Return true if found, false otherwise
       Time Complexity: O(n*m) [n = rows, m = cols]
       Space Complexity: O(1)
    ------------------------------------------------------ */
    public static boolean searchElementBrute(ArrayList<ArrayList<Integer>> matrix, int target) {
        int n = matrix.size();        // Number of rows
        int m = matrix.get(0).size(); // Number of columns

        // Traverse all rows
        for (int i = 0; i < n; i++) {
            // Traverse all columns
            for (int j = 0; j < m; j++) {
                // Check if current element equals target
                if (matrix.get(i).get(j) == target)
                    return true; // Target found
            }
        }

        return false; // Target not found
    }

    /* ------------------------------------------------------
       OPTIMAL APPROACH (Staircase Search)
       Idea:
       - Start from top-right corner (row=0, col=m-1)
       - If current element < target → move down (row++)
       - If current element > target → move left (col--)
       - Stop if found or out of bounds
       Time Complexity: O(n + m)
       Space Complexity: O(1)
    ------------------------------------------------------ */
    public static boolean searchElementOptimal(ArrayList<ArrayList<Integer>> matrix, int target) {
        int n = matrix.size();        // Number of rows
        int m = matrix.get(0).size(); // Number of columns

        int row = 0;       // Start from first row
        int col = m - 1;   // Start from last column (top-right corner)

        // Traverse until we go out of bounds
        while (row < n && col >= 0) {
            int current = matrix.get(row).get(col);

            if (current == target)
                return true;       // Target found
            else if (current < target)
                row++;             // Move down to next row
            else
                col--;             // Move left to previous column
        }

        return false; // Target not found
    }

    /* ------------------------------------------------------
       MAIN METHOD for Testing
    ------------------------------------------------------ */
    public static void main(String[] args) {
        // Example matrix:
        //  1  4  7 11
        //  2  5  8 12
        //  3  6  9 16
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        matrix.add(new ArrayList<>(Arrays.asList(1, 4, 7, 11)));
        matrix.add(new ArrayList<>(Arrays.asList(2, 5, 8, 12)));
        matrix.add(new ArrayList<>(Arrays.asList(3, 6, 9, 16)));

        int target1 = 5;
        int target2 = 10;

        // Brute Force
        System.out.println("Brute Force (target=" + target1 + "): " + searchElementBrute(matrix, target1)); // true
        System.out.println("Brute Force (target=" + target2 + "): " + searchElementBrute(matrix, target2)); // false

        // Optimal Staircase Search
        System.out.println("Optimal (target=" + target1 + "): " + searchElementOptimal(matrix, target1)); // true
        System.out.println("Optimal (target=" + target2 + "): " + searchElementOptimal(matrix, target2)); // false
    }
}
