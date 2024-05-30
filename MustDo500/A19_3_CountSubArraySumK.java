package MustDo500;

import java.util.HashMap;

/*
https://leetcode.com/problems/subarray-sum-equals-k/

Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

A subarray is a contiguous non-empty sequence of elements within an array.



Example 1:

Input: nums = [1,1,1], k = 2
Output: 2
Example 2:

Input: nums = [1,2,3], k = 3
Output: 2

 */
/*
https://takeuforward.org/arrays/count-subarray-sum-equals-k/

 */
public class A19_3_CountSubArraySumK {

    /*
    TC : O( n ^ 2)
    SC : O(1)
     */

    public int subarraySum_BruteForce(int[] nums, int k) {

        int n = nums.length;
        int count = 0;

        for(int i = 0 ; i < n; i++){
            int curSum = 0;
            for(int j = i ; j < n; j++){
                curSum += nums[j];
                if(curSum == k){
                    count++;
                }
            }
        }
        return count;
    }

    /*
    Idea : Maintain a hashmap of [sumSoFar, count (# of occurences of this sum)]

          To identify if a  window of sum X has a subArray of sum k, we can check if the map already contains sum (X-k), If yes then it means we do have a subarray previously with sum k, so increment count.

     */
    /*
    TC : O(n)
    SC : O(n)
     */
    public int subarraySum_PrefixSumHashing(int[] nums, int k) {

        int n = nums.length;
        HashMap<Integer,Integer> hmap = new HashMap<>(); // PrefixSum , count

        int count = 0;
        int preFixSum = 0;
        //initialize map (0  --> 1 ) prefixSum 0 = 1 time
        hmap.put(0,1);

        for(int i = 0 ; i < n; i++){
            preFixSum += nums[i];

            if(hmap.containsKey(preFixSum-k)){
                count += hmap.get(preFixSum-k);
            }
            hmap.put(preFixSum,hmap.getOrDefault(preFixSum,0)+1);
        }
        return count;
    }

    public static void main(String[] args) {
        A19_3_CountSubArraySumK ob = new A19_3_CountSubArraySumK();
        System.out.println("*********************** Brute Force *****************************");
        System.out.println(ob.subarraySum_BruteForce(new int[]{3,1,2,4},6));
        System.out.println(ob.subarraySum_BruteForce(new int[]{1,2,3},3));
        System.out.println(ob.subarraySum_BruteForce(new int[]{1,1,1},2));
        System.out.println(ob.subarraySum_BruteForce(new int[]{0,0,0},0));
        System.out.println(ob.subarraySum_BruteForce(new int[]{-1,-1,1},0));
        System.out.println(ob.subarraySum_BruteForce(new int[]{0},0));
        System.out.println("*********************** Using Prefix Si=um / HashMap *****************************");
        System.out.println(ob.subarraySum_PrefixSumHashing(new int[]{3,1,2,4},6));
        System.out.println(ob.subarraySum_PrefixSumHashing(new int[]{1,2,3},3));
        System.out.println(ob.subarraySum_PrefixSumHashing(new int[]{1,1,1},2));
        System.out.println(ob.subarraySum_PrefixSumHashing(new int[]{0,0,0},0));
        System.out.println(ob.subarraySum_PrefixSumHashing(new int[]{-1,-1,1},0));
        System.out.println(ob.subarraySum_PrefixSumHashing(new int[]{0},0));

    }
}
