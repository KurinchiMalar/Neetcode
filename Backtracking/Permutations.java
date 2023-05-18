/*
Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.



Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:

Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:

Input: nums = [1]
Output: [[1]]


Constraints:

1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.
 */
/*
TC : O(n!)
SC :

Permutation for given [1,2,3]
[1,2,3]
[1,3,2]
[2,1,3]
[2,3,1]
[3,1,2]
[3,2,1]
Time complexity
N!
for example given N = 5
intially  _ _ _ _ _
first dash - any one of the 5 numbers so, 5 chances
second dash - any of the 4 remaining numbers so, 4 chances
             ( one number already chosen for first dash )
...
so 5x4x3x2x1 = 120 ( 5! )
 */
package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    public List<List<Integer>> permutations(int[] nums){

        List<List<Integer>> list = new ArrayList<>();
        backtrack(list,new ArrayList<>(),nums);
        return list;
    }
    public void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums){

        // add to list from templist only if tempList has all elems from nums
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        }

        for(int i = 0 ; i < nums.length; i++){

            if(tempList.contains(nums[i])){ // the number is already in templist so skip it.
                continue;
            }
            tempList.add(nums[i]);
            backtrack(list,tempList,nums);
            tempList.remove(tempList.size()-1);
        }

    }
    public static void main(String[] args){
        Permutations ob = new Permutations();
        System.out.println(ob.permutations(new int[]{1,2,3}));
        System.out.println(ob.permutations(new int[]{0,1}));
        System.out.println(ob.permutations(new int[]{1}));

    }
}
