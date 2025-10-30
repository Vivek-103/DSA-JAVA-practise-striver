// File Name: MaxPointsFromCards.java
// Problem: Maximum points you can obtain from cards
// Language: Java

/* 
---------------------------------------------
🧩 PROBLEM UNDERSTANDING:
---------------------------------------------
We are given an array cardPoints[], where each element represents points on a card.
We can take exactly k cards either from the beginning or from the end of the array.

Goal: Maximize the sum of the k cards we take.

Example:
cardPoints = [1,2,3,4,5,6,1], k = 3
Choices:
- Take 3 from front => [1,2,3] = 6
- Take 3 from end => [6,1,?] = 7
- Or mix => [1,6,1] = 8 (best)
✅ Output: 12 (4 + 6 + 2)

---------------------------------------------
💡 VISUALIZATION (Sliding Window Approach):
---------------------------------------------
We can reframe the problem:
Instead of finding the max sum of k cards taken from both ends,
find the *minimum sum* of the subarray of length (n - k) that we are NOT taking.

Example:
cardPoints = [1,2,3,4,5,6,1], k = 3
n = 7
Subarray length we skip = n - k = 4

Total sum = 22
We find min sum subarray of length 4:
- [1,2,3,4] = 10
- [2,3,4,5] = 14
- [3,4,5,6] = 18
- [4,5,6,1] = 16
Min sum = 10
Max points = totalSum - minSum = 22 - 10 = 12 ✅

---------------------------------------------
⚙️ ALGORITHM:
---------------------------------------------
1️⃣ Compute total sum of all card points.
2️⃣ Use a sliding window of size (n - k) to find the subarray with the minimum sum.
3️⃣ Subtract that minimum sum from the total sum to get the maximum obtainable points.

---------------------------------------------
⏱️ TIME & SPACE COMPLEXITY:
---------------------------------------------
Time Complexity: O(n)  → Single pass for sum + sliding window
Space Complexity: O(1) → Constant extra space
---------------------------------------------
*/

import java.util.*;

public class MaxPointsFromCards {

    // Method to calculate the maximum score from cards
    public static int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;

        // Total sum of all elements
        int totalSum = 0;
        for (int num : cardPoints) {
            totalSum += num;
        }

        // If we take all cards, return total sum
        if (k == n) return totalSum;

        int windowSize = n - k; // Number of cards we won't take
        int currentWindowSum = 0;

        // Step 1: Initialize window with first 'windowSize' elements
        for (int i = 0; i < windowSize; i++) {
            currentWindowSum += cardPoints[i];
        }

        int minWindowSum = currentWindowSum;

        // Step 2: Slide the window across the array
        for (int i = windowSize; i < n; i++) {
            currentWindowSum += cardPoints[i] - cardPoints[i - windowSize];
            minWindowSum = Math.min(minWindowSum, currentWindowSum);
        }

        // Step 3: Result = total sum - minimum window sum
        return totalSum - minWindowSum;
    }

    // 🔍 Visualization Helper Function
    private static void visualizeProcess(int[] cardPoints, int k) {
        System.out.println("\n🧮 Visualization of Sliding Window Steps:");
        int n = cardPoints.length;
        int windowSize = n - k;
        int totalSum = Arrays.stream(cardPoints).sum();
        System.out.println("Total sum of cards = " + totalSum);
        System.out.println("Window size (to skip) = " + windowSize);

        int currentSum = 0;
        for (int i = 0; i < windowSize; i++) {
            currentSum += cardPoints[i];
        }
        System.out.println("Initial window sum = " + currentSum + " → " + Arrays.toString(Arrays.copyOfRange(cardPoints, 0, windowSize)));

        int minSum = currentSum;
        for (int i = windowSize; i < n; i++) {
            currentSum += cardPoints[i] - cardPoints[i - windowSize];
            minSum = Math.min(minSum, currentSum);
            System.out.println("Window " + (i - windowSize + 1) + " to " + i + " → " +
                Arrays.toString(Arrays.copyOfRange(cardPoints, i - windowSize + 1, i + 1)) + " → sum = " + currentSum);
        }

        System.out.println("Minimum subarray sum = " + minSum);
        System.out.println("Maximum obtainable points = " + (totalSum - minSum));
    }

    // 🧠 MAIN METHOD - Driver Code
    public static void main(String[] args) {
        int[] cardPoints = {1, 2, 3, 4, 5, 6, 1};
        int k = 3;

        // Print input
        System.out.println("Input Card Points: " + Arrays.toString(cardPoints));
        System.out.println("Number of cards to take (k): " + k);

        // Compute result
        int result = maxScore(cardPoints, k);
        System.out.println("\n✅ Maximum Points You Can Obtain = " + result);

        // Visualize the process
        visualizeProcess(cardPoints, k);
    }
}

/*
---------------------------------------------
✅ SAMPLE OUTPUT:
---------------------------------------------
Input Card Points: [1, 2, 3, 4, 5, 6, 1]
Number of cards to take (k): 3

✅ Maximum Points You Can Obtain = 12

🧮 Visualization of Sliding Window Steps:
Total sum of cards = 22
Window size (to skip) = 4
Initial window sum = 10 → [1, 2, 3, 4]
Window 1 to 4 → [2, 3, 4, 5] → sum = 14
Window 2 to 5 → [3, 4, 5, 6] → sum = 18
Window 3 to 6 → [4, 5, 6, 1] → sum = 16
Minimum subarray sum = 10
Maximum obtainable points = 12
---------------------------------------------
*/
