public class NodeCount {

    private HuffmanTreeNode node;
    private Integer count;

    public NodeCount(HuffmanTreeNode node, Integer count) {
        this.node = node;
        this.count = count;
    }

    public HuffmanTreeNode getNode() {
        return this.node;
    }

    public Integer getCount() {
        return this.count;
    }
}
