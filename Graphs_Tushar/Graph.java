package Graphs_Tushar;

import java.util.*;

public class Graph<T> {

/*
    1. Graph has n vertices and n-1 edges
            Edges in a list
            Vertices in a map ( id, Vertex)
    2. property to determine directed or undirected.
 */
    private List<Edge<T>> allEdges;
    private Map<Long,Vertex<T>> allVertex;
    boolean isDirected = false;

    public Graph(boolean isDirected){
        allEdges = new ArrayList<Edge<T>>();
        allVertex = new HashMap<Long,Vertex<T>>();
        this.isDirected = isDirected;
    }

    public void addEdge(long id1 , long id2){
        addEdge(id1,id2,0); // if no weight is provided default weight is 0.
    }

    public void addEdge(long id1 , long id2, int weight){
        // get the vertices with id from map. If not present create them.
        Vertex<T> vertex1 = null;
        if(allVertex.containsKey(id1)){
            vertex1 = allVertex.get(id1);
        }else{
            vertex1 = new Vertex<T>(id1);
            allVertex.put(id1,vertex1);
        }

        Vertex<T> vertex2 = null;
        if(allVertex.containsKey(id2)){
            vertex2 = allVertex.get(id2);
        }else{
            vertex2 = new Vertex<T>(id2);
            allVertex.put(id2,vertex2);
        }

        // create the edge now with the vertices
        Edge<T> edge = new Edge<T>(isDirected,vertex1,vertex2,weight);
        allEdges.add(edge);

        vertex1.addAdjacentVertex(edge,vertex2);
        if(!isDirected){
            vertex2.addAdjacentVertex(edge,vertex1);
        }

    }

    public List<Edge<T>> getAllEdges() {
        return allEdges;
    }

    public Collection<Vertex<T>> getAllVertex() {  // return the Vertex nodes
        return allVertex.values();
    }

    public void setDataForVertex(long id, T data){
        if(allVertex.containsKey(id)){
            Vertex<T> vertex = allVertex.get(id);
            vertex.setData(data);
        }
    }

    public boolean isDirected() {
        return isDirected;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "allEdges=" + allEdges +
                ", allVertex=" + allVertex +
                ", isDirected=" + isDirected +
                '}';
    }
}

class Edge<T>{
    /*
    1. Has two vertices
    2. can be directed or undirected
    3. has a weight
     */
    private boolean isDirected = false;
    private Vertex<T> vertex1;
    private Vertex<T> vertex2;
    int weight;

    public Edge(Vertex<T> vertex1, Vertex<T> vertex2) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    public Edge(boolean isDirected, Vertex<T> vertex1, Vertex<T> vertex2) {
        this.isDirected = isDirected;
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    public Edge(boolean isDirected, Vertex<T> vertex1, Vertex<T> vertex2, int weight) {
        this.isDirected = isDirected;
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    public boolean isDirected() {
        return isDirected;
    }

    public Vertex<T> getVertex1() {
        return vertex1;
    }

    public Vertex<T> getVertex2() {
        return vertex2;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return isDirected == edge.isDirected && weight == edge.weight && Objects.equals(vertex1, edge.vertex1) && Objects.equals(vertex2, edge.vertex2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isDirected, vertex1, vertex2, weight);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "isDirected=" + isDirected +
                ", vertex1=" + vertex1 +
                ", vertex2=" + vertex2 +
                ", weight=" + weight +
                '}';
    }
}

class Vertex<T>{
    /*
    1. has an id
    2. has data
    3. List of Edges
    4. List of Adjacent Vertices ( Adj list)
     */
    long id; // index
    private T data;
    private List<Edge<T>> edges = new ArrayList<>();
    private List<Vertex<T>> adjacentVertex = new ArrayList<>();

    Vertex(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }

    public List<Vertex<T>> getAdjacentVertex() {
        return adjacentVertex;
    }

    public void addAdjacentVertex(Edge<T> e, Vertex<T> v){
        edges.add(e);
        adjacentVertex.add(v);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + id +
                ", data=" + data +
                ", edges=" + edges +
                ", adjacentVertex=" + adjacentVertex +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return id == vertex.id && Objects.equals(data, vertex.data) && Objects.equals(edges, vertex.edges) && Objects.equals(adjacentVertex, vertex.adjacentVertex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data, edges, adjacentVertex);
    }
}
