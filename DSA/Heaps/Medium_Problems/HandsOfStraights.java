/**
 * ============================================================================
 * 📌 HANDS OF STRAIGHTS — JAVA IMPLEMENTATION (LEETCODE 846)
 * ============================================================================
 *
 * PROBLEM SUMMARY:
 * ----------------
 * We are given an array `hand[]`, representing card values.
 * We must group ALL cards into groups of size `groupSize`.
 *
 * Each group must consist of consecutive integers.
 *
 * Example:
 * hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
 *
 * Groups can be:
 *   [1,2,3], [2,3,4], [6,7,8]
 *
 * If this is possible → return true
 * Otherwise → return false
 *
 * ============================================================================
 * APPROACH (GREEDY + MIN HEAP + HASHMAP)
 * ============================================================================
 *
 * WHY GREEDY?
 * -----------
 * Whenever we form a group, we should start with the SMALLEST card available.
 * Because small numbers restrict future choices more tightly.
 *
 * DATA STRUCTURES USED:
 * ---------------------
 * ✔ HashMap<Integer, Integer>   → to count occurrences of each card.
 * ✔ PriorityQueue<Integer>      → min-heap, so we always pick smallest card.
 *
 * ALGORITHM:
 * ----------
 * 1️⃣ Build frequency map
 * 2️⃣ Put unique elements into a min-heap
 * 3️⃣ While heap is not empty:
 *      a) extract smallest number → start of a new group
 *      b) try to build groupSize consecutive cards
 *      c) decrement counts in map
 *      d) if count becomes 0 → remove from heap
 * 4️⃣ If all groups valid → return true
 *
 * ============================================================================
 * TIME & SPACE COMPLEXITY
 * ============================================================================
 *
 * Let N = number of cards
 * Let G = groupSize
 *
 * Building frequency map: O(N)
 * Building heap: O(N log N)
 * Removing cards: O(N log N)
 *
 * OVERALL TIME COMPLEXITY:   O(N log N)
 *
 * SPACE COMPLEXITY:
 *   HashMap → O(N)
 *   Heap → O(N)
 * OVERALL SPACE: O(N)
 *
 * ============================================================================
 */

import java.util.*;

public class HandsOfStraights {

    /**
     * Method checks if cards can be rearranged into groups
     * of size groupSize forming consecutive sequences.
     *
     * @param hand      the list of card values
     * @param groupSize desired group size
     * @return boolean  true if grouping possible, else false
     *
     * TIME COMPLEXITY  : O(N log N)
     * SPACE COMPLEXITY : O(N)
     */
    public static boolean isNStraightHand(int[] hand, int groupSize) {

        // Edge case:
        // if hand size not divisible by groupSize → cannot split evenly
        if (hand.length % groupSize != 0) {
            return false;
        }

        // STEP 1: Count frequency of each card
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int card : hand) {
            freq.put(card, freq.getOrDefault(card, 0) + 1);
        }

        // STEP 2: Min Heap for selecting smallest available card
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(freq.keySet());

        // STEP 3: Try forming groups
        while (!minHeap.isEmpty()) {

            int firstCard = minHeap.peek(); // smallest card available

            // Try to build groupSize consecutive cards
            for (int i = 0; i < groupSize; i++) {

                int currentCard = firstCard + i;

                if (!freq.containsKey(currentCard)) {
                    return false; // missing card in sequence
                }

                // decrement its frequency
                freq.put(currentCard, freq.get(currentCard) - 1);

                // remove card entirely if count hits zero
                if (freq.get(currentCard) == 0) {
                    freq.remove(currentCard);

                    // remove from heap → but only if it's the smallest
                    if (minHeap.peek() == currentCard) {
                        minHeap.poll();
                    } else {
                        // cannot remove this from heap without violating order
                        return false;
                    }
                }
            }
        }

        // if entire hand processed successfully
        return true;
    }

    /**
     * MAIN METHOD — To test different cases
     */
    public static void main(String[] args) {

        System.out.println("=== HANDS OF STRAIGHTS DEMO ===");

        int[] hand1 = {1,2,3,6,2,3,4,7,8};
        int groupSize1 = 3;
        System.out.println("Input: [1,2,3,6,2,3,4,7,8], groupSize = 3");
        System.out.println("Output: " + isNStraightHand(hand1, groupSize1));
        System.out.println();

        int[] hand2 = {1,2,3,4,5};
        int groupSize2 = 4;
        System.out.println("Input: [1,2,3,4,5], groupSize = 4");
        System.out.println("Output: " + isNStraightHand(hand2, groupSize2));
        System.out.println();

        int[] hand3 = {8,10,12};
        int groupSize3 = 3;
        System.out.println("Input: [8,10,12], groupSize = 3");
        System.out.println("Output: " + isNStraightHand(hand3, groupSize3));
    }
}
