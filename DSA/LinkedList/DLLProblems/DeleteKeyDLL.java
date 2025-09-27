// Class name: DeleteKeyDLL
public class DeleteKeyDLL {

    // ---------------- Doubly Linked List Node ----------------
    static class Node {
        int val;        // node value
        Node next;      // pointer to next node
        Node prev;      // pointer to previous node
        Node(int val) { this.val = val; }
    }

    // ---------------- Approach 1: Brute Force ----------------
    // Traverse the list, remove nodes by relinking previous and next
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static Node deleteAllKey(Node head, int key) {
        if (head == null) return null;

        Node curr = head;

        // Traverse the list
        while (curr != null) {
            if (curr.val == key) {
                Node prevNode = curr.prev;
                Node nextNode = curr.next;

                // If deleting head
                if (prevNode != null) {
                    prevNode.next = nextNode;
                } else {
                    head = nextNode; // update head
                }

                // Update next node's prev pointer if exists
                if (nextNode != null) {
                    nextNode.prev = prevNode;
                }
            }
            curr = curr.next;
        }

        return head; // return updated list
    }

    // ---------------- Utility: Print Doubly Linked List ----------------
    public static void printList(Node head) {
        Node curr = head;
        System.out.print("Forward: ");
        Node tail = null;

        // Print forward
        while (curr != null) {
            System.out.print(curr.val + " ");
            if (curr.next == null) tail = curr; // store last node for backward print
            curr = curr.next;
        }
        System.out.println();

        // Print backward using tail
        System.out.print("Backward: ");
        while (tail != null) {
            System.out.print(tail.val + " ");
            tail = tail.prev;
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
        int[] vals = {1, 2, 3, 2, 4, 2, 5};
        Node head = buildDLL(vals);

        System.out.println("Original Doubly Linked List:");
        printList(head);

        int key = 2;
        head = deleteAllKey(head, key);

        System.out.println("After Deleting all occurrences of " + key + ":");
        printList(head);
    }
}
