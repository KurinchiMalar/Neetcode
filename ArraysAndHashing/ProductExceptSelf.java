package ArraysAndHashing;/*
Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.



Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]
Example 2:

Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]


Constraints:

2 <= nums.length <= 105
-30 <= nums[i] <= 30
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.


Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
 */
import java.util.Arrays;

public class ProductExceptSelf {
    /*
    TC :O(n)
    SC : O(n)
    https://leetcode.com/problems/product-of-array-except-self/submissions/
     */
    public static int[] productExceptSelf(int[] nums) {
        if(nums == null){
            return new int[]{};
        }
        int sourceLen = nums.length;
        int[] leftProduct = new int[sourceLen];
        int[] rightProduct = new int[sourceLen];
        int[] result = new int[sourceLen];

        leftProduct[0] = 1;
        rightProduct[0] = 1;
        int j = sourceLen-2; // one before last element
        for(int i=1; i < sourceLen && j >= 0; i++){
            leftProduct[i] = leftProduct[i-1] * nums[i-1];
            rightProduct[i] = rightProduct[i-1] * nums[j+1];
            j--;
        }
        //System.out.println(Arrays.toString(leftProduct));
        //System.out.println(Arrays.toString(rightProduct));

        j = sourceLen - 1;
        for(int i=0;i < sourceLen; i++){
            result[i] = leftProduct[i] * rightProduct[j];
            j--;
        }
        return result;
    }
    public static void main(String[] args){
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1,2,3,4})));
    }
}
