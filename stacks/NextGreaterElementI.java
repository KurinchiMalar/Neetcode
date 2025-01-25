package stacks;
/*
https://leetcode.com/problems/next-greater-element-i/description/
The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.

You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.

For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.

Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.



Example 1:

Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
Output: [-1,3,-1]
Explanation: The next greater element for each value of nums1 is as follows:
- 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
- 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
- 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
Example 2:

Input: nums1 = [2,4], nums2 = [1,2,3,4]
Output: [3,-1]
Explanation: The next greater element for each value of nums1 is as follows:
- 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
- 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.


Constraints:

1 <= nums1.length <= nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 104
All integers in nums1 and nums2 are unique.
All the integers of nums1 also appear in nums2.

Follow up: Could you find an O(nums1.length + nums2.length) solution?
 */

import java.util.Arrays;
import java.util.Stack;
import java.util.HashMap;

public class NextGreaterElementI {
    /*
    BruteForce
    TC : O(m * n) ==> O(n * n)
    SC : O(1)
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null) return new int[]{};
        int m = nums1.length;
        int n = nums2.length;
        int[] result = new int[m];

        // Brute Force - Until you locate current elem in nums2 array, keep maintaining the immediate righMax_i
        int k = 0;

        for(int i = 0 ; i < m ; i++){

            int curElem = nums1[i];
            int rightMaxI = -1;
            int j = n-1; // start from the last everytime
            while(j >= 0 && nums2[j] != curElem ){
                if(nums2[j] > curElem){
                    rightMaxI = j;
                }
                j--;
            }
            result[k] = (rightMaxI != -1)?nums2[rightMaxI]:-1;
            k++;
        }

        return result;
    }
    /*
    Stacks Approach
    TC: O(n + m)
    SC : O( n + m) // n stack worstcase, m result array
     */
    public int[] nextGreaterElementEfficient(int[] nums1, int[] nums2) {

        if(nums1 == null || nums2 == null) return new int[]{};

        int m = nums1.length;
        int n = nums2.length;

        Stack<Integer> stk = new Stack<>();
        HashMap<Integer,Integer> ngeMap = new HashMap<>();
        // Keep adding to stack from nums2 , if an elemenet is < top of stack or stack is empty.
        // if you notice an element greater than top of stack, this is the nge for the top element (put that into a map for referencing later)

        // Adding to stack from nums2 and map if applicable.
        for(int j = 0 ; j < n; j++){
            while(!stk.isEmpty() && nums2[j] > stk.peek()){
                ngeMap.put(stk.pop(),nums2[j]);
            }
            stk.add(nums2[j]);
        }
        int[] result = new int[m];
        //Arrays.fill(result,-1);

        // Iterate nums1 and update result
        for(int i = 0 ; i < m ; i++){
            if(ngeMap.containsKey(nums1[i])){
                result[i] = ngeMap.get(nums1[i]);
            }else{
                result[i] = -1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        NextGreaterElementI ob = new NextGreaterElementI();
        System.out.println(Arrays.toString(ob.nextGreaterElement(new int[]{4,1,2},new int[]{1,3,4,2})));
        System.out.println(Arrays.toString(ob.nextGreaterElement(new int[]{2,4},new int[]{1,2,3,4})));
        System.out.println("*************************************** Efficient ******************************");
        System.out.println(Arrays.toString(ob.nextGreaterElement(new int[]{4,1,2},new int[]{1,3,4,2})));
        System.out.println(Arrays.toString(ob.nextGreaterElementEfficient(new int[]{2,4},new int[]{1,2,3,4})));


    }
}
