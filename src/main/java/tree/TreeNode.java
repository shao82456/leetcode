package tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private static final int NULL_VALUE = -1;
    int val;
    TreeNode left;
    TreeNode right;


    TreeNode(int x) {
        val = x;
    }

    public static TreeNode buildByPre(int[] arr) {
        List<Integer> queue = new ArrayList<>();
        for (int num : arr) queue.add(num);
        return buildByPre1(queue);
    }

    private static TreeNode buildByPre1(List<Integer> queue) {
        TreeNode root = null;
        int val = queue.remove(0);
        if (val != NULL_VALUE) {
            root = new TreeNode(val);
            root.left = buildByPre1(queue);
            root.right = buildByPre1(queue);
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

    public static void preview1(TreeNode root, StringBuilder sb){
        if(root!=null){
            sb.append(root.val+" ");
            preview1(root.left,sb);
            preview1(root.right,sb);
        };
    }
}
