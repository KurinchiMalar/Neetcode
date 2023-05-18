/*
Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.



Example 1:

Input: nums = [1,1,2]
Output:
[[1,1,2],
 [1,2,1],
 [2,1,1]]
Example 2:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]


Constraints:

1 <= nums.length <= 8
-10 <= nums[i] <= 10
 */
/*
TC : O(n!/(#ofduplicates)!)     AAB --> 3!/2! = (3*2*1)/(2*1) = 3
SC :
 */
package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsII {

    public List<List<Integer>> permutationsII(int[] nums){

        List<List<Integer>> list = new ArrayList<>();

        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(list,new ArrayList<>(),nums,used);
        return list;

    }
    public void backtrack(List<List<Integer>> list, List<Integer> tempList,int[] nums, boolean[] used){

        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        }
        for(int i=0; i < nums.length; i++){

            //leave current elem if it is already used (or) if the prev element is same as current and prev is not yet used.
            /*
            The difficulty is to handle the duplicates.
            With inputs as [1a, 1b, 2a],
            If we don't handle the duplicates, the results would be: [1a, 1b, 2a], [1b, 1a, 2a]...,
            so we must make sure 1a goes before 1b to avoid duplicates
            By using nums[i-1]==nums[i] && !used[i-1], we can make sure that 1b cannot be choosed before 1a
             */
            if(used[i] || (i > 0 && nums[i] == nums[i-1] && !used[i-1])){
                continue;
            }
            tempList.add(nums[i]);
            used[i]=true;
            backtrack(list,tempList,nums,used);
            used[i]=false; //reset
            tempList.remove(tempList.size()-1);
        }

    }
    public static void main(String[] args){
        PermutationsII ob = new PermutationsII();
        System.out.println(ob.permutationsII(new int[]{1,1,2}));
        System.out.println(ob.permutationsII(new int[]{1,2,3}));


    }
}
