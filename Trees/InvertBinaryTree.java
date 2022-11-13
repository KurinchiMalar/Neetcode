
/*
https://leetcode.com/problems/invert-binary-tree/description/
Given the root of a binary tree, invert the tree, and return its root.

ex 1 : Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]

ex 2 : Input: root = [2,1,3]
Output: [2,3,1]

ex 3 : Input: root = []
Output: []
Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
 */
/*
TC : O(n)
SC : O(n)
 */
package Trees;
public class InvertBinaryTree {
    public static boolean isLeaf(TreeNode root){
        if(root.left == null && root.right == null){
            return true;
        }
        return false;
    }
    public static TreeNode  invertTree(TreeNode root) {

        if(root == null)return null;
        if(isLeaf(root))return root;
        invertTree(root.left);
        invertTree(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }
    public static void main(String[] args){
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        System.out.print("Input: ");TreeNode.printLevelOrder(root);
        System.out.print(" Output: ");TreeNode.printLevelOrder(invertTree(root));

    }
}
