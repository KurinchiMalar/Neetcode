package Graphs.AdjMatrix;

public class GraphNode {

    public String name;
    public int index;
    public boolean isVisited;
    GraphNode parent;

    public GraphNode(String name, int index) {
        this.name = name;
        this.index = index;
        this.isVisited = false;
        this.parent = null;
    }
}
