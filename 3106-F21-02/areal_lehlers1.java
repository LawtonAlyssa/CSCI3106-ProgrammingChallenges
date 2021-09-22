import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Locale;
import java.util.Scanner;

/**
 * <a href="https://open.kattis.com/problems/areal/">https://open.kattis.com/problems/areal/</a>
 */
public class areal_lehlers1 {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        final BigDecimal a = scanner.nextBigDecimal();
        final BigDecimal r = a.sqrt(MathContext.DECIMAL128).multiply(BigDecimal.valueOf(4));
        System.out.println(r);
    }
}