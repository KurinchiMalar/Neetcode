package Graphs_PracticeProblems;

import java.util.*;
/*
    https://www.geeksforgeeks.org/topological-sorting-indegree-based-solution/

TC : O(V+E) // The outer for loop will be executed V number of times and the inner for loop will be executed E number of times.

SC : O(V) // The queue needs to store all the vertices of the graph. So the space required is O(V)

 */
public class TopoSortBFS_KahnsAlgo {

    static class Graph{
        int V;
        ArrayList<Integer>[] adj;

        Graph(int V){
            this.V = V;
            this.adj = new ArrayList[V];
            for(int i=0 ; i < V; i++){
                adj[i] = new ArrayList<Integer>();
            }
        }

        public void addEdge(int u, int v){
            this.adj[u].add(v); // Directed
        }

        public int getV() {
            return V;
        }

        public ArrayList<Integer>[] getAdj() {
            return adj;
        }
    }

    public static List<Integer> topoSortBFS(Graph g){

        int V = g.getV();
        ArrayList<Integer>[] adj = g.getAdj();
        List<Integer> resultList = new ArrayList<Integer>();

        // indegree Array
        int[] indegree = new int[V];
        Arrays.fill(indegree,0);

        //1. Update the indegree for the current vertices
        for(ArrayList<Integer> list: adj){
            for(int vertex: list){
                indegree[vertex]++;
            }
        }

        System.out.println("Indegree Array: "+Arrays.toString(indegree));
        Queue<Integer> queue = new LinkedList<Integer>();

        //2. Add all the vertices with indegree 0 into the queue.
        for(int i=0 ; i < V; i++){
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }

        //
        while(!queue.isEmpty()){  // O(V)
            int curNode = queue.poll();
            resultList.add(curNode);
            // decrement the indegree for all the neighbors. (basically removing the edges outward from curNode)
            for(int neighbor:adj[curNode]){ // O(E)
                indegree[neighbor]--;
                if(indegree[neighbor] == 0){
                    queue.offer(neighbor);
                }
            }
        }
        return resultList;
    }

    public static void main(String[] args) {

        Graph g = new Graph(4);
        g.addEdge(0,1);
        g.addEdge(1,2);
        g.addEdge(3,2);
        g.addEdge(3,1);

        List<Integer> resultList = topoSortBFS(g);
        System.out.println(resultList);
    }
}
