package ArraysAndHashing;

import java.util.Arrays;

/*
https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/
Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice. The relative order of the elements should be kept the same.

Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.

Return k after placing the final result in the first k slots of nums.

Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.

Custom Judge:

The judge will test your solution with the following code:

int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
    assert nums[i] == expectedNums[i];
}
If all assertions pass, then your solution will be accepted.



Example 1:

Input: nums = [1,1,1,2,2,3]
Output: 5, nums = [1,1,2,2,3,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
Example 2:

Input: nums = [0,0,1,1,1,1,2,3,3]
Output: 7, nums = [0,0,1,1,2,3,3,_,_]
Explanation: Your function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).


Constraints:

1 <= nums.length <= 3 * 104
-104 <= nums[i] <= 104
nums is sorted in non-decreasing order.
 */
public class RemoveDuplicatedSortedArrayII {
    /*
    TC : O(n)
    SC : O(1)
     */
    public int removeDuplicates(int[] nums) {

        int n = nums.length;
        if(n == 1) return 1;
        int k = 0;
        int start = 0;
        for(int i = 1 ; i < n ; i++){
            if(nums[i-1] != nums[i]){ // unique
                if(i - start >= 2){
                    nums[k+1] = nums[k]; // repeated more than twice so make an atmost 2 copy
                    k+=2;
                    nums[k] = nums[i];
                }else{
                    nums[k+1] = nums[i]; // direct replace
                    k+=1;
                }
                start = i;
            }
        }

        // for last elem.
        if(n-start >= 2){
            nums[k+1] = nums[k];
            k+=1;
        }
        System.out.println(Arrays.toString(nums));
        return k+1;
    }
    /*
    TC : O(n)
    SC : O(1)
     */
    public int removeDuplicatesNeat(int[] nums) {

        int k = 0;

        for(int n : nums){
            /*
            first 2 elems are anyways needed in result ... k < 2
            If n > nums[k - 2], it means n is not the third duplicate (or more) of the same value, so it is safe to add it.
            */
            if(k < 2 || n > nums[k-2]){ // atmost 2 duplicates allowed...therefore checking until k-2
                nums[k] = n;
                k++;
            }
        }
        System.out.println(Arrays.toString(nums));
        return k;
    }

    public static void main(String[] args) {
        RemoveDuplicatedSortedArrayII ob = new RemoveDuplicatedSortedArrayII();
        System.out.println(ob.removeDuplicates(new int[]{1,1,1,2,2,3}));
        System.out.println(ob.removeDuplicates(new int[]{0,0,1,1,1,1,2,3,3}));

        System.out.println("***************** Neat  *********************");
        System.out.println(ob.removeDuplicatesNeat(new int[]{1,1,1,2,2,3}));
        System.out.println(ob.removeDuplicatesNeat(new int[]{0,0,1,1,1,1,2,3,3}));
    }
}
