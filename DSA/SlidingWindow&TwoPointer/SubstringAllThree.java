//*******************************************************
// Number of Substrings Containing All Three Characters
// Approaches Covered: Brute Force, Better, Optimal
// Each line contains comments for teaching purposes
//*******************************************************

public class SubstringAllThree {

    // =========================================================
    // MAIN METHOD (For Testing All Methods)
    // =========================================================
    public static void main(String[] args) {
        // Sample input
        String s = "abcabc";

        // Calling brute-force method
        System.out.println("Brute Force Output: " + countBruteForce(s));

        // Calling better approach
        System.out.println("Better Approach Output: " + countBetter(s));

        // Calling optimal sliding window approach
        System.out.println("Optimal Output: " + countOptimal(s));
    }

    // =========================================================
    // 1️⃣ BRUTE FORCE APPROACH
    // Try every possible substring & check if it has a,b,c
    // =========================================================
    public static int countBruteForce(String s) {
        int n = s.length(); // Length of string
        int count = 0;      // Result variable

        // Outer loop for starting index
        for (int i = 0; i < n; i++) {
            // Inner loop for ending index
            for (int j = i; j < n; j++) {

                // Track if substring contains all characters
                boolean hasA = false, hasB = false, hasC = false;

                // Check substring s[i...j]
                for (int k = i; k <= j; k++) {
                    char ch = s.charAt(k);
                    if (ch == 'a') hasA = true;
                    if (ch == 'b') hasB = true;
                    if (ch == 'c') hasC = true;
                }

                // If all found, count the substring
                if (hasA && hasB && hasC) count++;
            }
        }
        return count;
    }

    /* TIME COMPLEXITY:
        - Three nested loops → O(n^3)
       SPACE COMPLEXITY:
        - O(1)
    */


    // =========================================================
    // 2️⃣ BETTER APPROACH - Using Frequency Count for Each Window
    // Instead of checking whole substring repeatedly
    // =========================================================
    public static int countBetter(String s) {
        int n = s.length();
        int count = 0;

        // Outer loop for start index
        for (int i = 0; i < n; i++) {

            // Maintain frequency array for window
            int[] freq = new int[3]; // 0->a,1->b,2->c

            // Expand window
            for (int j = i; j < n; j++) {
                char ch = s.charAt(j);

                // Update frequency
                freq[ch - 'a']++;

                // Check if all characters exist
                if (freq[0] > 0 && freq[1] > 0 && freq[2] > 0)
                    count++;
            }
        }
        return count;
    }

    /* TIME COMPLEXITY:
        - Two loops → O(n^2)
       SPACE COMPLEXITY:
        - O(1)
    */


    // =========================================================
    // 3️⃣ OPTIMAL APPROACH → Sliding Window + Last Occurrence
    // Count substrings ending at each index using formula
    // =========================================================
    public static int countOptimal(String s) {
        int n = s.length();
        int[] last = {-1, -1, -1}; // last occurrence of a,b,c
        int count = 0;

        // Traverse string
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            // Update last occurrence index
            last[ch - 'a'] = i;

            // Check if all a,b,c appeared so far
            if (last[0] != -1 && last[1] != -1 && last[2] != -1) {
                // Minimum last occurrence gives starting point
                count += 1 + Math.min(last[0], Math.min(last[1], last[2]));
            }
        }
        return count;
    }

    /* TIME COMPLEXITY:
        - Single loop → O(n)
       SPACE COMPLEXITY:
        - O(1)
    */
}


// =========================================================
// 📌 VISUALIZATION EXAMPLE (For String: "abcabc")
/*

Index: 0 1 2 3 4 5
       a b c a b c

Last occurrence updates:
i=0 -> a -> [-1,-1,0] → Not valid
i=1 -> b -> [-1,1,0] → Not valid
i=2 -> c -> [2,1,0]  → Valid → min=0 → count += 1

i=3 -> a -> [3,1,0] → min=0 → count += 1
i=4 -> b -> [3,4,0] → min=0 → count += 1
i=5 -> c -> [3,4,5] → min=3 → count += 4

Final Count = 10

*/
// =========================================================
