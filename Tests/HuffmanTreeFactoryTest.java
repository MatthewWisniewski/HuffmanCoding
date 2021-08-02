import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HuffmanTreeFactoryTest {

    @Test
    public void huffmanTreeBuilderFromStringCorrectHuffmanTree() {
        HuffmanTreeNode a = new HuffmanTreeNode();
        a.setLeaf('a');
        HuffmanTreeNode b = new HuffmanTreeNode();
        b.setLeaf('b');
        HuffmanTreeNode c = new HuffmanTreeNode();
        c.setLeaf('c');
        HuffmanTreeNode d = new HuffmanTreeNode();
        d.setLeaf('d');
        HuffmanTreeNode e = new HuffmanTreeNode();
        e.setLeaf('e');

        HuffmanTreeNode expected = new HuffmanTreeNode();

        HuffmanTreeNode right = new HuffmanTreeNode();

        HuffmanTreeNode rightRight = new HuffmanTreeNode();

        HuffmanTreeNode rightRightRight = new HuffmanTreeNode();

        rightRightRight.setNode(a, c);
        rightRight.setNode(b, rightRightRight);
        right.setNode(e, rightRight);
        expected.setNode(d, right);

        String inputStr = "abbcdddddeee";

        HuffmanTreeNode actual = HuffmanTreeFactory.HuffmanTreeBuilderFromString(inputStr);

        assertEquals(expected, actual, "Not generating the correct huffman tree " +
                "in this simple testcase");

    }
}
