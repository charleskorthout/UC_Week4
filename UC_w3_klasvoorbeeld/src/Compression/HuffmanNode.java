package Compression;

import java.util.Optional;

/**
 * A tree structure to hold the huffman elements and frequencies
 */
public class HuffmanNode<T> implements Comparable<HuffmanNode<T>> {
    private final Optional<T> element;
    private final Long freq;
    private HuffmanNode left, right;

    private HuffmanNode(Optional<T> element, Long freq, HuffmanNode<T> left, HuffmanNode<T> right) {
        this.element = element;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public HuffmanNode left() { return left; }
    public HuffmanNode right() { return right; }
    public Long frequency() { return freq;}
    public Optional<T> element() { return element;}

    /**
     * Create a Huffman node from the element and the frequency
     * @param element The element
     * @param freq The total occurences of this element
     * @param <T> The type of the element
     * @return A Huffman node with the element and frequencies and empty subtrees
     */
    public static <T> HuffmanNode<T> from(T element, Long freq) {
        return new HuffmanNode(Optional.of(element), freq, null, null);
    }

    /**
     * Combines two Huffman nodes into a single node, and adds the frequencies of the subnodes
     * @param left The left node
     * @param right The right node
     * @param <T> The type of the element
     * @return A Huffman Node that is build from the two subnodes
     */
    public static <T> HuffmanNode<T> combine(HuffmanNode<T> left, HuffmanNode<T> right) {
        return new HuffmanNode(Optional.empty(), left.frequency() + right.frequency(), left, right);
    }
    /**
     * The Huffman node is a leaf node if both substrees are null
     *
     * @return the logical result of both subtrees being null
     */
    private boolean isLeaf() {
        return (left == null) && (right == null);
    }

    /**
     * The compare of the nodes is based on the frequencies
     *
     * @param that The node to compare with
     * @return the comapre result
     */
    public int compareTo(HuffmanNode<T> that) {
        return Long.compare(this.freq, that.freq);
    }

}