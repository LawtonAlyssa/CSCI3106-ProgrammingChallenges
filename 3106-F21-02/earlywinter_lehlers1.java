import java.util.Locale;
import java.util.Scanner;

/**
 * <a href="https://open.kattis.com/problems/earlywinter/">https://open.kattis.com/problems/earlywinter/</a>
 */
public class earlywinter_lehlers1 {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        int n = scanner.nextInt();
        int t = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            if (scanner.nextInt() <= t) {
                System.out.printf("It hadn't snowed this early in %d years!%n", i);
                return;
            }
        }
        System.out.println("It had never snowed this early!\n");
    }
}