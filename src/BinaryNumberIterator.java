import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

//TODO: Error handling, n must be greater than or equal to 0
//TODO: What to do when asking for more bits than remaining in the iterator

public class BinaryNumberIterator {

    private String binaryNumber;
    public CharacterIterator iterator;

    public BinaryNumberIterator(String binaryNumber) {
        this.binaryNumber = binaryNumber;
        iterator = new StringCharacterIterator(binaryNumber);
    }


    public String getNextNBits(int n) {
        StringBuilder bits = new StringBuilder();
        char c = iterator.current();
        for (int i = 0; i < n; i++) {
            if (c == iterator.DONE) {
                break;
            }
            bits.append(c);
            c = iterator.next();
        }
        return bits.toString();
    }

    public String getNextNBytes(int n) {
        return getNextNBits(n * 8);
    }

    public void skipNextNBits(int n) {
        for (int i = 0; i < n; i++) {
            iterator.next();
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
