import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class to demonstrate different methods for printing all divisors of a number.
 */
public class DivisorPrinter {

    /**
     * The main method serves as a driver to test all the divisor-finding functions.
     */
    public static void main(String[] args) {
        // The number for which we want to find the divisors.
        int number = 36;
        
        System.out.println("Finding divisors for the number: " + number);
        
        // --- Call Method 1: Brute Force ---
        System.out.println("\n## Method 1: Brute Force Approach ##");
        printDivisorsBruteForce(number);
        
        // --- Call Method 2: Optimal (unsorted) ---
        System.out.println("\n\n## Method 2: Optimal Approach (Unsorted Output) ##");
        printDivisorsOptimal(number);
        
        // --- Call Method 3: Optimal (sorted) ---
        System.out.println("\n\n## Method 3: Optimal Approach (Sorted Output) ##");
        printDivisorsOptimalSorted(number);
    }

    // -------------------------------------------------------------------------
    //   Method 1: Brute Force Approach
    // -------------------------------------------------------------------------
    /*
     * Logic:
     * This is the most straightforward way. We simply loop through every number
     * from 1 up to the given number `n`. In each iteration, we check if the
     * current number `i` divides `n` perfectly (i.e., the remainder is 0).
     * If it does, we print it.
     *
     * Time Complexity: O(n)
     * The loop runs 'n' times, so the time taken is directly proportional to the
     * input number `n`. This becomes very slow for large numbers.
     *
     * Visualization (for n = 10):
     * i = 1:  10 % 1 == 0  ->  Print 1
     * i = 2:  10 % 2 == 0  ->  Print 2
     * i = 3:  10 % 3 != 0
     * i = 4:  10 % 4 != 0
     * i = 5:  10 % 5 == 0  ->  Print 5
     * i = 6:  10 % 6 != 0
     * i = 7:  10 % 7 != 0
     * i = 8:  10 % 8 != 0
     * i = 9:  10 % 9 != 0
     * i = 10: 10 % 10 == 0 ->  Print 10
     */
    public static void printDivisorsBruteForce(int n) {
        // Start a loop that goes from 1 all the way to the number 'n' itself.
        for (int i = 1; i <= n; i++) {
            // Check if 'i' is a divisor of 'n'.
            if (n % i == 0) {
                // If it is, print it, followed by a space.
                System.out.print(i + " ");
            }
        }
    }

    // -------------------------------------------------------------------------
    //   Method 2: Optimal Approach (Unsorted Output)
    // -------------------------------------------------------------------------
    /*
     * Logic:
     * Divisors always appear in pairs. For a number `n`, if `i` is a divisor,
     * then `n/i` is also a divisor. For example, for n=36, (2, 18) is a pair.
     * We only need to loop up to the square root of `n`. When we find a divisor `i`,
     * we can instantly find its pair `n/i`.
     *
     * Time Complexity: O(sqrt(n))
     * The loop only runs up to the square root of 'n', which is a massive
     * improvement over the brute-force O(n) approach.
     *
     * Visualization (for n = 36):
     * Loop runs from i = 1 to sqrt(36) = 6.
     * i = 1:  36 % 1 == 0  ->  Print 1 and its pair 36/1=36.
     * i = 2:  36 % 2 == 0  ->  Print 2 and its pair 36/2=18.
     * i = 3:  36 % 3 == 0  ->  Print 3 and its pair 36/3=12.
     * i = 4:  36 % 4 == 0  ->  Print 4 and its pair 36/4=9.
     * i = 5:  36 % 5 != 0
     * i = 6:  36 % 6 == 0  ->  This is a perfect square. The pair is 6 itself.
     * To avoid printing it twice, we only print `i`.
     */
    public static void printDivisorsOptimal(int n) {
        // Loop from 1 up to (and including) the square root of 'n'.
        for (int i = 1; i * i <= n; i++) {
            // Check if 'i' is a divisor.
            if (n % i == 0) {
                // If it is, print 'i'.
                System.out.print(i + " ");
                
                // Now, print its pair (n/i), but only if it's not the same as 'i'.
                // This condition prevents printing the square root twice for perfect squares.
                if (i != n / i) {
                    System.out.print(n / i + " ");
                }
            }
        }
    }
    
    // -------------------------------------------------------------------------
    //   Method 3: Optimal Approach (Sorted Output)
    // -------------------------------------------------------------------------
    /*
     * Logic:
     * This is a variation of the optimal approach that prints the divisors in
     * sorted order. We use a list to store the larger divisor of each pair (`n/i`).
     * First, we loop from 1 to sqrt(n) and print/store the divisors. After the
     * loop, we print the stored divisors in reverse order.
     */
    public static void printDivisorsOptimalSorted(int n) {
        // Use a List to store the second half of the divisor pairs.
        List<Integer> secondHalf = new ArrayList<>();
        
        // Loop from 1 up to the square root of 'n'.
        for (int i = 1; i * i <= n; i++) {
            // If 'i' is a divisor...
            if (n % i == 0) {
                // ...print 'i' immediately, as it's part of the first (smaller) half.
                System.out.print(i + " ");
                
                // If its pair (n/i) is different, add it to our list for later.
                if (i != n / i) {
                    secondHalf.add(n / i);
                }
            }
        }
        
        // Now, print the second half of divisors in reverse order to maintain sorted output.
        for (int i = secondHalf.size() - 1; i >= 0; i--) {
            System.out.print(secondHalf.get(i) + " ");
        }
    }
}