// A class named DigitExtractor for our program.
public class DigitExtractor {

    // This is the main function where the program execution begins.
    public static void main(String[] args) {
        
        // Define the number we want to extract digits from.
        int number = 7521;
        
        // Print the original number for context.
        System.out.println("Extracting digits from the number: " + number);
        
        // Call our function to perform the extraction.
        extract(number);
    }
    
    /*
     * ==========================================================
     * --- Optimal Solution Details ---
     * ==========================================================
     *
     * This method is the most efficient because it uses simple math and avoids converting
     * the number to a text string, which is a slower operation.
     *
     * Time Complexity: O(d), where 'd' is the number of digits.
     * The loop runs exactly once for each digit in the number.
     * For a number like 7521 (4 digits), the loop runs 4 times. This is extremely fast.
     * This complexity is also written as O(log10(N)), where N is the number itself.
     *
     * --- Visualization (Example with number = 752) ---
     *
     * 1. First Pass:
     * - Condition: 752 > 0 is true.
     * - Get Digit: digit = 752 % 10  -->  digit is 2.
     * - Update Number: num = 752 / 10  -->  num is now 75.
     *
     * 2. Second Pass:
     * - Condition: 75 > 0 is true.
     * - Get Digit: digit = 75 % 10   -->  digit is 5.
     * - Update Number: num = 75 / 10   -->  num is now 7.
     *
     * 3. Third Pass:
     * - Condition: 7 > 0 is true.
     * - Get Digit: digit = 7 % 10    -->  digit is 7.
     * - Update Number: num = 7 / 10    -->  num is now 0.
     *
     * 4. Final Check:
     * - Condition: 0 > 0 is false.
     * - The loop stops.
     *
     * The output will be the digits in reverse order: 2, 5, 7.
     * ==========================================================
     */
    
    // This function takes an integer and prints its digits.
    public static void extract(int num) {
        
        // First, handle the special case where the number is just 0.
        if (num == 0) {
            System.out.println("Digit: 0"); // If it's 0, just print 0.
            return; // And we're done, so exit the function.
        }

        // This loop will continue as long as the number is greater than 0.
        while (num > 0) {
            
            // Get the last digit of the number with the modulo operator.
            int digit = num % 10;
            
            // Print the digit we just extracted.
            System.out.println("Digit: " + digit);
            
            // Remove the last digit from the number by dividing it by 10.
            num = num / 10;
        }
    }
}