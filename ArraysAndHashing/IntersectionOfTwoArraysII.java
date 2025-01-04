package ArraysAndHashing;

import java.util.*;

/*
https://leetcode.com/problems/intersection-of-two-arrays-ii/description/
Given two integer arrays nums1 and nums2,
return an array of their intersection. Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.



Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Explanation: [9,4] is also accepted.


Constraints:

1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000


Follow up:

What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once
 */
public class IntersectionOfTwoArraysII {

    /*
    TC: O(m + n)
    SC: O(N)
     */
    public int[] intersect(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;
        List<Integer> result = new ArrayList<>();

        if(m < n) return intersect(nums2,nums1);
        // num1 will always contain more elems or equal elems
        HashMap<Integer,Integer> freqMap = new HashMap<>();

        for(int num: nums1){
            freqMap.put(num,freqMap.getOrDefault(num,0)+1);
        }
        for(int num: nums2){
            if(freqMap.containsKey(num)){
                result.add(num);
                freqMap.put(num,freqMap.get(num)-1);//reduce frequency
                if(freqMap.get(num) <= 0) freqMap.remove(num);
            }
        }
        return result.stream().mapToInt(i->i).toArray();
    }
    /*
    Followup 1: What if the given array is already sorted? How would you optimize your algorithm?
    TC : O(max(N,M)) Worst case, for example, would be nums1 = {100}, and nums2 = {1, 2, ..., 100 }. We will always iterate the longest array.
     SC : O(1) .. ignoring result space
     */
    public int[] intersectAlreadySorted(int[] nums1, int[] nums2) {
        //Assume input is sorted...lets sort it here explicity...we don't need this in actual algo
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> result = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        int i = 0; int j = 0;
        while(i < m && j < n){
            if(nums1[i] == nums2[j]){
                result.add(nums1[i]);
                i++;
                j++;
            }else if(nums1[i] > nums2[j]){
                j++;
            } else{
                i++;
            }
        }
        return result.stream().mapToInt(x->x).toArray();
    }
    /*
    Followup 2 : What if nums1's size is small compared to nums2's size? Which algorithm is better?

    Good explanation : https://github.com/RodneyShag/LeetCode_solutions/blob/master/Solutions/Intersection%20of%20Two%20Arrays%20II.md
    If the arrays are sorted, then we can loop through nums1 (the smaller array), and for each value, binary search it in nums2 (the larger array). Implementation becomes tricky if duplicate values are allowed.
    To deal with duplicates, we alter binary search so that if duplicates exist, we return the index for the match furthest left.
     */
    /*
    TC : O(m log n) // m is size of smaller array.... m times we do binary search on larger array of size n. (each binary search log n)
    SC : O(m) // intersection array. (== size of smaller array , case when all match)
     */

    public int[] intersectNums1Small_BinarySearch(int[] nums1, int[] nums2) {

        if(nums1 == null || nums2 == null ) return new int[]{0};
        if(nums1.length == 0 || nums2.length == 0) return new int[]{0};
        if(nums2.length < nums1.length) return intersect(nums2,nums1);
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int m = nums1.length;
        int n = nums2.length;
        // For every elem in smaller array nums1 do a binary search in nums2(longer array)
        /*
           This binary search will be special in such a way ==> 1 2 2 2 2 2 2 7 8 ,,, on  finding a mid position 2 , it will still continue and give the left most occurence index.

            We also take caution that we don't revisit the same visited number that we have matched already and taken into the result. (Meaning lets say num1 1 2 2 2 3    num2 1 2 2 2 2 3 ==> the third occurence of 2 in num2 should not again take the second occurence that we have already taken to result list. )
        */

        int lastVisitIndex = 0; // this is to make sure we don't revisit visited index
        List<Integer> intersection = new ArrayList<>();
        // for every elem in num1 , do a binary search of it in num2
        for(int i = 0 ; i < m ; i++){
            int foundLeftMostIndex = binarySearch(nums2,nums1[i],lastVisitIndex);
            if(foundLeftMostIndex != -1){
                intersection.add(nums1[i]);
                lastVisitIndex = foundLeftMostIndex + 1;// mark visited and move (we don't have to consider this index for binarysearch in nums2 anymore it is written to result already.
            }
        }
        return intersection.stream().mapToInt(i->i).toArray();
    }

    public int binarySearch(int[] arr, int target, int lo){

        int hi = arr.length-1;

        while(lo < hi){
            int mid = lo + ((hi - lo) >> 1);
            if(arr[mid] == target){
                // don't stop --> go to the left most to check for duplicates
                hi = mid;
            }else if(arr[mid] > target){
                hi = mid-1;
            }else{
                lo = mid+1;
            }
        }
        if(lo <= arr.length-1 && arr[lo] == target){
            return lo;
        }
        return -1;
    }



    public static void main(String[] args) {
        IntersectionOfTwoArraysII ob = new IntersectionOfTwoArraysII();
        System.out.println(Arrays.toString(ob.intersect(new int[]{1,2,2,1},new int[]{2,2})));
        System.out.println(Arrays.toString(ob.intersect(new int[]{4,9,5},new int[]{9,4,9,8,4})));
        System.out.println("******************** Followup - 1 : Already Sorted *************************");
        System.out.println(Arrays.toString(ob.intersectAlreadySorted(new int[]{1,2,2,1},new int[]{2,2})));
        System.out.println(Arrays.toString(ob.intersectAlreadySorted(new int[]{4,9,5},new int[]{9,4,9,8,4})));
        System.out.println("******************** Followup - 2 : nums1 is small , binary search  *************************");
        System.out.println(Arrays.toString(ob.intersectNums1Small_BinarySearch(new int[]{2,2},new int[]{1,2,2,1})));
        System.out.println(Arrays.toString(ob.intersectNums1Small_BinarySearch(new int[]{4,9,5},new int[]{9,4,9,8,4})));
        System.out.println(Arrays.toString(ob.intersectNums1Small_BinarySearch(new int[]{1,0},new int[]{1,3,8,9,3})));


    }
}
