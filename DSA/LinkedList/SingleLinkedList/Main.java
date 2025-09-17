public class Main{
    public static void main(String[] args){
        SingleLL list = new SingleLL();
        list.insertFirst(3);
        list.insertFirst(2);
        list.insertFirst(23);
        list.insertFirst(31);
        list.insertFirst(17);
        list.insertLast(97);
        list.insert(100, 3);
        list.display();
        System.out.println();
        System.out.println(list.deleteFirst());
        System.out.println(list.deleteLast());
        System.out.println(list.delete(2));
        System.out.println(list.find(23));
        list.display();

    }
}