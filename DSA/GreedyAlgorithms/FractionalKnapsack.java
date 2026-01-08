// File name and class name MUST be the same
public class FractionalKnapsack {

    // Inner class to represent an Item
    static class Item {
        int value;   // value of the item
        int weight;  // weight of the item

        // Constructor to initialize value and weight
        Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    // Function to solve Fractional Knapsack using Greedy approach
    public static double fractionalKnapsack(int capacity, Item[] items) {

        // Step 1: Sort items based on value/weight ratio in descending order
        java.util.Arrays.sort(items, (a, b) -> {
            // Calculate value per weight for both items
            double r1 = (double) a.value / a.weight;
            double r2 = (double) b.value / b.weight;

            // Sort in descending order
            return Double.compare(r2, r1);
        });

        // Variable to store the maximum value we can get
        double totalValue = 0.0;

        // Variable to track remaining capacity of knapsack
        int remainingCapacity = capacity;

        // Step 2: Traverse through sorted items
        for (Item item : items) {

            // If knapsack is already full, stop
            if (remainingCapacity == 0) {
                break;
            }

            // If the whole item can be taken
            if (item.weight <= remainingCapacity) {

                // Take the entire item
                totalValue += item.value;

                // Reduce the remaining capacity
                remainingCapacity -= item.weight;

            } else {
                // Otherwise, take only a fraction of the item

                // Fraction = remaining capacity / item's weight
                double fraction = (double) remainingCapacity / item.weight;

                // Add fractional value
                totalValue += item.value * fraction;

                // Knapsack becomes full
                remainingCapacity = 0;
            }
        }

        // Return the maximum value obtained
        return totalValue;
    }

    // Main method to test the implementation
    public static void main(String[] args) {

        // Example input
        int capacity = 50;

        // Create array of items
        Item[] items = {
            new Item(60, 10),
            new Item(100, 20),
            new Item(120, 30)
        };

        // Call fractional knapsack function
        double maxValue = fractionalKnapsack(capacity, items);

        // Print the result
        System.out.println("Maximum value in Knapsack = " + maxValue);
    }
}

/*
-------------------------------------------------------
🧠 APPROACH EXPLANATION
-------------------------------------------------------

BRUTE FORCE:
- Try all subsets and all fractions
- Time Complexity: O(2^n)
- Not practical → NOT USED

BETTER APPROACH:
- Dynamic Programming (used in 0/1 Knapsack)
- Does NOT apply here because items can be broken

OPTIMAL APPROACH (USED):
- Greedy Algorithm
- Sort items by value/weight ratio
- Take full item if possible, else take fraction

-------------------------------------------------------
⏱ TIME & SPACE COMPLEXITY
-------------------------------------------------------

Time Complexity:
- Sorting items: O(n log n)
- Traversing items: O(n)
- Total: O(n log n)

Space Complexity:
- O(1) extra space (excluding input array)

-------------------------------------------------------
*/
