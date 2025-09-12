package Medium;

class q05 {
    // Time Complexity: O(n^2), where n is the length of the string
    // We expand around each possible center, and each expansion takes O(n) in the worst case

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return ""; // If the string is empty or null, return an empty result
        }

        int start = 0; // Start index of the longest palindrome found
        int end = 0;   // End index of the longest palindrome found

        // Iterate over each character in the string, treating each as a potential center
        for (int i = 0; i < s.length(); i++) {
            // Check for odd-length palindromes (single center at i)
            int len1 = expandFromCenter(s, i, i);

            // Check for even-length palindromes (center between i and i+1)
            int len2 = expandFromCenter(s, i, i + 1);

            // Choose the longer palindrome length between the two
            int len = Math.max(len1, len2);

            // Update start and end if a longer palindrome is found
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        // Return the longest palindromic substring by slicing from start to end (inclusive)
        return s.substring(start, end + 1);
    }

    // Helper method to expand around the center and return the length of palindrome
    private int expandFromCenter(String s, int left, int right) {
        // Expand as long as the characters at left and right are equal and within bounds
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Return the length of the palindrome found
        return right - left - 1;
    }

    public static void main(String[] args) {
        q05 solution = new q05(); // Create an instance of q05

        // Example test cases
        String input1 = "babad";
        String input2 = "cbbd";
        String input3 = "a";
        String input4 = "ac";

        System.out.println("Input: \"" + input1 + "\" Output: " + solution.longestPalindrome(input1));
        System.out.println("Input: \"" + input2 + "\" Output: " + solution.longestPalindrome(input2));
        System.out.println("Input: \"" + input3 + "\" Output: " + solution.longestPalindrome(input3));
        System.out.println("Input: \"" + input4 + "\" Output: " + solution.longestPalindrome(input4));
    }
}

