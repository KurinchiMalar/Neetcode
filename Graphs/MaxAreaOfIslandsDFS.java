package Graphs;

/*
    TC : O(M*N)
    SC : worst case O(M×N) in case that the grid map is filled with lands

 */
public class MaxAreaOfIslandsDFS {
    int maxArea = Integer.MIN_VALUE;
    public int maxAreaOfIsland(int[][] grid) {
        int[] curArea = new int[1];
        int m = grid.length;
        int n = grid[0].length;
        for(int i=0; i < m ; i++){
            for(int j=0; j < n; j++){
                if(grid[i][j]==1){
                    curArea[0] = 0;
                    dfs(grid,i,j,m,n,curArea);
                    maxArea = Integer.max(maxArea,curArea[0]);
                }
            }
        }
        if(maxArea < 0){
            return 0;
        }
        return maxArea;
    }


    public void dfs(int[][] grid,int i, int j, int m , int n,int[] curArea){

        if(i >= 0 && j >= 0 && i < m && j < n && grid[i][j]==1){
            grid[i][j] = 0;
            curArea[0]++;
            dfs(grid,i-1,j,m,n,curArea);
            dfs(grid,i+1,j,m,n,curArea);
            dfs(grid,i,j-1,m,n,curArea);
            dfs(grid,i,j+1,m,n,curArea);
        }

    }
}