/*
Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
Example 1:
Input: root = [1,2,3,null,5,null,4]
Output: [1,3,4]
Example 2:

Input: root = [1,null,3]
Output: [1,3]
Example 3:

Input: root = []
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
 */
/*
Time Complexity : O(n)
Space Complexity : O(n)
 */
package Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RightSideView {
    public static List<Integer> rightSideView(TreeNode root) {
        if(root == null){
            return new ArrayList<Integer>();
        }
        List<Integer> resultList = new ArrayList<Integer>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while(!queue.isEmpty()){
            int levelCount = queue.size();
            for(int i=0;i < levelCount; i++){
                TreeNode temp = queue.poll();
                if(temp.left != null)queue.add(temp.left);
                if(temp.right != null)queue.add(temp.right);
                if(i == levelCount-1){
                    resultList.add(temp.val); // add the tail of queue. Basically the right-most of every level will be the tail of queue at that level.
                }
            }
        }
        return resultList;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);

        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        List<Integer> result = rightSideView(root1);
        for(Integer i:result){
            System.out.print(" "+i);
        }
    }
}
