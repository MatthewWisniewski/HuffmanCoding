import java.util.HashMap;

public class Counter {

    public static HashMap<Character, Integer> countCharacters (String str) {
        HashMap<Character, Integer> characterCounts = new HashMap<>();
        for (Character c : str.toCharArray()) {
            characterCounts.put(c, characterCounts.getOrDefault(c, 0 ) + 1);
        }
        return characterCounts;
    }

}
