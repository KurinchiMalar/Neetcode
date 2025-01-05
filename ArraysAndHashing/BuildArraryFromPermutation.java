package ArraysAndHashing;

import java.util.Arrays;

/*
Given a zero-based permutation nums (0-indexed), build an array ans of the same length where ans[i] = nums[nums[i]] for each 0 <= i < nums.length and return it.

A zero-based permutation nums is an array of distinct integers from 0 to nums.length - 1 (inclusive).



Example 1:

Input: nums = [0,2,1,5,3,4]
Output: [0,1,2,4,5,3]
Explanation: The array ans is built as follows:
ans = [nums[nums[0]], nums[nums[1]], nums[nums[2]], nums[nums[3]], nums[nums[4]], nums[nums[5]]]
    = [nums[0], nums[2], nums[1], nums[5], nums[3], nums[4]]
    = [0,1,2,4,5,3]
Example 2:

Input: nums = [5,0,1,2,3,4]
Output: [4,5,0,1,2,3]
Explanation: The array ans is built as follows:
ans = [nums[nums[0]], nums[nums[1]], nums[nums[2]], nums[nums[3]], nums[nums[4]], nums[nums[5]]]
    = [nums[5], nums[0], nums[1], nums[2], nums[3], nums[4]]
    = [4,5,0,1,2,3]


Constraints:

1 <= nums.length <= 1000
0 <= nums[i] < nums.length
The elements in nums are distinct.


Follow-up: Can you solve it without using an extra space (i.e., O(1) memory)?
 */
public class BuildArraryFromPermutation {
    /*
    TC : O(n)
    SC : O(n)
     */
    public int[] buildArray(int[] nums) {

        if(nums == null || nums.length ==1 ) return nums;
        int n = nums.length;
        // 0 <= nums[i] < nums.length
        // nums[i] won't be nums.length therefore n is good enough for ans array
        int[] ans = new int[n];

        for(int i = 0 ; i < n ; i++){
            ans[i] = nums[nums[i]];
        }

        return ans;
    }
    /* https://www.youtube.com/watch?v=1svjL7Docuo
    https://github.com/Algorithms-Made-Easy/Leetcode-Challenge/blob/main/1920.%20Build%20Array%20from%20Permutation
    Let's try to solve in O(1) space (inplace)
    Given : 1 <= nums.length <= 1000

    say a = nums[i] // orig val
        b = nums[nums[i]] //newval

        You basically need to maintain both a and b in place in the array and be able to retrieve.
        To do that we bump up one number with a random value that is not within the array value ranges.
        So we are encoding with a CONST value  ( a + b*CONST)
        To get a and b individually from this==>
                a ==> (a + b*CONST) % CONST   (To get ORIG do a modulo) --> (getting the reminder of what was added)
                b ==> (a + b*CONST) / CONST  (To get new do division)  ( reversing the *CONST by division to get b )

        while decoding we do a encodedValue/CONST
With example
let nums=[0,2,1,5,3,4] , CONST = 6
if a = nums[3] = 5 then b = nums[nums[3]] = nums[5] = 4
a+(CONST)*b = 5 + (6*4) = 29
29%n = 29 % 6 = 5 = a; so formula for a = (a+CONST*b)%n
29/n = 29/6 = 4 = b ; so formula for b = (a+CONST*b)/n
*/
    /*
    TC : O(n)
    SC : O(1)
     */
    public int[] buildArrayEfficient(int[] nums) {

        if(nums == null || nums.length ==1 ) return nums;
        int n = nums.length;
        //1 <= nums.length <= 1000
        int CONST = 1001;
        // Encoding step
        for(int i = 0 ; i < n; i++){
            int a = nums[i];
            // to avoid using a previously corrupted value (To get ORIG , do modulo),
            // If it was not corrupted the modulo wont have impact you will get the expected nums[nums[i]] as such.
            int b = nums[a] % CONST;
            nums[i] = a + (CONST * b);
        }
        //Decoding step
        for(int i = 0 ; i < n ; i++){
            nums[i] = nums[i] / CONST;
        }
        return nums;
    }

    public static void main(String[] args) {
        BuildArraryFromPermutation ob = new BuildArraryFromPermutation();
        System.out.println(Arrays.toString(ob.buildArray(new int[]{0,2,1,5,3,4})));
        System.out.println(Arrays.toString(ob.buildArray(new int[]{5,0,1,2,3,4})));
        System.out.println("************* Efficient *********************");
        System.out.println(Arrays.toString(ob.buildArrayEfficient(new int[]{0,2,1,5,3,4})));
        System.out.println(Arrays.toString(ob.buildArrayEfficient(new int[]{5,0,1,2,3,4})));

    }
}


