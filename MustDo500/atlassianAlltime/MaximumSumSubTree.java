package MustDo500.atlassianAlltime;

import Trees.TreeNode;

/*
https://www.geeksforgeeks.org/find-largest-subtree-sum-tree/

Practice : https://www.geeksforgeeks.org/problems/largest-subtree-sum-in-a-tree/1
 */
/*
IDEA :
Do post order traversal of the binary tree.
At every node, find left subtree value and right subtree value recursively.
 The value of subtree rooted at current node is equal to sum of current node value, left node subtree sum and right node subtree sum.
 Compare current subtree sum with overall maximum subtree sum so far.

TC:  O(n)
SC : O(n) recursion stack

 */
public class MaximumSumSubTree {
    static int result = Integer.MIN_VALUE;

    public static int helper(TreeNode root, int res){
        if(root == null) return 0;

        int curSum = root.val + helper(root.left,res) + helper(root.right,res);
        //result = Math.max(curSum,result);
        result = Math.max(curSum,result);
        System.out.println("Result: "+result);
        return curSum;

    }
    public static int findLargestSubtreeSum(TreeNode root) {
        if(root == null) return 0;
        helper(root,result);
        return result;
    }

    public static void main(String[] args) {
        MaximumSumSubTree ob = new MaximumSumSubTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println(ob.findLargestSubtreeSum(root));// Opt 28
        result = Integer.MIN_VALUE;
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(-2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(-6);
        root1.right.right = new TreeNode(2);
        System.out.println(ob.findLargestSubtreeSum(root1));// Opt 7

    }
}
