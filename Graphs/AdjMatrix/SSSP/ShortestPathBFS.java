package Graphs.AdjMatrix.SSSP;

import Graphs.AdjMatrix.Graph;
import Graphs.AdjMatrix.GraphNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathBFS {

    /*
   TC : O(V + E) we traverse through all  the vertices and edges in BFS approach
   SC : O(V)
    */
    public static void bfsSSSP(Graph g){

        GraphNode node = g.nodeList.get(0); // start node
        if(node == null) return;

        Queue<GraphNode> queue = new LinkedList<>();
        queue.offer(node);

        while(!queue.isEmpty()){
            GraphNode curr = queue.poll();
            System.out.print("Paths of node : "+curr.name+" = ");
            g.printPath(curr);
            curr.isVisited = true;
            System.out.println();
            if(!g.getNeighbors(curr).isEmpty()){
                for(GraphNode neighbor : g.getNeighbors(curr)){
                    if(!neighbor.isVisited){
                        queue.offer(neighbor);
                        neighbor.isVisited = true;
                        neighbor.parent = curr;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<GraphNode> nodeList = new ArrayList<>();
        nodeList.add(new GraphNode("A",0));
        nodeList.add(new GraphNode("B",1));
        nodeList.add(new GraphNode("C",2));
        nodeList.add(new GraphNode("D",3));
        nodeList.add(new GraphNode("E",4));
        nodeList.add(new GraphNode("F",5));
        nodeList.add(new GraphNode("G",6));

        Graph g1 = new Graph(nodeList);
        g1.addUndirectedEdge(0,1);
        g1.addUndirectedEdge(0,2);
        g1.addUndirectedEdge(1,3);
        g1.addUndirectedEdge(1,6);
        g1.addUndirectedEdge(2,3);
        g1.addUndirectedEdge(2,4);
        g1.addUndirectedEdge(4,5);
        g1.addUndirectedEdge(5,6);

        bfsSSSP(g1);
    }
}
