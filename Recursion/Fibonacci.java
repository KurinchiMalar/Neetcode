package Recursion;

//fib(0) = 0 , fib(1) = 1
//TC: O(2 pow n)
public class Fibonacci {

    static int fibo(int n){

        if( n < 0){
            return -1;
        }

        if(n==0 || n == 1)return n;

        return fibo(n-1)+fibo(n-2);
    }
    public static void main(String[] args) {
        System.out.println(fibo(7));
        System.out.println(fibo(3));

    }
}
