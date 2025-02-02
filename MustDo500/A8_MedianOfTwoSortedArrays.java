package MustDo500;

import java.util.Arrays;

/*
https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 */
public class A8_MedianOfTwoSortedArrays {

    /*
    My submisson: https://leetcode.com/problems/median-of-two-sorted-arrays/
    TC : O(n1 + n2)
    SC : O(n1 + n2) // mergedAr
     */
    public double findMedianSortedArrays_BruteForce(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        int total = n1 + n2;


        int[] mergedAr = new int[n1+n2];
        int l1 = 0 ;
        int l2 = 0;
        int l3 = 0;

        while(l1 < n1 && l2 < n2){

            if(nums1[l1] <= nums2[l2]){
                mergedAr[l3] = nums1[l1];
                l1++;
            }else{
                mergedAr[l3] = nums2[l2];
                l2++;
            }
            l3++;
        }

        while(l1 < n1){
            mergedAr[l3] = nums1[l1];
            l1++;
            l3++;
        }
        while(l2 < n2){
            mergedAr[l3] = nums2[l2];
            l2++;
            l3++;
        }
        //System.out.println(Arrays.toString(mergedAr));

        // find median in merged Ar
        int median_i = total/2;

        return (total % 2 == 0) ? ((double) (mergedAr[median_i] + mergedAr[median_i - 1]) /2) :(double) mergedAr[median_i];

    }
    /*
    https://www.youtube.com/watch?v=LPFhl65R7ww
    https://github.com/mission-peace/interview/blob/master/src/com/interview/binarysearch/MedianOfTwoSortedArrayOfDifferentLength.java
    IDEA:
        Partition the smallest array
        Have low and high pointers and adjust partition using binary search

        Found partition --> comparision between center 4 elements of imaginary merged ar

        partitionx --> Elements in nums1 until partionx-1 correspond to left partion of nums1

     */
    /*
    My submission : https://leetcode.com/problems/median-of-two-sorted-arrays/submissions/1267953256/
    Tc :  O(log(min(x,y))
    SC : O(1)
     */
    public double findMedianSortedArrays_UsingBinarySearch(int[] nums1, int[] nums2) {

        int n1 = nums1.length;
        int n2 = nums2.length;

        // nums1 should be the smallest array on which we will do the binarysearch

        if(n1 > n2){
            return findMedianSortedArrays_UsingBinarySearch(nums2,nums1);
        }

        int low = 0;
        int high = n1;
        while(low <= high){

            int partitionX = (low + high) / 2 ;
            int partitionY = ((n1 + n2 + 1) /2 ) - partitionX;

            //EdgeCases
            //if partitionX is 0 it means nothing is there on left side. Use -INF for maxLeftX
            //if partitionX is length of input then there is nothing on right side. Use +INF for minRightX
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE:nums1[partitionX-1];
            int minRightX = (partitionX == n1) ? Integer.MAX_VALUE:nums1[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE:nums2[partitionY-1];
            int minRightY = (partitionY == n2) ? Integer.MAX_VALUE:nums2[partitionY];

            //Found partition
            //We have partitioned array at correct place
            // Now get max of left elements and min of right elements to get the median in case of even length combined array size
            // or get max of left for odd length combined array size.
            if(maxLeftX <= minRightY && maxLeftY <= minRightX){
                if((n1 + n2)%2 != 0){ // odd
                    return Math.max(maxLeftX,maxLeftY);
                }else{ // even
                    return (double)(Math.max(maxLeftX,maxLeftY) + Math.min(minRightX,minRightY)) / 2;
                }
            }else if(maxLeftX > minRightY){ // nums1 partition is too much on the right need to move left.
                high = partitionX -1;
            }else{ // we are too much on the left so move right
                low = partitionX + 1;
            }
        }

        throw new IllegalArgumentException("Arrays are not sorted");
    }


        public static void main(String[] args) {
        A8_MedianOfTwoSortedArrays ob = new A8_MedianOfTwoSortedArrays();
        System.out.println("*****************  BruteForce *****************************");
        System.out.println(ob.findMedianSortedArrays_BruteForce(new int[]{1,3},new int[]{2}));
        System.out.println(ob.findMedianSortedArrays_BruteForce(new int[]{1,2},new int[]{3,4}));
        System.out.println(ob.findMedianSortedArrays_BruteForce(new int[]{},new int[]{2,3}));
        System.out.println("*****************  Binary Search *****************************");
        System.out.println(ob.findMedianSortedArrays_UsingBinarySearch(new int[]{1,3},new int[]{2}));
        System.out.println(ob.findMedianSortedArrays_UsingBinarySearch(new int[]{1,2},new int[]{3,4}));
        System.out.println(ob.findMedianSortedArrays_UsingBinarySearch(new int[]{},new int[]{2,3}));


        }
}
