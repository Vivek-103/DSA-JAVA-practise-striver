// 🧩 Problem Statement: Infix to Prefix Conversion
// Given an infix expression (like A+B*C), convert it into prefix form (*+ABC).
// Infix: Operators are between operands -> A + B
// Prefix: Operators come before operands -> + A B

// ✅ Approach (Optimal):
// 1️⃣ Reverse the infix expression.
// 2️⃣ Replace '(' with ')' and ')' with '('.
// 3️⃣ Convert the reversed expression to postfix using a stack.
// 4️⃣ Reverse the postfix result to get the prefix expression.

// 🕒 Time Complexity: O(N) — Each element is scanned once.
// 🧠 Space Complexity: O(N) — Due to stack usage and output string.

// 🎯 Visualization Example:
// Infix : (A-B/C)*(A/K-L)
// Step 1: Reverse  => (L-K/A)*(C/B-A)
// Step 2: Swap '(' & ')' => (L-K/A)*((C/B)-A)
// Step 3: Convert to postfix => LK/A-*CB/A--*
// Step 4: Reverse postfix => *-A/BC*/A-KL

import java.util.*;

public class InfixToPrefix {

    // Function to return precedence of operators
    static int precedence(char ch) {
        if (ch == '^') return 3;     // Highest precedence
        else if (ch == '*' || ch == '/') return 2;  // Medium precedence
        else if (ch == '+' || ch == '-') return 1;  // Lowest precedence
        else return -1;              // Not an operator
    }

    // Function to check if a character is an operator
    static boolean isOperator(char ch) {
        return (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^');
    }

    // Core function: Convert Infix expression to Prefix
    static String infixToPrefix(String s) {
        // Step 1️⃣: Reverse the infix expression
        StringBuilder input = new StringBuilder(s);
        input.reverse();

        // Step 2️⃣: Swap '(' with ')' and vice versa
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                input.setCharAt(i, ')');
            } else if (input.charAt(i) == ')') {
                input.setCharAt(i, '(');
            }
        }

        // Step 3️⃣: Convert the modified infix to postfix
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            // Case 1: If operand, directly add to result
            if (Character.isLetterOrDigit(ch)) {
                result.append(ch);
            }
            // Case 2: If '(', push to stack
            else if (ch == '(') {
                stack.push(ch);
            }
            // Case 3: If ')', pop until '('
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop(); // Remove '('
            }
            // Case 4: If operator, pop higher or equal precedence operators
            else if (isOperator(ch)) {
                while (!stack.isEmpty() && precedence(ch) < precedence(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(ch);
            }
        }

        // Pop all remaining operators
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        // Step 4️⃣: Reverse the postfix result to get prefix
        return result.reverse().toString();
    }

    // 🔍 Driver Code to Test
    public static void main(String[] args) {
        String infix = "(A-B/C)*(A/K-L)";
        String prefix = infixToPrefix(infix);
        System.out.println("Infix Expression  : " + infix);
        System.out.println("Prefix Expression : " + prefix);
    }
}


// ====================================
// 🧮 Visualization of the Process:
 //====================================
 //Infix : (A-B/C)*(A/K-L)
 
 //Step 1️⃣ Reverse => (L-K/A)*(C/B-A)
 //Step 2️⃣ Swap '(' and ')' => (L-K/A)*((C/B)-A)
 //Step 3️⃣ Convert to postfix => LK/A-*CB/A--*
 //Step 4️⃣ Reverse postfix => *-A/BC*/A-KL
 
// ✅ Final Prefix : *-A/BC*/A-KL
 
// ====================================
 ⏱️// Time & Space Complexity:
 //====================================
 //Time Complexity  : O(N)
 //- Each character processed once
 //- Stack operations are O(1)
 
 //Space Complexity : O(N)
 //- Stack used to hold operators
 //- Output string also O(N)
 
 //====================================
 //📘 Quick Revision Points:
 //====================================
 //• Reverse + Swap brackets + Postfix + Reverse again = Prefix
 //• Precedence order: ^ > * / > + -
 //• Always pop higher or equal precedence operators from stack
 //• '(' acts as temporary boundary in expression
 