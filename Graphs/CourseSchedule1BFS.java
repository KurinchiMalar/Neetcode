package Graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/*
Time Complexity:
O(n + e), where n is the number of courses/nodes, and e is the number of prerequisites/edges.

Building the graph from input data takes O(n + e) time.

Space Complexity:
O(n + e).
 */
public class CourseSchedule1BFS {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        ArrayList[] graph = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        Queue queue = new LinkedList<>();
        int count=0;

        for(int i=0 ; i < numCourses; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0 ; i < prerequisites.length; i++ ){
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
            degree[prerequisites[i][0]]++;
        }

        for(int i = 0 ; i < degree.length; i++){
            if(degree[i] == 0){
                queue.add(i);
                count++;
            }
        }
        while(!queue.isEmpty()){
            int course = (int)queue.poll();
            for(int i=0 ; i < graph[course].size(); i++){
                int p = (int)graph[course].get(i); // first adjacent
                degree[p]--;
                if(degree[p] == 0){
                    queue.add(p);
                    count++;
                }
            }
        }
        if(count == numCourses){
            return true;
        }
        return false;
    }
}
