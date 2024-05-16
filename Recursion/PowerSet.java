package Recursion;
/*
https://leetcode.com/problems/subsets/description/

https://takeuforward.org/data-structure/power-set-print-all-the-possible-subsequences-of-the-string/
https://www.youtube.com/watch?v=b7AYbpM5YrE

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Bit Manipulation :
        Pre-Requisites to remember :
            1)    2 pow(n)  ---> can be represented as  1 << n
            2)    n & ( 1 << i)  ---> condition to check if ith bit is set , != 0 implies bit is set

 */
public class PowerSet {

    /*
    My submission : https://leetcode.com/problems/subsets/submissions/1259183169/
    TC : O( 2 pow n   * n) // O(2^n) for the outer for loop and O(n) for the inner for loop.
    SC : O(1) .... for result list = O( 2 pow n)
     */
    public List<List<Integer>> powerSet_BitManipulation(int[] nums) {

        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); // empty
        for(int num = 0; num < (1 << n); num++ ){ // Until 2 pow n -1 combinations
            List<Integer> temp = new ArrayList<>();
            // For every num check if ith bit is set.
            for(int i = 0 ; i < n; i++ ){
                if((num & (1 << i)) != 0){ // bit is set
                    temp.add(nums[i]);
                }
            }
            if (temp.size() > 0) {
                result.add(temp);
            }
        }
        return result;
    }

    public ArrayList<String> powerSet_BitManipulation(String s) {

        int n = s.length();
        ArrayList<String> result = new ArrayList<>();
       //result.add("");

        for(int num = 0 ; num < Math.pow(2,n); num++){ // equivalent to        for (int num = 0; num < (1 << n); num++) {

            String temp = "";
            for(int i = 0 ; i < n; i++){
                if((num & (1 << i)) != 0){
                    temp += s.charAt(i);
                }
            }
            if (temp.length() > 0) {
                result.add(temp);
            }

        }

        return result;
    }
    /***********************************************************************************************************************/

    /*
    TC : O( 2 pow n)
    SC : O(n)
     */
    public void helper(int i, int n, String curr, String s, ArrayList<String> result){

        if(i == n){
            if(curr.length() > 0)result.add(curr);
            return;
        }
        helper(i+1,n,curr+s.charAt(i),s,result); // pick
        helper(i+1,n,curr,s,result); // not pick
    }

    public ArrayList<String> powerSet_Recursion(String s) {
        ArrayList<String> result = new ArrayList<>();
        int n = s.length();
        helper(0,n,"",s,result);
        return result;
    }


    public static void main(String[] args) {
        PowerSet ob = new PowerSet();
        System.out.println("*************** Bit Manipulation *******************");
        System.out.println(ob.powerSet_BitManipulation(new int[]{1,2,3}));
        System.out.println(ob.powerSet_BitManipulation("abc"));

        System.out.println("*************** Recursion *******************");
        System.out.println(ob.powerSet_Recursion("abc"));


    }
}
