package ArraysAndHashing;

import java.util.Arrays;
import java.util.HashSet;

/*
https://leetcode.com/problems/intersection-of-two-arrays/description/
 Given two integer arrays nums1 and nums2, return an array of their
intersection
. Each element in the result must be unique and you may return the result in any order.



Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Explanation: [4,9] is also accepted.


Constraints:

1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000
 */
/*
TC : O(m + n)
SC : O(m + min(m,n)) ... worstcase the resultSet will have all elements from second array if the entire array is intersection
 */
public class IntersectionOfTwoArrays {

    public int[] intersection(int[] nums1, int[] nums2) {

        HashSet<Integer> firstSet = new HashSet<>();
        HashSet<Integer> resultSet = new HashSet<>();
        for(int num: nums1){ // O(m)
            firstSet.add(num);
        }

        for(int num: nums2){ // O(n)
            if(firstSet.contains(num)){
                resultSet.add(num);
            }
        }
        return resultSet.stream().mapToInt(i->i).toArray();
    }

    public static void main(String[] args) {
        IntersectionOfTwoArrays ob = new IntersectionOfTwoArrays();
        System.out.println(Arrays.toString(ob.intersection(new int[]{1,2,2,1},new int[]{2,2})));
        System.out.println(Arrays.toString(ob.intersection(new int[]{4,9,5},new int[]{9,4,9,8,4})));

    }
}
