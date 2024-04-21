package Graphs_PracticeProblems;

import java.util.*;
import java.util.Queue.*;
import java.util.Stack.*;
import java.util.stream.*;
/*
https://leetcode.com/problems/course-schedule-ii/description/
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.

Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
Example 2:

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
Example 3:

Input: numCourses = 1, prerequisites = []
Output: [0]


Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= numCourses * (numCourses - 1)
prerequisites[i].length == 2
0 <= ai, bi < numCourses
ai != bi
All the pairs [ai, bi] are distinct.
 */
/*
TC : O(V+E)
SC : O(V+E)
(Space Complexity:
The graph (g) will hold at most all prerequisites, which is O(E).
The in-degree list (indeg) holds an entry for each course, taking O(V).
The queue (q) can at most hold all vertices with zero in-degree at once, in the worst case O(V).
The answer list (ans) also takes O(V) since it contains all courses in the topological order.)
 */
class CourseScheduleII {
    static class Graph{
        int V;
        ArrayList<Integer>[] adj;

        Graph(int V){
            this.V = V;
            this.adj = new ArrayList[V];
            for(int i = 0 ; i < V; i++){
                adj[i] = new ArrayList<Integer>();
            }
        }
        public int getV(){
            return this.V;
        }
        public ArrayList<Integer>[] getAdj(){
            return this.adj;
        }
        public void addEdge(int u, int v){
            this.adj[u].add(v);
        }
    }
    public int[] findOrderBFS(int numCourses, int[][] prerequisites) {

        Queue<Integer> queue = new LinkedList<>();
        Graph g = new Graph(numCourses);
        int V = g.getV();
        int[] result = new int[V];
        ArrayList<Integer> resultList = new ArrayList<Integer>();

        // create the adjlist
        for(int[] prereq: prerequisites){

            // prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai. For the purpose of ordering we need to reverse it.
            //because we have to take tasks which can be done before hand..firstly, so if 0 can be done only if 1 is done ..our graph will look like 1-->0 ultimately giving right order
            g.getAdj()[prereq[1]].add(prereq[0]);

        }
        ArrayList<Integer>[] adj = g.getAdj();

        int[] indegree = new int[V];
        Arrays.fill(indegree,0);

        //update indegree
        for(ArrayList<Integer> list: adj){
            for(int vertex: list){
                indegree[vertex]++;
            }
        }

        //initially push all the vertex with indegree 0 to queue.
        for(int i=0; i < V; i++){
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()){

            int currentV = queue.poll();
            resultList.add(currentV);

            for(int neighbor: adj[currentV]){
                indegree[neighbor]--;
                if(indegree[neighbor] == 0){
                    queue.offer(neighbor);
                }
            }

        }
        if(resultList.size() != V){
            //Cycle
            return new int[]{};
        }

        return resultList.stream().mapToInt(i->i).toArray();

    }

    public boolean isCycle(int vertex,ArrayList<Integer>[] adj,boolean[] visited,boolean[] recursionStack,ArrayList<Integer> resultList){  // topoVisit

        if(recursionStack[vertex]) return true;

        if (visited[vertex]) return false; // we don't have to consider the visited vertices.

        recursionStack[vertex] = true;
        for (int neigbhor: adj[vertex]) {
            if (isCycle(neigbhor,adj,visited,recursionStack,resultList))  // Idea of recursive stack: Taken a current vertex, if the neighbors map back to current vertex --> there is a cycle.
                return true;
        }

        visited[vertex]= true;
        recursionStack[vertex] = false; // unwind
        resultList.add(vertex);
        return false;
    }

    /*
    TC : O(V + E)
    SC : O(V + E ) // O(V) for recursive Stack, E --> graph Adj list
     */

    public int[] findOrderDFS(int numCourses, int[][] prerequisites) {
        Graph g = new Graph(numCourses);
        int V = g.getV();
        int[] result = new int[V];
        ArrayList<Integer> resultList = new ArrayList<Integer>();

        // create the adjlist
        for(int[] prereq: prerequisites){

            g.getAdj()[prereq[0]].add(prereq[1]);

        }
        ArrayList<Integer>[] adj = g.getAdj();

        boolean[] visited = new boolean[V];
        boolean[] recursionStack = new boolean[V];
        Arrays.fill(visited,false);
        Arrays.fill(recursionStack,false);

        for(int i = 0 ; i < V; i++) {
            if (isCycle(i, adj, visited, recursionStack, resultList)) {
                System.out.println("Cycle");
                return new int[]{};
            }
        }

        return resultList.stream().mapToInt(i->i).toArray();

    }
    public static void main(String[] args) {
        CourseScheduleII ob = new CourseScheduleII();
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(3, 2);
        g.addEdge(3, 1);
        int[][] prerequisites = {{0,1},{1,2},{3,2},{3,1}};
        System.out.println(Arrays.toString(ob.findOrderBFS(4,prerequisites)));

        int[][] prerequisites1 = {{0,1},{1,2}};
        System.out.println(Arrays.toString(ob.findOrderDFS(3,prerequisites1)));

    }
}