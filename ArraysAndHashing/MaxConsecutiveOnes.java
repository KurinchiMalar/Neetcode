package ArraysAndHashing;
/*
Given a binary array nums, return the maximum number of consecutive 1's in the array.



Example 1:

Input: nums = [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
Example 2:

Input: nums = [1,0,1,1,0,1]
Output: 2


Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
 */
public class MaxConsecutiveOnes {

    public int findMaxConsecutiveOnes(int[] nums) {
        if(nums == null ) return 0;
        int maxCount = Integer.MIN_VALUE;
        int curCount = 0;
        for(int num: nums){
            if(num == 1){
                curCount++;
                maxCount = Math.max(curCount, maxCount);

            }else{
                curCount = 0;
            }
        }
        return maxCount==Integer.MIN_VALUE?0:maxCount;
    }

    public static void main(String[] args) {
        MaxConsecutiveOnes ob = new MaxConsecutiveOnes();
        System.out.println(ob.findMaxConsecutiveOnes(new int[]{1,1,0,1,1,1}));
        System.out.println(ob.findMaxConsecutiveOnes(new int[]{1,0,1,1,0,1}));
    }
}
