/*
Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.

Example 1:
Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]

Example 2:
Input: preorder = [-1], inorder = [-1]
Output: [-1]

Constraints:

1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder and inorder consist of unique values.
Each value of inorder also appears in preorder.
preorder is guaranteed to be the preorder traversal of the tree.
inorder is guaranteed to be the inorder traversal of the tree.

Algo:
-----
The basic idea is here:
Say we have 2 arrays, PRE and IN.
Preorder traversing implies that PRE[0] is the root node.
Then we can find this PRE[0] in IN, say it's IN[5].
Now we know that IN[5] is root, so we know that IN[0] - IN[4] is on the left side, IN[6] to the end is on the right side.
Recursively doing this on subarrays, we can build a tree out of it :)

References:
------------
Good video to Understand how rightChild preStart is calculated : https://www.youtube.com/watch?v=-6ims3lgVA4
the immediate right child index is preStart + numsOnLeft + 1
(remember in preorder traversal array root is always ahead of children nodes but you don't know which one is the left child which one is the right, and this is why we need inorder array)

numsOnLeft = root - inStart. (in inorder array)
 */
/*
https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
TC : O(n)
SC : O(n)
 */
package Trees;

public class ConstructTreeFromPreOrderInOrder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(0,0,inorder.length-1,preorder,inorder);
    }
    public TreeNode helper(int preStart,int inStart,int inEnd,int[] preorder,int[] inorder){
        //boundary checks
        if(preStart >= preorder.length || inStart > inEnd){
            return null;
        }
        // preorder = root,left,right (Root is at the start)
        TreeNode root = new TreeNode(preorder[preStart]);

        // identify the index of root in inorder array , lets call it inIndex
        int inIndex = 0;
        for(int i=inStart; i <= inEnd ; i++){
            if(inorder[i] == root.val){
                inIndex = i;
                break;
            }
        }
        // preStart+1 is left in preorder array
        // inStart to inIndex-1 is the left subtree from root in inorder array
        root.left = helper(preStart+1,inStart,inIndex-1,preorder,inorder);
        // preStart = preStart + (numsOnLeft from root in inorder arr) + 1 is right in preorder array
        // inIndex+1 to inEnd is the right subtree from root in inorder array
        int numsOnLeft = inIndex-inStart;
        root.right = helper(preStart + numsOnLeft +1 , inIndex+1, inEnd,preorder,inorder);
        return root;
    }
}
