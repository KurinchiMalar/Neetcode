package Graphs.AdjList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphAdjListMain {


    public static void main(String[] args) {

        ArrayList<GraphNode> nodeList = new ArrayList<GraphNode>();
        nodeList.add(new GraphNode("A",0));
        nodeList.add(new GraphNode("B",1));
        nodeList.add(new GraphNode("C",2));
        nodeList.add(new GraphNode("D",3));
        nodeList.add(new GraphNode("E",4));

        Graph g = new Graph(nodeList);
        g.addUndirectedEdge(0,1);
        g.addUndirectedEdge(0,2);
        g.addUndirectedEdge(0,3);
        g.addUndirectedEdge(1,4);
        g.addUndirectedEdge(2,3);
        g.addUndirectedEdge(3,4);

        g.bfs(g);

        ArrayList<GraphNode> nodeList1 = new ArrayList<GraphNode>();
        nodeList1.add(new GraphNode("A",0));
        nodeList1.add(new GraphNode("B",1));
        nodeList1.add(new GraphNode("C",2));
        nodeList1.add(new GraphNode("D",3));
        nodeList1.add(new GraphNode("E",4));
        nodeList1.add(new GraphNode("F",5));
        nodeList1.add(new GraphNode("G",6));

        Graph g1 = new Graph(nodeList1);
        g1.addUndirectedEdge(0,1);
        g1.addUndirectedEdge(0,2);
        g1.addUndirectedEdge(1,3);
        g1.addUndirectedEdge(1,6);
        g1.addUndirectedEdge(2,3);
        g1.addUndirectedEdge(2,4);
        g1.addUndirectedEdge(4,5);
        g1.addUndirectedEdge(5,6);

        System.out.println(g.toString());
        System.out.println("--------------------");
        System.out.println(g1.toString());
        System.out.println("************");
        g1.bfs(g1);

    }
}
