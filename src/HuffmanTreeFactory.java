import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanTreeFactory {

    //Given a string, produce the associated huffman tree

    public static HuffmanTreeNode HuffmanTreeBuilderFromString (String str) {
        HashMap<Character, Integer> characterCounts = Counter.countCharacters(str);
        List<NodeCount> nodes = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : characterCounts.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            HuffmanTreeNode node = buildHuffmanTreeLeaf(key);
            NodeCount toAdd = new NodeCount(node, value);
            nodes.add(toAdd);
        }
        while (nodes.size() > 1) {
            nodes.sort(Comparator.comparingInt(NodeCount::getCount));
            NodeCount newNode = createNewNodeCount(nodes.get(0), nodes.get(1));
            nodes = nodes.subList(2, nodes.size());
            nodes.add(newNode);
        }
        return nodes.get(0).getNode();
    }

    private static NodeCount createNewNodeCount(NodeCount nc1, NodeCount nc2) {
        HuffmanTreeNode newNode = buildHuffmanTreeNode(nc1.getNode(), nc2.getNode());
        Integer newCount = nc1.getCount() + nc2.getCount();
        return new NodeCount (newNode, newCount);
    }

    private static HuffmanTreeNode buildHuffmanTreeLeaf(Character content) {
        HuffmanTreeNode leaf = new HuffmanTreeNode();
        leaf.setLeaf(content);
        return leaf;
    }

    private static HuffmanTreeNode buildHuffmanTreeNode(HuffmanTreeNode left, HuffmanTreeNode right) {
        HuffmanTreeNode node = new HuffmanTreeNode();
        node.setNode(left, right);
        return node;
    }

}
