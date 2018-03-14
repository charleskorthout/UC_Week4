package Compression;

import Compression.Utils.CharacterCounter;
import Compression.Utils.FrequencyCounter;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
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
    public void decode() {

        //TODO
    }

    /**
     * encrypt a text to a compressed form, using the Huffman coding
     * @param s The text to compress
     * @return The encrypted string compressed by the Huffman compression algorithm
     */
    public void encode() {
        if (s == null) {
            throw new NullPointerException("Input text cannot be null.");
        }
        if (s.length() == 0) {
            throw new IllegalArgumentException("The input text should at least have 1 character.");
        }
        final Map<Character,Long> frequencies = CharacterCounter.getFrequencies(s);
//        final HuffmanNode root = buildTree(frequencies);
//        final Map<Character, String> charCode = generateCodes(frequencies, root);
//        final String encodedMessage = encodeMessage(charCode, s);
        //return null;
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


//            HuffmanNode tree = null;
//            if (frequencies.hasNext()) {
//                Map.Entry<Character,Integer> entry = (Map.Entry) frequencies.next();
//                tree = new HuffmanNode(entry.getKey(),entry.getValue(), null,null);
//            }
//            while (frequencies.hasNext()) {
//                Map.Entry<Character,Integer> entry = (Map.Entry) frequencies.next();
//                HuffmanNode node = new HuffmanNode(entry.getKey(),entry.getValue(), null,null);
//                tree.add(node);
//            }
//            return tree;
//        }
//
//        private void add(HuffmanNode node) {
//            if (this.compareTo(node) == 0) { // the frequencies are equal, so move it to the right..
//                // TODO
//            }
//            else if(this.compareTo(node) == 1) { // the frequency of the first is larger so move into the left branch
//                if (null == left) { // no left branch so add the node here
//                    left = node;
//                }
//                else add(node);
//            }
//            else if (null == right) { // we have no rightbranch yet, so put node 2 here
//                right = node;
//            }
//            else add(node);
//        }

    private static Map<Character, String> generateCodes(Map<Character,Long> frequencies, HuffmanNode node) {
        final Map<Character, String> map = new HashMap<Character, String>();
        generateCode(node, map, "");
        return map;
    }

    private static void generateCode(HuffmanNode node, Map<Character, String> map, String s) {
        if (node.left == null && node.right == null) {
            map.put(node.ch, s);
            return;
        }
        generateCode(node.left, map, s + '0');
        generateCode(node.right, map, s + '1' );
    }

}
}
