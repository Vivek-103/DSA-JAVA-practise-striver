package Medium;
 
class q03 {
    // Time Complexity: O(n), where n is the length of the input string
    // We process each character at most once while parsing

    public int myAtoi(String s) {
        int index = 0; // Pointer to traverse the string
        int n = s.length(); // Length of the input string
        int result = 0; // Final integer result
        int sign = 1; // To store the sign of the number, default is positive

        // Step 1: Skip all leading whitespaces
        while (index < n && s.charAt(index) == ' ') {
            index++;
        }

        // Step 2: Check if the next character is '+' or '-'
        if (index < n) {
            if (s.charAt(index) == '+') {
                sign = 1; // Positive sign
                index++;
            } else if (s.charAt(index) == '-') {
                sign = -1; // Negative sign
                index++;
            }
        }

        // Step 3: Process numerical digits and build the result
        while (index < n) {
            char c = s.charAt(index);

            // If the character is not a digit, break the loop
            if (c < '0' || c > '9') {
                break;
            }

            int digit = c - '0'; // Convert character to integer

            // Step 4: Handle overflow by checking before multiplying or adding
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            // Update result by shifting digits left and adding the new digit
            result = result * 10 + digit;
            index++;
        }

        // Step 5: Apply the sign and return the result
        return result * sign;
    }

    public static void main(String[] args) {
        q03 solution = new q03(); // Create an instance of q03

        // Example test cases
        String input1 = "   -42";
        String input2 = "4193 with words";
        String input3 = "words and 987";
        String input4 = "-91283472332";

        System.out.println("Input: \"" + input1 + "\" Output: " + solution.myAtoi(input1));
        System.out.println("Input: \"" + input2 + "\" Output: " + solution.myAtoi(input2));
        System.out.println("Input: \"" + input3 + "\" Output: " + solution.myAtoi(input3));
        System.out.println("Input: \"" + input4 + "\" Output: " + solution.myAtoi(input4));
    }
}

