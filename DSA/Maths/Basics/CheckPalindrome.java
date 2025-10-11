/**
 * A class to check if an integer is a palindrome.
 */
public class CheckPalindrome {

    /**
     * The main method, which serves as the entry point to test the function.
     */
    public static void main(String[] args) {
        
        // Test case 1: A palindrome number.
        int num1 = 121;
        System.out.println("Is " + num1 + " a palindrome? " + isPalindrome(num1)); // Expected: true

        // Test case 2: A non-palindrome number.
        int num2 = 123;
        System.out.println("Is " + num2 + " a palindrome? " + isPalindrome(num2)); // Expected: false
        
        // Test case 3: A negative number (not a palindrome).
        int num3 = -121;
        System.out.println("Is " + num3 + " a palindrome? " + isPalindrome(num3)); // Expected: false
        
        // Test case 4: A number ending in 0 (not a palindrome).
        int num4 = 120;
        System.out.println("Is " + num4 + " a palindrome? " + isPalindrome(num4)); // Expected: false
    }
    
    /*
     * ==========================================================
     * --- Optimal Solution Details 🧠 ---
     * ==========================================================
     *
     * We create a reversed version of the number using a loop and simple math.
     * Then, we just compare the original number to its reversed version.
     *
     * Time Complexity: O(d) or O(log10(N))
     * The code runs through the loop once for each digit ('d') in the number 'N'.
     * This makes the solution very fast and efficient.
     *
     * --- Visualization (Example with number = 121) --- 🔄
     *
     * 1. Initial Checks:
     * - Is `num` (121) negative? No.
     * - Does `num` end in 0 (and is not 0)? No.
     * - Proceed.
     *
     * 2. Reversing the number (inside the loop):
     * - We keep track of the original `num` in a variable called `tempNum`.
     * - Initial State: tempNum = 121, reversed = 0
     *
     * - Pass 1: digit=1, reversed=1, tempNum=12
     * - Pass 2: digit=2, reversed=12, tempNum=1
     * - Pass 3: digit=1, reversed=121, tempNum=0
     * - Loop ends.
     *
     * 3. Final Comparison:
     * - Is the original `num` (121) equal to `reversed` (121)? ✅ Yes.
     * - Return true.
     * ==========================================================
     */

    /**
     * Checks if a given integer is a palindrome.
     * @return true if the number is a palindrome, false otherwise.
     */
    public static boolean isPalindrome(int num) {
        
        // Edge Case Check:
        // 1. A negative number can't be a palindrome (e.g., -121 reads differently).
        // 2. If a number ends in 0 (and isn't 0 itself), it can't be a palindrome
        //    because its reverse won't end in 0 (e.g., 120 reversed is 21).
        if (num < 0 || (num % 10 == 0 && num != 0)) {
            return false;
        }

        // This variable will hold the reversed version of the number.
        int reversed = 0;
        
        // We use a temporary variable so we don't destroy the original `num` for the final comparison.
        int tempNum = num;
        
        // This loop runs as long as there are still digits left in tempNum.
        while (tempNum != 0) {
            
            // Get the last digit of the number using the modulo operator.
            int digit = tempNum % 10;
            
            // Append this digit to our 'reversed' number.
            reversed = reversed * 10 + digit;
            
            // Remove the last digit from tempNum.
            tempNum = tempNum / 10;
        }

        // Return the result of comparing the original number with its reversed version.
        // This will be 'true' if they are the same, and 'false' otherwise.
        return num == reversed;
    }
}