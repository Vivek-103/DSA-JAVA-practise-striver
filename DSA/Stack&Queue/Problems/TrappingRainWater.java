// =====================================================
// Problem: Trapping Rain Water
// =====================================================
//
// 🧩 Problem Statement:
// Given n non-negative integers representing an elevation map 
// where the width of each bar is 1, compute how much water 
// can be trapped after raining.
//
// Example:
// Input: height = [4,2,0,3,2,5]
// Output: 9
//
// Visualization:
//
// Bars:        | |           |
//             | |     |     |
//     |       | | |   | |   |
//     4   2   0   3   2   5
//     ↑       ↑       ↑
//   water trapped between bars forms puddles (visualized as blue area)
//
// =====================================================
// ✅ Approach: Two-Pointer Technique (Optimal Solution)
// =====================================================
//
// Intuition:
//  - Water trapped above a bar depends on the shorter of the 
//    tallest bars to its left and right.
//  - So for each position, water trapped = 
//    min(maxLeft, maxRight) - currentHeight
//  - Instead of precomputing arrays for leftMax and rightMax,
//    we can use two pointers (left, right) and dynamically 
//    track maxLeft and maxRight.
//
// =====================================================
// ⚙️ Time Complexity: O(n)
// ⚙️ Space Complexity: O(1)
// =====================================================

public class TrappingRainWater {

    public int trap(int[] height) {
        // If no bars or less than 3 bars, no water can be trapped
        if (height == null || height.length < 3) return 0;

        // Initialize two pointers:
        int left = 0;                    // Start pointer
        int right = height.length - 1;   // End pointer

        // Initialize max heights seen so far from both sides
        int leftMax = 0;
        int rightMax = 0;

        // Variable to store total trapped water
        int trappedWater = 0;

        // Traverse the array using two pointers
        while (left < right) {

            // If height at left is smaller, process left side
            if (height[left] < height[right]) {

                // If current bar is greater than leftMax, update leftMax
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    // Otherwise, water can be trapped above this bar
                    trappedWater += leftMax - height[left];
                    // The difference between leftMax and height[left] 
                    // represents trapped water at this position
                }

                // Move the left pointer inward
                left++;
            }

            // Else, process the right side
            else {
                // If current bar is greater than rightMax, update rightMax
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    // Otherwise, water can be trapped above this bar
                    trappedWater += rightMax - height[right];
                    // The difference between rightMax and height[right] 
                    // represents trapped water at this position
                }

                // Move the right pointer inward
                right--;
            }
        }

        // Return total accumulated water
        return trappedWater;
    }

    // ===============================================
    // 🧠 Visualization Example Walkthrough:
    // ===============================================
    //
    // Input: [4, 2, 0, 3, 2, 5]
    //
    // Step-by-step process:
    //
    // left=0, right=5
    // leftMax=0, rightMax=0, trappedWater=0
    //
    // → height[left]=4 < height[right]=5
    //    leftMax updated to 4
    //    move left → 1
    //
    // → height[1]=2 < height[5]=5
    //    trapped += (4 - 2) = 2
    //
    // → height[2]=0 < height[5]=5
    //    trapped += (4 - 0) = 4
    //
    // → height[3]=3 < height[5]=5
    //    trapped += (4 - 3) = 1
    //
    // → height[4]=2 < height[5]=5
    //    trapped += (4 - 2) = 2
    //
    // → total trapped = 2 + 4 + 1 + 2 = 9
    //
    // ✅ Output: 9
    // ===============================================
}

//
// =====================================================
// ⏱️ Complexity Summary
// =====================================================
// Time Complexity: O(n)
//   - Each element is processed once by either pointer.
// Space Complexity: O(1)
//   - Uses constant extra variables only.
//
// =====================================================
// ✅ Key Takeaways
// =====================================================
// - Two-pointer approach is optimal for this problem.
// - Always move the pointer with smaller height first.
// - The trapped water is based on the min of leftMax and rightMax.
//
// =====================================================
