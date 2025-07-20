// PRINT MATRIX IN SPIRAL
import java.util.*; // Importing the Java utility package for using List and ArrayList

// Define the class
class q11 {

    // Method to return the matrix elements in spiral order
    public static List<Integer> printSpiral(int[][] mat) {
        // Define a List to store the final spiral order result
        List<Integer> ans = new ArrayList<>();

        // Get the number of rows
        int n = mat.length;

        // Get the number of columns (assuming matrix is non-empty)
        int m = mat[0].length;

        // Initialize four pointers to keep track of the current edges of the matrix
        int top = 0;         // Topmost row to be traversed
        int left = 0;        // Leftmost column to be traversed
        int bottom = n - 1;  // Bottommost row to be traversed
        int right = m - 1;   // Rightmost column to be traversed

        // Loop until the pointers overlap, meaning we have traversed the whole matrix
        while (top <= bottom && left <= right) {

            // 1. Traverse the top row from left to right
            for (int i = left; i <= right; i++)
                ans.add(mat[top][i]); // Add current element to result
            top++; // Move the top boundary down (next layer)

            // 2. Traverse the right column from top to bottom
            for (int i = top; i <= bottom; i++)
                ans.add(mat[i][right]); // Add current element to result
            right--; // Move the right boundary left

            // 3. Traverse the bottom row from right to left (only if top <= bottom to avoid double counting)
            if (top <= bottom) {
                for (int i = right; i >= left; i--)
                    ans.add(mat[bottom][i]); // Add current element to result
                bottom--; // Move the bottom boundary up
            }

            // 4. Traverse the left column from bottom to top (only if left <= right to avoid double counting)
            if (left <= right) {
                for (int i = bottom; i >= top; i--)
                    ans.add(mat[i][left]); // Add current element to result
                left++; // Move the left boundary right
            }
        }

        // Return the final spiral ordered list
        return ans;
    }
}
