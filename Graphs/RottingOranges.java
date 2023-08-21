package Graphs;
/*
You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
Example 1:
Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4

Example 2:

Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 10
grid[i][j] is 0, 1, or 2.

TC: O(M*N) // because we are traversing the matrix once to store all the rotten oranges in the queue.
SC: O(M*N) // worst case the all the oranges in the queue could be rotten so we’ll need n*m space to store them.
 */
import java.util.*;
public class RottingOranges {
    public int orangesRotting(int[][] grid) {

        if(grid == null || grid.length == 0 ) return 0;

        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        int freshOranges = 0;
        for(int i = 0 ; i < m; i++){
            for(int j = 0 ; j < n; j++){
                if(grid[i][j] == 2) { // rotten
                    queue.offer(new int[]{i,j});
                }
                if(grid[i][j] == 1){
                    freshOranges++;
                }
            }
        }
        if(freshOranges <=0 ) return 0;

        int minuteLevelCount = 0;
        while(!queue.isEmpty() && freshOranges > 0) {

            int curLevelSize = queue.size();
            for(int i=0 ; i < curLevelSize; i++){
                int[] point = queue.poll();

                for(int[] dir: dirs){
                    int x = point[0] + dir[0];
                    int y = point[1] + dir[1];

                    // boundary checks
                    if(x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == 0 || grid[x][y] == 2){
                        continue;
                    }

                    grid[x][y] = 2; // infection
                    freshOranges--;
                    queue.offer(new int[]{x,y});
                }

            }
            minuteLevelCount++;
        }

        return freshOranges != 0 ? -1:minuteLevelCount;

    }

}
