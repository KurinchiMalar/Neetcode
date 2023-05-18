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
https://medium.com/@vasanths294/permutation-combination-subset-time-complexity-eca924e00071
Combination for given [1,2,3,4], k = 2
  [2,4]
  [3,4]
  [2,3]
  [1,2]
  [1,3]
  [1,4]
Time complexity
N! / (N−k)! k!
for example given N = 4, k = 2
given 2 spots and 4 numbers
_ _
first dash - any one of the 4 numbers so, 4chances
second dash - any of the 3remaining numbers so, 3chances
             ( one number already chosen for first dash )
4*3 = 12 = (4*3*2*1)/(2*1) = N!/(N-K)!
but there are repetitions in this way (1,2) and (2,1) are counted.
(Eliminate repetitions of k numbers)
so to eliminate repetitions we need to find out the number of ways in which k can be arranged
for eg k = 2
_ _ ( 2*1 = 2! ) which is nothing but permuation of K = K!
To Eliminate this ( N!/(N-K)! ) / K!
N! / (N−k)! k!
 */

package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    public List<List<Integer>> combinations(int[] candidates, int target){

        List<List<Integer>> list = new ArrayList<>();
        backtrack(list,new ArrayList<>(),target,0,candidates);
        return list;
    }
    public void backtrack(List<List<Integer>> list, List<Integer> tempList, int remain, int start , int[] candidates){
        if(remain < 0)return; // invalid combination

        if(remain == 0 ){ // chosen numbers have summed up to target
            list.add(new ArrayList<>(tempList));
        }
        /*
        https://leetcode.com/problems/combination-sum/solutions/1777569/full-explanation-with-state-space-tree-recursion-and-backtracking-well-explained-c/
        As you might have noticed that we are not considering the whole array as possible options at every level, because we want unique combinations.
e.g. at the node [2, 2, 3], the possible options for the next level are only [3, 5]. Why?? because if we consider the whole array as possible options,
then we we will end up with [2, 2, 3, 2] (with 2 as a possible option), which has already been checked as [2, 2, 2, 3] (see the tree).
So to make the solution unique we are only considering the part of the array from current last element to the end element (like in this example).
         */
        for(int i = start ; i < candidates.length ; i++){
            tempList.add(candidates[i]);
            backtrack(list, tempList, remain-candidates[i], i, candidates); // not i+1 because we can reuse elements...eg) 2 can be used any number of times to arrive at given target
            tempList.remove(tempList.size()-1);
        }
    }
    public static void main(String[] args){
        CombinationSum ob = new CombinationSum();
        System.out.println(ob.combinations(new int[]{2,3,6,7},7));
        System.out.println(ob.combinations(new int[]{2,3,5},8));
        System.out.println(ob.combinations(new int[]{2},1));
    }
}
