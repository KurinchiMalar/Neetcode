package BinarySearch;
/*
Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.

You must write an algorithm with O(log n) runtime complexity.



Example 1:

Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4
Example 2:

Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1


Constraints:

1 <= nums.length <= 104
-104 < nums[i], target < 104
All the integers in nums are unique.
nums is sorted in ascending order.
 */
/*
TC : O(logn)
SC : O(logn) for stack recursive approach , O(1) for iterative approach
 */
public class BinarySearch {
    public static int search(int[] nums, int target) {
        //return bstRecursive(nums,0,nums.length-1,target);
        return bstIterative(nums,0,nums.length-1,target);
    }

    public static int bstRecursive(int[] nums,int start,int end,int target){

        if(start==end && nums[start]!=target){
            return -1;
        }

        int mid = (start+end)/2;
        if(target == nums[mid]){
            return mid;
        }
        else if(target < nums[mid]){
             return bstRecursive(nums,start,mid,target);
        }
        return bstRecursive(nums,mid+1,end,target);

    }
    public static int bstIterative(int[] nums,int start,int end,int target){

        while(start <= end){
            if(start == end && target != nums[start]){
                return -1;
            }
            int mid = (start+end)/2;
            if(target == nums[mid]){
                return mid;
            }else if(target < nums[mid]){
                end = mid;
            }else{
                start = mid + 1;
            }
        }
        return -1;
    }


        public static void main(String[] args){
        System.out.println(search(new int[]{-1,0,3,5,9,12},2));
        System.out.println(search(new int[]{-1,0,3,5,9,12},9));
        System.out.println(search(new int[]{5},5));
        System.out.println(search(new int[]{5},2));
        }
}
