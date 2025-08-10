import java.util.*; // import utility classes (Arrays etc) used in examples and debugging
// MINIMUM NO. OF DAYS TO MAKE M BOUQUETS
public class q4 { // main class wrapper for the solutions and examples

    // -------------------------
    // Helper: canMake
    // -------------------------
    // This helper checks if it's possible to make 'm' bouquets with 'k' flowers each
    // when we consider that a flower at index i is available iff bloomDay[i] <= days.
    public static boolean canMake(int[] bloomDay, int m, int k, int days) { // method header
        // if we need zero bouquets then it's always possible
        if (m == 0) return true; // trivial case: nothing to make

        int bouquets = 0; // count of bouquets formed so far
        int flowersInCurrent = 0; // consecutive bloomed flowers counter

        for (int i = 0; i < bloomDay.length; i++) { // iterate through each flower
            // if this flower has bloomed by 'days'
            if (bloomDay[i] <= days) { // check bloom condition
                flowersInCurrent++; // extend current contiguous segment of bloomed flowers
                // if we have enough contiguous flowers to make one bouquet
                if (flowersInCurrent == k) { // check bouquet completion
                    bouquets++; // increment bouquet count
                    flowersInCurrent = 0; // reset contiguous counter because bouquets cannot reuse flowers
                    // if we already formed required bouquets, return early
                    if (bouquets == m) return true; // early success
                }
            } else { // current flower not bloomed by 'days'
                flowersInCurrent = 0; // reset contiguous counter because sequence broken
            }
        }

        return false; // after scanning all flowers, couldn't make m bouquets
    }

    // -------------------------
    // Brute force approach
    // -------------------------
    // Iterate day by day from min bloom to max bloom and test feasibility using canMake.
    // This is simple but can be slow when max bloom value is very large.
    public static int minDaysBruteForce(int[] bloomDay, int m, int k) { // brute force method header
        int n = bloomDay.length; // number of flowers available
        // quick impossibility check: not enough total flowers to make required bouquets
        if ((long) m * k > n) { // use long to avoid integer overflow
            return -1; // impossible, not enough flowers total
        }

        // find minimum and maximum bloom day values to bound the brute force search
        int minDay = Integer.MAX_VALUE; // initialize min as very large
        int maxDay = Integer.MIN_VALUE; // initialize max as very small
        for (int x : bloomDay) { // loop through bloomDay array
            if (x < minDay) minDay = x; // update minimum bloom day
            if (x > maxDay) maxDay = x; // update maximum bloom day
        }

        // try every day from minDay to maxDay (inclusive)
        for (int day = minDay; day <= maxDay; day++) { // brute force over days
            if (canMake(bloomDay, m, k, day)) { // test feasibility for this day
                return day; // first feasible day is the minimal day, so return it
            }
        }

        return -1; // if none of the days in range worked (shouldn't happen if total check passed), return -1
    }

    // -------------------------
    // Optimal approach (binary search)
    // -------------------------
    // Use binary search on answer (days) because the feasibility function "canMake"
    // is monotonic: if you can make m bouquets at day D, you can do it at any day > D.
    public static int minDaysBinarySearch(int[] bloomDay, int m, int k) { // binary search method header
        int n = bloomDay.length; // number of flowers available
        // quick impossibility check: not enough total flowers to make required bouquets
        if ((long) m * k > n) { // again use long for safety
            return -1; // impossible
        }

        // find the search bounds: minimum and maximum bloom day values
        int left = Integer.MAX_VALUE; // will become minimum bloom day
        int right = Integer.MIN_VALUE; // will become maximum bloom day
        for (int x : bloomDay) { // loop through bloomDay array
            if (x < left) left = x; // update left boundary
            if (x > right) right = x; // update right boundary
        }

        // binary search for the smallest day that makes canMake true
        while (left < right) { // standard binary search loop
            int mid = left + (right - left) / 2; // avoid overflow computing mid
            if (canMake(bloomDay, m, k, mid)) { // if mid days is sufficient
                right = mid; // try to find a smaller feasible day on left side
            } else {
                left = mid + 1; // mid not sufficient, need larger days
            }
        }

        // after loop left == right, verify and return
        // extra verification (defensive): if even at left it's not possible, return -1
        if (canMake(bloomDay, m, k, left)) { // check final candidate
            return left; // left is minimal feasible day
        } else {
            return -1; // not possible (shouldn't happen due to earlier checks)
        }
    }

    // -------------------------
    // main method with examples
    // -------------------------
    public static void main(String[] args) { // program entry point
        // example 1: sample from known problem
        int[] bloom1 = new int[] {1, 10, 3, 10, 2}; // bloom days array for example 1
        int m1 = 3; // need 3 bouquets
        int k1 = 1; // each bouquet uses 1 flower
        // print brute force result for example 1
        System.out.println("Example1 - Brute force result: " + minDaysBruteForce(bloom1, m1, k1)); // expected 3
        // print binary search result for example 1
        System.out.println("Example1 - Binary search result: " + minDaysBinarySearch(bloom1, m1, k1)); // expected 3

        // example 2: impossible because total flowers < m * k
        int[] bloom2 = new int[] {1, 10, 3, 10, 2}; // same bloom array
        int m2 = 3; // need 3 bouquets
        int k2 = 2; // each bouquet needs 2 flowers => total need 6 flowers but only 5 available
        // print brute force result for example 2
        System.out.println("Example2 - Brute force result: " + minDaysBruteForce(bloom2, m2, k2)); // expected -1
        // print binary search result for example 2
        System.out.println("Example2 - Binary search result: " + minDaysBinarySearch(bloom2, m2, k2)); // expected -1

        // additional example: contiguous requirement matters
        int[] bloom3 = new int[] {7, 7, 7, 7, 12, 7, 7}; // many same-day blooms except one late
        int m3 = 2; // need 2 bouquets
        int k3 = 3; // each bouquet needs 3 contiguous flowers
        // print results for example 3
        System.out.println("Example3 - Brute force result: " + minDaysBruteForce(bloom3, m3, k3)); // expected 7
        System.out.println("Example3 - Binary search result: " + minDaysBinarySearch(bloom3, m3, k3)); // expected 7
    } // end main

} // end class
