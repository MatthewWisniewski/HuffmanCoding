import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HuffmanTreeFactoryTest {

    @Test
    public void huffmanTreeBuilderFromStringCorrectHuffmanTree() {
        HuffmanTreeNode a = new HuffmanTreeNode('a');
        HuffmanTreeNode b = new HuffmanTreeNode('b');
        HuffmanTreeNode c = new HuffmanTreeNode('c');
        HuffmanTreeNode d = new HuffmanTreeNode('d');
        HuffmanTreeNode e = new HuffmanTreeNode('e');

        HuffmanTreeNode rightRightRight = new HuffmanTreeNode(a, c);
        HuffmanTreeNode rightRight = new HuffmanTreeNode(b, rightRightRight);
        HuffmanTreeNode right = new HuffmanTreeNode(e, rightRight);

        HuffmanTreeNode expected = new HuffmanTreeNode(d, right);

        String inputStr = "abbcdddddeee";

        HuffmanTreeNode actual = HuffmanTreeFactory.HuffmanTreeBuilderFromString(inputStr);

        assertEquals(expected, actual, "Not generating the correct huffman tree " +
                "in this simple testcase");

    }
}
