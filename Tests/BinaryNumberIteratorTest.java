import org.junit.jupiter.api.Test;

import java.net.BindException;

import static org.junit.jupiter.api.Assertions.*;

class BinaryNumberIteratorTest {

    @Test
    public void testGetNextNBits() {
        String input = "1000011";
        BinaryNumberIterator iter = new BinaryNumberIterator(input);

        String expectedWhenNEqualsZero = "";
        String actualWhenNEqualsZero = iter.getNextNBits(0);
        assertEquals(actualWhenNEqualsZero, expectedWhenNEqualsZero);

        String expected1 = "1";
        String actual1 = iter.getNextNBits(1);
        assertEquals(expected1, actual1);

        String expected2 = "0000";
        String actual2 = iter.getNextNBits(4);
        assertEquals(expected2, actual2);

        String expected3 = "11";
        String actual3 = iter.getNextNBits(2);
        assertEquals(expected3, actual3);
    }

    @Test
    public void testSkipNextNBitsAndReturnRemainingBuffer() {
        String input = "1000110";

        BinaryNumberIterator iter1 = new BinaryNumberIterator(input);
        String expected1 = "000110";
        iter1.skipNextNBits(1);
        assertEquals(expected1, iter1.returnRemainingBuffer());

        BinaryNumberIterator iter2 = new BinaryNumberIterator(input);
        String expected2 = "110";
        iter2.skipNextNBits(4);
        assertEquals(expected2, iter2.returnRemainingBuffer());
    }

    @Test
    public void testSkipNextNBytesAndReturnRemainingBuffer() {
        String input = "11111111000000001";
        BinaryNumberIterator iter = new BinaryNumberIterator(input);
        iter.skipNextNBytes(2);
        assertEquals("1", iter.returnRemainingBuffer());
    }
}