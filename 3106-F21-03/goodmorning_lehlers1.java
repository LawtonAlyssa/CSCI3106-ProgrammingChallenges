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
 * <a href="https://open.kattis.com/problems/goodmorning/">https://open.kattis.com/problems/goodmorning/</a>
 */
public class goodmorning_lehlers1 {
    static Kattio kattio;
    static Queue<State> queue = new LinkedList<>();

    private static void solveProblem() {
        int numberOfTestcases = kattio.getInt();
        for (int testcase = 0; testcase < numberOfTestcases; testcase++) {
            int requiredNumber = kattio.getInt();
            int minError = Integer.MAX_VALUE;
            queue.add(new State(1, 0));
            while (!queue.isEmpty()) {
                State state = queue.poll();
                if (Math.abs(requiredNumber - state.sum) < Math.abs(minError)) {
                    minError = requiredNumber - state.sum;
                }
                if (state.sum + minError > requiredNumber) {
                    continue;
                }
                if (state.sum == requiredNumber) {
                    break;
                }
                // Same
                if (state.position != 0 || state.sum > 0) {
                    queue.add(new State(state.position, state.sum * 10 + state.position));
                }
                // Go down
                if (state.position > 0 && state.position < 7) {
                    // Go down and take the next one
                    queue.add(new State(state.position + 3, state.sum * 10 + state.position + 3));
                    // Go down without taking the next one
                    queue.add(new State(state.position + 3, state.sum));
                }
                if (state.position == 1 || state.position == 2 || state.position == 4 || state.position == 5 || state.position == 7 || state.position == 8) {
                    // Go right and take the next one
                    queue.add(new State(state.position + 1, state.sum * 10 + state.position + 1));
                    // Go right without taking the next one
                    queue.add(new State(state.position + 1, state.sum));
                }
                if (state.position == 8) {
                    // Go to 0 and take it
                    queue.add(new State(0, state.sum * 10));
                }
            }
            kattio.println(requiredNumber - minError);
            queue.clear();
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

    static class State {
        int position;
        int sum;

        public State(final int position, final int sum) {
            this.position = position;
            this.sum = sum;
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
