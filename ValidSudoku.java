import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ValidSudoku {
    public static boolean containsDuplicates(Set<Character> recordHashSet,char input){

        Set<Character> hset = recordHashSet;
        if(input != '.' && hset.contains(input)){
            return true;
        }
        hset.add(input);
        return false;
    }
    public static boolean isValidSudoku(char[][] board) {
        Set<Character> recordHashSet = new HashSet<>();
        int cornerIndex = 0;
        while (cornerIndex < 9){
            //row check
            for(int i = 0; i < 9 ; i++){
                if(containsDuplicates(recordHashSet,board[i][cornerIndex])){
                    return false;
                }
            }
            recordHashSet.clear();
            //column check
            for(int j=0; j < 9 ; j++){
                if(containsDuplicates(recordHashSet,board[cornerIndex][j])){
                    return false;
                }
            }
            recordHashSet.clear();
            System.out.println(cornerIndex);
            cornerIndex++;
        }

        System.out.println("rowcolumnclear");
        //3*3 check
        for(int i=0; i < 9 ; i=i+3){
            for(int j=0; j < 9 ; j++){
                if(containsDuplicates(recordHashSet,board[i][j])){
                    return false;
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