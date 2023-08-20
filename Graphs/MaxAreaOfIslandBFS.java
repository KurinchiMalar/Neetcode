/*
You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

The area of an island is the number of cells with a value 1 in the island.

Return the maximum area of an island in grid. If there is no island, return 0.



Example 1:


Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
Output: 6
Explanation: The answer is not 11, because the island must be connected 4-directionally.
Example 2:

Input: grid = [[0,0,0,0,0,0,0,0]]
Output: 0


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
grid[i][j] is either 0 or 1.
 */
package Graphs;

import java.util.LinkedList;

class MaxAreaOfIslandBFS {
    int maxArea = Integer.MIN_VALUE;
    LinkedList<Integer> queue = new LinkedList<>();

    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for(int i=0; i < m ; i++){
            for(int j=0; j < n; j++){
                if(grid[i][j]==1){
                    maxArea = Integer.max(bfs(grid,i,j,m,n),maxArea);
                }
            }
        }
        if(maxArea < 0){
            return 0;
        }
        return maxArea;
    }


    public int bfs(int[][] grid,int i, int j, int m , int n){
        int curArea = 0;
        int code = i*n+j;
        queue.offer(code);
        grid[i][j] = 0 ; // mark visited
        curArea++;
        while(!queue.isEmpty()){
            int item = queue.poll();
            int row = item / n;
            int col = item % n;

            if( row >0 && grid[row-1][col] == 1){ // up
                curArea++;
                queue.offer((row-1)*n+col);
                grid[row-1][col]=0;
            }
            if( row < m-1 && grid[row+1][col] == 1){ // down
                curArea++;
                queue.offer((row+1)*n+col);
                grid[row+1][col]=0;
            }
            if( col > 0 && grid[row][col-1] == 1){ // left
                curArea++;
                queue.offer((row)*n+col-1);
                grid[row][col-1]=0;
            }
            if( col < n-1 && grid[row][col+1] == 1){ // right
                curArea++;
                queue.offer((row)*n+col+1);
                grid[row][col+1]=0;
            }


        }
        maxArea = Integer.max(curArea,maxArea);
        curArea = 0;
        return maxArea;
    }
}
