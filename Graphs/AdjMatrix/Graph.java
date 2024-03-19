package Graphs.AdjMatrix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
            if(this.adjacencyMatrix[i][nodeIndex] == 1) { // this row label is a neighbor
                neighbors.add(this.nodeList.get(i));
            }
        }
        return neighbors;
    }

    /*
    TC : O(V + E)
    SC : O(V + E)
     */
    public String bfs(Graph g){
        if(g == null || g.nodeList.isEmpty()) return null;
        Queue<GraphNode> bfsQ = new LinkedList<GraphNode>();
        StringBuilder s = new StringBuilder();
        s.append(" ");
        bfsQ.offer(nodeList.get(0));
        while(!bfsQ.isEmpty()){
            GraphNode curr = bfsQ.poll();
            if(!curr.isVisited){
                curr.isVisited = true;
                s.append(curr.name+ " ");

                ArrayList<GraphNode> curNeighbors = g.getNeighbors(curr);
                if(curNeighbors != null && !curNeighbors.isEmpty()){
                    for(GraphNode neighborNode : curNeighbors){
                        if(!neighborNode.isVisited){
                            bfsQ.offer(neighborNode);
                        }
                    }
                }
            }

        }
       return s.toString();
    }

    /*
    TC : O(V + E)
    SC : O(V + E)
     */
    public String dfs(Graph g){
        if( g == null || g.nodeList.isEmpty()) return null;
        Stack<GraphNode> stk = new Stack<GraphNode>();
        StringBuilder s = new StringBuilder();
        s.append(" ");
        stk.push(nodeList.get(0));
        while(!stk.isEmpty()){
            GraphNode curr = stk.pop();
            if(!curr.isVisited){
                curr.isVisited = true;
                s.append(curr.name+" ");

                ArrayList<GraphNode> curNeighbors = g.getNeighbors(curr);
                if(curNeighbors != null && !curNeighbors.isEmpty()){
                    for(GraphNode neighborNode : curNeighbors){
                        if(!neighborNode.isVisited){
                            stk.push(neighborNode);
                        }
                    }
                }
            }
        }
        return s.toString();
    }

    // Below implementations from Tutorial
    void bfsVisit(GraphNode node) {
        LinkedList<GraphNode> queue = new LinkedList<GraphNode>();
        queue.add(node);
        while(!queue.isEmpty()) {
            GraphNode currentNode = queue.remove(0);
            currentNode.isVisited = true;
            System.out.print(currentNode.name + " ");
            ArrayList<GraphNode> neighbors = getNeighbors(currentNode);
            for (GraphNode neighbor: neighbors) {
                if (!neighbor.isVisited) {
                    queue.add(neighbor);
                    neighbor.isVisited = true;
                }
            }
        }
    }

    public void bfs1() {
        for (GraphNode node : nodeList) {
            if(!node.isVisited) {
                bfsVisit(node);
            }
        }
    }

    void dfsVisit(GraphNode node) {
        Stack<GraphNode> stack = new Stack<>();
        stack.push(node);
        while(!stack.isEmpty()) {
            GraphNode currentNode = stack.pop();
            currentNode.isVisited = true;
            System.out.print(currentNode.name + " ");
            ArrayList<GraphNode> neighbors = getNeighbors(currentNode);
            for (GraphNode neighbor : neighbors) {
                if (!neighbor.isVisited) {
                    stack.push(neighbor);
                    neighbor.isVisited = true;
                }
            }

        }
    }

    void dfs1() {
        for (GraphNode node : nodeList) {
            if(!node.isVisited) {
                dfsVisit(node);
            }
        }
    }
}
