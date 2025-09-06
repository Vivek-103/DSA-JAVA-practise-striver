public class q04 {

    // Function to find the longest common prefix among an array of strings
    public String longestCommonPrefix(String[] strs) {
        // Check if the array is empty or null
        if (strs == null || strs.length == 0) {
            return ""; // Return empty string if no input
        }

        // Initialize the prefix as the first string in the array
        String prefix = strs[0];

        // Loop through the rest of the strings in the array
        for (int i = 1; i < strs.length; i++) {
            // Check if the current string starts with the prefix
            while (strs[i].indexOf(prefix) != 0) {
                // If not, remove the last character from the prefix
                prefix = prefix.substring(0, prefix.length() - 1);
                // If prefix becomes empty, there is no common prefix
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        
        // Return the longest common prefix found
        return prefix;
    }

    // Main method to test the function
    public static void main(String[] args) {
        // Create an object of the q04 class
        q04 obj = new q04();

        // Define an array of strings to test
        String[] input = {"flower", "flow", "flight"};

        // Call the longestCommonPrefix method and print the result
        System.out.println("Longest Common Prefix: " + obj.longestCommonPrefix(input));
    }
}

/*
Time Complexity:
- In the worst case, we compare the prefix with each string completely.
- Let n be the number of strings and m be the length of the smallest string.
- The time complexity is O(n * m).
*/
