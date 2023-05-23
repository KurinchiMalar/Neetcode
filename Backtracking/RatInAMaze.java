/*
A Maze is given as N*N binary matrix of blocks where source block is the upper left most block
 i.e., maze[0][0] and destination block is lower rightmost block i.e., maze[N-1][N-1].
 A rat starts from source and has to reach the destination.
 The rat can move only in two directions: forward and down.

 Optimization: all directions

In the maze matrix, 0 means the block is a dead end and 1 means the block can be used in the path from source to destination. Note that this is a simple version of the typical Maze problem. For example, a more complex version can be that the rat can move in 4 directions and a more complex version can be with a limited number of moves.

Following is an example maze.

{1, 0, 0, 0}
{1, 1, 0, 1}
{0, 1, 0, 0}
{1, 1, 1, 1}
All entries in solution path are marked as 1.

Return true is such a path exits. otherwise return false.

 */
package Backtracking;

public class RatInAMaze {

    public void printPath(int[][] visited,int M, int N){
        for(int i=0; i < M ; i++){
            for(int j = 0 ; j < N ; j++){
                System.out.print(" "+visited[i][j]+" ");
            }
            System.out.println();
        }
    }

    public boolean isPathFound(int[][] maze){
        int M = maze.length;
        int N = maze[0].length;
        int[][] visited = new int[M][N];
        printPath(visited,M,N);
        return backtrack(maze,0,0,M,N,visited);
    }

    public boolean isSafe(int[][] maze, int row, int col, int M, int N){
        if(row >= 0 && col >= 0 && row < M && col < N && maze[row][col] == 1){
            return true;
        }
        return false;
    }

    public boolean backtrack(int[][] maze, int row, int col, int M , int N, int[][] visited){

        // reached destination?
        if(row == M-1 && col == N-1 && maze[row][col]==1){
            visited[row][col] = 1;
            return true;
        }

        if(isSafe(maze,row,col,M,N) && visited[row][col] == 0){
            visited[row][col] = 1;
            if(backtrack(maze,row+1,col,M,N,visited)) return true;

            if(backtrack(maze,row,col+1,M,N,visited)) return true ;

            if(backtrack(maze,row,col-1,M,N,visited)) return true;

            if(backtrack(maze,row-1,col,M,N,visited)) return true;

            visited[row][col] = 0; // making it zero since this is not a valid path. We are having the path as a trace in this visited matrix.
            return false;
        }
        return false;
    }

    public static void main(String[] args){
        RatInAMaze ob = new RatInAMaze();
        int maze[][] = { { 1, 0, 0, 0 },
                         { 1, 1, 0, 1 },
                         { 0, 1, 0, 0 },
                         { 1, 1, 1, 1 } };
        System.out.println(ob.isPathFound(maze));
    }

}
