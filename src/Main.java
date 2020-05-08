import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.System;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

//TODO: We appear to be losing the final character of the file in the encoding and decoding process

public class Main {

    public static void main (String[] args) {
//        String testString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc convallis in nulla ac " +
//                "hendrerit. Etiam vulputate libero mi, nec hendrerit dui lobortis eget. Nunc elementum imperdiet " +
//                "semper. Vivamus ac libero sed lorem facilisis dapibus eu non augue. Maecenas faucibus " +
//                "scelerisque arcu ut semper. Donec convallis eros eros, at semper lectus varius finibus. " +
//                "Aliquam placerat quam id justo consequat consequat. Integer sagittis tellus nulla, ut fringilla " +
//                "dui mollis ut. Quisque feugiat lacinia mi et lacinia. Maecenas vel velit sit amet arcu iaculis " +
//                "porttitor et in neque.";
        String testString;
        try {
            testString = new Scanner(new File("alice29.txt")).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            testString = null;
            e.printStackTrace();
        }

        HuffmanTreeNode huffmanTree = HuffmanTreeFactory.HuffmanTreeBuilderFromString(testString);
        HuffmanTreeEncoder encoder = new HuffmanTreeEncoder(huffmanTree);
        HuffmanTreeDecoder decoder = new HuffmanTreeDecoder(huffmanTree);
        System.out.println(encoder.exportLUTAsBinaryString().length()/8);
        System.out.println(testString.length());
        System.out.println(encoder.encode(testString).length()/8);
        //System.out.println(decoder.decodeString(encoder.encode(testString)));
        System.out.println(decoder.decodeString(encoder.encode(testString)).equals(testString));


        EncodedFileWriter fw =  new EncodedFileWriter(encoder);
        fw.encodeStringToFile(testString, Paths.get("test.huff"));

        EncodedFileReader fr = new EncodedFileReader("test.huff");
        String fileContent = fr.decode();

        //System.out.println(fileContent);
        System.out.println(fileContent.equals(testString));

        try {
            Files.writeString(Paths.get("beeMovieHuffman.txt"), fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


