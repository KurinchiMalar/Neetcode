package DynamicProgramming;

import java.util.ArrayList;
import java.util.List;
public class ClimbingStairs {

    public int climbStairs(int n) {
        /*
        Using recursion
        // fibonacci
        TC : O(2 pow n)
        SC : O(n)
        if (n == 0 || n == 1) {
            return 1;
        }
        return climbStairs(n-1) + climbStairs(n-2);

        */

        /*-------------------------------------------------------*/
        /*
        /*
        Using memoization (TOP DOWN DP)
        TC: O(n)
        SC : O(n)(for dp array) + O(n) (for recursive stack) = O(n)
        HashMap<Integer,Integer> hmap = new HashMap<>();
        if(n==0 || n==1){
            return 1;
        }
        if(!hmap.containsKey(n)){
            hmap.put(n,climbStairs(n-1)+climbStairs(n-2));
        }
        return hmap.get(n);*/
        /*-------------------------------------------------------*/
        /*
        Using dp tabulation (BOTTOM UP DP)
        TC: O(n)
        SC : O(n)
        int[] dp = new int[n+1];
        if(n == 0 || n == 1){
            return 1;
        }
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2 ; i <= n ; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];

        */
        /*-------------------------------------------------------*/
        /* further space optimization*/
        //TC: O(n)
        //SC : O(1) , size of prev and cur
        int prev = 1;
        int cur = 1;
        if( n == 0 || n == 1){
            return 1;
        }
        for(int i = 2 ; i <= n ; i++){
            int temp = cur;
            cur = prev + cur;
            prev = temp;
        }
        return cur;

    }


    public static void main(String[] args){
        ClimbingStairs ob = new ClimbingStairs();
        System.out.println(String.valueOf(ob.climbStairs(5)));
    }
}
