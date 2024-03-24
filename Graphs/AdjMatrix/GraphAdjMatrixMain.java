package Graphs.AdjMatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphAdjMatrixMain {

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
        //System.out.println("BFS: "+g.bfs(g));
        //clearVisits(g.nodeList);
        //System.out.println("DFS: "+g.dfs(g));
        System.out.println("From Tutorial");
        clearVisits(g.nodeList);
        //g.bfs1();
        clearVisits(g.nodeList);
        System.out.println();
        //g.dfs1();


        //System.out.println(Arrays.deepToString(g.adjacencyMatrix));
        System.out.println();
        System.out.println("*************************************");
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
        System.out.println(g1.toString());
        //System.out.println("BFS: "+g1.bfs(g1));
        //clearVisits(g1.nodeList);
        //System.out.println("DFS: "+g1.dfs(g1));
        System.out.println("From Tutorial");
        clearVisits(g1.nodeList);
        //g1.bfs1();
        clearVisits(g1.nodeList);
        System.out.println();
        //g1.dfs1();


        ArrayList<GraphNode> neighb = g1.getNeighbors(nodeList1.get(0));
        /*System.out.println("Neighbors of "+nodeList1.get(0).name);
        for(GraphNode n : neighb){
            System.out.print(" "+n.name);
        }*/


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
        clearVisits(gd1.nodeList);
        System.out.println(gd1.toString());
        System.out.println("TOPOLOGICAL SORT");
        System.out.println();
        gd1.topoSort(gd1.nodeList);
        clearVisits(gd1.nodeList);

        System.out.println();
        System.out.println(gd1.toString());


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
        System.out.println(gd2.toString());
        System.out.println("TOPOLOGICAL SORT"); // Expected :  B D A C E F G H
        System.out.println();
        gd2.topoSort(gd2.nodeList);
        //gd2.topologicalSort();
        System.out.println();


    }
}
