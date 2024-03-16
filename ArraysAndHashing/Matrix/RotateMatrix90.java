package ArraysAndHashing.Matrix;

public class RotateMatrix90 {



    // Function to print the matrix
    static void displayMatrix(int mat[][])
    {
        int N = mat.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(" " + mat[i][j]);

            System.out.print("\n");
        }
        System.out.print("\n");
    }

    static int[][] reverseGivenRow(int i, int mat[][])
    {
        int N = mat.length;
        int start = 0;
        int end = N-1;
        while(start < end){
            int temp = mat[i][start];
            mat[i][start] = mat[i][end];
            mat[i][end] = temp;
            start++;
            end--;
        }
        return mat;
    }

    static int[][] rotateMatrixExtraSpace(int N, int[][] mat,int[][] result){

        if(N==0 || mat.length != mat[0].length){
            return result;
        }
        for(int i=0; i < N; i++){
            for(int j=0; j < N; j++){
                result[j][N-1-i] = mat[i][j];
            }
        }
    return result;
    }

    /*
    Idea find the transpose (every column to be converted to rows)
    Reverse every row of the transpose
     */
    static int[][] rotateMatrixInPlace(int N, int[][] mat){
        if(N==0 || mat.length != mat[0].length){
            return mat;
        }
        // Exclude the diagonal, because diagonal elements stay in same position in transpose
        // which excludes [0][0] and [N-1][N-1]

        //TC : O(N/2) * O(N/2)
        for(int i = 0 ; i < N-1; i++ ){
            for(int j = 1; j < N; j++){
                //swap of mat[i][j] and mat[j][j]
                int temp=mat[i][j];
                mat[i][j]=mat[j][i];
                mat[j][i]=temp;
            }
        }
        //TC : O(N * N/2) ....N/2 for reverse
        for(int i= 0 ; i < N ; i++){
            reverseGivenRow(i,mat);
        }


    return mat;
    }

    /* Driver code*/
    public static void main(String[] args)
    {
        int mat[][] = { { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        int N = mat.length;
        // Function call
        int[][] result = new int[N][N];
        //displayMatrix(reverseGivenRow(0,mat));
        displayMatrix(rotateMatrixExtraSpace(N, mat,result));
        int mat1[][] = { { 1, 2, 3, 4 },
        { 5, 6, 7, 8 },
        { 9, 10, 11, 12 },
        { 13, 14, 15, 16 } };
        displayMatrix(rotateMatrixInPlace(N, mat1));

    }
}
