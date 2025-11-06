// Description: Solves the "Fruit Into Baskets" problem using both brute-force and optimal approaches
// Problem Type: Sliding Window / HashMap
// -------------------------------------------------------------------------
// Problem Summary:
// Given an array 'fruits', each element represents a type of fruit from a tree in a row.
// You have 2 baskets, each can only hold 1 type of fruit but unlimited quantity.
// Starting from any tree, pick one fruit from each tree moving right,
// until you encounter a fruit that doesn’t fit in the two baskets.
// Return the maximum number of fruits that can be collected.
// -------------------------------------------------------------------------
//
// Example:
// Input: fruits = [1, 2, 3, 2, 2]
// Output: 4
// Explanation:
//  - Start at tree index 1 (fruit type 2).
//  - Collect: [2, 3, 2, 2] => 4 fruits total.

import java.util.*;

public class FruitIntoBaskets {

    // ----------------------------------------------------------
    // BRUTE FORCE APPROACH
    // ----------------------------------------------------------
    // Idea:
    // For every starting index, try to move right and count how many fruits we can collect
    // before we encounter more than 2 different fruit types.
    // ----------------------------------------------------------
    // Time Complexity: O(n^2)
    // Space Complexity: O(n) due to using a set for distinct fruit types.
    // ----------------------------------------------------------
    public static int totalFruitBruteForce(int[] fruits) {
        int maxFruits = 0;

        for (int start = 0; start < fruits.length; start++) {
            Set<Integer> basket = new HashSet<>();
            int count = 0;

            for (int end = start; end < fruits.length; end++) {
                basket.add(fruits[end]);
                // If more than 2 types, we can’t pick further.
                if (basket.size() > 2) break;
                count++;
            }

            maxFruits = Math.max(maxFruits, count);
        }

        return maxFruits;
    }

    // ----------------------------------------------------------
    // OPTIMAL APPROACH (SLIDING WINDOW)
    // ----------------------------------------------------------
    // Idea:
    // Use two pointers (left and right) to maintain a window
    // that contains at most 2 distinct fruit types.
    //
    // Move 'right' pointer to include more fruits,
    // and when we exceed 2 types, move 'left' to shrink window.
    //
    // We use a HashMap<fruitType, count> to track fruit frequencies in the window.
    // ----------------------------------------------------------
    // Time Complexity: O(n)
    // Space Complexity: O(1) (since max 2-3 fruit types in map)
    // ----------------------------------------------------------
    public static int totalFruitOptimal(int[] fruits) {
        Map<Integer, Integer> basketMap = new HashMap<>();
        int left = 0, maxFruits = 0;

        // Move right pointer over the array
        for (int right = 0; right < fruits.length; right++) {
            int fruit = fruits[right];
            basketMap.put(fruit, basketMap.getOrDefault(fruit, 0) + 1);

            // If we have more than 2 fruit types, shrink window from left
            while (basketMap.size() > 2) {
                int leftFruit = fruits[left];
                basketMap.put(leftFruit, basketMap.get(leftFruit) - 1);

                // Remove the fruit from map if count drops to 0
                if (basketMap.get(leftFruit) == 0)
                    basketMap.remove(leftFruit);

                left++;
            }

            // Update max window size (valid number of fruits)
            maxFruits = Math.max(maxFruits, right - left + 1);
        }

        return maxFruits;
    }

    // ----------------------------------------------------------
    // VISUALIZATION (For better understanding)
    // ----------------------------------------------------------
    /*
        Example: fruits = [1, 2, 3, 2, 2]
        -----------------------------------
        right=0 -> [1] -> map={1=1} -> max=1
        right=1 -> [1,2] -> map={1=1,2=1} -> max=2
        right=2 -> [1,2,3] -> map={1=1,2=1,3=1} -> size>2 -> shrink left
                    -> remove 1 -> map={2=1,3=1} -> window=[2,3]
        right=3 -> [2,3,2] -> map={2=2,3=1} -> max=3
        right=4 -> [2,3,2,2] -> map={2=3,3=1} -> max=4 ✅
        Final maxFruits = 4
    */

    // ----------------------------------------------------------
    // MAIN METHOD for testing both approaches
    // ----------------------------------------------------------
    public static void main(String[] args) {
        int[] fruits1 = {1, 2, 1};
        int[] fruits2 = {1, 2, 3, 2, 2};
        int[] fruits3 = {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4}; // Extra test

        System.out.println("========== BRUTE FORCE APPROACH ==========");
        System.out.println("Input: [1, 2, 1] -> Output: " + totalFruitBruteForce(fruits1)); // 3
        System.out.println("Input: [1, 2, 3, 2, 2] -> Output: " + totalFruitBruteForce(fruits2)); // 4
        System.out.println("Input: [3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4] -> Output: " + totalFruitBruteForce(fruits3)); // 5

        System.out.println("\n========== OPTIMAL (SLIDING WINDOW) ==========");
        System.out.println("Input: [1, 2, 1] -> Output: " + totalFruitOptimal(fruits1)); // 3
        System.out.println("Input: [1, 2, 3, 2, 2] -> Output: " + totalFruitOptimal(fruits2)); // 4
        System.out.println("Input: [3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4] -> Output: " + totalFruitOptimal(fruits3)); // 5
    }
}
