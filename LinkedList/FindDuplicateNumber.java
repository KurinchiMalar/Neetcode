/*
287. Find the Duplicate Number
Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.

There is only one repeated number in nums, return this repeated number.

You must solve the problem without modifying the array nums and uses only constant extra space.



Example 1:

Input: nums = [1,3,4,2,2]
Output: 2
Example 2:

Input: nums = [3,1,3,4,2]
Output: 3


Constraints:

1 <= n <= 105
nums.length == n + 1
1 <= nums[i] <= n
All the integers in nums appear only once except for precisely one integer which appears two or more times.

 */
/*
TC : O(n)
SC : O(1)
https://leetcode.com/problems/find-the-duplicate-number/description/

Equation ===>
2 * slow = fast
2 * (p + c-x) = p + c + c - x
2p + 2c -2x = p + 2c -x
      p = x   .... There if we have two pointers one at start and other at cycle node and move towards each other we will get the duplicate number.
 */
package LinkedList;

public class FindDuplicateNumber {

    public int findDuplicate(int[] nums) {

        int fast = 0;
        int slow = 0;
        while(true){
            fast = nums[nums[fast]];
            slow = nums[slow];
            if(fast == slow)break;
        }

        int slow2 = 0;
        while(true){
            slow2 = nums[slow2];
            slow = nums[slow];
            if(slow2 == slow){
                return slow;
            }
        }
    }
}
