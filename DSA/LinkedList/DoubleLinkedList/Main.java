public class Main {
    public static void main(String[] args) {
        DoubleLL list = new DoubleLL();
        list.insertFirst(12);
        list.insertFirst(1);
        list.insertFirst(57);
        list.insertFirst(43);
        list.insertFirst(23);
        list.insertLast(100);
        list.insert(100, 65);
        list.display();
    }

}
