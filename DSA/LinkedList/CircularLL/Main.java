public class Main {
    public static void main(String[] args) {
        CircularLL list = new CircularLL();
        list.insert(23);
        list.insert(12);
        list.insert(82);
        list.insert(67);
        list.insert(32);
        list.display();
        list.delete(82);
        list.display();
    }
}
