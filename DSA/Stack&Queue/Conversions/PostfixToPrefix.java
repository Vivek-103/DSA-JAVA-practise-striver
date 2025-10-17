// 🧩 Problem Statement: Postfix to Prefix Conversion
// Given a postfix expression (like "AB+C*"), convert it into its equivalent prefix form ("*+ABC").
//
// 📘 What is Postfix (Reverse Polish Notation)?
// - In Postfix, operators come *after* operands.  Example: AB+ → (A + B)
//
// 📘 What is Prefix (Polish Notation)?
// - In Prefix, operators come *before* operands. Example: +AB → (A + B)
//
// 🎯 Goal:
// Convert a given postfix expression into prefix form using a stack.
//
// 🧠 Approach (Optimal - Using Stack):
// 1️⃣ Traverse the postfix expression from LEFT to RIGHT.
// 2️⃣ If the character is an operand (A-Z or 0-9), push it to the stack.
// 3️⃣ If the character is an operator (+, -, *, /, ^):
//      → Pop two operands from stack (say op2 and op1).
//      → Combine them as "operator + op1 + op2".
//      → Push this combined result back to the stack.
// 4️⃣ At the end, the stack will contain one element — the final prefix expression.
//
// 🕒 Time Complexity: O(N) — every character processed once.
// 💾 Space Complexity: O(N) — stack stores intermediate strings.
//
// ✅ Example:
// Postfix: AB+C*
// Step 1: Read A → push
// Step 2: Read B → push
// Step 3: Read '+' → pop B, A → form "+AB" → push
// Step 4: Read C → push
// Step 5: Read '*' → pop C, "+AB" → form "*+ABC" → push
// ✅ Prefix = *+ABC

import java.util.*;

public class PostfixToPrefix {

    // Function to check if a character is an operator
    static boolean isOperator(char ch) {
        return (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^');
    }

    // Function to convert Postfix to Prefix
    static String postfixToPrefix(String exp) {

        // Stack to store intermediate results
        Stack<String> stack = new Stack<>();

        // Traverse the postfix expression from LEFT to RIGHT
        for (int i = 0; i < exp.length(); i++) {

            // Current character
            char ch = exp.charAt(i);

            // Case 1️⃣: If operand, push it directly to stack
            if (Character.isLetterOrDigit(ch)) {
                stack.push(ch + ""); // Convert char → String and push
            }

            // Case 2️⃣: If operator, pop top two operands and combine
            else if (isOperator(ch)) {
                // Pop top two elements
                String op2 = stack.pop(); // second operand
                String op1 = stack.pop(); // first operand

                // Form prefix expression (operator first)
                String prefix = ch + op1 + op2;

                // Push the combined prefix string back to stack
                stack.push(prefix);
            }
        }

        // The final element of stack is the prefix expression
        return stack.pop();
    }

    // 🔍 Driver Code to test the function
    public static void main(String[] args) {
        String postfix = "AB+C*";
        System.out.println("Postfix Expression : " + postfix);
        System.out.println("Prefix Expression  : " + postfixToPrefix(postfix));
    }
}

/*
====================================
🧮 Visualization of the Process:
====================================
Postfix Expression: AB+C*

Step | Char | Action                                  | Stack Content
---------------------------------------------------------------
1    | A    | Operand → Push                          | [A]
2    | B    | Operand → Push                          | [A, B]
3    | +    | Operator → Pop B, A → "+AB" → Push      | [+AB]
4    | C    | Operand → Push                          | [+AB, C]
5    | *    | Operator → Pop C, +AB → "*+ABC" → Push  | [*+ABC]

✅ Final Prefix Expression = *+ABC

====================================
⏱️ Time & Space Complexity:
====================================
Time Complexity  : O(N)
→ Each character is scanned once.
→ Stack operations (push/pop) are O(1).

Space Complexity : O(N)
→ Stack stores intermediate prefix strings (max size N/2).

====================================
📘 Quick Revision Notes:
====================================
• Traverse POSTFIX → LEFT ➡ RIGHT.
• Push operands directly.
• When operator found:
   → Pop 2 operands.
   → Combine as "operator + op1 + op2".
   → Push back to stack.
• Final stack element = PREFIX Expression.
• Works for any valid postfix with single-character operands.
*/
