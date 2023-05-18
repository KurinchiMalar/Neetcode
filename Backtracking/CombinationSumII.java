/*
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.



Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8
Output:
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5
Output:
[
[1,2,2],
[5]
]


Constraints:

1 <= candidates.length <= 100
1 <= candidates[i] <= 50
1 <= target <= 30
 */
package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
    public List<List<Integer>> combinationsII(int[] candidates, int target){
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(candidates); // you need this because you need to discard the duplicates.
        backtrack(list,new ArrayList<>(),target,0,candidates);
        return list;
    }
    public void backtrack(List<List<Integer>> list, List<Integer> tempList, int remain, int start , int[] candidates){
        if(remain < 0)return;

        if(remain == 0){
            list.add(new ArrayList<>(tempList));
        }
        for(int i=start ; i < candidates.length; i++){
            if(i > start && candidates[i] == candidates[i-1]){ // we need to skip the duplicates, only unique elements reqd in combinationII
                continue;
            }
            tempList.add(candidates[i]);
            backtrack(list,tempList,remain-candidates[i],i+1,candidates); // start = i+1 since you can use current element only once, no reuse allowed.
            tempList.remove(tempList.size()-1);
        }
    }

    public static void main(String[] args){
        CombinationSumII ob = new CombinationSumII();
        System.out.println(ob.combinationsII(new int[]{10,1,2,7,6,1,5},8));
        System.out.println(ob.combinationsII(new int[]{2,5,2,1,2},5));

    }
}
