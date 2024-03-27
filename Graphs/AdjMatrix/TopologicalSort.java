package Graphs.AdjMatrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopologicalSort {

    static void topoVisit(Graph g, GraphNode node, Stack<GraphNode> stk){
        if(node == null) return;
        for(GraphNode neighbor : g.getNeighbors(node)){
            if(!neighbor.isVisited){
                topoVisit(g,neighbor,stk);
            }
        }
        node.isVisited = true;
        stk.push(node);
    }

    /*
TC : O(V + E)
SC : O(V + E)
*/
    static void topoSort(Graph g){
        List<GraphNode> nodeList = g.nodeList;
        Stack<GraphNode> stk = new Stack<>();
        if(nodeList == null || nodeList.isEmpty()){
            return;
        }
        for(GraphNode node:nodeList){
            if(!node.isVisited){
                topoVisit(g,node,stk);
            }
        }

        while(!stk.isEmpty()){
            System.out.print(stk.pop().name+" ");
        }
    }

    public static void main(String[] args) {
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
        gd1.clearVisits();
        System.out.println(gd1.toString());
        topoSort(gd1);
        gd1.clearVisits();

        System.out.println("****************************************************");

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
        gd2.clearVisits();
        System.out.println(gd2.toString());
        System.out.println("TOPOLOGICAL SORT"); // Expected :  B D A C E F G H
        System.out.println();
        topoSort(gd2);



    }
}
