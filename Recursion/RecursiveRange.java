package Recursion;
/*
Write a function called recursiveRange which accepts a number and adds up all the numbers from 0 to the number passed to the function.

Examples

recursiveRange(6)  // 21
recursiveRange(10) // 55
 */
/*
TC : O
 */
public class RecursiveRange {

    public static int recursiveRange(int n){
        if( n < 0 || n == 0) return 0;
        return n + recursiveRange(--n);
    }

    public static void main(String[] args) {
        System.out.println(recursiveRange(6));
    }
}
