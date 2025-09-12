package Medium;

class q04 {
    // Time Complexity: O(1)
    // We calculate the result using a mathematical formula without iterating over substrings

    public int countSubstrings(String s) {
        int n = s.length(); // Get the length of the string

        // The total number of substrings in a string of length n is n * (n + 1) / 2
        return n * (n + 1) / 2;
    }

    public static void main(String[] args) {
        q04 solution = new q04(); // Create an instance of q04

        // Example test cases
        String input1 = "abc";
        String input2 = "aaa";
        String input3 = "";

        System.out.println("Input: \"" + input1 + "\" Output: " + solution.countSubstrings(input1));
        System.out.println("Input: \"" + input2 + "\" Output: " + solution.countSubstrings(input2));
        System.out.println("Input: \"" + input3 + "\" Output: " + solution.countSubstrings(input3));
    }
}
