package DynamicProgramming;
/*
https://leetcode.com/problems/house-robber-ii/
Idea : Use the same logic of HouseRobber1 (MaxNonAdjSum),   here the twist is you should not include both the first and last element

     temp1[] = 0 to nums,length-2 // leave last elem
     temp2[] = 1 to nums.length-1 // leave first elem

     Solution : Math.max( maxNonAdjSum(temp1), maxNonAdjSum(temp2) )
 */
/*
TC : O(n) + O(n) = O(n)
SC : O(1)
 */
public class HouseRobber2 {

    // HouseRobber1 - Space optimized
    public int maxNonAdjSum(int[] nums,int start,int end,int n){
        if(n <= 0) return 0;
        if(n == 1) return nums[start];
        if(n == 2) return Math.max(nums[start],nums[end]);

        int prev1 = nums[start];
        int prev2 = Math.max(nums[start],nums[start+1]);
        int maxVal = 0;
        for(int i=start+2 ; i <= end; i++){
            int take = nums[i] + prev1;
            int notTake = prev2;
            maxVal = Math.max(take,notTake);
            prev1 = prev2;
            prev2=maxVal;
        }
        return maxVal;
    }
    public int robHelper(int[] nums){
        int n = nums.length;
        if(n <= 0) return 0;
        if(n == 1) return nums[0];
        if(n == 2) return Math.max(nums[0],nums[1]);

        int ans1 = maxNonAdjSum(nums,0,nums.length-2,nums.length-1); // leaving the last elem
        int ans2 = maxNonAdjSum(nums,1,nums.length-1,nums.length-1); // including the last elem

        return Math.max(ans1,ans2);
    }
    public int rob(int[] nums) {
        return robHelper(nums);
    }

    public static void main(String[] args) {
        HouseRobber2 ob = new HouseRobber2();
        System.out.println(ob.rob(new int[]{2,3,2})); // Expected : 3
        System.out.println(ob.rob(new int[]{1,2,3,1})); // Expected : 4
        System.out.println(ob.rob(new int[]{1,2,3})); // Expected : 3
    }
}
