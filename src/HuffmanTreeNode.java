public class HuffmanTreeNode {
    private Character content;

    private HuffmanTreeNode leftSubtree;
    private HuffmanTreeNode rightSubtree;

    private boolean isLeafNode;

    public HuffmanTreeNode(char content) {
        isLeafNode = true;
        this.content = content;
    }

    public HuffmanTreeNode(HuffmanTreeNode left, HuffmanTreeNode right) {
        isLeafNode = false;
        leftSubtree = left;
        rightSubtree = right;
    }

    public boolean isLeaf(){
        return isLeafNode;
    }

    public void setLeaf(Character content) {
        this.content = content;
        isLeafNode = true;
    }

    public void setSubtrees(HuffmanTreeNode left, HuffmanTreeNode right) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("Neither subtree can be a null value");
        }
        leftSubtree = left;
        rightSubtree = right;
        isLeafNode = false;
    }

    public Character getContent() {
        if (!isLeaf()) {
            throw new IllegalArgumentException("This is not a leaf node, it has no content");
        }
        return content;
    }

    public HuffmanTreeNode getLeftSubtree() {
        if (isLeaf()) {
            throw new IllegalArgumentException("Leaf nodes do not have subtrees");
        }
        return leftSubtree;
    }

    public HuffmanTreeNode getRightSubtree() {
        if (isLeaf()) {
            throw new IllegalArgumentException("Leaf nodes do not have subtrees");
        }
        return rightSubtree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HuffmanTreeNode that = (HuffmanTreeNode) o;

        if (this.isLeaf() && that.isLeaf()) {
            return this.content == that.getContent();
        } else {
            return this.getLeftSubtree().equals(that.getLeftSubtree())  &&
                    this.getRightSubtree().equals(that.getRightSubtree());
        }
    }
}
