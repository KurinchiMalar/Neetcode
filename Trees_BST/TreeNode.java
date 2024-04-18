package Trees_BST;


import java.util.LinkedList;
import java.util.Queue;

public class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(){}
    TreeNode(int val){
        this.val = val;
        this.left = null;
        this.right = null;
    }

    TreeNode(int val, TreeNode left,TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
    public static void inorder(TreeNode root){
        if(root == null ) {
            return;
        }
        System.out.print(root.val+ " ");
        inorder(root.left);
        inorder(root.right);
    }

    public static void levelOrder(TreeNode root){
        if(root == null)return;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while(!queue.isEmpty()){

            int curLevelSize = queue.size();
            for(int i = 0 ; i < curLevelSize; i++){ // poll this many times
                TreeNode cur = queue.poll();
                System.out.print(cur.val+" ");
                if(cur.left != null)queue.offer(cur.left);
                if(cur.right != null)queue.offer(cur.right);

            }
        }
    }
}
