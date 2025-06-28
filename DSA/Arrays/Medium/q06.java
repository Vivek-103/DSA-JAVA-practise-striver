//SORT COLORS We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.i.e you have to sort red,white and blue
// Without using library's Sort function
// ALSO KNOWN AS DUTCH NATIONAL FLAG PROBLEM
// SIMPLE METHOD i.e BRUTE FORCE - USE ANY SORTING ALGORITHM

    
public class q06 {

    // Brute Force Approach
    public static void sortColorsBruteForce(int[] arr) {
        // We will count how many 0s, 1s, and 2s are in the array
        int count0 = 0, count1 = 0, count2 = 0;

        // Loop through the array and count each color
        for (int num : arr) {
            if (num == 0) count0++;
            else if (num == 1) count1++;
            else count2++;
        }

        // Fill the array again with 0s, 1s, and 2s in order
        int i = 0;

        // First put all 0s
        while (count0-- > 0) arr[i++] = 0;

        // Then put all 1s
        while (count1-- > 0) arr[i++] = 1;

        // Finally put all 2s
        while (count2-- > 0) arr[i++] = 2;
    }

    // Better Approach (Using extra array)
    public static void sortColorsBetter(int[] arr) {
        int n = arr.length;
        int[] temp = new int[n]; // New array to store sorted result
        int index = 0;

        // First copy all 0s
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) temp[index++] = 0;
        }

        // Then copy all 1s
        for (int i = 0; i < n; i++) {
            if (arr[i] == 1) temp[index++] = 1;
        }

        // Then copy all 2s
        for (int i = 0; i < n; i++) {
            if (arr[i] == 2) temp[index++] = 2;
        }

        // Copy sorted elements back to original array
        for (int i = 0; i < n; i++) {
            arr[i] = temp[i];
        }
    }

    // Optimal Approach (Dutch National Flag Algorithm)
    public static void sortColorsOptimal(int[] arr) {
        // We will use 3 pointers: low, mid, high
        int low = 0, mid = 0, high = arr.length - 1;

        // We move mid from start to end
        while (mid <= high) {
            if (arr[mid] == 0) {
                // Swap arr[low] and arr[mid] if it's 0
                int temp = arr[low];
                arr[low] = arr[mid];
                arr[mid] = temp;
                low++;
                mid++;
            } else if (arr[mid] == 1) {
                // If it's 1, just move mid forward
                mid++;
            } else {
                // If it's 2, swap arr[mid] and arr[high]
                int temp = arr[mid];
                arr[mid] = arr[high];
                arr[high] = temp;
                high--;
                // Do not move mid here because swapped value may be 0 or 1
            }
        }
    }

    // Helper function to print array
    public static void printArray(int[] arr) {
        for (int num : arr) System.out.print(num + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {2, 0, 2, 1, 1, 0};

        System.out.println("Original Array:");
        printArray(arr);

        // Choose one of the sorting methods:
        // sortColorsBruteForce(arr);
        // sortColorsBetter(arr);
        sortColorsOptimal(arr);

        System.out.println("Sorted Array:");
        printArray(arr);
    }
}

