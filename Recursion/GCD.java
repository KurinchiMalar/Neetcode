package Recursion;

public class GCD {


    public static int gcd(int a, int b){
        if(a < 0 || b < 0) return -1;
        if(b == 0) return a;
        return gcd(b, a % b);
    }

    public static int gcdIterative(int a, int b){
        if(a < 0 || b < 0) return -1;
        while( a > 0 && b > 0){
            if(a > b) a = a % b;
            else b = b % a;
        }
        if(a == 0) return b;
        return a;
    }

    public static void main(String[] args) {
        System.out.println(gcd(52,10));
        System.out.println(gcdIterative(52,10));
        System.out.println(gcd(8,4));
        System.out.println(gcdIterative(8,4));
    }
}
