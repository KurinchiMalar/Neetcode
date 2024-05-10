package DynamicProgramming;

import java.util.Arrays;

/*

DP 15. Partition Equal Subset Sum | DP on Subsequences
https://www.youtube.com/watch?v=7win3dcgo3k&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=17
https://takeuforward.org/data-structure/partition-equal-subset-sum-dp-15/

https://leetcode.com/problems/partition-equal-subset-sum/description/
Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.



Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.


Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 100
 */
/*
IDEA:

    Let S be the sum of all the elements in nums[].
    S1 and S2 be sum of individual partitions of S.

    if S is odd --> we cannot partition such that S1 == S2

    if S is even ---> if we are able to find one subset S1 == S/2 , then it means we have two subsets S1 and S2 each of sum S/2
 */
/*
My submission : https://leetcode.com/problems/partition-equal-subset-sum/submissions/1254256551/
 */
public class PartitionEqualSubsetSum {


    public boolean isExistsSubset(int i,int target,int n, int[] nums,int[][] dp){

        if(i < 0 || i >= n) return false;
        if(dp[i][target] != -1) return (dp[i][target] == 1 )? true:false;

        if(i == 0){ // single elem
            if(nums[i] == 0 && target == 0)return true;
            if(target == 0 || nums[i] == target) return true;
            return false;
        }

        boolean notTake = isExistsSubset(i-1,target,n,nums,dp);
        boolean take = nums[i] <= target ? isExistsSubset(i - 1, target - nums[i], n, nums, dp):false;
        dp[i][target] = (take || notTake) ? 1 : 0;
        return (take || notTake);

    }
    /*
    TC : O(n) to find sum + O(n * target) identify subst
    SC : O(n * target) + O(n) for recursion stack
     */
    public boolean canPartition(int[] nums) {

        int sum = Arrays.stream(nums).sum();
        int n = nums.length;
        if(sum % 2 != 0) return false ; // odd length cannot be partitioned
        int target = sum / 2 ; //Single partition sum
        int[][] dp = new int[n][target+1];
        for(int[] ar: dp){
            Arrays.fill(ar,-1);
        }
        // if there is one subset with sum = sum/2 then there will be another as well. As it is even length array.
        return  isExistsSubset(n-1,target,n,nums,dp);

    }

    public static void main(String[] args) {
        PartitionEqualSubsetSum ob = new PartitionEqualSubsetSum();
        System.out.println(ob.canPartition(new int[]{1,5,11,5}));
        /*int[] nums = {1,2,3};
        int sum = Arrays.stream(nums).sum();
        System.out.println(sum);
        System.out.println(Arrays.stream(nums).reduce(0,(a,b)->a+b));*/

    }
}
