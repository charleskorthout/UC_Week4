package Huffman;

import java.util.Map;
import java.util.Set;

/**
 * Created by charles korthout on 4/2/2017.
 */
public class CharacterCounter {

     /**
     * Returns the set of characters seen along with their frequencies in a specific text.
     * @return set containing each character seen while counting frequencies
     */
    public static Set<Map.Entry<Character, Integer>> getCharacterCounts(String text) {
        FrequencyCounter<Character> counts = new FrequencyCounter<>();
        text.chars().forEach( (c) -> counts.increment((char)c));
        return counts.getCounts();
    }
}
