// File: LongestRepeatingCharacterReplacement.java 
// -----------------------------------------------------------------------------
// Problem: Given an integer k and a string s consisting of uppercase letters,
// you can replace up to k characters in s so that the resulting string contains
// a substring of the same letter. Return the length of the longest such substring.
// -----------------------------------------------------------------------------
// Example:
// Input: s = "BAABAABBBAAA", k = 2
// Output: 6
// Explanation:
// We can change 'B' at index 0 and 3 -> "AAAAAABBBAAA" -> longest "AAAAAA" = 6
// -----------------------------------------------------------------------------
// Topic: Sliding Window / Frequency Counting
// -----------------------------------------------------------------------------


import java.util.*;

public class LongestRepeatingCharacterReplacement {

    // -------------------------------------------------------------------------
    // BRUTE FORCE APPROACH
    // -------------------------------------------------------------------------
    // Idea:
    // For each substring, check if we can make all characters same using <= k changes.
    // Steps:
    // - For every (i, j), compute max frequency of a char.
    // - If (window_length - max_frequency <= k), it’s valid.
    // Keep track of max window size satisfying this.
    //
    // Time Complexity: O(n^2 * 26) -> O(26 * n^2)
    // Space Complexity: O(1)
    // -------------------------------------------------------------------------
    public static int characterReplacementBruteForce(String s, int k) {
        int maxLength = 0;

        for (int left = 0; left < s.length(); left++) {
            int[] freq = new int[26]; // frequency of each letter
            int maxFreq = 0;

            for (int right = left; right < s.length(); right++) {
                freq[s.charAt(right) - 'A']++;
                maxFreq = Math.max(maxFreq, freq[s.charAt(right) - 'A']);

                // total chars to change = windowSize - maxFreq
                int windowSize = right - left + 1;
                if (windowSize - maxFreq <= k) {
                    maxLength = Math.max(maxLength, windowSize);
                }
            }
        }

        return maxLength;
    }

    // -------------------------------------------------------------------------
    // OPTIMAL APPROACH (SLIDING WINDOW)
    // -------------------------------------------------------------------------
    // Idea:
    // - Use sliding window to find longest substring satisfying:
    //   (window length - maxFreq <= k)
    // - Keep expanding the right pointer.
    // - Track frequency of each character in the window.
    // - If window becomes invalid, shrink it from left.
    //
    // Visualization Example:
    // s = "AABABBA", k = 1
    //
    // Step-by-step:
    //   right=0: "A" -> freq[A]=1, maxFreq=1, valid (1-1<=1)
    //   right=1: "AA" -> freq[A]=2, maxFreq=2, valid (2-2<=1)
    //   right=2: "AAB" -> freq[B]=1, maxFreq=2, valid (3-2<=1)
    //   right=3: "AABA" -> freq[A]=3, valid (4-3<=1)
    //   right=4: "AABAB" -> freq[B]=2, invalid (5-3>1) -> shrink left
    //   window="ABAB" -> valid (4-2<=1)
    //   Max window length = 4 ✅
    //
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    // -------------------------------------------------------------------------
    public static int characterReplacementOptimal(String s, int k) {
        int[] freq = new int[26]; // frequency of letters in window
        int left = 0;
        int maxFreq = 0; // maximum frequency of a single char in current window
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            freq[c - 'A']++;
            maxFreq = Math.max(maxFreq, freq[c - 'A']);

            int windowSize = right - left + 1;

            // If more than k replacements needed, shrink window
            if (windowSize - maxFreq > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }

            // Update max length
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    // -------------------------------------------------------------------------
    // VISUALIZATION IN COMMENTS:
    // Input: s = "BAABAABBBAAA", k = 2
    //
    // Window Expansion:
    //  right=0  "B" -> freq[B]=1, maxFreq=1 -> valid (1-1<=2)
    //  right=1  "BA" -> freq[A]=1, freq[B]=1 -> valid (2-1<=2)
    //  right=2  "BAA" -> freq[A]=2, freq[B]=1 -> valid (3-2<=2)
    //  right=3  "BAAB" -> freq[A]=2, freq[B]=2 -> valid (4-2<=2)
    //  right=4  "BAABA" -> freq[A]=3, freq[B]=2 -> valid (5-3<=2)
    //  right=5  "BAABAA" -> freq[A]=4, freq[B]=2 -> valid (6-4<=2) ✅ max=6
    //  ...
    // Final Answer = 6
    // -------------------------------------------------------------------------

    // -------------------------------------------------------------------------
    // MAIN METHOD FOR TESTING BOTH APPROACHES
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        String s1 = "BAABAABBBAAA";
        int k1 = 2;
        String s2 = "AABABBA";
        int k2 = 1;
        String s3 = "ABAB"; // Simple edge test

        System.out.println("========== BRUTE FORCE APPROACH ==========");
        System.out.println("Input: " + s1 + ", k=" + k1 + " -> Output: " + characterReplacementBruteForce(s1, k1));
        System.out.println("Input: " + s2 + ", k=" + k2 + " -> Output: " + characterReplacementBruteForce(s2, k2));
        System.out.println("Input: " + s3 + ", k=" + k2 + " -> Output: " + characterReplacementBruteForce(s3, k2));

        System.out.println("\n========== OPTIMAL SLIDING WINDOW ==========");
        System.out.println("Input: " + s1 + ", k=" + k1 + " -> Output: " + characterReplacementOptimal(s1, k1));
        System.out.println("Input: " + s2 + ", k=" + k2 + " -> Output: " + characterReplacementOptimal(s2, k2));
        System.out.println("Input: " + s3 + ", k=" + k2 + " -> Output: " + characterReplacementOptimal(s3, k2));
    }
}
