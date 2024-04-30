package DivideAndConquer;

/*

https://leetcode.com/problems/house-robber/description/

You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed,
 the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected
 and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house,
return the maximum amount of money you can rob tonight without alerting the police.



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


/*
TC : O(2 ^ n)
SC : O(n)
 */
public class HouseRobber {
    public int robHelper(int[] nums, int i , int n){

        if( n <= 0 ) return 0;
        if( n == 1 ) return nums[0];
        if(i < 0 || i >= n)return 0;

        // Max{ (including i , i+2 to n)   ,  (excluding i, i+1 to n) }
        return Math.max( nums[i] + robHelper(nums,i+2,n) , robHelper(nums,i+1,n));
    }
    public int rob(int[] nums) {
        return  robHelper(nums,0,nums.length);
    }

    public static void main(String[] args) {
        HouseRobber ob = new HouseRobber();
        System.out.println(ob.rob(new int[]{6,7,1,30,8,2,4}));
        System.out.println(ob.rob(new int[]{1,2,3,1}));

    }
}
