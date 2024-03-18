package Graphs.AdjList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
    List<GraphNode> nodeList;

    public Graph(ArrayList<GraphNode> nodeList){
        this.nodeList = nodeList;
    }

    public void addUndirectedEdge(int i , int j){

        GraphNode first = nodeList.get(i);
        GraphNode second = nodeList.get(j);
        first.neighbors.add(second);
        second.neighbors.add(first);

    }
    public String toString() {

        StringBuilder s = new StringBuilder();
        s.append(" ");
        for (int i = 0; i < nodeList.size(); i++) {
            s.append(nodeList.get(i).name + " ");

            for (int j = 0; j < nodeList.get(i).neighbors.size(); j++) {
                if (j == nodeList.get(i).neighbors.size() - 1) { // last in neighbor list
                    s.append(nodeList.get(i).neighbors.get(j).name);
                } else {
                    s.append(nodeList.get(i).neighbors.get(j).name + " -> ");
                }
            }
            s.append("\n");
        }
        return s.toString();
    }

    /*
    TC : O(V + E)
    SC : O(V + E)
     */
    public  String bfs(Graph g){
        Queue<GraphNode> bfsQ = new LinkedList<GraphNode>();
        StringBuilder s = new StringBuilder();
        s.append(" ");
        if(g.nodeList == null || g.nodeList.isEmpty()){
            return null;
        }

        bfsQ.offer(g.nodeList.get(0));
        while(!bfsQ.isEmpty()){
            GraphNode cur = bfsQ.poll();
            if(!cur.isVisited){
                cur.isVisited = true;
                s.append(cur.name+" ");
                System.out.print(cur.name+ "--> ");

                for(GraphNode n : cur.neighbors){
                    if(n != null && !n.isVisited){
                        bfsQ.offer(n);
                    }
                }
            }
        }
        return s.toString();

    }
}
