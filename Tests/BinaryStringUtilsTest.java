import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryStringUtilsTest {


    // --------------- convertIntToBinaryString ---------------
    @Test
    public void testConvertIntToBinaryString() {
        assertEquals(BinaryStringUtils.convertIntToBinaryString(0), "0");
        assertEquals(BinaryStringUtils.convertIntToBinaryString(7), "111");
        assertEquals(BinaryStringUtils.convertIntToBinaryString(8), "1000");
    }


    @Test
    public void testConvertIntToPaddedBinaryString() {
        assertEquals(BinaryStringUtils.convertIntToPaddedBinaryString(8, 4), "1000");
        assertEquals(BinaryStringUtils.convertIntToPaddedBinaryString(8, 6), "001000");
    }

    // --------------- convertIntToPaddedBinaryString ---------------

    @Test
    public void testConvertIntToPaddedBinaryStringIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            BinaryStringUtils.convertIntToPaddedBinaryString(8, 0);
        });
    }

    // --------------- convertIntToBinaryString ---------------

    @Test
    public void testConvertBinaryStringToInt() {
        assertEquals(BinaryStringUtils.convertBinaryStringToInt("0"), 0);
        assertEquals(BinaryStringUtils.convertBinaryStringToInt("000"), 0);
        assertEquals(BinaryStringUtils.convertBinaryStringToInt("111"), 7);
        assertEquals(BinaryStringUtils.convertBinaryStringToInt("1000"), 8);
        assertEquals(BinaryStringUtils.convertBinaryStringToInt("000001000"), 8);
    }

    // --------------- convertBinaryStringToByteArray ---------------

    //TODO: ADD MORE TESTS

    @Test
    public void testConvertBinaryStringToByteArray() {
        assertArrayEquals(BinaryStringUtils.convertBinaryStringToByteArray("11111111"),
                new byte[]{ (byte) 0b11111111});
    }

    // --------------- convertByteArrayToBinaryString ---------------
    //TODO: Currently placeholder test

    @Test
    public void testBinaryStringToByteArrayAndBack() {
        String binStr = "11110000111100000000111100001111";
        assertEquals(binStr, BinaryStringUtils.convertByteArrayToBinaryString(
                BinaryStringUtils.convertBinaryStringToByteArray(binStr)));

    }
}