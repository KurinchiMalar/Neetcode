package Greedy;

import java.util.Arrays;
import java.util.Comparator;

/*

https://www.geeksforgeeks.org/fractional-knapsack-problem/

Given the weights and profits of N items, in the form of {profit, weight} put these items in a knapsack of capacity W to get the maximum total profit in the knapsack. In Fractional Knapsack, we can break items for maximizing the total value of the knapsack.

Input: arr[] = {{60, 10}, {100, 20}, {120, 30}}, W = 50
Output: 240
Explanation: By taking items of weight 10 and 20 kg and 2/3 fraction of 30 kg.
Hence total price will be 60+100+(2/3)(120) = 240

Input:  arr[] = {{500, 30}}, W = 10
Output: 166.667

IDEA:
1) Calculate the density / ratio of each item ---ratio = profit/weight  (1 weight's profit ?)
2) Sort the items based on this ratio
3) Pick items with the highest ratio sequentially until capacity allows
4) Add the next item as much (fractional) as we can
 */

/*
TC : O(nlogn)
SC : O(n) // ratio wise sorting consumes space corresponding to n items.
Doesn't matter which sorting algorithm we use for sorting, it will require O(n) extra space because we need to combine value and weight arrays using a 2-D array/list to sort them. Hence the overall space complexity is O(n)
 */
public class FractionalKnapSack {

    static class Item{
        int weight;
        int profit;
        Item(int weight, int profit){
            this.weight = weight;
            this.profit = profit;
        }
    }

    public double getMaxProfit(Item[] itemArr,int capacity){
        double maxProfit = 0d;
        // sort descending itemArr based on weight to profit ratio
        Arrays.sort(itemArr,new Comparator<Item>(){  // O(nlogn)
            @Override
            public int compare(Item i1,Item i2){
                double d1 = (double)i1.profit/(double)i1.weight;
                double d2 = (double)i2.profit/(double)i2.weight;

                // descending order of ratio is expected.
                if (d1 < d2)  // not in descending so return 1
                    return 1;
                else      // already in descending so return -1
                    return -1;            }
        });

        for(Item item: itemArr){ // O(n)

            int curWeight = item.weight;
            int curProfit = item.profit;

            if(capacity - curWeight >= 0){
                // the weight can be taken as a whole , knapsack has the ability to hold this weight as a whole.

                capacity = capacity - curWeight;
                maxProfit += curProfit;
            }else{
                // capacity is less than curWeight, we need to find the fraction of curWeight to be added to knapsack

                double fraction = (double) capacity / (double)curWeight;

                capacity = (int)(capacity -(fraction * curWeight));
                maxProfit += (fraction * curProfit);
                break; // IMPORTANT!! after fractional value there is no more capacity to continue so breaking out.
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        FractionalKnapSack ob = new FractionalKnapSack();
        Item[] itemArr = {new Item(10,60),
                          new Item(20,100),
                          new Item(30,120)};
        System.out.println("Max Profit : "+ob.getMaxProfit(itemArr,50));
    }
}
