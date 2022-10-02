/**
 * Decodes binary strings back into plaintext by processing
 * it back through a Huffman tree
 */
public class HuffmanTreeDecoder {
    /** The HuffmanTree that will be used to decode messages*/
    private HuffmanTreeNode huffmanTree;

    public HuffmanTreeDecoder(HuffmanTreeNode huffmanTree) {
        this.huffmanTree = huffmanTree;
    }

    /**
     * Decodes a binary string by traversing through
     * the Huffman Tree.
     *
     * @param encodedBinaryString Encoded binary string
     * @return                    The plaintext decoded message
     */
    public String decodeString(String encodedBinaryString) {
        HuffmanTreeNode currentNode = huffmanTree;
        StringBuilder output = new StringBuilder();
        for (Character c : encodedBinaryString.toCharArray()) {

            if (c == '0') {
                currentNode = currentNode.getLeftSubtree();
            }
            if (c == '1') {
                currentNode = currentNode.getRightSubtree();
            }
            if (currentNode.isLeaf()) {
                output.append(currentNode.getContent());
                currentNode = huffmanTree;
            }
        }
        return output.toString();
    }
}
