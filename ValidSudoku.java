
import java.util.*;
/*
Time Complexity : O(n*n)
Space Complexity : O(n)
https://leetcode.com/problems/valid-sudoku/submissions/
https://github.com/KurinchiMalar/Neetcode/blob/Arrays/ValidSudoku.java
 */
class ValidSudoku {
    public static boolean isValidSudoku(char[][] board) {
        HashSet<String> set = new HashSet<>();
        for(int i=0; i < 9;i++) {
            for(int j=0; j < 9; j++) {
                if(board[i][j] !='.'){
                    char number = board[i][j];
                    if(!set.add(number+"inrow"+i) ||
                        !set.add(number+"incolumn"+j) ||
                        !set.add(number+"inblock"+i/3+"-"+j/3)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public static void main(String[] args){
        /*char[][] board = {{'7','.','.','.','4','.','.','.','.'},
                          {'.','.','.','8','6','5','.','.','.'},
                          {'.','1','.','2','.','.','.','.','.'},
                          {'.','.','.','.','.','9','.','.','.'},
                          {'.','.','.','.','5','.','5','.','.'},
                          {'.','.','.','.','.','.','.','.','.'},
                          {'.','.','.','.','.','.','2','.','.'},
                          {'.','.','.','.','.','.','.','.','.'},
                          {'.','.','.','.','.','.','.','.','.'}};*/
        char[][] board = {{'.','.','.','3','.','.','.','1','.'},
                          {'.','.','.','.','.','7','.','.','.'},
                          {'.','.','.','.','.','.','.','.','.'},
                          {'.','.','.','.','.','8','2','7','.'},
                          {'1','.','.','.','.','.','.','.','.'},
                          {'.','.','.','5','.','.','.','.','.'},
                          {'2','.','.','.','8','.','.','.','7'},
                          {'7','.','.','.','.','.','.','.','.'},
                          {'.','.','.','.','.','.','.','.','.'}};
        System.out.println(isValidSudoku(board));
    }
}