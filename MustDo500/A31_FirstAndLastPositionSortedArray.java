package MustDo500;

import java.util.Arrays;

public class A31_FirstAndLastPositionSortedArray {

    /*
    My submission : https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/submissions/1290627299/
    TC: O(log n)
    SC : O(1)
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1,-1};
        if(nums == null || nums.length == 0)return result;
        int n = nums.length;
        int start = 0;
        int end = n-1;

        return binarySearch(nums,start,end,target,n,result);

    }
    public int[] binarySearch(int[] nums,int start, int end, int target,int n,int[] result){

        if(start < 0 || end >= n || start > end) return result;

        if(start == end && nums[start] != target) return result;

        int mid = (start + end) / 2;

        if(nums[mid] == target){
            start = mid;
            end = mid;

            while(start >= 0 && nums[start]==target) start--;
            while(end < n && nums[end] == target) end++;
            result[0] = start+1;
            result[1] = end-1;
            return result;

        }else if(nums[mid] > target){
            return binarySearch(nums,start,mid-1,target,n,result);
        }
        return binarySearch(nums,mid+1,end,target,n,result);

    }

    public static void main(String[] args) {
        A31_FirstAndLastPositionSortedArray ob = new A31_FirstAndLastPositionSortedArray();
        System.out.println(Arrays.toString(ob.searchRange(new int[]{},0)));
        System.out.println(Arrays.toString(ob.searchRange(new int[]{5,7,7,8,8,10},8)));
        System.out.println(Arrays.toString(ob.searchRange(new int[]{5,7,7,8,8,10},6)));
        System.out.println(Arrays.toString(ob.searchRange(new int[]{2,2},1)));


    }
}
