// Class name: RecursiveAtoi (short version of question name)
public class RecursiveAtoi {

    // Recursive helper function to convert string to integer
    static int convert(String s, int index, int result, int sign) {
        // Base case: if we've reached end of string or found a non-digit
        if (index == s.length() || !Character.isDigit(s.charAt(index)))
            return result * sign;

        // Get current digit value
        int digit = s.charAt(index) - '0';

        // Update result by shifting previous result by one place and adding digit
        result = result * 10 + digit;

        // Recursive call for next index
        return convert(s, index + 1, result, sign);
    }

    // Main function implementing atoi logic
    static int myAtoi(String s) {
        // Remove leading and trailing spaces
        s = s.trim();

        // Return 0 if empty string
        if (s.isEmpty()) return 0;

        int index = 0;
        int sign = 1;

        // Check for sign
        if (s.charAt(index) == '-') {
            sign = -1;
            index++;
        } else if (s.charAt(index) == '+') {
            index++;
        }

        // Call recursive function
        return convert(s, index, 0, sign);
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test cases
        System.out.println(myAtoi("42"));          // Output: 42
        System.out.println(myAtoi("   -123"));     // Output: -123
        System.out.println(myAtoi("4193 with text")); // Output: 4193
        System.out.println(myAtoi("words 99"));    // Output: 0
        System.out.println(myAtoi("+56"));         // Output: 56
    }
}
