package DynamicProgramming;

import java.util.Arrays;

/*

Count subsets with sum K
https://www.codingninjas.com/codestudio/problems/number-of-subsets_3952532?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos


https://www.youtube.com/watch?v=ZHyb-A2Mte4
https://takeuforward.org/data-structure/count-subsets-with-sum-k-dp-17/
DP 17. Counts Subsets with Sum K
 */
public class SubSetSumEqualsKCount {

    /*
    TC : O(2 pow n)
    SC : O(n)  // recursive stack
     */
    public int helper(int i, int target, int n, int[] nums){

        if(i < 0 || i >= n) return 0;
        if(i == 0){
            return ((nums[i] == target) ? 1 : 0);
        }

        if(target == 0) return 1;

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
        if(target == 0){
            //return 1;
            dp[i][target] = 1;
            return dp[i][target];
        }
        if(dp[i][target] != -1) return dp[i][target];
        if(i == 0){
            //dp[i][target] = (nums[i] == target) ? 1:0;
            return (nums[i] == target) ? 1:0;
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

        for(int ind=0; ind < N ; ind++){
            dp[ind][0] = 1;
        }
        //if(nums[0] <= k){
            dp[0][nums[0]] = 1;
        //}

        for(int i = 1; i < N; i++){
            for(int t = 1; t <= k; t++){
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


        public static void main(String[] args) {
        SubSetSumEqualsKCount ob = new SubSetSumEqualsKCount();
        System.out.println(ob.subSetSum(new int[]{1,2,3},3));
        System.out.println(ob.subSetSum(new int[]{1,1,1},2));
        System.out.println(ob.subSetSum(new int[]{1,2,1,2,1},3));
        System.out.println("***************** Top Down ********************************");
        System.out.println(ob.subSetSum_TD(new int[]{1,2,3},3));
        System.out.println(ob.subSetSum_TD(new int[]{1,1,1},2));
        System.out.println(ob.subSetSum_TD(new int[]{1,2,1,2,1},3));
        System.out.println("*******************  Bottom Up ******************************");
        System.out.println(ob.subSetSum_BU(new int[]{1,2,3},3));
        System.out.println(ob.subSetSum_BU(new int[]{1,1,1},2));
        System.out.println(ob.subSetSum_BU(new int[]{1,2,1,2,1},3));

        }
}
