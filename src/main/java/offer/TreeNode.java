package offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreeNode {
    private static int NULLVAL=-1;
    public int val = 0;
    public TreeNode left = null;
    public TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }

    public static TreeNode buildTreeByLevel(int[] arr){
        if(arr.length==0||arr[0]==NULLVAL) return null;
        int k=0;
        TreeNode root=new TreeNode(arr[k++]);
        LinkedList<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()&&k<arr.length){
            TreeNode current=queue.poll();
            if(arr[k]!=NULLVAL) {
                current.left = new TreeNode(arr[k]);
                queue.add(current.left);
            }
            k++;
            if(arr[k]!=NULLVAL){
                current.right = new TreeNode(arr[k]);
                queue.add(current.right);
            }
            k++;
        }
        return root;
    }

    public static String preview(TreeNode root){
        StringBuilder res=new StringBuilder();
        preview1(root,res);
        return res.toString();
    }
    public static String inview(TreeNode root){
        StringBuilder res=new StringBuilder();
        ArrayList<TreeNode> stk=new ArrayList<>();
        TreeNode cur=root;
        while (cur!=null||!stk.isEmpty()){
            while(cur!=null){
                stk.add(cur);
                cur=cur.left;
            }
            cur=stk.remove(stk.size()-1);
            res.append(cur.val+" ");
            cur=cur.right;
        }
        return res.toString();
    }


    public static void preview1(TreeNode root,StringBuilder sb){
        if(root!=null){
            sb.append(root.val+" ");
            preview1(root.left,sb);
            preview1(root.right,sb);
        };
    }


    public static void inview1(TreeNode root,StringBuilder sb){
        if(root!=null){
            inview1(root.left,sb);
            sb.append(root.val+" ");
            inview1(root.right,sb);
        };
    }

    private static int k;
    public static TreeNode buildByPre(int[] arr){
        k=0;
        return buildByPre1(arr);
    }
    public static TreeNode buildByPre(int[] arr,int NULLVAL){
        TreeNode.NULLVAL=NULLVAL;
        return buildByPre(arr);
    }
    private static TreeNode buildByPre1(int[] arr){
        TreeNode root=null;
        int val=arr[k++];
        if(val!=NULLVAL){
            root=new TreeNode(val);
            root.left=buildByPre1(arr);
            root.right=buildByPre1(arr);
        }
        return root;
    }
}
