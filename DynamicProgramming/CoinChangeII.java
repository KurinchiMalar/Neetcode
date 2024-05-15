package DynamicProgramming;

import java.util.Arrays;

/*
https://leetcode.com/problems/coin-change-ii/description/

https://takeuforward.org/data-structure/coin-change-2-dp-22/
https://leetcode.com/problems/coin-change-ii/submissions/1258729109/
 */
public class CoinChangeII {

    /*
    TC : O( N * amount)
    SC : O(N * amount) + O(N)
     */

    public int changeUtil(int i, int amount, int[] coins, int[][] dp) {

        if(dp[i][amount] != -1) return dp[i][amount];
        if(i == 0){
            if(amount % coins[i] == 0) return 1; // a valid combination
            return 0;
        }
        int notTake = changeUtil(i-1,amount,coins,dp);
        int take = 0;
        if(coins[i] <= amount){
            take = changeUtil(i,amount-coins[i],coins,dp);
        }

        dp[i][amount] = take + notTake;
        return dp[i][amount];
    }

    public int change( int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount+1];
        for(int[] ar: dp) Arrays.fill(ar,-1);
        return changeUtil(n-1,amount,coins,dp);

    }
    /*
    TC : O( N * amount)
    SC : O(N * amount) + O(N)
    My submission : https://leetcode.com/problems/coin-change-ii/submissions/1258735811/
     */
    public int change_BU_Tabulation(int[] coins,int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount+1];
        for(int[] ar: dp) Arrays.fill(ar,0);

        for(int j = 0 ; j <= amount; j++){
            if(j % coins[0] == 0){
                dp[0][j] = 1;
            }else{
                dp[0][j] = 0;
            }

        }
        for(int i = 1; i < n; i++){
            for(int t = 0; t <= amount; t++){
                int notTake = dp[i-1][t];
                int take = 0;
                if(coins[i] <= t){
                    take = dp[i][t-coins[i]];
                }

                dp[i][t] = take + notTake;
            }
        }


        return dp[n-1][amount];
    }

    public static void main(String[] args) {
        CoinChangeII ob = new CoinChangeII();
        System.out.println(ob.change(new int[]{1,2,5},5));
        System.out.println(ob.change_BU_Tabulation(new int[]{1,2,5},5));

    }
}
