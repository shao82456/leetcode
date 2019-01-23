package offer;

import sun.reflect.generics.tree.Tree;

public class TestTree {
    public static void main(String[] args) {
        int[] arr={1,2,3,5,-1,4,6};
        TreeNode root= TreeNode.getTreeByLevelOrder(arr,-1);
        preorder(root);
    }
    public static void preorder(TreeNode root){
        if(root!=null){
            System.out.println(root.val);
            preorder(root.left);
            preorder(root.right);
        }
    }
}
