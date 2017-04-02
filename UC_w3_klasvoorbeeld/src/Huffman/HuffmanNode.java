package Huffman;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.Map.Entry;

/**
 * Created by charles korthout on 4/2/2017.
 */
public class HuffmanNode implements Comparable<HuffmanNode>{
    List<Character> chars; // hold the list of all different characters that have the same frequency count
    Integer frequency; // the frequency count
    HuffmanNode leftbranch; // the nodes with frequency counts that are smaller than value
    HuffmanNode rightbranch; // the nodes with frequency counts that are larger than value.

    /**
     * De default constructor.
     * @param character The character to add
     * @param frequency The frequency count
     * @param left the bracnh with values that are smaller than the current frequency count
     * @param right The branch with values that are larger than the current frequency count.
     */
    HuffmanNode(char character, int frequency,  HuffmanNode left,  HuffmanNode right) {
        this.chars = new ArrayList<>();
        chars.add(character);
        this.frequency = frequency;
        this.leftbranch = left;
        this.rightbranch = right;
    }

    List<Character> getCharacters() {
        return chars;
    }

    int getFrequency() {
        return frequency;
    }

    @Override
    public int compareTo(HuffmanNode node) {
            return frequency - node.frequency; // The old integer compare trick...
    }

    @Override
    public boolean equals(Object o) {
        if (null==o) return false;
        if (!(o instanceof HuffmanNode)) return false;
        HuffmanNode node = (HuffmanNode) o;
        return frequency == node.frequency;
    }

    @Override
    public int hashCode(){
        return frequency.hashCode();
    }

    private void add(HuffmanNode node) {
        if (this.compareTo(node) == 0) { // the frequencies are equal
            this.chars.addAll(node.getCharacters()); // add all he characters to the list of characters with the same frequency
        }
        else if(this.compareTo(node) == 1) { // the frequency of the first is larger so move into the left branch
                if (null == leftbranch) { // no left branch so add the node here
                    leftbranch = node;
                }
                else add(node);
             }
             else if (null == rightbranch) { // we have no rightbranch yet, so put node 2 here
                        rightbranch = node;
                  }
                  else add(node);

    }

    /**
     * Build a Huffman tree from a text
     * @param text The text to code
     * @return an ordered Huffman tree for the text
     */
    public static HuffmanNode buildTree(String text){
        // TODO check if text is not empty...
        Set<Map.Entry<Character, Integer>> counts = CharacterCounter.getCharacterCounts(text);
        HuffmanNode tree = null;
        Iterator iterator = counts.iterator();
        if (iterator.hasNext()) {
            Entry<Character,Integer> entry = (Entry) iterator.next();
            tree = new HuffmanNode(entry.getKey(),entry.getValue(), null,null);
        }
        while (iterator.hasNext()) {
            Entry<Character,Integer> entry = (Entry) iterator.next();
            HuffmanNode node = new HuffmanNode(entry.getKey(),entry.getValue(), null,null);
            tree.add(node);
        }
        return tree;
    }

    /**
     * encrypt a text to a compressed form, using the Huffman coding
     * @param text The Text to compress
     * @return The encrypted string compressed by the Huffman compression algorithm
     */
    public static String encrypt(String text) {
        // TODO
        throw new NotImplementedException();
    }

    /**
     * Decrypts a Huffman encoded text message into the original text
     * @param codedText The message to decompress
     * @return The uncompressed text
     */
    public static String decrypt(String codedText) {
        // TODO
        throw new NotImplementedException();
    }

}
