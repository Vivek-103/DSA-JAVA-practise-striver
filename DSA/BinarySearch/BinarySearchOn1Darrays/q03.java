package BinarySearchOn1Darrays;

public class q03 {

    // Function to find the index to insert x in sorted array arr
    public static int SearchInsert(int[] arr, int x) {
        int n = arr.length;   // Length of the array

        int low = 0;          // Start index of search range
        int high = n - 1;     // End index of search range
        int ans = n;          // Default insert position is end of array (if x is largest)

        // Binary search loop
        while (low <= high) {
            // Calculate middle index safely
            int mid = low + (high - low) / 2;

            // If current element is greater than or equal to x, it may be a valid insert position
            if (arr[mid] >= x) {
                ans = mid;       // Store current index as potential insert position
                high = mid - 1;  // Search in the left half for a better (smaller) index
            } else {
                low = mid + 1;   // Move right, as current mid is less than x
            }
        }

        // Return the final insert position
        return ans;
    }

    // Main method to test the SearchInsert function
    public static void main(String[] args) {
        // Sorted array to perform insertion search
        int[] arr = {1, 3, 5, 6};

        // Target value to search or insert
        int x = 5;

        // Call the search insert function
        int insertIndex = SearchInsert(arr, x);

        // Print the result
        System.out.println("Insert Position of " + x + " = " + insertIndex);
    }
}
