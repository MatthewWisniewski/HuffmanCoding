import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class EncodedFileWriter {

    HuffmanTreeEncoder encoder;

    public EncodedFileWriter(HuffmanTreeEncoder huffmanTreeEncoder) {
        encoder = huffmanTreeEncoder;
    }

    public void encodeStringToFile(String string, Path fileName) {
        int encoderPadding = 0;
        int encoderByteLength;
        StringBuilder output = new StringBuilder(encoder.exportLUTAsBinaryString());
        while (output.length() % 8 != 0) {
            output.append("0");
            encoderPadding++;
        }
        encoderByteLength = output.length()/8;
        output.insert(0, toBinaryStringRepresentation(encoderByteLength*8, 16));
        output.insert(0, toBinaryStringRepresentation(encoderPadding, 8));
        String encodedMessage = encoder.encode(string);
        int requiredPadding = encodedMessage.length() % 8;
        output.append(BinaryStringUtils.convertIntToPaddedBinaryString(requiredPadding, 8));
        for (int i = 0; i < requiredPadding; i++) {
            output.append("0");
        }
        output.append(encodedMessage);

        int filePadding = 0;

        while (output.length() % 8 != 0) {
            output.insert(0, "0");
            filePadding++;
        }

        output.insert(0, BinaryStringUtils.convertIntToPaddedBinaryString(filePadding, 8));

        System.out.println(output.length() % 8);

        byte[] outputInBytes = BinaryStringUtils.convertBinaryStringToByteArray(output.toString());


        System.out.println(outputInBytes.length * 8 == output.length());

        try {
            FileOutputStream stream = new FileOutputStream(fileName.toString());
            stream.write(outputInBytes);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String toBinaryStringRepresentation(int i, int bitNo) {
        return String.format("%"+Integer.toString(bitNo)+"s",Integer.toBinaryString(i)).replace(" ","0");
    }
}
