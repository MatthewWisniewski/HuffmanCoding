import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

//TODO: Error handling, n must be greater than or equal to 0
//TODO: What to do when asking for more bits than remaining in the iterator

public class BinaryNumberIterator {

    private String binaryNumber;
    public CharacterIterator iterator;

    public BinaryNumberIterator(String binaryNumber) {
        this.binaryNumber = binaryNumber;
        initialiseIterator();
    }

    private void initialiseIterator() {
        iterator = new StringCharacterIterator(binaryNumber);
    }

    public String getNextNBits(int n) {
        StringBuilder output = new StringBuilder();
        for (char c = iterator.current(); c != iterator.DONE && n > 0; c = iterator.next()) {
            output.append(c);
            n--;
        }
        return output.toString();
    }

    public String getNextNBytes(int n) {
        return getNextNBits(n * 8);
    }

    public void skipNextNBits(int n) {
        for (char c = iterator.current(); c != iterator.DONE && n > 0; c = iterator.next()) {
            n--;
        }
    }

    public void skipNextNBytes(int n) {
        skipNextNBits(n * 8);
    }

    public String returnRemainingBuffer() {
        StringBuilder output = new StringBuilder();
        for (char c = iterator.current(); c != iterator.DONE; c = iterator.next()) {
            output.append(c);
        }
        return output.toString();
    }

    public boolean notEmpty() {
        return iterator.getIndex() != iterator.getEndIndex();
    }
}
