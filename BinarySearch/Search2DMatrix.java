/*
Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.

Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true

Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false
 */
/*
TC : O(log m + log n)
SC : O(1)
 */
package BinarySearch;

public class Search2DMatrix {

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int top = 0;
        int bottom = m-1;
        // isolate the row where target is present by binary search on row
        //(0 , 1, 2)
        /*
        00, 03
        10, 13
        20, 23
        comparing with the border candidates.
        */
        //O(log m)
        while(top <= bottom){
            int midRow = (top+bottom)/2;
            if(target > matrix[midRow][n-1]){  // right most in current row
                top = midRow+1;
            }else if (target < matrix[midRow][0]){ //left most in current row
                bottom = midRow-1;
            }else{
                break;
            }
        }
        if(top > bottom){  // while loop exited because target was not in range of top and bottom.
            return false;
        }

        // Binary search only in that row we have selected.
        //O(log n)
        int midRow = (top+bottom)/2;
        int left = 0;
        int right = n-1;
        while (left <= right){
            int mid = (left+right)/2;
            if(target > matrix[midRow][mid]){
                left = mid + 1;
            }else if(target < matrix[midRow][mid]){
                right = mid - 1;
            }else{
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        System.out.println(searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}},3));
        System.out.println(searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}},13));
        System.out.println(searchMatrix(new int[][]{{1}},1));
        System.out.println(searchMatrix(new int[][]{{1},{2}},2));

    }


}
