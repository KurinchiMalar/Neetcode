package MustDo500;
/*

https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
https://leetcode.com/problems/best-time-to-buy-and-sell-stock/submissions/1269258353/
 */
public class A10_BestTimeToBuySellStock {

    /*
    TC : O(n)
    Sc : O(1)
     */
    public int maxProfit(int[] prices) {

        int maxProfit = Integer.MIN_VALUE;
        int bestBuyPrice = prices[0];

        for(int i = 1 ; i < prices.length; i++){
            int curProfit = prices[i] - bestBuyPrice;
            curProfit = (curProfit < 0) ? 0 : curProfit;

            maxProfit = Math.max(curProfit,maxProfit);

            // do we have a better buy price
            bestBuyPrice = Math.min(bestBuyPrice,prices[i]);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        A10_BestTimeToBuySellStock ob = new A10_BestTimeToBuySellStock();
        System.out.println(ob.maxProfit(new int[]{7,1,5,3,6,4}));
        System.out.println(ob.maxProfit(new int[]{7,6,4,3,1}));
    }
}
