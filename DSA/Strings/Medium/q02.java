package Medium;

class q02 {
    // Time Complexity: O(n), where n is the length of the input string
    // We traverse each character in the string exactly once

    public int maxDepth(String s) {
        int currentDepth = 0; // Tracks the current depth of parentheses
        int maxDepth = 0;     // Tracks the maximum depth found so far

        // Iterate through each character in the string
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i); // Get the character at index i

            if (c == '(') { // If it's an opening parenthesis
                currentDepth++; // Increase the current depth
                maxDepth = Math.max(maxDepth, currentDepth); // Update max depth if needed
            } else if (c == ')') { // If it's a closing parenthesis
                currentDepth--; // Decrease the current depth
            }
            // Other characters are ignored
        }

        return maxDepth; // Return the maximum depth found
    }

    public static void main(String[] args) {
        q02 solution = new q02(); // Create an instance of q02
        String input = "(1+(2*3)+((8)/4))+1"; // Example input
        int result = solution.maxDepth(input); // Call maxDepth method
        System.out.println("Maximum nesting depth: " + result); // Print the result
    }
}
