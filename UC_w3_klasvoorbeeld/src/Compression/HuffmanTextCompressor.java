package Compression;

import Compression.Utils.CharacterCounter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

/**
 * The Huffman text compressor compresses and decompresses a text to/from a string of zeroes and ones
 */
public class HuffmanTextCompressor implements Encoder<String, String>, Decoder<String,String> {

    /**
     * Decrypts a Huffman encoded text message into the original text
     * @param s The message to decompress
     * @return The uncompressed text
     */
    @Override
    public String decode(String s) {

        return null;
    }

    /**
     * encrypt a text to a compressed form, using the Huffman coding
     * @param s The text to compress
     * @return The encrypted string compressed by the Huffman compression algorithm
     */
    @Override
    public String encode(String s) {
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
        return null;
    }

    /**
     * A tree structure to hold the huffman characters and frequencies
     */
    private static class HuffmanNode implements Comparable<HuffmanNode> {
        private final Character ch;
        private final int freq;
        private HuffmanNode left, right;

        HuffmanNode(char ch, int freq, HuffmanNode left, HuffmanNode right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        /**
         * The Huffman node is a leaf node if both substrees are null
         * @return the logical result of both subtrees being null
         */
        private boolean isLeaf() {
            return (left == null) && (right == null);
        }

        /**
         * The compare of the nodes is based on the frequencies
         * @param that The node to compare with
         * @return the comapre result
         */
        public int compareTo(HuffmanNode that) {
            return this.freq - that.freq;
        }

        /**
         * Build a Huffman tree from a text
         * @param text The text to code
         * @return an ordered Huffman tree for the text
         */
        private static HuffmanNode buildTree(String text){
            Map<Character,Long> frequencies = CharacterCounter.getFrequencies(text);
            HuffmanNode tree = null;
            /*if (iterator.hasNext()) {
                Map.Entry<Character,Integer> entry = (Map.Entry) iterator.next();
                tree = new HuffmanNode(entry.getKey(),entry.getValue(), null,null);
            }
            while (iterator.hasNext()) {
                Map.Entry<Character,Integer> entry = (Map.Entry) iterator.next();
                HuffmanNode node = new HuffmanNode(entry.getKey(),entry.getValue(), null,null);
                tree.add(node);
            }*/
            return tree;
        }

//        private void add(HuffmanNode node) {
//            if (this.compareTo(node) == 0) { // the frequencies are equal
//                this.chars.addAll(node.getCharacters()); // add all he characters to the list of characters with the same frequency
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

        private static Map<Character, String> generateCodes(Map<Character,Integer> frequencies, HuffmanNode node) {
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
