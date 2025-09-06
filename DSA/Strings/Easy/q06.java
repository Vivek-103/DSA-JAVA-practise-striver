package Easy;

public class q06{

    // Function to check if 'goal' is a rotation of 's'
    public boolean isRotation(String s, String goal) {
        // If lengths are not equal, one cannot be a rotation of the other
        if (s.length() != goal.length()) {
            return false;
        }

        // Concatenate s with itself; if goal is a substring, then it's a rotation
        String combined = s + s;

        // Check if goal is a substring of the concatenated string
        return combined.contains(goal);
    }

    // Main method to test the function
    public static void main(String[] args) {
        q05 obj = new q05(); // Create an object of the q05 class

        // Test cases
        String s1 = "abcde";
        String goal1 = "cdeab";
        System.out.println("Is 'cdeab' a rotation of 'abcde'? " + obj.isRotation(s1, goal1));

        String s2 = "abcde";
        String goal2 = "abced";
        System.out.println("Is 'abced' a rotation of 'abcde'? " + obj.isRotation(s2, goal2));

        String s3 = "waterbottle";
        String goal3 = "erbottlewat";
        System.out.println("Is 'erbottlewat' a rotation of 'waterbottle'? " + obj.isRotation(s3, goal3));
    }
}

/*
Time Complexity:
- Concatenating two strings takes O(n), where n is the length of the string s.
- Checking if goal is a substring of combined takes O(n).
- Overall time complexity is O(n).
*/

