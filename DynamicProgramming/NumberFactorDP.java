package DynamicProgramming;

import java.util.HashMap;
import java.util.stream.IntStream;

/*
Given N , find number of ways to express N as sum of 1, 3 and 4

Idea : N's combination will be adding 1 to the result so far previously.

*/
public class NumberFactorDP {

    /*
    TC : O(n)
    SC : O(n) // recursion + memo
     */
    public static int waysUsing134DP_TD(int n,HashMap<Integer,Integer> memo){
        if(n <=2) return 1; // {}
        if(n == 3) return 2;
        if(!memo.containsKey(n)){
            return waysUsing134DP_TD(n-1,memo) + waysUsing134DP_TD(n - 3,memo)+ waysUsing134DP_TD(n - 4,memo);
        }
        return memo.get(n);
    }

    /*
   TC : O(n)
   SC : O(n) // memo
    */
    public static int waysUsing134DP_BU(int n,HashMap<Integer,Integer> memo){
        if(n <=2){
            memo.put(n,1);
        }
        if(n == 3) memo.put(n,2);
        for(int i = 4; i <= n; i++){
            if(!memo.containsKey(n)){
                memo.put(n,memo.get(n-1)+memo.get(n-3)+memo.get(n-4));
            }
        }
        return memo.get(n);
    }

    public static void main(String[] args) {
        HashMap<Integer,Integer> memoTD = new HashMap<Integer,Integer>();
        IntStream.rangeClosed(0,10).forEach(i-> System.out.println("f( "+i+" )"+" = "+waysUsing134DP_TD(i,memoTD)));
        System.out.println("******************************************************************");
        HashMap<Integer,Integer> memoBU = new HashMap<Integer,Integer>();
        IntStream.rangeClosed(0,10).forEach(i-> System.out.println("f( "+i+" )"+" = "+waysUsing134DP_BU(i,memoBU)));

    }

}
