// Problem Statement: Valid Parenthesis
// Class Name: ValidParenthesis
// Goal: Check if a string of brackets ( '()', '{}', '[]' ) is valid or not.

// Example:
// Input: s = "{[()]}"
// Output: true
// Explanation: Every opening bracket has a matching closing one in correct order.

import java.util.Stack; // Importing Stack class from java.util package

public class ValidParenthesis {

    // Function to check if parenthesis string is valid
    public static boolean isValid(String s) {

        // Step 1: Create a Stack to store opening brackets
        Stack<Character> stack = new Stack<>();

        // Step 2: Traverse through each character of the string
        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i); // Get current character

            // Step 3: If the character is an opening bracket, push it to stack
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } 
            // Step 4: If it's a closing bracket, we must check the top of stack
            else {

                // If stack is empty, means there is no matching opening bracket
                if (stack.isEmpty()) return false;

                // Step 5: Pop the top element and compare with current closing bracket
                char top = stack.pop();

                // Step 6: Check for mismatch
                if ((ch == ')' && top != '(') ||
                    (ch == '}' && top != '{') ||
                    (ch == ']' && top != '[')) {
                    return false; // Mismatched pair found
                }
            }
        }

        // Step 7: If stack is empty, all brackets were matched correctly
        return stack.isEmpty();
    }

    // Driver Code to test
    public static void main(String[] args) {
        String s = "{[()]}"; // ✅ valid
        System.out.println(isValid(s)); // Output: true

        String s2 = "{[(])}"; // ❌ invalid
        System.out.println(isValid(s2)); // Output: false
    }
}

/*
───────────────────────────────
🧠 VISUALIZATION (for s = "{[()]}")

Step-by-step Stack Process:
---------------------------------
Input: { [ ( ) ] }

i=0 → '{' → push '{' → Stack: {  
i=1 → '[' → push '[' → Stack: { [  
i=2 → '(' → push '(' → Stack: { [ (  
i=3 → ')' → pop '(' → Stack: { [  
i=4 → ']' → pop '[' → Stack: {  
i=5 → '}' → pop '{' → Stack: [empty]

✅ All brackets matched → Valid Parenthesis

For "{[(])}" → mismatch happens at ']' (top = '(')

───────────────────────────────
⏱️ TIME COMPLEXITY ANALYSIS:
---------------------------------
- Each character is pushed or popped at most once → O(n)
- n = length of the string

🧮 SPACE COMPLEXITY:
---------------------------------
- Stack can hold at most n/2 opening brackets → O(n)

───────────────────────────────
💡 OPTIMAL INSIGHT:
- Stack is ideal because it naturally follows LIFO,
  matching the “last opened bracket closes first” rule.
- Any other approach (like counting) fails for nested brackets.

───────────────────────────────
✅ Summary:
- Uses Stack to track openings.
- Valid only if every opening has matching closing and stack ends empty.
- Optimal solution: O(n) time, O(n) space.
───────────────────────────────
*/
