package Graphs;
/*
TC : O(N) + O(M) + O(NxMx4) ~ O(N x M) =====> border y + border x + dfs on 4 directions for every cell  = O(M*N)

For the worst case, every element will be marked as 'O' in the matrix,
and the DFS function will be called for (N x M) nodes and for every node,
 we are traversing for 4 neighbors, so it will take O(N x M x 4) time.

Space complexity : O(M*N)

 */
public class SurroundedRegions {

    public void printBoard(char[][] board){
        for(int i = 0 ; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                System.out.print(" "+board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("------------------");
    }

    public void dfs(int row, int col, char[][] board,int m, int n){

        // edge cases
        if(row < 0 || col < 0 || row >= m || col >= n){
            return;
        }
        /* from border mark T all cells that you see as O..
        these lead to border anyways and cannot be captured
        */
        if(board[row][col] == 'O'){
            board[row][col] = 'T';
        }

        /*
        check all 4 directions excluding border
        row = 1 to m-2
        col = 1 to n-2
        */
        if(row > 1 && board[row-1][col] == 'O'){
            dfs(row-1,col,board,m,n);
        }
        if(row < m-2 && board[row+1][col] =='O'){
            dfs(row+1,col,board,m,n);
        }
        if(col > 1 && board[row][col-1] == 'O'){
            dfs(row,col-1,board,m,n);
        }
        if(col < n-2 && board[row][col+1] == 'O'){
            dfs(row,col+1,board,m,n);
        }

    }
    public void solve(char[][] board) {

        int m = board.length;
        int n = board[0].length;
        System.out.println("Original board");
        printBoard(board);

        //Step 1 : Start from borders and find all O's and mark them T to exclude from capture

        for(int j=0 ; j < n; j++){
            if(board[0][j] == 'O'){
                dfs(0,j,board,m,n);
            }
            if(board[m-1][j] == 'O'){
                dfs(m-1,j,board,m,n);
            }
        }

        for(int i = 0; i < m ; i++){
            if(board[i][0] == 'O'){
                dfs(i,0,board,m,n);
            }
            if(board[i][n-1] == 'O'){
                dfs(i,n-1,board,m,n);
            }
        }
        System.out.println("After changing to T");
        printBoard(board);
        //Step 2 : Now the remaining Os are all valid ones that can be captured.

        for(int i = 1; i < m-1 ; i++){
            for (int j=1 ; j < n-1; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }

        // Step 3: Revert back T to restore the board

        for(int i = 0 ; i < m ; i++){
            for( int j= 0; j < n ; j++){
                if(board[i][j] == 'T'){
                    board[i][j] = 'O';
                }
            }
        }
        System.out.println("Output");
        printBoard(board);


    }
    public static void main(String[] args){
        SurroundedRegions ob = new SurroundedRegions();
        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'O','O','O','X'},{'O','O','X','X'}};
        //char[][] board = {{'O','O','O','O'},{'O','O','O','O'},{'O','O','O','O'},{'O','O','O','O'}};
        ob.solve(board);

    }
}
