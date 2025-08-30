// Search in a Sorted 2D Matrix
// Problem: Given a matrix where each row is sorted and the first element of each row
// is greater than the last element of the previous row,
// determine if a target exists in the matrix.

import java.util.ArrayList;
import java.util.Arrays;

public class q02 {

    /* ------------------------------------------------------
       BRUTE FORCE APPROACH
       Steps:
       1. Traverse each row and column element
       2. Compare with target
       3. Return true if found, false otherwise
       Time Complexity: O(n*m)   [n = rows, m = cols]
       Space Complexity: O(1)
    ------------------------------------------------------ */
    public static boolean searchMatrixBrute(ArrayList<ArrayList<Integer>> matrix, int target) {
        int n = matrix.size();        // Number of rows
        int m = matrix.get(0).size(); // Number of columns

        // Traverse each element in the matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {   // (fixed: should be m, not n)
                if (matrix.get(i).get(j) == target) // Compare with target
                    return true;
            }
        }
        return false; // Not found
    }

    /* ------------------------------------------------------
       OPTIMAL APPROACH (Binary Search)
       Idea:
       - Flatten the 2D matrix into a 1D sorted array (conceptually, not physically)
       - Apply binary search on range [0 .. n*m-1]
       - Convert mid index back to row & col:
         row = mid / m
         col = mid % m
       Time Complexity: O(log(n*m)) 
       Space Complexity: O(1)
    ------------------------------------------------------ */
    public static boolean searchMatrix(ArrayList<ArrayList<Integer>> matrix, int target) {
        int n = matrix.size();        // Number of rows
        int m = matrix.get(0).size(); // Number of columns

        int low = 0;           // Start index of flattened array
        int high = n * m - 1;  // End index of flattened array

        // Binary search
        while (low <= high) {
            int mid = (low + high) / 2;  // Middle index in flattened array

            // Map 1D mid index → 2D row & column
            int row = mid / m;
            int col = mid % m;

            // Compare element with target
            if (matrix.get(row).get(col) == target) 
                return true;
            else if (matrix.get(row).get(col) < target) 
                low = mid + 1;   // Search in right half
            else 
                high = mid - 1;  // Search in left half
        }
        return false; // Not found
    }

    /* ------------------------------------------------------
       MAIN METHOD for Testing
    ------------------------------------------------------ */
    public static void main(String[] args) {
        // Example matrix:
        //  1   3   5   7
        // 10  11  16  20
        // 23  30  34  60
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        matrix.add(new ArrayList<>(Arrays.asList(1, 3, 5, 7)));
        matrix.add(new ArrayList<>(Arrays.asList(10, 11, 16, 20)));
        matrix.add(new ArrayList<>(Arrays.asList(23, 30, 34, 60)));

        int target1 = 3;
        int target2 = 13;

        // Brute Force Search
        System.out.println("Brute Force (target=" + target1 + "): " + searchMatrixBrute(matrix, target1)); // true
        System.out.println("Brute Force (target=" + target2 + "): " + searchMatrixBrute(matrix, target2)); // false

        // Optimal Search (Binary Search)
        System.out.println("Optimal (target=" + target1 + "): " + searchMatrix(matrix, target1)); // true
        System.out.println("Optimal (target=" + target2 + "): " + searchMatrix(matrix, target2)); // false
    }
}
