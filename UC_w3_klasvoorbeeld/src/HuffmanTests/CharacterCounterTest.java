package HuffmanTests;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import static Huffman.CharacterCounter.getCharacterCounts;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by charl on 4/2/2017.
 */
class CharacterCounterTest {

    @org.junit.jupiter.api.Test
    void getCharacterCountsWhenAllCharactersAreSame() {
        String test = "aaaaaa";
        Set<Map.Entry<Character, Integer>> counts = getCharacterCounts(test);
        Iterator iterator = counts.iterator();
        int expected = 6;
        int actual = ((Entry<Character,Integer>) iterator.next()).getValue();
        assertEquals(expected,actual);
    }

    @org.junit.jupiter.api.Test
    void getCharacterCountsWhenAllCharactersAreDifferent() {
        String test = "abcdef";
        Set<Map.Entry<Character, Integer>> counts = getCharacterCounts(test);
        int expected = 6;
        int actual = counts.size();
        assertEquals(expected,actual);
    }
}