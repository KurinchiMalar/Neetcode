package blind75;

import java.util.ArrayList;

//https://www.programiz.com/dsa/graph-adjacency-list
public class GraphAdjList {

    public static void addEdge(ArrayList<ArrayList<Integer>> al, int source, int destination){
        al.get(source).add(destination);
        al.get(destination).add(source);
    }

    static public void printGraph(ArrayList<ArrayList<Integer>> al) {

        System.out.println("Printing Graph");
        for(int i = 0 ; i < al.size(); i++){
            System.out.println("Vertex : "+i);
            for(int j = 0 ; j < al.get(i).size(); j++){
                System.out.println(" -> "+ al.get(i).get(j));
            }
            System.out.println();
        }

    }
    public static void main(String[] args) {
        //create the graph

        int V = 5;
        // everu vertex needs to hold a list of adjacents.... we need list of adj lists
        ArrayList<ArrayList<Integer>> al = new ArrayList<>();

        // initialize empty lists
        for(int i = 0 ; i < V ; i++){
            al.add(new ArrayList<>());
        }

        addEdge(al,0,1);
        addEdge(al,0,2);
        addEdge(al,0,3);
        addEdge(al,1,2);

        printGraph(al);
    }
}
