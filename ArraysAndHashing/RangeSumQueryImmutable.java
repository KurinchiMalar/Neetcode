package ArraysAndHashing;
/*
https://leetcode.com/problems/range-sum-query-immutable/description/
Given an integer array nums, handle multiple queries of the following type:

Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
Implement the NumArray class:

NumArray(int[] nums) Initializes the object with the integer array nums.
int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).


Example 1:

Input
["NumArray", "sumRange", "sumRange", "sumRange"]
[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
Output
[null, 1, -1, -3]

Explanation
NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
 */
/*
Constraints:

1 <= nums.length <= 104
-105 <= nums[i] <= 105
0 <= left <= right < nums.length
At most 10 pow 4 calls will be made to sumRange.
 */


import java.util.Arrays;

public class RangeSumQueryImmutable {
    public int[] nums;
    public int[] prefixSum;
    class NumArray {
        public NumArray(int[] nums) {
            RangeSumQueryImmutable.this.nums = nums;
            constructPrefixSum(nums);
        }
    }

    /*
    Cost of one call proportional to range size k = (right-left)+1
    For 10 pow 4 calls ==> worst case O(10 pow 4  * n)
    TC : O(10 pow 4  * n)
    SC : O(n)  //  nums array
     */
    public int sumRange(int left, int right) {
        int sum = 0;
        if(left < 0 || right >= nums.length || left > right) return sum;
        for(int i = left; i <= right && i < nums.length; i++){
            sum += nums[i];
        }
        return sum;
    }

    public void constructPrefixSum(int[] nums){
        prefixSum = new int[nums.length+1];
        prefixSum[0] = 0; // nothing on left
        for(int i = 1; i <= nums.length;i++){
            prefixSum[i] = prefixSum[i-1]+nums[i-1];// all to the left excluding itself
        }
        System.out.println("Prefix Sum Arr: "+ Arrays.toString(prefixSum));
    }

    /*
    TC : O(1) :)
    SC : O(n)
     */
    public int sumRangeEfficientUsingPrefixSum(int left, int right){
        // Whole sum from beginning including right - (sum from beginning excluding left)
        return prefixSum[right+1]-prefixSum[left];
    }

    public static void main(String[] args) {
        RangeSumQueryImmutable ob = new RangeSumQueryImmutable();
        ob.new NumArray(new int[]{-2,0,3,-5,2,-1});
        System.out.println(ob.sumRange(0,2));
        System.out.println(ob.sumRange(2,5));
        System.out.println(ob.sumRange(0,5));
        System.out.println("************** Efficient using Prefix Sum **************************");
        System.out.println(ob.sumRangeEfficientUsingPrefixSum(0,2));
        System.out.println(ob.sumRangeEfficientUsingPrefixSum(2,5));
        System.out.println(ob.sumRangeEfficientUsingPrefixSum(0,5));

    }
}
