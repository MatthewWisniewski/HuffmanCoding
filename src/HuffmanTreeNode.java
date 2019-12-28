public class HuffmanTreeNode {
    private Character content;
    private HuffmanTreeNode[] children;

    public boolean isLeaf(){
        return this.children.length == 0;
    }

    public void setLeaf(Character content) {
        this.content = content;
    }

    public void setNode(HuffmanTreeNode left, HuffmanTreeNode right) {
        this.children = new HuffmanTreeNode[] {left, right};
    }

    public HuffmanTreeNode getSubTree(Boolean direction) throws Exception {
        try {
            if (direction == Boolean.FALSE) {
                return this.children[0];
            } else {
                return this.children[1];
            }
        } catch (Exception e) {
            throw new Exception("This is a leaf, so has no subtrees");
        }
    }

}
