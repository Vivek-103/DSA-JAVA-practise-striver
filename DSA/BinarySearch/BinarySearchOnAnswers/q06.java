// Capacity to Ship Packages within D Days
// Two approaches included:
// 1) Brute force over all possible capacities from max(weights) to sum(weights).
// 2) Optimal binary search on answer within the same range using a feasibility check (findDays).

public class q6 { // Define the class (keep the same name as your file).

    // Helper: given a capacity 'cap', compute how many days are needed to ship all weights in order.
    public static int findDays(int[] weights, int cap) { // Method to calculate days needed for a given capacity.
        int days = 1; // First day starts as day 1.
        int load = 0; // Current day's accumulated load.
        int n = weights.length; // Cache the array length for the loop.

        for (int i = 0; i < n; i++) { // Traverse each package in order.
            if (load + weights[i] > cap) { // If adding current package exceeds capacity for the day...
                days += 1; // ...we need an additional day.
                load = weights[i]; // Start the new day with this package loaded.
            } else { // Otherwise, it fits in the current day.
                load += weights[i]; // Accumulate this package's weight on the same day.
            }
        }
        return days; // Return total days required at capacity 'cap'.
    }

    // Brute force: try every capacity from max(weights) to sum(weights) and return the first feasible one.
    public static int leastWeightCapacityBrute(int[] weights, int d) { // Method for brute-force search.
        int maxi = Integer.MIN_VALUE; // Track the maximum single package weight.
        int sum = 0; // Track the total weight across all packages.

        for (int i = 0; i < weights.length; i++) { // Single pass to compute 'maxi' and 'sum'.
            sum += weights[i]; // Add current weight to total sum.
            maxi = Math.max(maxi, weights[i]); // Update maximum package weight if needed.
        }

        for (int cap = maxi; cap <= sum; cap++) { // Try each possible capacity from tightest to loosest.
            if (findDays(weights, cap) <= d) { // If this capacity can ship within 'd' days...
                return cap; // ...it's the least feasible capacity (since we go upward).
            }
        }
        return -1; // Should not happen for valid inputs; indicates no feasible capacity found.
    }

    // Optimal: binary search on capacity between [max(weights), sum(weights)] using the feasibility check.
    public static int leastWeightCapacityOptimal(int[] weights, int d) { // Method for optimal binary search.
        int low = Integer.MIN_VALUE; // Will become max(weights) — smallest possible capacity.
        int high = 0; // Will become sum(weights) — largest necessary capacity.

        for (int i = 0; i < weights.length; i++) { // Compute search bounds in one pass.
            high += weights[i]; // Accumulate total weight to set upper bound.
            low = Math.max(low, weights[i]); // Track the heaviest item to set lower bound.
        }

        while (low <= high) { // Standard binary search on the answer range.
            int mid = (low + high) / 2; // Candidate capacity.
            int numberOfDays = findDays(weights, mid); // Days needed if ship capacity is 'mid'.

            if (numberOfDays <= d) { // If we can ship within 'd' days at this capacity...
                high = mid - 1; // ...try to see if an even smaller capacity also works.
            } else { // Otherwise, 'mid' is too small to meet the deadline.
                low = mid + 1; // Increase capacity to reduce the days needed.
            }
        }
        return low; // 'low' ends at the minimal feasible capacity.
    }

    // Main method to test both approaches
    public static void main(String[] args) {
        int[] weights = {1, 2, 3, 1, 1};
        int d = 4;

        // Brute force result
        int bruteResult = leastWeightCapacityBrute(weights, d);
        System.out.println("Brute Force Result: " + bruteResult);

        // Optimal result
        int optimalResult = leastWeightCapacityOptimal(weights, d);
        System.out.println("Optimal Result: " + optimalResult);
    }
}

/*
-------------------------------
Time & Space Complexity
-------------------------------

findDays(int[] weights, int cap):
    • Time: O(n), where n = number of packages (one full pass).
    • Space: O(1), only variables used.

leastWeightCapacityBrute(int[] weights, int d):
    • Time: O((sum(weights) - max(weights) + 1) * n)
            → Worst case O(W * n), where W = sum(weights).
    • Space: O(1).

leastWeightCapacityOptimal(int[] weights, int d):
    • Time: O(n * log(sum(weights) - max(weights) + 1))
            → Much faster due to binary search.
    • Space: O(1).

--------------------------------
*/
