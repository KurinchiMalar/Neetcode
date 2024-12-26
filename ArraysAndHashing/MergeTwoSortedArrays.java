package ArraysAndHashing;

import Greedy.MergeIntervals;

import java.util.Arrays;

public class MergeTwoSortedArrays {

    /*
    https://leetcode.com/problems/merge-sorted-array/description/
    TC : O( m + n)
    SC : O(1)
     */
    public int[] merge(int[] nums1, int m, int[] nums2, int n) {

        if(n == 0) return nums1; //nothing to merge

        if(nums1.length < (m+n)) return new int[]{};
        int tail = m + n - 1;
        int p2Tail = n-1;

        // nums1 = [0] , m = 0 , n = 1 , nums2 = [2]
        if(m == 0){
            // copy everything from nums2 to nums1
            while(p2Tail >= 0){
                nums1[tail] = nums2[p2Tail];
                p2Tail--;
                tail--;
            }
            return nums1;
        }
        // Both nums1 and nums2 have non zero length.
        int p1Tail = m-1;

        while(p1Tail >= 0 && p2Tail >= 0 && tail >= 0){
            if(nums1[p1Tail] > nums2[p2Tail]){
                nums1[tail] = nums1[p1Tail];
                p1Tail--;
            }else{
                nums1[tail] = nums2[p2Tail];
                p2Tail--;
            }
            tail--;
        }
        // nums1[2,0] , m = 1 , nums2[1] , n = 1
        while(tail >= 0 && p2Tail >= 0){
            nums1[tail] = nums2[p2Tail];
            p2Tail--;
            tail--;
        }
        return nums1;
    }
    /*
    https://leetcode.com/problems/merge-sorted-array/submissions/1487440031/
    TC : O(m+n)
    SC : O(1)
     */
    public int[] mergeNeat(int[] nums1, int m, int[] nums2, int n) {

        int i = m-1;
        int j = n-1;
        int k = m+n-1;

        // All from nums2 should be merged to nums1
        while(j >= 0){
            if(i >=0 && nums1[i] > nums2[j]){
                nums1[k] = nums1[i];
                i--;
            }else{
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
        return nums1;
    }


    public static void main(String[] args) {
        MergeTwoSortedArrays ob = new MergeTwoSortedArrays();
        System.out.println(Arrays.toString(ob.merge(new int[]{1,2,3,0,0,0},3,new int[]{2,5,6},3)));

        System.out.println(Arrays.toString(ob.merge(new int[]{1},1,new int[]{},0)));

        System.out.println(Arrays.toString(ob.merge(new int[]{0},0,new int[]{2},1)));

        System.out.println(Arrays.toString(ob.merge(new int[]{2,0},1,new int[]{1},1)));

        System.out.println("****************************************************************************");

        System.out.println(Arrays.toString(ob.mergeNeat(new int[]{1,2,3,0,0,0},3,new int[]{2,5,6},3)));

        System.out.println(Arrays.toString(ob.mergeNeat(new int[]{1},1,new int[]{},0)));

        System.out.println(Arrays.toString(ob.mergeNeat(new int[]{0},0,new int[]{2},1)));

        System.out.println(Arrays.toString(ob.mergeNeat(new int[]{2,0},1,new int[]{1},1)));


    }

}
