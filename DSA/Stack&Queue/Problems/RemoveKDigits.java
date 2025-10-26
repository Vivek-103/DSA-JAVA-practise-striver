// File Name: RemoveKDigits.java
// Problem: Remove K Digits
// Objective: Given a number represented as a string num and an integer k,
// remove k digits from the number so that the new number is the smallest possible.

import java.util.*;  // Importing required utilities

public class RemoveKDigits {

    // --------------------------- MAIN METHOD --------------------------- //
    public static void main(String[] args) {

        // Example Test Case
        String num = "1432219";
        int k = 3;

        // Call the function and print the result
        String result = removeKdigits(num, k);

        System.out.println("Original Number: " + num);
        System.out.println("Digits to remove (k): " + k);
        System.out.println("Smallest possible number after removal: " + result);

        // ---------------- VISUALIZATION EXPLANATION ----------------
        /*
         * Visualization:
         * num = "1432219", k = 3
         *
         * Step 1: Use stack to build smallest number.
         * Stack: []
         * 
         * i=0 -> '1' : stack empty -> push '1' -> [1]
         * i=1 -> '4' : 4 > 1 -> push -> [1,4]
         * i=2 -> '3' : 3 < 4 -> pop '4', k=2 -> push '3' -> [1,3]
         * i=3 -> '2' : 2 < 3 -> pop '3', k=1 -> push '2' -> [1,2]
         * i=4 -> '2' : 2 == 2 -> push -> [1,2,2]
         * i=5 -> '1' : 1 < 2 -> pop '2', k=0 -> push '1' -> [1,2,2,1]
         * (stop popping as k=0)
         * i=6 -> '9' : push -> [1,2,2,1,9]
         *
         * Step 2: k=0 now. Form number = 12219.
         * Remove leading zeros if any.
         * Final Answer: "1219"
         */
    }

    // ---------------------- FUNCTION IMPLEMENTATION ---------------------- //
    public static String removeKdigits(String num, int k) {

        // Edge case: if k equals the length of num, removing all digits -> return "0"
        if (k == num.length()) return "0";

        // Stack to store digits for building the smallest number
        Stack<Character> stack = new Stack<>();

        // Traverse each digit in the number
        for (char digit : num.toCharArray()) {

            // While stack not empty, k > 0, and top of stack > current digit,
            // pop from stack to remove higher digits and make smaller number
            while (!stack.isEmpty() && k > 0 && stack.peek() > digit) {
                stack.pop(); // remove previous larger digit
                k--;         // one digit removed
            }

            // Push current digit to stack
            stack.push(digit);
        }

        // If k still > 0 after full traversal, remove remaining digits from end
        while (k > 0) {
            stack.pop();
            k--;
        }

        // Build result from stack
        StringBuilder sb = new StringBuilder();
        for (char ch : stack) {
            sb.append(ch);
        }

        // Remove leading zeros (e.g. "00123" -> "123")
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        // Return the final result, or "0" if empty
        return sb.length() == 0 ? "0" : sb.toString();
    }

    // --------------------------- TIME COMPLEXITY --------------------------- //
    /*
     * Time Complexity:
     * ----------------
     * O(n) — where n is the length of the input number string.
     * Each digit is pushed and popped at most once.
     *
     * Space Complexity:
     * -----------------
     * O(n) — for the stack used to store digits.
     */
}
