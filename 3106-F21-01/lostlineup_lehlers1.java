import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * <a href="https://open.kattis.com/problems/lostlineup/">https://open.kattis.com/problems/lostlineup/</a>
 */
public class lostlineup_lehlers1 {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        int n = scanner.nextInt();
        Integer[] row = new Integer[n];
        row[0] = 1;
        for (int i = 2; i <= n; i++) {
            row[1 + scanner.nextInt()] = i;
        }
        System.out.println(
                Arrays
                        .stream(row)
                        .map(String::valueOf)
                        .collect(Collectors.joining(" ")));
    }
}