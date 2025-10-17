// 🧩 Problem Statement: Postfix to Infix Conversion
// Given a postfix expression (like "AB+C*"), convert it into its equivalent infix form ("(A+B)*C").
//
// 📘 What is Postfix (Reverse Polish Notation)?
// - In Postfix, operators come *after* operands. Example: AB+ means A + B
//
// 📘 What is Infix?
// - In Infix, operators are *between* operands. Example: A + B
//
// 🎯 Goal:
// Convert a given postfix expression into its infix form using a stack.
//
// 🧠 Approach (Optimal - Using Stack):
// 1️⃣ Traverse the postfix expression from left to right.
// 2️⃣ If the character is an operand (A-Z or 0-9), push it to the stack.
// 3️⃣ If the character is an operator (+, -, *, /, ^):
//     → Pop two operands from the stack (say op1 and op2).
//     → Combine them as "(op1 operator op2)" and push this new string back to the stack.
// 4️⃣ At the end, the stack will contain one element — the final infix expression.
//
// 🕒 Time Complexity: O(N)  → Traverse each character once.
// 💾 Space Complexity: O(N) → Stack stores up to N/2 intermediate strings.
//
// ✅ Example:
// Postfix: AB+C*
// Step 1: Push A
// Step 2: Push B
// Step 3: Operator '+' → Pop B, A → Form "(A+B)" → Push "(A+B)"
// Step 4: Push C
// Step 5: Operator '*' → Pop C, "(A+B)" → Form "((A+B)*C)"
// Final Infix: ((A+B)*C)

import java.util.*;

public class PostfixToInfix {

    // Function to check if character is an operator
    static boolean isOperator(char ch) {
        return (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^');
    }

    // Function to convert Postfix to Infix
    static String postfixToInfix(String exp) {

        // Stack to store intermediate infix expressions
        Stack<String> stack = new Stack<>();

        // Traverse the postfix expression
        for (int i = 0; i < exp.length(); i++) {

            // Current character
            char ch = exp.charAt(i);

            // Case 1️⃣: If operand (letter or digit), push to stack
            if (Character.isLetterOrDigit(ch)) {
                stack.push(ch + ""); // Convert char to string and push
            }

            // Case 2️⃣: If operator, pop two operands and form infix expression
            else if (isOperator(ch)) {
                // Pop top two operands
                String op2 = stack.pop();  // second operand
                String op1 = stack.pop();  // first operand

                // Combine them in infix format and add parentheses for clarity
                String infix = "(" + op1 + ch + op2 + ")";

                // Push the resulting string back to stack
                stack.push(infix);
            }
        }

        // The final element of the stack will be the complete infix expression
        return stack.pop();
    }

    // 🔍 Driver Code for Testing
    public static void main(String[] args) {
        String postfix = "AB+C*";
        System.out.println("Postfix Expression : " + postfix);
        System.out.println("Infix Expression   : " + postfixToInfix(postfix));
    }
}

/*
====================================
🧮 Visualization of the Process:
====================================
Postfix Expression: AB+C*

Stack Operations:
-----------------
i=0: 'A' → push → Stack = [A]
i=1: 'B' → push → Stack = [A, B]
i=2: '+' → pop B, pop A → form "(A+B)" → push → Stack = [(A+B)]
i=3: 'C' → push → Stack = [(A+B), C]
i=4: '*' → pop C, pop (A+B) → form "((A+B)*C)" → push → Stack = [((A+B)*C)]

✅ Final Infix Expression = ((A+B)*C)

====================================
⏱️ Time & Space Complexity:
====================================
Time Complexity  : O(N)
→ Each character is processed once.
→ Stack operations (push/pop) are O(1).

Space Complexity : O(N)
→ Stack stores intermediate expressions of up to N/2 size.

====================================
📘 Quick Revision Notes:
====================================
• Postfix (Reverse Polish Notation) → Operator comes *after* operands.
• Algorithm:
   1. Traverse postfix left to right.
   2. Push operands to stack.
   3. When operator found → pop two → form "(op1 operator op2)" → push back.
• Final stack element = Infix expression.
• Handles parentheses automatically through explicit concatenation.
*/
