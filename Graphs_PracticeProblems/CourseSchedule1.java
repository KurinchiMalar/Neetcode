package Graphs_PracticeProblems;

import java.util.*;

/*
https://leetcode.com/problems/course-schedule/description/
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

Dependency : 0 -> 1

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.


Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
All the pairs prerequisites[i] are unique.
 */
/*
Idea : TopoSortBFS_KahnsAlgo
TC : O(V+E)
SC : O(V)
 */
public class CourseSchedule1 {
     class Graph{
        int V;
        ArrayList<Integer>[] adj;

        Graph(int V){
            this.V = V;
            this.adj = new ArrayList[V];
            for(int i=0 ; i < V; i++){
                adj[i] = new ArrayList<Integer>();
            }
        }

        public void addEdge(int u, int v){
            this.adj[u].add(v); // Directed
        }

        public int getV() {
            return V;
        }

        public ArrayList<Integer>[] getAdj() {
            return adj;
        }
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Graph g = new Graph(numCourses);
        int V = g.getV();

        for(int[] preReq:prerequisites){
            g.addEdge(preReq[0],preReq[1]);
        }
        ArrayList<Integer>[] adj = g.getAdj();

        // create indegree array
        int[] indegree = new int[V];
        Arrays.fill(indegree,0);

        // Update indegree
        for(ArrayList<Integer> list : adj){
            for(int vertex : list){
                indegree[vertex]++;
            }
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        for(int i=0 ; i  < V; i++){
            if(indegree[i]==0){
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()){
            int curNode = queue.poll();
            resultList.add(curNode);
            for(int neighbor:adj[curNode]){
                indegree[neighbor]--;
                if(indegree[neighbor] == 0){
                    queue.offer(neighbor);
                }
            }
        }
        if(resultList.size() != V){
            System.out.println("Has Cycle");
            return false;
        }
        System.out.println("TopoSort result will be : "+resultList);
        return true;
    }
    public static void main(String[] args) {

        int numCourses = 2;
        int[][] preReq = {{1,0}};

        CourseSchedule1 ob = new CourseSchedule1();
        //System.out.println(ob.canFinish(numCourses,preReq));

        int[][] preReq1 = {{0,1},{1,2},{3,2},{3,1}};
        System.out.println(ob.canFinish(4,preReq1));
    }


}
