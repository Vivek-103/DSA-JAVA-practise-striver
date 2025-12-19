/**
 * ============================================================================
 * 📌 TASK SCHEDULER (LEETCODE 621) — JAVA IMPLEMENTATION
 * ============================================================================
 *
 * PROBLEM SUMMARY:
 * ----------------
 * We are given an array of tasks represented by characters ('A' to 'Z').
 * Executing a task takes 1 CPU interval.
 * If the same task repeats, it must be separated by at least 'n' cooldown intervals.
 *
 * We must find the MINIMUM number of CPU intervals needed to finish all tasks.
 *
 * APPROACH USED:
 * --------------
 * ✔ Count task frequencies
 * ✔ Push frequencies into a Max Heap
 * ✔ Always pick the most frequent tasks first (greedy strategy)
 * ✔ Use a temporary waiting queue to hold tasks during cooldown
 * ✔ Simulate CPU cycles and cooldown periods
 *
 * WHY MAX HEAP?
 * -------------
 * We want to execute tasks with highest remaining counts first
 * to minimize idle time.
 *
 * TIME COMPLEXITY:
 * ----------------
 * O(N log 26)  => O(N log 26) simplifies to O(N), because 26 < constant
 * where N = total number of tasks
 *
 * SPACE COMPLEXITY:
 * -----------------
 * O(1)  → (max heap holds at most 26 letters)
 *
 * ============================================================================
 */

import java.util.*;

public class TaskScheduler {

    /**
     * Method to compute the minimum CPU intervals required
     * 
     * @param tasks array of task characters
     * @param n cooldown interval
     * @return the minimum number of CPU intervals required
     *
     * TIME COMPLEXITY: O(N log 26) → simplifies to O(N)
     * SPACE COMPLEXITY: O(1)
     */
    public static int leastInterval(char[] tasks, int n) {

        // Step 1: Count frequency of each task using array of size 26
        int[] freq = new int[26];
        for (char task : tasks) {
            freq[task - 'A']++; // Convert 'A' to index 0, 'B' to 1, etc.
        }

        // Step 2: Create a Max Heap to always schedule most frequent task
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int f : freq) {
            if (f > 0) {
                maxHeap.add(f);
            }
        }

        // Queue to store tasks while cooling down
        Queue<int[]> waitQueue = new LinkedList<>();
        // Format: (remainingCount, nextAvailableTime)

        int time = 0; // total CPU time elapsed

        // Step 3: Process tasks until no remaining tasks
        while (!maxHeap.isEmpty() || !waitQueue.isEmpty()) {

            time++; // Every iteration simulates 1 CPU interval

            if (!maxHeap.isEmpty()) {
                int current = maxHeap.poll(); // Pick most frequent task
                current--; // Execute one instance

                if (current > 0) {
                    waitQueue.add(new int[]{current, time + n}); 
                    // add task back after n cooldown intervals
                }
            }

            // If task cooldown finished, push it back into heap
            if (!waitQueue.isEmpty() && waitQueue.peek()[1] == time) {
                maxHeap.add(waitQueue.poll()[0]);
            }
        }

        return time; // total intervals
    }

    /**
     * Utility method to display tasks neatly
     */
    public static void printTasks(char[] tasks) {
        System.out.print("[ ");
        for (char c : tasks) {
            System.out.print(c + " ");
        }
        System.out.println("]");
    }

    /**
     * MAIN METHOD → DEMO & TESTING
     */
    public static void main(String[] args) {

        System.out.println("=== TASK SCHEDULER DEMO ===");

        char[] tasks1 = {'A','A','A','B','B','B'};
        int n1 = 2;

        System.out.print("Tasks: ");
        printTasks(tasks1);
        System.out.println("Cooldown: " + n1);

        int result1 = leastInterval(tasks1, n1);

        System.out.println("Minimum CPU intervals required: " + result1);
        System.out.println();

        // Additional example
        char[] tasks2 = {'A','A','A','B','B','B','C','C'};
        int n2 = 1;

        System.out.print("Tasks: ");
        printTasks(tasks2);
        System.out.println("Cooldown: " + n2);

        int result2 = leastInterval(tasks2, n2);

        System.out.println("Minimum CPU intervals required: " + result2);
    }
}
