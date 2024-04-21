package Greedy;

import java.util.ArrayList;
import java.util.Arrays;

/*
https://leetcode.com/problems/coin-change/description/

This is a naive solution and doesnt work for certain cases. The best solution for this question is to use dp.
However implementing the greedy approach for understanding purposes.
 */

/*
Time complexity of the greedy coin change algorithm will be:

For sorting n coins O(nlogn).
While loop, the worst case is O(amount). If all we have is the coin with 1-denomination.
Complexity for coin change problem becomes O(n log n) + O(amount).

TC : O(n log n) + O(amount)
SC : O(amount) // to store the result (Denominations that produce the amount) ---if all 1 rs coin denomination (worst casen will be equal to amount)
 */
public class MinimumCoins {

    public static int coinChange(int[] coins, int amount) {
        int bkp = amount;
        int n = coins.length;
        ArrayList<Integer> result = new ArrayList<>();
        int totalCoins = 0;

        Arrays.sort(coins);
        int j = n-1;
        while(amount > 0 && j >= 0 ){

            while( coins[j] <= amount){
                amount = amount - coins[j];
                result.add(coins[j]);
                totalCoins++;
            }
            j--;
        }
        //System.out.println("amount remaining : "+amount);
        if(amount > 0){
            return -1;
        }
        System.out.println("Minimal change for rs:"+bkp+" is :- "+result);
        return totalCoins;
    }

    public static void main(String[] args) {

        System.out.println(coinChange(new int[]{1,2,5},11));
        System.out.println(coinChange(new int[]{2},3));
        System.out.println(coinChange(new int[]{1},0));



        System.out.println(coinChange(new int[]{186,419,83,408},6249));  // Greedy output = -1 , Expected = 20 (Need to go for DP approach)

    }
}
