package offer;

public class TestTree {
    public static void main(String[] args) {
        int[] arr={1,3,-1,7,5,-1,-1,6,-1,-1,-1};
        TreeNode root= TreeNode.getTreeByLevelOrder(arr,-1);
        BSTkNode test=new BSTkNode();
    }
    public static void preorder(TreeNode root){
        if(root!=null){
            System.out.println(root.val);
            preorder(root.left);
            preorder(root.right);
        }
    }
}
