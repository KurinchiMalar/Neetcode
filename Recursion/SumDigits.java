package Recursion;

/*
TC : O(log n)
 */
public class SumDigits {

    static int sumDigits(int n){
        if(n == 0 || n < 0)return 0;
        return (n % 10 + sumDigits(n/10));
    }

    public static void main(String[] args) {
        System.out.println(sumDigits(2531));
        System.out.println(sumDigits(27));
    }
}
