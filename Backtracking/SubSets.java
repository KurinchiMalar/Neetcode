
/*
Given an integer array nums of unique elements, return all possible
subsets
 (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.



Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [0]
Output: [[],[0]]


Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.
 */
/*
Time: O(n * 2^n)
Space: O(n/2 * 2^n) ~= O(n * 2^n)

The recursive function is called 2^n times. Because we have 2 choices at each iteration in nums array. Either we include nums[i] in the current set, or we exclude nums[i]. This array nums is of size n = number of elements in nums.
We need to create a copy of the current set because we reuse the original one to build all the valid subsets.
This copy costs O(n) and it is performed at each call of the recursive function, which is called 2^n times . So total time complexity is O(n x 2^n).


For output: we're creating 2^n subsets where the average set size is n/2 (for each A[i], half of the subsets will include A[i], half won't) = n/2 * 2^n = O(n * 2^n).

 */
package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class SubSets {
    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> list = new ArrayList<>();
        backTrack(list,new ArrayList<>(),nums,0);
        return list;

    }
    public void backTrack(List<List<Integer>> list, List<Integer> tempList,int[] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length ; i++){
            tempList.add(nums[i]);
            backTrack(list,tempList,nums,i+1);
            tempList.remove(tempList.size()-1); // goes one level above after finishing current
        }
    }
    public static void main(String[] args){
        SubSets ob = new SubSets();
        System.out.println(ob.subsets(new int[]{1,2,3}));
        System.out.println(ob.subsets(new int[]{0}));

    }

}
