package ArraysAndHashing;
/*
https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/description/

 */
public class WinnerTicTacToe {

    /*
    If the game has been won --> last moved person is the winner
    So lets pick from the last move and see if we are getting a winning condition
    otherwise we just check if Game is Draw or Pending

    To win a person should have achieved 3 hits in same row, same col or diagonals (2)

    Person A = even positions starting from 0 (starts the game)
    Person B = odd positions
*/
    /*
    TC : O(n)
    SC : O(n) //n is the length of moves array given
     */
    public String tictactoe(int[][] moves) {

        // row = 0,1,2 ; col = 0,1,2 ; diagonal = 2
        // instead of separate arrays keeping all these in 1 score array for brevity
        int[] score = new int[8]; // 0,1,2 (rows) ; 3,4,5(cols) ; 6 (diag1) , 7 (diag2)
        int n = moves.length;
        String winner = "";
        for(int i = n-1; i >= 0 ; i-=2){ // going from the last player

            int row = moves[i][0];
            int col = moves[i][1];

            // Update the score array corresponding to the above move positions.
            score[row]++;
            score[col + 3]++; // to map (0,1,2) col values to 3,4,5 in our score array

            if(row == col){ // diag1
                score[6]++;
            }
            if(row+col == 2){ // diag2
                score[7]++;
            }

            // check if win condition achieved
            if(score[row] == 3 || score[col+3] == 3 || score[6] == 3 || score[7] == 3){
                winner = (i % 2) == 0 ? "A" : "B";
                return winner;
            }

        }
        // No winner , the game can be "Draw" or "Pending"
        if(n == 9) return "Draw";  // all 9 possible moves on a 3*3 board done and no winner still achieved.
        return "Pending";
    }

    public static void main(String[] args) {
        WinnerTicTacToe ob = new WinnerTicTacToe();
        System.out.println(ob.tictactoe(new int[][]{{0,0},{2,0},{1,1},{2,1},{2,2}}));
        System.out.println(ob.tictactoe(new int[][]{{0,0},{1,1},{0,1},{0,2},{1,0},{2,0}}));
        System.out.println(ob.tictactoe(new int[][]{{0,0},{1,1},{2,0},{1,0},{1,2},{2,1},{0,1},{0,2},{2,2}}));

    }
}
