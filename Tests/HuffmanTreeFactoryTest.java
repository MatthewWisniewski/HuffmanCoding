import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class HuffmanTreeFactoryTest {

    private static HuffmanTreeNode huffmanTree;
    private static HashMap<Character, String> huffmanTreeLUT;

    @BeforeAll
    public static void setUp() {
        HuffmanTreeNode a = new HuffmanTreeNode('a');
        HuffmanTreeNode b = new HuffmanTreeNode('b');
        HuffmanTreeNode c = new HuffmanTreeNode('c');
        HuffmanTreeNode d = new HuffmanTreeNode('d');
        HuffmanTreeNode e = new HuffmanTreeNode('e');

        HuffmanTreeNode rightRightRight = new HuffmanTreeNode(a, c);
        HuffmanTreeNode rightRight = new HuffmanTreeNode(b, rightRightRight);
        HuffmanTreeNode right = new HuffmanTreeNode(e, rightRight);

        huffmanTree = new HuffmanTreeNode(d, right);

        huffmanTreeLUT = new HashMap<>() {
            {
                put('d', "0");
                put('e', "10");
                put('b', "110");
                put('a', "1110");
                put('c', "1111");
            }
        };
    }

    @Test
    public void testHuffmanTreeBuilderFromStringCorrectHuffmanTree() {

        HuffmanTreeNode expected = huffmanTree;

        String inputStr = "abbcdddddeee";

        HuffmanTreeNode actual = HuffmanTreeFactory.HuffmanTreeBuilderFromString(inputStr);

        assertEquals(expected, actual, "Not generating the correct huffman tree " +
                "in this simple testcase");

    }

    @Test
    public void testBuildHuffmanTreeFromLUT() {
        HuffmanTreeNode actual = HuffmanTreeFactory.buildHuffmanTreeFromLUT(huffmanTreeLUT);
        assertEquals(actual, huffmanTree,
                "Not properly constructing Huffman tree from LUT");
    }


}
