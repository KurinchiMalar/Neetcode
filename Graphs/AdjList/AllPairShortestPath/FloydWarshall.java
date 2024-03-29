package Graphs.AdjList.AllPairShortestPath;

import Graphs.AdjList.SSSP.WeightedGraph;
import Graphs.AdjList.SSSP.WeightedNode;

import java.util.ArrayList;
import java.util.List;


/*
TC : O(V pow 3) --- V*V*V
SC : O(V pow 2) --- V*V
 */
public class FloydWarshall {

    public static void floydWarshall(WeightedGraph g){

        List<WeightedNode> nodeList = g.nodeList;
        int V = nodeList.size(); // number of vertices
        int[][] dist = new int[V][V];

        // Update the self nodes dist to 0 , known distances , infinity for unknowns
        for(int i = 0 ; i < V; i++){
            WeightedNode first = nodeList.get(i);
            for(int j = 0 ; j < V ; j++){
                WeightedNode second = nodeList.get(j);
                if(i == j){ // self nodes
                    dist[i][j] = 0;
                }else{
                    dist[i][j] = first.weightMap.getOrDefault(second,Integer.MAX_VALUE/10); // direct link update and rest to infinity (/10 to avoid arithmetic overflow)
                }
            }
        }

        // Now we need to figure out the unknowns that may pass through knode(iax) and re-adjust dist if applicable
        for(int k=0 ; k < V; k++){
            for(int i=0 ; i < V; i++){
                for(int j=0; j < V; j++){
                    int distThroughK = dist[i][k] + dist[k][j];
                    if(dist[i][j] > distThroughK){
                        dist[i][j] = distThroughK;
                    }
                }
            }
        }

        for(int i=0 ; i < V; i++){
            System.out.print("Printing distance list for node " +nodeList.get(i).name+" : ");
            for(int j=0; j < V; j++){
                System.out.print(dist[i][j]+" ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        ArrayList<WeightedNode> nodeList = new ArrayList<>();
        nodeList.add(new WeightedNode("A",0));
        nodeList.add(new WeightedNode("B",1));
        nodeList.add(new WeightedNode("C",2));
        nodeList.add(new WeightedNode("D",3));

        WeightedGraph newGraph = new WeightedGraph(nodeList);
        newGraph.addWeightedEdge(0,3,1);
        newGraph.addWeightedEdge(0,1,8);
        newGraph.addWeightedEdge(1,2,1);
        newGraph.addWeightedEdge(2,0,4);
        newGraph.addWeightedEdge(3,1,2);
        newGraph.addWeightedEdge(3,2,9);
        System.out.println("FloydWarshall...");
        floydWarshall(newGraph);

    }
}
