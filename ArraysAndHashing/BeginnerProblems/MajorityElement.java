package ArraysAndHashing.BeginnerProblems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*

https://leetcode.com/problems/majority-element/description/

Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.



Example 1:

Input: nums = [3,2,3]
Output: 3
Example 2:

Input: nums = [2,2,1,1,1,2,2]
Output: 2


Constraints:

n == nums.length
1 <= n <= 5 * 104
-109 <= nums[i] <= 109


Follow-up: Could you solve the problem in linear time and in O(1) space?
 */
public class MajorityElement {

    /*
    TC : O(n)
    SC : O(n/2)
     */
    public static int majorityElementUsingExtraSpace(int[] nums) {

        Map<Integer,Integer> freqMap = new HashMap<Integer,Integer>();
        int n = nums.length;

        if(n == 1)return nums[0];

        for(int num: nums){
            if(!freqMap.containsKey(num)){
                freqMap.put(num,1);
                continue;
            }
            int count = freqMap.get(num);
            freqMap.put(num,count +1);
            if(count >= n / 2)return num;
        }
        return -1;
    }
    /*
    TC : O(n logn)
    SC : O(1)
     */
    public static int majorityElementSorting(int[] nums) {
        int n = nums.length;

        if(n == 1)return nums[0];
        Arrays.sort(nums);
        return nums[n/2];
    }


    /*
    Moore's Voting Algorithm
    https://leetcode.com/problems/majority-element/solutions/3676530/3-method-s-beats-100-c-java-python-beginner-friendly/
    TC : O(n)
    SC : O(1)
     */
    public static int majorityElementEfficient(int[] nums){

        int n = nums.length;
        if(n == 1)return nums[0];

        int candidate = nums[0];
        int count = 1;

        for(int i = 1 ; i < n; i++){


            count = (nums[i] == candidate) ? ++count:--count;

            if(count > n/2) return candidate;
            if(count == 0){
                candidate = nums[i]; // change the candidate
                count = 1;
            }


        }
        return candidate;
    }

    public static void main(String[] args) {
        System.out.println("Using ExtraSpace");
        System.out.println(majorityElementUsingExtraSpace(new int[]{3,2,3}));
        System.out.println(majorityElementUsingExtraSpace(new int[]{2,2,1,1,1,2,2}));

        System.out.println("Sorting");
        System.out.println(majorityElementSorting(new int[]{3,2,3}));
        System.out.println(majorityElementSorting(new int[]{2,2,1,1,1,2,2}));

        System.out.println("Without Extra Space...... Efficient");
        System.out.println(majorityElementEfficient(new int[]{3,2,3}));
        System.out.println(majorityElementEfficient(new int[]{2,2,1,1,1,2,2}));
        System.out.println(majorityElementEfficient(new int[]{6,5,5}));
        System.out.println(majorityElementEfficient(new int[]{8,8,7,7,7}));
        System.out.println(majorityElementEfficient(new int[]{8}));



    }
}
