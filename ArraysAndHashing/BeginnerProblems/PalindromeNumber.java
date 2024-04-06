package ArraysAndHashing.BeginnerProblems;

import java.util.Arrays;
/*
https://leetcode.com/problems/palindrome-number/solutions/3651712/2-method-s-c-java-python-beginner-friendly/
 */
public class PalindromeNumber {

    /*
    TC : O(n)
    SC : O(n) for int array
     */
    public boolean isPalindrome(int x) {
        if(x < 0) return false;

        // To get the actual int value of a character, we have to subtract the ASCII code value of the character '0' from the ASCII code of the actual character.
        int[] digits = Integer.toString(x).chars().map(c-> c- '0').toArray();

        int start = 0;
        int end = digits.length-1;

        while(start <= end){

            if(digits[start] != digits[end]){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public boolean isPalindromeWithoutConvertingToString_M1_ReverseEntireNumber(int x) {
        if(x < 0) return false;

        int reversedX = 0;
        int temp = x;

        while(temp != 0){
            //digit  = temp % 10;
            reversedX = reversedX * 10 + (int)(temp % 10);
            temp = temp / 10;
        }
        return (reversedX == x);
    }

    /*

    Idea : Instead of reversing the entire number, we can reverse only the last half of the number.

    For an even number of digits, if x is equal to reversed, then the number is a palindrome. We return true.
    For an odd number of digits, if x is equal to reversed / 10 (ignoring the middle digit), then the number is a palindrome. We return true.

     */
    /*
    TC : O(n/2)
    SC: O(1)
     */

    public boolean isPalindromeWithoutConvertingToString_M2_ReverseHalfNumber_Optimized(int x) {

        if(x < 0 || (x != 0 && x % 10 == 0)) return false; // palindrome cannot have leading zeroes.

        int reversedX = 0;
        int temp = x;

        // this time reverse just half the number   eg) x = 1221 ,  (reversed = 12 , x = 12)
        while(x > reversedX){
            reversedX = reversedX * 10 +(int) (x % 10);
            x = x / 10;
        }
        return (x == reversedX) || (x == reversedX / 10);
    }

    public static void main(String[] args) {
        PalindromeNumber ob = new PalindromeNumber();
        System.out.println(ob.isPalindrome(121));
        System.out.println(ob.isPalindrome(123));

        System.out.println(ob.isPalindromeWithoutConvertingToString_M1_ReverseEntireNumber(121));
        System.out.println(ob.isPalindromeWithoutConvertingToString_M1_ReverseEntireNumber(123));

        System.out.println(ob.isPalindromeWithoutConvertingToString_M2_ReverseHalfNumber_Optimized(121));
        System.out.println(ob.isPalindromeWithoutConvertingToString_M2_ReverseHalfNumber_Optimized(123));

    }
}
