import java.util.*;

class SingleNumberIII {

    // 🔹 Approach 1: Using HashMap (Brute Force / Easy to Understand)
    // ---------------------------------------------------------------
    // Idea: Count the occurrence of each number in the array
    // Numbers that appear exactly once are the ones we want
    // Time Complexity: O(n)
    // Space Complexity: O(n) → for storing counts in the HashMap
    public int[] singleNumberHashMap(int[] nums) {
        // Create a map to store the frequency of each number
        Map<Integer, Integer> freq = new HashMap<>();

        // Count the frequency of each number
        for (int num : nums) {
            // get current count, default 0 if num not present, then increment
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int[] result = new int[2]; // Since exactly 2 numbers appear once
        int index = 0;

        // Find the two numbers that appear only once
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() == 1) {
                result[index++] = entry.getKey(); // add to result
            }
        }

        return result;
    }

    // 🔹 Approach 2: Bit Manipulation (Optimal / O(1) extra space)
    // -------------------------------------------------------------
    // Idea: Use XOR properties:
    // - XOR of a number with itself = 0
    // - XOR of a number with 0 = number
    // Steps:
    // 1️⃣ XOR all numbers → get xor = a ^ b (a and b are unique numbers)
    // 2️⃣ Find any set bit in xor (we use rightmost set bit) → distinguishes a and b
    // 3️⃣ Partition all numbers into two groups based on this set bit
    // 4️⃣ XOR numbers in each group to get a and b
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int[] singleNumberXOR(int[] nums) {
        int xor = 0;

        // Step 1️⃣: XOR all numbers
        for (int num : nums) {
            xor ^= num; // after this, xor = a ^ b (two unique numbers)
        }

        // Step 2️⃣: Find the rightmost set bit in xor
        // This bit is 1 for one unique number and 0 for the other
        int setBit = xor & -xor; // isolates rightmost 1-bit

        int a = 0, b = 0; // Will hold the two unique numbers

        // Step 3️⃣: Partition numbers into two groups based on setBit
        for (int num : nums) {
            if ((num & setBit) == 0) {
                // Group where setBit is 0
                a ^= num;
            } else {
                // Group where setBit is 1
                b ^= num;
            }
        }

        // Step 4️⃣: Return the two unique numbers
        return new int[]{a, b};
    }

    // 🔹 Approach 3: Sorting (Optional / Less Optimal)
    // ------------------------------------------------
    // Idea: Sort array → numbers appearing twice will be adjacent
    // Numbers appearing once will be alone
    // Time Complexity: O(n log n) → due to sorting
    // Space Complexity: O(1) if in-place sort, or O(n) if using extra space
    public int[] singleNumberSorting(int[] nums) {
        Arrays.sort(nums); // sort the array
        List<Integer> unique = new ArrayList<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            // First element: check with next only
            if (i == 0 && nums[i] != nums[i + 1]) unique.add(nums[i]);
            // Last element: check with previous only
            else if (i == n - 1 && nums[i] != nums[i - 1]) unique.add(nums[i]);
            // Middle elements: check with both previous and next
            else if (i > 0 && i < n - 1 && nums[i] != nums[i - 1] && nums[i] != nums[i + 1]) unique.add(nums[i]);
        }

        return new int[]{unique.get(0), unique.get(1)};
    }

    // 🧠 Detailed Visualization for XOR Approach
    /*
        Example: nums = [1,2,1,3,2,5]

        Step 1: XOR all numbers
        1 ^ 2 ^ 1 ^ 3 ^ 2 ^ 5
        = (1 ^ 1) ^ (2 ^ 2) ^ 3 ^ 5
        = 0 ^ 0 ^ 3 ^ 5
        = 3 ^ 5 = 6 (0110 in binary)

        Step 2: Find rightmost set bit
        xor = 6 (0110)
        rightmost set bit = xor & -xor = 2 (0010)

        Step 3: Partition numbers into two groups based on bit 2
        Group 0 (bit 0) → numbers with 0 at setBit → [1,1,5]
        Group 1 (bit 1) → numbers with 1 at setBit → [2,2,3]

        Step 4: XOR each group
        Group 0: 1 ^ 1 ^ 5 = 5
        Group 1: 2 ^ 2 ^ 3 = 3

        ✅ Result = [3,5]
    */

    // 🚀 Driver Method
    public static void main(String[] args) {
        SingleNumberIII obj = new SingleNumberIII();
        int[] nums = {1, 2, 1, 3, 2, 5};

        System.out.println("🔹 Using HashMap Approach: " + Arrays.toString(obj.singleNumberHashMap(nums)));
        System.out.println("🔹 Using Bit Manipulation XOR: " + Arrays.toString(obj.singleNumberXOR(nums)));
        System.out.println("🔹 Using Sorting Approach: " + Arrays.toString(obj.singleNumberSorting(nums)));
    }
}

/*
------------------------------------------------------------
📌 Summary of Approaches:
------------------------------------------------------------
| Approach             | Time Complexity | Space Complexity | Notes
|----------------------|----------------|-----------------|------------------------------------|
| HashMap              | O(n)           | O(n)             | Simple, easy to understand, uses extra space
| Bit Manipulation XOR | O(n)           | O(1)             | Most optimal, elegant XOR trick
| Sorting              | O(n log n)     | O(1)/O(n)        | Optional, less efficient, relies on sorting
------------------------------------------------------------

✅ Key Idea (XOR):
- XOR of all numbers = a ^ b
- Any set bit in XOR tells you that a and b differ at that bit
- Partition array into 2 groups based on that bit
- XOR each group → get a and b
------------------------------------------------------------
*/
