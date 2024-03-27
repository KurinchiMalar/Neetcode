package Graphs.AdjList.SSSP;

import java.util.ArrayList;
import java.util.HashMap;

public class WeightedNode implements Comparable<WeightedNode> {

    String name;
    int index;
    public boolean isVisited;
    ArrayList<WeightedNode>  neighbors; // adj list
    WeightedNode parent;
    int cost;

    public HashMap<WeightedNode,Integer> weightMap = new HashMap<>();
    public WeightedNode(String name , int index){
        this.name = name;
        this.index = index;
        this.cost = Integer.MAX_VALUE;
        this.neighbors = new ArrayList<WeightedNode>();
        this.parent = null;
        this.isVisited = false;
    }

    @Override
    public int compareTo(WeightedNode o) {
        return this.cost - o.cost;
    }
}
