/**
 * A class to demonstrate the optimal way of reversing an integer.
 */
public class ReverseNumber {

    /**
     * The main method, which is the entry point for the program.
     */
    public static void main(String[] args) {
        
        // Define an integer to be reversed.
        int number = 12345;
        
        // Call the method to reverse the number.
        int reversedNumber = reverse(number);
        
        // Print the original and reversed numbers for comparison.
        System.out.println("Original number: " + number);
        System.out.println("Reversed number: " + reversedNumber); // Expected: 54321
        
        System.out.println("---");
        
        // Test with a negative number.
        int negativeNumber = -987;
        System.out.println("Original number: " + negativeNumber);
        System.out.println("Reversed number: " + reverse(negativeNumber)); // Expected: -789
    }
    
    /*
     * ==========================================================
     * --- Optimal Solution Details 🧠 ---
     * ==========================================================
     *
     * We use a loop to pop the last digit off the original number and push it
     * onto the end of our reversed number.
     *
     * Time Complexity: O(d) or O(log10(N))
     * The efficiency is excellent because the loop runs only once for each digit ('d')
     * in the input number 'N'.
     *
     * --- Visualization (Example with number = 123) --- 🔄
     *
     * Initial State: num = 123, reversed = 0
     *
     * 1️⃣ First Pass:
     * - Get last digit: digit = 123 % 10  -->  digit is 3.
     * - Build reversed: reversed = (0 * 10) + 3  -->  reversed is 3.
     * - Update num: num = 123 / 10      -->  num is 12.
     *
     * 2️⃣ Second Pass:
     * - Get last digit: digit = 12 % 10   -->  digit is 2.
     * - Build reversed: reversed = (3 * 10) + 2   -->  reversed is 32.
     * - Update num: num = 12 / 10       -->  num is 1.
     *
     * 3️⃣ Third Pass:
     * - Get last digit: digit = 1 % 10    -->  digit is 1.
     * - Build reversed: reversed = (32 * 10) + 1   -->  reversed is 321.
     * - Update num: num = 1 / 10        -->  num is 0.
     *
     * 4️⃣ Final Check:
     * - The condition `num != 0` (0 != 0) is false.
     * - The loop terminates, and the function returns 321.
     * ==========================================================
     */

    /**
     * Reverses the digits of an integer.
     */
    public static int reverse(int num) {
        
        // Use a 'long' type for the reversed number to temporarily hold values
        // that might be larger than Integer.MAX_VALUE, allowing us to detect overflow.
        long reversed = 0;

        // The loop continues as long as the number is not zero.
        // This works for both positive and negative numbers.
        while (num != 0) {
            
            // Get the last digit of the number using the modulo operator.
            // For -123, this will be -3, which is what we want.
            int digit = num % 10;
            
            // Append the digit to the reversed number.
            // We multiply the current reversed number by 10 to shift its digits to the left.
            reversed = reversed * 10 + digit;

            // Remove the last digit from the number.
            num = num / 10;
        }

        // After the loop, check if the reversed number has exceeded the 32-bit integer range.
        if (reversed > Integer.MAX_VALUE || reversed < Integer.MIN_VALUE) {
            // If it has, an overflow occurred. Return 0 as per convention.
            return 0;
        }

        // If there's no overflow, safely cast the long back to an int and return it.
        return (int) reversed;
    }
}