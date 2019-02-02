package offer;

import java.util.LinkedList;

public class SeriallizeTree {

    public String Serialize(TreeNode root) {
        if (root == null) return null;

        StringBuilder sb = new StringBuilder();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (null == cur) {
                sb.append("!#");
                continue;
            }
            sb.append(cur.val + "#");
            queue.offer(cur.left);
            queue.offer(cur.right);
        }

        return sb.toString();
    }

    TreeNode Deserialize(String str) {
        if(str==null||str.length()==0) return null;
        String[] ele=str.split("#");

        LinkedList<TreeNode> queue=new LinkedList<>();
        int k=0;
        TreeNode root=new TreeNode(Integer.parseInt(ele[k++]));
        queue.add(root);

        while(!queue.isEmpty()){
            TreeNode cur=queue.poll();
            if(!ele[k].equals("!")){
                cur.left=new TreeNode(Integer.parseInt(ele[k]));
                queue.offer(cur.left);
            }
            k++;
            if(!ele[k].equals("!")){
                cur.right=new TreeNode(Integer.parseInt(ele[k]));
                queue.offer(cur.right);
            }
            k++;
        }
        return root;
    }

}
