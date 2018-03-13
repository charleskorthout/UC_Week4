package Compression;

public interface Encoder<S,T> {
    /**
     * Encode from the source format into the target format
     * @param s The source format for example String
     * @return the encoded data in the target format
     */
    T encode(S s);
}

