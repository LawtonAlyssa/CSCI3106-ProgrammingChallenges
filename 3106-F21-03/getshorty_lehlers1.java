import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href="https://open.kattis.com/problems/getshorty/">https://open.kattis.com/problems/getshorty/</a>
 */
public class getshorty_lehlers1 {
    static Kattio kattio;
    static Node[] nodes;

    private static void solveProblem() {
        int numberOfNodes, numberOfEdges;
        while (!((numberOfNodes = kattio.getInt()) == 0 || (numberOfEdges = kattio.getInt()) == 0)) {
            nodes = new Node[numberOfNodes];
            for (int node = 0; node < numberOfNodes; node++) {
                nodes[node] = new Node(new ArrayList<>(), 0);
            }
            for (int edge = 0; edge < numberOfEdges; edge++) {
                int x = kattio.getInt();
                int y = kattio.getInt();
                double f = kattio.getDouble();
                nodes[x].adjacency.add(new Edge(y, f));
                nodes[y].adjacency.add(new Edge(x, f));
            }
            dijkstra(0);
            double ans = nodes[numberOfNodes - 1].size;
            kattio.printf("%1.4f%n", ans);
        }
    }

    private static void dijkstra(final int startIndex) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        nodes[startIndex].size = 1;
        priorityQueue.add(nodes[startIndex]);
        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            if (node.done) {
                continue;
            }
            node.done = true;
            for (Edge edge : node.adjacency) {
                double newSize = node.size * edge.factor;
                if (newSize > nodes[edge.adjacentNode].size) {
                    nodes[edge.adjacentNode].size = newSize;
                    priorityQueue.remove(nodes[edge.adjacentNode]);
                    priorityQueue.add(nodes[edge.adjacentNode]);
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        List<Edge> adjacency;
        double size;
        boolean done;

        public Node(final List<Edge> adjacency, final double size) {
            this.adjacency = adjacency;
            this.size = size;
        }

        @Override
        public int compareTo(final Node o) {
            return Double.compare(o.size, size);
        }
    }

    static class Edge {
        int adjacentNode;
        double factor;

        public Edge(final int adjacentNode, final double factor) {
            this.adjacentNode = adjacentNode;
            this.factor = factor;
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
