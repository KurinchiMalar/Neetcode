/*
Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

You must solve it in O(n) time complexity.



Example 1:

Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
Example 2:

Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4


Constraints:

1 <= k <= nums.length <= 105
-104 <= nums[i] <= 104

 */
package HeapsAndPQ;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class KthLargestElementArray {

    //TC : O(nlogn)
    //SC : O(1)
    public int findKthLargestSorting(int[] nums, int k){
        final int n = nums.length;
        Arrays.sort(nums);
        return nums[n-k];
    }

    //TC : O(n) adding to maxHeap + Take maxElem out : O(log n) do this K times O(klogn) ==> O(k logn)
    //SC : O(n)
    PriorityQueue<Integer> maxPQ = new PriorityQueue<Integer>(Collections.reverseOrder());
    public int findKthLargestMaxHeap(int[] nums, int k) {

        for(int num:nums){  // n
            maxPQ.add(num);  // log n
        }
        for(int i = 1; i <= k && maxPQ.size() > 0 ; i++){  // k
            if( i == k ){
                return maxPQ.peek();
            }
            maxPQ.poll(); // log n
        }
        return -1;
    }

    //TC : O(n log k)
    //SC : O(k)
    // Keep adding elems to minHeap. At any point maintain K elements in minHeap.
    PriorityQueue<Integer> minPQ = new PriorityQueue<Integer>();

    public int findKthLargestMinHeap(int[] nums, int k){

        for(int num : nums){ //n
            minPQ.add(num);  //log k  (k elements in heap...add will be log k)

            if(minPQ.size() > k){
                minPQ.poll(); // min so far will be popped out (log k)
            }
        }
        return minPQ.peek();
    }

    /*
    Most efficient QuickSelect Algorithm
    TC : O(n) average case , O(n*n) worstcase
    SC : O(1)
     */
    public int findKthLargestQuickSelect(int[] nums, int k){
        return quickSelect(nums,0,nums.length-1,k);
    }

    int quickSelect(int[] nums,int low, int high, int k){

        int pivot = low;

        // use quick sort's idea
        // put nums that are <= pivot to the left
        // put nums that are  > pivot to the right
        for(int j = low ; j < high ; j++){
            if(nums[j] <= nums[high]){
                swap(nums,j,pivot);
                pivot++;
            }
        }
        swap(nums,pivot,high);

        // count the nums that are > pivot from high
        int count = high - pivot + 1;

        if(count == k) return nums[pivot];

        if(count < k) {    // kth largest should be on the left because pivot to high  has less than k large elements.
            return quickSelect(nums,low,pivot-1,k-count);
        }
        return quickSelect(nums,pivot,high,k); // count > k , kth largest should on right.
    }
    private void swap(int[] a, int i, int j) {
        final int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args){
        KthLargestElementArray ob = new KthLargestElementArray();
        int[] nums = {3,2,1,5,6,4};
        System.out.println(ob.findKthLargestSorting(nums,2));
        System.out.println(ob.findKthLargestMaxHeap(nums,2));
        System.out.println(ob.findKthLargestMinHeap(nums,2));
        System.out.println(ob.findKthLargestQuickSelect(nums,2));
        ob.minPQ.clear();
        ob.maxPQ.clear();
        int[] nums1 = {3,2,3,1,2,4,5,5,6};
        System.out.println(ob.findKthLargestSorting(nums1,4));
        System.out.println(ob.findKthLargestMaxHeap(nums1,4));
        System.out.println(ob.findKthLargestMinHeap(nums1,4));
        System.out.println(ob.findKthLargestQuickSelect(nums1,4));

    }

}
