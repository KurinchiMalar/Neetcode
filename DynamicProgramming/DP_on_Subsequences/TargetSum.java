package DynamicProgramming.DP_on_Subsequences;
/*
https://leetcode.com/problems/target-sum/description/

https://takeuforward.org/data-structure/target-sum-dp-21/
https://www.youtube.com/watch?v=b3GD8263-PQ

You are given an integer array nums and an integer target.

You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.

For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.



Example 1:

Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
Example 2:

Input: nums = [1], target = 1
Output: 1


Constraints:

1 <= nums.length <= 20
0 <= nums[i] <= 1000
0 <= sum(nums[i]) <= 1000
-1000 <= target <= 1000
 */

import java.util.Arrays;

public class TargetSum {

    /*
    TC : O(2 pow n)
    SC : O(n) // recursive stack
     */
    public int findTargetSumWays_Recursion(int i, int curTarget, int target, int n, int[] nums){

        // If target is reached, return 1
        if(i == n && curTarget == target) {
            return 1;
        }

        // If all elements are processed and
        // target is not reached, return 0
        if(i >= n){
            return 0;
        }

        int plusCount = findTargetSumWays_Recursion(i+1,curTarget + nums[i],target,n,nums);
        int minusCount = findTargetSumWays_Recursion(i+1,curTarget - nums[i],target,n,nums);
        return plusCount + minusCount;

    }
    public int findTargetSumWaysRecursion(int[] nums, int target) {

        int n = nums.length;
        return findTargetSumWays_Recursion(0,0,target,n,nums);

    }

    /********************************************************************************************************************************/
    /*
    We can reuse the problem count ways to partition such that S1 - S2 = D // CountPartitionsWithGivenDifference

    This problem (positive elems) + (negativeElems) = target
     */

    public int countPartitionsWithGivenTarget(int i, int target,int n, int[] nums,int[][] dp){
        if( i < 0 || i >= n) return 0;

        if(dp[i][target] != -1){
            return dp[i][target];
        }
        if(i == 0){
            if(nums[i] == 0 && target == 0) return 2;
            if(target == 0 || nums[i] == target) return 1;
            return 0;
        }

        int notTake = countPartitionsWithGivenTarget(i-1,target,n,nums,dp);
        int take = (nums[i] <= target) ? countPartitionsWithGivenTarget(i-1,target-nums[i],n,nums,dp) : 0;

        dp[i][target] = take + notTake;
        return dp[i][target];
    }
    /*
    TC : O(n) for sum + O(n * target)
    SC : O(n * target) + O(n)
    My submission: https://leetcode.com/problems/target-sum/submissions/1255641721/
     */

    public int findTargetSumWays(int[] nums, int target) {

        int n = nums.length;

        if(n <= 0 ) return 0;
        if(n == 1 && nums[0] == target) return 1;

        // find the total
        int total = Arrays.stream(nums).sum();

        // 2 partitions such that diff is target
        int numerator = (total - target);
        if(numerator < 0  || (numerator % 2 != 0)){
            return 0;
        }
        int t = numerator / 2; // as per equation
        int[][] dp = new int[n][t+1];
        for(int[] ar: dp)Arrays.fill(ar , -1);

        return countPartitionsWithGivenTarget(n-1,t,n,nums,dp);

    }



    public static void main(String[] args) {
        TargetSum ob = new TargetSum();
        System.out.println(ob.findTargetSumWaysRecursion(new int[]{1,1,1,1,1},3));
        System.out.println(ob.findTargetSumWays(new int[]{1,1,1,1,1},3));


    }
}
