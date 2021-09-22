import java.util.Scanner;

/**
 * <a href="https://open.kattis.com/problems/crne/">https://open.kattis.com/problems/crne/</a>
 */
public class crne_lehlers1 {
    public static void main(String[] args) throws InterruptedException {
        final Scanner scanner = new Scanner(System.in);
        final long n = scanner.nextLong();
        final long x = n - 1;
        if (n == 2) {
            System.out.println("4");
        } else if (x % 2 == 0) {
            System.out.println((x / 2 + 2) * (x / 2 + 1));
        } else {
            System.out.println(((x - 1) / 2 + 2) * ((x - 1) / 2 + 1) + (long) Math.floor(x / 2.0 + 1) + 1);
        }
    }
}