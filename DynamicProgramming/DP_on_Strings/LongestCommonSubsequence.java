package DynamicProgramming.DP_on_Strings;

import java.util.Arrays;

/*
https://leetcode.com/problems/longest-common-subsequence/description/
https://www.youtube.com/watch?v=NPZn9jBrX8U
https://takeuforward.org/data-structure/longest-common-subsequence-dp-25/
Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.



Example 1:

Input: text1 = "abcde", text2 = "ace"
Output: 3
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.


Constraints:

1 <= text1.length, text2.length <= 1000
text1 and text2 consist of only lowercase English characters.
 */
public class LongestCommonSubsequence {

    public int lcsRecursion(int i,int j, String s1, String s2){
        if( i < 0 || j < 0) {
            return 0;
        }

        if(s1.charAt(i) == s2.charAt(j)){
            return 1 + lcsRecursion(i-1,j-1,s1,s2);
        }
        int moveS1 = lcsRecursion(i-1,j,s1,s2);
        int moveS2 = lcsRecursion(i,j-1,s1,s2);
        return Math.max(moveS1,moveS2);

    }

    public int lcs_Rec(String text1, String text2) {
        int n1 = text1.length();
        int n2 = text2.length();
        return lcsRecursion(n1-1,n2-1,text1,text2);
    }
    /************************************************************************************************************/
    /*
    My submission: https://leetcode.com/problems/longest-common-subsequence/submissions/1260099646/
    TC : O(M * N)
    SC : O(M * N) + O( M + N) // stack space , max depth is equal to sum of length since at each not match step we take branch both sides.
     */
    public int lcs_TopDown(int i,int j, String s1, String s2,int[][] dp){
        if( i < 0 || j < 0) {
            return 0;
        }
        if(dp[i][j] != -1)return dp[i][j];
        /*if(i == 0 && j == 0){
            return (s1.charAt(i) == s2.charAt(j)) ? 1 : 0;
        }*/

        if(s1.charAt(i) == s2.charAt(j)){
            dp[i][j] = 1 + lcs_TopDown(i-1,j-1,s1,s2,dp);
            return dp[i][j];
        }
        int moveS1 = lcs_TopDown(i-1,j,s1,s2,dp);
        int moveS2 = lcs_TopDown(i,j-1,s1,s2,dp);
        dp[i][j] = Math.max(moveS1,moveS2);
        return dp[i][j];
    }
    public int lcs_TD(String text1, String text2) {
        int n1 = text1.length();
        int n2 = text2.length();
        int[][] dp = new int[n1][n2];
        for(int[] ar: dp) Arrays.fill(ar , -1);
        return lcs_TopDown(n1-1,n2-1,text1,text2,dp);
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int n1 = text1.length();
        int n2 = text2.length();
        return lcsRecursion(n1-1,n2-1,text1,text2);
    }
    /************************************************************************************************************/
    /*
    MY submission: https://leetcode.com/problems/longest-common-subsequence/submissions/1260345640/
    Tc : O( M * N)
    SC : O( M * N) // for dp array
     */
    public int lcs_BottomUp(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();

        int[][] dp = new int[n1+1][n2+1];
        //Base Case  Conversion:  if( i < 0 || j < 0)
        /* We cannot have negative index in dp...therefore we need to shift index
             i in dp  ====> i-1 in input
                 0 1 2 3
             0     a c e
             1 a
             2 b
             3 c
             4 d
             5 e

            i = -1 is regular out of boundary
            in our case i = 0 is out of boundary hence.
         */

        //first row
        for(int j = 0 ; j < n2; j++){
                dp[0][j] = 0;
        }
        //first column
        for(int i = 1 ; i < n1; i++){ // i= 0 already completed
                dp[i][0] = 0;
        }
        for(int i = 1; i <= n1; i++){
            for(int j = 1; j <= n2 ; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[n1][n2];
    }

    /************************************************************************************************************/

    /*
    TC: O(M *N)
    SC: O(N)
     */
    public int lcs_BottomUp_SpaceOptimized(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();

        int[] prev = new int[n2+1]; // prev row
        int[] cur = new int[n2+1]; //cur row
        // Base Case - (Boundary condition)
        // for 0th row
        for(int j = 0 ; j < n2; j++){
            prev[j] = 0;
        }
        for(int i = 1; i <= n1; i++){
            for(int j = 1; j <= n2 ; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    cur[j] = 1 + prev[j-1];
                }else{
                    cur[j] = Math.max(prev[j],cur[j-1]);
                }
            }
            prev = cur;
        }

    return prev[n2];

    }


        public static void main(String[] args) {
        LongestCommonSubsequence ob = new LongestCommonSubsequence();
        System.out.println(ob.lcs_Rec("abcde","ace"));
        System.out.println(ob.lcs_Rec("abc","abc"));
        System.out.println(ob.lcs_Rec("abc","def"));
        System.out.println(ob.lcs_Rec("ac","ab"));

        System.out.println("***************************************** Top Down Memoization **********************************************************");
        System.out.println(ob.lcs_TD("abcde","ace"));
        System.out.println(ob.lcs_TD("abc","abc"));
        System.out.println(ob.lcs_TD("abc","def"));
        System.out.println(ob.lcs_TD("ac","ab"));

        System.out.println("***************************************** Bottom Up Tabulation **********************************************************");
        System.out.println(ob.lcs_BottomUp("abcde","ace"));
        System.out.println(ob.lcs_BottomUp("abc","abc"));
        System.out.println(ob.lcs_BottomUp("abc","def"));
        System.out.println(ob.lcs_BottomUp("ac","ab"));

        System.out.println("***************************************** Bottom Up Tabulation Space Optimized **********************************************************");
        System.out.println(ob.lcs_BottomUp_SpaceOptimized("abcde","ace"));
        System.out.println(ob.lcs_BottomUp_SpaceOptimized("abc","abc"));
        System.out.println(ob.lcs_BottomUp_SpaceOptimized("abc","def"));
        System.out.println(ob.lcs_BottomUp_SpaceOptimized("ac","ab"));

        }
}
