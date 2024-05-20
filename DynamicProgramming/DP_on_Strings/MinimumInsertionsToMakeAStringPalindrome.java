package DynamicProgramming.DP_on_Strings;

/*
https://takeuforward.org/data-structure/minimum-insertions-to-make-string-palindrome-dp-29/

A palindromic string is a string that is the same as its reverse. For example: “nitin” is a palindromic string. Now the question states that we are given a string, we need to find the minimum insertions that we can make in that string to make it a palindrome.

eg)

I/p : abcaa
Opt : 2 ---> abcacba (insert cb)

Idea:
    Find the longest common palidromic subsequence. Let it be x

    We need to insert all remaining chars other than this sequence.

    Min insertions required = n - x

      I/P : codingninjas

            longest palindromic subsequence = ingni --> x = 5
            n = 12

            Min insertions required = (12-5) = 7

     I/P : abcaa
            longest palindromic subsequence = aba ---> x = 3
            n = 5

            Min insertions required = (5-3) = 2
*/

public class MinimumInsertionsToMakeAStringPalindrome {

    /*
    TC : O(N * N)
    SC : O(N * N)
     */

    public int longestCommonPalindromicSubseq(String s1, String s2){ // longest common subsequence between str and rev(str)

        int n = s1.length();
        int[][] dp = new int[n+1][n+1];

        //0th row
        for(int j = 0 ; j <= n; j++){
            dp[0][j] = 0;
        }
        //0th column
        for(int i=0 ; i <= n; i++){
            dp[i][0] = 0;
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[n][n];
    }

    public int minInsertions_BU(String str){
        StringBuilder s = new StringBuilder(str);
        String rev = s.reverse().toString();
        int x = longestCommonPalindromicSubseq(str,rev);
        return str.length() - x;
    }

    /************************************************************************************************************/

    public int longestCommonPalindromicSubseq_BU_SpaceOptimized(String s1, String s2){ // longest common subsequence between str and rev(str)

        int n = s1.length();
        int[] prev = new int[n+1];
        int[] cur = new int[n+1];
        //int[][] dp = new int[n+1][n+1];

        //0th row
        for(int j = 0 ; j <= n; j++){
            prev[j] = 0;
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    cur[j] = 1 + prev[j-1];
                }else{
                    cur[j] = Math.max(prev[j],cur[j-1]);
                }
            }
            prev = cur.clone();
        }
        return prev[n];
    }

    public int minInsertions_BU_SpaceOptimized(String str){
        StringBuilder s = new StringBuilder(str);
        String rev = s.reverse().toString();
        int x = longestCommonPalindromicSubseq_BU_SpaceOptimized(str,rev);
        return str.length() - x;
    }

    public static void main(String[] args) {
        MinimumInsertionsToMakeAStringPalindrome ob = new MinimumInsertionsToMakeAStringPalindrome();
        System.out.println("********************* BU - Tabulation ************************");
        System.out.println(ob.minInsertions_BU("abcaa"));
        System.out.println(ob.minInsertions_BU("codingninjas"));
        System.out.println("********************* BU - Space Optimized ************************");
        System.out.println(ob.minInsertions_BU_SpaceOptimized("abcaa"));
        System.out.println(ob.minInsertions_BU_SpaceOptimized("codingninjas"));

    }
}
