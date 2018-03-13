package Compression;

public interface Decoder<T, S> {
    /**
     * Decodes encoded data to the source format
     * @param t the encoded data
     * @return the decoded data
     */
    S decode(T t);
}
