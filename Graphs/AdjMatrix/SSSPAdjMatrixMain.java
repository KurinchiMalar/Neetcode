package Graphs.AdjMatrix;

import java.util.ArrayList;

public class SSSPAdjMatrixMain {

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

        g1.bfsSSSP(g1.nodeList.get(0));

    }
}
