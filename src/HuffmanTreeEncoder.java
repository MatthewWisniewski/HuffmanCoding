import java.util.HashMap;

public class HuffmanTreeEncoder {
    private HuffmanTreeNode huffmanTree;
    private HashMap<Character, String> huffmanTreeLUT;

    public HuffmanTreeEncoder(HuffmanTreeNode huffmanTree) {
        this.huffmanTree = huffmanTree;
        buildLUT();
    }

    public HuffmanTreeEncoder(HashMap<Character, String> LUT) {
        huffmanTreeLUT = LUT;
    }

    public String encode(String str) {
        StringBuilder output = new StringBuilder();
        for (Character c : str.toCharArray()) {
            //TODO :What to do if char isn't in Huffman tree
            //      Probably crash (IDK)
            output.append(huffmanTreeLUT.get(c));
        }
        return output.toString();
    }

    private void buildLUT() {
        //Recursive algorithm:
        //For each node, append the relevant path (0/1) to the front of
        // each of the following paths in each subtree, producing a hashmap
        // with each character and the path through the huffman tree to it
        huffmanTreeLUT = buildLUTHelper(huffmanTree);
    }

    public String exportLUTAsBinaryString() {
        StringBuilder output = new StringBuilder();
        for (Character c : huffmanTreeLUT.keySet()) {
            output.append(BinaryStringUtils.convertIntToPaddedBinaryString(huffmanTreeLUT.get(c).length(), 16));
            output.append(BinaryStringUtils.convertIntToPaddedBinaryString(c, 16));
            output.append(huffmanTreeLUT.get(c));
        }
        return output.toString();
    }

    private HashMap<Character, String> buildLUTHelper(HuffmanTreeNode huffmanTree) {
        HashMap<Character, String> characterPaths = new HashMap<>();
        if (huffmanTree.isLeaf()) {
            characterPaths.put(huffmanTree.getContent(), "");
        } else {
            HashMap<Character, String> leftMap = buildLUTHelper(huffmanTree.getLeftSubtree());
            for (Character key : leftMap.keySet()) {
                characterPaths.put(key, "0" + leftMap.get(key));
            }
            HashMap<Character, String> rightMap = buildLUTHelper(huffmanTree.getRightSubtree());
            for (Character key : rightMap.keySet()) {
                characterPaths.put(key, "1" + rightMap.get(key));
            }
        }
        return characterPaths;
    }
}
