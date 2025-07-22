package BinarySearchOn1Darrays;
// FLOOR AND CEIL IMPLEMENTATION
public class q04 {

    // Function to find the floor of x in sorted array
    static int findFloor(int[] arr, int n, int x) {
        int low = 0;            // Start index of search space
        int high = n - 1;       // End index of search space
        int ans = -1;           // Default floor value if not found

        // Binary search loop
        while (low <= high) {
            int mid = low + (high - low) / 2;  // Safe mid calculation

            // If current mid element is less than or equal to x, it could be a floor
            if (arr[mid] <= x) {
                ans = arr[mid];    // Store current value as potential floor
                low = mid + 1;     // Move right to find a bigger floor candidate
            } else {
                high = mid - 1;    // Move left since arr[mid] > x
            }
        }

        return ans;  // Return the greatest element ≤ x
    }

    // Function to find the ceil of x in sorted array
    static int findCeil(int[] arr, int n, int x) {
        int low = 0;             // Start index of search space
        int high = n - 1;        // End index of search space
        int ans = -1;            // Default ceil value if not found

        // Binary search loop
        while (low <= high) {
            int mid = low + (high - low) / 2;  // Safe mid calculation

            // If current mid element is greater than or equal to x, it could be a ceil
            if (arr[mid] >= x) {
                ans = arr[mid];   // Store current value as potential ceil
                high = mid - 1;   // Move left to find a smaller ceil candidate
            } else {
                low = mid + 1;    // Move right since arr[mid] < x
            }
        }

        return ans;  // Return the smallest element ≥ x
    }

    // Main method to test floor and ceil functions
    public static void main(String[] args) {
        // Sorted input array
        int[] arr = {1, 2, 4, 6, 10, 12, 15};
        int n = arr.length;

        // Target value to find floor and ceil for
        int x = 5;

        // Call floor and ceil functions
        int floor = findFloor(arr, n, x);
        int ceil = findCeil(arr, n, x);

        // Print results
        System.out.println("Floor of " + x + " is: " + floor);
        System.out.println("Ceil of " + x + " is: " + ceil);
    }
}
