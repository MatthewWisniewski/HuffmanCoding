public class HuffmanTreeDecoder {
    private HuffmanTreeNode huffmanTree;

    public HuffmanTreeDecoder(HuffmanTreeNode huffmanTree) {
        this.huffmanTree = huffmanTree;
    }

    public String decodeString(String str) {
        HuffmanTreeNode currentNode = this.huffmanTree;
        StringBuilder output = new StringBuilder();
        for (Character c : str.toCharArray()) {

            if (c == '0') {
                currentNode = currentNode.getSubTree(Boolean.FALSE);
            }
            if (c == '1') {
                currentNode = currentNode.getSubTree(Boolean.TRUE);
            }
            if (currentNode.isLeaf()) {
                output.append(currentNode.getContent());
                currentNode = this.huffmanTree;
            }
        }
        return output.toString();
    }
}
