package Easy;

// Program to find the largest odd number from a given numeric string

class q03 {
    // Function to find the largest odd number
    public String largestOddNumber(String num) {
        // Start from the last digit (rightmost side of string)
        for (int i = num.length() - 1; i >= 0; i--) {
            char ch = num.charAt(i);  // Get current character (digit)

            // Check if this digit is odd
            if ((ch - '0') % 2 == 1) {
                // If odd, return substring from start to this index
                // This ensures the number ends with an odd digit
                return num.substring(0, i + 1);
            }
        }

        // If no odd digit found, return empty string (or "0" if required)
        return "";
    }

    // Main method to test the function
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Test case 1
        String num1 = "35427";
        System.out.println("Largest odd number in " + num1 + " = " + sol.largestOddNumber(num1));
        // Output: "35427"

        // Test case 2
        String num2 = "4206";
        System.out.println("Largest odd number in " + num2 + " = " + sol.largestOddNumber(num2));
        // Output: "" (no odd number exists)

        // Test case 3
        String num3 = "52";
        System.out.println("Largest odd number in " + num3 + " = " + sol.largestOddNumber(num3));
        // Output: "5"
    }
}

/*
-----------------------------------
Time Complexity Analysis:
-----------------------------------
- We scan the string once from right to left → O(n),
  where n = length of the string.
- Substring operation → O(n) in worst case (Java creates new String).
- Overall time complexity = O(n).
- Space complexity = O(1), as we only use a few variables
  (ignoring substring output which is required result).
*/
