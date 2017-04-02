package HuffmanTests;

import Huffman.HuffmanNode;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by charles korthout on 4/2/2017.
 */
class HuffmanNodeTest {
    HuffmanNode huffman = null;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    /**
     * The decrypt from the encrypted message must provide the original message
     */
    @org.junit.jupiter.api.Test
    void testEncryptAndDecrypt() {
        String test = "test";
        assertEquals(test, HuffmanNode.decrypt(HuffmanNode.encrypt(test)));
    }
}