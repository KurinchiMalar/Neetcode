package Graphs.AdjList;

import java.util.ArrayList;

public class GraphNode {
    public String name;
    public int index;
    boolean isVisited;
    public ArrayList<GraphNode> neighbors;

    GraphNode parent;

    public GraphNode(String name , int index){
        this.name = name;
        this.index = index;
        this.isVisited = false;
        this.neighbors = new ArrayList<GraphNode>();
        this.parent = null;

    }
}
