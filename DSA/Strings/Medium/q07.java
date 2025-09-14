/*
Problem: Sum of Beauty of All Substrings
----------------------------------------
The beauty of a string is defined as:
    beauty = (frequency of most frequent character) - (frequency of least frequent character)

Task:
- For every substring of the given string, calculate its beauty.
- Return the sum of beauty values of all substrings.

Example:
Input: "aabcb"
Substrings and their beauties:
- "a" -> 0
- "aa" -> 0
- "aab" -> 1
- "aabc" -> 1
- "aabcb" -> 2
...
Total Sum = 5
*/

class q07 {
    // Time Complexity: O(n^2 * 26) ≈ O(n^2)
    // Outer loop runs O(n) times (start index of substring)
    // Inner loop runs O(n) times (end index of substring)
    // For each substring, we compute max/min in O(26)
    // => Total: O(n^2)

    public int beautySum(String s) {
        int n = s.length();       // Length of the string
        int totalBeauty = 0;      // To accumulate the sum of beauty values

        // Outer loop → fix the start of the substring
        for (int i = 0; i < n; i++) {
            int[] freq = new int[26]; // Frequency array for characters in current substring

            // Inner loop → extend substring from i to j
            for (int j = i; j < n; j++) {
                char c = s.charAt(j);    // Current character
                freq[c - 'a']++;         // Update frequency of this character

                // Find the max and min frequency in the current substring
                int maxFreq = 0;
                int minFreq = Integer.MAX_VALUE;

                for (int f : freq) {
                    if (f > 0) {              // Consider only characters present in substring
                        maxFreq = Math.max(maxFreq, f);
                        minFreq = Math.min(minFreq, f);
                    }
                }

                // Beauty = maxFreq - minFreq
                totalBeauty += (maxFreq - minFreq);
            }
        }

        return totalBeauty; // Return the final sum of beauty values
    }

    public static void main(String[] args) {
        q07 solution = new q07(); // Create instance of q07

        // Example test cases
        String input1 = "aabcb";   // Expected Output: 5
        String input2 = "aabcbaa"; // Example: larger test case

        System.out.println("Input: \"" + input1 + "\" Output: " + solution.beautySum(input1));
        System.out.println("Input: \"" + input2 + "\" Output: " + solution.beautySum(input2));
    }
}
