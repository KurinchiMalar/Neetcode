package DynamicProgramming.DP_on_Strings;

import java.util.Arrays;

/*
https://takeuforward.org/data-structure/distinct-subsequences-dp-32/
https://www.youtube.com/watch?v=nVG7eTiD2bY

https://leetcode.com/problems/distinct-subsequences/
 */
/*
Given two strings s and t, return the number of distinct subsequences of s which equals t.

The test cases are generated so that the answer fits on a 32-bit signed integer.



Example 1:

Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from s.
rabbbit
rabbbit
rabbbit
Example 2:

Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from s.
babgbag
babgbag
babgbag
babgbag
babgbag


Constraints:

1 <= s.length, t.length <= 1000
s and t consist of English letters.
 */
public class DistinctSubsequences {
    /*
    TC : O(2 pow (n1+n2)) // exponential
    SC : O(N1 * N2 ) + O( N1 + N2) for recursion stack
     */

    public int rechelper(int i, int j, String s1, String s2){

        if(j < 0){ // all of s2 are matched and it is exhausted.
            return 1;
        }
        if(i < 0){ // s1 got exhausted but s2 still has chars
            return 0;
        }
        if (s1.charAt(i) == s2.charAt(j)) {

            // on matched char you can do 2 things.
            int pickMatchedInS1 = rechelper(i-1,j-1,s1,s2);
            //ignore this match in S1 and search for other matches for this char in s1
            int notPickMatchedInS1 = rechelper(i-1,j,s1,s2);

            return pickMatchedInS1 + notPickMatchedInS1;
        }else{
            // As chars don't match shrink s1 to find if you see this char anywhere else in s1
            return rechelper(i-1,j,s1,s2);
        }
    }

    public int numDistinct_Rec(String s, String t) {

        int n1 = s.length();
        int n2 = t.length();
        return rechelper(n1-1,n2-1,s,t);

    }
    /*************************************************************************************************************************/
    /*
    MY Submission : https://leetcode.com/problems/distinct-subsequences/submissions/1263599614/
    TC : O(N1 * N2)
    SC : O(N1 * N2 ) + O( N1 + N2) for recursion stack
     */
    public int helper_TD(int i, int j, String s1, String s2, int[][] dp){

        if(j < 0){ // all of s2 are matched and it is exhausted.
            return 1;
        }
        if(i < 0){ // s1 got exhausted but s2 still has chars
            return 0;
        }
        if(dp[i][j] != -1) return dp[i][j];
        if (s1.charAt(i) == s2.charAt(j)) {
            // on matched char you can do 2 things.
            int pickMatchedInS1 = helper_TD(i-1,j-1,s1,s2,dp);
            //ignore this match in S1 and search for other matches for this char in s1
            int notPickMatchedInS1 = helper_TD(i-1,j,s1,s2,dp);
            dp[i][j] = pickMatchedInS1 + notPickMatchedInS1;

        }else{
            dp[i][j] = helper_TD(i-1,j,s1,s2,dp);
        }
        return dp[i][j];
    }
    public int numDistinct_TD(String s, String t) {
        int n1 = s.length();
        int n2 = t.length();
        int[][] dp = new int[n1][n2];
        for(int[] ar: dp) Arrays.fill(ar,-1);
        return helper_TD(n1-1,n2-1,s,t,dp);
    }

    /*************************************************************************************************************************/
    /*
    My submission : https://leetcode.com/problems/distinct-subsequences/submissions/1263683952/
    TC : O(N1 * N2)
    SC : O(N1 * N2 )
     */
    public int numDistinct_BU(String s, String t) {
        int n1 = s.length();
        int n2 = t.length();
        int[][] dp = new int[n1+1][n2+1];

        // 0th row
        for(int j=0 ; j <= n2; j++){
            dp[0][j] = 0;
        }
        //0th column
        for(int i=0 ; i <= n1; i++){   // j finished means fully matched. therefore 0th column is fully 1
            dp[i][0] = 1;
        }

        for(int i = 1; i <= n1; i++){
            for(int j = 1 ; j <= n2; j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n1][n2];
    }
    /*
    My submission : https://leetcode.com/problems/distinct-subsequences/submissions/1263711218/
    TC : O(N1 * N2)
    SC : O(N2)
     */
    public int numDistinct_BU_SpaceOptimized(String s, String t) {
        int n1 = s.length();
        int n2 = t.length();
        //int[][] dp = new int[n1+1][n2+1];
        int[] prev = new int[n2+1];
        int[] cur = new int[n2+1];

        // Initialize the first element to 1 because there's one empty subsequence in any string.
        // j finished means fully matched. therefore 0th column is fully 1
        prev[0] = 1;
        cur[0] = 1;

        for(int i = 1; i <= n1; i++){
            for(int j = 1 ; j <= n2; j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    cur[j] = prev[j-1] + prev[j];
                }else{
                    cur[j] = prev[j];
                }
            }
            prev = cur.clone();
        }
        return prev[n2];
    }


    public static void main(String[] args) {
        DistinctSubsequences ob = new DistinctSubsequences();
        System.out.println("**************** Recursion ***********************");
        System.out.println(ob.numDistinct_Rec("babgbag","bag"));
        System.out.println(ob.numDistinct_Rec("rabbbit","rabbit"));
        System.out.println("**************** Top Down ***********************");
        System.out.println(ob.numDistinct_TD("babgbag","bag"));
        System.out.println(ob.numDistinct_TD("rabbbit","rabbit"));
        System.out.println("**************** Bottom up ***********************");
        System.out.println(ob.numDistinct_BU("babgbag","bag"));
        System.out.println(ob.numDistinct_BU("rabbbit","rabbit"));
        System.out.println("**************** Bottom up Space Optimization ***********************");
        System.out.println(ob.numDistinct_BU_SpaceOptimized("babgbag","bag"));
        System.out.println(ob.numDistinct_BU_SpaceOptimized("rabbbit","rabbit"));


    }
}
