/**
 * A class to check if a given number is an Armstrong number.
 */
public class ArmstrongNumberCheck {

    /**
     * The main method, which is the entry point to test the function.
     */
    public static void main(String[] args) {
        
        // Test case 1: A 3-digit Armstrong number.
        int num1 = 153;
        System.out.println("Is " + num1 + " an Armstrong number? " + isArmstrong(num1)); // Expected: true

        // Test case 2: A 4-digit Armstrong number.
        int num2 = 1634;
        System.out.println("Is " + num2 + " an Armstrong number? " + isArmstrong(num2)); // Expected: true

        // Test case 3: A non-Armstrong number.
        int num3 = 123;
        System.out.println("Is " + num3 + " an Armstrong number? " + isArmstrong(num3)); // Expected: false
    }
    
    /*
     * ==========================================================
     * --- Optimal Solution Details 🧠 ---
     * ==========================================================
     *
     * The solution requires two passes over the number's digits:
     * 1. First Pass: Count the total number of digits. This tells us the power to use.
     * 2. Second Pass: Extract each digit, raise it to that power, and add it to a running sum.
     * Finally, we compare the sum with the original number.
     *
     * Time Complexity: O(d) or O(log10(N))
     * The complexity is determined by the number of digits ('d') in the input number 'N'.
     * Since we perform two separate loops over the digits, the complexity is O(d) + O(d),
     * which simplifies to O(d). This is very efficient.
     *
     * --- Visualization (Example with number = 153) --- ✨
     *
     * Part 1: Count the digits
     * ------------------------
     * - We start with a temporary copy of the number (153) and a digit count of 0.
     * - Loop 1: Number becomes 15, count becomes 1.
     * - Loop 2: Number becomes 1, count becomes 2.
     * - Loop 3: Number becomes 0, count becomes 3.
     * - The total number of digits is 3.
     *
     * Part 2: Calculate the sum of powers
     * ------------------------------------
     * - The power to use is 3. We reset our temporary number to 153 and sum to 0.
     *
     * - Loop 1 (for digit 3):
     * - Extract last digit: 153 % 10  ->  3.
     * - Calculate power: 3^3 = 27.
     * - Update sum: sum = 0 + 27  ->  27.
     * - Update number: 153 / 10  ->  15.
     *
     * - Loop 2 (for digit 5):
     * - Extract last digit: 15 % 10  ->  5.
     * - Calculate power: 5^3 = 125.
     * - Update sum: sum = 27 + 125  ->  152.
     * - Update number: 15 / 10  ->  1.
     *
     * - Loop 3 (for digit 1):
     * - Extract last digit: 1 % 10  ->  1.
     * - Calculate power: 1^3 = 1.
     * - Update sum: sum = 152 + 1  ->  153.
     * - Update number: 1 / 10  ->  0.
     *
     * Part 3: Final Comparison
     * ------------------------
     * - Is the final sum (153) equal to the original number (153)? ✅ Yes.
     * - Return true.
     * ==========================================================
     */

    /**
     * Checks if a given integer is an Armstrong number.
     * @return true if the number is an Armstrong number, false otherwise.
     */
    public static boolean isArmstrong(int num) {
        // Armstrong numbers are generally defined for non-negative integers.
        if (num < 0) {
            return false;
        }
        
        // We need a copy of the original number for the final comparison.
        int originalNumber = num;
        
        // --- Step 1: Count the number of digits ---
        int numberOfDigits = 0;
        int temp = num;
        // The number 0 is sometimes considered a special case. Here, 0^1 = 0.
        if (temp == 0) {
            return true;
        }
        // This loop counts the digits.
        while (temp != 0) {
            numberOfDigits++; // Increment the counter.
            temp /= 10;       // Remove the last digit.
        }

        // --- Step 2: Calculate the sum of powers of digits ---
        int sum = 0;
        temp = originalNumber; // Reset temp to the original number for the second pass.
        
        // This loop extracts each digit and calculates its contribution to the sum.
        while (temp != 0) {
            // Get the last digit.
            int digit = temp % 10;
            
            // Add the digit raised to the power of 'numberOfDigits' to the sum.
            // Math.pow returns a double, so we cast it to an integer.
            sum += Math.pow(digit, numberOfDigits);
            
            // Remove the last digit from the number.
            temp /= 10;
        }

        // --- Step 3: Compare the sum with the original number ---
        return sum == originalNumber;
    }
}