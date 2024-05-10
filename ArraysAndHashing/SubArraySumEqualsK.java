package ArraysAndHashing;

import java.util.HashMap;

/*

SubArray = Contiguous
A subarray is a contiguous non-empty sequence of elements within an array.


https://leetcode.com/problems/subarray-sum-equals-k/

https://leetcode.com/problems/subarray-sum-equals-k/editorial/
 */
public class SubArraySumEqualsK {

    /*
    TC : O(n * n * n)
    SC : O(1)

    Approach 1 : BruteForce
     */
    public int subarraySumBruteForce(int[] nums, int k) {
        int n = nums.length;
        int count = 0;

        for(int start=0 ; start < n; start++){
            for(int end = start+1; end <= n; end++){ // <= n is applied to cater the window corrosponding to start = n-1 , end = n
                int curWindowSum = 0;
                for(int i=start; i < end; i++){
                    curWindowSum += nums[i];
                }
                if(curWindowSum == k)count++;
            }
        }
        return count;
    }
    /*
    TC : O(n) populative sum array + O(n * n) // for every possible subarray
    SC : O(n) for sum array

    Approach 2 : Using Cumulative Sum
    Instead of determining the sum of elements every time for every new subarray considered, we can make use of a cumulative sum array ,
     Then, in order to calculate the sum of elements lying between two indices, we can subtract the cumulative sum corresponding to the two indices to obtain the sum directly,
      instead of iterating over the subarray to obtain the sum.

     */
    public int subarraySum_UsingCumulativeSum(int[] nums, int k){
        // maintain a sum array where sum[i] holds the sum until nums[i-1]
        int n = nums.length;
        int count = 0;
        int[] sum = new int[n + 1];
        // sum[0] = 0  , sum[1] = sum until nums[0] , sum[2] = sum until nums[1]
        sum[0] = 0;
        for(int i = 1 ; i <= n ; i++){
            sum[i] = sum[i-1] + nums[i-1];  // at any i , sum until i-1 maintained.
        }

        for( int start = 0 ; start < n; start++){
            for(int end = start+1 ; end <=n ; end++){
                if(sum[end] -  sum[start] == k){
                    count++;
                }
            }
        }
        return count;
    }
    /*
    Approach 3 : Without Extra space.
    Without sum array... calculate the running sum on the go

     */
    /*
    TC : O( n * n)
    SC : O(1)
     */
    public int subarraySum_WithoutExtraSpace(int[] nums, int k){

        int n = nums.length;
        int count = 0;
        for( int start = 0 ; start < n; start++){
            int sum = 0; // re-initialize at every subArray window
            for(int end = start ; end < n ; end++){
                sum += nums[end];  // calculate the subarray sum on the fly
                if(sum == k){
                    count++;
                }
            }
        }
        return count;
    }

    /*
    Approach 4 : Using HashMap

    The idea behind this approach is as follows:
    If the cumulative sum represented by sum[i] (for sum up to ith index) up to two indices is the same,
        the sum of the elements lying in between those indices is zero.

         Extending the same thought further,
         if the cumulative sum up to two indices, say i and j is at a difference of k
            i.e. if sum[i]−sum[j]=k the sum of elements lying between indices i and j is k.

     */
    /*
    TC : O(n)
    SC : O(n)
     */

    public int subarraySum_Efficient(int[] nums, int k){

        int n = nums.length;
        int count = 0;
        int sumSoFar = 0;
        // sum, occurence
        HashMap<Integer,Integer> hmap = new HashMap<>();
        hmap.put(sumSoFar,1); // initialize
        for(int i = 0 ; i < n; i++){
            sumSoFar += nums[i];
            int diff = sumSoFar-k;
            /*if(!hmap.containsKey(diff)){
                hmap.put(sumSoFar,1);
            }else{
                count += hmap.get(diff);
                hmap.put(sumSoFar,hmap.getOrDefault(sumSoFar,0)+1);

            }*/
            if(hmap.containsKey(diff)){
                count += hmap.get(diff);
            }
            hmap.put(sumSoFar,hmap.getOrDefault(sumSoFar,0)+1);

        }
        return count;
    }

    public static void main(String[] args) {
        SubArraySumEqualsK ob = new SubArraySumEqualsK();
        System.out.println(ob.subarraySumBruteForce(new int[]{1,2,1,2,1},3));
        System.out.println(ob.subarraySum_UsingCumulativeSum(new int[]{1,2,1,2,1},3));
        System.out.println(ob.subarraySum_WithoutExtraSpace(new int[]{1,2,1,2,1},3));
        System.out.println(ob.subarraySum_Efficient(new int[]{1,2,1,2,1},3));



    }
}
