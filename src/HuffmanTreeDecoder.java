public class HuffmanTreeDecoder {
    private HuffmanTreeNode huffmanTree;

    public HuffmanTreeDecoder(HuffmanTreeNode huffmanTree) {
        this.huffmanTree = huffmanTree;
    }

    public String decodeString(String str) {
        HuffmanTreeNode currentNode = huffmanTree;
        StringBuilder output = new StringBuilder();
        for (Character c : str.toCharArray()) {

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
