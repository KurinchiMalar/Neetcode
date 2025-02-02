package DynamicProgramming.DP_on_Strings;
/*
https://takeuforward.org/data-structure/shortest-common-supersequence-dp-31/
https://leetcode.com/problems/shortest-common-supersequence/description/
 */

/*
Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences. If there are multiple valid strings, return any of them.

A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.



Example 1:

Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation:
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.
Example 2:

Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
Output: "aaaaaaaa"


Constraints:

1 <= str1.length, str2.length <= 1000
str1 and str2 consist of lowercase English letters.
 */

/*
eg)
    s1 = "brute"       s2 ="groot"

    Supersequence(Concatenating both strings) : brutegroot  --> eliminating duplicates (shortest) --->
                                                bgruoote (Shortest) :)


    Idea :

       length of shortest supersequence = (n1+n2) - lcs ......... lcs is the length of the commons between both. we are reducing one set of duplicates.
       To print follow the similar approach as printing lcs , but when equal take only one char
 */


public class ShortestCommonSuperSequence {
    StringBuilder result = new StringBuilder();

    // to print the dp and get the sequence from the last cell to first
    public String getSuperSequence(int[][] dp, String str1,String str2,int n1,int n2){

        int i = n1;
        int j = n2;

        while(i > 0 && j > 0){

            if(str1.charAt(i-1) == str2.charAt(j-1)){
                result.insert(0,str1.charAt(i-1));
                i = i-1;
                j = j-1;
            }else{
                if(dp[i-1][j] > dp[i][j-1]){
                    result.insert(0,str1.charAt(i-1));
                    i = i -1;
                }else{
                    result.insert(0,str2.charAt(j-1));
                    j = j-1;
                }
            }
        }

        // Remaining Characters
        while(i > 0){
            result.insert(0,str1.charAt(i-1));
            i--;
        }
        while (j > 0){
            result.insert(0, str2.charAt(j-1));
            j--;
        }
        return result.toString();

    }

    public String lcs(String str1,String str2,int n1,int n2){

        int[][] dp = new int[n1+1][n2+1];

        //0th row
        for(int j = 0 ; j <= n2; j++){
            dp[0][j] = 0;
        }
        // 0th column
        for(int i = 0 ; i <= n1; i++){
            dp[i][0] = 0;
        }

        for(int i = 1; i <= n1; i++){
            for(int j = 1; j <= n2; j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                }
            }
        }

        System.out.println("Length of shortest common SuperSeq: "+ ((n1 + n2) - dp[n1][n2]));
        //System.out.println("SuperSeq: "+ getSuperSequence(dp,str1,str2,n1, n2).toString());

        return getSuperSequence(dp,str1,str2,n1, n2);
    }

    /*
    TC : O( N1 * N2) + O(N1 +N2) for printing subseq ----> O(N1 * N2)
    SC : O(N1 * N2)
    My submission : https://leetcode.com/problems/shortest-common-supersequence/submissions/1263114311/
     */

    public String shortestCommonSupersequence(String str1, String str2) {
        int n1 = str1.length();
        int n2 = str2.length();

        return lcs(str1,str2,n1,n2);

    }

    public static void main(String[] args) {
        ShortestCommonSuperSequence ob = new ShortestCommonSuperSequence();
        System.out.println(" "+ ob.shortestCommonSupersequence("brute","groot"));
    }

}
