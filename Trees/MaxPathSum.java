/*
A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.

Example 1:
Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.

Example 2:
Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.

Constraints:
The number of nodes in the tree is in the range [1, 3 * 104].
-1000 <= Node.val <= 1000
 */
/*
TC : O(n)
SC : O(n)  ...O(log n) when tree is balanced
 */
package Trees;

public class MaxPathSum {
    static int result = Integer.MIN_VALUE;
    public static int maxPathSum(TreeNode root) {
        dfs(root);
        return result;
    }
    public static int dfs(TreeNode root){
        if(root == null){
            return 0;
        }
        int leftMax = dfs(root.left);
        int rightMax = dfs(root.right);
        leftMax = Integer.max(leftMax,0);
        rightMax = Integer.max(rightMax,0);

        //calculate and compare the pathsum of path including the split
        result = Integer.max(result,root.val+leftMax+rightMax);

        //return to root without split in path.
        return root.val+Integer.max(leftMax,rightMax);
    }
    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(maxPathSum(root));

        TreeNode root1 = new TreeNode(-10);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        System.out.println(maxPathSum(root1));
    }
}
