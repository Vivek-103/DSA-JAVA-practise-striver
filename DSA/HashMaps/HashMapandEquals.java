import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HashCodeAndEqualsMethod {
    public static void main(String[] args) { 
        // Creating a HashMap with custom object 'Person' as the key
        HashMap<Person, String> map = new HashMap<>();

        // Creating three Person objects
        Person p1 = new Person("Alice", 1);  // First entry
        Person p2 = new Person("Bob", 2);    // Second entry
        Person p3 = new Person("Alice", 1);  // Same content as p1, but a different object

        // Inserting the first person
        map.put(p1, "Engineer");

        // Inserting the second person
        map.put(p2, "Designer");

        // Inserting the third person — but p3 is equal to p1 (same name and id)
        // Because p1.equals(p3) == true AND p1.hashCode() == p3.hashCode(),
        // this will replace the existing value for p1 in the map
        map.put(p3, "Manager");

        // So at this point:
        // p1 and p3 are considered the same key → "Manager" replaces "Engineer"
        // p2 is a separate key

        System.out.println("HashMap Size: " + map.size()); 
        // Output: 2 — because p1 and p3 are treated as one key

        System.out.println("Value for p1: " + map.get(p1)); 
        // Output: Manager — value was replaced by p3

        System.out.println("Value for p3: " + map.get(p3)); 
        // Output: Manager — same as above since p3 == p1

        // Demonstrating same behavior with String keys (Strings already override equals & hashCode)
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("Shubham", 90); // First put
        map1.put("Neha", 92);    // Second put
        map1.put("Shubham", 99); // Key "Shubham" exists → value updated

        // Key "Shubham" appears only once in the map with updated value 99
    }

}

// Custom class used as key in the HashMap
class Person {
    private String name;
    private int id;

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    // Overriding hashCode so that two persons with same name and id have same hash code
    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    // Overriding equals to define equality based on 'name' and 'id'
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // same memory reference
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Person other = (Person) obj;
        return id == other.getId() && Objects.equals(name, other.getName());
    }

    // Overriding toString for readable object representation (not required but helpful)
    @Override
    public String toString() {
        return "id: " + id + ", name: " + name;
    }
}
