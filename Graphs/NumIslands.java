package Graphs;
/*
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
import java.util.LinkedList;

// https://leetcode.com/problems/number-of-islands/solutions/56338/java-dfs-and-bfs-solution/
public class NumIslands {

    /*
    TC : O(m * n)
    SC :  worst case O(M×N) in case that the grid map is filled with lands where DFS goes by M×N deep.
     */
    public int numIslandsDFS(char[][] grid){
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0 ; j < n; j++){
                if(grid[i][j] == '1'){
                    dfs(grid,i,j,m,n);
                    count++;
                }
            }
        }
        return count;
    }
    public void dfs(char[][] grid, int i, int j, int m, int n){

        if(i >=0 && j >=0 && i < m && j < n && grid[i][j]=='1'){
            grid[i][j]='0'; // mark current cell visited
            dfs(grid,i-1,j,m,n);
            dfs(grid,i+1,j,m,n);
            dfs(grid, i,j+1,m,n);
            dfs(grid,i,j-1,m,n);
        }
    }

    //-----------------------------------------------------------------------------------------------------------
    public int numIslandsBFS(char[][] grid) {

        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        for(int i=0 ;  i < m ; i++){
            for (int j=0 ; j < n ; j++){
                if(grid[i][j] == '1'){
                    bfs(grid,i,j,m,n);
                    count++;
                }
            }
        }
        return count;
    }

    /*
    TC : O(M*N)
    SC : O(min(M,N)) because in worst case where the grid is filled with lands, the size of queue can grow up to min(M,N)
     */
    public void bfs(char[][] grid,int row, int col, int m, int n) {
        grid[row][col] = '0';
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(row * n +col);

        while(!queue.isEmpty()){
            int item = queue.poll();

            int i = item/n;
            int j = item % n;

            if(i > 0 && grid[i-1][j]=='1'){ //up
                queue.offer((i-1)* n + j);
                grid[i-1][j] = '0';
            }
            if(i < m-1 && grid[i+1][j] == '1'){ //down
                queue.offer((i+1)*n+j);
                grid[i+1][j] = '0';
            }
            if( j > 0 && grid[i][j-1] == '1') { //left
                queue.offer((i)*n+j-1);
                grid[i][j-1] = '0';
            }
            if( j < n-1 && grid[i][j+1] == '1') { // right
                queue.offer((i)*n+j+1);
                grid[i][j+1] = '0';
            }

        }

    }

    public static void main(String[] args){
        NumIslands ob = new NumIslands();
        char[][] grid = new char[][]{
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        System.out.println(ob.numIslandsDFS(grid));
        char[][] grid1 = new char[][]{
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        System.out.println(ob.numIslandsDFS(grid1));

        char[][] gridB1 = new char[][]{
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        System.out.println(ob.numIslandsBFS(gridB1));
        char[][] gridB2 = new char[][]{
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        System.out.println(ob.numIslandsBFS(gridB2));
    }
}
