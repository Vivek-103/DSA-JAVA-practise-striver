// Problem Statement: Infix to Postfix Conversion
// Class Name: InfixToPostfix
// Goal: Convert an infix expression (like "A+B*C") to a postfix expression (like "ABC*+")

// Example:
// Input:  A + B * C
// Output: A B C * +
// Explanation: '*' has higher precedence, so B*C is evaluated first.

import java.util.Stack;

public class InfixToPostfix {

    // Function to define operator precedence
    private static int precedence(char ch) {
        if (ch == '+' || ch == '-') return 1;      // lowest precedence
        else if (ch == '*' || ch == '/') return 2; // medium precedence
        else if (ch == '^') return 3;              // highest precedence
        return -1;
    }

    // Function to check if a character is an operator
    private static boolean isOperator(char ch) {
        return (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^');
    }

    // Main conversion function
    public static String infixToPostfix(String exp) {
        Stack<Character> stack = new Stack<>(); // Stack for operators
        StringBuilder result = new StringBuilder(); // Output (postfix) expression

        // Step 1: Traverse each character in the infix expression
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            // Step 2: If character is an operand (A-Z, a-z, or digit), add to result
            if (Character.isLetterOrDigit(ch)) {
                result.append(ch);
            }

            // Step 3: If character is '(', push to stack
            else if (ch == '(') {
                stack.push(ch);
            }

            // Step 4: If character is ')', pop until '(' is found
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop(); // remove '(' from stack
            }

            // Step 5: If it's an operator
            else if (isOperator(ch)) {
                // Pop operators from stack to result while:
                //  - stack not empty
                //  - precedence of top >= precedence of current operator
                //  - and current operator is left-associative (not '^')
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(ch) && ch != '^') {
                    result.append(stack.pop());
                }
                // Push current operator to stack
                stack.push(ch);
            }
        }

        // Step 6: Pop all remaining operators from stack
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        // Step 7: Return the postfix expression
        return result.toString();
    }

    // Driver code to test the function
    public static void main(String[] args) {
        String infix = "A+(B*C-(D/E^F)*G)*H";
        System.out.println("Infix Expression:   " + infix);
        System.out.println("Postfix Expression: " + infixToPostfix(infix));
    }
}

/*
─────────────────────────────────────────────
🧠 VISUALIZATION (Step-by-Step)

Example Input:  A + ( B * C - ( D / E ^ F ) * G ) * H

Stack (for operators) | Result (Postfix)
----------------------------------------
ch = A → operand → result = "A"
ch = + → push to stack → stack = [+]
ch = ( → push → stack = [+ (]
ch = B → operand → result = "AB"
ch = * → push → stack = [+ ( *]
ch = C → operand → result = "ABC"
ch = - → pop * → result = "ABC*" → push - → stack = [+ ( -]
ch = ( → push → stack = [+ ( - (]
ch = D → result = "ABC*D"
ch = / → push → stack = [+ ( - ( /]
ch = E → result = "ABC*DE"
ch = ^ → push → stack = [+ ( - ( / ^]
ch = F → result = "ABC*DEF"
ch = ) → pop ^, / → result = "ABC*DEF^/" → stack = [+ ( -]
ch = * → pop - → result = "ABC*DEF^/-" → push * → stack = [+ ( *]
ch = G → result = "ABC*DEF^/-G"
ch = ) → pop *, - → result = "ABC*DEF^/-G*-" → stack = [+]
ch = * → push → stack = [+ *]
ch = H → result = "ABC*DEF^/-G*-H"

End: Pop remaining operators → +, * → result = "ABC*DEF^/-G*-H*+"

✅ Final Postfix = ABC*DEF^/-G*-H*+

─────────────────────────────────────────────
⏱️ TIME COMPLEXITY ANALYSIS:

Let n = length of infix expression

Each character is:
 - Pushed at most once
 - Popped at most once

→ Time Complexity = O(n)
→ Space Complexity = O(n)  (for stack)

─────────────────────────────────────────────
💡 OPTIMAL INSIGHT:
- Stack ensures operators are applied in correct order of precedence.
- Parentheses handled by temporarily delaying evaluation.
- '^' is right-associative, so handled separately.
- Postfix eliminates need for parentheses, simplifying evaluation.

─────────────────────────────────────────────
✅ SUMMARY:
- Converts infix → postfix using stack.
- Handles operator precedence & associativity correctly.
- Time: O(n)
- Space: O(n)
─────────────────────────────────────────────
*/
