package MustDo500.atlassianAlltime;

import Trees.TreeNode;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/*
Given a binary tree, collect a tree's nodes as if you were doing this:
Collect and remove all leaves,
 repeat until the tree is empty.



Example:

Input: [1,2,3,4,5]

          1
         / \
        2   3
       / \
      4   5

Output: [[4,5,3],[2],[1]]


Explanation:

1. Removing the leaves [4,5,3] would result in this tree:

          1
         /
        2


2. Now removing the leaf [2] would result in this tree:

          1


3. Now removing the leaf [1] would result in the empty tree:

          []
 */

public class FindLeavesOfBT {

    public void levelorder(TreeNode root){
        if(root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            System.out.print(" "+cur.val);
            if(cur.left != null)queue.offer(cur.left);
            if(cur.right != null)queue.offer(cur.right);
        }
    }

    public void inorder(TreeNode root){
        if(root == null) return;
        inorder(root.left);
        System.out.print(" "+root.val);
        inorder(root.right);
    }
    public boolean isLeaf(TreeNode root){
        // root null is handled in caller
        if(root.left == null && root.right == null){
            return true;
        }
        return false;
    }

    public void printLeaves(TreeNode root){
        if(root == null) return;
        if(isLeaf(root)){
            System.out.print(" "+root.val);
        }
        if(root.left != null) printLeaves(root.left);
        if(root.right != null) printLeaves(root.right);
    }
    /*
    Question : https://leetcode.ca/all/366.html
     https://leetcode.com/problems/find-leaves-of-binary-tree (Leet - premium)
    https://leetcode.ca/2016-11-30-366-Find-Leaves-of-Binary-Tree/

    TC : O(n log n ) (log n iterations with O(n) traversals ) for complete binary tree
         O(n * n ) for skewed

    SC : O(n)
     */
    public void dfs(TreeNode root, TreeNode prev,List<Integer> curList){  //  --------------> O(n) dfs traversal
        if(root == null) return;
        if(isLeaf(root)){
            curList.add(root.val);
            // remove this from tree
            if(prev.left == root){
                prev.left = null;
            }else{
                prev.right = null;
            }
        }
        dfs(root.left,root,curList);
        dfs(root.right,root,curList);
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        TreeNode prev = new TreeNode(0);
        prev.left = root; // make prev parent of root
        while(prev.left != null){ // until you have nodes in tree  --------------> O(log n) iterations
            List<Integer> curList = new ArrayList<>();
            dfs(prev.left,prev,curList);
            result.add(curList);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(8);
        root.right.left.left = new TreeNode(6);
        root.right.left.right = new TreeNode(7);
        root.right.right.left = new TreeNode(9);
        root.right.right.right = new TreeNode(10);
        FindLeavesOfBT ob = new FindLeavesOfBT();
        ob.inorder(root);
        System.out.println();
        ob.levelorder(root);
        System.out.println();
        System.out.println("************ Print Leaves DFS **********");
        ob.printLeaves(root);
        System.out.println();
        System.out.println("************** Find Leaves DFS *************");
        System.out.println(ob.findLeaves(root));

        //TreeNode root1 = new TreeNode(1);

    }
}
