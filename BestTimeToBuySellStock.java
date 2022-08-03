/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.



Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
Example 2:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.


Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 104
 */
/*
Time Complexity : O(N)
Space Complexity : O(1)
https://leetcode.com/problems/best-time-to-buy-and-sell-stock/submissions/
https://www.geeksforgeeks.org/maximum-difference-between-two-elements/
// Maximum difference between two elements such that larger element appears after the smaller number
 */
public class BestTimeToBuySellStock {
    public static int maxProfit(int[] prices) {

        int minBuyPrice = prices[0];
        int maxProfit = Integer.MIN_VALUE;
        for(int i=1; i < prices.length; i++){
            // check if current profit is maxprofit
            if(prices[i]-minBuyPrice > maxProfit ){
                maxProfit = prices[i]-minBuyPrice;
            }

            // check if there is a better buyPrice
            if(prices[i] < minBuyPrice){
                minBuyPrice = prices[i];
            }
        }
        maxProfit = maxProfit > 0?maxProfit:0;
        return maxProfit;
    }

    public static void main(String[] args){
        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));
        System.out.println(maxProfit(new int[]{7,6,4,3,1}));

    }
}