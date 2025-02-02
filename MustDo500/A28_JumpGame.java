package MustDo500;

import java.util.Arrays;

/*
https://leetcode.com/problems/jump-game/
 */
public class A28_JumpGame {

    /*
    Recursion
    TC: O(2 ^ n)
    SC : O(n)
     */

    public boolean canJump_RecHelper(int index, int[] nums, int n){
        if(index >= n) return false;
        if(index == n-1) return true;

        //iterate for all possible jumps from current index. nums[i] = 4 ==> 1 step jump, 2 step jump, 3 step jump, 4 step jump
        for(int i = 1; i <= nums[index]; i++){

            // for each of the jumpstep need to verify possiblity
            if(canJump_RecHelper(index + i,nums,n)){
                return  true;
            }
        }
        return false;
    }
    public boolean canJump_Rec(int[] nums) {
        int n = nums.length;
        return canJump_RecHelper(0,nums,n);
    }

    //*********************************************************************************************
    /*
    TC : O(n  * n)
    SC : O(n)
     */
    public boolean canJump_TD_Helper(int index, int[] nums, int n,int[] dp) {
        if(index >= n) return false;
        if(index == n-1) return true; // reached goal
        if(dp[index] != -1){
            return dp[index] == 1;
        }

        for(int i = 1; i <= nums[index]; i++){
            if(canJump_TD_Helper(index+i,nums,n,dp)){
                dp[index] = 1;
                return true;
            }
        }
        dp[index]= 0;
        return false;
    }
    public boolean canJump_TD(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);
        return canJump_TD_Helper(0,nums,n,dp);
    }

    //*********************************************************************************************
    /*
    TC : O(n*n)
    SC : O(n)
     */
    public boolean canJump_BU(int[] nums) {

        int n = nums.length;
        //int[] dp = new int[n+1];
        boolean[] dp = new boolean[n+1];

        // goal
        dp[n-1] = true;
        for(int i = n-2; i >= 0; i--){
            // for all possible jumps
            for(int j=1 ; j <= nums[i] && (i+j) < n; j++){
                if(dp[i + j]){ // need to boundary check this i+j as well so add this to condition.
                    dp[i] = true;
                    break; // no need to try further jump possibilities for this index(i)
                }
            }
        }
        return dp[0];

    }


    //*********************************************************************************************

    /*
    My submission : https://leetcode.com/problems/jump-game/submissions/1279186727/
    Greedy approach
        Have a goal post at n-1 and keep reducing the goal post towards 0.

        https://www.youtube.com/watch?v=Yan0cv2cLy8
    */
    /*
     TC : O(n)
     SC : O(1)
     */

    public boolean canJump_Greedy(int[] nums) {
        int n = nums.length;

        if(n ==1) return true;
        int gi = n-1;

        for(int j=n-2; j >= 0 ; j--){
            if(nums[j] + j >= gi){
                gi = j;
            }
            if(gi == 0) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        A28_JumpGame ob = new A28_JumpGame();
        System.out.println("**************** Recursion ***********************");
        System.out.println(ob.canJump_Rec(new int[]{2,3,1,1,4}));
        System.out.println(ob.canJump_Rec(new int[]{3,2,1,0,4}));
        System.out.println("**************** Top Down DP ***********************");
        System.out.println(ob.canJump_TD(new int[]{2,3,1,1,4}));
        System.out.println(ob.canJump_TD(new int[]{3,2,1,0,4}));
        System.out.println("**************** Bottom UP DP ***********************");
        System.out.println(ob.canJump_BU(new int[]{2,3,1,1,4}));
        System.out.println(ob.canJump_BU(new int[]{3,2,1,0,4}));
        System.out.println("**************** Greedy ***********************");
        System.out.println(ob.canJump_Greedy(new int[]{2,3,1,1,4}));
        System.out.println(ob.canJump_Greedy(new int[]{3,2,1,0,4}));

    }

}
