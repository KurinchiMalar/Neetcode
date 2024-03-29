package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}


Test case format:

For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.

An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.

Example 1:


Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]
Explanation: There are 4 nodes in the graph.
1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
Example 2:


Input: adjList = [[]]
Output: [[]]
Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
Example 3:

Input: adjList = []
Output: []
Explanation: This an empty graph, it does not have any nodes.


Constraints:

The number of nodes in the graph is in the range [0, 100].
1 <= Node.val <= 100
Node.val is unique for each node.
There are no repeated edges and no self-loops in the graph.
The Graph is connected and all nodes can be visited starting from the given node.



https://leetcode.com/problems/clone-graph/solutions/1793436/java-simple-code-with-heavy-comments/
 */

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

/*
TC = O(V + E) since we have to visit every vertex and edge once
SC = O(V + E ) to create copy of all vertices and edges + O(V) for visited array = O(V+E)
 */
class CloneGraphDFS {
    Node[] visited = new Node[101]; // The number of nodes in the graph is in the range [0, 100]
    public Node cloneGraphOld(Node node) {
        if(node == null) return null;
        Node copy = new Node(node.val);
        dfs(node,copy,visited);
        return copy;
    }

    public void dfs(Node node, Node copy, Node[] visited){
        visited[copy.val] = copy;

        for(Node n : node.neighbors){
            if(visited[n.val] == null) { // not visited
                Node newNode = new Node(n.val); //making a copy if copy not present already
                copy.neighbors.add(newNode);
                dfs(n,newNode,visited);
            }else{
                copy.neighbors.add(visited[n.val]); // copy of this node already present map it
            }
        }
    }

    /*
    Cleaner implementation with HashMap
     */

    public Node cloneGraph(Node node) {

        if(node == null)return null;
        Node copy = new Node(node.val);
        HashMap<Node,Node> visited = new HashMap<>();
        dfs(node,copy,visited);
        return copy;

    }
    public void dfs(Node node, Node copy, HashMap<Node,Node> visited){
        visited.put(node,copy);

        for(Node n:node.neighbors){
            if(!visited.containsKey(n)){
                Node newNode = new Node(n.val); //create a copy
                copy.neighbors.add(newNode);
                dfs(n,newNode,visited);
            }else{
                copy.neighbors.add(visited.get(n));
            }
        }
    }
}