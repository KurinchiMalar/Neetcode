package Graphs.SSSP.Dijkstra_BellManFord;

import java.util.ArrayList;

public class DijikstraBellManFordMain {

    static public void clearVisits(ArrayList<WeightedNode> nodeList){
        for(WeightedNode node:nodeList){
            node.isVisited = false;
        }
    }

    public static void main(String[] args) {
        ArrayList<WeightedNode> nodeList = new ArrayList<>();
        nodeList.add(new WeightedNode("A",0));
        nodeList.add(new WeightedNode("B",1));
        nodeList.add(new WeightedNode("C",2));
        nodeList.add(new WeightedNode("D",3));
        nodeList.add(new WeightedNode("E",4));
        nodeList.add(new WeightedNode("F",5));
        nodeList.add(new WeightedNode("G",6));
        //nodeList.add(new WeightedNode("A",7));
        WeightedGraph newGraph = new WeightedGraph(nodeList);
        newGraph.addWeightedEdge(0,1,2);
        newGraph.addWeightedEdge(0,2,5);
        newGraph.addWeightedEdge(1,2,6);
        newGraph.addWeightedEdge(1,3,1);
        newGraph.addWeightedEdge(1,4,3);
        newGraph.addWeightedEdge(2,5,8);
        newGraph.addWeightedEdge(3,4,4);
        newGraph.addWeightedEdge(4,6,9);
        newGraph.addWeightedEdge(5,6,7);

        System.out.println(newGraph.toString());
        System.out.println("Djiktra from source : A ");
        newGraph.djikstra(nodeList.get(0));
        /*
Djiktra from source : A
Node: A , cost : 0 , Path: A
Node: B , cost : 2 , Path: A B
Node: C , cost : 5 , Path: A C
Node: D , cost : 3 , Path: A B D
Node: E , cost : 5 , Path: A B E
Node: F , cost : 13 , Path: A C F
Node: G , cost : 14 , Path: A B E G
         */
        clearVisits(nodeList);
        System.out.println("-------------------------------------------------");
        System.out.println("BellManFord from source : A ");
        //newGraph.bellManFord(nodeList.get(0));
        /*
BellManFord from source : A
Negative cycle not found
Node: A , cost : 0 , Path: A
Node: B , cost : 2 , Path: A B
Node: C , cost : 5 , Path: A C
Node: D , cost : 3 , Path: A B D
Node: E , cost : 5 , Path: A B E
Node: F , cost : 13 , Path: A C F
Node: G , cost : 14 , Path: A B E G
         */

        ArrayList<WeightedNode> nodeList1 = new ArrayList<>();
        nodeList1.add(new WeightedNode("A",0));
        nodeList1.add(new WeightedNode("B",1));
        nodeList1.add(new WeightedNode("C",2));
        nodeList1.add(new WeightedNode("D",3));
        nodeList1.add(new WeightedNode("E",4));

        //nodeList.add(new WeightedNode("A",7));
        WeightedGraph newGraph1 = new WeightedGraph(nodeList1);
        newGraph1.addWeightedEdge(0,1,-1);
        newGraph1.addWeightedEdge(0,2,4);
        newGraph1.addWeightedEdge(1,2,3);
        newGraph1.addWeightedEdge(1,3,2);
        newGraph1.addWeightedEdge(1,4,2);
        newGraph1.addWeightedEdge(3,2,5);
        newGraph1.addWeightedEdge(3,1,1);
        newGraph1.addWeightedEdge(4,3,-6);

        System.out.println(newGraph1.toString());

        clearVisits(nodeList1);
        System.out.println("-------------------------------------------------");
        System.out.println("BellManFord from source : A ");
        newGraph1.bellManFord(nodeList1.get(0));

    }
}
