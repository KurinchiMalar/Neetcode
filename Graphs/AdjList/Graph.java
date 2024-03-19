package Graphs.AdjList;

import java.util.*;

public class Graph {
    List<GraphNode> nodeList;

    public Graph(ArrayList<GraphNode> nodeList){
        this.nodeList = nodeList;
    }

    public void addUndirectedEdge(int i , int j){

        GraphNode first = nodeList.get(i);
        GraphNode second = nodeList.get(j);
        first.neighbors.add(second);
        second.neighbors.add(first);

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
    TC : O(V + E)
    SC : O(V + E)
     */
    public  String bfs(Graph g){
        Queue<GraphNode> bfsQ = new LinkedList<GraphNode>();
        StringBuilder s = new StringBuilder();
        s.append(" ");
        if(g.nodeList == null || g.nodeList.isEmpty()){
            return null;
        }

        bfsQ.offer(g.nodeList.get(0));
        while(!bfsQ.isEmpty()){
            GraphNode cur = bfsQ.poll();
            if(!cur.isVisited){
                cur.isVisited = true;
                s.append(cur.name+" ");

                for(GraphNode n : cur.neighbors){
                    if(n != null && !n.isVisited){
                        bfsQ.offer(n);
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
        Stack<GraphNode> stk = new Stack<GraphNode>();
        StringBuilder s = new StringBuilder();
        s.append(" ");

        if(g == null || g.nodeList.isEmpty()){
            return null;
        }
        stk.push(nodeList.get(0));
        while(!stk.isEmpty()){

            GraphNode curr = stk.pop();
            if(!curr.isVisited){
                curr.isVisited = true;
                s.append(curr.name+" ");
                List<GraphNode> currNeighbors = curr.neighbors;
                if(currNeighbors != null && !currNeighbors.isEmpty()){
                    for(GraphNode n : currNeighbors){
                        if(!n.isVisited){
                            stk.push(n);
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
        while (!queue.isEmpty()) {
            GraphNode currentNode = queue.remove(0);
            currentNode.isVisited = true;
            System.out.print(currentNode.name + " ");
            for (GraphNode neighbor : currentNode.neighbors) {
                if (!neighbor.isVisited) {
                    queue.add(neighbor);
                    neighbor.isVisited=true;
                }
            }
        }
    }

    void bfs1() {
        for (GraphNode node : nodeList) {
            if (!node.isVisited) {
                bfsVisit(node);
            }
        }
    }
    void dfsVisit(GraphNode node) {
        Stack<GraphNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            GraphNode currentNode = stack.pop();
            currentNode.isVisited = true;
            System.out.print(currentNode.name + " ");
            for (GraphNode neighbor : currentNode.neighbors) {
                if (!neighbor.isVisited) {
                    stack.push(neighbor);
                    neighbor.isVisited=true;  // Avoids pushing visited nodes
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
