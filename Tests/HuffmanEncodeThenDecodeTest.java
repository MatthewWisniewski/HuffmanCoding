import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HuffmanEncodeThenDecodeTest {

    @Test
    public void simpleCase() {
        String testString = "ABCD";
        HuffmanTreeNode huffmanTree = HuffmanTreeFactory.HuffmanTreeBuilderFromString(testString);
        HuffmanTreeEncoder encoder = new HuffmanTreeEncoder(huffmanTree);
        HuffmanTreeDecoder decoder = new HuffmanTreeDecoder(huffmanTree);

        String outputString = decoder.decodeString(encoder.encode(testString));
        assertEquals(testString, outputString);
    }

    @Test
    public void complexCase() {
        String testString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc convallis in nulla ac " +
                "hendrerit. Etiam vulputate libero mi, nec hendrerit dui lobortis eget. Nunc elementum imperdiet " +
                "semper. Vivamus ac libero sed lorem facilisis dapibus eu non augue. Maecenas faucibus " +
                "scelerisque arcu ut semper. Donec convallis eros eros, at semper lectus varius finibus. " +
                "Aliquam placerat quam id justo consequat consequat. Integer sagittis tellus nulla, ut fringilla " +
                "dui mollis ut. Quisque feugiat lacinia mi et lacinia. Maecenas vel velit sit amet arcu iaculis " +
                "porttitor et in neque.";
        HuffmanTreeNode huffmanTree = HuffmanTreeFactory.HuffmanTreeBuilderFromString(testString);
        HuffmanTreeEncoder encoder = new HuffmanTreeEncoder(huffmanTree);
        HuffmanTreeDecoder decoder = new HuffmanTreeDecoder(huffmanTree);

        String outputString = decoder.decodeString(encoder.encode(testString));
        assertEquals(testString, outputString);
    }
}