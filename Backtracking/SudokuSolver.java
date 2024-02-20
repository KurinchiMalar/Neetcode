/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.
https://leetcode.com/problems/sudoku-solver/

 */
package Backtracking;

import java.util.Arrays;

public class SudokuSolver {
    public void solveSudoku(char[][] board) {
       if(board == null || board.length == 0){
           return;
       }
       solve(board);
       System.out.println(Arrays.deepToString(board));
    }

    public boolean solve(char[][] board){

        for(int i = 0 ; i < board.length ; i++){
            for(int j = 0 ; j < board[0].length ; j++){
                if(board[i][j] == '.'){ // there are 9 choices to check for a blank
                    for(char c='1' ; c <= '9'; c++){ //trial. Try 1 through 9
                        if(isValid(board,i,j,c)){
                            board[i][j] = c; //Put c for this cell

                            if(solve(board) == true){ //If it's the solution return true
                                return true;
                            }else{
                                board[i][j] = '.'; //Otherwise go back
                            }
                        }
                    }
                    return false; // exhausted all the 9 chars possible
                }
            }
        }
        return true;
    }

    public boolean isValid(char[][] board, int row, int col, char c){
        int regionRow =  3 * (row / 3);
        int regionCol =  3 * (col / 3);

        // 9 cells in a grid , 9 rows, 9 cols
        for(int i = 0 ; i < 9; i++){
            if(board[i][col] == c)return false;
            if(board[row][i] == c)return false;
            if(board[regionRow + i/3][regionCol + i % 3] == c) return false;

        }
        return true;
    }

    public static void main(String[] args){
        char[][] board = new char[][]{{'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        SudokuSolver ob = new SudokuSolver();
        ob.solveSudoku(board );
    }
}
