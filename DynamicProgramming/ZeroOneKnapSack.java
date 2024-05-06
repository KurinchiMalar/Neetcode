package DynamicProgramming;

import java.util.Arrays;

/*
https://takeuforward.org/data-structure/0-1-knapsack-dp-19/
https://www.youtube.com/watch?v=GqOmJHQZivw&t=887s

- Given weights and profits of N items
- Find the maximum profit within given capacity C
- Items cannot be broken
 */
/*
 Greedy will not work because of the following reason
    int[] weights = {3,2,5}
    int[] values = {30,40,60}
    Capacity = 6
    By greedy --> 5  = {60}
    Better --> 2 , 3 = {40 + 30} = 70

    There is no uniformity
 */
public class ZeroOneKnapSack {

    /*
    TC : O(2 pow n)
    SC : O(n)
     */
    public int maxValPossible_Recursion(int i,int W,int[] weights,int[] values){

        if(i < 0 || i >= weights.length){
            return 0;
        }
        if(i == 0){ //single element
            if(weights[0] <= W){
                return values[0];
            }else {
                return 0;
            }
        }

        int notTake = 0 + maxValPossible_Recursion(i-1,W,weights,values); // if we dont take current weight, capacity of bag doesn't change.
        int take = Integer.MIN_VALUE;
        if(weights[i] <= W){
            take = values[i] + maxValPossible_Recursion(i-1,W-weights[i],weights,values);
        }
        return Math.max(take,notTake);

    }

    /*
    TC : O(N * W)
    SC : O(N * W) + O(N) due to stack
     */
    public int maxValPossible_TopDown(int i,int W,int[] weights,int[] values,int[][] dp){

        if(i < 0 || i >= weights.length){
            return 0;
        }
        if(dp[i][W] != -1){ // av
            return dp[i][W];
        }
        if(i == 0){ //single element
            dp[i][W] = (weights[0] <= W) ? values[0]:0;
            return dp[i][W];
        }
        int notTake = 0 + maxValPossible_TopDown(i-1,W,weights,values,dp); // if we dont take current weight, capacity of bag doesn't change.
        int take = Integer.MIN_VALUE;
        if(weights[i] <= W){ // take only if current weight is eligible to put in sack
            take = values[i] + maxValPossible_TopDown(i-1,W-weights[i],weights,values,dp);
        }
        dp[i][W] = Math.max(take,notTake);
        return dp[i][W];
    }
    /*
    TC : O(N * W)
    SC : O(N * W)
     */
    public int maxValPossible_BottomUp(int[] weights,int[] values,int n, int W){
        int[][] dp = new int[n][W+1];

        //Base Condition
        //At ind==0, we are considering the first element,
        // if the capacity of the knapsack is greater than the weight of the first item,
        //       we return val[0] as answer. We will achieve this using a for loop.
        for(int i= weights[0]; i <= W; i++){  // W = 8 , weights[0] = 5 ...... All weights 5 ,6, 7, 8 can be picked as such
            dp[0][i] = values[0];
        }
        //we are done for the first row above

        for(int ind = 1 ; ind < n; ind++){
            for(int cap = 0 ; cap <= W; cap++){

                int notTake = dp[ind-1][cap];
                int take = Integer.MIN_VALUE;
                if(weights[ind] <= cap){
                    take = values[ind] + dp[ind-1][cap - weights[ind]];
                }
                dp[ind][cap] = Math.max(take,notTake);
            }
        }
        // The result is stored in the last row and last column of the DP array
        return dp[n-1][W];
    }
    public static void main(String[] args) {
        ZeroOneKnapSack ob = new ZeroOneKnapSack();
        int[] weights = {1,2,4,5};
        int[] values = {5,4,8,6};
        int N = weights.length;
        int W = 5;
        System.out.println(ob.maxValPossible_Recursion(N-1,W,weights,values));

        System.out.println(ob.maxValPossible_Recursion(2,6,new int[]{3,2,5},new int[]{30,40,60}));
        System.out.println("********* Top down Memoization *************");
        //W=5;
        int[][] dp = new int[N][W+1]; // [index][weight] --> index : 0 to N-1 , weight : 0 to W
        for(int[] ar: dp){
            Arrays.fill(ar,-1);
        }
        System.out.println(ob.maxValPossible_TopDown(N-1,W,weights,values,dp));
        //W=6
        int[][] dp1 = new int[N][7];
        for(int[] ar: dp1){
            Arrays.fill(ar,-1);
        }
        System.out.println(ob.maxValPossible_TopDown(2,6,new int[]{3,2,5},new int[]{30,40,60},dp1));

        System.out.println("********* Bottom up Tabulation *************");
        System.out.println(ob.maxValPossible_BottomUp(weights,values,weights.length,5));
        System.out.println(ob.maxValPossible_BottomUp(new int[]{3,2,5},new int[]{30,40,60},3,6));


    }
}
