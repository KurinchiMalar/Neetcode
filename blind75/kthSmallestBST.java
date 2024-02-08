package blind75;

import java.util.Stack;
import Trees.TreeNode;
/*
TC : O(n)
SC : O(h)
 */
public class kthSmallestBST {

    public static int kthSmallestBstIterative(TreeNode root, int k){

        if(root == null) return -1;

        int count1 = 0;
        Stack<TreeNode> stack = new Stack<>();

        while(root != null || !stack.isEmpty()) {
            while(root != null){
                stack.push(root);
                root = root.left;
            }

            TreeNode curNode = stack.pop(); // smallest
            count1++;
            if(count1 == k){
                return curNode.val;
            }
            if(curNode.right != null){
                root = curNode.right;
            }
        }
        return -1;
    }

    static int count = 0;
    static int result1 = -1;
    public static int kthSmallestBstRecursive(TreeNode root, int k){
        count = k;

        if(root == null) return -1;

        helper(root);
        return result1;

    }
    public static void helper(TreeNode root){
        if(root.left != null){
            helper(root.left);
        }
        count--;
        if(count == 0){
            result1 = root.val;
            return;
        }
        if(root.right != null) {
            helper(root.right);
        }
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new Trees.TreeNode(6);
        root.left.left = new Trees.TreeNode(2);
        root.left.right = new Trees.TreeNode(4);
        root.left.left.left = new Trees.TreeNode(1);
       // System.out.println(kthSmallestBstIterative(root,5));

        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.left.right = new TreeNode(2);
        System.out.println(kthSmallestBstRecursive(root1,3));

    }
}
