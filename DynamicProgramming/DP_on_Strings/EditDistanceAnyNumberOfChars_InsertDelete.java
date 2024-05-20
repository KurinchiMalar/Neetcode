package DynamicProgramming.DP_on_Strings;
/*
https://takeuforward.org/data-structure/minimum-insertions-deletions-to-convert-string-dp-30/


Problem Statement: Minimum Insertions/Deletions to Convert String A to String B

We are given two strings, str1 and str2. We are allowed the following operations:

Delete any number of characters from string str1.
Insert any number of characters in string str1.
We need to tell the minimum operations required to convert str1 to str2.

 */


import java.util.Arrays;

/*
Idea :

   i , j indices in S1 and S2 respectively

   Any number of characters is the catch here.  In Previous Edit Distance only 1 character can be modified in one operation.

   S1.length = n1
   S2.length =  n2

   chars not be touched as they are same already is ===> longest common subsequence (lcs) of S1 and S2

   delete = n1 - len(lcs)  (Deletion of chars from S1)
   insert = n2 - len(lcs)   (insertion of chars into S1 from S2)

    Total operations = delete + insert =
                                        (n1 + n2) - 2len(lcs)
 */
public class EditDistanceAnyNumberOfChars_InsertDelete {

    public int lcs(String s1, String s2,int n1,int n2){
        // Bottom UP lcs
        int[][] dp = new int[n1+1][n2+1];

        //0th row
        for(int j=0; j <= n2; j++){
            dp[0][j] = 0;
        }

        //0th column
        for(int i=0; i <= n1; i++){
            dp[i][0] = 0;
        }

        for(int i=1; i <= n1; i++){
            for(int j=1; j <= n2; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                }
            }
        }
        return dp[n1][n2];
    }

    /*
    My submission : https://www.naukri.com/code360/problems/minimum-number-of-deletions-and-insertions_4244510?leftPanelTabValue=SUBMISSION
    TC : O( N1 * N2) // lcs
    SC : O( N1 * N2)
     */

    public int editDistance_TD(String s1, String s2){
        int n1 = s1.length();
        int n2 = s2.length();
        int lcs = lcs(s1,s2,n1,n2);

        //int delete = n1 - lcs;
        //int insert = n2 - lcs;

        // min operations reqd = insert + delete
        return (n1+n2 - 2*lcs);
    }

    public static void main(String[] args) {
        EditDistanceAnyNumberOfChars_InsertDelete ob = new EditDistanceAnyNumberOfChars_InsertDelete();
        System.out.println("*****************Bottom up ******************************");
        //System.out.println(ob.editDistance_TD("abcd","anc"));
        System.out.println(ob.editDistance_TD("aaa","aa"));
        System.out.println(ob.editDistance_TD("edl","xcqja"));
        System.out.println("*****************Bottom up Space optimized******************************");
        // LCS Space optimization refer.

    }
}
