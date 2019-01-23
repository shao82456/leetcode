package offer;

import java.util.LinkedList;
import java.util.List;

public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }

    public static TreeNode getTreeByLevelOrder(int[] arr,int nullVal){
        if(arr.length==0||arr[0]==nullVal) return null;
        TreeNode root=new TreeNode(arr[0]);

        int k=1;
        LinkedList<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        while (k<arr.length&&!queue.isEmpty()){
            TreeNode current=queue.poll();
            if(arr[k++]!=nullVal) {
                current.left = new TreeNode(arr[k-1]);
                queue.add(current.left);
            }
            if(k<arr.length&&arr[k++]!=nullVal){
                current.right = new TreeNode(arr[k-1]);
                queue.add(current.right);
            }
        }
        return root;
    }
}
