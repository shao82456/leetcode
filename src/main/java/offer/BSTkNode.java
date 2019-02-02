package offer;

public class BSTkNode {
    int i=0;
    TreeNode KthNode(TreeNode pRoot, int k) {
        i=0;
        TreeNode res=null;
        return findK(pRoot,k-1);
    }

    private TreeNode findK(TreeNode pRoot, int k) {
        TreeNode res=null;
        if(pRoot!=null){
            res=findK(pRoot.left,k);
            if(null!=res) return res;
            if(k==i++) return pRoot;
            res=findK(pRoot.left,k);
            if(null!=res) return res;
        }
        return res;
    }
}
