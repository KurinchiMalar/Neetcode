/*
Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]

Example 2:

Input: root = [1]
Output: [[1]]
Example 3:

Input: root = []
Output: []

Constraints:

The number of nodes in the tree is in the range [0, 2000].
-1000 <= Node.val <= 1000
-
 */
/*
https://leetcode.com/problems/binary-tree-level-order-traversal/
TC : O(n/2) = O(n) ......(n nodes can have maximum n/2 levels in a binary tree)
SC : O(n)  queue
 */
package Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {
    //BFS - Breadth First Search
    public static List<List<Integer>> levelOrder(TreeNode root) {

        if(root == null){
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> resultList =  new ArrayList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            List<Integer> currentLevelList = new ArrayList<Integer>();
            int levelCount = queue.size();
            for(int i=0 ; i < levelCount ; i++){
                TreeNode temp = queue.poll();
                currentLevelList.add(temp.val);
                if(temp.left != null)queue.add(temp.left);
                if(temp.right != null)queue.add(temp.right);
            }
            resultList.add(currentLevelList);
        }
        return resultList;
    }
    public static void levelOrderTest(TreeNode root) {

        if(root == null){
            return;
        }
        //List<List<Integer>> resultList =  new ArrayList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            //List<Integer> currentLevelList = new ArrayList<Integer>();
            //int levelCount = queue.size();
            //for(int i=0 ; i < levelCount ; i++){
                TreeNode temp = queue.poll();
                System.out.println(temp.val+ " ");
                //currentLevelList.add(temp.val);
                if(temp.left != null)queue.add(temp.left);
                if(temp.right != null)queue.add(temp.right);
            //}
            //resultList.add(currentLevelList);
        }
        //return resultList;
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        List<List<Integer>> resultList = levelOrder(root);
        for(List<Integer> innerList:resultList){
            for(Integer i : innerList){
                System.out.print(" "+i);
            }
        }
        System.out.println("\n");

        //Another implementation where complexity is O(n * n)
        TreeNode.printLevelOrder(root);

        System.out.println("Test");
        levelOrderTest(root);
    }

}
