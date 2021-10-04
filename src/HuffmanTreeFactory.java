import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTreeFactory {

    //Given a string, produce the associated huffman tree

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

        if (leftLUT.keySet().size() == 1 && rightLUT.keySet().size() == 1) {
            left = new HuffmanTreeNode(leftLUT.keySet().iterator().next());
            right = new HuffmanTreeNode(rightLUT.keySet().iterator().next());
            return new HuffmanTreeNode(left, right);
        } else if (leftLUT.keySet().size() == 1) {
            left = new HuffmanTreeNode(leftLUT.keySet().iterator().next());
            return new HuffmanTreeNode(left,
                    buildHuffmanTreeFromLUT(rightLUT, startsWith + "1"));
        }   else if (rightLUT.keySet().size() == 1) {
            right = new HuffmanTreeNode(rightLUT.keySet().iterator().next());
            return new HuffmanTreeNode(buildHuffmanTreeFromLUT(leftLUT, startsWith + "0"), right);
        } else {
            return new HuffmanTreeNode(buildHuffmanTreeFromLUT(leftLUT, startsWith + "0"),
                    buildHuffmanTreeFromLUT(rightLUT, startsWith + "1"));
        }
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
