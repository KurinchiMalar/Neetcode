/*
Given an integer array nums that may contain duplicates, return all possible
subsets
 (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.



Example 1:

Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
Example 2:

Input: nums = [0]
Output: [[],[0]]


Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
 */
package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetII {

    public List<List<Integer>> subsetsII(int[] nums) {

        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list,new ArrayList<>(),nums,0);
        return list;
    }

    public void backtrack(List<List<Integer>> list, List<Integer> templist, int[] nums, int start){

        list.add(new ArrayList<>(templist));
        for(int i=start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1])continue; //skip duplicates

            templist.add(nums[i]);
            backtrack(list,templist,nums,i+1);
            templist.remove(templist.size()-1);
        }
    }

    public static void main(String[] args){
        SubsetII ob = new SubsetII();
        System.out.println(ob.subsetsII(new int[]{1,2,2}));
        System.out.println(ob.subsetsII(new int[]{0}));

    }
}
