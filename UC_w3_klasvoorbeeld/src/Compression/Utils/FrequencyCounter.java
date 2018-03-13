package Compression.Utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by charles korthout on 4/2/2017.
 * March 12 2018 Refactored the Hashmap to streams
 */
public class FrequencyCounter<T extends Comparable<T>> {

    /**
     * Calcualte the frequencies of elements in a stream
     * @param stream The stream of elements
     * @return A map with elements and their frequencies
     */
    public final Map<T,Long> getFrequencies(Stream<T> stream) {
        Map<T, Long> frequencies = new LinkedHashMap<>();
        // Group the elements in a stream based on there identity and count the occurences
        stream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                                 .entrySet()
                                 .stream()
                                 .sorted(Map.Entry.<T, Long>comparingByValue())
                                 .forEachOrdered(e -> frequencies.put(e.getKey(), e.getValue()));;
        return frequencies;
    }
}
