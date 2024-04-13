package Graphs_PracticeProblems;

import Graphs_Tushar.Graph;

import java.util.*;

/*
Given a Directed Graph and two vertices in it, check whether there is a path from the first given vertex to second.

Input : (u, v) = (1, 3)
Output: Yes
Explanation: There is a path from 1 to 3, 1 -> 2 -> 3

Input : (u, v) = (3, 6)
Output: No
Explanation: There is no path from 3 to 6

https://www.geeksforgeeks.org/find-if-there-is-a-path-between-two-vertices-in-a-given-graph/
 */
/*
Time Complexity: O(V+E) where V is number of vertices in the graph and E is number of edges in the graph.
Space Complexity: O(V).
 */
public class PathExistsDirected {

    static class Graph {

        int V;
        ArrayList<Integer>[] adj; // adjacency list (Array of ArrayLists)

        Graph(int V){
            this.V = V;
            adj = new ArrayList[V];
            //Arrays.fill(adj,new ArrayList<>());
            for (int i=0; i<V; ++i) {
                adj[i] = new ArrayList<>();
            }
        }

        public void addEdge(int u, int v){
            adj[u].add(v);
        }

        public int getV() {
            return V;
        }

        public ArrayList<Integer>[] getAdj() {
            return adj;
        }
    }

    public boolean isReachableBFS(Graph g, int s, int d){

        int V = g.getV();
        ArrayList<Integer>[] adj = g.getAdj();

        boolean[] visited = new boolean[V];
        Queue<Integer> bfsQ = new LinkedList<>();

        bfsQ.offer(s);
        visited[s] = true;

        while(!bfsQ.isEmpty()){

            int current = bfsQ.poll();
            visited[current] = true;
            for(int x : adj[current]){
                if(x == d) return  true;

                if(!visited[x]){
                    visited[x] = true;
                    bfsQ.offer(x);
                }
            }
        }

        return false;
    }

    public boolean isReachableDFS(Graph g, int s, int d){
        int V = g.getV();
        ArrayList<Integer>[] adj = g.getAdj();

        boolean[] visited = new boolean[V];

        Stack<Integer> dfsStk = new Stack<>();

        dfsStk.push(s);
        visited[s] = true;

        while(!dfsStk.isEmpty()){
            int current = dfsStk.pop();
            visited[current] = true;

            for(int x : adj[current]){
                if(x == d) return  true;

                if(!visited[x]){
                    visited[x] = true;
                    dfsStk.push(x);
                }
            }


        }
        return false;
    }

    public static void main(String[] args) {

        PathExistsDirected ob = new PathExistsDirected();
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Does path exist ? : "+ob.isReachableBFS(g,1,3));
        System.out.println("Does path exist ? : "+ob.isReachableBFS(g,3,1));

        System.out.println("Does path exist ? : "+ob.isReachableDFS(g,1,3));
        System.out.println("Does path exist ? : "+ob.isReachableDFS(g,3,1));


    }
}
