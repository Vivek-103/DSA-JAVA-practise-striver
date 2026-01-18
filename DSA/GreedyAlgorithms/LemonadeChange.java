import java.util.*;
// Imports utility classes (Map, HashMap, etc.)
// Time Complexity: O(1)
// Space Complexity: O(1)

/**
 * Class: LemonadeChange
 * Problem: Determine if correct change can be given to every customer
 */
public class LemonadeChange {

    // ==========================================
    // 1️⃣ MOST OPTIMAL APPROACH (GREEDY)
    // ==========================================

    /**
     * Greedy solution
     * Always prioritize giving larger change first
     */
    public static boolean lemonadeChangeGreedy(int[] bills) {

        // Count of $5 bills we currently have
        // Space Complexity: O(1)
        int five = 0;

        // Count of $10 bills we currently have
        // Space Complexity: O(1)
        int ten = 0;

        // Traverse through each customer
        // Time Complexity: O(N)
        for (int bill : bills) {

            // If customer pays with $5
            if (bill == 5) {
                // No change required, store the $5
                five++;
                // Time Complexity: O(1)
            }

            // If customer pays with $10
            else if (bill == 10) {

                // We must give back $5
                if (five == 0) {
                    // No $5 to give change
                    return false;
                }

                // Give $5 as change
                five--;

                // Store $10 bill
                ten++;
            }

            // If customer pays with $20
            else {

                // Best case: give $10 + $5
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                }
                // Second option: give three $5 bills
                else if (five >= 3) {
                    five -= 3;
                }
                // No valid change possible
                else {
                    return false;
                }
            }
        }

        // If all customers got correct change
        return true;
    }

    // ==========================================
    // 2️⃣ BETTER APPROACH (MAP SIMULATION)
    // ==========================================

    /**
     * Uses HashMap to simulate cash register
     */
    public static boolean lemonadeChangeMap(int[] bills) {

        // Map to store bill counts
        // Space Complexity: O(1) (only 3 keys max)
        Map<Integer, Integer> cash = new HashMap<>();

        // Initialize all bills with 0 count
        cash.put(5, 0);
        cash.put(10, 0);
        cash.put(20, 0);

        // Traverse customers
        // Time Complexity: O(N)
        for (int bill : bills) {

            // Add received bill
            cash.put(bill, cash.get(bill) + 1);

            // Change needed
            int change = bill - 5;

            // Try to give change using available bills
            while (change > 0) {

                // Prefer larger bills first
                if (change >= 10 && cash.get(10) > 0) {
                    change -= 10;
                    cash.put(10, cash.get(10) - 1);
                }
                else if (change >= 5 && cash.get(5) > 0) {
                    change -= 5;
                    cash.put(5, cash.get(5) - 1);
                }
                else {
                    // Cannot provide change
                    return false;
                }
            }
        }

        return true;
    }

    // ==========================================
    // 3️⃣ BRUTE FORCE APPROACH (BACKTRACKING)
    // ==========================================

    /**
     * Brute force recursive solution
     * NOT suitable for large inputs
     */
    public static boolean lemonadeChangeBrute(
            int[] bills,
            int index,
            int five,
            int ten
    ) {

        // Base case: all customers processed
        if (index == bills.length) {
            return true;
        }

        int bill = bills[index];

        // If customer pays with $5
        if (bill == 5) {
            return lemonadeChangeBrute(bills, index + 1, five + 1, ten);
        }

        // If customer pays with $10
        if (bill == 10) {
            if (five == 0) return false;
            return lemonadeChangeBrute(bills, index + 1, five - 1, ten + 1);
        }

        // If customer pays with $20
        // Try both possibilities
        boolean option1 = false;
        boolean option2 = false;

        // Option 1: $10 + $5
        if (ten > 0 && five > 0) {
            option1 = lemonadeChangeBrute(
                    bills, index + 1, five - 1, ten - 1
            );
        }

        // Option 2: three $5
        if (five >= 3) {
            option2 = lemonadeChangeBrute(
                    bills, index + 1, five - 3, ten
            );
        }

        return option1 || option2;
    }

    // ==========================================
    // MAIN METHOD
    // ==========================================

    public static void main(String[] args) {

        // Test input
        int[] bills = {5, 5, 5, 10, 20};

        // Greedy approach (BEST)
        System.out.println(
                "Greedy Result: " + lemonadeChangeGreedy(bills)
        );
        // Time Complexity: O(N)
        // Space Complexity: O(1)

        // Map based approach
        System.out.println(
                "Map Approach Result: " + lemonadeChangeMap(bills)
        );
        // Time Complexity: O(N)
        // Space Complexity: O(1)

        // Brute force approach (small input only)
        System.out.println(
                "Brute Force Result: " +
                lemonadeChangeBrute(bills, 0, 0, 0)
        );
        // Time Complexity: Exponential
        // Space Complexity: O(N) recursion stack
    }
}
