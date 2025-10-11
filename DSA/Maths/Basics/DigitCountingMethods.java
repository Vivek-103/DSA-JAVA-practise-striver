/**
 * A class demonstrating two different methods for counting digits in an integer.
 */
public class DigitCountingMethods {

    /**
     * The main method serves as a driver to test both counting functions.
     */
    public static void main(String[] args) {
        // Define a number to test with.
        int numberToTest = 78123;
        
        System.out.println("Testing with the number: " + numberToTest);
        System.out.println("----------------------------------------");

        // --- Call Method 1: Iterative (Looping) ---
        int countFromLoop = countWithLoop(numberToTest);
        System.out.println("✅ Result using Loop:      " + countFromLoop);

        // --- Call Method 2: Logarithmic ---
        int countFromLog = countWithLog(numberToTest);
        System.out.println("🧠 Result using Logarithm: " + countFromLog);
        
        // --- Testing an edge case: 0 ---
        System.out.println("\nTesting with the number: 0");
        System.out.println("----------------------------------------");
        System.out.println("✅ Result using Loop:      " + countWithLoop(0));
        System.out.println("🧠 Result using Logarithm: " + countWithLog(0));
    }

    // -------------------------------------------------------------------------
    //   Method 1: Iterative (Looping) Approach
    // -------------------------------------------------------------------------
    /**
     * Counts digits by repeatedly dividing the number by 10.
     * This method is highly reliable and recommended for general use.
     *
     * Time Complexity: O(d), where 'd' is the number of digits.
     */
    public static int countWithLoop(int num) {
        // The number 0 has exactly one digit.
        if (num == 0) {
            return 1;
        }

        // Initialize a counter for the digits.
        int count = 0;
        
        // Use the absolute value to correctly handle negative numbers.
        num = Math.abs(num);

        // Loop until the number is reduced to 0.
        while (num > 0) {
            // Integer division by 10 removes the last digit.
            num = num / 10;
            // Increment the counter for each digit removed.
            count++;
        }
        
        return count;
    }

    // -------------------------------------------------------------------------
    //   Method 2: Logarithmic Approach
    // -------------------------------------------------------------------------
    /**
     * Counts digits using the mathematical formula: floor(log10(n)) + 1.
     * This method is faster in theory but requires careful handling of edge cases.
     *
     * Time Complexity: O(1).
     */
    public static int countWithLog(int num) {
        // Logarithm is undefined for 0, so this case must be handled separately.
        if (num == 0) {
            return 1;
        }
        
        // Use the absolute value because log10 is undefined for negative numbers.
        num = Math.abs(num);
        
        // Apply the logarithmic formula and cast the result to an integer.
        return (int) (Math.log10(num) + 1);
    }
}