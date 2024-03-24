package Graphs.SSSP.Dijkstra;

import Graphs.AdjMatrix.GraphNode;

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
            System.out.println("Node: "+nodeToCheck.name + " , cost : "+nodeToCheck.cost+" , Path: ");
            printPath(nodeToCheck);
        }
    }
}
