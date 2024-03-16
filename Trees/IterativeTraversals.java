package Trees;

import java.util.Stack;

public class IterativeTraversals {
    static Stack<TreeNode> stk = new Stack<TreeNode>();
    static Stack<TreeNode> stk2 = new Stack<TreeNode>();

    /*
    TC : O(n)
    SC : O(h)
    The space complexity is determined by the height of the tree, h, because, in the worst case, the stack can contain all the nodes on one branch of the tree (from the root to the leaf).
    For a balanced tree, h is log(n), and for a skewed tree (worst case), h can be n.
     */
    public static void inOrderTraversal(TreeNode root) {

        if (root == null) return;
        TreeNode cur = root;

        while (cur != null || !stk.isEmpty()) {

            while (cur != null) {
                stk.push(cur);
                cur = cur.left;
            }

            cur = stk.pop();
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
    }
    /*
    TC : O(n)
    SC : O(h)
 The space complexity is determined by the height of the tree, h, because, in the worst case, the stack can contain all the nodes on one branch of the tree (from the root to the leaf).
 For a balanced tree, h is log(n), and for a skewed tree (worst case), h can be n.
  */
        public static void preOderTraversal(TreeNode root){

            if (root == null) return;
            TreeNode cur = root;
            stk.push(root);
            while ( !stk.isEmpty()) {
                cur = stk.pop();
                System.out.print(cur.val + " ");
                if(cur.right != null){
                    stk.push(cur.right);
                }
                if(cur.left != null){
                    stk.push(cur.left);
                }
            }
        }
    /*
    TC : O(n)
    SC : O(n) //  In the worst case, each of the two stacks could hold up to n nodes at the same time (particularly when the tree is skewed). Therefore, the space complexity is O(n).
     2 stacks
     */
    public static void postOderTraversal(TreeNode root){

        if (root == null) return;

        TreeNode cur = root;
        stk.push(cur);

        while(!stk.isEmpty()){
            cur = stk.pop();
            stk2.push(cur);

            if(cur.left != null){
                stk.push(cur.left);
            }
            if(cur.right != null){
                stk.push(cur.right);
            }
        }
        while(!stk2.isEmpty()){
            cur = stk2.pop();
            System.out.print(cur.val + " ");
        }
    }
    /*
    TC : O(n)
    SC : O(h)
     */

    public static void postOrderTraversal1Stack(TreeNode root){
        if(root == null) return;

        TreeNode cur = root;

        while(cur != null || !stk.isEmpty()){

            if(cur != null){
                stk.push(cur);
                cur = cur.left;
            }else{
                TreeNode temp = stk.peek().right;

                if(temp == null){ // both left and right done , you can print this
                    temp = stk.pop();
                    System.out.print(temp.val+" ");
                    // check if further unwinding of stack possible. If current node is at right of the stack's top element then that also can be popped.
                    while(!stk.isEmpty() && temp == stk.peek().right){
                        temp = stk.pop();
                        System.out.print(temp.val+" ");
                    }
                }else{
                    cur = temp;
                }
            }
        }

    }



    public static void main(String args[])
    {
        // Creating a binary tree and entering
        // the nodes
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(6);
        inOrderTraversal(root);
        System.out.println();
        stk.clear();
        preOderTraversal(root);
        System.out.println();
        stk.clear();
        postOderTraversal(root);
        stk.clear();
        System.out.println();
        postOrderTraversal1Stack(root);
    }

}
