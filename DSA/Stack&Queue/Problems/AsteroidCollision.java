// =============================================================
// Problem: Asteroid Collision
// Short class name: AsteroidCollision
// =============================================================
//
// Statement (brief):
//  - We have an array of integers representing asteroids in a row.
//  - Each integer's absolute value is the asteroid's size.
//  - The sign indicates direction: positive → moving right, negative → moving left.
//  - When two asteroids moving in opposite directions meet, the smaller one explodes.
//    If equal size, both explode. Asteroids moving in same direction never meet.
//  - Return the state of asteroids after all collisions.
//
// Intuition (stack simulation):
//  - Keep a stack of surviving asteroids as we scan left → right.
//  - When a new asteroid moves left (negative) and the stack top moves right (positive),
//    they may collide. Simulate collisions by comparing sizes and popping the smaller one.
//  - Continue until the incoming asteroid is destroyed, inserted, or no collision possible.
//
// Complexity:
//  - Time: O(n) amortized — each asteroid pushed/popped at most once.
//  - Space: O(n) for the stack.
// =============================================================

public class AsteroidCollision {

    public int[] asteroidCollision(int[] asteroids) {
        // Use java.util.Stack to hold surviving asteroids (we'll store ints)
        java.util.Stack<Integer> stack = new java.util.Stack<>();

        // Iterate through each asteroid in the input from left to right
        for (int ast : asteroids) {

            // Flag to check if the current asteroid survives after possible collisions
            boolean alive = true;

            // Only collisions possible when:
            //  - there is at least one asteroid on stack (stack.peek() > 0)
            //  - current asteroid is moving left (ast < 0)
            // If these are true, we must simulate collisions.
            while (alive && !stack.isEmpty() && stack.peek() > 0 && ast < 0) {

                // Compare absolute sizes of top-of-stack and current asteroid
                int top = stack.peek();            // positive (moving right)
                int topSize = Math.abs(top);      // size of top asteroid
                int curSize = Math.abs(ast);      // size of current asteroid

                // Case 1: top asteroid is smaller -> it explodes
                if (topSize < curSize) {
                    stack.pop();   // remove the top asteroid from stack
                    // keep alive = true so the loop continues to check further collisions
                    // with the next asteroid on the stack (if any)
                }

                // Case 2: top asteroid is larger -> current asteroid explodes
                else if (topSize > curSize) {
                    // current asteroid destroyed, mark alive false to stop and do NOT push it
                    alive = false;
                }

                // Case 3: equal sizes -> both explode
                else { // topSize == curSize
                    // remove the top asteroid
                    stack.pop();
                    // current asteroid also destroyed
                    alive = false;
                }

                // After each comparison loop continues only if 'alive' still true
                // and there is another possible collision (stack.peek() > 0 && ast < 0)
            }

            // If current asteroid survived all possible collisions, push it onto stack
            if (alive) {
                stack.push(ast);
            }
        }

        // Convert stack to result array in correct order (stack is LIFO)
        int n = stack.size();
        int[] result = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        // Return final state of asteroids after all collisions
        return result;
    }

    // =============================================================
    // Visualization and Example Walkthroughs
    // =============================================================
    //
    // Example 1:
    // Input:  [5, 10, -5]
    // Step:
    //  - push 5        → stack = [5]
    //  - push 10       → stack = [5,10]
    //  - see -5: top=10 (positive) and -5 (negative) collide:
    //      topSize=10 > curSize=5 → -5 explodes
    //    Result: stack stays [5,10]
    // Output: [5,10]
    //
    // Example 2:
    // Input: [8, -8]
    // Step:
    //  - push 8        → stack = [8]
    //  - see -8: topSize == curSize → both explode (pop 8, -8 destroyed)
    //    Result: stack = []
    // Output: []
    //
    // Example 3:
    // Input: [10, 2, -5]
    // Step:
    //  - push 10      → [10]
    //  - push 2       → [10,2]
    //  - see -5:
    //      compare with top 2: 2 < 5 → pop 2 -> stack [10], continue
    //      compare with top 10: 10 > 5 → -5 destroyed
    //    Result: [10]
    // Output: [10]
    //
    // Example 4:
    // Input: [-2, -1, 1, 2]
    // Step:
    //  - -2 pushed -> [-2]
    //  - -1 pushed -> [-2,-1]
    //  - 1 pushed  -> [-2,-1,1] (1 moves right; no collision)
    //  - 2 pushed  -> [-2,-1,1,2]
    // Output: [-2,-1,1,2] (no opposite-direction collisions that meet)
    //
    // =============================================================

    // =============================================================
    // Complexity:
    //  - Time: O(n) amortized, because each asteroid is pushed/popped at most once.
    //  - Space: O(n) in worst case for the stack.
    // =============================================================
}
