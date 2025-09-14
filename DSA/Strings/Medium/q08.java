/*
Problem: Reverse Every Word in a String
---------------------------------------
Given a string `s`, you need to reverse every word in the string 
while maintaining the order of the words and whitespace. 

Example:
Input:  "Hello World"
Output: "olleH dlroW"

Approach:
1. Split the string into words using space as a delimiter.
2. Reverse each word individually.
3. Join the reversed words back together with spaces.

Time Complexity: 
- Splitting takes O(n), where n = length of the string.
- Reversing each word collectively also takes O(n).
- Joining words back takes O(n).
=> Overall Time Complexity = O(n).
*/

class q08 {
    // Method to reverse each word in a string
    public static String reverseWords(String s) {
        // Split the string into words based on spaces
        String[] words = s.split(" ");
        
        // Use StringBuilder to build the final result
        StringBuilder result = new StringBuilder();
        
        // Loop through each word
        for (int i = 0; i < words.length; i++) {
            // Reverse the current word using StringBuilder reverse() method
            String reversedWord = new StringBuilder(words[i]).reverse().toString();
            
            // Append reversed word to the result
            result.append(reversedWord);
            
            // If it's not the last word, add a space
            if (i < words.length - 1) {
                result.append(" ");
            }
        }
        
        // Convert StringBuilder to String and return
        return result.toString();
    }
    
    // Main method to test the function
    public static void main(String[] args) {
        String input = "Hello World from Java";
        String output = reverseWords(input);
        System.out.println("Original: " + input);
        System.out.println("Reversed: " + output);
    }
}
