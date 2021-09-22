import java.util.Locale;
import java.util.Scanner;

/**
 * <a href="https://open.kattis.com/problems/geppetto/">https://open.kattis.com/problems/geppetto/</a>
 */
public class geppetto_lehlers1_original {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        boolean[] arr = new boolean[1 << n];
        while (m-- > 0) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int baseCross = (1 << a - 1) | (1 << b - 1);
            for (int i = 0; i < 1 << n; i++) {
                arr[baseCross | i] = true;
            }
        }
        int ans = 0;
        for (boolean b : arr) {
            if (!b) {
                ans++;
            }
        }
        System.out.println(ans);
    }
}