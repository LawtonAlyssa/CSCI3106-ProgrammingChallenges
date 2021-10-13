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
 * <a href="https://open.kattis.com/problems/buttonbashing/">https://open.kattis.com/problems/buttonbashing/</a>
 */
public class buttonbashing_lehlers1 {
    static Kattio kattio;
    static Queue<State> queue = new LinkedList<>();
    static int[] buttons;
    static boolean[] alreadyChecked;

    private static void solveProblem() {
        int numberOfTestcases = kattio.getInt();
        for (int testcase = 0; testcase < numberOfTestcases; testcase++) {
            alreadyChecked = new boolean[3601];
            int numberOfButtons = kattio.getInt();
            int requiredNumber = kattio.getInt();
            buttons = new int[numberOfButtons];
            for (int button = 0; button < numberOfButtons; button++) {
                buttons[button] = kattio.getInt();
            }
            int minPresses = Integer.MAX_VALUE;
            int minError = Integer.MAX_VALUE;
            queue.add(new State(0, 0));
            while (!queue.isEmpty()) {
                State state = queue.poll();
                if (alreadyChecked[state.sum]) {
                    continue;
                }
                alreadyChecked[state.sum] = true;
                if (state.sum >= requiredNumber && state.sum - requiredNumber < minError) {
                    minPresses = state.presses;
                    minError = state.sum - requiredNumber;
                }
                if (state.sum == requiredNumber) {
                    continue;
                }
                for (int button : buttons) {
                    final int newSum = Math.max(0, Math.min(3600, state.sum + button));
                    queue.add(new State(state.presses + 1, newSum));
                }
            }
            kattio.printf("%d %d%n", minPresses, minError);
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
        int presses;
        int sum;

        public State(final int presses, final int sum) {
            this.presses = presses;
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
