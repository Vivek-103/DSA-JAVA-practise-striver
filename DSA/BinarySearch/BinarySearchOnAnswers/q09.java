// BOOK ALLOCATION PROBLEM
// Problem Statement: 
// We are given 'n' books, each with some number of pages, and 'm' students. 
// The books must be allocated to students in a contiguous manner (no book splitting).
// We want to allocate books such that the maximum number of pages assigned to any student is minimized.

import java.util.*;

public class q9 {

    // -----------------------------------------------------------
    // Helper Function: countStudents
    // -----------------------------------------------------------
    // This function checks how many students are needed if we assume
    // that no student can be assigned more than 'pages' pages.
    // Input: 
    // - arr -> list of books (pages in each book)
    // - pages -> maximum pages a student can take
    // Output:
    // - number of students required under this condition
    public static int countStudents(ArrayList<Integer> arr, int pages) {
        int n = arr.size();   // number of books
        int students = 1;     // start with 1 student
        long pagesStudent = 0; // pages allocated to current student

        // Traverse all books
        for (int i = 0; i < n; i++) {
            // If adding current book does not exceed 'pages'
            if (pagesStudent + arr.get(i) <= pages) {
                pagesStudent += arr.get(i);  // allocate book to current student
            } else {
                // Otherwise, assign this book to next student
                students++;
                pagesStudent = arr.get(i);
            }
        }
        return students; // return total students required
    }

    // -----------------------------------------------------------
    // Brute Force Approach
    // -----------------------------------------------------------
    // Try every possible maximum pages from "max book pages" to "sum of all pages"
    // and check the minimum valid allocation.
    // Time Complexity: O((sum of pages - maxPage) * n) ≈ O(n * sum(pages))
    // Very slow for large inputs.
    public static int findPagesBrute(ArrayList<Integer> arr, int n, int m) {
        if (m > n) return -1; // More students than books -> impossible

        // Minimum possible = max single book (as no student can take less than that)
        int low = Collections.max(arr);
        // Maximum possible = sum of all pages (if one student takes everything)
        int high = arr.stream().mapToInt(Integer::intValue).sum();

        // Try every possible number of pages
        for (int pages = low; pages <= high; pages++) {
            // If exactly 'm' students are required with this allocation
            if (countStudents(arr, pages) == m) {
                return pages; // return answer
            }
        }
        return low; // fallback (shouldn't normally reach here)
    }

    // -----------------------------------------------------------
    // Optimal Approach (Binary Search)
    // -----------------------------------------------------------
    // Instead of brute force, we apply binary search on "answer space".
    // Search between low = max(arr) and high = sum(arr).
    // At each mid, check how many students are needed.
    // If more students are needed than 'm', increase low.
    // Otherwise, decrease high.
    // Time Complexity: O(n * log(sum(arr)))
    public static int findPagesOptimal(ArrayList<Integer> arr, int n, int m) {
        if (m > n) return -1; // More students than books -> impossible

        // Search space
        int low = Collections.max(arr);
        int high = arr.stream().mapToInt(Integer::intValue).sum();

        // Binary search loop
        while (low <= high) {
            int mid = (low + high) / 2; // try middle value
            int students = countStudents(arr, mid); // how many students needed?

            if (students > m) {
                // Too many students needed -> capacity is too small
                low = mid + 1;
            } else {
                // Valid allocation -> try smaller max pages
                high = mid - 1;
            }
        }
        return low; // 'low' will be at the minimized max pages
    }

    // -----------------------------------------------------------
    // Driver Code (For Testing)
    // -----------------------------------------------------------
    public static void main(String[] args) {
        ArrayList<Integer> books = new ArrayList<>(Arrays.asList(12, 34, 67, 90));
        int n = books.size();
        int m = 2; // number of students

        System.out.println("Brute Force Answer: " + findPagesBrute(books, n, m));
        System.out.println("Optimal Answer: " + findPagesOptimal(books, n, m));
    }
}
