package Graphs.AdjList.SSSP;

import java.util.ArrayList;

public class ShortestPathBellManFord {

    /*
BellMan ford can identify and report negative cycle. For graph with negative cycle SSSP cannot be found.
    How ? Iterates V-1 times and on Vth iteration if the cost of nodes changes --> there is a negative cycle

Does not use priority queue, it iterates through all possible nodes and its neighbors.
 */
/*
TC : O(V * E)
SC : O(V) // to store the cost from source to all vertices as part of each graph node.
 */
    public static void bellManFord(WeightedGraph g){
        WeightedNode node = g.nodeList.get(0); // start Node
        node.cost = 0; // rest of the nodes cost = (infinity) this is marked in constructor itself.

        for(int i = 0 ; i < g.nodeList.size(); i++){ // V-1 iterations

            for(WeightedNode curNode : g.nodeList){
                for(WeightedNode neighbor : curNode.neighbors){
                    //if(!neighbor.isVisited){
                    int costOfPathFromCurNode= curNode.cost + curNode.weightMap.get(neighbor);
                    if(neighbor.cost > costOfPathFromCurNode){
                        neighbor.cost = costOfPathFromCurNode;
                        // neighbor.isVisited = true;
                        neighbor.parent = curNode;
                    }
                    // }

                }
            }
        }

        // Vth iteration - how do you determine if the distance is changing or not
        // ---> if node.cost > newPathCost (this implies there is a lesser cost path ==> negative cycle)
        for(WeightedNode curNode1: g.nodeList){
            for(WeightedNode neighbor1 : curNode1.neighbors){
                int costOfPathFromCurNode = curNode1.cost + curNode1.weightMap.get(neighbor1);
                if(neighbor1.cost > costOfPathFromCurNode){
                    System.out.println("Negative cycle");
                    System.out.println("Vertex name : "+neighbor1.name);
                    System.out.println("Old cost : "+neighbor1.cost);
                    System.out.println("New cost : "+costOfPathFromCurNode);
                    return;
                }
            }
        }
        System.out.println("Negative cycle not found");

        for(WeightedNode nodeToCheck: g.nodeList){
            System.out.print("Node: "+nodeToCheck.name + " , cost : "+nodeToCheck.cost+" , Path: ");
            g.printPath(nodeToCheck);
            System.out.println();
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
        System.out.println("BellManFord from source : A ");
        bellManFord(newGraph);
                        /*
BellManFord from source : A
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
        System.out.println("-------------------------------------------------");
        System.out.println("BellManFord from source : A ");
        bellManFord(newGraph1);
        /*
BellManFord from source : A
Negative cycle
Vertex name : C
Old cost : -4
New cost : -6

         */

    }
}
