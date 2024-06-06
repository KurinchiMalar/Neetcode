package MustDo500;
/*
https://leetcode.com/problems/climbing-stairs/

Constraints:

1 <= n <= 45
 */
import java.util.Arrays;
public class A26_ClimbingStairs {
    // Recursion
    /*
    My submission : https://leetcode.com/problems/climbing-stairs/submissions/1277981393/
     */
    public int climbStairs(int n) {
        // n is used as index here, 0th stair = 1 step
        if(n < 0) return 0;

        if(n == 0 ) return 1;


        return climbStairs(n-1)+climbStairs(n-2);

    }

    /*
    TopDown
    My submission : https://leetcode.com/problems/climbing-stairs/submissions/1277989516/

     */


    public int topdown(int n ,int[] dp){

        if(n < 0) return 0;
        if(n == 0 ) return 1;
        if(dp[n] != 0) return dp[n];

        dp[n] = topdown(n-1,dp)+topdown(n-2,dp);

        return dp[n];

    }
    public int climbStairs_TD(int n) {

        int[] dp = new int[n+1];
        Arrays.fill(dp,0);
        return topdown(n,dp);
    }

    /*Bottom up
    My submission : https://leetcode.com/problems/climbing-stairs/submissions/1277994364/
     */
    public int climbStairs_BU(int n) {

        int[] dp = new int[n+1];
        if(n == 0 || n == 1){
            return 1;
        }
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        A26_ClimbingStairs ob = new A26_ClimbingStairs();
        System.out.println("************* Recursion ****************");
        System.out.println(ob.climbStairs(4));
        System.out.println("************* TopDown ****************");
        System.out.println(ob.climbStairs_TD(4));
        System.out.println("************* BottomUp ****************");
        System.out.println(ob.climbStairs_BU(4));
    }
}
