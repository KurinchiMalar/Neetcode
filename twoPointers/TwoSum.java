package twoPointers;/*
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.



Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]
Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]


Constraints:

2 <= nums.length <= 104
-109 <= nums[i] <= 109
-109 <= target <= 109
Only one valid answer exists.
 */
import java.util.*;
/*
Time :O(nlogn)+O(n/2) = O(nlogn)
Space : O(n) // sorting +indicesMap
https://leetcode.com/problems/two-sum/submissions/
https://github.com/KurinchiMalar/Neetcode/blob/Arrays/TwoSum.java
 */
class TwoSum {
    public static int[] twoSum(int[] nums, int target) {

        HashMap<Integer,Integer> indicesMap = new HashMap<Integer,Integer>();
        for(int i = 0; i < nums.length ; i++){
            indicesMap.put(nums[i],i);
        }

        Arrays.sort(nums);
        int minIndex = 0;
        int maxIndex = nums.length-1;

        while( minIndex < maxIndex){
            int curSum = nums[minIndex]+nums[maxIndex];
            if( curSum == target){
                break;
            }
            else if(curSum < target){
                minIndex++;
            }else{
                maxIndex--;
            }
        }
        //System.out.println("min: "+nums[minIndex])+"max: "+nums[maxIndex]);
        return new int[]{indicesMap.get(nums[minIndex]),indicesMap.get(nums[maxIndex])};
    }

    //Time :O(n)
    //Space : O(n) //  indicesMap
    public static int[] twoSumAcceptedSolution(int[] nums, int target) {

        HashMap<Integer,Integer> indicesMap = new HashMap<Integer,Integer>();

        for(int i=0; i < nums.length; i++){
            int diff = target-nums[i];
            if(indicesMap.containsKey(diff)){
                return new int[]{i,indicesMap.get(diff)};
            }
            indicesMap.put(nums[i],i);
        }
        return null;
    }

    public static void main(String[] args){
        int[] result = twoSumAcceptedSolution(new int[]{3,3},6);
        for(int res:result){
            System.out.println(res);
        }
    }
}