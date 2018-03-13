package CompressionTests;

import Compression.Utils.CharacterCounter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

class CharacterCounterTest {

    /**
     * Check if an collection is sorted in descending order
     * @param iterator the collection that can be iterated
     * @param <T> The element
     * @return true if sorted, false otherwise
     */
    private static <T extends Comparable<? super T>> boolean isSorted(Iterator<T> iterator) {
        if (!iterator.hasNext()) {
            return true;
        }
        T t = iterator.next();
        while (iterator.hasNext()) {
            T t2 = iterator.next();
            if (t.compareTo(t2) > 0) {
                return false;
            }
            t = t2;
        }
        return true;
    }

    /**
     * Check if a stream is sorted
     * @param stream the stream of elements
     * @param <T> The type of elements
     * @return true if the stream is sorted, false otherwise
     */
    private static <T extends Comparable<? super T>> boolean isSorted(Stream<T> stream) {
        return CharacterCounterTest.isSorted(stream.iterator());
    }

    /**
     * Test that identical characters give the appropriate count
     */
    @Test
    void getCharacterCountsWhenAllCharactersAreSame() {
        String test = "aaaaaa";
        Map<Character, Long> counts = CharacterCounter.getFrequencies(test);
        System.out.println(counts);
        int expected = 1;
        int actual = counts.size();
        assertEquals(expected,actual, "Expexting a frequency of 6");
    }

    /**
     * Test that different characters give the appropriate count
     */
    @Test
    void getCharacterCountsWhenAllCharactersAreDifferent() {
        String test = "abcdef";
        Map<Character, Long> counts = CharacterCounter.getFrequencies(test);
        System.out.println(counts);
        long expected = 6;
        long actual = counts.size();
        assertEquals(expected,actual, "Expexting a frequency of 6");
    }

    /**
     * Test that different characters give the appropriate count
     */
    @Test
    void getCharacterCountsWhenBothEqualAndDifferentCharacters() {
        String test = "aaabbc";
        Map<Character, Long> counts = CharacterCounter.getFrequencies(test);
        System.out.println(counts);
        long expected = 3;
        long actual = counts.size();
        assertEquals(expected,actual, "Expexting a frequency of 3");
    }

    /**
     * Test that different characters give the appropriate count
     */
    @Test
    void checkIfStreamIsSorted() {
        String test = "aaabbc";
        Map<Character, Long> counts = CharacterCounter.getFrequencies(test);
        System.out.println(counts);
        boolean expected = true;
        boolean actual = CharacterCounterTest.isSorted(counts.values().stream());
        assertEquals(expected,actual, "Expecting a sorted stream");
    }
}