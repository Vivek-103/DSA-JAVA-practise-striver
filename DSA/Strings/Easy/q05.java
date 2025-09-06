package Easy;

import java.util.HashMap;

public class q05 {

    // Function to check if two strings are isomorphic
    public boolean isIsomorphic(String s, String t) {
        // If the lengths of both strings are different, they cannot be isomorphic
        if (s.length() != t.length()) {
            return false;
        }

        // Create two hash maps to store character mappings from s to t and t to s
        HashMap<Character, Character> mapST = new HashMap<>();
        HashMap<Character, Character> mapTS = new HashMap<>();

        // Loop through each character of the strings
        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i); // Get character from string s
            char charT = t.charAt(i); // Get corresponding character from string t

            // Check if mapping from s to t exists and is inconsistent
            if (mapST.containsKey(charS)) {
                if (mapST.get(charS) != charT) {
                    return false; // Mismatch found, not isomorphic
                }
            } else {
                mapST.put(charS, charT); // Create new mapping from s to t
            }

            // Check if mapping from t to s exists and is inconsistent
            if (mapTS.containsKey(charT)) {
                if (mapTS.get(charT) != charS) {
                    return false; // Mismatch found, not isomorphic
                }
            } else {
                mapTS.put(charT, charS); // Create new mapping from t to s
            }
        }

        // If all mappings are consistent, return true
        return true;
    }

    // Main method to test the function
    public static void main(String[] args) {
        q05 obj = new q05(); // Create an object of q05 class

        // Test cases
        String s1 = "egg";
        String t1 = "add";
        System.out.println("Are 'egg' and 'add' isomorphic? " + obj.isIsomorphic(s1, t1));

        String s2 = "foo";
        String t2 = "bar";
        System.out.println("Are 'foo' and 'bar' isomorphic? " + obj.isIsomorphic(s2, t2));

        String s3 = "paper";
        String t3 = "title";
        System.out.println("Are 'paper' and 'title' isomorphic? " + obj.isIsomorphic(s3, t3));
    }
}

/*
Time Complexity:
- We loop through the length of the strings once.
- Each operation on the hash map is O(1) on average.
- Let n be the length of the strings.
- The time complexity is O(n).
*/

