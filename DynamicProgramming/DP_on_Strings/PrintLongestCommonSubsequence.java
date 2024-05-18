package DynamicProgramming.DP_on_Strings;

public class PrintLongestCommonSubsequence {

    /*
    TC : O( N1 * N2)
    SC : O(N1 * N2)
     */

    public String printLCS_BU_Tabulation(String s1, String s2){
        int n1 = s1.length();
        int n2 = s2.length();

        int[][] dp = new int[n1+1][n2+1];
        StringBuilder result = new StringBuilder();

        //Base Case i < 0,  j < 0 return 0
        // 0th row
        for(int j = 0 ; j <= n2; j++) dp[0][j] = 0;
        // 0th column
        for(int i = 0 ; i <= n1; i++) dp[i][0] = 0;

        for(int i = 1; i <= n1; i++){
            for(int j = 1; j <= n2; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        System.out.println("LCS value :"+ dp[n1][n2]);

        // To print the LCS , traverse the dp array from end ie) the lcs point
        int i  = n1;
        int j  = n2;
        while ( i > 0 && j > 0){

            if(s1.charAt(i-1) == s2.charAt(j-1)){
                result.insert(0,s1.charAt(i-1)); // this prepends the character, insert at beginning
                i = i-1;
                j = j-1;
            }else{
                if(dp[i-1][j] > dp[i][j-1]){
                    i = i - 1;
                }else{
                    j = j - 1;
                }
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        PrintLongestCommonSubsequence ob = new PrintLongestCommonSubsequence();
        System.out.println(ob.printLCS_BU_Tabulation("abcde","ace"));
        System.out.println(ob.printLCS_BU_Tabulation("abcde","bdgek"));


    }
}
