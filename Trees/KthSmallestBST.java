/*
Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.

Example 1:
Input: root = [3,1,4,null,2], k = 1
Output: 1

Example 2:
Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3

Constraints:

The number of nodes in the tree is n.
1 <= k <= n <= 104
0 <= Node.val <= 104
 */
/*
https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
TC : O(n)
SC : O(n) worstcase.
 */
package Trees;

import java.util.Stack;

public class KthSmallestBST {
    public static int kthSmallest(TreeNode root, int k) {
        if(root == null){
            return -1;
        }
        int count = 0;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        //Inorder = left, root, right
        while(root!= null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            // root null . reached left most.
            TreeNode removedNode = stack.pop();
            count++;
            if(count == k)return removedNode.val;
            root = removedNode.right;
        }
        return -1;
    }
    public static void main(String[] args){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);
        System.out.println(kthSmallest(root,3));

        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.left.right = new TreeNode(2);
        System.out.println(kthSmallest(root1,1));

    }
}
