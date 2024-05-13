package DynamicProgramming;

import com.sun.xml.internal.bind.v2.util.StackRecorder;

import java.util.Arrays;

/*
https://leetcode.com/problems/coin-change/description/

You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.



Example 1:

Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Example 3:

Input: coins = [1], amount = 0
Output: 0


Constraints:

1 <= coins.length <= 12
1 <= coins[i] <= 231 - 1
0 <= amount <= 104

 */
/*
Infinite supply of coins -> Same coin can be used multiple times

You need to take the minimum possible
 */
public class MinimumCoins_CoinChange {
    int MAX = (int) Math.pow(10, 9);
    /*
    TC : much greater than O( 2 pow n)
    SC : O(amount)  ... with 1 denomination
     */
    public int coinChangeRecursion(int i, int amount,int n, int[] coins){
        //if(i < 0 || i >= n) return -1;

        if(i == 0)
        {
            if(amount % coins[i] == 0){
                return amount / coins[i];
            }
            return MAX; // to avoid overflow Intmax + 1 + 1 etc.
        }

        int notTake = 0 + coinChangeRecursion(i-1,amount,n,coins);
        int take = MAX;
        if(coins[i] <= amount){
            take = 1 + coinChangeRecursion(i,amount-coins[i],n,coins); // same elem can be reused. (Infinite supply of coins)
        }
        return Math.min(take,notTake);
    }

    public int coinChangeRec(int[] coins, int amount) {
        int n = coins.length;

        int res =  coinChangeRecursion(n-1,amount,n,coins);
        return (res >= MAX) ? -1 : res;
    }

    /**********************************************************************************************************************/
    /*
    TC : O( N * amount)
    SC : O(N * amount) + O(N)
    My submission : https://leetcode.com/problems/coin-change/submissions/1256923519/
     */
    public int coinChangeTD_Memoization(int i, int amount,int[] coins,int[][] dp){

        if(dp[i][amount] != -1){
            return dp[i][amount];
        }

        if (i == 0){
            if(amount % coins[i] == 0){
                return amount / coins[i];
            }
            return MAX;
        }
        int notTake = coinChangeTD_Memoization(i-1,amount,coins,dp);
        int take = MAX;
        if(coins[i] <= amount){
            take = 1 + coinChangeTD_Memoization(i,amount-coins[i],coins,dp);
        }
        dp[i][amount] = Math.min(take,notTake);
        return dp[i][amount];

    }

    public int coinChange_TD(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount+1];
        for(int[] ar:dp) Arrays.fill(ar,-1);

        int res =  coinChangeTD_Memoization(n-1,amount,coins,dp);
        return (res >= MAX) ? -1 : res;
    }

    /**********************************************************************************************************************/
      /*
    TC : O( N * amount)
    SC : O(N * amount)
      My submission : https://leetcode.com/problems/coin-change/submissions/1256924093/
     */
    public int coinChangeBU_Tabulation(int[] coins,int amount){
        int n = coins.length;
        int[][] dp = new int[n][amount+1];

        for(int j = 0 ; j <= amount; j++){
            if(j % coins[0] == 0){
                dp[0][j] = j / coins[0];
            }else{
                dp[0][j] = MAX;
            }
        }
        for(int i = 1 ; i < n; i++){
            for(int j = 0 ; j <= amount; j++){

                int notTake = dp[i-1][j];
                int take = MAX;
                if(coins[i] <= j){
                    take = 1 + dp[i][j-coins[i]];
                }
                dp[i][j] = Math.min(take,notTake);
            }
        }
        int res =  dp[n-1][amount];
        return (res >= MAX) ? -1 : res;
    }
    /**********************************************************************************************************************/
          /*
    TC : O( N * amount)
    SC : O(amount)
    My submission : https://leetcode.com/problems/coin-change/submissions/1256925126/
     */
    public int coinChangeBU_SpaceOptimized(int[] coins,int amount){
        int n = coins.length;
        //int[][] dp = new int[n][amount+1];

        int[] prev = new int[amount+1];
        int[] cur = new int[amount+1];

        for(int j = 0 ; j <= amount; j++){
            if(j % coins[0] == 0){
                prev[j] = j / coins[0];
            }else{
                prev[j] = MAX;
            }
        }
        for(int i = 1 ; i < n; i++){
            //cur = new int[amount+1];
            for(int j = 0 ; j <= amount; j++){

                int notTake = prev[j];
                int take = MAX;
                if(coins[i] <= j){
                    take = 1 + cur[j-coins[i]];
                }
                cur[j] = Math.min(take,notTake);
            }
            prev = cur;
        }
        int res = prev[amount];
        return (res >= MAX) ? -1 : res;
    }



    public static void main(String[] args) {
        MinimumCoins_CoinChange ob = new MinimumCoins_CoinChange();
        System.out.println(ob.coinChangeRec(new int[]{1,3,5},11));
        System.out.println(ob.coinChangeRec(new int[]{2},3));
        System.out.println(ob.coinChangeRec(new int[]{1},0));
        System.out.println("**************  Memoization ***************");
        System.out.println(ob.coinChange_TD(new int[]{1,3,5},11));
        System.out.println(ob.coinChange_TD(new int[]{2},3));
        System.out.println(ob.coinChange_TD(new int[]{1},0));
        System.out.println("**************  Tabulation ***************");
        System.out.println(ob.coinChangeBU_Tabulation(new int[]{1,3,5},11));
        System.out.println(ob.coinChangeBU_Tabulation(new int[]{2},3));
        System.out.println(ob.coinChangeBU_Tabulation(new int[]{1},0));
        System.out.println("**************  Space Optimized ***************");
        System.out.println(ob.coinChangeBU_SpaceOptimized(new int[]{1,3,5},11));
        System.out.println(ob.coinChangeBU_SpaceOptimized(new int[]{2},3));
        System.out.println(ob.coinChangeBU_SpaceOptimized(new int[]{1},0));




    }
}
