//There’s an array ‘A’ of size ‘N’ with an equal number of positive and negative elements. Without altering the relative order of positive and negative elements, you must return an array of alternately positive and negative values.

// Rearrange Array Elements by Sign
import java.util.*;

public class q07 {
    public static void main(String[] args) {
        // Input array with positive and negative numbers
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 2, -4, -5));

        // Call the function to rearrange elements by sign
        ArrayList<Integer> ans = RearrangebySign(A);

        // Print the rearranged array
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }
    }

    // Function to rearrange array so that positive and negative numbers alternate
    public static ArrayList<Integer> RearrangebySign(ArrayList<Integer> A) {
        int n = A.size(); // Get the size of the input array

        // Create an answer array of the same size, filled with 0s initially
        ArrayList<Integer> ans = new ArrayList<>(Collections.nCopies(n, 0));

        // posIndex will hold the index for placing positive numbers (starting at 0)
        // negIndex will hold the index for placing negative numbers (starting at 1)
        int posIndex = 0, negIndex = 1;

        // Loop through each element in the input array
        for (int i = 0; i < n; i++) {
            int current = A.get(i);

            // If the element is negative, place it at the next available odd index
            if (current < 0) {
                ans.set(negIndex, current);
                negIndex += 2; // Move to the next odd index
            }

            // If the element is positive, place it at the next available even index
            else {
                ans.set(posIndex, current);
                posIndex += 2; // Move to the next even index
            }
        }

        // Return the rearranged array
        return ans;
    }
}


/* another way to solve this problem⬇️
class Solution {
    public int[] rearrangeArray(int[] nums) {
      int n = nums.length;
      int [] result = new int [n];
      int posIndex = 0;
      int negIndex = 1;
      for (int i = 0;i<n;i++){
        if(nums[i]>0){
            result[posIndex] = nums[i];
            posIndex +=2;
        }else {
            result[negIndex] = nums[i];
            negIndex +=2;
        }
      }
      return result;

    }
}
 */

 

//VARIETY 2 IS WHEN BOTH POSITIVE AND NEGATIVE ELEMENTS ARE NOT EQUAL
class Solution {
    public int[] rearrangeArray(int[] nums) {
        int n = nums.length;

        // Step 1: Separate positives and negatives into two lists
        List<Integer> positives = new ArrayList<>();
        List<Integer> negatives = new ArrayList<>();

        for (int num : nums) {
            if (num >= 0) positives.add(num);  // Collect all positive numbers
            else negatives.add(num);           // Collect all negative numbers
        }

        // Step 2: Prepare result array to store the rearranged output
        int[] result = new int[n];

        int i = 0; // pointer for result array
        int p = 0; // pointer for positives list
        int ng = 0; // pointer for negatives list

        // Step 3: Alternate filling from both lists while both have elements
        while (p < positives.size() && ng < negatives.size()) {
            // First add one positive, then one negative
            result[i++] = positives.get(p++);
            result[i++] = negatives.get(ng++);
        }

        // Step 4: Add remaining positives if any
        while (p < positives.size()) {
            result[i++] = positives.get(p++);
        }

        // Step 5: Add remaining negatives if any
        while (ng < negatives.size()) {
            result[i++] = negatives.get(ng++);
        }

        return result;
    }
}

