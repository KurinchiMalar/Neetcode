package DynamicProgramming.DP_on_Subsequences;

import java.util.Arrays;

/*
Given an array arr[] of length N and an integer X, the task is to find the number of subsets with a sum equal to X.

Input: arr[] = {1, 2, 3, 3}, X = 6
Output: 3
All the possible subsets are {1, 2, 3},
{1, 2, 3} and {3, 3}

Input: arr[] = {1, 1, 1, 1}, X = 1
Output: 4

Count subsets with sum K
https://www.codingninjas.com/codestudio/problems/number-of-subsets_3952532?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos


https://www.youtube.com/watch?v=ZHyb-A2Mte4
https://takeuforward.org/data-structure/count-subsets-with-sum-k-dp-17/
DP 17. Counts Subsets with Sum K

Constraints given : 1 <=  T  <= 10
                    1 <=  N <= 100
                    1 <= nums[i] <= 1000

    Tweak to above constraint .....  0<= nums[i] <= 1000 , nums[i] can be 0 as well how will you modify below solution

 */
public class SubSetSumEqualsKCount {

    /*
    TC : O(2 pow n)
    SC : O(n)  // recursive stack
     */
    public int helper(int i, int target, int n, int[] nums){

        if(i < 0 || i >= n) return 0;
        if(i == 0){
            //return ((nums[i] == target) ? 1 : 0);   Does not work for testcase {0,0,1}
            if(nums[i] == 0 && target == 0)return 2; // take0 + notTake0  both doesn't alter the target sum. Hence 2 ways
            //if(nums[i] != 0 && target == 0) return 1; // 1 way dont take nums[i]
            //if(nums[i] == target) return 1; // 1 way take nums[i]
            //Clubbing the above 2
            if(target == 0 || target == nums[i]) return 1;
            return 0;
        }

        //if(target == 0) return 1;  --> Adding this wont work for case {0,0,1} , target = 1

        int notTake = helper(i-1,target,n, nums);
        int take = (nums[i] <= target) ? helper(i-1,target-nums[i],n, nums):0;
        return notTake+take;

    }
    public int subSetSum(int[] nums, int k) {

        return helper(nums.length-1,k,nums.length,nums);
    }

    /*****************************************************************************************/

    /*
    TC : O(n * k)
    SC : O(n * k) dp array + O(n)  // recursive stack
     */
    public int helper_TD(int i, int target, int n, int[] nums,int[][] dp){

        if(i < 0 || i >= n) return 0;
        /*if(target == 0){    Removing this for constraint when nums[i] can be 0
            //return 1;
            dp[i][target] = 1;
            return dp[i][target];
        }*/
        if(dp[i][target] != -1) return dp[i][target];
        if(i == 0){
            if(nums[i] == 0 && target == 0) return 2;
            if(target == 0 || nums[i] == target) return 1;
            return 0;
        }

        int notTake = helper_TD(i-1,target,n, nums,dp);
        int take = (nums[i] <= target) ? helper_TD(i-1,target-nums[i],n, nums,dp):0;
        dp[i][target] = notTake+take;
        return dp[i][target];
    }
    public int subSetSum_TD(int[] nums, int k) {
        int N = nums.length;
        int[][] dp = new int[N][k+1];
        for(int[] ar : dp) Arrays.fill(ar,-1); // mark unvisited
        return helper_TD(nums.length-1,k,nums.length,nums,dp);
    }

    /*****************************************************************************************/

    /*
    TC : O(n * k)
    SC : O(n * k) dp array
     */
    public int subSetSum_BU(int[] nums, int k) {
        int N = nums.length;

        int[][] dp = new int[N][k+1];

        /*for(int ind=0; ind < N ; ind++){  // BC 1    does not work if nums contain 0
            dp[ind][0] = 1;
        }*/

        // target == 0 and nums[0] == 0 return 2
        // target == 0 , nums[0] != 0  return 1
        dp[0][0] = (nums[0] == 0) ? 2 : 1;  // --------------------------> BC 1 handling 0 case as well


        /*// populate for i = 0
        //if(nums[0] <= k){
            dp[0][nums[0]] = 1;   // BC 2   nums[i] == target  // this is wrong when nums[0] = 0 ---> dp[0][0] = 1 (expected is 2) this will be overwritten

        // {0,0,1} test case not handled .
        //}*/

        if(nums[0] != 0 && nums[0] <= k){
            dp[0][nums[0]] = 1; // nums[0] = target case basically , // --------------------------> BC 2 handling 0 case as well
        }


        for(int i = 1; i < N; i++){
            for(int t = 0; t <= k; t++){
                int notTake = dp[i-1][t];
                int take = 0;
                if(nums[i] <= t){
                    take = dp[i-1][t-nums[i]];
                }
                dp[i][t] = take + notTake;
            }

        }
        return dp[N-1][k];
    }



    /*****************************************************************************************/
    /*
    TC : O(n * k)
    SC : O(k)
     */
    public int subSetSum_BU_SpaceOptimized(int[] nums, int k) {
        int N = nums.length;

        //int[][] dp = new int[N][k+1];
        int[] prev = new int[k+1];

        prev[0] = (nums[0] == 0) ? 2 : 1;  // --------------------------> BC 1 handling 0 case as well

        if(nums[0] != 0 && nums[0] <= k){
            prev[nums[0]] = 1; // nums[0] = target case basically , // --------------------------> BC 2 handling 0 case as well
        }


        for(int i = 1; i < N; i++){
            int[] cur = new int[k+1];
            for(int t = 0; t <= k; t++){
                int notTake = prev[t];
                int take = 0;
                if(nums[i] <= t){
                    take =prev[t-nums[i]];
                }
                cur[t] = take + notTake;
            }
            prev = cur;
        }
        return prev[k];
    }


    public static void main(String[] args) {
        SubSetSumEqualsKCount ob = new SubSetSumEqualsKCount();
        System.out.println(ob.subSetSum(new int[]{1,2,3},3));
        System.out.println(ob.subSetSum(new int[]{1,1,1},2));
        System.out.println(ob.subSetSum(new int[]{1,2,1,2,1},3));
        System.out.println(ob.subSetSum(new int[]{0,0,1},1));

        System.out.println("***************** Top Down ********************************");
        System.out.println(ob.subSetSum_TD(new int[]{1,2,3},3));
        System.out.println(ob.subSetSum_TD(new int[]{1,1,1},2));
        System.out.println(ob.subSetSum_TD(new int[]{1,2,1,2,1},3));
        System.out.println(ob.subSetSum_TD(new int[]{0,0,1},1));

        System.out.println("*******************  Bottom Up ******************************");
        System.out.println(ob.subSetSum_BU(new int[]{1,2,3},3));
        System.out.println(ob.subSetSum_BU(new int[]{1,1,1},2));
        System.out.println(ob.subSetSum_BU(new int[]{1,2,1,2,1},3));
        System.out.println(ob.subSetSum_BU(new int[]{0,0,1},1));

        System.out.println("*******************  Bottom Up Space Optimized ******************************");
        System.out.println(ob.subSetSum_BU_SpaceOptimized(new int[]{1,2,3},3));
        System.out.println(ob.subSetSum_BU_SpaceOptimized(new int[]{1,1,1},2));
        System.out.println(ob.subSetSum_BU_SpaceOptimized(new int[]{1,2,1,2,1},3));
        System.out.println(ob.subSetSum_BU_SpaceOptimized(new int[]{0,0,1},1));

    }
}
