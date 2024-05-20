package DynamicProgramming.DP_on_Strings;

import java.util.Arrays;

/*
https://leetcode.com/problems/longest-palindromic-subsequence/description/
https://takeuforward.org/data-structure/longest-palindromic-subsequence-dp-28/

Given a string s, find the longest palindromic subsequence's length in s.

A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.



Example 1:

Input: s = "bbbab"
Output: 4
Explanation: One possible longest palindromic subsequence is "bbbb".
Example 2:

Input: s = "cbbd"
Output: 2
Explanation: One possible longest palindromic subsequence is "bb".


Constraints:

1 <= s.length <= 1000
s consists only of lowercase English letters.
 */
public class LongestCommonPalindromicSubsequence {

    /*
    My submission: https://leetcode.com/problems/longest-palindromic-subsequence/submissions/1262643415/
    TC : O(N * N) + O(N) for reverse
    SC : O(N * N) + O(N) for recursion stk
     */
   public int longestCommonSubseq_TD(int i,int j, String s1,String s2,int[][] dp){

        if(i < 0 || j < 0) return 0;

        if(dp[i][j] != -1) return dp[i][j];

        if(s1.charAt(i) == s2.charAt(j)){
            return 1 + longestCommonSubseq_TD(i-1,j-1,s1,s2,dp);
        }
        int moveS1 = longestCommonSubseq_TD(i-1,j,s1,s2,dp);
        int moveS2 = longestCommonSubseq_TD(i,j-1,s1,s2,dp);
        dp[i][j] = Math.max(moveS1,moveS2);

        return dp[i][j];

    }
    /*
    My submission : https://leetcode.com/problems/longest-palindromic-subsequence/submissions/1262644087/
    TC : O(N * N)
    SC : O(N * N)
     */
    public int longestCommonSubseq_BU_Tablulation(String s1, String s2){
        // since both are same string with same len
        int n  = s1.length();
        int[][] dp = new int[n+1][n+1];

        // 0th row
        for(int j = 0 ; j <= n ; j++) dp[0][j] = 0;

        //0th column
        for(int i = 0 ; i <= n ; i++) dp[i][0] = 0;

        for(int i = 1 ; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[n][n];
    }

    /*
My submission : https://leetcode.com/problems/longest-palindromic-subsequence/submissions/1262646095/
TC : O(N * N)
SC : O(N)
 */
    public int longestCommonSubseq_BU_Tablulation_SpaceOptimized(String s1, String s2){
        // since both are same string with same len
        int n  = s1.length();
        //int[][] dp = new int[n+1][n+1];
        int[] prev = new int[n+1];
        int[] cur = new int[n+1];

        // 0th row
        for(int j = 0 ; j <= n ; j++) prev[j] = 0;

        for(int i = 1 ; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    cur[j] = 1 + prev[j-1];
                }else{
                    cur[j] = Math.max(prev[j],cur[j-1]);
                }
            }
            prev = cur.clone();
        }
        return prev[n];
    }

    public int longestPalindromeSubseq_BU(String s) {
        StringBuilder sb = new StringBuilder(s);

        String rev = sb.reverse().toString();
        return longestCommonSubseq_BU_Tablulation(s,rev);
    }

    /******************************************************************************************************************/

    public int longestPalindromeSubseq_TD(String s) {
        StringBuilder sb = new StringBuilder(s);

        String rev = sb.reverse().toString();
        int n = s.length();
        int[][] dp = new int[n+1][n+1];
        for(int[] ar: dp)Arrays.fill(ar,-1);
        return longestCommonSubseq_TD(n-1,n-1,s,rev,dp);
    }

    /******************************************************************************************************************/

    public int longestPalindromeSubseq_BU_SpaceOptimized(String s) {
        StringBuilder sb = new StringBuilder(s);

        String rev = sb.reverse().toString();

        return longestCommonSubseq_BU_Tablulation_SpaceOptimized(s,rev);
    }

    public static void main(String[] args) {
        LongestCommonPalindromicSubsequence ob = new LongestCommonPalindromicSubsequence();
        System.out.println("****************** Top Down *****************");
        System.out.println(ob.longestPalindromeSubseq_TD("bbbab"));
        System.out.println(ob.longestPalindromeSubseq_TD("nitin"));

        System.out.println("****************** Bottom up *****************");
        System.out.println(ob.longestPalindromeSubseq_BU("bbbab"));
        System.out.println(ob.longestPalindromeSubseq_BU("nitin"));

        System.out.println("****************** Bottom up Space Optimized*****************");
        System.out.println(ob.longestPalindromeSubseq_BU_SpaceOptimized("bbbab"));
        System.out.println(ob.longestPalindromeSubseq_BU_SpaceOptimized("nitin"));


    }
}
