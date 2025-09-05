package Easy;
// Problem: Remove Outermost Parentheses
// Given a valid parentheses string, remove the outermost parentheses of every primitive substring.

class q02 {
    public String removeOuterParentheses(String s) {
        // StringBuilder is used instead of string concatenation for efficiency (O(1) append)
        StringBuilder result = new StringBuilder();

        // This counter keeps track of the "depth" of parentheses
        // Example: '(' increases depth, ')' decreases depth
        int depth = 0;

        // Traverse the string character by character
        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                // If depth > 0, it means this '(' is NOT the outermost, so we keep it
                if (depth > 0) {
                    result.append(ch);
                }
                // Increase depth after processing
                depth++;
            } else {
                // For closing bracket, first decrease depth
                depth--;

                // If depth > 0 after decrement, this ')' is not the outermost, so we keep it
                if (depth > 0) {
                    result.append(ch);
                }
            }
        }

        // Convert StringBuilder to String and return
        return result.toString();
    }
}

/*
---------------------------------
Example Walkthrough:
---------------------------------
Input: "(()())(())"
Decomposition: "(()())" + "(())"

Primitive 1: (()())
  Remove outermost -> ()()
Primitive 2: (())
  Remove outermost -> ()
Output: "()()()"

---------------------------------
Time Complexity:
---------------------------------
- We traverse the string once (O(n)).
- Each character is appended at most once into StringBuilder (O(1) amortized).
- Total time = O(n), where n = length of string.

Space Complexity:
---------------------------------
- StringBuilder stores the result (O(n)).
- Extra variable depth = O(1).
- Overall: O(n)
*/
