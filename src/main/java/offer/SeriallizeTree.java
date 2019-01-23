package offer;

public class SeriallizeTree {
        int k;
        String Serialize(TreeNode root) {
            StringBuilder sb=new StringBuilder();
            preOrder(root,sb);
            return sb.toString();
        }
        TreeNode Deserialize(String str) {
            String[] values=str.split("#");
            k=0;
            TreeNode root=construct(values);
            return root;
        }

        TreeNode construct(String[] values){
            String t=values[k++];
            if(t.equals("!")) return null;
            else{
                int val=Integer.parseInt(t);
                TreeNode root=new TreeNode(val);
                root.left=construct(values);
                root.right=construct(values);
                return root;
            }
        }
        public void preOrder(TreeNode root,StringBuilder sb) {
            if(root!=null) {
                sb.append(root.val+"#");
                preOrder(root.left,sb);
                preOrder(root.right,sb);
            }else
                sb.append("!#");
        }
    }
