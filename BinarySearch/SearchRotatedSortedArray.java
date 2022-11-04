/*
There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.



Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
Example 3:

Input: nums = [1], target = 0
Output: -1


Constraints:

1 <= nums.length <= 5000
-104 <= nums[i] <= 104
All values of nums are unique.
nums is an ascending array that is possibly rotated.
-104 <= target <= 104
 */
/*
TC : O(log n)
SC : O(1)
https://leetcode.com/problems/search-in-rotated-sorted-array/

 */
package BinarySearch;

public class SearchRotatedSortedArray {
    public static int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while(left <= right){

            int mid = left + ((right-left)>>1);

            if(nums[mid] == target){
                return mid;
            }

            //left sorted array
            if(nums[left] <= nums[mid]){ // potential left half
                if(target > nums[mid] || target < nums[left]){ // go to right half
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }else{ // potential right half
                if(target < nums[mid] || target > nums[right] ){ // go to left half
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args){
        System.out.println(search(new int[]{4,5,6,7,8,1,2,3},8));
        System.out.println(search(new int[]{3,1},1));

    }
}
