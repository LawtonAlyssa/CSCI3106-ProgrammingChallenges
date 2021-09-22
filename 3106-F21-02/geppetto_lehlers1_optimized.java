import java.util.Locale;
import java.util.Scanner;

/**
 * <a href="https://open.kattis.com/problems/geppetto/">https://open.kattis.com/problems/geppetto/</a>
 */
public class geppetto_lehlers1_optimized {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        // Number of ingredients
        int n = scanner.nextInt();
        // Number of illegal combinations
        int m = scanner.nextInt();
        // Number of theoretically possible combinations (2^n)
        final int numberOfCombinations = 1 << n;
        if (m == 0) {
            System.out.println(numberOfCombinations);
            return;
        }
        // Number of possible combinations with the restrictions in mind (will be decreased)
        int possibleCombinations = numberOfCombinations;
        int[] illegalCombinations = new int[m];
        while (m-- > 0) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            // 2^a OR 2^b --> when a = 0010, b = 1000; then (1 << a - 1) | (1 << b - 1) == 1010
            int illegalCombination = (1 << a - 1) | (1 << b - 1);
            illegalCombinations[m] = illegalCombination;
        }
        for (int i = 1; i < numberOfCombinations; i++) {
            for (int illegalCombination : illegalCombinations) {
                // Example 1: i = 0111, illegalCombination = 0101; i & illegalCombination = 0111 & 0101 == 0101
                // Example 2: i = 0110, illegalCombination = 0101; i & illegalCombination = 0110 & 0101 != 0101 (because it is 0100)
                if ((i & illegalCombination) == illegalCombination) {
                    // Decrease number of actual possible combinations
                    possibleCombinations--;
                    // Leave inner loop, because the combination is already illegal, it does not have to be checked any further
                    break;
                }
            }
        }
        System.out.println(possibleCombinations);
    }
}