package Recursion;

// TC : O(n)
public class Factorial {

    static int factorial(int n){
        if( n < 0){
            return -1;
        }
        if(n==0){
            return 1;
        }

        return n * factorial(n-1);
    }
    public static void main(String[] args) {
        System.out.println(factorial(4));
        System.out.println(factorial(3));
        System.out.println(factorial(10));
        System.out.println(factorial(1));
        System.out.println(factorial(-111));
    }
}
