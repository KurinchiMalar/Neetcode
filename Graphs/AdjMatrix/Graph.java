package Graphs.AdjMatrix;

import java.util.ArrayList;

public class Graph {

    ArrayList<GraphNode> nodeList = new ArrayList<GraphNode>();
    int[][] adjacencyMatrix;

    public Graph(ArrayList<GraphNode> nodeList) {
        this.nodeList = nodeList;
        this.adjacencyMatrix = new int[this.nodeList.size()][this.nodeList.size()];
    }

    public void addUndirectedEdge(int i, int j){
        this.adjacencyMatrix[i][j] = 1;
        this.adjacencyMatrix[j][i] = 1;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("    ");

        for(int i = 0 ; i < nodeList.size(); i++){
            s.append(nodeList.get(i).name+" ");
        }
        s.append("\n");

        for(int i = 0 ; i < nodeList.size(); i++){
            s.append(nodeList.get(i).name+ " : ");

            for(int j : adjacencyMatrix[i]){ // row elements
                s.append(j+" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
