import java.util.BitSet;
import java.util.Locale;
import java.util.Scanner;

/**
 * <a href="https://open.kattis.com/problems/reachableroads/">https://open.kattis.com/problems/reachableroads/</a>
 */
public class reachableroads_lehlers1 {
    private static BitSet ENDPOINTS;
    private static boolean[][] ADJACENCY;

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        byte n = scanner.nextByte();
        while (n-- > 0) {
            short m = scanner.nextShort();
            short r = scanner.nextShort();
            ENDPOINTS = new BitSet(m);
            ADJACENCY = new boolean[m][m];
            for (short i = 0; i < r; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                ADJACENCY[a][b] = true;
                ADJACENCY[b][a] = true;
            }
            int nrOfGraphs = 0;
            for (short i = 0; i < m; i++) {
                if (ENDPOINTS.get(i)) {
                    continue;
                }
                findConnectedEndpoints(i);
                nrOfGraphs++;
            }
            System.out.println(nrOfGraphs - 1);
            ENDPOINTS = null;
            ADJACENCY = null;
        }
    }

    private static void findConnectedEndpoints(final short x) {
        ENDPOINTS.set(x);
        for (short y = 0; y < ADJACENCY.length; y++) {
            if (x == y || ENDPOINTS.get(y)) {
                continue;
            }
            if (ADJACENCY[x][y]) {
                ENDPOINTS.set(y);
                findConnectedEndpoints(y);
            }
        }
    }
}