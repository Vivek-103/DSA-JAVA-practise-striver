/*
    Problem: Implement a Doubly Linked List (DLL) in Java
    - Support insertion at head, tail, and after a given node
    - Support deletion (first, last, by index, by value)
    - Support traversal forward & backward
    - Include helper methods (find, display)

    Example Usage:
    DoubleLL dll = new DoubleLL();
    dll.insertFirst(10);
    dll.insertFirst(20);
    dll.insertLast(30);
    dll.insert(20, 25);   // insert 25 after 20
    dll.display();

    dll.deleteFirst();    // deletes 20
    dll.deleteLast();     // deletes 30
    dll.deleteIndex(1);   // deletes value at index 1
    dll.deleteValue(25);  // deletes node with value 25
*/

public class DoubleLL {

    private Node head; // head pointer of DLL

    // ================= INSERTION METHODS =================

    // Insert at the beginning
    // Time Complexity: O(1), Space Complexity: O(1)
    public void insertFirst(int val){
        Node node = new Node(val);  // create new node
        node.next = head;           // new node points to old head
        node.prev = null;           // since it's first node, prev = null

        if(head != null){
            head.prev = node;       // old head's prev now points to new node
        }
        head = node;                // update head to new node
    }

    // Insert at the end
    // Time Complexity: O(n), Space Complexity: O(1)
    public void insertLast(int val){
        Node node = new Node(val);
        node.next = null;           // last node's next = null

        if(head == null){           // if DLL is empty
            node.prev = null;       // first node has prev = null
            head = node;            // head becomes this node
            return;
        }

        Node last = head;           // start from head
        while(last.next != null){   // traverse to the end
            last = last.next;
        }
        last.next = node;           // connect last node to new node
        node.prev = last;           // new node's prev points to old last
    }

    // Insert after a given value
    // Time Complexity: O(n), Space Complexity: O(1)
    public void insert(int after , int val){
        Node p = find(after);       // find node with value "after"

        if(p == null){              // if not found
            System.out.println("Value " + after + " does not exist!");
            return;
        }

        Node node = new Node(val);  // create new node
        node.next = p.next;         // new node points to "after.next"
        p.next = node;              // "after.next" updated to new node
        node.prev = p;              // new node's prev points to "after"

        if(node.next != null){      // if not inserting at end
            node.next.prev = node;  // update next node's prev pointer
        }
    }

    // ================= DELETION METHODS =================

    // Delete first node
    // Time Complexity: O(1), Space Complexity: O(1)
    public void deleteFirst(){
        if(head == null){           // if empty list
            System.out.println("List is empty!");
            return;
        }
        head = head.next;           // move head to next node
        if(head != null){           // if list not empty after deletion
            head.prev = null;       // new head's prev must be null
        }
    }

    // Delete last node
    // Time Complexity: O(n), Space Complexity: O(1)
    public void deleteLast(){
        if(head == null){           // if empty list
            System.out.println("List is empty!");
            return;
        }
        if(head.next == null){      // only one element
            head = null;
            return;
        }
        Node last = head;
        while(last.next != null){   // go till last node
            last = last.next;
        }
        last.prev.next = null;      // remove last node by nullifying prev.next
    }

    // Delete node at a given index (0-based)
    // Time Complexity: O(n), Space Complexity: O(1)
    public void deleteIndex(int index){
        if(head == null){
            System.out.println("List is empty!");
            return;
        }
        if(index == 0){             // if deleting first node
            deleteFirst();
            return;
        }
        Node temp = head;
        int count = 0;
        while(temp != null && count < index){
            temp = temp.next;
            count++;
        }
        if(temp == null){           // index out of range
            System.out.println("Index out of range!");
            return;
        }
        if(temp.next != null){      // if not last node
            temp.next.prev = temp.prev;
        }
        if(temp.prev != null){      // if not first node
            temp.prev.next = temp.next;
        }
    }

    // Delete node by value
    // Time Complexity: O(n), Space Complexity: O(1)
    public void deleteValue(int val){
        Node temp = head;

        while(temp != null && temp.val != val){
            temp = temp.next;
        }

        if(temp == null){           // value not found
            System.out.println("Value " + val + " not found!");
            return;
        }

        if(temp == head){           // if it's the head node
            deleteFirst();
            return;
        }
        if(temp.next != null){      // if not last node
            temp.next.prev = temp.prev;
        }
        if(temp.prev != null){      // if not first node
            temp.prev.next = temp.next;
        }
    }

    // ================= HELPER METHODS =================

    // Find a node by value
    // Time Complexity: O(n), Space Complexity: O(1)
    public Node find(int value){
        Node node = head;
        while(node != null){
            if(node.val == value){
                return node;        // return node if found
            }
            node = node.next;
        }
        return null;                // not found
    }

    // Display the DLL in forward and reverse order
    // Time Complexity: O(n), Space Complexity: O(1)
    public void display(){
        Node node = head;
        Node last = null;

        // Forward traversal
        while(node != null){
            System.out.print(node.val + " -> ");
            last = node;            // keep updating last to reach tail
            node = node.next;
        }
        System.out.println("END");

        // Reverse traversal
        System.out.println("Print in Reverse:");
        while(last != null){
            System.out.print(last.val + " -> ");
            last = last.prev;
        }
        System.out.println("START");
    }

    // ================= NODE CLASS =================
    private class Node{
        int val;
        Node next;
        Node prev;

        public Node (int val){
            this.val = val;
        }
        public Node(int val, Node next, Node prev){
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }
}

