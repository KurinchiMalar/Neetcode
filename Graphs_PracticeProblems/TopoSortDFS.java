package Graphs_PracticeProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
TC : O(V+E)
SC : O(V) // for stack
 */
public class TopoSortDFS {

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

    public void topoVisit( int vertex,ArrayList<Integer>[] adj,boolean[] visited,List<Integer> resultList,Stack<Integer> stk){

        for(int neighbor: adj[vertex]){
            if(!visited[neighbor]){
                topoVisit(neighbor,adj,visited,resultList,stk);
            }
        }
        visited[vertex] = true;
        stk.push(vertex);
    }
    public List<Integer> topoSortDFS(Graph g){
        List<Integer> resultList = new ArrayList<Integer>();
        Stack<Integer> stk = new Stack<>();

        int V = g.getV();
        ArrayList<Integer>[] adj = g.getAdj();

        boolean[] visited = new boolean[V];
        Arrays.fill(visited,false);

        for(int i = 0 ; i < V; i++){
            if(!visited[i]){
                topoVisit(i,adj,visited,resultList,stk);
            }
        }

        while(!stk.isEmpty()){
            resultList.add(stk.pop());
        }
        return resultList;
    }
    public static void main(String[] args) {
        TopoSortDFS ob = new TopoSortDFS();
        Graph g = new Graph(4);
        g.addEdge(0,1);
        g.addEdge(1,2);
        g.addEdge(3,2);
        g.addEdge(3,1);
        List<Integer> resultList = ob.topoSortDFS(g);
        System.out.println("TopoSort DFS : "+resultList);

        Graph g1 = new Graph(4);
        g1.addEdge(1,0);
        g1.addEdge(2,0);
        g1.addEdge(3,1);
        g1.addEdge(3,2);
        System.out.println("TopoSort DFS : "+ ob.topoSortDFS(g1));
    }
}
