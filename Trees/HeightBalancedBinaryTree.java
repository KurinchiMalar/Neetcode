/*
Given a binary tree, determine if it is
height-balanced
.
A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: true

Example 2:
Input: root = [1,2,2,3,3,null,null,4,4]
Output: false

Examples 3:
Input: root = []
Output: true

Constraints:

The number of nodes in the tree is in the range [0, 5000].
-104 <= Node.val <= 104

 */
/*
TC : O(n * n)
SC : O(1)
 */
package Trees;

public class HeightBalancedBinaryTree {
    static boolean isHeightBalanced = true;
    public static boolean isHeightBalanced(TreeNode root) {
        if(root==null){
            return true;
        }
        int lheight = TreeNode.getHeight(root.left);
        int rheight = TreeNode.getHeight(root.right);
        if(Math.abs(lheight - rheight) > 1){
            isHeightBalanced = false;
            return isHeightBalanced;
        }
        isHeightBalanced(root.left);
        isHeightBalanced(root.right);
        return isHeightBalanced;
    }
    public static void main(String[] args){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(isHeightBalanced(root));
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(3);
        root1.left.left.left = new TreeNode(4);
        root1.left.left.right= new TreeNode(4);
        System.out.println(isHeightBalanced(root1));
    }
}
