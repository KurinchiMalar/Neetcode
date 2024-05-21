package DynamicProgramming.DP_on_Strings;

import java.util.Arrays;

/*
https://takeuforward.org/data-structure/wildcard-matching-dp-34/
https://leetcode.com/problems/wildcard-matching/description/
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).



Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.


Constraints:

0 <= s.length, p.length <= 2000
s contains only lowercase English letters.
p contains only lowercase English letters, '?' or '*'.

 */
public class WildCardMatching {

    /*
    TC : O(2 pow n ) exponential
    SC : O(N1 + N2)
     */
    public boolean isMatchRec(int i, int j, String s1, String s2){

        if(i < 0 && j < 0) return true; //empty string

        if(j < 0){ // if source string contains only * it can match to empty
            while(i >= 0){
                if(s1.charAt(i) != '*')return false;
                i--;
            }
            return true;
        }
        if(i < 0){ // source is over but there are still chars left in the other string
            return false;
        }

        if(s1.charAt(i) == s2.charAt(j) || s1.charAt(i) == '?'){
            return isMatchRec(i-1,j-1,s1,s2);
        }

        if(s1.charAt(i) == '*'){
            return isMatchRec(i-1,j,s1,s2)    // * matched to empty
                    || isMatchRec(i,j-1,s1,s2); // * matched to current char and moving further for matches with *
        }
        return false; // not equal
    }
    public boolean isMatch(String s, String p) {
        int n1 = p.length(); // p is the string that contains regex
        int n2 = s.length(); // s is the other string
        return isMatchRec(n1-1,n2-1,p,s);
    }

    /********************************************************************************************************************/

    /*
    My submission : https://leetcode.com/problems/wildcard-matching/submissions/1263896974/
    TC : O(N1* N2)
    SC : O(N1 * N2) + O(N1 + N2)
     */
    public boolean isMatch_TDhelper(int i, int j, String s1, String s2,int[][] dp){

        if(i < 0 && j < 0) return true;
        if(j < 0){
            while(i >= 0){
                if(s1.charAt(i) != '*')return false;
                i--;
            }
            return true;
        }
        if(i < 0) return false;
        if(dp[i][j] != -1){
            return (dp[i][j] == 1) ? true : false;
        }
        if(s1.charAt(i) == s2.charAt(j) || s1.charAt(i) == '?'){
            dp[i][j] = isMatch_TDhelper(i - 1, j - 1, s1, s2, dp) ? 1 : 0;
            return dp[i][j]==1;
        }
        if(s1.charAt(i) == '*'){
            dp[i][j] = ( isMatch_TDhelper(i-1,j,s1,s2,dp)    // * matched to empty
                    || isMatch_TDhelper(i,j-1,s1,s2,dp) ) ? 1 : 0; // * matched to current char and moving further for matches with *
            return dp[i][j]==1;
        }
        dp[i][j] = 0;
        return false;
    }

    public boolean isMatch_TD(String s, String p) {
        int n1 = p.length(); // p is the string that contains regex
        int n2 = s.length(); // s is the other string
        int[][] dp = new int[n1][n2];
        for(int[] ar:dp) Arrays.fill(ar,-1);
        return isMatch_TDhelper(n1-1,n2-1,p,s,dp);
    }
    /********************************************************************************************************************/

    /*
    My submission : https://leetcode.com/problems/wildcard-matching/submissions/1263922409/
    TC : O(N1 * N2)
    SC : O(N1 * N2)
     */
    boolean isAllStars(String S1, int i) {
        for (int j = 1; j <= i; j++) {
            if (S1.charAt(j - 1) != '*')
                return false;
        }
        return true;
    }
    public boolean isMatch_BU_helper(int n1, int n2, String s1, String s2){

        boolean [][] dp = new boolean[n1+1][n2+1];

        // 0th row 0th column
        dp[0][0] = true; // empty string

        // i < 0 ...for all j false. --> 0th row
        for(int j = 1 ; j <= n2; j++){
            dp[0][j] = false;
        }

        // j < 0 ... check if all stars in S1
        for(int i = 1; i <= n1; i++){

            dp[i][0] = isAllStars(s1,i);
        }

        for(int i = 1; i <= n1; i++){
            for(int j = 1; j <= n2; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1) || s1.charAt(i-1) == '?'){
                    dp[i][j] = dp[i-1][j-1];
                }else if (s1.charAt(i-1) == '*'){
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }else{
                    dp[i][j] = false;
                }
            }

        }
        return dp[n1][n2];
    }

    public boolean isMatch_BU(String s, String p) {
        int n1 = p.length(); // p is the string that contains regex
        int n2 = s.length(); // s is the other string

        return isMatch_BU_helper(n1,n2,p,s);
    }
    public static void main(String[] args) {
        WildCardMatching ob = new WildCardMatching();
        System.out.println("********************* Recursion *******************");
        System.out.println(ob.isMatch("abdefcd","ab*cd"));
        System.out.println(ob.isMatch("aa","a"));
        System.out.println(ob.isMatch("aa","*"));
        System.out.println(ob.isMatch("cb","?a"));
        System.out.println("********************* TopDown *******************");
        System.out.println(ob.isMatch_TD("abdefcd","ab*cd"));
        System.out.println(ob.isMatch_TD("aa","a"));
        System.out.println(ob.isMatch_TD("aa","*"));
        System.out.println(ob.isMatch_TD("cb","?a"));
        System.out.println("********************* Bottom UP *******************");
        System.out.println(ob.isMatch_BU("abdefcd","ab*cd"));
        System.out.println(ob.isMatch_BU("aa","a"));
        System.out.println(ob.isMatch_BU("aa","*"));
        System.out.println(ob.isMatch_BU("cb","?a"));
        System.out.println(ob.isMatch_BU("aab","c*a*b"));
        System.out.println(ob.isMatch_BU("adceb","*a*b"));




    }
}
