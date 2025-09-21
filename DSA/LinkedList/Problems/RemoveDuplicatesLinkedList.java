import java.util.HashSet;

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;          // Value stored in node
    ListNode next;    // Pointer to next node

    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class RemoveDuplicatesLinkedList {

    /**************************
     * 1️⃣ Remove duplicates from SORTED list
     * Deletes all nodes with duplicate values, leaving only distinct nodes.
     **************************/
    public ListNode deleteDuplicatesSorted(ListNode head) {
        // Dummy node helps handle duplicates at the head
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;    // Points to last distinct node
        ListNode current = head;  // Used to traverse

        while (current != null) {
            // Check if current node has duplicates
            if (current.next != null && current.val == current.next.val) {
                // Skip all duplicates
                while (current.next != null && current.val == current.next.val) {
                    current = current.next;
                }
                // Connect prev.next to the node after last duplicate
                prev.next = current.next;
            } else {
                // Current node is distinct → move prev forward
                prev = prev.next;
            }
            current = current.next; // Move to next node
        }

        return dummy.next; // New head of modified list
    }

    /**************************
     * 2️⃣ Remove duplicates from UNSORTED list
     * Keeps only the first occurrence of each value.
     **************************/
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        if (head == null) return null;

        HashSet<Integer> seen = new HashSet<>(); // Track seen values
        ListNode current = head;
        ListNode prev = null;

        while (current != null) {
            if (seen.contains(current.val)) {
                // Duplicate → skip current node
                prev.next = current.next;
            } else {
                // First occurrence → add to set
                seen.add(current.val);
                prev = current;
            }
            current = current.next; // Move forward
        }

        return head; // Head remains same
    }

    /**************************
     * Utility: Insert node at end
     **************************/
    public ListNode insert(ListNode head, int val) {
        ListNode newNode = new ListNode(val);
        if (head == null) return newNode;

        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        return head;
    }

    /**************************
     * Utility: Display list
     **************************/
    public void display(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }
        System.out.println("END");
    }

    /**************************
     * Main method: Test both methods
     **************************/
    public static void main(String[] args) {
        RemoveDuplicatesLinkedList obj = new RemoveDuplicatesLinkedList();

        // ===== Test SORTED list =====
        ListNode sortedHead = null;
        sortedHead = obj.insert(sortedHead, 1);
        sortedHead = obj.insert(sortedHead, 2);
        sortedHead = obj.insert(sortedHead, 3);
        sortedHead = obj.insert(sortedHead, 3);
        sortedHead = obj.insert(sortedHead, 4);
        sortedHead = obj.insert(sortedHead, 4);
        sortedHead = obj.insert(sortedHead, 5);

        System.out.println("Original SORTED List:");
        obj.display(sortedHead);

        sortedHead = obj.deleteDuplicatesSorted(sortedHead);

        System.out.println("After Removing All Duplicates (Sorted List):");
        obj.display(sortedHead);

        // ===== Test UNSORTED list =====
        ListNode unsortedHead = null;
        unsortedHead = obj.insert(unsortedHead, 10);
        unsortedHead = obj.insert(unsortedHead, 20);
        unsortedHead = obj.insert(unsortedHead, 20);
        unsortedHead = obj.insert(unsortedHead, 30);
        unsortedHead = obj.insert(unsortedHead, 40);
        unsortedHead = obj.insert(unsortedHead, 30);
        unsortedHead = obj.insert(unsortedHead, 10);

        System.out.println("Original UNSORTED List:");
        obj.display(unsortedHead);

        unsortedHead = obj.deleteDuplicatesUnsorted(unsortedHead);

        System.out.println("After Removing Duplicates (Unsorted List):");
        obj.display(unsortedHead);
    }
}

/*
========================
Time & Space Complexity:

1️⃣ Sorted List:
- Time Complexity: O(n) → traverse once
- Space Complexity: O(1) → only pointers used

2️⃣ Unsorted List:
- Time Complexity: O(n) → traverse once
- Space Complexity: O(n) → HashSet stores seen values
========================
*/
