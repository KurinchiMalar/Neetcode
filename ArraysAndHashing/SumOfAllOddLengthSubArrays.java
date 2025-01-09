package ArraysAndHashing;
/*
https://leetcode.com/problems/sum-of-all-odd-length-subarrays/description/
Given an array of positive integers arr, return the sum of all possible odd-length subarrays of arr.

A subarray is a contiguous subsequence of the array.



Example 1:

Input: arr = [1,4,2,5,3]
Output: 58
Explanation: The odd-length subarrays of arr and their sums are:
[1] = 1
[4] = 4
[2] = 2
[5] = 5
[3] = 3
[1,4,2] = 7
[4,2,5] = 11
[2,5,3] = 10
[1,4,2,5,3] = 15
If we add all these together we get 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
Example 2:

Input: arr = [1,2]
Output: 3
Explanation: There are only 2 subarrays of odd length, [1] and [2]. Their sum is 3.
Example 3:

Input: arr = [10,11,12]
Output: 66


Constraints:

1 <= arr.length <= 100
1 <= arr[i] <= 1000


Follow up:

Could you solve this problem in O(n) time complexity?
 */
public class SumOfAllOddLengthSubArrays {

    /*
     TC : O(N * N * N)
     SC : O(1)
     */
    public int sumOddLengthSubarrays(int[] arr) {
        if( arr == null ) return 0;
        int n = arr.length;
        int result = 0;
        for(int i = 0 ; i  < n ; i++){ // start index
            for(int j = i; j < n; j+=2){ // end index ...we need only the old length subarrays therefore j+=2
                // for current subarray i to j , find the sum of all elem
                for(int k = i ; k <= j; k++){
                    result += arr[k];
                }
            }
        }
        return result;
    }
    /*
    TC : O(n)
    SC : O(1)
     */
    public int sumOddLengthSubarraysEfficient(int[] arr) {
        if( arr == null ) return 0;
        int n = arr.length;
        int result = 0;
        /*
        We need to get the contribution(# of subarrays) that an element at arr[i] makes
        left = i + 1 (including itself)
        right = n - i (including itself)

        1) Total subarrays = left * right

            Only even Subarrays = (total) / 2
            Only odd = (total+1) / 2   (Half the remaining subarrays are odd, rounded up (+1))

            No of OddLengthSubArrays  = ( (left * right) + 1 ) / 2

            In each of these subarrays arr[i] is present exactly once. So total contribution of arr[i] to the sum is

        2) totalSum = No of OddLengthSubArrays * arr[i];

        */
        for(int i = 0 ; i < n ; i++){
            int left = i + 1;
            int right = n - i;

            int noOfOddSubArraysOfi = ((left * right) + 1) / 2;

            result += noOfOddSubArraysOfi * arr[i];
        }
        return result;
    }

    public static void main(String[] args) {
        SumOfAllOddLengthSubArrays ob = new SumOfAllOddLengthSubArrays();
        System.out.println(ob.sumOddLengthSubarrays(new int[]{1,4,2,5,3}));
        System.out.println(ob.sumOddLengthSubarrays(new int[]{1,2}));
        System.out.println(ob.sumOddLengthSubarrays(new int[]{10,11,12}));
        System.out.println("************* Efficient *****************");
        System.out.println(ob.sumOddLengthSubarraysEfficient(new int[]{1,4,2,5,3}));
        System.out.println(ob.sumOddLengthSubarraysEfficient(new int[]{1,2}));
        System.out.println(ob.sumOddLengthSubarraysEfficient(new int[]{10,11,12}));
    }

}
