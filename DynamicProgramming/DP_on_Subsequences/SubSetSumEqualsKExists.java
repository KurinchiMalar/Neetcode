package DynamicProgramming.DP_on_Subsequences;

import java.util.Arrays;

/*

Problem statement
You are given an array/list ‘ARR’ of ‘N’ positive integers and an integer ‘K’. Your task is to check if there exists a subset in ‘ARR’ with a sum equal to ‘K’.

Note: Return true if there exists a subset with sum equal to ‘K’. Otherwise, return false.

For Example :
If ‘ARR’ is {1,2,3,4} and ‘K’ = 4, then there exists 2 subsets with sum = 4. These are {1,3} and {4}. Hence, return true.

1 <= N <= 10^3
0 <= ARR[i] <= 10^9
0 <= K <= 10^3


Is Subset with sum K exists --> return true else false
https://takeuforward.org/data-structure/subset-sum-equal-to-target-dp-14/
https://www.youtube.com/watch?v=fWX9xDmIzRI&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=15
DP 14. Subset Sum Equals to Target | Identify DP on Subsequences and Ways to Solve them
 */
public class SubSetSumEqualsKExists {

    public boolean iSExistsSubset(int i, int target,int[] nums){
        if(i < 0 || i >= nums.length){
            return false;
        }
        if(i == 0){
            return nums[0] == target;
        }
        if(target == 0){
            return true;
        }
        boolean notTake = iSExistsSubset(i-1,target,nums);
        boolean take = false;
        if(target >= nums[i]){ // take eligibility check
            take = iSExistsSubset(i-1,target-nums[i],nums);

        }
        return notTake || take;
    }

    public boolean isExistsSubsetWithSumK_Recursion(int[] nums,int target){
        return iSExistsSubset(nums.length-1,target,nums);
    }
    /*********************************************************************************************************************/
    /*
    2  varying params
    i and target

    i --> 0 to N-1
    target --> 0 to target
    so we need a dp array [N+1][target+1] ...because we need until dp[N][target]

    int[][] dp for memoization.
        -1 for unvisited
        1 for true
        0 for false
     */
    /*
    TC : O(N * target)
    SC : O(N * target) + O(N) stack space
     */
    private boolean iSExistsSubset_TD(int i, int target, int[] nums, int[][] dp) {

        if( i < 0 || i >= nums.length) return false; // boundary

        if(dp[i][target] != -1) {
            return dp[i][target] == 1;  // avoids unnecessary recursion
        }

        if(target == 0){ // found subset    BaseCase 1
            dp[i][target] = 1;
            return true;
        }

        if(i == 0){ // single element      BaseCase 2
            dp[i][target] = (nums[0] == target) ? 1 : 0;
            return nums[0] == target;
        }

        //Recurrence
        boolean notTake = iSExistsSubset_TD(i-1,target,nums,dp);
        boolean take = target >= nums[i] && iSExistsSubset_TD(i - 1, target - nums[i], nums, dp);

        dp[i][target] = (notTake || take) ? 1 : 0 ;
        return notTake || take;
    }
    public boolean isExistsSubsetWithSumK_topDownMemoization(int[] nums,int target){
        int[][] dp = new int[nums.length][target+1];
        for(int[] ar: dp){
            Arrays.fill(ar,-1);
        }
        return iSExistsSubset_TD(nums.length-1,target,nums,dp);
    }

    /*********************************************************************************************************************/
    /*
    TC : O(N * target)
    SC : O(N * target)
     */
    private boolean iSExistsSubset_BU(int[] nums,int target){

        int N = nums.length;
        boolean[][] dp = new boolean[N][target+1];

        //Initialize first column of dp table
        // if target = 0  return true  (//Recurrence) Base Case 1
        for(int ind = 0 ; ind < N; ind++){
            dp[ind][0] = true;
        }

        //Initialize first row of dp table
        // if index 0 and target == nums[i] return true Base Case 2
        // nums[0] > target --> not applicable that should be handled
        if(nums[0] <= target){
            dp[0][nums[0]] = true;
        }

        for(int i = 1; i < N ; i++){
            for(int t = 1 ; t <= target; t++){  // t = 0 already completed base Case 1
                boolean notTake = dp[i-1][t];
                boolean take = false;
                if(nums[i] <= t){
                    take = dp[i-1][t-nums[i]];
                }
                dp[i][t] = take || notTake;
            }
        }


        return dp[N-1][target];
    }

    /*********************************************************************************************************************/

    public boolean isExistsSubset_TD1(int i,int target,int n, int[] nums,int[][] dp){

        if(i < 0 || i >= n) return false;
        if(dp[i][target] != -1) return dp[i][target] == 1;

        if(i == 0){ // single elem
            if(nums[i] == 0 && target == 0)return true;
            return target == 0 || nums[i] == target;
        }

        boolean notTake = isExistsSubset_TD1(i-1,target,n,nums,dp);
        boolean take = nums[i] <= target && isExistsSubset_TD1(i - 1, target - nums[i], n, nums, dp);
        dp[i][target] = (take || notTake) ? 1 : 0;
        return (take || notTake);

    }

    public boolean isExistsSubset_TopDown_ExtraCasesFor0(int[] nums,int target){
        int[][] dp = new int[nums.length][target+1];
        for(int[] ar: dp){
            Arrays.fill(ar,-1);
        }
        return isExistsSubset_TD1(nums.length-1,target,nums.length,nums,dp);
    }

    public static void main(String[] args) {
        SubSetSumEqualsKExists ob = new SubSetSumEqualsKExists();
        System.out.println(ob.isExistsSubsetWithSumK_Recursion(new int[]{1,2,3,4},4));
        System.out.println(ob.isExistsSubsetWithSumK_Recursion(new int[]{1,2,3,4},91));
        System.out.println(ob.isExistsSubsetWithSumK_Recursion(new int[]{1,1,1},2));
        System.out.println("*********************** Top Down with Memoization ************************");
        System.out.println(ob.isExistsSubsetWithSumK_topDownMemoization(new int[]{1,2,3,4},4));
        System.out.println(ob.isExistsSubsetWithSumK_topDownMemoization(new int[]{1,2,3,4},91));
        System.out.println(ob.isExistsSubsetWithSumK_topDownMemoization(new int[]{1,1,1},2));
        System.out.println(ob.isExistsSubsetWithSumK_topDownMemoization(new int[]{1,0,0},0));

        System.out.println("*********************** Bottom Up with Tabulation ************************");
        System.out.println(ob.iSExistsSubset_BU(new int[]{1,2,3,4},4));
        System.out.println(ob.iSExistsSubset_BU(new int[]{1,2,3,4},91));
        System.out.println(ob.iSExistsSubset_BU(new int[]{1,1,1},2));
        System.out.println("***********************isExistsSubset_TopDown_ExtraCasesFor - nums[i] can be 0 ***********************");
        System.out.println(ob.isExistsSubset_TopDown_ExtraCasesFor0(new int[]{1,2,3,4},4));
        System.out.println(ob.isExistsSubset_TopDown_ExtraCasesFor0(new int[]{1,2,3,4},91));
        System.out.println(ob.isExistsSubset_TopDown_ExtraCasesFor0(new int[]{1,1,1},2));
        System.out.println(ob.isExistsSubset_TopDown_ExtraCasesFor0(new int[]{1,0,0},0));
        System.out.println(ob.isExistsSubset_TopDown_ExtraCasesFor0(new int[]{1,5,11,5},22));


    }
}
