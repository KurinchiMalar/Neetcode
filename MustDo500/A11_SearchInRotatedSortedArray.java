package MustDo500;

/*
https://leetcode.com/problems/search-in-rotated-sorted-array/
 */
public class A11_SearchInRotatedSortedArray {

    /*
    TC : O( log n)
    SC : O(1)
     */
    public int search(int[] nums, int target) {

        int n = nums.length;
        int low = 0;
        int high = n-1;

        while(low <= high){

            int mid = (low + high)/2;

            if(nums[mid] == target)return mid;

            if(nums[low] <= nums[mid]){ // left sorted array
                if(target < nums[low] || target > nums[mid]){
                    low = mid+1; // go to right half
                }else{
                    high = mid-1;
                }
            }else{ // right sorted array
                if(target < nums[mid] || target > nums[high]){
                    high =  mid-1; // go to left half
                }else{
                    low = mid+1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        A11_SearchInRotatedSortedArray ob = new A11_SearchInRotatedSortedArray();
        System.out.println(ob.search(new int[]{4,5,6,7,0,1,2},0));
        System.out.println(ob.search(new int[]{4,5,6,7,0,1,2},3));
        System.out.println(ob.search(new int[]{1},0));
    }
}
