// FIND FIRST AND LAST OCCURENCE IN AN ARRAY(SORTED)

class q05{
    public int[] searchRange(int[] nums, int target) {
        // Call the helper method to find the first occurrence of the target
        int first = findFirst(nums, target);
        // Call the helper method to find the last occurrence of the target
        int last = findLast(nums, target);
        // Return the indices of the first and last occurrences in an array
        return new int[]{first, last};
    }

    // Helper method to find the first occurrence of the target
    private int findFirst(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        int res = -1; // Initialize the result to -1 (indicating not found)

        // Perform binary search to find the first occurrence
        while (start <= end) {
            int mid = start + (end - start) / 2; // Calculate the middle index
            if (nums[mid] == target) {
                res = mid; // Found the target, update the result
                end = mid - 1; // Keep searching to the left for the first occurrence
            } else if (target < nums[mid]) {
                end = mid - 1; // Search in the left half if the target is smaller
            } else {
                start = mid + 1; // Search in the right half if the target is larger
            }
        }
        return res; // Return the index of the first occurrence, or -1 if not found
    }

    // Helper method to find the last occurrence of the target
    private int findLast(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        int res = -1; // Initialize the result to -1 (indicating not found)

        // Perform binary search to find the last occurrence
        while (start <= end) {
            int mid = start + (end - start) / 2; // Calculate the middle index
            if (nums[mid] == target) {
                res = mid; // Found the target, update the result
                start = mid + 1; // Keep searching to the right for the last occurrence
            } else if (target < nums[mid]) {
                end = mid - 1; // Search in the left half if the target is smaller
            } else {
                start = mid + 1; // Search in the right half if the target is larger
            }
        }
        return res; // Return the index of the last occurrence, or -1 if not found
    }
}
