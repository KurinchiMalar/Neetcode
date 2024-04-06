package Graphs_Tushar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/KruskalMST.java
 */

/*
TC : O(E log E) + O(E) // sorting edges + n operations in disjoint set = O(n) for E edges ==> O(E)
SC : O(V + E) // disjoint set union and Find , n sets ==> O(n) worstcase.
 */
public class KruskalMST {

    public class EdgeComparator implements Comparator<Edge<Integer>> {

        /*
         , 4 , 1 , 1 , 3 , 2 , 2 , 3 , 2 , 5 , 8
            After sort
          , 1 , 1 , 2 , 2 , 2 , 3 , 3 , 4 , 5 , 8

         */
        @Override
        public int compare(Edge<Integer> edge1, Edge<Integer> edge2) {
            if (edge1.getWeight() <= edge2.getWeight()) {
                return -1;  // as expected
            } else {
                return 1; // requires sorting
            }
        }
    }
    public void printEdges(List<Edge<Integer>> allEdges){

        for (Edge edge: allEdges){
            System.out.print(" , "+edge.getWeight());
        }
        System.out.println();
    }
    public List<Edge<Integer>> getMST(Graph<Integer> graph){

        List<Edge<Integer>> allEdges = graph.getAllEdges();
        printEdges(allEdges);
        EdgeComparator edgeComparator = new EdgeComparator();

        Collections.sort(allEdges,edgeComparator);
        //System.out.println("After sort");
        //printEdges(allEdges);

        DisjointSet disjointSet = new DisjointSet();
        //For all the vertices create independent sets first
        for(Vertex<Integer> vertex:graph.getAllVertex()){
            disjointSet.makeSet(vertex.getId());
        }

        List<Edge<Integer>> resultEdge = new ArrayList<Edge<Integer>>(); // List of edges forming the MST

        // allEdges has the edges sorted in minimum order of weights
        for(Edge<Integer> edge : allEdges){

            // get the sets correspnding to the vertices
            long Vertex1Set = disjointSet.findSet(edge.getVertex1().getId());
            long Vertex2Set = disjointSet.findSet(edge.getVertex2().getId());

            // if the vertices are in the same sets --> cycle so ignore, otherwise they can go in resultEdge forming MST

            if(Vertex1Set == Vertex2Set){ // ignore this edge
                continue;
            }else{
                resultEdge.add(edge);
                disjointSet.union(edge.getVertex1().getId(),edge.getVertex2().getId()); // form the connection
            }


        }
        return  resultEdge;
    }
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<Integer>(false); // undirected graph

        graph.addEdge(1, 2, 4);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 5, 1);
        graph.addEdge(2, 6, 3);
        graph.addEdge(2, 4, 2);
        graph.addEdge(6, 5, 2);
        graph.addEdge(6, 4, 3);
        graph.addEdge(4, 7, 2);
        graph.addEdge(3, 4, 5);
        graph.addEdge(3, 7, 8);

        KruskalMST mst = new KruskalMST();
        List<Edge<Integer>> resultMstEdges = mst.getMST(graph);

        for(Edge<Integer> edge: resultMstEdges){
            System.out.println(edge.getVertex1()+" "+edge.getVertex2());
        }

    }
}
