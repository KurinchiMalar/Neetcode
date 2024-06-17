package MustDo500;
import java.util.ArrayList;
import java.util.List;
/*
https://leetcode.com/problems/permutations/description/
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
public class A31_Permutation {

    public void backTrack(int[] nums, List<Integer> curList, List<List<Integer>> result, int n, boolean[] visited){

        // when curListSize reached n --> we have got a permutation
        if(curList.size() == n){
            result.add(new ArrayList<>(curList));
            return;
        }
        // for each index start from 0 to pick every element before it as well.
        for(int i = 0; i < n; i++){ //-------------------------------------------------------------> O(n)

            // pick only if not visited already
            if(!visited[i]){
                curList.add(nums[i]);
                visited[i] = true;
                backTrack(nums,curList,result,n,visited);    //-------------------------------------> O(n!)
                curList.remove(curList.size()-1); // remove last element
                visited[i] = false;
            }
        }
    }
    /*
    With Extra Space, Using a boolean visited map
    For n ---> number of permutations = n!
    My submission : https://leetcode.com/problems/permutations/submissions/1290800408/
    TC : O(n * n!)
    SC : O(n) stack + O(n) (freqmap)
     */

    public List<List<Integer>> permute_UsingExtraSpace(int[] nums) {
        int n = nums.length;

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> curList = new ArrayList<>();

        boolean[] visited = new boolean[n]; // initially all are false

        backTrack(nums,curList,result,n,visited);
        return result;

    }
    //*****************************************************************************************************************************
    /*
    My Submission : https://leetcode.com/problems/permutations/submissions/1290918725/
    TC : O( n * n!)
    SC: O(n) (stack)
            result list = O(n!) this is generally not included in space complexity calculation
     */
    public void swap(int[] nums,int x, int y){
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    public void backTrack1(int ind, int[] nums, List<Integer> curList, List<List<Integer>> result, int n){
        if(ind == n){
            if(!curList.isEmpty()){
                result.add(new ArrayList<>(curList));
            }
            return;
        }
        for(int i = ind; i < n; i++){  //-------------------------------------------------------------> O(n)
            swap(nums,ind,i);
            curList.add(nums[ind]);
            backTrack1(ind+1,nums,curList,result,n);  // n! permutations
            //unwind
            curList.remove(curList.size()-1);
            swap(nums,ind,i);
        }
    }


    public List<List<Integer>> permute(int[] nums) {
        int n = nums.length;

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> curList = new ArrayList<>();
        backTrack1(0,nums,curList,result,n);
        return result;
    }




        public static void main(String[] args) {
        A31_Permutation ob = new A31_Permutation();
        System.out.println("**************** Using visited map - extra space ****************");
        System.out.println(ob.permute_UsingExtraSpace(new int[]{1,2,3}));
        System.out.println(ob.permute_UsingExtraSpace(new int[]{0,1}));
        System.out.println(ob.permute_UsingExtraSpace(new int[]{1}));
        System.out.println("**************** Without Extra space - Swapping technique ****************");
        System.out.println(ob.permute(new int[]{1,2,3}));
        System.out.println(ob.permute(new int[]{0,1}));
        System.out.println(ob.permute(new int[]{1}));

    }

}
