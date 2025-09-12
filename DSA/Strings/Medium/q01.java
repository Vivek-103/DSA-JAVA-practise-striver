import java.util.*;

public class q01{

    public static String frequencySort(String s) {
        // Step 1: Create a frequency map to count occurrences of each character
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        // Time complexity of this loop is O(n), where n is the length of the string

        // Step 2: Create a priority queue (max heap) that sorts characters by frequency
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
            (a, b) -> b.getValue() - a.getValue()
        );
        // Add all entries from the frequency map into the heap
        maxHeap.addAll(freqMap.entrySet());
        // Adding all entries takes O(k log k), where k is the number of unique characters

        // Step 3: Build the result string by removing elements from the heap one by one
        StringBuilder result = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> entry = maxHeap.poll();
            char character = entry.getKey();
            int frequency = entry.getValue();

            // Append the character 'frequency' number of times
            for (int i = 0; i < frequency; i++) {
                result.append(character);
            }
        }
        // Time complexity for building the result is O(n), since we append at most n characters

        return result.toString(); // Return the sorted string
    }

    public static void main(String[] args) {
        String input = "tree";
        String output = frequencySort(input);
        System.out.println(output); // Output could be "eert" or "eetr"
    }
}
