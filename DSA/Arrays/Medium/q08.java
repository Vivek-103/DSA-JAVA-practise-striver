// LONGEST CONSECUTIVE SEQUENCE IN ARRAY

// Importing necessary libraries
import java.util.Arrays;  // For sorting in the better approach
import java.util.HashSet; // For optimal approach
import java.util.Set;     // Interface for HashSet

// Class definition
class q08 {

    // Linear search function to find if 'num' exists in array 'a'
    public static boolean linearSearch(int[] a, int num) {
        int n = a.length; // Get the size of the array
        for (int i = 0; i < n; i++) { // Traverse each element
            if (a[i] == num) // If element matches the number
                return true; // Return true
        }
        return false; // If not found, return false
    }

    // Brute force approach to find the longest consecutive sequence
    public static int longestSuccessiveElementsbruteforce(int[] a) {
        int n = a.length; // Size of the array
        int longest = 1;  // Stores the maximum length found

        // Pick each element one by one
        for (int i = 0; i < n; i++) {
            int x = a[i]; // Current element
            int cnt = 1;  // Count the length of current sequence

            // Keep searching for the next consecutive element
            while (linearSearch(a, x + 1) == true) {
                x = x + 1;  // Move to next consecutive element
                cnt = cnt + 1; // Increase sequence length
            }

            // Update the longest if current sequence is greater
            longest = Math.max(longest, cnt);
        }
        return longest; // Return the maximum length
    }

    // Better approach using sorting
    public static int longestSuccessiveElementsBetter(int[] a) {
        int n = a.length;
        if (n == 0) return 0; // Edge case: empty array

        Arrays.sort(a); // Sort the array to bring consecutive elements together

        int lastSmaller = Integer.MIN_VALUE; // Initialize with min value
        int cnt = 0;      // Counter for current sequence
        int longest = 1;  // Variable to store max sequence length

        for (int i = 0; i < n; i++) {
            if (a[i] - 1 == lastSmaller) { // If current element is consecutive to previous
                cnt += 1; // Increase count
                lastSmaller = a[i]; // Update last smaller
            } else if (a[i] != lastSmaller) { // If current is not duplicate
                cnt = 1; // Reset count
                lastSmaller = a[i]; // Update last smaller
            }
            longest = Math.max(longest, cnt); // Update max if needed
        }

        return longest; // Return result
    }

    // Optimal approach using HashSet
    public static int longestSuccessiveElementsOptimal(int[] a) {
        int n = a.length;
        if (n == 0) return 0; // Handle empty array

        int longest = 1; // Variable to store the longest sequence
        Set<Integer> set = new HashSet<>(); // HashSet to store elements

        // Add all elements to the set
        for (int i = 0; i < n; i++) {
            set.add(a[i]);
        }

        // Iterate through the set
        for (int it : set) {
            // Only start counting if it's the beginning of a sequence
            if (!set.contains(it - 1)) {
                int cnt = 1; // Counter for sequence length
                int x = it;  // Current element

                // Check next consecutive elements
                while (set.contains(x + 1)) {
                    x = x + 1; // Move to next
                    cnt = cnt + 1; // Increase counter
                }

                // Update the maximum
                longest = Math.max(longest, cnt);
            }
        }

        return longest; // Return the longest sequence length
    }

    // Main method to test the logic
    public static void main(String[] args) {
        int[] a = {100, 200, 300, 4, 5, 6, 1, 2, 3}; // Test array
        int ans = longestSuccessiveElementsOptimal(a); // Call optimal method
        System.out.println("Longest consecutive sequence is " + ans); // Output result
    }
}
