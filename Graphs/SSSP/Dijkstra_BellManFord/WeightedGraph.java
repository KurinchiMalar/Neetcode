package Graphs.SSSP.Dijkstra_BellManFord;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class WeightedGraph {
    ArrayList<WeightedNode> nodeList = new ArrayList<WeightedNode>();

    public WeightedGraph(ArrayList<WeightedNode> nodeList){
        this.nodeList = nodeList;
    }
    public void printPath(WeightedNode node){
        if(node == null) return;
        if(node.parent != null){
            printPath((node.parent));
        }
        System.out.print(node.name+" ");
    }
    public void addWeightedEdge(int i, int j, int d) {
        WeightedNode first = nodeList.get(i);
        WeightedNode second = nodeList.get(j);
        first.neighbors.add(second);
        first.weightMap.put(second,d);  // populate the weighmap with the weight of current path.
    }

    public String toString() {

        StringBuilder s = new StringBuilder();
        s.append(" ");
        for (int i = 0; i < nodeList.size(); i++) {
            s.append(nodeList.get(i).name + " ");

            for (int j = 0; j < nodeList.get(i).neighbors.size(); j++) {
                if (j == nodeList.get(i).neighbors.size() - 1) { // last in neighbor list
                    s.append(nodeList.get(i).neighbors.get(j).name);
                } else {
                    s.append(nodeList.get(i).neighbors.get(j).name + " -> ");
                }
            }
            s.append("\n");
        }
        return s.toString();
    }


    /*
    TC : O( E log V)
    SC :
     Worst case O(V*V) Dense undirected graph where each V has V-1 edges  ;
     Average case O(V+E)
     */
    public void djikstra(WeightedNode node){
        PriorityQueue<WeightedNode> pq = new PriorityQueue<>();
        node.cost = 0; // initial cost
        pq.addAll(nodeList); // requires The WeightedNode to be Comparable

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

        for(WeightedNode nodeToCheck: nodeList){
            System.out.print("Node: "+nodeToCheck.name + " , cost : "+nodeToCheck.cost+" , Path: ");
            printPath(nodeToCheck);
            System.out.println();
        }
    }

    /*
    BellMan ford can identify and report negative cycle. For graph with negative cycle SSSP cannot be found.
        How ? Iterates V-1 times and on Vth iteration if the cost of nodes changes --> there is a negative cycle

    Does not use priority queue, it iterates through all possible nodes and its neighbors.
     */
/*
TC : O(V * E)
SC : O(V) // to store the cost from source to all vertices as part of each graph node.
 */
   public void bellManFord(WeightedNode node){

        node.cost = 0; // rest of the nodes cost = (infinity) this is marked in constructor itself.

        for(int i = 0 ; i < nodeList.size(); i++){ // V-1 iterations

            for(WeightedNode curNode : nodeList){
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
        for(WeightedNode curNode1: nodeList){
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

        for(WeightedNode nodeToCheck: nodeList){
            System.out.print("Node: "+nodeToCheck.name + " , cost : "+nodeToCheck.cost+" , Path: ");
            printPath(nodeToCheck);
            System.out.println();
        }
    }
}
