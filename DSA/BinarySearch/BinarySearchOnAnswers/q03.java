package BinarySearchOnAnswers;

// KOKO EATING MANGOES — Binary Search on Answers Problem
// Given piles of mangoes and time 'h', find the minimum eating rate to finish all in h hours.

public class q3 {

    // Function to find the maximum element in the array
    public static int findMax(int[] v) {
        int maxi = Integer.MIN_VALUE;
        int n = v.length;

        for (int i = 0; i < n; i++) {
            maxi = Math.max(maxi, v[i]);
        }

        return maxi;
    }

    // Function to calculate total hours required at a given eating rate
    public static int calculateTotalHours(int[] v, int hourly) {
        int totalH = 0;
        int n = v.length;

        for (int i = 0; i < n; i++) {
            totalH += Math.ceil((double)(v[i]) / (double)(hourly));
        }

        return totalH;
    }

    // Brute-force method to find minimum eating rate
    public static int minimumRatetoEatBananasBrute(int[] v, int h) {
        int maxi = findMax(v);

        for (int i = 1; i <= maxi; i++) {
            int reqTime = calculateTotalHours(v, i);
            if (reqTime <= h) {
                return i;
            }
        }

        return maxi;
    }

    // Optimal method using Binary Search
    public static int minimumRateToEatBananasOptimal(int[] v, int h) {
        int low = 1;
        int high = findMax(v);

        while (low <= high) {
            int mid = (low + high) / 2;
            int totalH = calculateTotalHours(v, mid);

            if (totalH <= h) {
                high = mid - 1;  // Try smaller rate
            } else {
                low = mid + 1;   // Try larger rate
            }
        }

        return low;
    }

    // ----------------------------
    // MAIN METHOD FOR TESTING
    // ----------------------------
    public static void main(String[] args) {
        // Example test case
        int[] mangoes = {30, 11, 23, 4, 20}; // Piles of mangoes
        int h = 6;  // Hours available to eat all

        System.out.println("Test Case:");
        System.out.print("Mango piles: ");
        for (int m : mangoes) System.out.print(m + " ");
        System.out.println("\nHours available: " + h);

        // Run both methods
        int bruteResult = minimumRatetoEatBananasBrute(mangoes, h);
        int optimalResult = minimumRateToEatBananasOptimal(mangoes, h);

        // Output results
        System.out.println("\nMinimum Rate (Brute-force): " + bruteResult);
        System.out.println("Minimum Rate (Optimal/Binary Search): " + optimalResult);
    }
}
