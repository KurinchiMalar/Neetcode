package DivideAndConquer;

import java.util.stream.IntStream;

public class Fibonacci {

    public static int fibo(int n){
        if(n <= 0) return 0;
        if(n == 1) return 1;
        return fibo(n-1) + fibo(n-2);
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(0,10).forEach(i-> System.out.println("f( "+i+" )"+" = "+fibo(i)));



    }
}
