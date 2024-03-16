package Recursion;

public class ProductOfAllElemsArray {

    public static int productofArray(int A[], int n)
    {
        return productofArray1(A,n-1);
    }
    public static int productofArray1(int A[], int n)
    {
        if(n < 0) return -1;
        if(n == 0){
            return A[n];
        }
        return A[n] * productofArray1(A,n-1);
    }

    public static int productofArrayN(int A[], int n)
    {
        if(n < 0) return -1;
        if(n-1 == 0){
            return A[n-1];
        }
        return A[n-1] * productofArray1(A,n-2);
    }

    public static void main(String[] args) {
        System.out.println(productofArray(new int[]{1,2,3},3));
        System.out.println(productofArrayN(new int[]{1,2,3},3));
    }
}
