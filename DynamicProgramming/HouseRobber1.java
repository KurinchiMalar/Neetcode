package DynamicProgramming;

/*

https://leetcode.com/problems/house-robber/description/

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.



Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.


Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 400
 */


import java.util.Arrays;

/*
TC : O(n)
SC : O(n) // memo + recursion
 */
public class HouseRobber1 {

    public int robHelper_TD(int[] nums, int i , int n,int[] memo){

        if(i < 0 || i >= n) return 0;
        if( n == 1 ) return nums[0];

        // Max{ (including i , i+2 to n)   ,  (excluding i, i+1 to n) }
        if(memo[i] == 0){
            memo[i] = Math.max( nums[i] + robHelper_TD(nums,i+2,n,memo) ,
                                          robHelper_TD(nums,i+1,n,memo));
        }

        return memo[i];
    }
    public int robDP_TD(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo,0);

        return  robHelper_TD(nums,0,nums.length,memo);
    }

    /*
    TC : O(n)
    SC : O(n)
    https://leetcode.com/problems/house-robber/submissions/1246290478/
     */

    public int robHelper_BU(int[] nums,int n,int[] memo){
        if(n <= 0 ) return 0;
        if( n == 1 ) return memo[0];
        if(n == 2) return  Math.max(nums[0],nums[1]);

        // memo[i] represent the maximum value stolen so far after reaching house i.

        //Initialize
        memo[0] = nums[0];
        memo[1] = Math.max(nums[0],nums[1]);
        for(int i = 2 ; i < n; i++){
            memo[i] = Math.max(nums[i] + memo[i-2],memo[i-1]);
        }
        return memo[n-1];

    }
    /*
    TC : O(n)
    SC : O(1)
     */

    public  int robHelper_BU_SpaceOptimized(int[]  nums){
        int n = nums.length;
        if(n <= 0)return 0;
        if(n == 1)return nums[0];
        if(n == 2) return  Math.max(nums[0],nums[1]);

        int prev1 = nums[0];
        int prev2 = Math.max(nums[0],nums[1]);
        int maxValue = 0;
        for(int i = 2 ; i < n; i++){
            int take = nums[i] + prev1;
            int notTake = prev2;
            maxValue = Math.max(take,notTake);

            prev1 = prev2;
            prev2 = maxValue;

        }
        return maxValue;
    }
    public int robDP_BU(int[] nums) {
        int[] memo = new int[nums.length+2];
        Arrays.fill(memo,0);

        return  robHelper_BU(nums,nums.length,memo);
    }

    public static void main(String[] args) {
        HouseRobber1 ob = new HouseRobber1();
        System.out.println(ob.robDP_TD(new int[]{6,7,1,30,8,2,4}));
        System.out.println(ob.robDP_TD(new int[]{1,2,3,1}));
        System.out.println(ob.robDP_TD(new int[]{2,7,9,3,1}));
        System.out.println("*********************************************");
        System.out.println(ob.robDP_BU(new int[]{6,7,1,30,8,2,4}));
        System.out.println(ob.robDP_BU(new int[]{1,2,3,1}));
        System.out.println(ob.robDP_BU(new int[]{2,7,9,3,1}));
        System.out.println("*********************************************");
        System.out.println(ob.robHelper_BU_SpaceOptimized(new int[]{6,7,1,30,8,2,4}));
        System.out.println(ob.robHelper_BU_SpaceOptimized(new int[]{1,2,3,1}));
        System.out.println(ob.robHelper_BU_SpaceOptimized(new int[]{2,7,9,3,1}));


    }
}
