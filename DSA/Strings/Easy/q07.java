package Easy;
import java.util.Arrays;

public class q07 {

    // Function to check if two strings are anagrams of each other
    public boolean isAnagram(String s, String t) {
        // If the lengths of the strings are different, they can't be anagrams
        if (s.length() != t.length()) {
            return false;
        }

        // Convert both strings to character arrays
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        // Sort both character arrays
        Arrays.sort(sArray);
        Arrays.sort(tArray);

        // Compare the sorted arrays; if they are equal, it's an anagram
        return Arrays.equals(sArray, tArray);
    }

    // Main method to test the function
    public static void main(String[] args) {
        q07 obj = new q07(); // Create an object of the q07 class

        // Test case 1
        String s1 = "listen";
        String t1 = "silent";
        System.out.println("Are 'listen' and 'silent' anagrams? " + obj.isAnagram(s1, t1));

        // Test case 2
        String s2 = "triangle";
        String t2 = "integral";
        System.out.println("Are 'triangle' and 'integral' anagrams? " + obj.isAnagram(s2, t2));

        // Test case 3
        String s3 = "apple";
        String t3 = "papel";
        System.out.println("Are 'apple' and 'papel' anagrams? " + obj.isAnagram(s3, t3));

        // Test case 4
        String s4 = "rat";
        String t4 = "car";
        System.out.println("Are 'rat' and 'car' anagrams? " + obj.isAnagram(s4, t4));
    }
}

/*
Time Complexity:
- Converting strings to character arrays takes O(n), where n is the length of the strings.
- Sorting each array takes O(n log n).
- Comparing the two arrays takes O(n).
- Therefore, the overall time complexity is O(n log n).
*/
