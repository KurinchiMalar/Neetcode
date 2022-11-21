/*
Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.

Return the number of good nodes in the binary tree.
Example 1:
Input: root = [3,1,4,3,null,1,5]
Output: 4
Explanation: Nodes in blue are good.
Root Node (3) is always a good node.
Node 4 -> (3,4) is the maximum value in the path starting from the root.
Node 5 -> (3,4,5) is the maximum value in the path
Node 3 -> (3,1,3) is the maximum value in the path.

Example 2:
Input: root = [3,3,null,4,2]
Output: 3
Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.

Example 3:
Input: root = [1]
Output: 1
Explanation: Root is considered as good.

Constraints:

The number of nodes in the binary tree is in the range [1, 10^5].
Each node's value is between [-10^4, 10^4].
 */
/*
TC : O(n)
SC : O(h)  ... h will be log n for a complete binary tree, n for a 1-ary tree.
 */
package Trees;

public class CountGoodNodes {
    static int result = 0;
    public static int goodNodes(TreeNode root) {
        return goodNodesActual(root,Integer.MIN_VALUE);
    }

    public static int goodNodesActual(TreeNode root,int max) {
        if(root == null ){
            return 0;
        }
        if(root.val >= max){
            result += 1;
        }
        goodNodesActual(root.left,Integer.max(max,root.val));
        goodNodesActual(root.right,Integer.max(max,root.val));
        return result;
    }

    // Another implementation.
    public static int goodNodesA(TreeNode root,int max) {
        if(root == null ){
            return 0;
        }
        if(root.val >= max){
            result = 1;
        }else{
            result = 0;
        }
        result += goodNodesA(root.left,Integer.max(max,root.val));
        result += goodNodesA(root.right,Integer.max(max,root.val));
        return result;
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(5);

        System.out.println(goodNodes(root));
        result = 0;
        System.out.println(goodNodesA(root,0));

    }
}
