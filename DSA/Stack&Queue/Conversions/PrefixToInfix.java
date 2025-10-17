// 🧩 Problem Statement: Prefix to Infix Conversion
// Given a Prefix expression (like "*+AB-CD"), convert it into its equivalent Infix form ("((A+B)*(C-D))").
//
// 📘 What is Prefix (Polish Notation)?
// - In Prefix, operators come *before* operands.
//   Example: +AB → represents (A + B)
//
// 📘 What is Infix?
// - In Infix, operators are *between* operands.
//   Example: (A + B)
//
// 🎯 Goal:
// Convert a prefix expression into infix form using a stack.
//
// 🧠 Approach (Optimal - Using Stack):
// 1️⃣ Traverse the prefix expression from **right to left** (reverse order).
// 2️⃣ If the character is an operand (A–Z or 0–9), push it to the stack.
// 3️⃣ If the character is an operator (+, -, *, /, ^):
//      → Pop two operands from the stack (say op1 and op2).
//      → Combine them as "(op1 operator op2)".
//      → Push the resulting string back to the stack.
// 4️⃣ At the end, the stack will contain a single element — the final infix expression.
//
// 🕒 Time Complexity: O(N) — each element processed once.
// 💾 Space Complexity: O(N) — stack stores intermediate expressions.
//
// ✅ Example:
// Prefix: *+AB-CD
// Step-by-step Conversion:
//   → Start from right: D, C, -, B, A, +, *
//   → ((A+B)*(C-D))

import java.util.*;

public class PrefixToInfix {

    // Function to check if a character is an operator
    static boolean isOperator(char ch) {
        return (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^');
    }

    // Function to convert Prefix to Infix
    static String prefixToInfix(String exp) {

        // Stack to store intermediate infix expressions
        Stack<String> stack = new Stack<>();

        // Traverse the prefix expression from RIGHT to LEFT
        for (int i = exp.length() - 1; i >= 0; i--) {

            // Current character
            char ch = exp.charAt(i);

            // Case 1️⃣: If operand, push directly to stack
            if (Character.isLetterOrDigit(ch)) {
                stack.push(ch + ""); // Convert char → String and push
            }

            // Case 2️⃣: If operator, pop two operands and combine
            else if (isOperator(ch)) {
                // Pop two operands from stack
                String op1 = stack.pop(); // first operand
                String op2 = stack.pop(); // second operand

                // Combine them into infix format
                String infix = "(" + op1 + ch + op2 + ")";

                // Push the combined result back to the stack
                stack.push(infix);
            }
        }

        // Final infix expression will be at top of the stack
        return stack.pop();
    }

    // 🔍 Driver code for testing
    public static void main(String[] args) {
        String prefix = "*+AB-CD";
        System.out.println("Prefix Expression : " + prefix);
        System.out.println("Infix Expression  : " + prefixToInfix(prefix));
    }
}

/*
====================================
🧮 Visualization of the Process:
====================================
Prefix Expression: *+AB-CD

Traverse from RIGHT to LEFT:

Step | Char | Stack Action                 | Stack Content
-----------------------------------------------------------
1    | D    | Push operand                 | [D]
2    | C    | Push operand                 | [D, C]
3    | -    | Operator → Pop C, D → "(C-D)"| [(C-D)]
4    | B    | Push operand                 | [(C-D), B]
5    | A    | Push operand                 | [(C-D), B, A]
6    | +    | Operator → Pop A, B → "(A+B)"| [(C-D), (A+B)]
7    | *    | Operator → Pop (A+B), (C-D) → "((A+B)*(C-D))"
     |      | Push result                  | [((A+B)*(C-D))]

✅ Final Infix Expression = ((A+B)*(C-D))

====================================
⏱️ Time & Space Complexity:
====================================
Time Complexity  : O(N)
→ Each character is processed exactly once.
→ Stack operations (push, pop) are O(1).

Space Complexity : O(N)
→ Stack stores intermediate expressions.

====================================
📘 Quick Revision Notes:
====================================
• Traverse PREFIX from RIGHT ➡ LEFT.
• Push operands, and when operator is found:
   → Pop 2 operands
   → Form "(op1 operator op2)"
   → Push back
• The final element in stack = full INFIX expression.
• Handles both single letters and digits as operands.
*/
