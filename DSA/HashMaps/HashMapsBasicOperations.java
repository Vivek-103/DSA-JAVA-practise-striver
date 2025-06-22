import java.util.*;

class ex1{
    public static void main(String[] args) {
        // Create a HashMap
        HashMap<Integer,String> map= new HashMap<>();
        map.put(1,"akshit");
        map.put(2,"neha");
        map.put(3,"kunal");
        System.out.println(map);
        // map.put is used to store value

        String student = map.get(3);// used to retreive information from MAP.
        System.out.println(student);


       System.out.println(map.containsKey(2));// tells us if there is any key available in MAP. 
       System.out.println(map.containsValue("neha"));// tells us if there is any value available

       // FOR LOOPING IN HASHMAPS map.keySet();
       Set <Integer> keys = map.keySet();// here we can also remove this and ⬇️
        for (int i : keys){// for (int i : map.keySet){} ➡️we can skip previous line and write this directly also
            System.out.println(map.get(i));
        }
       
        Set<Map.Entry<Integer,String>> entries = map.entrySet();// another way for looping but this will give u both value key and string

        for(Map.Entry<Integer,String> entry: entries){// here u can extract both key and value in an easy way 
            System.out.println(entry.getKey()+ ":" + entry.getValue());
            entry.setValue(entry.getValue().toUpperCase());
        }
        System.out.println(map);

        map.remove(2);// removes the value from map
        System.out.println(map);

     }
}
