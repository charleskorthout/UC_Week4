package Compression.Utils;

import java.util.Map;

/**
 * Created by charles korthout on 4/2/2017.
 * March 2018, Refactored to use the FrequencyCounter refactored getFrequencies
 */
public class CharacterCounter {

    /**
     * Gets the characters and the frequency from a text
     * @param text The text with characters
     * @return A map of characters and their frequencies in descending sorted order
     */
    public final static Map<Character,Long> getFrequencies(String text) {
         FrequencyCounter counter = new FrequencyCounter();
         return counter.getFrequencies(text.chars().mapToObj(i-> (char)(i)));
    }
}
