import java.util.Scanner;

/**
 * <a href="https://open.kattis.com/problems/dasblinkenlights/">https://open.kattis.com/problems/dasblinkenlights/</a>
 */
public class dasblinkenlights_lehlers1 {
    public static void main(String[] args) throws InterruptedException {
        final Scanner scanner = new Scanner(System.in);
        final int p = scanner.nextInt();
        final int q = scanner.nextInt();
        final int s = scanner.nextInt();
        final int higher = Math.max(p, q);
        final int lower = Math.min(p, q);
        int lcm = higher;
        while (lcm % lower != 0 && lcm <= s) {
            lcm += higher;
        }
        if (lcm <= s && lcm % lower == 0) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}