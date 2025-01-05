package ArraysAndHashing;

import java.util.Arrays;

/*
https://leetcode.com/problems/running-sum-of-1d-array/description/
Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).

Return the running sum of nums.



Example 1:

Input: nums = [1,2,3,4]
Output: [1,3,6,10]
Explanation: Running sum is obtained as follows: [1, 1+2, 1+2+3, 1+2+3+4].
Example 2:

Input: nums = [1,1,1,1,1]
Output: [1,2,3,4,5]
Explanation: Running sum is obtained as follows: [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1].
Example 3:

Input: nums = [3,1,2,10,1]
Output: [3,4,6,16,17]


Constraints:

1 <= nums.length <= 1000
-10^6 <= nums[i] <= 10^6
 */
public class RunningSumOf1dArray {
    /*
    TC : O(n)
    SC : O(n) // result array
     */
    public int[] runningSum(int[] nums) {
        if(nums == null) return new int[]{};

        int prevSum = 0;
        int n = nums.length;
        int[] prevSumArr = new int[n];
        for(int i = 0 ; i < n ; i++){
            prevSumArr[i] = prevSum + nums[i];
            prevSum = prevSumArr[i];
        }
        return prevSumArr;
    }

    public static void main(String[] args) {
        RunningSumOf1dArray ob = new RunningSumOf1dArray();
        System.out.println(Arrays.toString(ob.runningSum(new int[]{1,2,3,4})));
        System.out.println(Arrays.toString(ob.runningSum(new int[]{1,1,1,1,1})));
        System.out.println(Arrays.toString(ob.runningSum(new int[]{3,1,2,10,1})));

    }
}
