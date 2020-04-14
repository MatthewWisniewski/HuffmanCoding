public class NodeCount {

    private HuffmanTreeNode node;
    private Integer count;

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
