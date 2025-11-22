//*************************************************************
// LONGEST SUBSTRING WITH AT MOST K DISTINCT CHARACTERS
// Approaches Covered: Brute Force, Better, Optimal
// Each line has comments for teaching.
//*************************************************************

import java.util.*;

public class LongestSubstringKDistinct {

    // =========================================================
    // MAIN METHOD (TESTING ALL APPROACHES)
    // =========================================================
    public static void main(String[] args) {
        String s = "eceba";
        int k = 2;

        System.out.println("Brute Force: " + longestBruteForce(s, k));
        System.out.println("Better Approach: " + longestBetter(s, k));
        System.out.println("Optimal Approach: " + longestOptimal(s, k));
    }

    // =========================================================
    // 1️⃣ BRUTE FORCE APPROACH
    // Check all substrings and count distinct characters each time
    // =========================================================
    public static int longestBruteForce(String s, int k) {
        int n = s.length();
        int maxLen = 0; // To store answer

        // Try every possible starting point
        for (int i = 0; i < n; i++) {

            // Try every ending point for substring
            for (int j = i; j < n; j++) {

                // Create a set to count distinct characters
                Set<Character> set = new HashSet<>();

                // Insert characters of substring s[i...j]
                for (int x = i; x <= j; x++) {
                    set.add(s.charAt(x)); // Add characters to set
                }

                // If distinct characters <= k, update max length
                if (set.size() <= k) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        return maxLen;
    }

    /* TIME COMPLEXITY: O(n^3)  → triple nested operations
       SPACE COMPLEXITY: O(1) or O(k) because set is small
    */


    // =========================================================
    // 2️⃣ BETTER APPROACH - Frequency array for each window
    // Avoid recounting characters from scratch
    // =========================================================
    public static int longestBetter(String s, int k) {
        int n = s.length();
        int maxLen = 0;

        // Start index
        for (int i = 0; i < n; i++) {

            // Frequency map to count each character in window
            Map<Character, Integer> freq = new HashMap<>();

            // Expand end pointer j
            for (int j = i; j < n; j++) {
                char ch = s.charAt(j);

                // Increase frequency
                freq.put(ch, freq.getOrDefault(ch, 0) + 1);

                // Check if distinct count <= k
                if (freq.size() <= k) {
                    maxLen = Math.max(maxLen, j - i + 1);
                } else {
                    break; // No need to expand further
                }
            }
        }
        return maxLen;
    }

    /* TIME COMPLEXITY: O(n^2)
       SPACE COMPLEXITY: O(k)
    */


    // =========================================================
    // 3️⃣ OPTIMAL APPROACH → Sliding Window + HashMap
    // Move both pointers; shrink when > k distinct
    // =========================================================
    public static int longestOptimal(String s, int k) {
        int n = s.length();
        int maxLen = 0;

        // Frequency map for current window
        Map<Character, Integer> map = new HashMap<>();

        int left = 0; // Left pointer (slow pointer)

        // Move right pointer
        for (int right = 0; right < n; right++) {
            char ch = s.charAt(right);

            // Add character to frequency map
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            // If distinct characters exceed k, shrink window
            while (map.size() > k) {
                char leftChar = s.charAt(left);

                // Decrease frequency
                map.put(leftChar, map.get(leftChar) - 1);

                // If freq becomes zero, remove character
                if (map.get(leftChar) == 0)
                    map.remove(leftChar);

                left++; // Move left pointer
            }

            // Update max length when condition satisfied
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    /* TIME COMPLEXITY: O(n)
       - Each character visited at most twice (left & right pointers)
       SPACE COMPLEXITY: O(k)
    */

}


//============================================================
// 📌 VISUALIZATION (Example: s = "eceba", k = 2)
//
// Window Expansion:
// right=0 -> "e"      map={e:1}     valid → max=1
// right=1 -> "ec"     map={e:1,c:1} valid → max=2
// right=2 -> "ece"    map={e:2,c:1} valid → max=3
// right=3 -> "eceb"   map={e:2,c:1,b:1} → size=3 > k
//      shrink left → left moves from 0→1:
//      window becomes "ceb", map={c:1,e:1,b:1} size=3
//      shrink again → left moves to 2:
//      window becomes "eb", map={e:1,b:1} valid → max=3
//
// right=4 -> "eba"    map={e:1,b:1,a:1} size=3>2
//      shrink → window="ba" → size=2
// max length remains = 3
//
// FINAL ANSWER = 3
// Longest substring = "ece"
//============================================================
