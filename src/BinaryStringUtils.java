public class BinaryStringUtils {

    //TODO: Currently assuming int is >= 0, either make that assumption is clear or deal with it otherwise.

    public static String  convertIntToBinaryString(int i) {
        return Integer.toBinaryString(i);
    }

    public static String convertIntToPaddedBinaryString(int i, int desiredLength) {
        String binaryNumber = convertIntToBinaryString(i);
        if (desiredLength < binaryNumber.length()) {
            throw new IllegalArgumentException("binary number cannot be a " +
                    "shorter number of bits than needed to represent the number in binary");
        }
        return convertBinaryStringToNBitNumber(binaryNumber, desiredLength);
    }

    public static int convertBinaryStringToInt(String binStr) {
        return Integer.parseInt(binStr, 2);
    }

    public static byte[] convertBinaryStringToByteArray(String binStr) {
        byte[] bytes = new byte[binStr.length() / 8];

        BinaryNumberIterator iter = new BinaryNumberIterator(binStr);
        for (int i = 0; i < binStr.length() / 8; i++) {
            Integer byteAsInt = Integer.parseInt(iter.getNextNBytes(1), 2);
            bytes[i] = byteAsInt.byteValue();
        }
        return bytes;
    }

    public static String convertByteArrayToBinaryString(byte[] bytes) {
        StringBuilder binaryString = new StringBuilder();
        for (byte b : bytes) {
            binaryString.append(convertByteToBinaryString(b));
        }
        return binaryString.toString();
    }

    public static String convertByteToBinaryString(byte b) {
        return String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
    }

    private static String convertBinaryStringToNBitNumber(String str, int n) {
        int lengthDiff = n - str.length();
        StringBuilder padding = new StringBuilder();
        padding.append("0".repeat(Math.max(0, lengthDiff)));
        return padding.append(str).toString();
    }
}
