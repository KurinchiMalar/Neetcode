package ArraysAndHashing;

import java.util.Arrays;

/*
https://leetcode.com/problems/move-zeroes/description/
Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the array.



Example 1:

Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
Example 2:

Input: nums = [0]
Output: [0]


Constraints:

1 <= nums.length <= 104
-231 <= nums[i] <= 231 - 1


Follow up: Could you minimize the total number of operations done?
 */
/*
TC : O(n)
SC : O(1)
 */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        if(n == 1) return;
        int start = 0;
        for(int i = 0 ; i < n; i++){
            if(nums[i] != 0){
                nums[start] = nums[i];
                start++;
            }
        }
        while(start < n){
            nums[start] = 0;
            start++;
        }
        return ;
    }
    public static void main(String[] args){
        MoveZeroes ob = new MoveZeroes();
        int[] nums = new int[]{0,1,0,3,12};
        ob.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
