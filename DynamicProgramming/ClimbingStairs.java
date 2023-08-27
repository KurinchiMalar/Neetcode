package DynamicProgramming;

/*
You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?



Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step


Constraints:

1 <= n <= 45
 */
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
