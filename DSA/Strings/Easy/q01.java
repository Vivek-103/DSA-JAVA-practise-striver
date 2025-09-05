// Program to reverse the words in a given String
// Example: "hello world java" -> "java world hello"

import java.io.*;

public class q01 {

    // Function to reverse words in a string
    static private String result(String s) {
        // Initialize left pointer at start of string
        int left = 0;

        // Initialize right pointer at end of string (not directly used, but loop condition checks it)
        int right = s.length() - 1;

        // Temporary variable to hold current word
        String temp = "";

        // Final answer string that will store reversed words
        String ans = "";

        // Traverse the string character by character
        while (left <= right) {
            // Get the current character at position 'left'
            char ch = s.charAt(left);

            // If character is not a space, add it to current word
            if (ch != ' ') {
                temp += ch;
            }
            // If character is a space, it means one word has ended
            else if (ch == ' ') {
                // If answer already has some words, append current word before it
                if (!ans.equals("")) {
                    ans = temp + " " + ans;
                }
                // If answer is empty, simply assign current word
                else {
                    ans = temp;
                }
                // Reset temp for next word
                temp = "";
            }
            // Move to next character
            left++;
        }

        // After loop ends, we still need to add the last word (if present)
        if (!temp.equals("")) {
            if (!ans.equals("")) {
                ans = temp + " " + ans;
            } else {
                ans = temp;
            }
        }

        // Return the final reversed string
        return ans;
    }

    // Main method to test the function
    public static void main(String[] args) throws IOException {
        // Example input string
        String input = "hello world java";

        // Call the function and store the result
        String reversed = result(input);

        // Print original string
        System.out.println("Original String: " + input);

        // Print reversed words string
        System.out.println("Reversed Words: " + reversed);
    }
}

/*
-------------------------------
Time Complexity Analysis:
-------------------------------
- The function traverses the string once -> O(n), where n = length of string
- String concatenation using '+' inside loops can be costly 
  (O(n) each time since Strings are immutable in Java).
- So in worst case (many small words), complexity = O(n^2).
- If we used StringBuilder instead of String concatenation, 
  we could reduce it to O(n).

Space Complexity: O(n) for storing 'ans' and 'temp'
*/
