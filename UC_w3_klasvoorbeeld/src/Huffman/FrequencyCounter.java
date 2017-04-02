package Huffman;

import java.util.HashMap;
import java.util.Set;
import java.util.Map;

/**
 * Created by charles korthout on 4/2/2017.
 */
public class FrequencyCounter<T extends Comparable<T>> {

    private final HashMap<T, Integer> frequencies = new HashMap<>();

    /**
    * Increments the count of the given type,
    * setting it to one on first appearance.
    * @param t the type to count
    */
    public void increment(T t) {
        Integer freq = frequencies.get(t);
        if (freq == null) {
            frequencies.put(t, 1);
        } else {
            frequencies.put(t, freq + 1);
        }
    }


    /**
     * Returns the set of types seen along with their frequencies.
     * @return set containing each type seen while counting frequencies
     */
    public Set<Map.Entry<T, Integer>> getCounts() {
        return frequencies.entrySet();
    }

}
