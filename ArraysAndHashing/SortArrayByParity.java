package ArraysAndHashing;

import java.util.Arrays;

/*
Given an integer array nums, move all the even integers at the beginning of the array followed by all the odd integers.

Return any array that satisfies this condition.



Example 1:

Input: nums = [3,1,2,4]
Output: [2,4,3,1]
Explanation: The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
Example 2:

Input: nums = [0]
Output: [0]


Constraints:

1 <= nums.length <= 5000
0 <= nums[i] <= 5000
 */
/*
Tc : O(n)
SC : O(1)
 */
public class SortArrayByParity {
    public boolean isOdd(int x){
        return (x%2 != 0);
    }
    public boolean isEven(int x){
        return (x%2 == 0);
    }
    public void swap(int[] nums,int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public int[] sortArrayByParity(int[] nums) {
        if(nums == null) return new int[]{};
        int n = nums.length;
        int oddI = n-1;
        int evenI = 0 ;

        while(evenI < oddI && oddI >= 0){
            if(isOdd(nums[evenI]) && isEven(nums[oddI])){
                //eligible to swap
                swap(nums,evenI,oddI);
                oddI--;
                evenI++;
            }else if(isEven(nums[evenI])){
                evenI++;
            }else if(isOdd(nums[oddI])){
                oddI--;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        SortArrayByParity ob = new SortArrayByParity();
        System.out.println(Arrays.toString(ob.sortArrayByParity(new int[]{3, 1, 2, 4})));
        System.out.println(Arrays.toString(ob.sortArrayByParity(new int[]{0})));
    }

}
