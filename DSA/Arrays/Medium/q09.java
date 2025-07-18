import java.util.*;

public class q09 {

    // Helper to mark a full row with -1 where needed (brute-force)
    static void markRow(ArrayList<ArrayList<Integer>> matrix, int n, int m, int i) {
        for (int j = 0; j < m; j++) {
            // If the element is not already zero, mark it as -1
            if (matrix.get(i).get(j) != 0) {
                matrix.get(i).set(j, -1);
            }
        }
    }

    // Helper to mark a full column with -1 where needed (brute-force)
    static void markCol(ArrayList<ArrayList<Integer>> matrix, int n, int m, int j) {
        for (int i = 0; i < n; i++) {
            // If the element is not already zero, mark it as -1
            if (matrix.get(i).get(j) != 0) {
                matrix.get(i).set(j, -1);
            }
        }
    }

    // ❌ Brute-force approach: Mark affected cells with -1 and convert to 0 later
    static ArrayList<ArrayList<Integer>> zeroMatrix(ArrayList<ArrayList<Integer>> matrix, int n, int m) {
        // First pass: Mark rows and columns
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // If element is zero, mark its row and column with -1
                if (matrix.get(i).get(j) == 0) {
                    markRow(matrix, n, m, i);
                    markCol(matrix, n, m, j);
                }
            }
        }

        // Second pass: Convert all -1s to actual zeroes
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix.get(i).get(j) == -1) {
                    matrix.get(i).set(j, 0);
                }
            }
        }

        return matrix;
    }

    // ✅ Better approach: Use auxiliary row[] and col[] arrays for marking
    static ArrayList<ArrayList<Integer>> zeroMatrixBetter(ArrayList<ArrayList<Integer>> matrix, int n, int m) {
        int[] row = new int[n]; // Row marker array
        int[] col = new int[m]; // Column marker array

        // First pass: Store rows and columns that need to be zeroed
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix.get(i).get(j) == 0) {
                    row[i] = 1; // Mark i-th row
                    col[j] = 1; // Mark j-th column
                }
            }
        }

        // Second pass: Set matrix[i][j] to 0 if its row or column was marked
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (row[i] == 1 || col[j] == 1) {
                    matrix.get(i).set(j, 0);
                }
            }
        }

        return matrix;
    }

    // ✅ Optimal approach: Use the matrix itself to reduce space usage to O(1)
    static ArrayList<ArrayList<Integer>> zeroMatrixOptimal(ArrayList<ArrayList<Integer>> matrix, int n, int m) {
        int col0 = 1; // Flag to track if the first column should be zeroed

        // Step 1: Use first row and column to mark zeros for the rest of matrix
        for (int i = 0; i < n; i++) {
            // Check if first column needs to be zeroed
            if (matrix.get(i).get(0) == 0) col0 = 0;

            for (int j = 1; j < m; j++) {
                if (matrix.get(i).get(j) == 0) {
                    matrix.get(i).set(0, 0);     // Mark corresponding row
                    matrix.get(0).set(j, 0);     // Mark corresponding column
                }
            }
        }

        // Step 2: Traverse the matrix in reverse order (bottom to top, right to left)
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 1; j--) {
                // If the row or column is marked, set element to 0
                if (matrix.get(i).get(0) == 0 || matrix.get(0).get(j) == 0) {
                    matrix.get(i).set(j, 0);
                }
            }
            // Finally update the first column based on col0 flag
            if (col0 == 0) {
                matrix.get(i).set(0, 0);
            }
        }

        return matrix;
    }

    // ✅ Test driver
    public static void main(String[] args) {
        // Sample 3x3 matrix with a zero in (1,1)
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        matrix.add(new ArrayList<>(Arrays.asList(1, 1, 1)));
        matrix.add(new ArrayList<>(Arrays.asList(1, 0, 1)));
        matrix.add(new ArrayList<>(Arrays.asList(1, 1, 1)));

        int n = matrix.size();        // Number of rows
        int m = matrix.get(0).size(); // Number of columns

        System.out.println("Original Matrix:");
        printMatrix(matrix);

        // Uncomment one approach to test
        // zeroMatrix(matrix, n, m);        // Brute-force
        // zeroMatrixBetter(matrix, n, m); // Better
        zeroMatrixOptimal(matrix, n, m);   // Optimal

        System.out.println("\nMatrix After Zeroing:");
        printMatrix(matrix);
    }

    // Utility function to print a matrix
    static void printMatrix(ArrayList<ArrayList<Integer>> matrix) {
        for (ArrayList<Integer> row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
