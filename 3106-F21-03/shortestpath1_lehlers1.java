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
 * <a href="https://open.kattis.com/problems/shortestpath1/">https://open.kattis.com/problems/shortestpath1/</a>
 */
public class shortestpath1_lehlers1 {
    static Kattio kattio;
    static Node[] nodes;

    private static void solveProblem() {
        while (true) {
            int numberOfNodes = kattio.getInt();
            nodes = new Node[numberOfNodes];
            for (int i = 0; i < numberOfNodes; i++) {
                nodes[i] = new Node(new ArrayList<>(), Integer.MAX_VALUE);
            }
            int numberOfEdges = kattio.getInt();
            int numberOfQueries = kattio.getInt();
            int startIndex = kattio.getInt();
            if (numberOfNodes == 0 && numberOfEdges == 0 && numberOfQueries == 0 && startIndex == 0) {
                return;
            }
            for (int edge = 0; edge < numberOfEdges; edge++) {
                nodes[kattio.getInt()].adjacency.add(new Edge(kattio.getInt(), kattio.getInt()));
            }
            dijkstra(startIndex);
            for (int query = 0; query < numberOfQueries; query++) {
                int nodeIndex = kattio.getInt();
                int distance = nodes[nodeIndex].distance;
                kattio.println(distance == Integer.MAX_VALUE ? "Impossible" : distance);
            }
            kattio.println();
        }
    }

    private static void dijkstra(final int startIndex) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        nodes[startIndex].distance = 0;
        priorityQueue.add(nodes[startIndex]);
        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            if (node.done) {
                continue;
            }
            node.done = true;
            for (Edge edge : node.adjacency) {
                if (node.distance + edge.weight < nodes[edge.adjacentNode].distance) {
                    nodes[edge.adjacentNode].distance = node.distance + edge.weight;
                    priorityQueue.remove(nodes[edge.adjacentNode]);
                    priorityQueue.add(nodes[edge.adjacentNode]);
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        List<Edge> adjacency;
        int distance;
        boolean done;

        public Node(final List<Edge> adjacency, final int distance) {
            this.adjacency = adjacency;
            this.distance = distance;
        }

        @Override
        public int compareTo(final Node o) {
            return Integer.compare(distance, o.distance);
        }
    }

    static class Edge {
        int adjacentNode;
        int weight;

        public Edge(final int adjacentNode, final int weight) {
            this.adjacentNode = adjacentNode;
            this.weight = weight;
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
