package Graphs.AdjList;

import java.util.*;

public class Graph {
    public List<GraphNode> nodeList;

    public Graph(ArrayList<GraphNode> nodeList){
        this.nodeList = nodeList;
    }

    public void addDirectedEdge(int i , int j){

        GraphNode first = nodeList.get(i);
        GraphNode second = nodeList.get(j);
        first.neighbors.add(second);
    }

    public void addUndirectedEdge(int i , int j){

        GraphNode first = nodeList.get(i);
        GraphNode second = nodeList.get(j);
        first.neighbors.add(second);
        second.neighbors.add(first);

    }
    public  void clearVisits(){
        List<GraphNode> nodeList = this.nodeList;
        for(GraphNode gnode : nodeList){
            gnode.isVisited =false;
        }
    }
    /*
    Given a GraphNode print the path until this node.
     */
    public void printPath(GraphNode node){
        if(node.parent != null){
            printPath(node.parent);
        }
        System.out.print(node.name+"->");
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

}
