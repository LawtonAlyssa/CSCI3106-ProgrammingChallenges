// Author: Curt Powley, Hawaii Pacific University, cpowley@hpu.edu, 
// partially based on code from Jiru Xu
// Dijkstra shortest path (lowest cost) general code

import java.io.*;
import java.util.*;

public class dijkstraTemplate {
    static boolean debug = false;                // for tracing

    public static class Edge {
        public Vertex adj;
        public int weight;

        public Edge(Vertex adj, int weight) {
            this.adj = adj;
            this.weight = weight;
        }
    }

    public static class EdgeDouble {
        public Vertex adj;
        public double weight;

        public EdgeDouble(Vertex adj, double weight) {
            this.adj = adj;
            this.weight = weight;
        }
    }

    public static class Vertex implements Comparable<Vertex> {

        public final int name;
        public boolean   inTree;           // inTree[u] is true when a shortest path to u has found
        public ArrayList<Edge> adjacent;
        //  public double minDis = Double.POSITIVE_INFINITY;  // use if edge weights are not integers
        public int minDis = Integer.MAX_VALUE;

        public Vertex(int name) {
            this.name = name;
            inTree = false;
            adjacent = new ArrayList<>();  // adjacency list for this node (all nodes connected to it by an edge)
        }
        @Override
            public int compareTo(Vertex other) {
            // return Double.compare(this.minDis, other.minDis);   // use if edges are not integers
            return Integer.compare(this.minDis, other.minDis);
        }
    }
        
    public static void dijkstra(Vertex source, Vertex dest) {
        // argument dest is irrelvant if finding paths to all nodes
        source.minDis = 0;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<>();
        vertexQueue.add(source);

        while(!vertexQueue.isEmpty()) {
            Vertex u = vertexQueue.poll();
            if (debug) System.out.println("selected from queue node: " + u.name + "("+ u.minDis + ")");
            u.inTree = true;
            if(u == dest) return; // commented ==> find paths to all nodes
            
            for(Edge e : u.adjacent) {
                Vertex v = e.adj;
                if (!v.inTree) {
                    if (debug) System.out.println("    adj: " + v.name + "("+ v.minDis + ")");
                    int distToVthroughU = u.minDis + e.weight;
                    if(distToVthroughU < v.minDis) {
                        vertexQueue.remove(v);       // remove it to update priority and reinsert
                        v.minDis = distToVthroughU;
                        if (debug) System.out.println("       updating " + v.name + " to: " + v.minDis);
                        vertexQueue.add(v);      
                    }
                }
            }
        }   
    }      

}
