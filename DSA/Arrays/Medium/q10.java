import java.util.*;

class q10 {

    // Brute-force approach to rotate a matrix by 90 degrees clockwise
    static int[][] rotateBruteApproach(int[][] matrix) {
        int n = matrix.length; // Get the size of the matrix (assuming square)
        int rotated[][] = new int[n][n]; // Create a new matrix to store rotated version

        // Traverse the original matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Place the element at the correct rotated position
                rotated[j][n - i - 1] = matrix[i][j];
            }
        }
        return rotated; // Return the rotated matrix
    }

    // Optimal in-place approach to rotate matrix 90 degrees clockwise
    static void rotateOptimal(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Transpose the matrix (swap rows with columns)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Swap matrix[i][j] with matrix[j][i]
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                // Swap elements symmetrically from left and right
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }

    // Utility function to print a matrix
    static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    // Main method to test the rotation functions
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        // Print original matrix
        System.out.println("Original Matrix:");
        printMatrix(matrix);

        // Brute-force rotated result
        int[][] bruteRotated = rotateBruteApproach(matrix);
        System.out.println("\nRotated Matrix (Brute Force):");
        printMatrix(bruteRotated);

        // Clone the matrix to apply optimal in-place rotation
        int[][] matrixForOptimal = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        rotateOptimal(matrixForOptimal);
        System.out.println("\nRotated Matrix (Optimal In-Place):");
        printMatrix(matrixForOptimal);
    }
}
