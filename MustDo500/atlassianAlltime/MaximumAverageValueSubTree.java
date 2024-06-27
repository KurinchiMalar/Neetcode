package MustDo500.atlassianAlltime;
import Trees.TreeNode;
/*
https://www.naukri.com/code360/problems/maximum-average-value-of-a-subtree_1281431
https://www.naukri.com/code360/library/maximum-average-of-subtree-values-in-a-given-binary-tree
 */
class ResultType{
    int sum;
    int count;

    ResultType(int sum, int count){
        this.sum = sum;
        this.count = count;
    }
}

public class MaximumAverageValueSubTree {

    static double maxAvg = Double.NEGATIVE_INFINITY;
    static TreeNode maxNode = null;

    /*public int countOfNodes(TreeNode root){
        if(root == null) return 0;
        return 1 + countOfNodes(root.left)+countOfNodes(root.right);
    }

    public int sumOfNodes(TreeNode root){
        if(root == null) return 0;
        int sumLeft = sumOfNodes(root.left);
        int sumRight = sumOfNodes(root.right);

        return root.val+sumLeft+sumRight;
    }

    // This calculates for entire tree and not for every subtree
    public double maxSubtreeAverage_Brute(TreeNode root){
        if(root == null) return 0.0;
        double avg = (double)sumOfNodes(root)/countOfNodes(root);
        maxAvgBrute = Double.max(avg,maxAvgBrute);
        return avg;
    }*/
    /*
    IDEA : Do postorder traversal , (left, right, then root)
           calculate the sum and count , compute avg and compare against maxAvg

     TC : O(n)
     SC : O(n)
     */

    public ResultType maxSubtreeAverage(TreeNode root){

        if(root == null) return new ResultType(0,0);

        ResultType l = maxSubtreeAverage(root.left);
        ResultType r = maxSubtreeAverage(root.right);

        int curSum = root.val + l.sum + r.sum;
        int curCount = 1 + l.count + r.count;
        double avg = (double) curSum/curCount;

        // update maxAvg if possible
        if(avg > maxAvg){
            maxAvg = avg;
            maxNode = root;
        }

        return new ResultType(curSum,curCount);
    }



    public static void main(String[] args) {
        MaximumAverageValueSubTree ob = new MaximumAverageValueSubTree();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);

        ResultType r = ob.maxSubtreeAverage(root);
        System.out.println(r.sum);
        System.out.println(r.count);
        System.out.println(maxAvg);
        System.out.println("**************************************************************");
        maxNode =null;
        maxAvg = Double.NEGATIVE_INFINITY;
        TreeNode root1 = new TreeNode(20);
        root1.left = new TreeNode(12);
        root1.right = new TreeNode(18);
        root1.left.left = new TreeNode(11);
        root1.left.right = new TreeNode(3);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(8);
        ResultType r1 = ob.maxSubtreeAverage(root1);
        System.out.println(r1.sum);
        System.out.println(r1.count);
        System.out.println(maxAvg);
        System.out.println("**************************************************************");
        maxNode =null;
        maxAvg = Double.NEGATIVE_INFINITY;
        TreeNode root2 = new TreeNode(15);
        root2.left = new TreeNode(8);
        root2.right = new TreeNode(6);
        ResultType r2 = ob.maxSubtreeAverage(root2);
        System.out.println(r2.sum);
        System.out.println(r2.count);
        System.out.println(maxAvg);


    }
}
