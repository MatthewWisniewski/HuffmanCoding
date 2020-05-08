import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

/**
 * File Structure:
 * 3 bits signifying the amount of padding, in bits, at the end of the encoder
 * 2 bytes signifying the length of the encoder, in bytes
 * Each key value pair in the encoder consists of 3 components:
 *  - The length of the value (2 bytes)
 *  - The key (2 bytes)
 *  - The key (Value of first component in bits)
 *
 *  Encoder padding
 *
 *  - The length of padding prior to the encoded message (1 byte)
 *  - padding (number of bits corresponding to previous value)
 *
 *  The rest of the file is the encoded message.
 */
public class EncodedFileReader {
    String fileContent;

    String encoderBinaryString;

    HashMap<Character, String> LUT = new HashMap<>();
    String encodedMessage;
    String clearText;

    public EncodedFileReader(String path) {
        try {
            File file = new File(path);

            byte[] fileBytes = Files.readAllBytes(file.toPath());

            System.out.println(fileBytes.length);
            fileContent = BinaryStringUtils.convertByteArrayToBinaryString(fileBytes);
            //fileContent = new Scanner(new File(path)).useDelimiter("\\Z").next();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String decode() {
        splitFileContentIntoEncoderAndContent();
        buildLUTFromBinaryString();
        HuffmanTreeNode huffmanTree = HuffmanTreeFactory.buildHuffmanTreeFromLUT(LUT);
        clearText = new HuffmanTreeDecoder(huffmanTree).decodeString(encodedMessage);
        return clearText;
    }

    private void splitFileContentIntoEncoderAndContent() {
        BinaryNumberIterator bitStream = new BinaryNumberIterator(fileContent);
        int filePadding = BinaryStringUtils.convertBinaryStringToInt(bitStream.getNextNBits(8));
        bitStream.skipNextNBits(filePadding);
        int encoderPadding = BinaryStringUtils.convertBinaryStringToInt(bitStream.getNextNBits(8));
        int encoderBitLength = BinaryStringUtils.convertBinaryStringToInt(bitStream.getNextNBits(16));
        encoderBinaryString = bitStream.getNextNBits(encoderBitLength-encoderPadding);
        bitStream.skipNextNBits(encoderPadding);

        int messagePaddingLength = BinaryStringUtils.convertBinaryStringToInt(bitStream.getNextNBytes(1));
        bitStream.skipNextNBits(messagePaddingLength);

        encodedMessage = bitStream.returnRemainingBuffer();
    }

    private void buildLUTFromBinaryString() {
        BinaryNumberIterator bitStream = new BinaryNumberIterator(encoderBinaryString);
        while (bitStream.notEmpty()) {
            int valueLength = BinaryStringUtils.convertBinaryStringToInt(bitStream.getNextNBits(16));
            byte[] bval = new BigInteger(bitStream.getNextNBits(16), 2).toByteArray();
            char key = new String(bval, Charset.defaultCharset()).toCharArray()[0];
            String value = bitStream.getNextNBits(valueLength);

            LUT.put(key, value);
        }
        //TODO: Error handling in the case that the final length isn't zero

    }
}
