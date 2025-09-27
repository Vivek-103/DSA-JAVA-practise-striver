// Class name: RemoveDuplicatesSortedDLL
public class RemoveDuplicatesSortedDLL {

    // ---------------- Doubly Linked List Node ----------------
    static class Node {
        int val;       // node value
        Node next;     // pointer to next node
        Node prev;     // pointer to previous node
        Node(int val) { this.val = val; }
    }

    // ---------------- Approach 1: Brute Force ----------------
    // Compare each node with next nodes and remove duplicates
    // Time Complexity: O(n) since list is sorted, only one pass needed
    // Space Complexity: O(1)
    public static Node removeDuplicates(Node head) {
        if (head == null) return null;

        Node curr = head;

        // Traverse the list
        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) { // duplicate found
                Node duplicate = curr.next;
                curr.next = duplicate.next; // skip duplicate

                if (duplicate.next != null) { // update prev pointer
                    duplicate.next.prev = curr;
                }
                // duplicate node will be garbage collected
            } else {
                curr = curr.next; // move to next node if no duplicate
            }
        }

        return head; // return updated list
    }

    // ---------------- Utility: Print DLL ----------------
    public static void printList(Node head) {
        Node curr = head;
        System.out.print("DLL: ");
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    // ---------------- Helper: Build DLL from Array ----------------
    public static Node buildDLL(int[] arr) {
        if (arr.length == 0) return null;
        Node head = new Node(arr[0]);
        Node tail = head;
        for (int i = 1; i < arr.length; i++) {
            Node newNode = new Node(arr[i]);
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        return head;
    }

    // ---------------- Main Method: Example ----------------
    public static void main(String[] args) {
        int[] vals = {1, 2, 2, 3, 3, 3, 4, 5, 5};
        Node head = buildDLL(vals);

        System.out.println("Original Sorted Doubly Linked List:");
        printList(head);

        head = removeDuplicates(head);

        System.out.println("After Removing Duplicates:");
        printList(head);
    }
}
