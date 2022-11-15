package Trees;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode() {
    }
    public TreeNode(int val) {
        this.val = val;
    }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    public static void printPreOrder(TreeNode root){
        if(root==null)return;
        System.out.print(root.val+" ");
        printPreOrder(root.left);
        printPreOrder(root.right);
    }
    public static int getHeight(TreeNode root){
        if(root==null)return 0;
        int lheight = getHeight(root.left);
        int rheight = getHeight(root.right);
        return (lheight > rheight) ? lheight+1:rheight+1;
    }

    public static boolean isLeaf(TreeNode root){
        if(root == null)return false;
        if(root.left == null && root.right == null){
            return true;
        }
        return false;
    }
    public static void printCurrentLevel(TreeNode root, int level){
        if(root == null)return;
        if(level == 1)System.out.print(root.val+" ");
        else if (level > 1) {
            printCurrentLevel(root.left,level - 1);   // -1 is because at Level 2 for example...we call this method as (root,2)....you keep decrementing until your level 2 is reached. it becomes 0 when level 2 from root is reached. then you print the data.
            printCurrentLevel(root.right,level - 1);
        }
    }
    //https://www.youtube.com/watch?v=lXIk1PXb1Jc
    public static void printLevelOrder(TreeNode root){
        int level = getHeight(root);
        for(int i=1 ; i <= level ; i++){
            printCurrentLevel(root,i);
        }
        System.out.println();
    }
}
