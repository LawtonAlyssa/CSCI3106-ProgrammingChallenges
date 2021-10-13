import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href="https://open.kattis.com/problems/islandhopping/">https://open.kattis.com/problems/islandhopping/</a>
 */
public class islandhopping_lehlers1 {
    static Kattio kattio;
    static Island[] islands;
    static boolean[] visited;
    static PriorityQueue<QueueElement> priorityQueue;

    private static void solveProblem() {
        int n = kattio.getInt();
        while (n-- > 0) {
            int numberOfIslands = kattio.getInt();
            islands = new Island[numberOfIslands];
            visited = new boolean[numberOfIslands];
            priorityQueue = new PriorityQueue<>();
            for (int islandIndex = 0; islandIndex < numberOfIslands; islandIndex++) {
                islands[islandIndex] = new Island(kattio.getDouble(), kattio.getDouble());
            }
            kattio.println(prim(0));
        }
    }

    private static double prim(final int initial) {
        priorityQueue.add(new QueueElement(0, initial));
        QueueElement element;
        Island island;
        int islandIndex;
        double value = 0;
        while (!priorityQueue.isEmpty()) {
            element = priorityQueue.poll();
            islandIndex = element.neighbour;
            if (visited[islandIndex]) {
                continue;
            }
            value += element.weight;
            visited[islandIndex] = true;
            island = islands[islandIndex];
            for (int i = 0; i < islands.length; i++) {
                if (i == islandIndex || visited[i]) {
                    continue;
                }
                priorityQueue.add(new QueueElement(distance(island, islands[i]), i));
            }
        }
        return value;
    }

    private static double distance(Island i1, Island i2) {
        return Math.sqrt(Math.pow(i1.x - i2.x, 2) + Math.pow(i1.y - i2.y, 2));
    }

    static class Island {
        double x, y;

        public Island(final double x, final double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class QueueElement implements Comparable<QueueElement> {
        double weight;
        int neighbour;

        public QueueElement(final double weight, final int neighbour) {
            this.weight = weight;
            this.neighbour = neighbour;
        }

        @Override
        public int compareTo(final QueueElement o) {
            return weight != o.weight ? Double.compare(weight, o.weight) : Integer.compare(neighbour, o.neighbour);
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
