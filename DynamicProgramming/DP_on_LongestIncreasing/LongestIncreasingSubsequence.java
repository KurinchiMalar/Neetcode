package DynamicProgramming.DP_on_LongestIncreasing;

/*
https://leetcode.com/problems/longest-increasing-subsequence/description/
 */
/*
Given an integer array nums, return the length of the longest strictly increasing
subsequence
.



Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1


Constraints:

1 <= nums.length <= 2500
-104 <= nums[i] <= 104

 */

import java.util.Arrays;

/*
Idea / Recurrence :
    you need two variables part of precurrence (ind, prevInd)

    1) not pick = 0 + f(ind+1,prevInd) --> prevInd stays the same
    2) pick = 1 + f(ind+1,ind)   --> current ind becomes prev for next
            eligibility to pick
                prev == -1 || ar[ind] > ar[prevInd]

     3) maxLen = Math.max(maxLen, max(pick,notPick))

    Base Case : Boundary check

 */
public class LongestIncreasingSubsequence {


    /*
    TC : O(2 ^ n)
    SC : O(n)
     */
    public int lengthOfLISActual(int i, int prev, int n,int[] nums){
        if(i == n) return 0;

        int notPick = lengthOfLISActual(i+1,prev,n,nums);
        int pick = 0;
        if(prev == -1 || nums[i] > nums[prev]){
            pick = 1 + lengthOfLISActual(i+1,i,n,nums);
        }
        return Math.max(pick,notPick);
    }
    public int lengthOfLIS_Recursion(int[] nums) {
        return lengthOfLISActual(0,-1,nums.length,nums);
    }
    //*******************************************************************************************
    /*
    TC : O(N * N)
    SC : O(N * N) + O(N)
    My submission : https://leetcode.com/problems/longest-increasing-subsequence/submissions/1273760218/
    https://takeuforward.org/data-structure/longest-increasing-subsequence-dp-41/
     */

    public int lengthOfLIS_TDActual(int i , int prev, int n,int[] nums,int[][] dp){
        if(i == n) return 0;

        if(dp[i][prev+1] != -1)return dp[i][prev+1];

        int notPick = lengthOfLIS_TDActual(i+1,prev,n,nums,dp);
        int pick = 0;
        if(prev+1 == 0 || nums[i] > nums[prev]){
            pick = 1 + lengthOfLIS_TDActual(i+1,i,n,nums,dp);
        }
        dp[i][prev+1] = Math.max(pick,notPick);
        return dp[i][prev+1];
    }


    public int lengthOfLIS_TD(int[] nums) {
        int n =  nums.length;
        int[][] dp = new int[n][n+1];
        for(int[] ar: dp)Arrays.fill(ar,-1);
        return lengthOfLIS_TDActual(0,-1,nums.length,nums,dp);
    }


    /***************************************************************************************************************/

    /*
    TC : O(n *n)
    SC : O(n)
     */
    public int lengthOfLIS_BU(int[] nums) {
        int n =  nums.length;
        if (nums == null || n== 0) {
            return 0;
        }
        int[] dp = new int[n];
        // Initialize all dp values as 1 since the minimum length of LIS is 1
        Arrays.fill(dp,1);


        for(int i = 1 ; i < n; i++){
            for(int prev = 0 ; prev < i ; prev++){
                // Check if the current element is greater than the previous element

                if(nums[i] > nums[prev]){
                    // Update the dp value for the current element

                    dp[i] = Math.max(dp[i],dp[prev]+1);
                 }
            }
        }
        int maxLength = 0;
        for (int length : dp) {
            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }
    public static void main(String[] args) {
        LongestIncreasingSubsequence ob = new LongestIncreasingSubsequence();
        System.out.println("******************** Recursion  **************************");
        System.out.println(ob.lengthOfLIS_Recursion(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println(ob.lengthOfLIS_Recursion(new int[]{0,1,0,3,2,3}));
        System.out.println(ob.lengthOfLIS_Recursion(new int[]{7,7,7,7,7,7,7}));
        System.out.println("******************** TopDown  **************************");
        System.out.println(ob.lengthOfLIS_TD(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println(ob.lengthOfLIS_TD(new int[]{0,1,0,3,2,3}));
        System.out.println(ob.lengthOfLIS_TD(new int[]{7,7,7,7,7,7,7}));
        System.out.println("******************** Bottomup  **************************");
        System.out.println(ob.lengthOfLIS_BU(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println(ob.lengthOfLIS_BU(new int[]{0,1,0,3,2,3}));
        System.out.println(ob.lengthOfLIS_BU(new int[]{7,7,7,7,7,7,7}));
    }
}
