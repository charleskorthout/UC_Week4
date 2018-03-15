package Compression;

import Compression.Utils.FrequencyCounter;
import javafx.util.Pair;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * The Huffman text compressor compresses and decompresses a stream of elements a string of zeroes and ones
 */
public class HuffmanCompressor {


    /**
     * Decrypts a Huffman encoded text message into the original text
     * @param s The message to decompress
     * @return The uncompressed message
     */
    public static <T> Stream<T> decode() {
        return null;
        //TODO
    }

    /**
     *
     * @param elements the stream of elements to process
     * @param <T> Stores the compressed stream of elements
     */
    public static <T> void encode(Stream<T> elements) {
        if (elements == null) {
            throw new NullPointerException("Input text cannot be null.");
        }
        if (elements.count() == 0) {
            throw new IllegalArgumentException("The input text should at least have 1 character.");
        }
        final HuffmanNode root = buildTree(elements);
        final Map<Optional<T>, String> charCode = generateCodes(root);
        final String encodedMessage = encodeMessage(charCode, elements);
    }

    /**
     * Build a Huffman tree from a text
     * @param elements the elements to process
     * @return an ordered Huffman tree for the text
     */
    private static <T> HuffmanNode<T> buildTree(Stream<T> elements) {
        Queue<HuffmanNode> pq = new PriorityQueue<>();
        Iterator<Map.Entry<T,Long>> frequencies = FrequencyCounter.getFrequencies(elements).entrySet().iterator();
        while (frequencies.hasNext()){
            Map.Entry<T,Long> entry = frequencies.next();
            pq.add(HuffmanNode.from(entry.getKey(),entry.getValue()));
        }
        while (pq.size() > 1) {
            //Remove two smallest elements.
            HuffmanNode node1 = pq.poll();
            HuffmanNode node2 = pq.poll();
            // Combine into a single node with these two as its children.
            pq.add(HuffmanNode.combine(node1,node2));
        }
        // pq.size must be 1 so return the node
        return pq.poll();
    }

    /**
     * Generate a code map from the Huffman nodes
     * @param node The huffman root with nodes
     * @param <T> The type of the elements
     * @return A map with all the elements and their corresponding frequencies
     */
    private static <T> Map<Optional<T>, String> generateCodes(HuffmanNode node) {
        final Map<Optional<T>, String> map = new HashMap<Optional<T>, String>();
        generateCode(node, map, "");
        return map;
    }

    /**
     * Recusively generate the code for a specific node of the Huffman tree
     * @param node The node to create the code for
     * @param map A map with the code for all descendants of the node
     * @param s The code as a concatenated '0' / '1' string
     * @param <T> The type of the elements
     */
    private static <T> void generateCode(HuffmanNode node, Map<Optional<T>, String> map, String s) {
        if (node.left() == null && node.right() == null) {
            map.put(node.element(), s);
            return;
        }
        generateCode(node.left(), map, s + '0');
        generateCode(node.right(), map, s + '1' );
    }

    /**
     * encode the stream of elements into a string using the code table
     * @param map The code table for the elements
     * @param elements The elements
     * @param <T> The type of the elements
     * @return A string representing the huffman coding of the elements
     */
    private static <T> String encodeMessage(Map<Optional<T>, String> map, Stream<T> elements) {
        StringBuilder b = new StringBuilder();
        elements.forEach( (element) -> b.append(map.get(element)));
        return b.toString();
    }

    /**
     * Create a lookup map from the code table
     * @param codelist The list with codes
     * @param <T> The type of the elements
     * @return A Map with the codes and the elements to retrieve quickly the elements based on the provided code
     */
    private static <T> Map<String, Optional<T>> createReverseLookup(Map<Optional<T>, String> codelist) {
        return codelist.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

    /**
     * Decode the message string into a message stream
     * @param reverseMap The lookup table for codes and elements
     * @param codedmessage The coded message serialized as a string
     * @param <T> The type of the elements
     * @return A stream of decoded elements
     */
    private static <T> Stream<T> decodeMessage(Map<String, Optional<T>> reverseMap, String codedmessage) {
        List<Optional<T>> elements = new LinkedList<>();
        Iterator<Character> iterator =  codedmessage.chars().mapToObj(c -> (char)c).iterator();
        while (iterator.hasNext()){
            String prefixcode = "";
            // append the prefix till we find a match or we are at the end of the message
            while (!reverseMap.containsKey(prefixcode) && iterator.hasNext()) {
                prefixcode += iterator.next();
                if (reverseMap.containsKey(prefixcode)) {
                    ((LinkedList<Optional<T>>) elements).addLast(reverseMap.get(prefixcode));
                }
            }
            prefixcode = "";
        }
        // filter out all non empty elements
        return elements.stream()
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    /**
     * Convert a message of 0 and 1 into a bitset
     * @param codemessage The message containing a sequence of 0 and 1
     * @return the BitSet representation of the 0 and 1
     */
    private static BitSet toBits(String codemessage) {
        BitSet bits = new BitSet(codemessage.length());
        for (int i = 0; i < codemessage.length(); i++){ ///// TODO UGLY
            if (codemessage.charAt(i) == '1') bits.set(i);
        }
        return bits;
    }

    /**
     * convert a sequence of bits in to a message string of zeroes and ones
     * @param bits The sequence of bits
     * @return The converted message
     */
    private static String fromBits(BitSet bits) {
        String message = "";
        for (int i = 0; i < bits.length(); i++) { ///// TODO UGLY
            if (bits.get(i)) message += "1";
            else message += "0";
        }
        return message;
    }
}

