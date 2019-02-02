package offer;

import java.util.LinkedList;
import java.util.List;

public class TreeNode {
    public int val = 0;
    public TreeNode left = null;
    public TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }

    public static TreeNode getTreeByLevelOrder(int[] arr,int nullVal){
        if(arr.length==0||arr[0]==nullVal) return null;
        int k=0;
        TreeNode root=new TreeNode(arr[k++]);
        LinkedList<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode current=queue.poll();
            if(arr[k]!=nullVal) {
                current.left = new TreeNode(arr[k]);
                queue.add(current.left);
            }
            k++;
            if(arr[k]!=nullVal){
                current.right = new TreeNode(arr[k]);
                queue.add(current.right);
            }
            k++;
        }
        return root;
    }
}
