import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static ArrayList<Integer> destinations;
    private static int swaps;

    public static void main(String[] args) {
        swaps = 0;

        Scanner scanner = new Scanner(System.in);

        destinations = digits(scanner.nextInt());

        while (!isSorted(destinations)) {
            for (int i = 0; i < destinations.size() - 1; i++) {
                if (destinations.get(i) > destinations.get(i+1)) {
                    swaps++;
                    int temp = destinations.get(i);
                    destinations.set(i, destinations.get(i +1));
                    destinations.set(i + 1, temp);

                }
            }
        }
        System.out.println(swaps);
    }

    private static ArrayList<Integer> digits(int i) {
        ArrayList<Integer> digits = new ArrayList<Integer>();
        while (i > 0) {
            digits.add(i % 10);
            i /= 10;
        }
        return digits;
    }

    private static boolean isSorted(ArrayList<Integer> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i) > arr.get(i+1)) {
                return false;
            }
        }
        return true;
    }

}
