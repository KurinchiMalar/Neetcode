
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

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode() {
    }
    public TreeNode(int val) {
        this.val = val;
    }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    public static void printPreOrder(TreeNode root){
        if(root==null)return;
        System.out.print(root.val+" ");
        printPreOrder(root.left);
        printPreOrder(root.right);
    }
    public static int getHeight(TreeNode root){
        if(root==null)return 0;
        int lheight = getHeight(root.left);
        int rheight = getHeight(root.right);
        return (lheight > rheight) ? lheight+1:rheight+1;
    }

    public static void printCurrentLevel(TreeNode root, int level){
        if(root == null)return;
        if(level == 1)System.out.print(root.val+" ");
        else if (level > 1) {
            printCurrentLevel(root.left,level - 1);   // -1 is because at Level 2 for example...we call this method as (root,2)....you keep decrementing until your level 2 is reached. it becomes 0 when level 2 from root is reached. then you print the data.
            printCurrentLevel(root.right,level - 1);
        }
    }
    //https://www.youtube.com/watch?v=lXIk1PXb1Jc
    public static void printLevelOrder(TreeNode root){
        int level = getHeight(root);
        for(int i=0 ; i <= level ; i++){
            printCurrentLevel(root,i);
        }
    }
}
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
