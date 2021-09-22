import java.util.Locale;
import java.util.Scanner;

/**
 * <a href="https://open.kattis.com/problems/deceptivedice/">https://open.kattis.com/problems/deceptivedice/</a>
 */
public class deceptivedice_lehlers1 {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        double n = scanner.nextInt();
        double k = scanner.nextInt();
        double ans = avgAns(n, k);
        System.out.println(ans);
    }

    private static double avgAns(final double n, double k) {
        if (k == 0) {
            return 0;
        }
        double ans = 0;
        final double x = avgAns(n, k - 1);
        for (double i = 1; i <= n; i++) {
            if (x < i) {
                ans += 1 / n * i;
            } else {
                ans += 1 / n * x;
            }
        }
        return ans;
    }
}