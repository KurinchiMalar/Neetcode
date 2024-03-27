package Graphs.AdjList.SSSP;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class ShortestPathDjikstra {


    /*
    TC : O( E log V)
    SC :
     Worst case O(V*V) Dense undirected graph where each V has V-1 edges  ;
     Average case O(V+E)
     */
    public static void djikstra(WeightedGraph g){
        PriorityQueue<WeightedNode> pq = new PriorityQueue<>();
        WeightedNode node = g.nodeList.get(0); // start Node
        node.cost = 0; // initial cost
        pq.addAll(g.nodeList); // requires The WeightedNode to be Comparable

        while(!pq.isEmpty()){
            WeightedNode cur = pq.poll();
            cur.isVisited = true;
            for(WeightedNode neighbor : cur.neighbors){
                if(!neighbor.isVisited){
                    int costFromCurNode = cur.cost + cur.weightMap.get(neighbor);
                    if(neighbor.cost > costFromCurNode){
                        neighbor.cost = costFromCurNode; // this is automatically reflected in weightmap of curNode
                        neighbor.parent = cur;
                        neighbor.isVisited = true;
                        pq.remove(neighbor); // need to re-add in pq with new cost (to shuffle and have the most min on top
                        pq.add(neighbor);
                    }
                }
            }
        }

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
        System.out.println("Djiktra from source : A ");
        djikstra(newGraph);
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



    }
}
