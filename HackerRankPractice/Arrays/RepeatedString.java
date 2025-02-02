package HackerRankPractice.Arrays;
/*
https://www.hackerrank.com/challenges/repeated-string/problem

There is a string, s , of lowercase English letters that is repeated infinitely many times.
Given an integer, n , find and print the number of letter a's in the first n letters of the infinite string.

Example
s= 'abcac'
n = 10

The substring we consider is abcacabcac, the first 10 characters of the infinite string.
There are 4 occurrences of a in the substring.
 */
public class RepeatedString {
    /*
    TC : O(n)
    SC : O(1)
     */
    public  long repeatedString(String s, long n) {

        long result = 0L;
        int sLen = s.length();
        int numOfA = 0;

        // O(n)
        for(Character ch : s.toCharArray()){
            if(ch.equals('a')) numOfA++;
        }

        long fullRep = (n / sLen) * numOfA ;
        result += fullRep;

        long remainder = n % sLen;
        // count a in reminder string
        for(int i = 0 ; i < remainder; i++){
            if(s.charAt(i) == 'a') result++;
        }
        return result;
    }

    public static void main(String[] args) {
        RepeatedString ob = new RepeatedString();
        System.out.println(ob.repeatedString("aba",10));
    }


}
