// 🧩 Problem Statement: Prefix to Postfix Conversion
// Given a prefix expression (like "*+AB-CD"), convert it into its equivalent postfix form ("AB+CD-*").
//
// 📘 What is Prefix (Polish Notation)?
// - In Prefix, operators come *before* operands. Example: +AB → (A + B)
//
// 📘 What is Postfix (Reverse Polish Notation)?
// - In Postfix, operators come *after* operands. Example: AB+ → (A + B)
//
// 🎯 Goal:
// Convert a prefix expression into postfix form using a stack.
//
// 🧠 Approach (Optimal - Using Stack):
// 1️⃣ Traverse the prefix expression from RIGHT to LEFT.
// 2️⃣ If the character is an operand (A–Z or 0–9), push it to the stack.
// 3️⃣ If the character is an operator (+, -, *, /, ^):
//      → Pop two operands from the stack (say op1 and op2).
//      → Combine them as "op1 + op2 + operator".
//      → Push the new string back to the stack.
// 4️⃣ At the end, the stack will contain the final postfix expression.
//
// 🕒 Time Complexity: O(N) — each character processed once.
// 💾 Space Complexity: O(N) — stack used to store intermediate results.
//
// ✅ Example:
// Prefix: *+AB-CD
// Step-by-step Conversion:
//   1. Read from right → D, C, -, B, A, +, *
//   2. Output → AB+CD-*
// ✅ Postfix = AB+CD-*

import java.util.*;

public class PrefixToPostfix {

    // Function to check if a character is an operator
    static boolean isOperator(char ch) {
        return (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^');
    }

    // Function to convert Prefix to Postfix
    static String prefixToPostfix(String exp) {

        // Stack to store intermediate postfix expressions
        Stack<String> stack = new Stack<>();

        // Traverse the prefix expression from RIGHT to LEFT
        for (int i = exp.length() - 1; i >= 0; i--) {

            // Current character
            char ch = exp.charAt(i);

            // Case 1️⃣: If operand, push to stack
            if (Character.isLetterOrDigit(ch)) {
                stack.push(ch + ""); // Convert char → String and push
            }

            // Case 2️⃣: If operator, pop two operands and combine
            else if (isOperator(ch)) {
                // Pop top two elements
                String op1 = stack.pop(); // first operand
                String op2 = stack.pop(); // second operand

                // Combine in postfix form: operand1 + operand2 + operator
                String postfix = op1 + op2 + ch;

                // Push back the result
                stack.push(postfix);
            }
        }

        // The top element of the stack is the final postfix expression
        return stack.pop();
    }

    // 🔍 Driver Code for Testing
    public static void main(String[] args) {
        String prefix = "*+AB-CD";
        System.out.println("Prefix Expression : " + prefix);
        System.out.println("Postfix Expression: " + prefixToPostfix(prefix));
    }
}

/*
====================================
🧮 Visualization of the Process:
====================================
Prefix Expression: *+AB-CD

Traverse from RIGHT to LEFT:

Step | Char | Action                                     | Stack Content
--------------------------------------------------------------
1    | D    | Operand → Push                             | [D]
2    | C    | Operand → Push                             | [D, C]
3    | -    | Operator → Pop C, D → Combine "CD-" → Push | [CD-]
4    | B    | Operand → Push                             | [CD-, B]
5    | A    | Operand → Push                             | [CD-, B, A]
6    | +    | Operator → Pop A, B → Combine "AB+" → Push | [CD-, AB+]
7    | *    | Operator → Pop AB+, CD- → Combine "AB+CD-*"| [AB+CD-*]

✅ Final Postfix Expression = AB+CD-*

====================================
⏱️ Time & Space Complexity:
====================================
Time Complexity  : O(N)
→ Each character processed once.
→ Push/Pop operations take O(1).

Space Complexity : O(N)
→ Stack used for storing intermediate strings.

====================================
📘 Quick Revision Notes:
====================================
• Traverse PREFIX from RIGHT ➡ LEFT.
• Push operands directly.
• When operator found:
   → Pop 2 operands.
   → Combine as "operand1 + operand2 + operator".
   → Push back to stack.
• Final stack element = POSTFIX Expression.
• Works for any valid prefix with single-character operands.
*/
