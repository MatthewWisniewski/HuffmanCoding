public class HuffmanTreeNode {
    private Character content;
    private HuffmanTreeNode[] children = {};

    public boolean isLeaf(){
        return children.length == 0;
    }

    public void setLeaf(Character content) {
        this.content = content;
    }

    public void setNode(HuffmanTreeNode left, HuffmanTreeNode right) {
        children = new HuffmanTreeNode[] {left, right};
    }

    public Character getContent() {
        return content;
    }

    public HuffmanTreeNode getSubTree(Boolean direction) {
        try {
            if (direction == Boolean.FALSE) {
                return children[0];
            } else {
                return children[1];
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("This is a leaf, so has no subtrees");
        }
    }

}
