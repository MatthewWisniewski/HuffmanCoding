import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/** A collection of static methods to generate HuffmanTreeNode objects */
public class HuffmanTreeFactory {

    /**
     * Generates the Huffman tree for a given string.
     *
     * @param str The string that will be analysed to generate
     *            a Huffman tree.
     * @return    The Huffman tree corresponding to the provided string.
     */
    public static HuffmanTreeNode HuffmanTreeBuilderFromString (String str) {
        HashMap<Character, Integer> characterCounts = Counter.countCharacters(str);

        PriorityQueue<NodeCount> nodeQueue = new PriorityQueue<>((a,b)->(a.getCount()-b.getCount()));

        for (Map.Entry<Character, Integer> entry : characterCounts.entrySet()) {
            char key = entry.getKey();
            int count = entry.getValue();
            HuffmanTreeNode node = buildHuffmanTreeLeaf(key);
            nodeQueue.add(new NodeCount(node, count));
        }

        while (nodeQueue.size() > 1) {
            NodeCount a = nodeQueue.poll();
            NodeCount b = nodeQueue.poll();
            NodeCount newNode = createNewNodeCount(a, b);
            nodeQueue.add(newNode);
        }

        return nodeQueue.poll().getNode();
    }

    /**
     * Converts a Huffman tree look-up table, which matches characters to binary strings
     * into a HuffmanTreeNode object.
     *
     * @param LUT A Hashmap mapping a character to its route encoding in a Huffman tree.
     * @return    The Huffman tree corresponding to the look-up table.
     */
    public static HuffmanTreeNode buildHuffmanTreeFromLUT(HashMap<Character, String> LUT) {
        return buildHuffmanTreeFromLUT(LUT, "");
    }

    private static HuffmanTreeNode buildHuffmanTreeFromLUT(HashMap<Character, String> LUT, String startsWith) {
        HashMap<Character, String> leftLUT = new HashMap<>();
        HashMap<Character, String> rightLUT = new HashMap<>();
        for (char c : LUT.keySet()) {
            if (LUT.get(c).startsWith(startsWith + "0")) {
                leftLUT.put(c, LUT.get(c));
            } else {
                rightLUT.put(c, LUT.get(c));
            }
        }

        HuffmanTreeNode left;
        HuffmanTreeNode right;

        if (leftLUT.keySet().size() == 1) {
            left = new HuffmanTreeNode(leftLUT.keySet().iterator().next());
        } else {
            left = buildHuffmanTreeFromLUT(leftLUT, startsWith + "0");
        }

        if (rightLUT.keySet().size() == 1) {
            right = new HuffmanTreeNode(rightLUT.keySet().iterator().next());
        } else {
            right = buildHuffmanTreeFromLUT(rightLUT, startsWith + "1");
        }

        return new HuffmanTreeNode(left, right);
    }

    private static NodeCount createNewNodeCount(NodeCount nc1, NodeCount nc2) {
        HuffmanTreeNode newNode = buildHuffmanTreeNode(nc1.getNode(), nc2.getNode());
        Integer newCount = nc1.getCount() + nc2.getCount();
        return new NodeCount (newNode, newCount);
    }

    private static HuffmanTreeNode buildHuffmanTreeLeaf(Character content) {
        return  new HuffmanTreeNode(content);
    }

    private static HuffmanTreeNode buildHuffmanTreeNode(HuffmanTreeNode left, HuffmanTreeNode right) {
        return new HuffmanTreeNode(left, right);
    }

}
