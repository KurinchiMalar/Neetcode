package Graphs;
import java.util.*;
/*
Time Complexity : N* (M*N) + M*(M*N) + (M*N) = O(M*M + N*N)
Space Complexity : O(m * n) // recursive stack same as islands
 */
class PacificAtlanticWaterFlow {

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        int m = heights.length;
        int n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        List<List<Integer>> result = new ArrayList<>();

        // i -> row iteration
        // j -> col iteration

        // top and bottom row cells horizontally, row = 0 and row = m-1
        for(int j=0; j < n ; j++){
            dfs(0,j,pacific,heights[0][j],heights);
            dfs(m-1,j,atlantic,heights[m-1][j],heights);
        }

        //left and right col cells vertically, col = 0 and col = n-1
        for(int i=0; i < m ; i++){
            dfs(i,0,pacific,heights[i][0],heights);
            dfs(i,n-1,atlantic,heights[i][n-1],heights);
        }

        // traverse the visited arrays once to get the result set
        for(int i=0; i < m ; i++){
            for( int j=0; j < n; j++){
                if(pacific[i][j] && atlantic[i][j]){
                    result.add(Arrays.asList(i,j));
                }
            }
        }

        return result;
    }

    public void dfs(int row, int col, boolean[][] visited, int prevheight,int[][] heights){

        if(     row >=0 && col >=0
                && row < heights.length && col < heights[0].length
                && !visited[row][col]
                && prevheight <= heights[row][col]){

            visited[row][col]=true;
            dfs(row+1,col,visited,heights[row][col],heights);
            dfs(row-1,col,visited,heights[row][col],heights);
            dfs(row,col+1,visited,heights[row][col],heights);
            dfs(row,col-1,visited,heights[row][col],heights);
        }
    }
}