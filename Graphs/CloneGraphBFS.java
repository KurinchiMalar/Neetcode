package Graphs;

// https://leetcode.com/problems/clone-graph/solutions/1496599/java-tc-o-v-e-sc-o-v-both-bfs-dfs-solutions/


/*
/**
 * BFS - Iterative
 *
 * Time Complexity: O(V + E)
 *
 * Space Complexity: O(V). Both Queue and HashMap will take O(V) space
 *
 * V = Number of nodes. E = Number of edges in the graph.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class CloneGraphBFS {
    HashMap<Node,Node> visited = new HashMap<>();
    LinkedList<Node> queue = new LinkedList<>();
    public Node cloneGraphBFS(Node node){
        if(node == null) return null;

        Node copy = new Node(node.val);
        visited.put(node,copy);

        queue.offer(node);

        while(!queue.isEmpty()){

            Node cur = queue.poll();

            for(Node n: cur.neighbors){
                if (!visited.containsKey(n)) {
                    Node newNode = new Node(n.val);
                    visited.get(cur).neighbors.add(newNode); // add this new node to neighbors

                    visited.put(n,newNode);
                    queue.offer(n);

                }else{
                    visited.get(cur).neighbors.add(visited.get(n)); // add copy node of n that is present in visited already into neighbors
                }
            }
        }
        return copy;
    }
}
