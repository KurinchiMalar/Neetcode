package MustDo500;

import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/number-of-islands/description/
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.



Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
 */

/*
My submission : https://leetcode.com/problems/number-of-islands/submissions/1269970651/
Tc : O(M * N)
SC : O(M * N) // visited
 */
public class A12_NumberOfIslands {

    class Pair{
        int first;
        int second;
        Pair(int first, int second){
            this.first = first;
            this.second = second;
        }
    }

    public boolean isSafe(char[][] grid,boolean[][] visited, int row, int col,int m, int n){

        if(row < 0 || row >=m || col < 0 || col >= n || grid[row][col] == '0' || visited[row][col]){
            return false;
        }
        return true;
    }
    public void bfs(char[][] grid,boolean[][] visited, int row, int col,int m, int n){

        // Arrays corresponding to 8 adj positions (including diagonal)
        //int[] nRow = {-1,-1,0,1,1,1,0,-1};
        //int[] nCol = {0,1,1,1,0,-1,-1,-1};

        // Arrays corresponding to 4 adj positions (excluding diagonal)
        int[] nRow = {-1, 0, 1, 0};
        int[] nCol = {0, 1, 0, -1};

        Queue<Pair> queue = new LinkedList<Pair>();
        queue.offer(new Pair(row,col));
        visited[row][col] = true; // mark current island visited and go for it's adjacents

        while(!queue.isEmpty()){
            Pair p = queue.poll();

            // check all adjacents
            for(int k= 0; k < 4; k++){
                // Current neighbor (x,y)
                int x = p.first + nRow[k];
                int y = p.second + nCol[k];
                if(isSafe(grid,visited,x,y,m,n)){
                    queue.offer(new Pair(x,y));
                    visited[x][y]=true;
                }
            }
        }
    }

    public int numIslands(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        boolean[][] visited = new boolean[m][n];

        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n; j++){
                if(grid[i][j] == '1' && !visited[i][j]){ // pick a non visited island and start from there
                    bfs(grid,visited,i,j,m,n);
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        A12_NumberOfIslands ob = new A12_NumberOfIslands();
        /*char[][] grid1 = new char[][]{
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        //System.out.println(ob.numIslands(grid1));*/

        char[][] grid2 = new char[][]{
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        System.out.println(ob.numIslands(grid2));
    }
}
