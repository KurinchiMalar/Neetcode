/*
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
Time Complexity:
O(n + e), where n is the number of courses/nodes, and e is the number of prerequisites/edges.

Building the graph from input data takes O(n + e) time.

Space Complexity:
O(n + e).
 */
package Graphs;

import java.util.*;
class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        ArrayList[] graph = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        int count = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        ArrayList<Integer> result = new ArrayList<>();


        for(int i=0 ; i < numCourses; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < prerequisites.length; i++){
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
            result.add(course);
            for(int i=0 ; i < graph[course].size(); i++){
                int p = (int)graph[course].get(i);
                degree[p]--;
                if(degree[p] == 0){
                    queue.add(p);
                    count++;
                }
            }
        }
        if(result.size() != numCourses){
            return new int[0];
        }
        /*int[] output = new int[result.size()];
        for(int i=0 ; i < result.size(); i++){
            output[i] = result.get(i);
        }*/
        return result.stream().mapToInt(i->i).toArray();
        //return output;
    }
}
