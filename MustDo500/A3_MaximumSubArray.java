package MustDo500;

import java.util.Arrays;

public class A3_MaximumSubArray {

    /*
    TC : O(n * n * n)
    Sc : O(1)
     */
    public int maxSubArray_BruteForce(int[] nums) {
        //Kadane's algo --> if current element added makes the overall sum negative, we will ignore the chosen window.
        int maxSumSoFar = Integer.MIN_VALUE;

        for(int i = 0 ; i < nums.length; i++){
            for(int j = i; j < nums.length; j++){

                // subarray [i....j]
                int curSum = 0;
                for(int k = i ; k <= j ; k++){
                    curSum += nums[k];
                }
                maxSumSoFar = Math.max(maxSumSoFar,curSum);
            }
        }
        return maxSumSoFar;

    }

    /*
    My submission : https://leetcode.com/problems/maximum-subarray/
    TC : O(n)
    SC : O(1)
     */
    public int maxSubArray(int[] nums) {
        //Kadane's algo --> if current element added makes the overall sum negative, we will ignore the chosen window.
        int maxSumSoFar = Integer.MIN_VALUE;
        int curSum = 0;

        for(int i = 0 ; i < nums.length; i++){
            curSum += nums[i];
            maxSumSoFar = Math.max(maxSumSoFar,curSum);
            // initialize for next iteration
            curSum = (curSum < 0) ? 0:curSum;
        }
        return maxSumSoFar;

    }

    /*
    TC : O(n)
    Sc : O(n) // for result
     */
    public int[] getMaxSubArray(int[] nums) {
        //Kadane's algo --> if current element added makes the overall sum negative, we will ignore the chosen window.
        int maxSumSoFar = Integer.MIN_VALUE;
        int curSum = 0;

        int ansStart = 0;
        int ansEnd = 0;

        for(int i = 0 ; i < nums.length; i++){
            if(curSum == 0){
                // new window
                ansStart = i;
            }
            curSum += nums[i];
            if(maxSumSoFar < curSum){
                //maxSumSoFar = Math.max(maxSumSoFar,curSum);
                maxSumSoFar = curSum;
                ansEnd = i;
            }
            // initialize for next iteration
            curSum = (curSum < 0) ? 0:curSum;

        }
        return Arrays.copyOfRange(nums,ansStart,ansEnd+1);

    }

    public static void main(String[] args) {
        A3_MaximumSubArray ob = new A3_MaximumSubArray();
        System.out.println(ob.maxSubArray_BruteForce(new int[]{-2,1,-3,4,-1,2,1,-5,4}));

        System.out.println(ob.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(Arrays.toString(ob.getMaxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4})));
    }
}
