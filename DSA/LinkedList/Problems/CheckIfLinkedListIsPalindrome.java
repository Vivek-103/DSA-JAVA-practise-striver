// Class to check if a linked list is palindrome
public class CheckIfLinkedListIsPalindrome {

    // Node class representing a linked list node
    static class Node {
        int data;   // Value stored in this node
        Node next;  // Pointer to the next node in the list

        // Constructor to create a new node with data and next as null
        Node(int data) {
            this.data = data;
            this.next = null;
        }

        // Constructor to create a new node with data and next pointer
        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    // ---------------------------
    // 1. BRUTE FORCE APPROACH
    // ---------------------------
    public static boolean isPalindromeBruteForce(Node head) {
        // Create a list to store values of linked list nodes
        java.util.ArrayList<Integer> list = new java.util.ArrayList<>();

        // Temporary pointer to traverse the linked list
        Node temp = head;

        // Traverse the linked list and store all node values in the ArrayList
        while (temp != null) {
            list.add(temp.data);  // Add current node's value to list
            temp = temp.next;     // Move to next node
        }

        // Initialize two pointers for start and end of ArrayList
        int start = 0;
        int end = list.size() - 1;

        // Compare elements from start and end moving towards the center
        while (start < end) {
            // If values at start and end are different, not a palindrome
            if (!list.get(start).equals(list.get(end))) {
                return false;  // Not a palindrome
            }
            start++;  // Move start pointer forward
            end--;    // Move end pointer backward
        }

        // All elements matched → linked list is palindrome
        return true;
    }

    // ---------------------------
    // 2. OPTIMAL APPROACH
    // ---------------------------
    public static boolean isPalindromeOptimal(Node head) {
        // Edge case: empty list or single node → always palindrome
        if (head == null || head.next == null) return true;

        // Step 1: Find the middle of the linked list
        Node slow = head;  // Moves one step at a time
        Node fast = head;  // Moves two steps at a time

        // Traverse until fast reaches end of list
        while (fast != null && fast.next != null) {
            slow = slow.next;       // Move slow one step
            fast = fast.next.next;  // Move fast two steps
        }
        // Now slow points to the middle node (second middle if even length)

        // Step 2: Reverse the second half of the linked list
        Node secondHalf = reverseList(slow);  // Reverse from middle to end
        Node firstHalf = head;                 // Start pointer for first half

        // Step 3: Compare first half and reversed second half
        Node tempSecond = secondHalf;  // Temp pointer to traverse second half
        boolean isPalindrome = true;   // Flag to store result

        while (tempSecond != null) {
            // If values do not match → not a palindrome
            if (firstHalf.data != tempSecond.data) {
                isPalindrome = false;
                break;  // No need to continue
            }
            firstHalf = firstHalf.next;    // Move first half pointer
            tempSecond = tempSecond.next;  // Move second half pointer
        }

        // Step 4 (Optional): Restore the original linked list
        reverseList(secondHalf);

        return isPalindrome;  // Return result
    }

    // Helper function to reverse a linked list
    private static Node reverseList(Node head) {
        Node prev = null;  // Previous node initially null
        Node curr = head;  // Current node starts at head
        Node next = null;  // Next node placeholder

        while (curr != null) {       // Traverse until end
            next = curr.next;        // Store next node
            curr.next = prev;        // Reverse current node's pointer
            prev = curr;             // Move prev forward
            curr = next;             // Move curr forward
        }

        return prev;  // New head of reversed list
    }

    // Helper function to print linked list
    public static void printList(Node head) {
        Node temp = head;               // Temp pointer to traverse list
        while (temp != null) {          // Traverse until end
            System.out.print(temp.data + " -> ");  // Print node value
            temp = temp.next;           // Move to next node
        }
        System.out.println("null");     // End of list
    }

    // MAIN method to test palindrome check
    public static void main(String[] args) {
        // Example 1: Palindrome list 1 -> 2 -> 3 -> 2 -> 1
        Node head1 = new Node(1,
                        new Node(2,
                            new Node(3,
                                new Node(2,
                                    new Node(1, null)))));

        System.out.println("Linked List 1:");
        printList(head1);
        System.out.println("Brute Force palindrome: " + isPalindromeBruteForce(head1));
        System.out.println("Optimal palindrome: " + isPalindromeOptimal(head1));

        // Example 2: Not a palindrome 10 -> 20 -> 30 -> 40
        Node head2 = new Node(10,
                        new Node(20,
                            new Node(30,
                                new Node(40, null))));

        System.out.println("\nLinked List 2:");
        printList(head2);
        System.out.println("Brute Force palindrome: " + isPalindromeBruteForce(head2));
        System.out.println("Optimal palindrome: " + isPalindromeOptimal(head2));
    }
}

/*
-----------------------------------------
TIME AND SPACE COMPLEXITY:

1. Brute Force:
   - Time: O(N) → traverse all nodes to copy values
   - Space: O(N) → store values in ArrayList

2. Optimal Approach:
   - Time: O(N) → find middle + reverse second half + compare
   - Space: O(1) → only pointers used, no extra memory

-----------------------------------------
*/
