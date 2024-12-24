package ArraysAndHashing;

import java.util.Arrays;

/*
https://leetcode.com/problems/squares-of-a-sorted-array/description/

Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.



Example 1:

Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].
Example 2:

Input: nums = [-7,-3,2,3,11]
Output: [4,9,9,49,121]


Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in non-decreasing order.


Follow up: Squaring each element and sorting the new array is very trivial, could you find an O(n) solution using a different approach?
 */
public class SquaresOfSortedArray {
    /*
    TC : O(nlogn)
    SC : O(1)
     */
    public int[] sortedSquares_Naive(int[] nums) {

        return Arrays.stream(nums).map(i->i * i).sorted().toArray();

    }
    /*
    TC : O(n)
    SC : O(1)
    Logic --> Gn sorted array including negative signs
          --> Forgetting the signs , the numbers at the left most end and right most end are big numbers
          --> start filling the same input array from end...with 2 pointers at these positions and max comparisions.
     */
    /*
    TC : O(n)
    SC : O(1) // ignoring the result array
     */
    public int[] sortedSquares_Efficient(int[] nums) {

        int n = nums.length;
        if(n == 1) {
            nums[0] =(int) Math.pow(nums[0],2);
            return nums;
        }
        int left = 0;
        int right = n-1;
        int tail = n-1;
        int[] result = new int[n];
        while(left <= right && tail >= 0){

            if(Math.abs(nums[left]) > Math.abs(nums[right])){
                result[tail] = (int)Math.pow(nums[left],2);
                left++;
            }else{
                result[tail] = (int)Math.pow(nums[right],2);
                right--;
            }
            tail--;
        }
        return result;

    }

    public static void main(String[] args) {
        SquaresOfSortedArray ob = new SquaresOfSortedArray();
        System.out.println(Arrays.toString(ob.sortedSquares_Naive(new int[]{-4, -1, 0, 3, 10})));
        System.out.println(Arrays.toString(ob.sortedSquares_Efficient(new int[]{-4, -1, 0, 3, 10})));
    }
}
