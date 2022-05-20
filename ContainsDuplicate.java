/*
Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
Example 1:
Input: nums = [1,2,3,1]
Output: true
Example 2:
Input: nums = [1,2,3,4]
Output: false
Example 3:
Input: nums = [1,1,1,3,3,4,3,2,4,2]
Output: true
Constraints:
1 <= nums.length <= 105
-109 <= nums[i] <= 109
 */
/*
Time Complexity : O(n)
Space Complexity : O(n)
https://leetcode.com/problems/contains-duplicate/submissions/
https://github.com/KurinchiMalar/Neetcode/blob/Arrays/ContainsDuplicate.java
 */


import java.util.*;
class ContainsDuplicate {
    public boolean containsDuplicateWithCount(int[] nums) {
        if(nums.length == 0){
            return false;
        }

        Hashtable<Integer,Integer> ht = new Hashtable<>();
        for(int num:nums){
            if(ht.contains(num) && ht.get(num) >= 1){
                return true;
            }else{
                ht.put(num,1);
            }
        }
        return false;
    }
    //Neetcode Solution using HashSet..you can avoid maintaining count
    public static boolean containsDuplicateUsingHashSet(int[] nums) {
        if(nums.length == 0){
            return false;
        }

        HashSet<Integer> hset = new HashSet<>();

        for(int num:nums){
            if(hset.contains(num)){
                return true;

            }else{
                hset.add(num);
            }
        }
        return false;
    }
    public static void main(String[] args){
        System.out.println("1,2,1 :"+containsDuplicateUsingHashSet(new int[]{1,2,1}));
        System.out.println("1,2 :"+containsDuplicateUsingHashSet(new int[]{1,2}));
        System.out.println("1,2,3,1 :"+containsDuplicateUsingHashSet(new int[]{1,2,3,1}));

    }
}


