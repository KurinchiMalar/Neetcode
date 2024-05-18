package DynamicProgramming.DP_on_Strings;

import java.util.Arrays;

/*
https://takeuforward.org/data-structure/longest-common-substring-dp-27/
 */
public class LongestCommonSubstring {

    /*
    My submission : https://www.naukri.com/code360/problems/longest-common-substring_1235207?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos
    TC: O(N1 * N2)
    SC: O( N1 * N2)
     */
    public int longestCommonSubString_BU_Tabulation(String s1, String s2){

        int n1 = s1.length();
        int n2 = s2.length();

        int[][] dp = new int[n1 + 1][n2 + 1];
        int result = Integer.MIN_VALUE;

        //Base Case
        //0th row
        for(int j = 0 ; j <= n2; j++){
            dp[0][j] = 0;
        }
        //0th column
        for(int i = 0; i <= n1; i++){
            dp[i][0] = 0;
        }
        for(int i = 1; i <= n1; i++){
            for(int j = 1 ; j <= n2; j++){

                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                    result = Math.max(result,dp[i][j]); // update the result as well , so that you dont have to traverse dp again
                }else{
                    dp[i][j] = 0;  // We are not dependent on the previous ones, since substring should be continuous/consecutive
                }
            }
        }

        return result; // this is the max in entire dp

    }

    /*
    My submission: https://www.naukri.com/code360/problems/longest-common-substring_1235207?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos
    TC : O(N1 * N2)
    SC : O( N2)
     */

    public int longestCommonSubString_BU_Tabulation_SpaceOptimized(String s1, String s2){

        int n1 = s1.length();
        int n2 = s2.length();

        int[] prev = new int[n2+1];
        int[] cur = new int[n2+1];

        int result = Integer.MIN_VALUE;
        /*Base Case
        //0th row
        for(int j = 0 ; j <= n2; j++){
            prev[j] = 0;
        }*/

        for(int i = 1; i <= n1; i++){
            for(int j = 1 ; j <= n2; j++){

                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    cur[j] = 1 + prev[j-1];
                    result = Math.max(result,cur[j]);
                }else{
                    cur[j] = 0;  // We are not dependent on the previous ones, since substring should be continuous/consecutive
                }
            }
            prev = cur.clone();
        }

        return result;

    }

    public static void main(String[] args) {
        LongestCommonSubstring ob = new LongestCommonSubstring();
        System.out.println("************************ BU Tabulation ****************************");
        System.out.println(ob.longestCommonSubString_BU_Tabulation("abcde","bcd"));
        System.out.println(ob.longestCommonSubString_BU_Tabulation("abcjklp","jk"));
        System.out.println(ob.longestCommonSubString_BU_Tabulation("wasdijkl","wsdjkl"));
        System.out.println("************************ Space Optimized ****************************");
        System.out.println(ob.longestCommonSubString_BU_Tabulation_SpaceOptimized("abcde","bcd"));
        System.out.println(ob.longestCommonSubString_BU_Tabulation_SpaceOptimized("abcjklp","jk"));
        System.out.println(ob.longestCommonSubString_BU_Tabulation_SpaceOptimized("wasdijkl","wsdjkl"));

    }
}
