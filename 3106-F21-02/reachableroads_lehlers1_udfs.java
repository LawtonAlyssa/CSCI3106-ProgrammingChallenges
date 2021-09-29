import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * <a href="https://open.kattis.com/problems/reachableroads/">https://open.kattis.com/problems/reachableroads/</a>
 */
public class reachableroads_lehlers1_udfs {
    static Kattio kattio;
    static UnionFindDisjointSets udfs;

    private static void solveProblem() {
        int numberOfTestcases = kattio.getInt();
        while (numberOfTestcases-- > 0) {
            int numberOfEndpoints = kattio.getInt();
            int numberOfRoads = kattio.getInt();
            udfs = new UnionFindDisjointSets(numberOfEndpoints);
            for (int i = 0; i < numberOfRoads; i++) {
                int a = kattio.getInt();
                int b = kattio.getInt();
                udfs.unionSets(a, b);
            }
            System.out.println(udfs.numSets - 1);
        }
    }

    static class UnionFindDisjointSets {
        int[] parent;
        int[] rank;
        int numSets;

        UnionFindDisjointSets(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            rank = new int[n];
            numSets = n;
        }

        int getParent(int i) {
            if (parent[i] == i) {
                return i;
            } else {
                int newParent = getParent(this.parent[i]);
                parent[i] = newParent;
                return parent[i];
            }
        }

        void unionSets(int a, int b) {
            int x = getParent(a);
            int y = getParent(b);
            if (x == y) {
                return;
            }
            if (rank[x] == rank[y]) {
                rank[x]++;
            }
            if (rank[x] > rank[y]) {
                parent[y] = x;
            } else {
                parent[x] = y;
            }
            numSets--;
        }
    }

    public static void main(String[] args) {
        // Initialize fast IO
        kattio = new Kattio(System.in);
        // The actual problem code
        solveProblem();
        // Flush fast IO, so that everything is printed to the console
        kattio.flush();
    }

    /**
     * Faster input reading than with {@link java.util.Scanner}
     * <p>
     * <a href="https://open.kattis.com/help/java">Source</a>
     * <p>
     *
     * @author Kattis
     */
    static class Kattio extends PrintWriter {
        public Kattio(InputStream i) {
            super(new BufferedOutputStream(System.out));
            r = new BufferedReader(new InputStreamReader(i));
        }

        public Kattio(InputStream i, OutputStream o) {
            super(new BufferedOutputStream(o));
            r = new BufferedReader(new InputStreamReader(i));
        }

        public boolean hasMoreTokens() {
            return peekToken() != null;
        }

        public int getInt() {
            return Integer.parseInt(nextToken());
        }

        public double getDouble() {
            return Double.parseDouble(nextToken());
        }

        public long getLong() {
            return Long.parseLong(nextToken());
        }

        public String getWord() {
            return nextToken();
        }

        private BufferedReader r;
        private String line;
        private StringTokenizer st;
        private String token;

        private String peekToken() {
            if (token == null) {
                try {
                    while (st == null || !st.hasMoreTokens()) {
                        line = r.readLine();
                        if (line == null) {
                            return null;
                        }
                        st = new StringTokenizer(line);
                    }
                    token = st.nextToken();
                } catch (IOException e) {
                }
            }
            return token;
        }

        private String nextToken() {
            String ans = peekToken();
            token = null;
            return ans;
        }
    }
}