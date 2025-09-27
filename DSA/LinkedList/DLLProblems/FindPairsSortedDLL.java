// Class name: FindPairsSortedDLL
public class FindPairsSortedDLL {

    // ---------------- Doubly Linked List Node ----------------
    static class Node {
        int val;       // value of node
        Node next;     // pointer to next node
        Node prev;     // pointer to previous node
        Node(int val) { this.val = val; }
    }

    // ---------------- Approach 1: Brute Force ----------------
    // Check all pairs in DLL
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    public static void findPairsBrute(Node head, int target) {
        Node first = head;

        System.out.println("Pairs (Brute Force) for target " + target + ":");

        while (first != null) {
            Node second = first.next;
            while (second != null) {
                if (first.val + second.val == target) {
                    System.out.println("(" + first.val + ", " + second.val + ")");
                }
                second = second.next;
            }
            first = first.next;
        }
    }

    // ---------------- Approach 2: Optimal Two-Pointer ----------------
    // Since DLL is sorted, use two pointers: start and end
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static void findPairsOptimal(Node head, int target) {
        if (head == null) return;

        Node start = head;
        Node end = head;

        // Move end pointer to last node
        while (end.next != null) {
            end = end.next;
        }

        System.out.println("Pairs (Optimal Two-Pointer) for target " + target + ":");

        // Two pointer loop
        while (start != null && end != null && start != end && end.next != start) {
            int sum = start.val + end.val;
            if (sum == target) {
                System.out.println("(" + start.val + ", " + end.val + ")");
                start = start.next;   // move both pointers inward
                end = end.prev;
            } else if (sum < target) {
                start = start.next;   // increase sum by moving start forward
            } else {
                end = end.prev;       // decrease sum by moving end backward
            }
        }
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

    // ---------------- Utility: Print Doubly Linked List ----------------
    public static void printList(Node head) {
        Node curr = head;
        System.out.print("DLL: ");
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    // ---------------- Main Method: Examples ----------------
    public static void main(String[] args) {
        int[] vals = {1, 2, 3, 4, 5, 6, 7};
        Node head = buildDLL(vals);

        System.out.println("Sorted Doubly Linked List:");
        printList(head);

        int target = 8;
        findPairsBrute(buildDLL(vals), target);      // Brute force approach
        findPairsOptimal(buildDLL(vals), target);    // Optimal two-pointer approach

        // Example 2: target = 10
        int target2 = 10;
        findPairsOptimal(buildDLL(vals), target2);
    }
}
