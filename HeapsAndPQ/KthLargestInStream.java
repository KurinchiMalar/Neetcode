package HeapsAndPQ;
import java.util.PriorityQueue;
import java.util.Queue.*;
/*
Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Implement KthLargest class:

KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
int add(int val) Appends the integer val to the stream and returns the element representing the kth largest element in the stream.

Example 1:

Input
["KthLargest", "add", "add", "add", "add", "add"]
[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
Output
[null, 4, 5, 5, 8, 8]

Explanation
KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
kthLargest.add(3);   // return 4
kthLargest.add(5);   // return 5
kthLargest.add(10);  // return 5
kthLargest.add(9);   // return 8
kthLargest.add(4);   // return 8


Constraints:

1 <= k <= 104
0 <= nums.length <= 104
-104 <= nums[i] <= 104
-104 <= val <= 104
At most 104 calls will be made to add.
It is guaranteed that there will be at least k elements in the array when you search for the kth element.


 Complexity Analysis Best Explained : https://leetcode.com/problems/kth-largest-element-in-a-stream/editorial/
 Given N as the length of nums and M as the number of calls to add(),
 TC : O(NlogN + Mlogk)
 SC : O(N) ...though we later maintain only k elements , we initially do heapify all.
 */
public class KthLargestInStream {
    final PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
    final int k;

    public KthLargestInStream(int k, int[] nums){
        this.k=k;
        for(int num:nums){
            add(num);
        }
    }

    public int add(int val) { // at any point maintain minHeap of the max 3 elements
        if (pq.size() < k) {
            pq.add(val);
        } else if (pq.peek() < val) { // lesser than the smallest in minHeap, so remove and add
            pq.poll();
            pq.add(val);
        }
        return pq.peek();
    }
    public static void main(String[] args){
        KthLargestInStream kthLargest = new KthLargestInStream(3,new int[]{4, 5, 8, 2});
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.add(9));
        System.out.println(kthLargest.add(4));
    }
}

