// File Name: LongestSubstringWithoutRepeat.java
// Problem: Longest Substring Without Repeating Characters
// Language: Java

/*
---------------------------------------------
🧩 PROBLEM UNDERSTANDING:
---------------------------------------------
We are given a string s.
We need to find the *length* of the longest substring 
that contains NO repeating characters.

🔹 Substring means continuous sequence of characters.
🔹 We cannot rearrange or skip characters.

Example:
s = "abcabcbb"

Possible substrings:
- "abc" ✅ (length 3, all unique)
- "bca" ✅ (length 3)
- "abc" ✅ (length 3)
- "cb" ❌ (repeats 'b')

Answer = 3

---------------------------------------------
💡 VISUALIZATION (Sliding Window Approach):
---------------------------------------------
We use two pointers: 
→ left (start of current substring)
→ right (end of current substring)

We slide 'right' through the string:
- Add each character to a set (to track uniqueness)
- If a duplicate is found:
  → Move 'left' forward until duplicate is removed.
- Keep updating max length.

Example Walkthrough:
s = "pwwkew"

Step-by-step:
1. right=0 → 'p' → unique → max=1
2. right=1 → 'w' → unique → max=2
3. right=2 → 'w' → duplicate → move left until duplicate gone:
   remove 'p', then remove first 'w' → now substring = "w"
4. right=3 → 'k' → unique → substring = "wk" → max=2
5. right=4 → 'e' → unique → substring = "wke" → max=3
6. right=5 → 'w' → duplicate → move left till first 'w' removed → substring = "kew" → max=3 ✅

Final answer = 3

---------------------------------------------
⚙️ ALGORITHM (Sliding Window using HashSet):
---------------------------------------------
1️⃣ Create a HashSet<Character> to store unique chars.
2️⃣ Initialize two pointers: left=0, right=0.
3️⃣ Move right pointer across the string:
    - If char not in set → add it → update maxLen.
    - If char already in set → remove chars from left until unique again.
4️⃣ Return maxLen.

---------------------------------------------
⏱️ TIME & SPACE COMPLEXITY:
---------------------------------------------
Time Complexity: O(n)  → each character visited at most twice (once added, once removed)
Space Complexity: O(min(n, charset))  → HashSet stores unique chars only

---------------------------------------------
*/

import java.util.*;

public class LongestSubstringWithoutRepeat {

    // Function to find length of longest substring without repeating characters
    public static int lengthOfLongestSubstring(String s) {
        // HashSet to store unique characters in current window
        Set<Character> set = new HashSet<>();

        int left = 0;       // Left pointer of sliding window
        int maxLen = 0;     // Result (max length found)

        // Traverse the string with right pointer
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // If current character already exists, remove from left side
            while (set.contains(currentChar)) {
                set.remove(s.charAt(left));
                left++;
            }

            // Add current character and update maxLen
            set.add(currentChar);
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    // 🔍 Visualization helper function
    private static void visualizeProcess(String s) {
        System.out.println("\n🧮 Visualization of Sliding Window Steps:");
        Set<Character> set = new HashSet<>();
        int left = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            System.out.println("\n➡ Step " + (right + 1) + ": Checking '" + currentChar + "'");
            System.out.println("Current window before processing: " + set);

            // Remove duplicates if any
            while (set.contains(currentChar)) {
                System.out.println("Duplicate found: '" + currentChar + "', removing '" + s.charAt(left) + "' from set");
                set.remove(s.charAt(left));
                left++;
            }

            set.add(currentChar);
            maxLen = Math.max(maxLen, right - left + 1);

            // Print current window
            System.out.println("Current substring: \"" + s.substring(left, right + 1) + "\"  → Length = " + (right - left + 1));
            System.out.println("Unique characters in window: " + set);
            System.out.println("Current Max Length = " + maxLen);
        }

        System.out.println("\n✅ Final Longest Substring Length = " + maxLen);
    }

    // 🧠 MAIN METHOD - Driver Code
    public static void main(String[] args) {
        String s = "pwwkew";

        // Print input
        System.out.println("Input String: " + s);

        // Compute result
        int result = lengthOfLongestSubstring(s);
        System.out.println("\n✅ Length of Longest Substring Without Repeating Characters = " + result);

        // Visualize process
        visualizeProcess(s);
    }
}

/*
---------------------------------------------
✅ SAMPLE OUTPUT:
---------------------------------------------
Input String: pwwkew

✅ Length of Longest Substring Without Repeating Characters = 3

🧮 Visualization of Sliding Window Steps:

➡ Step 1: Checking 'p'
Current window before processing: []
Current substring: "p"  → Length = 1
Unique characters in window: [p]
Current Max Length = 1

➡ Step 2: Checking 'w'
Current window before processing: [p]
Current substring: "pw"  → Length = 2
Unique characters in window: [p, w]
Current Max Length = 2

➡ Step 3: Checking 'w'
Duplicate found: 'w', removing 'p' from set
Duplicate found: 'w', removing 'w' from set
Current substring: "w"  → Length = 1
Unique characters in window: [w]
Current Max Length = 2

➡ Step 4: Checking 'k'
Current window before processing: [w]
Current substring: "wk"  → Length = 2
Unique characters in window: [w, k]
Current Max Length = 2

➡ Step 5: Checking 'e'
Current window before processing: [w, k]
Current substring: "wke"  → Length = 3
Unique characters in window: [w, k, e]
Current Max Length = 3

➡ Step 6: Checking 'w'
Duplicate found: 'w', removing 'w' from set
Current substring: "kew"  → Length = 3
Unique characters in window: [k, e, w]
Current Max Length = 3

✅ Final Longest Substring Length = 3
---------------------------------------------
*/
