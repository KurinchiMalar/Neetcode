package MustDo500;

import java.util.ArrayList;
import java.util.List;

public class A33_CombinationSum {

    /*
    Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the
frequency
 of at least one of the chosen numbers is different.

The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.



Example 1:

Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.
Example 2:

Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
Example 3:

Input: candidates = [2], target = 1
Output: []


Constraints:

1 <= candidates.length <= 30
2 <= candidates[i] <= 40
All elements of candidates are distinct.
1 <= target <= 40
     */

    /*
    My submission: https://leetcode.com/problems/combination-sum/submissions/1292964957/
    TC : O( (2 ^ n ) * k)   k is the avg length of each combination, n is the size of array
    SC : O( k * x)   x is the number of combinations
     */
    public void backTrack(int ind, int target,List<List<Integer>> result,List<Integer> curList, int[] candidates, int n){
        if(ind == n){
            if(target == 0 && !curList.isEmpty()){
                result.add(new ArrayList<>(curList));
            }
            return;
        }
        //pick
        if(candidates[ind] <= target){
            curList.add(candidates[ind]);
            backTrack(ind,target-candidates[ind],result,curList,candidates,n); // ind can be used any number of times, given in question
            //unwinding
            curList.remove(curList.size()-1);
        }
        backTrack(ind+1,target,result,curList,candidates,n);
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if(candidates == null || candidates.length == 0){
            return  new ArrayList<>();
        }
        int n = candidates.length;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> curList = new ArrayList<>();
        backTrack(0,target,result,curList,candidates,n);
        return result;
    }

    public static void main(String[] args) {
        A33_CombinationSum ob = new A33_CombinationSum();
        System.out.println(ob.combinationSum(new int[]{2,3,6,7},7));
        System.out.println(ob.combinationSum(new int[]{2,7,6,3},7));

        System.out.println(ob.combinationSum(new int[]{7},7));

        System.out.println(ob.combinationSum(new int[]{2,3,5},8));
        System.out.println(ob.combinationSum(new int[]{2},1));

    }
}