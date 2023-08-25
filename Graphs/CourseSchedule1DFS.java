/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.



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
Time Complexity:
O(n + e), where n is the number of courses/nodes, and e is the number of prerequisites/edges.

Building the graph from input data takes O(n + e) time.

Space Complexity:
O(n + e).
 */
package Graphs;

import java.util.ArrayList;

public class CourseSchedule1DFS {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //dfs

        ArrayList[] graph = new ArrayList[numCourses];
        boolean[] visited = new boolean[numCourses];
        boolean[] dp = new boolean[numCourses];

        for(int i = 0 ; i < numCourses; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0 ; i < prerequisites.length; i++){
            // build adjacency list
            graph[prerequisites[i][1]].add(prerequisites[i][0]);

        }

        for(int i = 0 ; i < numCourses ; i++){
            if(!dfs(graph,visited,i,dp)){
                return false;
            }
        }
        return true;
    }

    public boolean dfs(ArrayList[] graph, boolean[] visited, int course, boolean[] dp){

        if(visited[course]){
            return dp[course];
        }
        visited[course] = true;
        for(int i = 0 ; i < graph[course].size(); i++){
            if(!dfs(graph,visited,(int)graph[course].get(i),dp)){
                dp[course] = false;
                return dp[course];
            }
        }
        dp[course] = true;
        return dp[course];
    }
}
