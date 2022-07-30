/*
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.



Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation:
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.
Example 2:

Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.
Example 3:

Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.


Constraints:

3 <= nums.length <= 3000
-105 <= nums[i] <= 105
 */
/*
https://leetcode.com/problems/3sum/submissions/
Time Complexity : O(nlogn) + O(n*n) = O(n*n)
Space Complexity : O(1)
 */
import java.util.*;
class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        int numsLen = nums.length;
        Arrays.sort(nums); // O(nlogn)
        Set<List<Integer>> resultSet = new HashSet<>();
        List<List<Integer>> resultList = new ArrayList<>();
        for(int i=0; i < numsLen;i++){
            int head = i+1;
            int tail = numsLen-1;
            while(head < tail){
                int sum = nums[i]+nums[head]+nums[tail];
                if(sum == 0){
                    resultSet.add(Arrays.asList(nums[i],nums[head],nums[tail]));
                    head++;
                    tail--;
                }else if(sum < 0){
                    head++;
                }else{
                    tail--;
                }
            }
        }
        resultList.addAll(resultSet);
        return resultList;
    }

    public static void main(String[] args){
        System.out.println(""+threeSum(new int[]{-1,0,1,2,-1,4}));
        System.out.println(""+threeSum(new int[]{0,0,0,0}));
        System.out.println(""+threeSum(new int[]{0,1,1}));

    }
}
