package CompressionTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Compression.HuffmanTextCompressor;

class HuffmanTextCompressorTest {

    /**
     * The decoded, code message must match the inital text
     */
    @Test
    public void CodeTextDecodedMustMatchUncodeMessage(){
        HuffmanTextCompressor compressor = new HuffmanTextCompressor();
        String actual = "This is text";
        String expected = compressor.decode(compressor.encode(actual));
        assertEquals(expected, actual);
    }
}