import java.util.HashMap;

public class Counter {

    public static HashMap<Character, Integer> countCharacters (String str) {
        HashMap<Character, Integer> characterCounts = new HashMap<>();
        for (Character c : str.toCharArray()) {
            if (characterCounts.containsKey(c)) {
                characterCounts.put(c, characterCounts.get(c) + 1);
            } else {
                characterCounts.put(c, 1);
            }
        }
        return characterCounts;
    }

}
/* Implementation idea: Once hashmap is built, convert each key into a HuffmanTreeNode containing the character */