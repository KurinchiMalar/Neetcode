package DynamicProgramming.DP_on_Subsequences;

/*
DP 18. Count Partitions With Given Difference | Dp on Subsequences
https://www.youtube.com/watch?v=zoilQD1kYSg

https://takeuforward.org/data-structure/count-partitions-with-given-difference-dp-18/


 */


import java.util.Arrays;

/*
Problem statement
Given an array ‘ARR’, partition it into two subsets (possibly empty) such that their union is the original array. Let the sum of the elements of these two subsets be ‘S1’ and ‘S2’.

Given a difference ‘D’, count the number of partitions in which ‘S1’ is greater than or equal to ‘S2’ and the difference between ‘S1’ and ‘S2’ is equal to ‘D’. Since the answer may be too large, return it modulo ‘10^9 + 7’.

For each test case, find the number of partitions satisfying the above conditions modulo 10^9 + 7.

If ‘Pi_Sj’ denotes the Subset ‘j’ for Partition ‘i’. Then, two partitions P1 and P2 are considered different if:

1) P1_S1 != P2_S1 i.e, at least one of the elements of P1_S1 is different from P2_S2.
2) P1_S1 == P2_S2, but the indices set represented by P1_S1 is not equal to the indices set of P2_S2.


Note that the sum of the elements of an empty subset is 0.

For example :
If N = 4, D = 3, ARR = {5, 2, 5, 1}
There are only two possible partitions of this array.
Partition 1: {5, 2, 1}, {5}. The subset difference between subset sum is: (5 + 2 + 1) - (5) = 3
Partition 2: {5, 2, 1}, {5}. The subset difference between subset sum is: (5 + 2 + 1) - (5) = 3
These two partitions are different because, in the 1st partition, S1 contains 5 from index 0, and in the 2nd partition, S1 contains 5 from index 2.


N= 4, D = 3 , ARR = {5 , 2, 6, 4}

N= 4 , D = 0, ARR = {1,1,1,1}



 */
/*

Idea:
     arr -> sum = Total
     2 partitions S1, S2
     S1 > S2 , S1 - S2 = D


     Lets try to derive an equation
      Total = S1 + S2  ==>
                    S1 = Total - S2



      Given S1 - S2 = D

      Substituting S1 ==>

                    Total - S2 -S2 = D
                     Total - 2S2 = D
                      Total - D = 2*S2

                        S2 = (Total - D )/ 2  --> This is the target. If we identify one partition with this sum the rest corresponds to S1 :)



 */
/*
My submission : https://www.naukri.com/code360/problems/partitions-with-given-difference_3751628?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTabValue=SUBMISSION

 */
public class CountPartitionsWithGivenDifference {
    static  int mod =(int)(Math.pow(10,9)+7);
    public int countSubSetSumEqualsK(int i, int target, int n , int[] nums, int[][] dp){

        if(i < 0 || i >= n) return 0;

        if(dp[i][target] != -1) return dp[i][target];

        if(i == 0){
            if(nums[i] == 0 && target == 0) return 2;
            if(target == 0 || nums[i] == target) return 1;
            return 0;
        }

        int notTake = countSubSetSumEqualsK(i-1,target,n,nums,dp);
        int take = (nums[i] <= target) ? countSubSetSumEqualsK(i-1,target-nums[i],n,nums,dp) : 0;
        dp[i][target] = (notTake + take) % mod; // asked in question
        return dp[i][target];

    }


    /*
    TC : O(n) for sum + O(n * k)
    SC : O(n * k) + O(n)
     */
    public  int countPartitions(int n, int d, int[] arr) {

        if( n <= 1) return 0;
        int total = Arrays.stream(arr).sum();

        int numerator = total - d;

        // all are numbers and hence (total-d) cannot be negative, also no fractions should come on /2  and hence we ensure numerator to be even
        //Base Cases
        if(numerator < 0  || ((numerator % 2) != 0)){
            return  0;
        }

        int target = numerator / 2;
        int[][] dp = new int[n][target+1];
        for(int[] ar:dp){
            Arrays.fill(ar,-1);
        }

        return  countSubSetSumEqualsK(n-1,target,n,arr,dp);

    }


    public static void main(String[] args) {
        CountPartitionsWithGivenDifference ob = new CountPartitionsWithGivenDifference();
        System.out.println(ob.countPartitions(4,3, new int[]{5, 2, 5, 1})); // {2, 5, 1}  {5}  ==> 8 - 5 = 3
        System.out.println(ob.countPartitions(4,3, new int[]{5, 2, 6, 4})); // {2, 5, 1}  {5}  ==> 8 - 5 = 3
        System.out.println(ob.countPartitions(4,0, new int[]{1, 1 , 1, 1})); // {2, 5, 1}  {5}  ==> 8 - 5 = 3



    }
}
