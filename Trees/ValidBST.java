/*
Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left
subtree
 of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

Example 1:
Input: root = [2,1,3]
Output: true

Example 2:
Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.

Constraints:

The number of nodes in the tree is in the range [1, 104].
-231 <= Node.val <= 231 - 1
 */
/*
https://leetcode.com/problems/validate-binary-search-tree/
TimeComplexity : O(n)
Space Complexity : O(n) , bestcase : O(log n ) depth of the tree

There is no additional data used other than the method variables and the return value, so indeed, all memory is "cost of recursion".
The total cost would hence be linearly proportional to the depth of the tree.
In a balanced binary search tree, the depth is O(log n), so indeed, the space complexity would be O(log n) too.
However, in general a BST is not necessarily balanced, it could even be a chain of length n,
if the root is the minimum, its right child is the second smallest element, and so on. In that case the space complexity of this recursion is O(n).
https://stackoverflow.com/questions/21546611/space-complexity-of-validation-of-a-binary-search-tree
 */
package Trees;

public class ValidBST {

    public boolean isValidBST(TreeNode root){
        return isValidBSTUtil(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }
    public boolean isValidBSTUtil(TreeNode root,long min,long max) {

        if(root == null) return true;

        if(root.val <= min) return false;
        if(root.val >= max) return false;

        return isValidBSTUtil(root.left,min,root.val) &&
                isValidBSTUtil(root.right,root.val,max);
    }
}

