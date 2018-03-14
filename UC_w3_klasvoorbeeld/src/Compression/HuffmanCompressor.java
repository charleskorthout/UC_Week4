package Compression;

import Compression.Utils.FrequencyCounter;
import java.util.*;
import java.util.stream.Stream;

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
//        final Map<Character, String> charCode = generateCodes(frequencies, root);
//        final String encodedMessage = encodeMessage(charCode, s);
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

    private static <T> Map<T, String> generateCodes(Map<T,Long> frequencies, HuffmanNode node) {
        final Map<T, String> map = new HashMap<T, String>();
        generateCode(node, map, "");
        return map;
    }

    private static <T> void generateCode(HuffmanNode node, Map<T, String> map, String s) {
        if (node.left() == null && node.right() == null) {
            map.put(node.element(), s); //TODO
            return;
        }
        generateCode(node.left(), map, s + '0');
        generateCode(node.right(), map, s + '1' );
    }

}
}
