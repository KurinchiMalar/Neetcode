package Graphs.AdjMatrix;

import java.util.*;

public class Graph {

    public ArrayList<GraphNode> nodeList = new ArrayList<GraphNode>();
    int[][] adjacencyMatrix;

    public Graph(ArrayList<GraphNode> nodeList) {
        this.nodeList = nodeList;
        this.adjacencyMatrix = new int[this.nodeList.size()][this.nodeList.size()];
    }

    public void addUndirectedEdge(int i, int j){
        this.adjacencyMatrix[i][j] = 1;
        this.adjacencyMatrix[j][i] = 1;
    }

    public void addDirectedEdge(int i, int j){
        this.adjacencyMatrix[i][j] = 1;
    }

    // get Neighbors
    /*
    A B C D E F G
A : 0 1 1 0 0 0 0
B : 1 0 0 1 0 0 1
C : 1 0 0 1 1 0 0
D : 0 1 1 0 0 0 0
E : 0 0 1 0 0 1 0
F : 0 0 0 0 1 0 1
G : 0 1 0 0 0 1 0

      Get the node index from list (node list)
      iterate through all rows of this column and where all you see 1, those row indices are all neighbors
     */

    public ArrayList<GraphNode> getNeighbors(GraphNode node){
        ArrayList<GraphNode> neighbors = new ArrayList<GraphNode>();

        int nodeIndex = node.index;
        for(int i = 0 ; i < this.adjacencyMatrix.length; i++){ // iterate all rows
            if(this.adjacencyMatrix[nodeIndex][i] == 1) { // whichever column for this row is marked 1 is a neighbor
                neighbors.add(this.nodeList.get(i));
            }
        }
        return neighbors;
    }

    public  void clearVisits(){
        List<GraphNode> nodeList = this.nodeList;
        for(GraphNode gnode : nodeList){
            gnode.isVisited =false;
        }
    }

    public void printPath(GraphNode node){
        if(node == null) return;
        if(node.parent != null){
            printPath((node.parent));
        }
        System.out.print(node.name+" ");
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
