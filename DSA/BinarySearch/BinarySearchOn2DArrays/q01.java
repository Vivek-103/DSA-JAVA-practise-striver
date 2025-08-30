// Find the row with maximum number of 1's in a binary matrix
// Problem: Each row is sorted (0's first, then 1's). We need the row index with the maximum number of 1's.

import java.util.*;

public class q01 {

    /* ------------------------------------------------------
       BRUTE FORCE APPROACH
       Steps:
       1. Traverse each row
       2. Count number of 1's
       3. Track row with maximum count
       Time Complexity: O(n*m)  [n = rows, m = cols]
       Space Complexity: O(1)
    ------------------------------------------------------ */
    public static int rowWithMax1sBrute(ArrayList<ArrayList<Integer>> matrix, int n, int m) {
        int cnt_max = 0;   // Maximum count of 1's seen so far
        int index = -1;    // Index of row having max 1's

        // Traverse all rows
        for (int i = 0; i < n; i++) {
            int cnt_ones = 0; // Count of 1's in this row

            // Count 1's in row i
            for (int j = 0; j < m; j++) {
                cnt_ones += matrix.get(i).get(j); // Add cell value (0 or 1)
            }

            // If this row has more 1's, update answer
            if (cnt_ones > cnt_max) {
                cnt_max = cnt_ones;
                index = i;
            }
        }
        return index; // Return row index with max 1's
    }

    /* ------------------------------------------------------
       BINARY SEARCH HELPER
       Purpose: Find the first index where arr[mid] >= x
       Here used to find the first '1' in a sorted row
       Returns: index of first 1, or n if not found
       Time Complexity: O(log m)
    ------------------------------------------------------ */
    public static int lowerBound(ArrayList<Integer> arr, int n, int x) {
        int low = 0, high = n - 1;
        int ans = n; // Default = not found (index beyond end)

        while (low <= high) {
            int mid = (low + high) / 2;

            // If arr[mid] >= x, it can be a candidate
            if (arr.get(mid) >= x) {
                ans = mid;
                high = mid - 1; // Look left for earlier occurrence
            } else {
                low = mid + 1;  // Look right
            }
        }
        return ans; // First index where arr[mid] >= x
    }

    /* ------------------------------------------------------
       OPTIMAL APPROACH
       Steps:
       1. For each row, use binary search to find index of first '1'
       2. Count 1's = m - index_of_first_1
       3. Track row with maximum count
       Time Complexity: O(n log m)
       Space Complexity: O(1)
    ------------------------------------------------------ */
    public static int rowWithMax1sOptimal(ArrayList<ArrayList<Integer>> matrix, int n, int m) {
        int cnt_max = 0; // Maximum number of 1's
        int index = -1;  // Row index with maximum 1's

        // Traverse all rows
        for (int i = 0; i < n; i++) {
            // Find index of first 1 using binary search
            int firstOneIndex = lowerBound(matrix.get(i), m, 1);

            // Count 1's in this row
            int cnt_ones = m - firstOneIndex;

            // If more 1's found, update result
            if (cnt_ones > cnt_max) {
                cnt_max = cnt_ones;
                index = i;
            }
        }
        return index;
    }

    /* ------------------------------------------------------
       MAIN METHOD for Testing
    ------------------------------------------------------ */
    public static void main(String[] args) {
        // Create binary matrix
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        matrix.add(new ArrayList<>(Arrays.asList(0, 0, 1, 1)));
        matrix.add(new ArrayList<>(Arrays.asList(0, 1, 1, 1)));
        matrix.add(new ArrayList<>(Arrays.asList(0, 0, 0, 1)));
        matrix.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0)));

        int n = matrix.size();       // Number of rows
        int m = matrix.get(0).size(); // Number of columns

        // Test brute force
        System.out.println("Row with max 1's (Brute Force): " + rowWithMax1sBrute(matrix, n, m));

        // Test optimal
        System.out.println("Row with max 1's (Optimal): " + rowWithMax1sOptimal(matrix, n, m));
    }
}
