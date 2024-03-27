package Graphs.AdjList;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopologicalSort {

    static void topoVisit(GraphNode node, Stack<GraphNode> stk){

        for(GraphNode neighbor: node.neighbors){
            if(!neighbor.isVisited){
                topoVisit(neighbor,stk);
            }
        }
        node.isVisited = true;
        stk.push(node);
    }

    /*
TC : O(V + E)
SC : O(V + E)
*/
    public static void topoSort(Graph g){

        List<GraphNode> nodeList = g.nodeList;
        Stack<GraphNode> stk = new Stack<GraphNode>();
        if(nodeList == null || nodeList.isEmpty()){
            return;
        }
        for(GraphNode node: nodeList){
            if(!node.isVisited){
                topoVisit(node, stk);
            }
        }
        while(!stk.isEmpty()){
            System.out.print(stk.pop().name+" ");
        }
    }

    public static void main(String[] args){
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

        topoSort(gd1);
        System.out.println();



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
        System.out.println("TOPOLOGICAL SORT");  // Expected :  B D A C E F G H
        topoSort(gd2);

    }
}
