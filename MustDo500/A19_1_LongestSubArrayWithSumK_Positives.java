package MustDo500;

import java.util.HashMap;

/*
https://takeuforward.org/data-structure/longest-subarray-with-given-sum-k/

https://www.youtube.com/watch?v=frf7qxiN2qU

 Find the length of the longest subarray in which the sum of elements is equal to ‘K’.

 Example:
Input: ‘N’ = 5,  ‘K’ = 4, ‘NUMS’ = [ 1, 2, 1, 0, 1 ]

Output: 4

There are two subarrays with sum = 4, [1, 2, 1] and [2, 1, 0, 1]. Hence the length of the longest subarray with sum = 4 is 4.
 */
public class A19_1_LongestSubArrayWithSumK_Positives {

    /*
    TC : O(n^3)
    SC : O(1)
     */
    public  int getLongestSubarrayBruteForce(int []nums, int k) {

        int n =  nums.length;
        int maxLen = 0;
        for(int i = 0 ; i < n; i++){ //start

            for(int j = i ; j < n; j++){ //end
                // i to j window is a subArray
                int sum = 0;
                // to find the sum of elements in this subArray
                for(int k1 = i; k1 <= j ; k1++){
                    sum += nums[k1];
                }
                if(sum == k){
                    maxLen = Math.max(maxLen,j-i+1);
                }
            }
        }
        return maxLen;
    }

    /*
    TC : O(n ^ 2)
    SC : O(1)
     */

    public  int getLongestSubarrayBruteForceBetter(int []nums, int k) {

        int n =  nums.length;
        int maxLen = 0;

        for(int i = 0 ; i < n; i++){ //start
            int cursum = 0;
            for(int j = i ; j < n; j++){ //end
                // i to j window is a subArray
                cursum += nums[j];
                if(cursum == k){
                    maxLen = Math.max(maxLen,j-i+1);
                }
            }
        }
        return maxLen;
    }

    /*
    Using Hashing

    IDEA:
    We want to identify subArray with sum =k --->
                    i -> j we have a subArray with sum = x
                    Within this window [i,] If we have seen already a sum of x-k it means , there is now a window with sum k (Reverse math)

                    Algo
                    Keep adding all the sum with the index
                    Before adding check if x-k has been encountered already, Subtract from current index (gives the length of window with sum k)...compare this with max len.

     */
    /*
    Optimal for Array with Postives and Negatives
    https://takeuforward.org/data-structure/longest-subarray-with-given-sum-k/
    TC: O(N)
    SC : O(N)
     */
    public  int getLongestSubarray_Hashing(int []nums, int k) {
        int n = nums.length;
        HashMap<Integer,Integer> hmap = new HashMap<>(); // sumSoFar, index
        int maxLen = 0;
        int curSum = 0;
        for(int i = 0 ; i < n; i++ ){
            curSum += nums[i]; // This is x
            if(curSum == k){
                maxLen = Math.max(maxLen,i+1);
            } else if(hmap.containsKey(curSum - k)){ // subArray with sum K is present inclusive of current index
                int subArraySumKLen = i - (hmap.get(curSum-k));
                Math.max(maxLen,subArraySumKLen);
            }else{
                if(!hmap.containsKey(curSum))hmap.put(curSum,i); // to avoid re-updating indicies when elements in original array are 0 and the sum is obtained again and again, we need the left most occurence to get the longest
            }

        }
        return maxLen;
    }
    /* Optimal for Array with Postives and Zeroes
    Keep two pointers at beginning and move like sliding window

        if the sum exceeds k move head,...else keep moving tail.
        Whenever sum = k , keep track of length
     */

    public static int getLongestSubarray_2PointerGreedy(int []a, long k) {
        int n = a.length; // size of the array.

        int left = 0, right = 0; // 2 pointers
        long sum = a[0];
        int maxLen = 0;
        while (right < n) {
            // if sum > k, reduce the subarray from left
            // until sum becomes less or equal to k:
            while (left <= right && sum > k) {
                sum -= a[left];
                left++;
            }

            // if sum = k, update the maxLen i.e. answer:
            if (sum == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }

            // Move forward thw right pointer:
            right++;
            if (right < n) sum += a[right];
        }

        return maxLen;
    }





    public static void main(String[] args) {
        A19_1_LongestSubArrayWithSumK_Positives ob = new A19_1_LongestSubArrayWithSumK_Positives();
        System.out.println("************ BruteForce **********************************");
        System.out.println(ob.getLongestSubarrayBruteForce(new int[]{1, 2, 1, 0, 1 },4));
        System.out.println(ob.getLongestSubarrayBruteForce(new int[]{2, 3, 5, 1, 9},10));
        System.out.println(ob.getLongestSubarrayBruteForce(new int[]{1,2,1,2,1},3));
        System.out.println("************ BruteForce Better ********************************");


        System.out.println(ob.getLongestSubarrayBruteForceBetter(new int[]{1, 2, 1, 0, 1 },4));
        System.out.println(ob.getLongestSubarrayBruteForceBetter(new int[]{2, 3, 5, 1, 9},10));
        System.out.println(ob.getLongestSubarrayBruteForceBetter(new int[]{1,2,1,2,1},3));

        System.out.println("************ Using Hashing **********************************");
        System.out.println(ob.getLongestSubarray_Hashing(new int[]{1, 2, 1, 0, 1 },4));
        System.out.println(ob.getLongestSubarray_Hashing(new int[]{2, 3, 5, 1, 9},10));
        System.out.println(ob.getLongestSubarray_Hashing(new int[]{1,2,1,2,1},3));
        System.out.println("************ Using 2Pointer Greedy **********************************");
        System.out.println(ob.getLongestSubarray_2PointerGreedy(new int[]{1, 2, 1, 0, 1 },4));
        System.out.println(ob.getLongestSubarray_2PointerGreedy(new int[]{2, 3, 5, 1, 9},10));
        System.out.println(ob.getLongestSubarray_2PointerGreedy(new int[]{1,2,1,2,1},3));
        }


}
