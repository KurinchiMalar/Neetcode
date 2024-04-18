package Trees_BST;

/*
https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
Given an integer array nums where the elements are sorted in ascending order, convert it to a
height-balanced binary search tree.

Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: [0,-10,5,null,-3,null,9] is also accepted:

Input: nums = [1,3]
Output: [3,1]
Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.

Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in a strictly increasing order.
 */
public class SortedArrayToBST {

    public TreeNode sortedArrayToBST(int[] nums) {

        return sortedArrayToBST(nums,0,nums.length-1);

    }

    /*
    TC :  T(n) = 2T(n/2) + O(1) = O(n)
    SC :  O(log N) // space complexity is actually the stack used for recursion, which for balanced tree should be O(logN).

     */
    public TreeNode sortedArrayToBST(int[] nums, int start, int end){

        if(start > end)return null;

        int mid = start + (end -start)/2;  // O(1)

        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums,start,mid-1); // O(n/2)
        root.right = sortedArrayToBST(nums,mid+1,end);  // O(n/2)
        return root;
    }

    public static void main(String[] args) {
        SortedArrayToBST ob = new SortedArrayToBST();
        int[] nums = {-10,-3,0,5,9};
        //TreeNode.inorder(ob.sortedArrayToBST(nums));
        TreeNode.levelOrder(ob.sortedArrayToBST(nums));

    }
}
