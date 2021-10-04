public class NodeCount {

    private final HuffmanTreeNode node;
    private final int count;

    public NodeCount(HuffmanTreeNode node, Integer count) {
        this.node = node;
        this.count = count;
    }

    public HuffmanTreeNode getNode() {
        return node;
    }

    public Integer getCount() {
        return count;
    }
}
