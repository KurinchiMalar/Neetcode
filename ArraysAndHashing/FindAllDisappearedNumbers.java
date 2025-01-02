package ArraysAndHashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.



Example 1:

Input: nums = [4,3,2,7,8,2,3,1]
Output: [5,6]
Example 2:

Input: nums = [1,1]
Output: [2]


Constraints:

n == nums.length
1 <= n <= 105
1 <= nums[i] <= n


Follow up: Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 */
public class FindAllDisappearedNumbers {

    /*
    TC : O(n)
    SC : O(n)
     */

    public List<Integer> findDisappearedNumbers(int[] nums) {

        int n = nums.length;
        List<Integer> disappList = new ArrayList<>();
        if(n == 1 && nums[0] != 1 ) return List.of(1);

        int[] arr = new int[n+1]; // extra space
        Arrays.fill(arr,0);
        for(int i : nums){
            arr[i]++;
        }
        for(int i = 1 ; i < n+1; i++){
            if(arr[i] == 0)disappList.add(i);
        }
        return disappList;
    }
    /*
    TC : O(n)
    SC : O(1)

    Logic : In the same array ,got to the index position of a number and negate it. If already negated ignore.
            Make one more pass and see who are all positive, (No one as attempted to come to this index and make it negative)
            So this index+1 is the result(Since array starts from 0)
     */
    public List<Integer> findDisappearedNumbersEfficient(int[] nums) {
        int n = nums.length;
        List<Integer> disappList = new ArrayList<>();
        if(n == 1 && nums[0] != 1) return List.of(1);

        for(int x : nums){
            x = Math.abs(x);

            if(nums[x-1] > 0){ // if already negated skip
                nums[x-1] = -nums[x-1];
            }
        }

        for(int i = 0 ; i < n; i++){
            if(nums[i] > 0){
                disappList.add(i+1);
            }
        }
        return disappList;
    }

    public static void main(String[] args) {
        FindAllDisappearedNumbers ob = new FindAllDisappearedNumbers();
        System.out.println(ob.findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1}));
        System.out.println(ob.findDisappearedNumbers(new int[]{1,1}));
        System.out.println("****************** Efficient O(n) ********************");
        System.out.println(ob.findDisappearedNumbersEfficient(new int[]{4,3,2,7,8,2,3,1}));
        System.out.println(ob.findDisappearedNumbersEfficient(new int[]{1,1}));
    }
}
