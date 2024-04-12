package Graphs_Tushar;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
TC : O(E log V) // But as explained in Dijkstra’s algorithm, time complexity remains O(E Log V) as there will be at most O(E) vertices in priority queue and O(Log E) is same as O(Log V).
SC : O(V)
 */

class Pair{
    int vertex;
    int weight;
    Pair(int vertex,int weight){
        this.vertex = vertex;
        this.weight = weight;
    }
}
public class PrimsMST {


    static void addEdge(ArrayList<Pair>[] adj, int u, int v, int weight){
        adj[u].add(new Pair(v,weight));
        adj[v].add(new Pair(u,weight)); // undirected
    }

    static int primMST(ArrayList<Pair>[] adj, int V){
        int minCost = 0;
        /*
        All vertices key = infinity
        Start with source vertex, initialize the key to 0

        Use priority queue and add the source vertex.

        From the source vertex, traverse through the best cost neighbours. If the neighbor is already picked in MST ignore it.
         */

        int src = 0 ; // source vertex to zero.

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparing(p -> p.weight)); // min heap

        int[] key = new int[V];
        int[] parent = new int[V];
        boolean[] inMST = new boolean[V];

        // Initialize the arrays
        Arrays.fill(key,Integer.MAX_VALUE); // initialize all to inifinity
        Arrays.fill(parent,-1); // initially no parents

        pq.add(new Pair(src,0));
        key[src] = 0;  // infinity is changed to zero for source node.

        while(!pq.isEmpty()){

            Pair current = pq.poll();
            int curVertex = current.vertex;
            int curWeight = current.weight;

            // if this vertex already taken in MST we have to ignore (We don't do this in Djikstra)
            if(inMST[curVertex]){
                continue;
            }
            inMST[curVertex] = true; // mark Visited

            // Traverse all neigbors of this vertex
            for(Pair p : adj[curVertex]){

                int v = p.vertex;
                int vWeight = p.weight;

                //For every adjacent vertex v, if the weight of edge u-v (vWeight) is less than the previous key value of v, update the key value as the weight of u-v.
                if(!inMST[p.vertex] && key[v] > vWeight ){ // p is a better choice

                    key[v] = vWeight;
                    pq.add(new Pair(v,key[v]));
                    parent[v] = current.vertex;
                    //inMST[p.vertex] = true; // mark visited
                }

            }
        }
        // Print edges of MST using parent array
        for (int i = 1; i < V; ++i){
            minCost += key[i];
            System.out.println(parent[i] + " - " + i);}
        System.out.println("MinCost : "+minCost);

        return minCost;
    }


    public static void main(String[] args) {

        int V = 9;
        ArrayList<Pair>[] adj = new ArrayList[V];

        for(int i = 0 ; i < V ; i++){
            adj[i] = new ArrayList<>(); // initialize with empty lists.
        }

        // Adding edges to the graph
        addEdge(adj, 0, 1, 4);
        addEdge(adj, 0, 7, 8);
        addEdge(adj, 1, 2, 8);
        addEdge(adj, 1, 7, 11);
        addEdge(adj, 2, 3, 7);
        addEdge(adj, 2, 8, 2);
        addEdge(adj, 2, 5, 4);
        addEdge(adj, 3, 4, 9);
        addEdge(adj, 3, 5, 14);
        addEdge(adj, 4, 5, 10);
        addEdge(adj, 5, 6, 2);
        addEdge(adj, 6, 7, 1);
        addEdge(adj, 6, 8, 6);
        addEdge(adj, 7, 8, 7);

        System.out.println("Minimum Cost : " + primMST(adj,V));

        ArrayList<Pair>[] adj1 = new ArrayList[5];

        for(int i = 0 ; i < 5 ; i++){
            adj1[i] = new ArrayList<>(); // initialize with empty lists.
        }
        addEdge(adj1, 0, 1, 5);
        addEdge(adj1, 0, 2, 13);
        addEdge(adj1, 0, 4, 15);
        addEdge(adj1, 1, 2, 10);
        addEdge(adj1, 1, 3, 8);
        addEdge(adj1, 2, 3, 6);
        addEdge(adj1, 2, 4, 20);
        System.out.println("Minimum Cost : " + primMST(adj1,5));

    }


}
