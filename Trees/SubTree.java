/*
Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.

A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself.

Example 1:
Input: root = [3,4,5,1,2], subRoot = [4,1,2]
Output: true

Example 2:
Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
Output: false

Constraints:

The number of nodes in the root tree is in the range [1, 2000].
The number of nodes in the subRoot tree is in the range [1, 1000].
-104 <= root.val <= 104
-104 <= subRoot.val <= 104

 */
/*
https://leetcode.com/problems/subtree-of-another-tree/
If assume m is the number of nodes in the 1st tree and n is the number of nodes in the 2nd tree, then

Time complexity: O(m * n),
       worst case: for each node in the 1st tree, we need to check if isSame(Node s, Node t).
       Total m nodes, isSame(...) takes O(n) worst case

Space complexity: O(height of 1str tree)(Or you can say: O(m) for worst case, O(log m) for average case)
 */
package Trees;

public class SubTree {
    public static boolean isSameTree(TreeNode p, TreeNode q){
        if(p==null && q==null)return true;
        if(p==null || q==null)return false;
        if(p.val != q.val)return false;
        return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }

    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root == null)return false;
        if(isSameTree(root,subRoot)){ // see if both trees are equal
            return true;
        }
        return isSubtree(root.left,subRoot) || isSubtree(root.right,subRoot);
    }
    public static void main(String[] args){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(0);

        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(2);
        System.out.println(isSubtree(root,root1));

        root.left.right.left = null;
        System.out.println(isSubtree(root,root1));

    }
}
