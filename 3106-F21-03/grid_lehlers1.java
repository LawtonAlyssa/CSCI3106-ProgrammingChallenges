import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href="https://open.kattis.com/problems/grid/">https://open.kattis.com/problems/grid/</a>
 */
public class grid_lehlers1 {
    static Kattio kattio;
    static Queue<State> queue = new LinkedList<>();
    static char[][] grid;

    private static void solveProblem() {
        int height = kattio.getInt();
        int width = kattio.getInt();
        grid = new char[height][width];
        for (int i = 0; i < height; i++) {
            System.arraycopy(kattio.getWord().toCharArray(), 0, grid[i], 0, width);
        }
        queue.add(new State(0, 0, 0));
        int neededSteps = -1;
        while (!queue.isEmpty()) {
            State state = queue.poll();
            if (state.y == height - 1 && state.x == width - 1) {
                neededSteps = state.jumps;
                break;
            }
            if (state.y < 0 || state.x < 0 || state.y >= height || state.x >= width || grid[state.y][state.x] == '-') {
                continue;
            }
            int digit = grid[state.y][state.x] - 48;
            queue.add(new State(state.jumps + 1, state.x, state.y + digit));
            queue.add(new State(state.jumps + 1, state.x, state.y - digit));
            queue.add(new State(state.jumps + 1, state.x + digit, state.y));
            queue.add(new State(state.jumps + 1, state.x - digit, state.y));
            grid[state.y][state.x] = '-';
        }
        kattio.println(neededSteps);
    }

    public static void main(String[] args) {
        // Initialize fast IO
        kattio = new Kattio(System.in);
        // The actual problem code
        solveProblem();
        // Flush fast IO, so that everything is printed to the console
        kattio.flush();
    }

    static class State {
        int jumps;
        int x;
        int y;

        public State(final int jumps, final int x, final int y) {
            this.jumps = jumps;
            this.x = x;
            this.y = y;
        }
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
