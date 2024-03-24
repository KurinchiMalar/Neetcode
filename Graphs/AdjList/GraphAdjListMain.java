package Graphs.AdjList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphAdjListMain {

    public static void clearVisits(List<GraphNode> nodeList){
        for(GraphNode gnode : nodeList){
            gnode.isVisited =false;
        }
    }


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

        System.out.println(g.toString());
        //System.out.println("BFS Adj list: " + g.bfs(g));
        //clearVisits(g.nodeList);
        //System.out.println("DFS Adj list: " + g.dfs(g));
        clearVisits(g.nodeList);
        System.out.println("From tutorial...");  // MOST optimized avoids duplicate entries , Works for unconnected graphs as well.
        g.bfs1();
        clearVisits(g.nodeList);
        System.out.println();
        g.dfs1();
        clearVisits(g.nodeList);
        System.out.println();

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


        System.out.println("-----------------------------");
        System.out.println("*****************************");
        System.out.println("-----------------------------");
        System.out.println(g1.toString());
        //System.out.println("BFS Adj list: " + g1.bfs(g1));
        //clearVisits(g1.nodeList);
        //System.out.println("DFS Adj list: " + g1.dfs(g1));
        clearVisits(g1.nodeList);
        System.out.println("From tutorial..."); // // MOST optimized avoids duplicate entries , Works for unconnected graphs as well.
        g1.bfs1();
        clearVisits(g1.nodeList);
        System.out.println();
        g1.dfs1();
        clearVisits(g1.nodeList);
        System.out.println();
        System.out.println("TOPOLOGICAL SORT");

        ArrayList<GraphNode> nodeList2 = new ArrayList<GraphNode>();
        nodeList2.add(new GraphNode("A",0));
        nodeList2.add(new GraphNode("B",1));
        nodeList2.add(new GraphNode("C",2));
        nodeList2.add(new GraphNode("D",3));
        nodeList2.add(new GraphNode("E",4));

        Graph gd1 = new Graph(nodeList2);
        gd1.addDirectedEdge(0,1);
        gd1.addDirectedEdge(0,2);
        gd1.addDirectedEdge(0,3);
        gd1.addDirectedEdge(1,4);
        gd1.addDirectedEdge(2,3);
        gd1.addDirectedEdge(3,4);
        //clearVisits(g1.nodeList);
        gd1.topoSort(gd1.nodeList);
        System.out.println();
        //g1.topoSort(g1.nodeList);


        ArrayList<GraphNode> nodeListBig = new ArrayList<GraphNode>();
        nodeListBig.add(new GraphNode("A",0));
        nodeListBig.add(new GraphNode("B",1));
        nodeListBig.add(new GraphNode("C",2));
        nodeListBig.add(new GraphNode("D",3));
        nodeListBig.add(new GraphNode("E",4));
        nodeListBig.add(new GraphNode("F",5));
        nodeListBig.add(new GraphNode("G",6));
        nodeListBig.add(new GraphNode("H",7));

        Graph gd2 = new Graph(nodeListBig);
        gd2.addDirectedEdge(0,2);
        gd2.addDirectedEdge(2,4);
        gd2.addDirectedEdge(4,7);
        gd2.addDirectedEdge(4,5);
        gd2.addDirectedEdge(5,6);
        gd2.addDirectedEdge(1,2);
        gd2.addDirectedEdge(1,3);
        gd2.addDirectedEdge(3,5);
        clearVisits(gd2.nodeList);
        System.out.println("TOPOLOGICAL SORT");  // Expected :  B D A C E F G H
        System.out.println();
        gd2.topoSort(gd2.nodeList);
        System.out.println();


    }
}
