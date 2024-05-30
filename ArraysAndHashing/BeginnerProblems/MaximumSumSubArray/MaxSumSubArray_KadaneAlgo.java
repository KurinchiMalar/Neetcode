package ArraysAndHashing.BeginnerProblems.MaximumSumSubArray;

import java.util.Arrays;

public class MaxSumSubArray_KadaneAlgo {

    /*
    TC : O(n)
    SC : O(1)
    https://leetcode.com/problems/maximum-subarray/description/

    Additional tweak to question:
    Find the sum of the subarray (including empty subarray) having maximum sum among all subarrays.
    The sum of an empty subarray is 0.

    https://www.youtube.com/watch?v=AHZpyENo7k4

     */
    /*
    Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.
Example 2:

Input: nums = [1]
Output: 1
Explanation: The subarray [1] has the largest sum 1.
Example 3:

Input: nums = [5,4,-1,7,8]
Output: 23
Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.


Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104

     */
    public int maxSubArray(int[] nums) {

        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;
        for(int i = 0; i < nums.length;i++){
            curSum += nums[i];

            maxSum = Integer.max(maxSum,curSum);
            curSum = Math.max(curSum, 0);  // curSum if negative need not carry forward because that is anyway going to impact our window sum. Therefore ignore this and make it 0. (we are looking for a new window now)
        }
        return Math.max(maxSum, 0); // for empty subarray.
    }

    /*
    TC : O(n)
    SC: O(1)
     */
    public int[] getMaxSubArray(int[] nums){
        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;
        int curWindowStart = 0;
        int ansStart = -1;
        int ansEnd = -1;
        for(int i = 0; i < nums.length;i++){
            if(curSum == 0) {
                // we are initiating a new window
                curWindowStart = i;
            }
            curSum += nums[i];

            //maxSum = Integer.max(maxSum,curSum);
            if(curSum > maxSum){  // this is a potential answer window so update the ans indices
                maxSum = curSum;
                ansStart = curWindowStart;
                ansEnd = i;
            }
            curSum = Math.max(curSum, 0);
        }
        return Arrays.copyOfRange(nums,ansStart,ansEnd+1);
    }


    /* Tried to replicate without seeing :) */

    public int maxSubArrayTest(int[] nums){

        int n = nums.length;
        int curSum = 0;
        int maxSum = 0;
        int start_i = 0;
        int end_i = 0;
        for(int i = 0 ; i < n; i++){
            if(curSum == 0){
                start_i = i;
            }
            curSum += nums[i];
            curSum = (curSum <= 0)?0 : curSum;
            if(maxSum < curSum){
                end_i = i;
                maxSum = curSum;
            }
        }
        System.out.println(Arrays.toString(Arrays.copyOfRange(nums,start_i,end_i+1)));
        return maxSum;
    }

    public static void main(String[] args) {
        MaxSumSubArray_KadaneAlgo ob = new MaxSumSubArray_KadaneAlgo();
        System.out.println(ob.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(ob.maxSubArray(new int[]{5,4,-1,7,8}));
        System.out.println(ob.maxSubArray(new int[]{-1}));  // if in question , return 0 if there is no maxSumSubArray

        System.out.println(Arrays.toString(ob.getMaxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4})));
        System.out.println(Arrays.toString(ob.getMaxSubArray(new int[]{5,4,-1,7,8})));


        System.out.println("*****************************");
        System.out.println(ob.maxSubArrayTest(new int[]{-2,1,-3,4,-1,2,1,-5,4}));


    }

}
