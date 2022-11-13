/*
Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges between them.

Example 1:
Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].

Example 2:

Input: root = [1,2]
Output: 1

Constraints:

The number of nodes in the tree is in the range [1, 104].
-100 <= Node.val <= 100

 */
/*
TC: O(n * n)  - O(n) for every node to find height then for n nodes = n * n
SC : O(1)
 */
package Trees;

public class DiameterBinaryTree {
    static int maxDiaSoFar = Integer.MIN_VALUE;
    public static int longestDiameterOfBinaryTreeFromAnyNode(TreeNode root) {

        if(root == null){
            return 0;
        }
        int lheight = TreeNode.getHeight(root.left);
        int rheight = TreeNode.getHeight(root.right);
        maxDiaSoFar = Integer.max(maxDiaSoFar ,(lheight + rheight));
        longestDiameterOfBinaryTreeFromAnyNode(root.left);
        longestDiameterOfBinaryTreeFromAnyNode(root.right);
        return maxDiaSoFar;
    }
    public static int longestDiameterOfBinaryTreeFromRoot(TreeNode root) {

        if(root == null || TreeNode.isLeaf(root)){
            return 0;
        }
        int lheight = TreeNode.getHeight(root.left);
        int rheight = TreeNode.getHeight(root.right);
        longestDiameterOfBinaryTreeFromRoot(root.left);
        longestDiameterOfBinaryTreeFromRoot(root.right);
        return 1+lheight+rheight;
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);

        System.out.println("From Any node");
        System.out.println(longestDiameterOfBinaryTreeFromAnyNode(root));

        maxDiaSoFar = Integer.MIN_VALUE; // reinitialize for re-using the maxDiasoFar class variable.
        System.out.println(longestDiameterOfBinaryTreeFromAnyNode(root1));
        System.out.println("From Root");
        System.out.println(longestDiameterOfBinaryTreeFromRoot(root));
        System.out.println(longestDiameterOfBinaryTreeFromRoot(root1));


    }
}
